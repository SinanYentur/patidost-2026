# Universal Codex - Bölüm 5: Veri Taşınabilirliği ve Kullanıcı Hakları
## 5.1 DATA EXPORT (GDPR ART. 20)
- **Format:** Kullanıcının tüm verileri (Posts, Messages, Activity) JSON veya SPDX formatında mühürlenip sunulacaktır.
- **Portability:** `DataPortabilityService` üzerinden şifreli (E2EE) veri paketi üretimi zorunludur.

## 5.2 DATA DELETION (GDPR ART. 17)
`completeDeletion` akışıyla; anonimleştirme, backup silme ve CDN temizleme işlemleri 30 gün içinde fiziksel olarak tamamlanacaktır.
