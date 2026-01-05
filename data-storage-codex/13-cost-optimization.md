# Data & Storage Codex - Bölüm 13: Maliyet Optimizasyonu
## 13.1 DATA TIERING
- **Hot Data:** Sık erişilen veriler (Auth, Active Session) In-Memory veya Room'da.
- **Warm Data:** Haftalık veriler (History) Proto DataStore'da.
- **Cold Data:** Nadir erişilen veriler (Old Logs) Zstandard (Zstd) ile sıkıştırılarak saklanmalıdır.

## 13.2 NETWORK COST
- Adaptive Compression: 3G/4G bağlantılarda agresif sıkıştırma, Wi-Fi'da yüksek hız mühürü.
