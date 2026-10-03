#!/usr/bin/env python3
"""Scaffold Katalon entities (test cases, test suites, suite collections) from the command line.

Katalon stores every entity as an XML descriptor plus (for test cases) a script in a
timestamped folder. Creating them by hand is error prone, so this helper generates the
boilerplate with fresh GUIDs. Existing files are never overwritten.

Examples:
    python tools/katalon_scaffold.py testcase --project "Web Testing/SauceDemo" \\
        --id "Auth/Login With Valid User" --description "Standard user can sign in" --tag smoke

    python tools/katalon_scaffold.py suite --project "Web Testing/SauceDemo" --id "Smoke" \\
        --cases "Auth/Login With Valid User" "Checkout/Checkout Single Item"

    python tools/katalon_scaffold.py collection --project "Web Testing/SauceDemo" \\
        --id "Full Regression" --suites "Smoke" "Regression" --browser "Chrome (headless)"
"""
from __future__ import annotations

import argparse
import sys
import time
import uuid
from pathlib import Path
from xml.sax.saxutils import escape

REPO_ROOT = Path(__file__).resolve().parent.parent

SCRIPT_TEMPLATE = """\
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import internal.GlobalVariable as GlobalVariable

// TODO: implement '{name}'
"""

SUITE_SCRIPT = """\
import com.kms.katalon.core.annotation.SetUp
import com.kms.katalon.core.annotation.SetupTestCase
import com.kms.katalon.core.annotation.TearDown
import com.kms.katalon.core.annotation.TearDownTestCase

/**
 * Suite-level hooks. Per-test-case browser lifecycle is handled by the project's Test Listener.
 */
@SetUp(skipped = true)
def setUp() {
}

@TearDown(skipped = true)
def tearDown() {
}

@SetupTestCase(skipped = true)
def setupTestCase() {
}

@TearDownTestCase(skipped = true)
def tearDownTestCase() {
}
"""


def _write(path: Path, content: str) -> bool:
    if path.exists():
        print(f"skip  {path.relative_to(REPO_ROOT)} (exists)")
        return False
    path.parent.mkdir(parents=True, exist_ok=True)
    path.write_text(content, encoding="utf-8", newline="\n")
    print(f"write {path.relative_to(REPO_ROOT)}")
    return True


def _project(path: str) -> Path:
    project = (REPO_ROOT / path).resolve()
    if not any(project.glob("*.prj")):
        sys.exit(f"'{path}' is not a Katalon project (no .prj file)")
    return project


def create_test_case(project: Path, tc_id: str, description: str, tags: list[str], variables: list[str]) -> None:
    name = tc_id.split("/")[-1]
    var_xml = []
    for spec in variables:
        var_name, _, default = spec.partition("=")
        default = default or "''"
        var_xml.append(
            "   <variable>\n"
            f"      <defaultValue>{escape(default)}</defaultValue>\n"
            "      <description></description>\n"
            f"      <id>{uuid.uuid4()}</id>\n"
            "      <masked>false</masked>\n"
            f"      <name>{escape(var_name)}</name>\n"
            "   </variable>\n"
        )
    tc = (
        '<?xml version="1.0" encoding="UTF-8"?>\n'
        "<TestCaseEntity>\n"
        f"   <description>{escape(description)}</description>\n"
        f"   <name>{escape(name)}</name>\n"
        f"   <tag>{escape(','.join(tags))}</tag>\n"
        "   <comment></comment>\n"
        "   <recordOption>OTHER</recordOption>\n"
        f"   <testCaseGuid>{uuid.uuid4()}</testCaseGuid>\n"
        + "".join(var_xml)
        + "</TestCaseEntity>\n"
    )
    if _write(project / "Test Cases" / f"{tc_id}.tc", tc):
        script_dir = project / "Scripts" / tc_id
        if not list(script_dir.glob("Script*.groovy")):
            stamp = int(time.time() * 1000)
            time.sleep(0.002)  # keep timestamps unique when called in a loop
            _write(script_dir / f"Script{stamp}.groovy", SCRIPT_TEMPLATE.format(name=name))


def create_suite(project: Path, suite_id: str, cases: list[str], description: str, timeout: int) -> None:
    for case in cases:
        if not (project / "Test Cases" / f"{case}.tc").is_file():
            sys.exit(f"test case '{case}' does not exist in {project.name}")
    links = "".join(
        "   <testCaseLink>\n"
        f"      <guid>{uuid.uuid4()}</guid>\n"
        "      <isReuseDriver>false</isReuseDriver>\n"
        "      <isRun>true</isRun>\n"
        f"      <testCaseId>Test Cases/{escape(case)}</testCaseId>\n"
        "      <usingDataBindingAtTestSuiteLevel>true</usingDataBindingAtTestSuiteLevel>\n"
        "   </testCaseLink>\n"
        for case in cases
    )
    ts = (
        '<?xml version="1.0" encoding="UTF-8"?>\n'
        "<TestSuiteEntity>\n"
        f"   <description>{escape(description)}</description>\n"
        f"   <name>{escape(suite_id.split('/')[-1])}</name>\n"
        "   <tag></tag>\n"
        "   <isRerun>false</isRerun>\n"
        "   <mailRecipient></mailRecipient>\n"
        "   <numberOfRerun>1</numberOfRerun>\n"
        f"   <pageLoadTimeout>{timeout}</pageLoadTimeout>\n"
        "   <pageLoadTimeoutDefault>true</pageLoadTimeoutDefault>\n"
        "   <rerunFailedTestCasesOnly>true</rerunFailedTestCasesOnly>\n"
        "   <rerunImmediately>false</rerunImmediately>\n"
        f"   <testSuiteGuid>{uuid.uuid4()}</testSuiteGuid>\n"
        + links
        + "</TestSuiteEntity>\n"
    )
    if _write(project / "Test Suites" / f"{suite_id}.ts", ts):
        _write(project / "Test Suites" / f"{suite_id}.groovy", SUITE_SCRIPT)


def create_collection(project: Path, collection_id: str, suites: list[str], browser: str, group: str) -> None:
    for suite in suites:
        if not (project / "Test Suites" / f"{suite}.ts").is_file():
            sys.exit(f"test suite '{suite}' does not exist in {project.name}")
    configs = "".join(
        "      <TestSuiteRunConfiguration>\n"
        "         <configuration>\n"
        f"            <groupName>{escape(group)}</groupName>\n"
        "            <profileName>default</profileName>\n"
        "            <requireConfigurationData>false</requireConfigurationData>\n"
        f"            <runConfigurationId>{escape(browser)}</runConfigurationId>\n"
        "         </configuration>\n"
        "         <runEnabled>true</runEnabled>\n"
        f"         <testSuiteEntity>Test Suites/{escape(suite)}</testSuiteEntity>\n"
        "      </TestSuiteRunConfiguration>\n"
        for suite in suites
    )
    tsc = (
        '<?xml version="1.0" encoding="UTF-8"?>\n'
        "<TestSuiteCollectionEntity>\n"
        "   <description></description>\n"
        f"   <name>{escape(collection_id.split('/')[-1])}</name>\n"
        "   <tag></tag>\n"
        "   <delayBetweenInstances>0</delayBetweenInstances>\n"
        "   <executionMode>SEQUENTIAL</executionMode>\n"
        "   <maxConcurrentInstances>4</maxConcurrentInstances>\n"
        "   <testSuiteRunConfigurations>\n"
        + configs
        + "   </testSuiteRunConfigurations>\n"
        "</TestSuiteCollectionEntity>\n"
    )
    _write(project / "Test Suites" / f"{collection_id}.ts", tsc)


def main() -> None:
    parser = argparse.ArgumentParser(description=__doc__, formatter_class=argparse.RawDescriptionHelpFormatter)
    sub = parser.add_subparsers(dest="command", required=True)

    tc = sub.add_parser("testcase", help="create a test case + script")
    tc.add_argument("--project", required=True)
    tc.add_argument("--id", required=True, help="path below 'Test Cases', e.g. 'Auth/Login'")
    tc.add_argument("--description", default="")
    tc.add_argument("--tag", action="append", default=[])
    tc.add_argument("--var", action="append", default=[], help="test case variable, e.g. product='Sauce Labs Backpack'")

    ts = sub.add_parser("suite", help="create a test suite")
    ts.add_argument("--project", required=True)
    ts.add_argument("--id", required=True, help="path below 'Test Suites'")
    ts.add_argument("--cases", nargs="+", required=True)
    ts.add_argument("--description", default="")
    ts.add_argument("--timeout", type=int, default=30)

    tsc = sub.add_parser("collection", help="create a test suite collection")
    tsc.add_argument("--project", required=True)
    tsc.add_argument("--id", required=True)
    tsc.add_argument("--suites", nargs="+", required=True)
    tsc.add_argument("--browser", default="Chrome")
    tsc.add_argument("--group", default="Web Desktop")

    args = parser.parse_args()
    project = _project(args.project)
    if args.command == "testcase":
        create_test_case(project, args.id, args.description, args.tag, args.var)
    elif args.command == "suite":
        create_suite(project, args.id, args.cases, args.description, args.timeout)
    else:
        create_collection(project, args.id, args.suites, args.browser, args.group)


if __name__ == "__main__":
    main()
