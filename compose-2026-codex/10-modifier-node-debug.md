# Compose 2026 Codex - Bölüm 10: Modifier.Node ve Debugging
## 10.1 LOW-LEVEL MODIFIERS
- **Modifier.Node:** Performans kritik (Custom Layout, Draw) yerlerde geleneksel Modifier yerine `Modifier.Node` yapısı mühürlenecektir.
- **Node Lifecycle:** `onAttach` ve `onDetach` mühürleriyle kaynak yönetimi kontrol altına alınacaktır.

## 10.2 RECOMPOSITION DEBUGGING
`LocalInspectionMode` ve `frameMetrics` mühürleriyle, 60 FPS altındaki her performans sorunu otomatik olarak taranacaktır.
