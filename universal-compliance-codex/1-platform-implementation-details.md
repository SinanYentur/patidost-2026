# Universal Codex - Bölüm 1: Platforma Özgü Uygulama Detayları
## 1.1 IOS (APP STORE)
- **ATT Authorization:** `requestTrackingAuthorization` ile IDFA rızası mühürlenmiştir.
- **Privacy Manifest:** `NSPrivacyAccessedAPITypes` (UserDefaults, FileTimestamp, SystemBootTime) tanımları Info.plist içine mühürlenecektir.
- **Sign in with Apple:** Sosyal ağlarda anonim giriş desteği zorunludur.

## 1.2 ANDROID (PLAY STORE)
- **Play Integrity:** `setupPlayIntegrity` ve `setupSafetyNet` ile cihaz bütünlüğü mühürlenmiştir.
- **Target SDK:** Android 14 (API 34) baseline olarak tescil edildi; Ağustos 2026'da SDK 36'ya yükseltme planı mühürlendi.
