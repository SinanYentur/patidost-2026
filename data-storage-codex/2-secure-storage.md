# Data & Storage Codex - Bölüm 2: Güvenli Veri Depolama
## 2.1 CREDENTIAL STORAGE
- **Hardware-backed:** Android Keystore + Biometric Strong mühürü zorunludur.
- **EncryptedPrefs:** AES256_GCM ile kimlik bilgileri (tokens) kilitlenmelidir.

## 2.2 KEY ATTESTATION
Android 15+ için donanım seviyesinde anahtar doğrulaması (Key Attestation) her oturum açılışında yapılmalıdır.
