plugins {
    id("patidost.android.convention")
    id("com.android.test")
    alias(libs.plugins.kotlin.android)
}

android {
    namespace = "com.patidost.app.baselineprofile"

    defaultConfig {
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    targetProjectPath = ":app"
    experimentalProperties["android.experimental.enableProfileCreation"] = true
}

dependencies {
    implementation(libs.androidx.benchmark.macro.junit4)
    implementation(libs.junit)
    implementation(libs.androidx.junit)
    implementation(libs.androidx.espresso.core)
}
