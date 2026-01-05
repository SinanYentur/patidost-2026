# UI Mimari Kitabı - Bölüm 4: Cerrahi Operasyon Protokolü
## 4.1 MİGRASYON PLANI
Flat dizin yapısından hiyerarşik yapıya geçiş için zorunlu adımlar:
1. Feature Inventory tespiti.
2. Klasörlerin atomik olarak oluşturulması.
3. Component usage matrix analizi.
4. Import fix ve refactoring.

## 4.2 RİSK YÖNETİMİ
Her adım öncesi `validation-reports/` altına fiziksel kanıt dökülmelidir.
