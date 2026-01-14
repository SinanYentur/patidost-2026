# 0004_LAW_CHARTER.md

## Yürürlükteki Mimari Hukuklar

Bu dosya, kodun uymak zorunda olduğu, platformdan ve iş mantığından bağımsız, değişmez kuralları içerir. Kod bu hukuka tabidir.

---

### Veri Hukuku (Data Law)
- **Room Migration:** `fallbackToDestructiveMigration` yasaktır. Tüm şema değişiklikleri, `exportSchema = true` ile belgelenmeli ve manuel `Migration` sınıfları ile yönetilmelidir.
- **Single Source of Truth (SSOT):** Cache ve network'ten gelen veriler için tek bir güvenilir kaynak (genellikle veritabanı) olmalıdır.
- **Paging:** Büyük veri setleri `Paging` kütüphanesi ile yönetilmelidir.

### State Rollback Hukuku (State Rollback Law)
- **Atomicity:** Kritik state değişiklikleri (örn: puan bağışı) atomik olmalıdır.
- **Mutex Lock:** Eşzamanlı state değişikliklerini ve race condition'ları önlemek için Coroutine `Mutex` kullanılacaktır. `AtomicBoolean` gibi daha zayıf mekanizmalar yetersizdir.
- **Rollback Mekanizması:** Hem pesimistik (önce sunucu onayı) hem de optimistik (UI güncellemesi + geri alma) rollback senaryoları tanımlı olmalıdır. Ağ hatalarında veya sunucu reddinde state tutarlılığı garanti edilmelidir.

### Build & Environment Hijyen Hukuku (Build & Environment Hygiene Law)
- **Toolchain Stabilitesi:** Projeyi çökerten kütüphane güncellemeleri, kök neden analizi yapılmadan geri alınamaz veya güncellenemez.
- **Kaynak (Resource) Hijyeni:** XML ve diğer kaynak dosyalarına şüpheli veya bozuk karakterlerin girişi engellenmelidir. String'ler `values.xml` içinde tanımlanmalıdır.
- **Cache Temizliği:** "Clean build" ve "Invalidate caches" gibi işlemler, ancak ve ancak tekrarlanabilir bir sorunun cache kaynaklı olduğu kanıtlandığında uygulanır.

### Dependency Lineage Hukuku (Dependency Lineage Law)
- **Hilt Soyağacı:** Her `ViewModel`, `UseCase`, `Repository` ve `DataSource`'un Hilt dependency grafiğindeki yeri ve bağlantısı net olmalıdır.
- **Eksik Ata Yasağı:** Bir bileşen, kendisini sağlayacak olan üst bileşen (ata) Hilt grafiğine eklenmeden kullanılamaz.

---

### **[FAZ 3 YENİ HUKUKLARI]**

### Network Reality Law
- İstemci hiçbir zaman Fake/sahte bir veri kaynağı ile production build'da çalışamaz.
- Tüm network çağrıları, hataları yakalayan ve yöneten bir `SafeApiCall` mekanizmasından geçmek zorundadır.
- UI katmanı (Composable, Activity, Fragment) ham network `Exception`'larını (`HttpException`, `IOException`, vb.) görmez; yalnızca bu hataların çevrildiği anlamlı `UiState` (örn: `Error`, `Offline`) durumlarını görür.

### Environment Contract
- `baseUrl` gibi ortama özgü konfigürasyonlar kod içinde sabit olarak (`hardcode`) yazılamaz.
- Bu değerler, `build.gradle` ( `buildTypes` veya `productFlavors` aracılığıyla) üzerinden gelmeli ve merkezi bir `Environment` veya `ApiConfig` nesnesi tarafından yönetilmelidir.
- Ortamlar (DEV, STAGING, PROD) arasındaki geçiş, tek bir kontrol noktasından yapılabilmelidir.

### Network Error Law
- HTTP hata kodları (401, 403, 404, 422, 500+) rastgele `Exception`'lar olarak değil, sınıflandırılmış ve yönetilen `NetworkException` alt türleri olarak ele alınmalıdır.
- Sunucudan gelen hata mesajını içeren `error body`, parse edilerek anlamlı bir hata nesnesine dönüştürülmeden üst katmanlara (özellikle UI) iletilemez.
- `Timeout`, `No-Internet` (DNS, `UnknownHostException`), ve `SSL` hataları gibi ağ altyapısı sorunları, HTTP hatalarından ayrı olarak ele alınmalıdır.

### Network Failure Doctrine
- Network işleminin başarısız olması, sistemin çökmesi anlamına gelmez. Bir network hatası, uygulamanın donmasına, çökmesine veya sessizce başarısız olmasına (`silent fail`) asla neden olamaz.
- Tüm potansiyel network hataları (beklenen ve beklenmeyen), kullanıcıya bilgi veren, net ve yönetilebilir bir `UiState`'e dönüştürülmelidir (örn: "İnternet bağlantısı yok" mesajı gösteren bir snackbar, "Tekrar dene" butonu).
