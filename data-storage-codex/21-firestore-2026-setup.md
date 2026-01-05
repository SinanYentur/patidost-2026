# Data & Storage Codex - Bölüm 21: Firestore 2026 Kurumsal Yapılandırma
## 21.1 FIREBASE BOM & APP CHECK
- **BOM Integration:** Tüm Firebase kütüphaneleri `com.google.firebase:firebase-bom:32.7.0` mühürü üzerinden merkezi olarak yönetilmelidir.
- **App Check:** `PlayIntegrityAppCheckProviderFactory` ile her bir Firestore çağrısı cihaz bütünlüğü doğrulamasına (Android 15+) mühürlenmiştir.

## 21.2 RECURSIVE SECURITY RULES
`rules_version = '2'` standardında, varsayılan olarak tüm erişimlerin kapalı olduğu (Default Deny) ve sub-collection seviyesinde yetkilendirme yapan mühürler kullanılacaktır.
