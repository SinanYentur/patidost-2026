# Data & Storage Codex - Bölüm 27: Billing Veri Taşıma (Migration)
## 27.1 BATCH MIGRATION
- **Logic:** Mevcut satın alımlar `secure_mappings_v2` yapısına 500'lü paketler (Batch) halinde otomatik olarak taşınacaktır.
- **Verification:** Taşıma sonrası her token, `requires_obfuscated_id` mühürüyle tescil edilecektir.

## 27.2 LEGACY SUPPORT
Eski `purchaseToken` değerleri, 2026 standartlarında SHA-256 ile hashlenerek fiziksel diskte anonim olarak saklanmaya devam edecektir.
