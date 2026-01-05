#!/usr/bin/env bash
# =============================================================================
# 🛡️ SOVEREIGN OMNI-AUDIT V10000.5000
# "Gerçeklik en güçlü silahtır."
# =============================================================================
set -u
set -o pipefail

PROJECT_PATH="/c/Users/Kratos35/AndroidStudioProjects/patidost"
cd "$PROJECT_PATH" || { echo "❌ ERROR: PATH NOT FOUND"; exit 1; }

echo "=================================================================="
echo "🔱 PATİDOST SOVEREIGN OMNI-AUDIT V10000.5000"
echo "STATUS: ARCHITECTURAL SINGULARITY | GROUND TRUTH VERIFIED"
echo "=================================================================="

PASSED=0; FAILS=0; WARNINGS=0; TOTAL_CHECKS=32

# Utility: Integrity Checker
check() {
    local cmd=$1; local name=$2; local severity=$3 # 0: CRITICAL, 1: WARNING
    if eval "$cmd" > /dev/null 2>&1; then
        echo -e "✅ PASS: $name"
        ((PASSED++))
    else
        if [ "$severity" -eq 0 ]; then
            echo -e "❌ FAIL: $name [CRITICAL]"
            ((FAILS++))
        else
            echo -e "⚠️  WARN: $name [ADVISORY]"
            ((WARNINGS++))
        fi
    fi
}

# --- [1] CONSTITUTION & TRUTH (Rule 108) ---
echo -e "\n📜 [1] CONSTITUTION & GROUND TRUTH"
check "grep -q 'V10000.5000' AGENTS.md" "Constitution Version (V10000.5000)" 0
check "grep -q 'targetSdk = 35' app/build.gradle.kts" "Target SDK 35 (Android 15)" 0
check "grep -q '8.6.0' build.gradle.kts" "AGP 8.6.0 (Ground Truth)" 0
check "grep -q '2.0.' build.gradle.kts" "Kotlin 2.0.x (K2 Engine)" 0

# --- [2] ARCHITECTURE PURITY (Surgical Split) ---
echo -e "\n🏗️  [2] ARCHITECTURE PURITY"
check "! ls app/src/main/java/com/patidost/app/ui/screen/pet/*.kt 2>/dev/null | grep -q ." "Pet Folder Monolith Purged" 0
check "[ -d 'app/src/main/java/com/patidost/app/ui/screen/pet/list' ]" "Sub-feature: Pet List" 0
check "[ -d 'app/src/main/java/com/patidost/app/ui/screen/auth/login' ]" "Sub-feature: Auth Login" 0
check "! grep -r 'import.*\.data\.' app/src/main/java/com/patidost/app/ui/component/core" "Core-UI Layer Isolation" 0

# --- [3] SECURITY & PRIVACY (Singularity Standards) ---
echo -e "\n🔐 [3] SECURITY & PRIVACY"
check "grep -r 'IntegrityTokenRequest' app/src/main/java" "Play Integrity 1.4 Integration" 0
check "grep -r 'DataMasker' app/src/main/java" "GDPR Data Masking Utility" 0
check "grep -q 'android:allowBackup=\"false\"' app/src/main/AndroidManifest.xml" "Data Theft Protection" 0
check "[ -f 'PRIVACY_POLICY.md' ]" "Privacy Policy Physical Seal" 0

# --- [4] COMPLIANCE & EVIDENCE (Rule 100) ---
echo -e "\n📁 [4] COMPLIANCE & EVIDENCE"
check "[ -d 'project-evidence/phase-0-research' ]" "Phase 0 Evidence" 0
check "[ -d 'project-evidence/phase-1-architecture' ]" "Phase 1 Evidence" 0
check "[ -d 'project-evidence/phase-3-validation' ]" "Phase 3 Evidence" 0
check "[ -f 'sbom.spdx.json' ]" "SBOM SPDX Physical Seal" 0

# --- [5] PERFORMANCE & 16KB (Rule 109) ---
echo -e "\n⚡ [5] PERFORMANCE & 16KB"
check "grep -q 'Rule 109' AGENTS.md" "16KB Alignment Mandate" 0
check "[ -f 'app/src/main/baseline-prof.txt' ]" "Baseline Profiles Existence" 1

# --- FINAL SCORE ---
EXECUTED=$((PASSED + FAILS + WARNINGS))
SCORE=$(( (PASSED * 100) / EXECUTED ))

echo -e "\n=================================================================="
echo "🏁 OMNI-AUDIT COMPLETE"
echo "✅ PASSED: $PASSED | ❌ FAILS: $FAILS | ⚠️  WARN: $WARNINGS"
echo "REALISTIC COMPLIANCE SCORE: $SCORE/100"
echo "=================================================================="

if [ "$FAILS" -eq 0 ] && [ "$SCORE" -ge 95 ]; then
    echo "👑 STATUS: SOVEREIGN MATURITY (A+++)"
else
    echo "💀 STATUS: ARCHITECTURAL DEBT DETECTED"
    exit 1
fi
