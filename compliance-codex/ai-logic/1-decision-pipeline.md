# AI Logic Codex - Bölüm 1: Karar Verme Pipeline'ı
## 1.1 DATA PREPARATION
- **Quality:** Veri temizleme (NaN, Outlier) ve normalizasyon (Z-score) işlemleri mühürlenmiştir.
- **Bias Control:** Demographic parity ve equal opportunity testleri her eğitim döngüsünde zorunludur.

## 1.2 MODEL SELECTION & INFERENCE
- **Standard:** Küçük veriler için Tree-based (XGBoost), büyük veriler için Neural Networks mühürleri.
- **Optimization:** Float16 ve INT8 quantization ile NPU çıkarım (inference) verimliliği maksimize edilecektir.
