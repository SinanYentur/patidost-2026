# UI Mimari Kitabı - Bölüm 7: UI State Management Pattern
## 7.1 STATE SINIFI (ZORUNLU)
Her ViewModel bir `UIState` sealed interface veya @Stable data class barındırmalıdır.
- Loading, Success, Error durumları açıkça tanımlanmalı.
- Immutable persistent collection kullanımı şarttır.

## 7.2 EVENT/STATE PATTERN
- Kullanıcı aksiyonları `sealed interface Event` olarak mühürlenmeli.
- StateFlow koleksiyonu `collectAsStateWithLifecycle` ile yapılmalı.
