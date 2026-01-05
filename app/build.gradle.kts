import java.util.Properties
import java.io.FileInputStream
import org.jetbrains.kotlin.gradle.dsl.JvmTarget

// 🛡️ Rule 300: Secure Secrets Management
val keystoreProperties = Properties().apply {
    val file = rootProject.file("keystore.properties")
    if (file.exists()) load(FileInputStream(file))
}

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.google.services)
    alias(libs.plugins.hilt.android)
    alias(libs.plugins.ksp)
    alias(libs.plugins.kotlin.serialization)
    alias(libs.plugins.firebase.crashlytics)
    alias(libs.plugins.firebase.perf)
}

android {
    namespace = "com.patidost.app"
    compileSdk = 36

    defaultConfig {
        applicationId = "com.patidost.app"
        minSdk = 24
        targetSdk = 36
        versionCode = (System.currentTimeMillis() / 1000).toInt()
        versionName = "1.0.0"
        testInstrumentationRunner = "com.patidost.app.HiltTestRunner"

        ksp {
            arg("room.schemaLocation", "$projectDir/schemas")
            arg("room.exportSchema", "true")
        }
        
        buildConfigField("String", "AI_ETHICS_URL", "\"https://patidost.com/ai-ethics-report\"")
        experimentalProperties["android.experimental.enableK2"] = true
    }

    bundle {
        language { enableSplit = true }
        density { enableSplit = true }
        abi { enableSplit = true }
    }

    signingConfigs {
        create("release") {
            storeFile = file(keystoreProperties["STORE_FILE"] ?: "release.jks")
            storePassword = keystoreProperties["STORE_PASSWORD"] as String?
            keyAlias = keystoreProperties["KEY_ALIAS"] as String?
            keyPassword = keystoreProperties["KEY_PASSWORD"] as String?
            enableV1Signing = true
            enableV2Signing = true
            enableV3Signing = true
            enableV4Signing = true
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = true
            isShrinkResources = true
            isDebuggable = false
            signingConfig = signingConfigs.getByName("release")
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
            
            firebaseCrashlytics {
                nativeSymbolUploadEnabled = true
                unstrippedNativeLibsDir = file("build/intermediates/merged_native_libs/release/out/lib")
            }
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    kotlin {
        compilerOptions {
            jvmTarget.set(JvmTarget.JVM_17)
        }
    }
    
    buildFeatures {
        buildConfig = true
        compose = true
    }
}

dependencies {
    // 🛡️ CORE & UI
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.compose)
    implementation(libs.androidx.activity.compose)
    implementation(libs.androidx.core.splashscreen) // 🚀 FİX: Eksik olan Splashscreen eklendi
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.compose.ui)
    implementation(libs.androidx.compose.material3)
    implementation(libs.androidx.navigation.compose)
    implementation(libs.coil.compose)

    // 🛡️ ARCHITECTURE & DATA
    implementation(libs.hilt.android.lib)
    ksp(libs.hilt.compiler)
    implementation(libs.androidx.room.runtime)
    ksp(libs.androidx.room.compiler)
    implementation(libs.androidx.room.ktx)
    implementation(libs.kotlinx.serialization.json)
    implementation(libs.androidx.security.crypto)

    // 🛡️ FIREBASE & SECURITY 2026
    implementation(platform(libs.firebase.bom))
    implementation(libs.firebase.auth)
    implementation(libs.firebase.analytics)
    implementation(libs.firebase.crashlytics.lib)
    implementation(libs.firebase.perf.lib)
    implementation(libs.firebase.appcheck.playintegrity)
    
    // 🛡️ 2026 MANDATORY ADDITIONS
    implementation(libs.play.integrity)
    implementation(libs.billing.client)
    implementation(libs.billing.ktx)
    implementation(libs.androidx.work.runtime.ktx)
    implementation(libs.androidx.startup.runtime)

    // 🛡️ TESTING
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}
