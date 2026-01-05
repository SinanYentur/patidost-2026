# Cross-Platform Codex - Bölüm 3: Sosyal Ağ API Kontratları
## 3.1 CONTENT & MODERATION
- **Content Creation:** @Multipart mühürü ile media upload ve audience (Public/Friends) seçimi zorunludur.
- **Reporting:** Her post için `reportPost` API mühürü ve 24 saatlik response süresi tescil edilmiştir.

## 3.2 SAFETY APIS
`checkMessageSafety` ve `getBlockedUsers` mühürleri üzerinden, kullanıcı güvenliği her bir endpoint çağrısında fiziksel olarak denetlenecektir.
