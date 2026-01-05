# Data & Storage Codex - Bölüm 14: Gözlemlenebilirlik ve İzleme
## 14.1 DISTRIBUTED TRACING
- **OpenTelemetry:** Her Repository ve Network çağrısı bir `Span` ile izlenmelidir.
- **Trace Correlation:** UI'dan başlayıp Database'e kadar uzanan işlem kimliği (Correlation ID) mühürü.

## 14.2 STRUCTURED LOGGING
- Üretim logları `Log.d` yerine JSON formatında, PII (Personal Identifiable Information) içermeyecek şekilde merkezi bir sunucuya (Firebase/RUM) aktarılmalıdır.
