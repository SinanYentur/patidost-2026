#!/bin/bash
# PENTAGRAM SENTINEL RUNNER - V45.96
set -euo pipefail

echo "=== [GATE 0] CLEAN BUILD ==="
./gradlew clean assembleDebug --stacktrace

echo "=== [GATE 1] FORBIDDEN KEYWORD SCAN ==="
./gradlew forbiddenKeywordScan

echo "=== [GATE 2] PHYSICAL PATH INTEGRITY ==="
find app/src/main/java -name "*.kt" -print0 | xargs -0 -I {} bash -c '
    file="{}"
    pkg_decl=$(grep "^package " "$file" | head -n 1)
    clean_pkg=$(echo "$pkg_decl" | sed "s/package //; s/;//; s/[[:space:]]//g")
    expected_path=$(echo "$file" | sed "s|^.*app/src/main/java/||; s|/[^/]*\.kt$||; s|/|.|g")
    if [ "$clean_pkg" != "$expected_path" ]; then
        echo "🚨 PATH MISMATCH: $file"
        exit 1
    fi
'

echo "=== [GATE 3] UNIT TEST VALIDATION ==="
./gradlew testDebugUnitTest --stacktrace

echo "✔ ALL GATES PASSED. CODE AUDIT COMPLETED."
