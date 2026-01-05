# UI Mimari Kitabı - Bölüm 11: UI Performance Monitoring
## 11.1 FRAME METRICS
`PerformanceMonitor.kt` üzerinden her ekranın jank oranı (60 FPS threshold) taranmalıdır.

## 11.2 RECOMPOSITION TRACKING
Önemli ekranlarda `TrackRecompositions` sarmalayıcısı kullanılarak gereksiz UI yükleri (Rule 11.2) fiziksel olarak raporlanmalıdır.
