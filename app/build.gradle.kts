import java.util.Properties
import java.io.FileInputStream
import org.jetbrains.kotlin.gradle.dsl.JvmTarget

// 🛡️ Rule 300: Secure Secrets Management
val keystoreProperties = Properties().apply {
    val file = rootProject.file("keystore.properties")
    if (file.exists()) load(FileInputStream(file))
}

plugins {
    id("patidost.android.convention")
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.google.services)
    alias(libs.plugins.hilt.android)
    alias(libs.plugins.ksp)
    alias(libs.plugins.kotlin.serialization)
    alias(libs.plugins.firebase.crashlytics)
    alias(libs.plugins.firebase.perf)
    alias(libs.plugins.protobuf)
}

android {
    namespace = "com.patidost.app"

    defaultConfig {
        applicationId = "com.patidost.app"
        versionCode = (System.currentTimeMillis() / 1000).toInt()
        versionName = "1.0.0"
        testInstrumentationRunner = "com.patidost.app.HiltTestRunner"

        ksp {
            arg("room.schemaLocation", "$projectDir/schemas")
            arg("room.exportSchema", "true")
        }
    }

    compileOptions {
        isCoreLibraryDesugaringEnabled = true
    }

    buildTypes {
        release {
            isMinifyEnabled = true
            isShrinkResources = true
            isDebuggable = false
            signingConfig = signingConfigs.getByName("debug")
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }

    buildFeatures {
        buildConfig = true
        compose = true
    }

    // 🛡️ Rule 310: Packaging Options for Test DNA Integrity
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
            excludes += "META-INF/LICENSE.md"
            excludes += "META-INF/LICENSE-notice.md"
            excludes += "META-INF/NOTICE.md"
        }
    }

    // 🛡️ Rule 300.2: Add schema location to test assets
    sourceSets {
        getByName("androidTest").assets.srcDir("$projectDir/schemas")
    }
}

// 🛡️ Mühür: Protobuf Compilation V10000.70045
protobuf {
    protoc {
        artifact = "com.google.protobuf:protoc:${libs.versions.protobuf.get()}"
    }
    generateProtoTasks {
        all().forEach { task ->
            task.builtins {
                register("java") {
                    option("lite")
                }
                register("kotlin") {
                    option("lite")
                }
            }
        }
    }
}

dependencies {
    // 🛡️ GÖREV-006: DNA SAPMASI DÜZELTİLDİ
    coreLibraryDesugaring("com.android.tools:desugar_jdk_libs:2.1.5")

    // 🛡️ CORE & UI
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.compose)
    implementation(libs.androidx.activity.compose)
    implementation(libs.androidx.core.splashscreen)
    implementation(libs.timber)
    implementation(libs.kotlinx.collections.immutable)
    implementation("com.github.anrwatchdog:anrwatchdog:1.4.0") // 🛡️ RULE 300.4: DNA SAPMASI DÜZELTİLDİ

    val composeBom = platform(libs.androidx.compose.bom)
    implementation(composeBom)
    androidTestImplementation(composeBom)

    implementation(libs.androidx.compose.ui)
    implementation(libs.androidx.compose.material3)
    implementation(libs.androidx.compose.material.icons.extended)
    implementation(libs.androidx.navigation.compose)
    implementation(libs.androidx.hilt.navigation.compose)
    implementation(libs.coil.compose)
    implementation(libs.coil.core)

    // 🛡️ ARCHITECTURE & DATA
    implementation(libs.hilt.android)
    ksp(libs.hilt.compiler)
    implementation(libs.androidx.room.runtime)
    ksp(libs.androidx.room.compiler)
    implementation(libs.androidx.room.ktx)
    implementation(libs.kotlinx.serialization.json)
    implementation(libs.androidx.security.crypto)
    implementation(libs.androidx.datastore.preferences)
    
    // 🛡️ ANAYASA İHLALİ DÜZELTMESİ (KTOR CLIENT)
    implementation("io.ktor:ktor-client-core:2.3.11")
    implementation("io.ktor:ktor-client-cio:2.3.11")
    implementation("io.ktor:ktor-client-content-negotiation:2.3.11")
    implementation("io.ktor:ktor-serialization-kotlinx-json:2.3.11")
    implementation("io.ktor:ktor-client-logging:2.3.11")

    implementation(libs.protobuf.javalite)
    implementation(libs.protobuf.kotlin.lite)

    // 🛡️ FIREBASE & SERVICES
    implementation(platform(libs.firebase.bom))
    implementation(libs.firebase.auth)
    implementation(libs.firebase.firestore)
    implementation(libs.firebase.analytics)
    implementation(libs.firebase.crashlytics.lib)
    implementation(libs.firebase.perf.lib)
    implementation(libs.firebase.appcheck.playintegrity)
    implementation(libs.firebase.appcheck.debug)
    
    implementation(libs.androidx.work.runtime.ktx)
    implementation(libs.androidx.hilt.work)
    implementation(libs.androidx.window)
    implementation(libs.play.integrity)
    implementation(libs.billing.client)
    implementation(libs.billing.ktx)

    // 🛡️ TESTING DNA ZİNCİRİ (V10000.70088)
    testImplementation(libs.junit)
    testImplementation(libs.mockk)
    testImplementation(libs.google.truth)
    testImplementation(libs.turbine)
    testImplementation(libs.kotlinx.coroutines.test)
    testImplementation(libs.hilt.android.testing)
    kspTest(libs.hilt.compiler)

    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(libs.mockk.android)
    androidTestImplementation(libs.google.truth)
    androidTestImplementation(libs.turbine)
    androidTestImplementation(libs.kotlinx.coroutines.test)
    androidTestImplementation(libs.hilt.android.testing)
    kspAndroidTest(libs.hilt.compiler)
    androidTestImplementation("androidx.room:room-testing:2.8.4") // 🛡️ RULE 300.2: DNA SAPMASI DÜZELTİLDİ
    
    androidTestImplementation(libs.androidx.compose.ui.test.junit4)
    debugImplementation(libs.androidx.compose.ui.test.manifest)
}
