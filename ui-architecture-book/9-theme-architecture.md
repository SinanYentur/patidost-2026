# UI Mimari Kitabı - Bölüm 9: Dark/Light Theme Fiziksel Yapısı
## 9.1 THEME PROVIDER
Theme switching mantığı `ThemeProvider.kt` içinde mühürlenmeli ve `isSystemInDarkTheme()` ile senkronize olmalıdır.

## 9.2 SEMANTIC COLORS
Renkler doğrudan `Color` nesnesi olarak değil, `AppColors` data class'ı üzerinden semantik isimlerle (primary, onSurface, success vb.) kullanılmalıdır.
