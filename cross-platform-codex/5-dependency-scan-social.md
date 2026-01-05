# Cross-Platform Codex - Bölüm 5: Sosyal Ağ Bağımlılık Denetimi
## 5.1 COMPLIANCE LIBRARIES
- **Moderation:** ML Kit Vision ve Text Recognition kütüphaneleri için CVSS denetimi zorunludur.
- **Analytics:** Facebook Core ve Adjust SDK'lar için App Tracking Transparency (ATT) mühürü mühürlenmiştir.

## 5.2 SAFETY STACK
Sendbird ve Twilio Voice gibi sosyal SDK'lar, `scripts/dependency_scan_social_app.py` üzerinden her build öncesi taranacaktır.
