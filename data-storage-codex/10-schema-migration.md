# Data & Storage Codex - Bölüm 10: Şema Migrasyonu ve Versiyonlama
## 10.1 ROOM MIGRATION STRATEGY
- Her şema değişikliği için `Migration` objesi tanımlanmalı ve `MIGRATION_X_Y` şeklinde isimlendirilmelidir.
- Veri dönüşümleri (Data Transformation) SQL sorguları ile atomik olarak yapılmalıdır.

## 10.2 PROTOBUF EVOLUTION
- Protocol Buffer dosyalarında alan numaraları (field numbers) asla değiştirilmemeli, silinen alanlar `reserved` ile işaretlenmelidir.
