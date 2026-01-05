# Data & Storage Codex - Bölüm 5: Uzak Veri Senkronizasyonu
## 5.1 OFFLINE-FIRST ARCHITECTURE
- Veri her zaman önce yerel (Local) DB'ye yazılmalı, ardından arka planda senkronize edilmelidir.
- **Sync States:** PENDING, IN_PROGRESS, SYNCED, CONFLICT, ERROR durumları mühürlenmelidir.

## 5.2 CONFLICT RESOLUTION
- Çakışma durumlarında "Smart Merge" veya "Server Wins" stratejileri UseCase bazında tanımlanmalıdır.
