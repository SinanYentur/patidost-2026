# Data & Memory Codex - Bölüm 10: NPU Model Quantization
## 10.1 QUANTIZATION STRATEGY
- **Hexagon (Qualcomm):** INT8 ve INT16 hassasiyetiyle birlikte 128-byte bellek hizalaması zorunludur.
- **Model Conversion:** ConvertedModel formatları (QNN, MNN, ONNX) donanıma göre dinamik olarak mühürlenmelidir.

## 10.2 TENSOR ALIGNMENT
Tensor verileri 64-byte hizalaması ile DMA (Direct Memory Access) kullanımına uygun olarak mühürlenecektir.
