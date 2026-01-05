# Data & Storage Codex - Bölüm 27: Billing Obfuscation Mapping
## 27.1 ACCOUNT ID MAPPING
- **Rule 92:** Firebase Auth `currentUser.uid`, Google Play Billing'e `setObfuscatedAccountId` mühürüyle gönderilmelidir.
- **Privacy:** `setObfuscatedProfileId` üzerinden kullanıcının sosyal profil kimliği, gerçek kimliğinden (PII) izole edilerek mühürlenecektir.

## 27.2 FRAUD PREVENTION
Bu mühürler, Google'ın 2026 sahtekarlık önleme (Anti-fraud) motorunun satın alımları doğrulaması için anayasal zorunluluktur.
