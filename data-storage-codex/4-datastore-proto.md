# Data & Storage Codex - Bölüm 4: Protocol Buffers DataStore
## 4.1 TYPE-SAFE STORAGE
- SharedPreferences yerine Protocol Buffers (proto3) tabanlı DataStore kullanımı zorunludur.
- **Migration:** Eski tercihler `SharedPreferencesMigration` ile otomatik taşınmalıdır.

## 4.2 TRANSACTIONAL UPDATES
Veri güncellemeleri atomik `updateData` blokları içinde, `IO` dispatcher ile mühürlenmelidir.
