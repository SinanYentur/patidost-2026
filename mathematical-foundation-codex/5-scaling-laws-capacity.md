# Mathematical Foundation - Bölüm 5: Kapasite Planlama ve Scaling
## 5.1 PARALLEL SPEEDUP (AMDAHL & GUSTAFSON)
- **Amdahl's Law:** Speedup = 1 / ((1-p) + p/s). Sabit iş yükünde paralel işlemci sayısının getirisini mühürler.
- **Gustafson's Law:** Speedup = s + (1-s)*N. Problem büyüklüğü arttıkça ölçeklenebilirliği (scalability) kanıtlar.
- **Karp-Flatt Metric:** Paralel işlemedeki "overhead" (ek yük) mühürlerini ölçmek için tescil edilmiştir.

## 5.2 QUEUE THEORY (M/M/1 & M/M/C)
- **Little's Law:** L = λW (Sistemdeki kullanıcı = Varış hızı * Zaman).
- **Utilization (ρ):** λ/μ < 1 şartı, sistem kararlılığı için fiziksel bir mühürdür.
- **Tail Latency:** Poisson dağılımı üzerinden P95 ve P99 bekleme süreleri matematiksel olarak garantilenmiştir.
