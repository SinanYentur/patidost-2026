# Play Store 2026 Codex - Bölüm 7: Google Cloud Setup & Handshake
## 7.1 API ENABLMENT
- Google Cloud Console üzerinden "Play Integrity API" aktif edilmelidir.
- Play Console > Setup > App Integrity üzerinden Google Cloud projesi ile linkleme (linking) zorunludur.

## 7.2 SERVICE ACCOUNT
- `play-integrity-validator` rolüne sahip bir Service Account oluşturulmalı.
- JSON Key dosyası, Firebase Functions (`index.js`) içindeki `GOOGLE_APPLICATION_CREDENTIALS` mühürüne bağlanmalıdır.
- **Quota:** Günlük 10.000 request sınırı, 2026 Enterprise planında "Sustained" moduna çekilmelidir.
