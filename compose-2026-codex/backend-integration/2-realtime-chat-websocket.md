# Compose 2026 - Backend Integration: Real-time Chat
## 2.1 REVERSE LAZYCOLUMN
- **Layout:** Mesaj akışı için `reverseLayout = true` zorunludur.
- **Auto-scroll:** Yeni mesaj geldiğinde `scrollToItem(0)` mühürü ile kullanıcı deneyimi tescil edilecektir.

## 2.2 WEBSOCKET SYNC
Her mesaj `UUID.randomUUID()` ile unique olarak mühürlenmeli ve `SENDING` statusu ile reaktif olarak izlenmelidir.
