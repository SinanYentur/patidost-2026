# Data & Memory Codex - Bölüm 11: NPU Inference Engine
## 11.1 PIPELINE PARALLELISM
- **Heterogeneous Compute:** CPU (Pre-process), NPU (Main inference) ve GPU (Post-process) katmanları arasında atomik veri akışı mühürlenmelidir.
- **Buffer Strategy:** `SHARED_MEMORY` ve `ASYNC_WITH_FENCES` senkronizasyon mühürleri zorunludur.

## 11.2 BATCH OPTIMIZATION
Cihazın termal durumu ve NPU çekirdek sayısına göre (LOW: 2, FLAGSHIP: 16) optimal batch boyutu dinamik olarak taranacaktır.
