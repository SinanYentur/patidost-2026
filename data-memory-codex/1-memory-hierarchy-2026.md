# Data & Memory Codex - Bölüm 1: 2026 Android Bellek Hiyerarşisi
## 1.1 DONANIM KATMANI
- **RAM:** 2026 standardı 6GB-24GB arası değişkenlik gösterir.
- **ZRAM:** 6GB compressed swap artık endüstri standardıdır.
- **Unified Memory:** Compute Express Link (CXL) ile CPU-GPU birleşik bellek yapısı.

## 1.2 ART VM (ANDROID 16+)
- **Generational Concurrent GC:** Düşük gecikmeli çöp toplama.
- **JVMCI:** GraalVM tabanlı optimizasyonlar.
- **PGO:** Profile Guided Optimization mühürü.
