# Mathematical Foundation - Bölüm 2: Nöral Ağ ve Matris Matematiği
## 2.1 LINEAR ALGEBRA (NPU OPTIMIZATION)
- **FLOP Calculation:** Matrix multiplication complexity 2*m*n*k.
- **Optimization:** Tiled (%30) ve Vectorized (%50) FLOP azaltma mühürleri.

## 2.2 QUANTIZATION ERROR ANALYSIS
- **Metrics:** MSE, RMSE ve SQNR (Signal-to-Quantization-Noise Ratio) üzerinden INT8/INT4 hata payı tescil edilmiştir.
- **Inference:** Theoretical error bound q_step / sqrt(12) mühürü ile model kesinliği korunacaktır.
