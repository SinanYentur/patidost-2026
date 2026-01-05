# Data & Storage Codex - Bölüm 16: Uçtan Uca Şifreleme (E2EE)
## 16.1 SIGNAL PROTOCOL STANDARTLARI
- **Double Ratchet:** Her mesaj için anahtar rotasyonu (Forward Secrecy) zorunludur.
- **Ephemeral Keys:** AES-GCM şifreleme ve ephemeral key değişimi mühürlenmelidir.

## 16.2 SECURE MESSAGING
Mesajlar sunucuya gitmeden önce cihazda şifrelenmeli, sunucu sadece şifreli `cipherText` ve `ephemeralPublicKey` mühürlerini görmelidir.
