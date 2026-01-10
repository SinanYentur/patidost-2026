# 🏗️ patidost - Teknik Dokümantasyon
> **Oluşturulma Tarihi:** 2026-01-06 01:33:10  
> **Son Güncelleme:** 2026-01-06 01:33:12

---

## 📋 İÇİNDEKİLER
1. [Proje Genel Bakış](#1-proje-genel-bakış)
2. [Teknoloji Stack](#2-teknoloji-stack)
3. [Mimari Yapı](#3-mimari-yapı)
4. [Bağımlılıklar](#4-bağımlılıklar)
5. [Modüller](#5-modüller)
6. [Kaynak Kod Analizi](#6-kaynak-kod-analizi)
7. [Gradle Yapılandırması](#7-gradle-yapılandırması)
8. [Firebase Konfigürasyonu](#8-firebase-konfigürasyonu)
9. [Kullanıcı Akışları](#9-kullanıcı-akışları)
10. [Test Yapısı](#10-test-yapısı)
11. [CI/CD](#11-cicd)
12. [Performans Metrikleri](#12-performans-metrikleri)

---

## 1. PROJE GENEL BAKIŞ

### 📱 Temel Bilgiler
- **Proje Adı:** patidost
- **Package Name:** com.patidost.app
- **Versiyon:** 1.0.0
- **Version Code:** 1000

### 🎯 Amaç


---

## 2. TEKNOLOJİ STACK

### 🤖 Çekirdek Teknolojiler
- **Android SDK:** 
- **Kotlin:** 1.9.0
- **Kotlin Coroutines:** 1.7.3
- **Jetpack Compose:** 1.5.4

### 🏗️ Mimari
- **Mimari Pattern:** MVVM
- **Dependency Injection:** Manual/DI
- **Navigation:** Manual/Other

### 💾 Veri Katmanı
- **Local Database:** SharedPreferences/DataStore
- **Remote Data:** HTTPURLConnection/Other
- **Cache Strategy:** 

---

## 3. MİMARİ YAPI

### 📁 Proje Dizin Yapısı
```
.
./.git
./.git/hooks
./.git/info
./.git/logs
./.git/logs/refs
./.git/objects
./.git/objects/00
./.git/objects/01
./.git/objects/02
./.git/objects/03
./.git/objects/04
./.git/objects/05
./.git/objects/06
./.git/objects/07
./.git/objects/08
./.git/objects/09
./.git/objects/0a
./.git/objects/0b
./.git/objects/0c
./.git/objects/0d
./.git/objects/0f
./.git/objects/10
./.git/objects/11
./.git/objects/12
./.git/objects/13
./.git/objects/14
./.git/objects/15
./.git/objects/16
./.git/objects/17
./.git/objects/18
./.git/objects/19
./.git/objects/1a
./.git/objects/1b
./.git/objects/1c
./.git/objects/1d
./.git/objects/1e
./.git/objects/1f
./.git/objects/20
./.git/objects/21
./.git/objects/22
./.git/objects/23
./.git/objects/24
./.git/objects/25
./.git/objects/26
./.git/objects/27
./.git/objects/28
./.git/objects/29
./.git/objects/2a
./.git/objects/2b
./.git/objects/2c
./.git/objects/2f
./.git/objects/31
./.git/objects/32
./.git/objects/33
./.git/objects/34
./.git/objects/35
./.git/objects/36
./.git/objects/37
./.git/objects/38
./.git/objects/3a
./.git/objects/3b
./.git/objects/3c
./.git/objects/3d
./.git/objects/3e
./.git/objects/3f
./.git/objects/40
./.git/objects/41
./.git/objects/42
./.git/objects/43
./.git/objects/44
./.git/objects/45
./.git/objects/46
./.git/objects/47
./.git/objects/48
./.git/objects/49
./.git/objects/4a
./.git/objects/4b
./.git/objects/4c
./.git/objects/4d
./.git/objects/4e
./.git/objects/4f
./.git/objects/50
./.git/objects/51
./.git/objects/52
./.git/objects/53
./.git/objects/54
./.git/objects/55
./.git/objects/56
./.git/objects/57
./.git/objects/58
./.git/objects/59
./.git/objects/5a
./.git/objects/5b
./.git/objects/5c
./.git/objects/5d
./.git/objects/5e
./.git/objects/5f
./.git/objects/60
./.git/objects/61
./.git/objects/62
./.git/objects/63
./.git/objects/65
./.git/objects/66
./.git/objects/67
./.git/objects/68
./.git/objects/69
./.git/objects/6a
./.git/objects/6c
./.git/objects/6d
./.git/objects/6e
./.git/objects/6f
./.git/objects/70
./.git/objects/71
./.git/objects/72
./.git/objects/73
./.git/objects/74
./.git/objects/75
./.git/objects/76
./.git/objects/77
./.git/objects/78
./.git/objects/79
./.git/objects/7a
./.git/objects/7b
./.git/objects/7c
./.git/objects/7d
./.git/objects/7e
./.git/objects/80
./.git/objects/81
./.git/objects/82
./.git/objects/83
./.git/objects/84
./.git/objects/85
./.git/objects/86
./.git/objects/87
./.git/objects/88
./.git/objects/89
./.git/objects/8a
./.git/objects/8b
./.git/objects/8d
./.git/objects/8e
./.git/objects/8f
./.git/objects/90
./.git/objects/91
./.git/objects/92
./.git/objects/93
./.git/objects/94
./.git/objects/95
./.git/objects/96
./.git/objects/97
./.git/objects/98
./.git/objects/99
./.git/objects/9a
./.git/objects/9b
./.git/objects/9c
./.git/objects/9d
./.git/objects/9e
./.git/objects/9f
./.git/objects/a0
./.git/objects/a1
./.git/objects/a2
./.git/objects/a3
./.git/objects/a4
./.git/objects/a5
./.git/objects/a6
./.git/objects/a7
./.git/objects/a8
./.git/objects/a9
./.git/objects/aa
./.git/objects/ab
./.git/objects/ac
./.git/objects/ad
./.git/objects/ae
./.git/objects/af
./.git/objects/b0
./.git/objects/b1
./.git/objects/b2
./.git/objects/b3
./.git/objects/b4
./.git/objects/b5
./.git/objects/b6
./.git/objects/b7
./.git/objects/b8
./.git/objects/b9
./.git/objects/ba
./.git/objects/bb
./.git/objects/bc
./.git/objects/bd
./.git/objects/be
./.git/objects/bf
./.git/objects/c0
./.git/objects/c1
./.git/objects/c2
./.git/objects/c3
./.git/objects/c4
./.git/objects/c5
./.git/objects/c6
./.git/objects/c7
./.git/objects/c8
./.git/objects/c9
./.git/objects/ca
./.git/objects/cb
./.git/objects/cc
./.git/objects/cd
./.git/objects/ce
./.git/objects/cf
./.git/objects/d0
./.git/objects/d1
./.git/objects/d2
./.git/objects/d3
./.git/objects/d4
./.git/objects/d5
./.git/objects/d6
./.git/objects/d7
./.git/objects/d8
./.git/objects/d9
./.git/objects/da
./.git/objects/db
./.git/objects/dc
./.git/objects/dd
./.git/objects/de
./.git/objects/df
./.git/objects/e0
./.git/objects/e1
./.git/objects/e2
./.git/objects/e3
./.git/objects/e4
./.git/objects/e5
./.git/objects/e6
./.git/objects/e9
./.git/objects/ea
./.git/objects/eb
./.git/objects/ec
./.git/objects/ed
./.git/objects/ee
./.git/objects/ef
./.git/objects/f0
./.git/objects/f1
./.git/objects/f2
./.git/objects/f3
./.git/objects/f4
./.git/objects/f5
./.git/objects/f7
./.git/objects/f8
./.git/objects/f9
./.git/objects/fa
./.git/objects/fb
./.git/objects/fc
./.git/objects/fd
./.git/objects/fe
./.git/objects/ff
./.git/objects/info
./.git/objects/pack
./.git/refs
./.git/refs/heads
./.git/refs/remotes
./.git/refs/tags
./.github
./.github/workflows
./.gradle
./.gradle/8.13
./.gradle/8.13/checksums
./.gradle/8.13/executionHistory
./.gradle/8.13/expanded
./.gradle/8.13/fileChanges
./.gradle/8.13/fileHashes
./.gradle/8.13/vcsMetadata
./.gradle/buildOutputCleanup
./.gradle/noVersion
./.gradle/vcs-1
./.idea
./.idea/caches
./.idea/inspectionProfiles
./.kotlin
./.kotlin/errors
./.kotlin/sessions
./SOVEREIGN_ULTIMATE_BACKUP
./app
./app/build
./app/build/crashlytics
./app/build/extracted-include-protos
./app/build/extracted-protos
./app/build/generated
./app/build/gmpAppId
./app/build/intermediates
./app/build/kotlin
./app/build/kspCaches
./app/build/outputs
./app/build/tmp
./app/src
./app/src/androidTest
./app/src/main
./app/src/release
./app/src/test
./audit_logs
./baselineprofile
./baselineprofile/build
./baselineprofile/build/generated
./baselineprofile/build/intermediates
./baselineprofile/src
./baselineprofile/src/androidTest
./baselineprofile/src/main
./build
./build-logic
./build-logic/.gradle
./build-logic/.gradle/8.13
./build-logic/.gradle/9.0-milestone-1
./build-logic/.gradle/buildOutputCleanup
./build-logic/.gradle/vcs-1
./build-logic/.kotlin
./build-logic/.kotlin/sessions
./build-logic/android-convention
./build-logic/android-convention/build
./build-logic/android-convention/src
./build/reports
./build/reports/problems
./compliance-codex
./compliance-codex/ai-logic
./compliance-codex/sbom-privacy
./compose-2026-codex
./compose-2026-codex/backend-integration
./cross-platform-codex
./data-memory-codex
./data-storage-codex
./docs
./docs/adr
./docs/lingo-codex
./docs/technical-codex
./gradle
./gradle/wrapper
./mathematical-foundation-codex
./play-store-2026-codex
./play-store-2026-codex/final-compliance
./project-evidence
./project-evidence/phase-0-research
./project-evidence/phase-1-architecture
./project-evidence/phase-2-contracts
./project-evidence/phase-2-contracts/api-contracts
./project-evidence/phase-2-contracts/navigation-contracts
./project-evidence/phase-3-validation
./scripts
./sovereign_core_engine
./sovereign_core_engine/ui
./sovereign_core_engine/ui/component
./ui-architecture-book
./universal-compliance-codex
./vital-systems-codex
```

### 🏗️ Paket Yapısı
```
java/com/patidost/app/MainActivity.kt
java/com/patidost/app/PatiApplication.kt
java/com/patidost/app/PatidostApp.kt
java/com/patidost/app/billing/BillingClientProvider.kt
java/com/patidost/app/billing/BillingRepository.kt
java/com/patidost/app/billing/manager/SubscriptionManager.kt
java/com/patidost/app/billing/validator/PurchasesValidator.kt
java/com/patidost/app/data/local/PatiDatabase.kt
java/com/patidost/app/data/local/PetDao.kt
java/com/patidost/app/data/local/dao/PetDao.kt
java/com/patidost/app/data/local/dao/UserDao.kt
java/com/patidost/app/data/local/database/PatidostDatabase.kt
java/com/patidost/app/data/local/datastore/UserPrefsSerializer.kt
java/com/patidost/app/data/local/entity/PetEntity.kt
java/com/patidost/app/data/local/entity/UserEntity.kt
java/com/patidost/app/data/mapper/PetMapper.kt
java/com/patidost/app/data/model/PetEntity.kt
java/com/patidost/app/data/remote/AuthRemoteDataSource.kt
java/com/patidost/app/data/remote/PetRemoteDataSource.kt
java/com/patidost/app/data/remote/model/PetDto.kt
java/com/patidost/app/data/repository/AuthRepositoryImpl.kt
java/com/patidost/app/data/repository/BillingRepositoryImpl.kt
java/com/patidost/app/data/repository/FirestoreBatchWorker.kt
java/com/patidost/app/data/repository/PetRepositoryImpl.kt
java/com/patidost/app/data/repository/ProductRepositoryImpl.kt
java/com/patidost/app/data/repository/UserRepositoryImpl.kt
java/com/patidost/app/data/worker/PetSyncWorker.kt
java/com/patidost/app/di/AppModule.kt
java/com/patidost/app/di/BillingModule.kt
java/com/patidost/app/di/CoroutineModule.kt
```

---

## 4. BAĞIMLILIKLAR

### 📦 Core Dependencies
```gradle
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.compose)
    implementation(libs.androidx.activity.compose)
    implementation(libs.androidx.core.splashscreen)
    implementation(libs.timber)
    implementation(libs.androidx.metrics)
    implementation(libs.kotlinx.collections.immutable)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.compose.ui)
    implementation(libs.androidx.compose.material3)
    implementation(libs.androidx.navigation.compose)
    implementation(libs.coil.compose)
    implementation(libs.coil.core)
    implementation(libs.hilt.android)
    implementation(libs.androidx.room.runtime)
    implementation(libs.androidx.room.ktx)
    implementation(libs.kotlinx.serialization.json)
    implementation(libs.androidx.security.crypto)
    implementation(libs.androidx.datastore.preferences)
    implementation(libs.protobuf.javalite)
    implementation(libs.protobuf.kotlin.lite)
    implementation(platform(libs.firebase.bom))
    implementation(libs.firebase.auth)
    implementation(libs.firebase.firestore)
    implementation(libs.firebase.analytics)
    implementation(libs.firebase.crashlytics.lib)
    implementation(libs.firebase.perf.lib)
    implementation(libs.firebase.appcheck.playintegrity)
    implementation(libs.play.integrity)
    implementation(libs.billing.client)
```

### 🔧 Plugin'ler
```gradle
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
```

---

## 5. MODÜLLER

### 🧩 app

- **Tip:** Application Module
- **Bağımlılıklar:** 36 adet

### 🧩 baselineprofile

- **Tip:** Library Module
- **Bağımlılıklar:** 4 adet

### 🧩 android-convention

- **Tip:** Library Module
- **Bağımlılıklar:** 3 adet

---

## 6. KAYNAK KOD ANALİZİ

### 📊 Kod İstatistikleri
```
168 Kotlin/Java dosyası
12 XML layout dosyası
6624 Kotlin satırı
0 Java satırı
```

### 🎨 UI Bileşenleri
- **SnapFlingBehavior.kt**: 1 Composable fonksiyon
- **AppArmor.kt**: 3 Composable fonksiyon
- **PatiButtons.kt**: 1 Composable fonksiyon
- **CustomPullToRefresh.kt**: 2 Composable fonksiyon
- **EmergencyFAB.kt**: 1 Composable fonksiyon
- **GlassComponents.kt**: 4 Composable fonksiyon
- **SovereignButton.kt**: 1 Composable fonksiyon
- **SovereignCard.kt**: 2 Composable fonksiyon
- **SovereignScreenState.kt**: 2 Composable fonksiyon
- **PetFeatureComponents.kt**: 1 Composable fonksiyon

### 🏛️ ViewModel'ler
- **AuthViewModel.kt**: 2 public fonksiyon
- **LoginViewModel.kt**: 5 public fonksiyon
- **CartViewModel.kt**: 0 public fonksiyon
- **CartViewModel.kt**: 1 public fonksiyon
- **HomeViewModel.kt**: 1 public fonksiyon
- **PetDetailViewModel.kt**: 2 public fonksiyon
- **DiscoverViewModel.kt**: 1 public fonksiyon
- **PetListViewModel.kt**: 1 public fonksiyon
- **PetDetailViewModel.kt**: 0 public fonksiyon
- **PetListViewModel.kt**: 0 public fonksiyon
- **PremiumViewModel.kt**: 2 public fonksiyon
- **ProfileViewModel.kt**: 5 public fonksiyon
- **ProfileViewModel.kt**: 2 public fonksiyon

---

## 7. GRADLE YAPILANDIRMASI

### ⚙️ build.gradle.kts (app)
```kotlin
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

    buildFeatures {
        buildConfig = true
        compose = true
    }
}

// 🛡️ Mühür: Protobuf Configuration (V7.5 Kotlin DSL Fix)
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
```

### 🔐 Keystore Konfigürasyonu
> Keystore konfigürasyonu bulunamadı

---

## 8. FIREBASE KONFİGÜRASYONU

### ✅ Firebase Entegre Edilmiş

- **Proje ID:** patidost-b20d5
- **Paket Adı:** com.patidost.app
- **SHA-1:** 

---

## 9. KULLANICI AKIŞLARI

### 🔄 Navigation Graph
**Compose Navigation Kullanılıyor**
- MainActivity.kt: Navigation tanımları içeriyor
- NavGraph.kt: Navigation tanımları içeriyor
- Routes.kt: Navigation tanımları içeriyor

---

## 10. TEST YAPISI

### 🧪 Test İstatistikleri
```
Unit Test: 9 dosya
UI Test: 3 dosya
```

---

## 11. CI/CD

### 🔄 Yapılandırma Dosyaları
- **:**: CI/CD pipeline
- **total 12**: CI/CD pipeline
- **drwxr-xr-x 1 Kratos35 197121    0 Jan  5 18:42 .**: CI/CD pipeline
- **drwxr-xr-x 1 Kratos35 197121    0 Jan  4 05:18 ..**: CI/CD pipeline
- ****: CI/CD pipeline
- ****: CI/CD pipeline

---

## 12. PERFORMANS METRİKLERİ

### ⚡ Derleme Bilgileri
- **Min SDK:** 
- **Target SDK:** 
- **Build Tools:** 

### 📱 Ekran Destekleri
- **Screen Density:** 
- **Screen Size:** 

---

## 📋 PROJE CHECKLIST

### ✅ Tamamlananlar
- [ ] ./app/src/main/java/com/patidost/app/ui/screen/profile/ProfileScreen.kt: 53:                onClick = { /* TODO */ }
- [ ] ./app/src/main/java/com/patidost/app/ui/screen/profile/ProfileScreen.kt: 58:                onClick = { /* TODO */ }

### 🔄 Geliştirme Aşamasında
- [ ] Unit test coverage %70
- [ ] UI test coverage %50

---

## 🔗 FAYDALI LİNKLER

- **Git Repository:** https://github.com/kartyler35/patidost-2026.git
- **JIRA/Project:** 
- **Design System:** 

---

> 📄 Doküman otomatik olarak oluşturulmuştur. Son güncelleme: 2026-01-06 01:33:42

