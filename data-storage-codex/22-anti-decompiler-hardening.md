# Data & Storage Codex - Bölüm 22: Tersine Mühendislik Koruması
## 22.1 R8/PROGUARD HARSH RULES
- **Dictionary Attack:** `-obfuscationdictionary` mühürü ile değişken ve sınıf isimleri matematiksel olarak anlamsız hale getirilmelidir.
- **Log Stripping:** Üretim (Release) build'lerinde tüm `android.util.Log` çağrıları fiziksel diskten kazınacaktır.

## 22.2 NATIVE KEY STORAGE (JNI)
Hassas API anahtarları ve şifreleme tuzları (salts) sadece C++ katmanında (.so dosyaları) mühürlü olarak saklanacaktır.
