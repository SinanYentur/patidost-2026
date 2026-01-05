# UI Mimari Kitabı - Bölüm 5: Compose Modern Mimari
## 5.1 PERFORMANS ANNOTASYONLARI
- **@Stable:** State sınıfları için zorunlu.
- **@Immutable:** Değişmez konfigürasyon nesneleri için zorunlu.

## 5.2 DERIVED STATE
Hesaplanan her UI değeri `derivedStateOf` ile sarmalanmalıdır.
- Liste filtreleme.
- Fiyat toplamları.
- Dynamic boolean kontrolleri.
