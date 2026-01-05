# Data & Storage Codex - Bölüm 17: GDPR Denetim Kaydı
## 17.1 AUTOMATIC LOGGING
- **Interceptor:** Tüm API çağrıları `AuditLogInterceptor` ile mühürlenmeli; method, header ve URL bilgileri kurumsal log formatında tutulmalıdır.
- **Sensitive Mapping:** Hassas endpointlerden dönen veriler (`DATA_ACCESS`) yasal dayanağıyla birlikte `AuditLog` tablosuna kaydedilmelidir.

## 17.2 DATA EXPORT (ART. 20)
Kullanıcının tüm verileri (Posts, Messages, Activity) `GDRPExport` formatında şifreli bir paket olarak mühürlenip sunulabilmelidir.
