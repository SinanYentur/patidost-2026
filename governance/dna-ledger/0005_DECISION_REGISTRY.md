# 0005_DECISION_REGISTRY.md

## Stratejik Karar Defteri

Bu dosya, mimariyi şekillendiren "neden"leri kaydeder. Bir teknolojinin neden seçildiği veya bir paternin neden reddedildiği burada mühürlenir.

---

### **Karar: Coroutine `Mutex` Kullanımı**
- **Soru:** Eşzamanlı operasyonlarda state bütünlüğü nasıl korunacak?
- **Seçim:** `kotlinx.coroutines.sync.Mutex`
- **Reddedilen Alternatif:** `java.util.concurrent.atomic.AtomicBoolean`
- **Gerekçe:** `AtomicBoolean` sadece tek bir "flag"in atomik değişimini garanti eder, ancak `Mutex.withLock { ... }` bir kod bloğunun tamamının (birden fazla state değişikliği içerebilir) başka bir coroutine tarafından bölünmeden, atomik bir birim olarak çalışmasını sağlar. Bu, asenkron güvenlik ve state bütünlüğü için daha güçlü bir garantidir. State Rollback Hukuku'nu destekler.

---

### **Karar: `fallbackToDestructiveMigration` Yasağı**
- **Soru:** Room veritabanı şeması değiştiğinde ne olacak?
- **Seçim:** Manuel `Migration` sınıfları yazmak.
- **Reddedilen Alternatif:** `fallbackToDestructiveMigration()`
- **Gerekçe:** Veri, uygulamanın en değerli varlığıdır. `fallbackToDestructiveMigration`, kullanıcı verilerinin kolayca silinmesine yol açar ki bu kabul edilemez bir risktir. Manuel migration yazmak, şema evrimini kontrol altında tutar ve veri bütünlüğünü korur. Veri Hukuku'nu tesis eder.

---

### **Karar: `FakeApiService` Emekliliği (Production'da)**
- **Soru:** Geliştirme ve test aşamasında backend olmadan UI nasıl geliştirilir?
- **Seçim:** `FakeApiService` implementasyonu, ancak sadece `debug` veya `test` build varyantlarında enjekte edilir.
- **Reddedilen Alternatif:** Production kodunda `if (BuildConfig.DEBUG)` kontrolü yapmak.
- **Gerekçe:** Production kodunun sahte veya test verileriyle hiçbir bağı olmamalıdır. Bu, hem güvenlik açığı riskini ortadan kaldırır hem de kodun temiz kalmasını sağlar. `FakeApiService`'in `release` build'de bulunması, Network Reality Law'un ihlalidir.

---

### **Karar: FAZ 2 Kapanışı**
- **Soru:** Ne zaman yeni özellikler eklemeye başlayabiliriz?
- **Seçim:** FAZ 2'yi kapatıp, tüm savunma hukukları (State, Veri, Build, vb.) tesis edildikten sonra FAZ 3'e geçmek.
- **Reddedilen Alternatif:** Savunma hatlarını kurarken aynı anda yeni özellikler geliştirmek.
- **Gerekçe:** Savunmasız bir temelin üzerine yeni katlar çıkmak, gelecekte daha büyük ve öngörülemeyen krizlere yol açar. Önce temeli sertleştirmek (Hardening), sonra üzerine inşa etmek, projenin uzun vadeli sağlığı için zorunludur. Bu, PROJECT ZERO PROTOCOL'ün temel bir ilkesidir.
