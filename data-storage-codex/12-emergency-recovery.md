# Data & Storage Codex - Bölüm 12: Acil Durum ve Veri Kurtarma
## 12.1 AUTOMATED BACKUPS
- Uygulama verileri (`databases/` ve `shared_prefs/`) haftalık olarak `backups/` dizinine mühürlenmelidir.
- Veri bozulması tespit edildiğinde (Integrity FAIL), en güncel yedek üzerinden otomatik geri yükleme başlatılmalıdır.

## 12.2 DISASTER RECOVERY
Kritik veri kayıplarında sunucu ile tam senkronizasyon (Force Sync) tetiklenerek SSOT (Single Source of Truth) yeniden inşa edilmelidir.
