# Patidost 2026 PRODUCTION Standard R8 Rules
# RVWL: Synchronized with com.patidost.app and API 36 behavior.

# Keep domain models from obfuscation (Firebase/Serialization requirement)
-keepclassmembers class com.patidost.app.domain.model.** { *; }
-keep class com.patidost.app.domain.model.** { *; }

# Hilt/Dagger generated code preservation
-keep class com.patidost.app.**_HiltModules* { *; }
-keep class dagger.hilt.android.internal.managers.** { *; }

# Coil/AsyncImage optimization
-keep class coil.** { *; }
-dontwarn coil.**

# Firebase/Firestore internal stability
-keep class com.google.firebase.** { *; }
-dontwarn com.google.firebase.**

# Rule 47 Evidence: Preserve Kotlin Serialization descriptors
-keepclassmembers class ** {
    *** Companion;
    *** $serializer;
}
