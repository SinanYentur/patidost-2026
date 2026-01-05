# UI Mimari Kitabı - Bölüm 2: Component Paylaşım Protokolü
## 2.1 PAYLAŞIM HİYERARŞİSİ
1. **Tek Ekran:** `/ui/screen/{feature}/components/` (Private)
2. **Feature İçi (2+ Ekran):** `/ui/component/feature/{feature}/`
3. **Global (2+ Feature):** `/ui/component/core/`

## 2.2 CORE-UI SINIRLARI
- **YASAK:** Domain/Data referansları (import com.patidost.data.*).
- **İZİN:** Compose material3 ve local theme (import com.patidost.ui.theme.*).
