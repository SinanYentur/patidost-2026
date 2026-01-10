plugins {
    id("patidost.android.convention")
    id("com.android.test")
    alias(libs.plugins.kotlin.android)
}

android {
    namespace = "com.patidost.app.baselineprofile"

    defaultConfig {
        // 🛡️ Rule 100: Grant permissions automatically for physical device profiling
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        testInstrumentationRunnerArguments["grantRuntimePermissions"] = "true"
    }

    targetProjectPath = ":app"
    experimentalProperties["android.experimental.enableProfileCreation"] = true
}

dependencies {
    // 🛡️ Rule 310: Compose BOM mandatory for version resolution
    val composeBom = platform(libs.androidx.compose.bom)
    implementation(composeBom)

    implementation(libs.androidx.benchmark.macro.junit4)
    implementation(libs.junit)
    implementation(libs.androidx.junit)
    implementation(libs.androidx.espresso.core)
    implementation(libs.androidx.compose.ui.test.junit4)
}
