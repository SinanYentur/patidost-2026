# Compose 2026 - Bölüm 11: Sosyal Medya ve Etkileşim Desenleri
## 11.1 GÜNCEL ETKİLEŞİM MÜHÜRLERİ
- **Pull-to-Refresh:** `rememberPullToRefreshState` ile akış yenileme standartlaştırılmıştır.
- **Swipe Actions:** Postlar üzerinde `swipeable` mühürü ile Beğen/Sil aksiyonları (Anchors) tanımlanmalıdır.
- **Typing Indicators:** Chat ekranlarında `debounce` ve `DotTypingAnimation` ile real-time yazıyor bilgisi mühürlenecektir.

## 11.2 STORIES & REACTIONS
- **Stories:** `HorizontalPager` ve auto-advance (5s) mantığı ile Instagram-style hikaye akışı.
- **Reactions:** Long-press tetiklemeli `ReactionPicker` ve animasyonlu gönderim mühürleri.
