# Compose 2026 Codex - Bölüm 4: Shared Element ve Morphing
## 4.1 SHARED TRANSITIONS
- **SharedBounds:** Ekranlar arası geçişlerde kart ve görsel mühürleri için `sharedElement` ve `sharedBounds` zorunludur.
- **Morphing:** İçerik değişimlerinde `Crossfade` ve `animatedVisibilityScope` ile yumuşak geçişler sağlanacaktır.

## 4.2 CONTENT STATE
`rememberSharedContentState` ile her bileşenin kimliği (Key) fiziksel olarak mühürlenmelidir.
