#!/usr/bin/env bash
# detect_platform.sh - V10000.510 Platform Detection
set -u
set -o pipefail

detect_mobile_platform() {
    local project_root="$1"
    if [[ -f "${project_root}/android/build.gradle.kts" ]] || [[ -d "${project_root}/app/src/main" ]]; then
        echo "android"
    elif [[ -f "${project_root}/ios/Podfile" ]]; then
        echo "ios"
    else
        echo "unknown"
    fi
}

# Main execution logic
PLATFORM=$(detect_mobile_platform ".")
echo "📱 Detected Platform: $PLATFORM"
