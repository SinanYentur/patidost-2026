# UI Mimari Kitabı - Bölüm 12: Modern Mimari Desenleri (2026)
## 12.1 MVI & STATE INTENT
- **State:** @Stable data class ile tek yönlü (Unidirectional) veri akışı.
- **Intent:** Kullanıcı aksiyonları sealed interface ile mühürlenir.
- **Side Effects:** Channel üzerinden `Flow` ile UI'a iletilir.

## 12.2 KMM (KOTLIN MULTIPLATFORM) READY
Business logic (UseCase & Repository) `expect/actual` yapısına hazır, Pure Kotlin olarak kurgulanmalıdır.
