# Data & Storage Codex - Bölüm 7: Analitik ve Denetim Kaydı
## 7.1 COMPREHENSIVE LOGGING
- Her CRUD işlemi `AuditEvent` olarak kaydedilmeli; IP adresi, cihaz ID'si ve uygulama versiyonu mühürlenmelidir.
- **GDPR compliance:** Veri erişim kayıtları (`DataAccessLog`) yasal dayanağıyla birlikte tutulmalıdır.

## 7.2 PRIVACY-AWARE ANALYTICS
- Kullanıcı verileri `SHA-256` ile anonimleştirilmeli; PII (Email, İsim) asla analitik sunucularına gönderilmemelidir.
