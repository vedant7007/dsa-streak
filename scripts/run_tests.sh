#!/usr/bin/env bash
# Usage: bash scripts/run_tests.sh YYYY-MM-DD
# Compiles all .java files (Solution, SolutionTest, optional Support.java with
# ListNode/TreeNode helpers) in each problem folder and runs days/<date>/<NN-slug>/SolutionTest.java.
# Exits non-zero if any problem fails to compile or any test fails.
set -u
DATE="$1"
ROOT="$(cd "$(dirname "$0")/.." && pwd)"
fail=0
for dir in "$ROOT"/days/"$DATE"/*/; do
  [ -f "$dir/Solution.java" ] || continue
  name="$(basename "$dir")"
  out="$(mktemp -d)"
  if ! javac -d "$out" "$dir"/*.java 2>&1; then
    echo "COMPILE FAIL: $name"; fail=1; continue
  fi
  if java -cp "$out" SolutionTest 2>&1; then
    echo "PASS: $name"
  else
    echo "TEST FAIL: $name"; fail=1
  fi
  rm -rf "$out"
done
exit $fail
