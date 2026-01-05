# Phase 3: Test Evidence Plan - V8500.100 (Cross-Platform Social Standards)
## 1. UNIT & INSTRUMENTATION TESTING
- **Platform Cross Test Evidence (KMP):** Shared ViewModels and UseCases verified across Android/iOS using `TestEvidenceGenerator`.
- **Android Specifics:** `AndroidTestEvidenceGenerator` capturing accessibility and content moderation proofs.
- **iOS Specifics:** `IOSTestEvidenceGenerator` verifying VoiceOver, Dynamic Type, and Switch Control.

## 2. EVIDENCE GENERATION (Gate 2)
- **Visual Evidence:** Automated screenshots for every UI state.
- **Security Evidence:** Real-time content moderation pipeline testing (Hate speech, Adult content, Violence thresholds).
- **Compliance Evidence:** Verification of Age Gating, Block User functionality, and Data Export.

## 3. PHYSICAL EVIDENCE ARTIFACTS
All results are dumper to `test_evidence/` with device info, app version, and video recordings for failed tests.
