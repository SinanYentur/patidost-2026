# Data & Storage Codex - Bölüm 26: Google Play Billing 6.0+ Implementasyonu
## 26.1 ARCHITECTURAL STRUCTURE
- **BillingClientProvider:** Bağlantı yönetimi için Singleton mühürü.
- **BillingRepository:** Clean Architecture prensiplerine uygun veri erişim katmanı.
- **BillingManager:** ViewModel seviyesinde abonelik ve satın alım yönetimi.

## 26.2 SECURITY & VALIDATION
- **Signature Verification:** RSA-SHA256 ile satın alım imzalarının fiziksel doğrulaması.
- **Anti-Fraud:** Satın alım sıklığı ve cihaz bütünlüğü kontrolleri (RASP entegreli).
- **Acknowledge/Consume:** Her başarılı işlem 2026 standartlarında mühürlenmeli veya tüketilmelidir.
