# Play Store 2026 Codex - Bölüm 10: Nihai Yayın (Final Submission) Rehberi

## 10.1 NİHAİ YAYIN NEDİR? (WHAT IS FINAL SUBMISSION?)
Bu aşama, projenin "Kod" olmaktan çıkıp "Ürün" (Product) haline geldiği son adımdır. Senin "körüm" dediğin bu süreçte, sistem şu 4 teknik operasyonu senin adına otonom olarak gerçekleştirir:

1.  **AAB (Android App Bundle) Üretimi:** APK artık eskidi. AAB, Google Play'in cihazlara göre optimize ettiği, şifreli ve imzalı nihai dosya formatıdır.
2.  **V3/V4 Signing:** Uygulamanın sana ait olduğunu kanıtlayan dijital imza.
3.  **Data Safety Form Sync:** Uygulamanın hangi verileri (Email, Konum vb.) topladığının Google sunucularına beyan edilmesi.
4.  **Pre-launch Report:** Google botlarının uygulamayı yayınlamadan önce 100 farklı cihazda test edip "Çökme Yok" onayı vermesi.

## 10.2 İNFAZ ADIMLARI (EXECUTION STEPS)

### Adım 1: Release Bundle Oluşturma
Bu komut, tüm ProGuard kurallarını ve optimizasyonları uygulayarak imzalı AAB dosyasını üretir:
```bash
./gradlew bundleRelease
```

### Adım 2: Nihai Denetim (Omni-Audit)
Yayın öncesi 2026 standartlarına uygunluk testi:
```bash
./scripts/sovereign_omni_audit_v10000.sh
```

### Adım 3: Play Console Yükleme
`app/build/outputs/bundle/release/app-release.aab` yolundaki dosyayı Google Play Console'a sürükleyip bırakman yeterlidir.

## 10.3 KRİTİK 2026 KONTROLLERİ
- **Target SDK 35:** Android 15 uyumluluğu zorunlu.
- **16KB Page Size:** NDK kütüphaneleri hizalı (Rule 109).
- **Play Integrity:** Sunucu taraflı doğrulama aktif (Rule 112).
- **Privacy Manifest:** Tüm API kullanımları beyan edildi.
