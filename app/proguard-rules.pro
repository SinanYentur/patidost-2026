# ProGuard Rules - V10000.19500 Sovereign Optimization
# Rule 102: Simple, humble, but powerful obfuscation.

# 1. Google Play Billing Library 8.1.0
-keep class com.android.billingclient.api.** { *; }
-keep class com.google.android.gms.internal.play_billing.** { *; }

# 2. Firebase & Firestore 2026
-keep class com.google.firebase.** { *; }
-keep class com.google.android.gms.** { *; }
-keepattributes Signature, *Annotation*, EnclosingMethod, InnerClasses
-dontwarn com.google.firebase.**

# 3. Kotlin Multiplatform & Serialization
-keep class kotlinx.serialization.json.** { *; }
-keepattributes RuntimeVisibleAnnotations, RuntimeVisibleParameterAnnotations

# 4. Hilt & Dagger
-keep class dagger.hilt.** { *; }
-keep @dagger.hilt.android.lifecycle.HiltViewModel class * extends androidx.lifecycle.ViewModel

# 5. Signal Protocol (E2EE) & PQC
-keep class org.whispersystems.libsignal.** { *; }
-keep class org.bouncycastle.** { *; }

# 6. Domain Models (Immutable)
-keep class com.patidost.app.domain.model.** { *; }

# 7. Optimization
-repackageclasses ''
-allowaccessmodification
-optimizations !code/simplification/arithmetic,!field/*,!class/merging/*
-assumenosideeffects class timber.log.Timber {
    public static *** d(...);
    public static *** v(...);
    public static *** i(...);
}
