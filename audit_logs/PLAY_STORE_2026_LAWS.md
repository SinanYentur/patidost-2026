# GOOGLE PLAY STORE 2026 COMPLIANCE LAWS (API 36)

## ⚖️ 1. TARGET SDK SOVEREIGNTY
- **Requirement:** Apps must target API 36 (Android 16) by Aug 2026.
- **Impact:** Mandatory Edge-to-Edge, Predictive Back, and Photo Picker.
- **Evidence:** https://developer.android.com/google/play/requirements/target-sdk

## 🔒 2. DATA SAFETY & PRIVACY
- **Requirement:** Complete disclosure of data collection (Location, Auth, Storage).
- **Rule:** Firebase Analytics and Auth must be declared even if unused.
- **Evidence:** https://developer.android.com/privacy-and-security/declare-data-use

## 🛡️ 3. APP INTEGRITY
- **Requirement:** Play Integrity API or App Check is mandatory for high-risk actions.
- **Rule:** Unauthorized clients must be blocked from Firestore/Auth.
- **Evidence:** https://developer.android.com/google/play/integrity

## 🚀 4. PERFORMANCE VITALS
- **Requirement:** Startup time < 2s. ANR rate < 0.47%.
- **Recommendation:** Baseline Profiles are mandatory for "Play Store Promotion".
- **Evidence:** https://developer.android.com/topic/performance/vitals

## 🎨 5. VISUAL EXCELLENCE
- **Requirement:** Adaptive layouts for large screens (tablets/foldables).
- **Rule:** Zero UI overlap with system bars.
- **Evidence:** https://developer.android.com/design/ui/mobile/guides/layout-and-content/edge-to-edge
