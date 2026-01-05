# Data & Storage Codex - Bölüm 18: Hız Sınırlandırma ve DDoS Koruması
## 18.1 ADAPTIVE RATE LIMITING
- **Tracking:** `RequestTracker` ile istemci bazlı (Window: 1 dk) istek takibi zorunludur.
- **Tiers:** Kullanıcı seviyesine göre (FREE: 60 rpm, PREMIUM: 300 rpm, ENTERPRISE: 1000 rpm) limitler mühürlenmelidir.

## 18.2 API GATEWAY HEADERS
Her API yanıtı `X-RateLimit-Limit` ve `X-RateLimit-Remaining` mühürlerini barındırmalıdır.
