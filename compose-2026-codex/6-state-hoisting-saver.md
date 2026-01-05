# Compose 2026 Codex - Bölüm 6: State Hoisting ve Tasarruf
## 6.1 TYPE-SAFE HOISTING
- **Hoisting Pattern:** Alt bileşenlerin durumu, `rememberLazyListStateWithType` gibi tip-güvenli sarmalayıcılarla üst katmanda yönetilmelidir.
- **Custom Savers:** Karmaşık nesneler için `rememberSavableState` ve `Saver` mühürleri zorunludur.

## 6.2 PAGINATION
`derivedStateOf` ile listenin sonuna ulaşıldığı otomatik olarak taranmalı ve paginasyon tetiklenmelidir.
