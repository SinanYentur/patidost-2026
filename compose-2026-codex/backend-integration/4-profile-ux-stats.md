# Compose 2026 - Backend Integration: Profile UX & Stats
## 4.1 COLLAPSING TOOLBAR
- **Motion:** `scrollState` ve `offset` mühürleri ile dinamik header daralma efekti zorunludur.
- **Stats:** `StatItem` bileşenleri ile kullanıcı etkileşim metrikleri (Posts, Followers vb.) mühürlenmelidir.

## 4.2 TAB LAYOUT
`TabRow` ve `Tab` hiyerarşisi, reaktif `selectedTab` mühürü üzerinden farklı içerik türlerini (UserPosts, LikedPosts) yönetmelidir.
