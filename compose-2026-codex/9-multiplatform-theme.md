# Compose 2026 Codex - Bölüm 9: Compose Multiplatform (KMM)
## 9.1 EXPECT/ACTUAL UI
- **Theme Bridge:** Platforma özgü temalar (Android Material vs iOS Bridge) `expect` ve `actual` mühürleriyle yönetilecektir.
- **Shared Components:** Ortak bileşenlerde `PlatformSpecificTheme` sarmalayıcıları zorunludur.

## 9.2 COMPONENT DEFAULTS
Platforma özgü `ButtonElevation` veya `Shape` tanımları, `interactionSource` mühürleriyle senkronize edilmelidir.
