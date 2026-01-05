# Data & Storage Codex - Bölüm 1: 2026 Kurumsal Veri Mimarisi
## 1.1 STORAGE STACK
- **Presentation:** Compose UI State + ViewModel holders.
- **Domain:** Pure Kotlin UseCases + Entity Models.
- **Data:** Repository (SSOT) + Room 3.0 + Proto DataStore.
- **Infrastructure:** Android Keystore + TLS 1.3 + Zstandard Compression.

## 1.2 SSOT PROTOKOLÜ
Network verisi mutlaka Room'a yazılmalı ve UI'a Flow üzerinden SSOT (Single Source of Truth) olarak aktarılmalıdır.
