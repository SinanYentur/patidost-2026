# Patidost Technical Codex - Proje Yapısı ve Bileşenleri

## 1. DİZİN HİYERARŞİSİ (PROJECT STRUCTURE)
- **Root:** Gradle wrapper, Version Catalog (libs.versions.toml), AGENTS.md, ROADMAP.md.
- **App Module:**
    - `src/main/kotlin`: Tüm kurumsal iş mantığı ve UI bileşenleri.
    - `src/main/res`: `values/`, `xml/` (Network Security, Backup), `drawable/`, `mipmap/`, `font/`.
    - `AndroidManifest.xml`: Uygulama kimliği ve izin merkezi.
- **BaselineProfile Module:** Performans optimizasyon verileri.

## 2. GRADLE BİLEŞENLERİ
- **libs.versions.toml:** Tüm bağımlılıklar için Tek Gerçeklik Kaynağı (SSOT).
- **build.gradle.kts (App):** Android 14 (SDK 34) hedeflemesi, Signing Configs, ProGuard zırhı.
- **gradle.properties:** Java 21 (JBR) yolu ve paralel derleme optimizasyonları.

## 3. KRİTİK DOSYA KAYITLARI
- `network_security_config.xml`: SSL Pinning (Google/Firebase) tescilli.
- `proguard-rules.pro`: Billing 7.1.0 ve E2EE sınıfları korunmuş.
- `SecurityGuard.kt`: RASP ve Integrity denetçisi.
- `PatidostApp.kt`: Firebase App Check (Play Integrity) giriş noktası.
