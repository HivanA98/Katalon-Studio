#!/usr/bin/env bash
# Runs a Katalon test suite against the first connected Android device/emulator.
# Used by .github/workflows/katalon-mobile.yml inside android-emulator-runner.
#
# Usage: run-katalon-mobile.sh <project .prj path> <test suite path> <report folder>
# Env:   KATALON_API_KEY (required), KRE_HOME (default: ~/kre)
set -euo pipefail

project="$1"
suite="$2"
report_dir="$3"
kre_home="${KRE_HOME:-$HOME/kre}"

katalonc="$(find "$kre_home" -maxdepth 2 -type f -name katalonc | head -n 1)"
if [ -z "$katalonc" ]; then
  echo "katalonc not found under $kre_home" >&2
  exit 1
fi
chmod +x "$katalonc"

adb wait-for-device
device="$(adb devices | awk 'NR > 1 && $2 == "device" { print $1; exit }')"
echo "Running '$suite' of '$project' on $device"

"$katalonc" -noSplash -runMode=console \
  -projectPath="$project" \
  -testSuitePath="$suite" \
  -browserType="Android" \
  -deviceId="$device" \
  -executionProfile="default" \
  -reportFolder="$report_dir" \
  -apiKey="$KATALON_API_KEY"
