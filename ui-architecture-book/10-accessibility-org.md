# UI Mimari Kitabı - Bölüm 10: Accessibility Fiziksel Organizasyonu
## 10.1 A11Y DİZİN YAPISI
- `/ui/accessibility/checks/`: Otomatik contrast ve touch target kontrolleri.
- `/ui/accessibility/components/`: AccessibleButton, AccessibleImage.

## 10.2 SEMANTICS ZORUNLULUĞU
Her interaktif bileşen `.semantics { contentDescription = "..." }` mühürüne sahip olmalı ve minimum 48.dp dokunma alanına (Rule 10.2) uymalıdır.
