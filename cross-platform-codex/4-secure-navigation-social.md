# Cross-Platform Codex - Bölüm 4: Güvenli Sosyal Navigasyon
## 4.1 ROUTE SECURITY
- **Age Gating:** `isAgeRestricted` mühürü olan rotalarda `navigateToAgeVerification` akışı zorunludur.
- **Regional Blocking:** `isBlockedInRegion` kontrolü ile coğrafi kısıtlamalar NavGraph seviyesinde mühürlenmiştir.

## 4.2 DEEP LINK SAFETY
`yourapp://profile/{userId}` formatındaki tüm deep linkler, `ContentSafetyChecker` mühüründen geçmeden navigasyon tetiklenmeyecektir.
