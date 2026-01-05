# UI Mimari Kitabı - Bölüm 3: Navigasyon Tasarımı
## 3.1 NAVİGASYON-FEATURE EŞLEŞMESİ
Her NavGraph fiziksel olarak bir feature ile eşleşmelidir.
- `/ui/navigation/graph/MainNavGraph.kt`: Root delegasyon.
- `/ui/navigation/graph/AuthNavGraph.kt`: Auth feature ekranları.
- `/ui/navigation/graph/PetNavGraph.kt`: Pet feature ekranları.

## 3.2 DESTİNATİON KONUMLANDIRMA
Destination tanımları fiziksel olarak feature klasörü içinde yer almalıdır.
- Örn: `/ui/screen/pet/detail/PetDetailDestination.kt`

## 3.3 TİP GÜVENLİĞİ
Tüm navigasyon argümanları `/ui/navigation/argument/` altında `Serializable` veya `Parcelable` olarak tanımlanmalıdır.
