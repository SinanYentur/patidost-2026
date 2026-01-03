# SYSTEMIC BLINDNESS REGISTRY (OMNI-AUDIT 2025-2026)

## 1. Tooling & Configuration Blindness
- **Issue:** AGP 8.0+ `buildConfig` default value.
- **Cause:** Assuming `BuildConfig` is always generated.
- **Evidence:** Android Developers AGP 8.0 Release Notes.
- **Fix:** Explicitly set `buildFeatures.buildConfig = true`.

## 2. Dependency Graph Integrity Blindness
- **Issue:** [Dagger/MissingBinding] and [Dagger/DuplicateBindings].
- **Cause:** Partial refactoring of Repository interfaces without updating all call sites (Use Cases).
- **Evidence:** Dagger/Hilt Official Error Documentation.
- **Fix:** Atomic refactoring of Interface -> Impl -> UseCase -> ViewModel.

## 3. Classpath & Metadata Corruption Blindness
- **Issue:** Hilt `NullPointerException` during aggregateddeps processing.
- **Cause:** Using Gradle `exclude` to hide files instead of physically deleting them.
- **Evidence:** Dagger Hilt Metadata Aggregation Guide.
- **Fix:** Physical Purge Authority (Rule 36).

## 4. Platform (API 36) Compliance Blindness
- **Issue:** WindowInsets leaks and UI overlaps.
- **Cause:** Assuming manual padding is sufficient for Android 16.
- **Evidence:** Android 16 (API 36) Visual Integrity Summary.
- **Fix:** Enforced Edge-to-Edge with Scaffold systemBars.

## 5. Reactive Synchronization Blindness
- **Issue:** Hardcoded error messages in ViewModels.
- **Cause:** Forgetting localization parity during rapid logic changes.
- **Evidence:** Android App Quality Guidelines (Resource Mapping).
- **Fix:** Mandatory @StringRes mapping in UI State models.
