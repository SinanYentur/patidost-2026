# Play Store 2026 Codex - Bölüm 3: Play Integrity API Entegrasyonu
## 3.1 ÜRETİM KONTROLLERİ
- Her kritik işlemden (Auth, Payment) önce `IntegrityTokenRequest` zorunludur.
- **2026 Flag'leri:** Device Integrity, Install Source Integrity ve Runtime Integrity kontrolleri mühürlenmiştir.

## 3.2 ANTI-ABUSE MEKANİZMASI
- Rate limiting (saatte 1000 request) ve şüpheli pattern tespiti için fiziksel fingerprinting mühürü eklendi.
- Backend doğrulaması için `playintegrity.googleapis.com` senkronizasyonu şarttır.
