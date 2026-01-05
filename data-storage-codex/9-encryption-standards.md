# Data & Storage Codex - Bölüm 9: Şifreleme Standartları
## 9.1 HYBRID ENCRYPTION
- Büyük veriler için AES256-GCM (simetrik) ve RSA (asimetrik) anahtar değişimi kullanılmalıdır.
- **HMAC:** Veri bütünlüğü için her şifreli paket bir HMAC imzası taşımalıdır.

## 9.2 CERTIFICATE PINNING
Android 15+ için Network Security Config üzerinden Certificate Transparency ve TLS 1.3 mühürü zorunludur.
