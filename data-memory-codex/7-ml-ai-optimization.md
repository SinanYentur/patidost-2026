# Data & Memory Codex - Bölüm 7: ML ve Edge AI Optimizasyonu
## 7.1 MODEL PRECISION (2026)
- **Quantization:** Android 15+ cihazlarda INT4 ve INT8 modelleri NPU (Neural Processing Unit) kullanımı için zorunludur.
- **Interpreter Pooling:** Bellek tasarrufu için model yorumlayıcıları (Interpreters) LRU Cache (max: 3) mantığıyla yönetilmelidir.

## 7.2 NPU ACCELERATION
`setUseNNAPI(true)` ve `setEnableAutoDelegation(true)` mühürleri, donanımsal yapay zeka hızlandırması için anayasal standarttır.
