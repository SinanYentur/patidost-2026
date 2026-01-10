#!/bin/bash
echo "🛡️ PATİDOST SOVEREIGN AUDIT STARTING..."

# Rule 112: Security Check
grep -q "checkSecurityEnvironment()" app/src/main/java/com/patidost/app/PatidostApp.kt && echo "✅ Rule 112: Security Guard Linked" || echo "❌ Rule 112: Security Guard MISSING"

# Rule 300: OEM Watchdog Check
grep -q "OEMWatchdog.requestAutostart(this)" app/src/main/java/com/patidost/app/PatidostApp.kt && echo "✅ Rule 300: OEM Watchdog Linked" || echo "❌ Rule 300: OEM Watchdog MISSING"

# Rule 310: Gradle Check
grep -q "firebase-appcheck-debug" app/build.gradle.kts && echo "✅ Rule 310: Gradle Physical Sync Ready" || echo "❌ Rule 310: Gradle Physical Sync FAILED"

echo "🏁 AUDIT COMPLETE."
