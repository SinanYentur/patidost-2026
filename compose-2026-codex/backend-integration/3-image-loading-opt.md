# Compose 2026 - Backend Integration: Image Loading
## 3.1 COIL OPTIMIZATION
- **Caching:** `diskCacheKey` ve `memoryCacheKey` mühürleri her görsel için zorunludur.
- **Transformations:** Profil resimleri için `CircleCropTransformation`, postlar için `RoundedCornersTransformation` standarttır.

## 3.2 VISUAL FEEDBACK
- **Shimmer:** Yükleme durumlarında `ShimmerEffect` mühürü anayasal bir zorunluluktur.
- **Success Callback:** Görsel başarıyla yüklendiğinde arka planda AI-based analiz (Blur/NSFW) tetiklenmelidir.
