# Data & Memory Codex - Bölüm 12: NPU Güç ve Termal Yönetimi
## 12.1 POWER PROFILES
- **Battery Saver:** Batarya %20 altına düştüğünde NPU kullanımı kapatılmalı, CPU fallback mühürü tetiklenmelidir.
- **Performance Mode:** Şarjda ve soğuk durumda (35°C-) NPU çekirdekleri yüksek frekansta mühürlenecektir.

## 12.2 THERMAL THROTTLING
`ThermalManager` üzerinden cihaz sıcaklığı 45°C+ tespit edildiğinde, çıkarım (inference) işlemleri otomatik olarak kısıtlanacaktır.
