# Data & Storage Codex - Bölüm 20: Uyumluluk Otomasyonu
## 20.1 AUTOMATED CLEANUP
- **GDPR Art. 17:** 90 gün üzerindeki rızası geri çekilmiş veriler `ComplianceWorker` üzerinden otomatik olarak imha edilmelidir.
- **Cleanup Reports:** Silinen her veri bloğu için bir `CleanupReport` mühürü oluşturulmalıdır.

## 20.2 COMPLIANCE DASHBOARD
Uygulamanın yasal durumu (GDPR Score, Deadlines, Audit Logs) real-time bir `ComplianceViewModel` üzerinden izlenmeli ve fiziksel olarak raporlanmalıdır.
