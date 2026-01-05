# Compose 2026 Codex - Bölüm 1: Stabilite ve Performans Mühürleri
## 1.1 @IMMUTABLE & @STABLE
- **Immutable:** Data class'lar (User, Pet vb.) ve değişmez konfigürasyonlar için zorunludur.
- **Stable:** Dinamik State tutan sınıflar (UiState) için recomposition mühürü olarak kullanılacaktır.

## 1.2 DYNAMIC INFERENCE
`remember { mutableStateOf(...) }` blokları, Compose Runtime 2026 standardına göre otomatik stabilite çıkarımı (Inference) ile mühürlenecektir.
