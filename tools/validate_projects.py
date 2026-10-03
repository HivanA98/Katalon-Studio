#!/usr/bin/env python3
"""Static validation for every Katalon project in this repository.

Runs without Katalon Studio / Runtime Engine, so it is safe to execute on any CI agent.
It catches the mistakes that otherwise only surface at execution time:

* malformed XML in .prj / .tc / .ts / .rs / .glbl / .dat files
* test cases without a script (or scripts without a test case)
* test suites that point at test cases that no longer exist
* test suite collections that point at missing test suites
* findTestObject / Locator.repo / findTestCase / findTestData calls that reference missing entities
* GlobalVariable.<name> usages that are not declared in the default profile
* CSV files read through CsvData.read(...) that do not exist
* duplicated test case / test suite GUIDs

Usage:
    python tools/validate_projects.py            # validate every project
    python tools/validate_projects.py --project "Web Testing/SauceDemo"
"""
from __future__ import annotations

import argparse
import os
import re
import sys
import xml.etree.ElementTree as ET
from collections import Counter
from dataclasses import dataclass, field
from pathlib import Path

REPO_ROOT = Path(__file__).resolve().parent.parent
XML_SUFFIXES = {".prj", ".tc", ".ts", ".rs", ".glbl", ".dat"}
IGNORED_DIRS = {".git", "bin", "Libs", "Reports", ".gradle", "build", "output", ".cache"}

# 'literal' or "literal" without Groovy interpolation
_STR = r"""(?P<q>['"])(?P<value>[^'"$]+)(?P=q)"""
OBJECT_REF = re.compile(r"(?:findTestObject|Locator\.repo|repo)\(\s*" + _STR)
TEST_CASE_REF = re.compile(r"findTestCase\(\s*" + _STR)
TEST_DATA_REF = re.compile(r"findTestData\(\s*" + _STR)
CSV_REF = re.compile(r"CsvData\.read\(\s*" + _STR)
GLOBAL_REF = re.compile(r"GlobalVariable\.(\w+)")
GLOBAL_OPTIONAL_REF = re.compile(r"Config\.(?:get|text|integer|bool)\(\s*" + _STR)


@dataclass
class Report:
    errors: list[tuple[str, str]] = field(default_factory=list)
    warnings: list[tuple[str, str]] = field(default_factory=list)
    stats: dict[str, dict[str, int]] = field(default_factory=dict)

    def error(self, path: Path, message: str) -> None:
        self.errors.append((_rel(path), message))

    def warn(self, path: Path, message: str) -> None:
        self.warnings.append((_rel(path), message))


def _rel(path: Path) -> str:
    try:
        return path.resolve().relative_to(REPO_ROOT).as_posix()
    except ValueError:
        return path.as_posix()


def _walk(root: Path):
    for dirpath, dirnames, filenames in os.walk(root):
        dirnames[:] = [d for d in dirnames if d not in IGNORED_DIRS]
        for name in filenames:
            yield Path(dirpath) / name


def _strip_comments(source: str) -> str:
    source = re.sub(r"/\*.*?\*/", "", source, flags=re.S)
    return re.sub(r"(?m)^\s*//.*$", "", source)


def _entity_ids(base: Path, suffix: str) -> set[str]:
    if not base.is_dir():
        return set()
    return {p.relative_to(base).with_suffix("").as_posix() for p in _walk(base) if p.suffix == suffix}


def _normalise(ref: str, prefix: str) -> str:
    ref = ref.strip().replace("\\", "/")
    return ref[len(prefix):] if ref.startswith(prefix) else ref


def find_projects(only: str | None = None) -> list[Path]:
    projects = sorted({p.parent for p in _walk(REPO_ROOT) if p.suffix == ".prj"})
    if only:
        wanted = (REPO_ROOT / only).resolve()
        projects = [p for p in projects if p.resolve() == wanted]
    return projects


def validate_project(project: Path, report: Report) -> None:
    files = list(_walk(project))

    # 1. XML well-formedness ------------------------------------------------------------
    parsed: dict[Path, ET.Element] = {}
    for f in files:
        if f.suffix in XML_SUFFIXES:
            try:
                parsed[f] = ET.parse(f).getroot()
            except ET.ParseError as exc:
                report.error(f, f"Malformed XML: {exc}")

    test_cases = _entity_ids(project / "Test Cases", ".tc")
    test_suites = _entity_ids(project / "Test Suites", ".ts")
    objects = _entity_ids(project / "Object Repository", ".rs")
    data_files = _entity_ids(project / "Data Files", ".dat")

    # 2. Test case <-> script pairing --------------------------------------------------
    scripts_root = project / "Scripts"
    for tc in sorted(test_cases):
        script_dir = scripts_root / tc
        scripts = list(script_dir.glob("Script*.groovy")) if script_dir.is_dir() else []
        if len(scripts) != 1:
            report.error(project / "Test Cases" / f"{tc}.tc",
                         f"expected exactly one script in Scripts/{tc}, found {len(scripts)}")
    if scripts_root.is_dir():
        for script in scripts_root.rglob("Script*.groovy"):
            tc = script.parent.relative_to(scripts_root).as_posix()
            if tc not in test_cases:
                report.error(script, f"orphan script: no 'Test Cases/{tc}.tc'")

    # 3. GUID uniqueness ---------------------------------------------------------------
    guids: Counter[str] = Counter()
    for f, root in parsed.items():
        for tag in ("testCaseGuid", "testSuiteGuid"):
            node = root.find(tag)
            if node is not None and node.text:
                guids[node.text.strip()] += 1
    for guid, count in guids.items():
        if count > 1:
            report.error(project, f"GUID {guid} is used by {count} entities")

    # 4. Suites and collections --------------------------------------------------------
    for f, root in parsed.items():
        if f.suffix != ".ts":
            continue
        if root.tag == "TestSuiteEntity":
            for node in root.iter("testCaseId"):
                ref = _normalise(node.text or "", "Test Cases/")
                if ref not in test_cases:
                    report.error(f, f"suite references missing test case '{ref}'")
        elif root.tag == "TestSuiteCollectionEntity":
            for node in root.iter("testSuiteEntity"):
                ref = _normalise(node.text or "", "Test Suites/")
                if ref not in test_suites:
                    report.error(f, f"collection references missing test suite '{ref}'")

    # 5. Profiles ----------------------------------------------------------------------
    default_profile = project / "Profiles" / "default.glbl"
    globals_defined: set[str] = set()
    if default_profile in parsed:
        globals_defined = {
            (e.findtext("name") or "").strip() for e in parsed[default_profile].iter("GlobalVariableEntity")
        }

    # 6. Groovy references -------------------------------------------------------------
    groovy_files = [f for f in files if f.suffix == ".groovy"]
    for f in groovy_files:
        source = _strip_comments(f.read_text(encoding="utf-8", errors="replace"))
        for m in OBJECT_REF.finditer(source):
            ref = _normalise(m.group("value"), "Object Repository/")
            if ref not in objects:
                report.error(f, f"missing test object 'Object Repository/{ref}'")
        for m in TEST_CASE_REF.finditer(source):
            ref = _normalise(m.group("value"), "Test Cases/")
            if ref not in test_cases:
                report.error(f, f"missing test case 'Test Cases/{ref}'")
        for m in TEST_DATA_REF.finditer(source):
            ref = _normalise(m.group("value"), "Data Files/")
            if ref not in data_files:
                report.error(f, f"missing data file 'Data Files/{ref}'")
        for m in CSV_REF.finditer(source):
            if not (project / m.group("value")).is_file():
                report.error(f, f"missing CSV file '{m.group('value')}'")
        for m in GLOBAL_REF.finditer(source):
            name = m.group(1)
            if name not in globals_defined:
                report.error(f, f"GlobalVariable.{name} is not declared in Profiles/default.glbl")
        is_shared_core = "com/qa/core" in f.as_posix()  # optional settings with documented fallbacks
        for m in GLOBAL_OPTIONAL_REF.finditer(source):
            if not is_shared_core and m.group("value") not in globals_defined:
                report.warn(f, f"Config value '{m.group('value')}' is not in default profile (fallback is used)")

    keywords = [f for f in groovy_files if "Keywords" in f.relative_to(project).parts]
    report.stats[_rel(project)] = {
        "test cases": len(test_cases),
        "test suites": len(test_suites),
        "test objects": len(objects),
        "keyword classes": len(keywords),
    }


def write_summary(report: Report) -> None:
    lines = ["## Katalon static validation", "",
             "| Project | Test cases | Test suites | Test objects | Keyword classes |",
             "|---|---:|---:|---:|---:|"]
    for project, s in report.stats.items():
        lines.append(f"| `{project}` | {s['test cases']} | {s['test suites']} | "
                     f"{s['test objects']} | {s['keyword classes']} |")
    lines += ["", f"**{len(report.errors)} error(s), {len(report.warnings)} warning(s)**"]
    if report.errors:
        lines += ["", "| File | Error |", "|---|---|"]
        lines += [f"| `{p}` | {m} |" for p, m in report.errors]
    summary = os.environ.get("GITHUB_STEP_SUMMARY")
    if summary:
        with open(summary, "a", encoding="utf-8") as fh:
            fh.write("\n".join(lines) + "\n")


def main() -> int:
    parser = argparse.ArgumentParser(description=__doc__, formatter_class=argparse.RawDescriptionHelpFormatter)
    parser.add_argument("--project", help="validate a single project folder (relative to repo root)")
    parser.add_argument("--strict", action="store_true", help="treat warnings as errors")
    args = parser.parse_args()

    projects = find_projects(args.project)
    if not projects:
        print("No Katalon projects found.", file=sys.stderr)
        return 1

    report = Report()
    for project in projects:
        validate_project(project, report)

    in_ci = os.environ.get("GITHUB_ACTIONS") == "true"
    for path, message in report.warnings:
        print(f"::warning file={path}::{message}" if in_ci else f"WARN  {path}: {message}")
    for path, message in report.errors:
        print(f"::error file={path}::{message}" if in_ci else f"ERROR {path}: {message}")

    for project, s in report.stats.items():
        print(f"OK    {project}: " + ", ".join(f"{v} {k}" for k, v in s.items()))
    print(f"\n{len(projects)} project(s), {len(report.errors)} error(s), {len(report.warnings)} warning(s)")
    write_summary(report)

    failed = report.errors or (args.strict and report.warnings)
    return 1 if failed else 0


if __name__ == "__main__":
    sys.exit(main())
