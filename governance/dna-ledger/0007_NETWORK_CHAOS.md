# 0007_NETWORK_CHAOS.md

## Network Kaos Senaryoları

Bu dosya, backend'e bağlanmadan önce sistemin ağ hatalarına karşı davranışını test etmek için yazılmıştır. Kod, bu senaryoları geçmek zorundadır.

---

### **Senaryo 1: İnternet Yokken Operasyon Başlatma**
- **Aksiyon:** Kullanıcı, cihaz offline iken bir gönderiye bağış yapar.
- **Beklenen Sistem Davranışı:** `SafeApiCall` `IOException` veya `UnknownHostException` yakalar ve bunu `NetworkException.Unavailable` olarak sınıflandırır.
- **Beklenen UI State:** `UiState.Error` durumuna geçilir. Ekranda "İnternet bağlantınızı kontrol edin" mesajı içeren bir `SnackBar` veya benzeri bir bileşen gösterilir. Loading indicator gizlenir.
- **Yasaklı Davranış:** Crash, ANR (donma), sessiz başarısızlık (hiçbir şey olmaması).

---

### **Senaryo 2: Yetkisiz İstek (401 Fırtınası)**
- **Aksiyon:** Kullanıcının token'ı geçersiz hale gelmişken ardı ardına birden fazla istek yapılır.
- **Beklenen Sistem Davranışı:** `AuthInterceptor` veya `TokenAuthenticator` ilk 401 yanıtını alır. Sonraki tüm istekler duraklatılır veya reddedilir. Token yenileme mekanizması devreye girer. Yenileme başarısız olursa kullanıcı Login ekranına yönlendirilir.
- **Beklenen UI State:** Ekranda kısa bir `Loading` görünebilir, ancak sonrasında ya akış devam eder (başarılı token yenileme) ya da kullanıcı Login ekranına yönlendirilir. Hiçbir zaman 401 hatası doğrudan UI'a yansımaz.
- **Yasaklı Davranış:** Her istek için ayrı ayrı hata göstermek, crash, kullanıcıyı habersizce akışta bırakmak.

---

### **Senaryo 3: Bozuk veya Beklenmedik JSON Yanıtı**
- **Aksiyon:** Sunucu, beklenen DTO yapısına uymayan bir JSON (eksik/fazla alan, yanlış tip) veya tamamen alakasız bir `HTML` sayfası döner.
- **Beklenen Sistem Davranışı:** JSON `SerializationException` fırlatır. `SafeApiCall` bu hatayı yakalar ve `NetworkException.Serialization` olarak sınıflandırır.
- **Beklenen UI State:** `UiState.Error` durumuna geçilir. Ekranda genel bir hata mesajı ("Bir sorun oluştu, lütfen tekrar deneyin") gösterilir.
- **Yasaklı Davranış:** Crash (özellikle `JsonDataException`, `JsonEncodingException`), UI'da bozuk veya eksik veri göstermek.

---

### **Senaryo 4: Yavaş Ağ ve Timeout**
- **Aksiyon:** Sunucu 10 saniyeden daha uzun bir sürede yanıt verir (OkHttp `readTimeout` aşıldı).
- **Beklenen Sistem Davranışı:** `SafeApiCall` `SocketTimeoutException` yakalar ve bunu `NetworkException.Timeout` olarak sınıflandırır.
- **Beklenen UI State:** `UiState.Error` durumuna geçilir ve "İstek zaman aşımına uğradı, lütfen tekrar deneyin" mesajı gösterilir. `Loading` indicator durdurulur.
- **Yasaklı Davranış:** ANR, kullanıcının sonsuza kadar `Loading` indicator görmesi.

---

### **Senaryo 5: Sunucu Hatası (5xx)**
- **Aksiyon:** Sunucu `500 Internal Server Error` veya `503 Service Unavailable` döner.
- **Beklenen Sistem Davranışı:** `SafeApiCall`, HTTP 5xx kodunu yakalar ve bunu `NetworkException.ServerError` olarak sınıflandırır.
- **Beklenen UI State:** `UiState.Error` durumuna geçilir. Kullanıcıya genel bir hata mesajı ("Sunucuda bir sorun oluştu, daha sonra tekrar deneyin") gösterilir.
- **Yasaklı Davranış:** Crash, ham HTML hata sayfasını kullanıcıya göstermek.
