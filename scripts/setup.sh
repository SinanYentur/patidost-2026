#!/usr/bin/env bash
# setup.sh - V10000.7200 Disciplinary Alignment
set -u
set -o pipefail

echo "🔧 Global DevOps Toolkit Setup & Health Check..."
echo "================================================"

# 1. Java Environment Check
if command -v java &> /dev/null; then
    JAVA_VER=$(java -version 2>&1 | awk -F '"' '/version/ {print $2}')
    echo "✅ Java found: $JAVA_VER"
else
    echo "❌ CRITICAL: Java not found. Please install JDK 17+."
    exit 1
fi

# 2. Tool dependencies
REQUIRED_TOOLS=("jq" "find" "du" "date" "mkdir" "cp" "rm")
for tool in "${REQUIRED_TOOLS[@]}"; do
    if ! command -v "$tool" &> /dev/null; then
        echo "❌ MISSING: $tool"
        exit 1
    fi
done

# 3. Android SDK Check
if [ -n "${ANDROID_HOME:-}" ]; then
    echo "✅ ANDROID_HOME is set: $ANDROID_HOME"
else
    echo "⚠️  WARNING: ANDROID_HOME not found in environment."
fi

# 4. Final Sealing
chmod +x scripts/*.sh 2>/dev/null || true
echo "✅ V10000.7200 Disciplinary Setup complete."
