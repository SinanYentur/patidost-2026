# Compose 2026 Codex - Bölüm 5: Özel Layout ve Intrinsics
## 5.1 LOOKAHEAD LAYOUT
- **Predictive Layouts:** Smooth animasyonlar için `LookaheadLayout` ile ön-ölçüm (Pre-measure) mühürü zorunludur.
- **Placement:** Donanım hızlandırmalı katmanlar için `placeWithLayer` kullanılmalıdır.

## 5.2 CUSTOM INTRINSICS
Özel bileşenlerde `minIntrinsicWidth` ve `maxIntrinsicWidth` hesaplamalarıyla, düzen (layout) kararlılığı fiziksel olarak mühürlenecektir.
