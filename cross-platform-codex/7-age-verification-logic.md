# Cross-Platform Codex - Bölüm 7: Yaş Doğrulama Mantığı
## 7.1 VERIFICATION METHODS
- **Strict:** Government ID ve NFC mühürleme (Android 15+).
- **Moderate:** Kredi kartı doğrulaması ve 3D Secure mühürleri.
- **Light:** AI tabanlı yüz analizi (Age Estimation) ve içerik tarama mühürleri.

## 7.2 AGE RESTRICTIONS
Kullanıcı yaş grubuna (Under 13, Teen, Adult) göre `applyAgeBasedRestrictions` mühürüyle uygulama içeriği dinamik olarak filtrelenecektir.
