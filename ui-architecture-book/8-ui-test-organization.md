# UI Mimari Kitabı - Bölüm 8: UI Test Fiziksel Organizasyonu
## 8.1 TEST DİZİN YAPISI
Test dosyaları production koduyla aynı klasörde (internal visibility) veya ilgili sub-package içinde durmalıdır.
- `/ui/screen/{feature}/test/`: ScreenTest + ViewModelTest.
- `/ui/component/core/{cat}/test/`: ComponentTest + Preview.

## 8.2 TEST ZORUNLULUKLARI
Her ekran için minimum 3 mühür: ScreenTest, ViewModelTest, Preview.
