# Mathematical Foundation - Bölüm 7: Database Scaling ve CAP
## 7.1 CAP & PACELC THEOREMS
- **Trade-offs:** Network partition anında Tutarlılık (C) veya Erişilebilirlik (A) seçimi matematiksel olarak mühürlenmiştir.
- **Latency:** AP/LC (Low Latency) sistemi için replikasyon gecikmesi (lag) olasılık hesabı tescil edilmiştir.

## 7.2 QUORUM CONSISTENCY
- **Guarantee:** R + W > N şartı ile strong consistency (güçlü tutarlılık) mühürlenmiştir.
- **Availability:** Binom dağılımı üzerinden sistemin kaç adet 9 (Availability Nines) ile çalışacağı tescil edilmiştir.
