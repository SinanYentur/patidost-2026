# Data & Storage Codex - Bölüm 19: Gizlilik İçin Veri Maskeleme
## 19.1 REAL-TIME MASKING
- **Standard:** Email, Telefon ve Kredi Kartı verileri UI'da gösterilmeden önce `DataMasker` mühürüyle taranmalıdır.
- **Annotations:** `@SensitiveData` annotasyonu ile PII (Personal Identifiable Information) alanları kod seviyesinde mühürlenecektir.

## 19.2 CONTEXT-AWARE MASKING
Veri maskeleme kararı bağlama göre (LOGGING: Always Mask, DISPLAY: Partial Mask, LEGAL: No Mask) dinamik olarak mühürlenmelidir.
