# UI Mimari Kitabı - Bölüm 1: Fiziksel Paket Yapısı
## 1.1 TEMEL DİZİN DERİNLİĞİ
- `/ui/screen/{feature}/{sub-feature}/`: SADECE ekranlar ve o ekrana özel bileşenler.
- `/ui/component/core/`: Global paylaşılan bileşenler.
- `/ui/navigation/`: NavGraph ve Destination tanımları.

## 1.2 FEATURE ZORUNLULUKLARI
Her feature klasörü `README.md`, `{Feature}Screen.kt`, `{Feature}ViewModel.kt` ve `components/` klasörü içermelidir.
