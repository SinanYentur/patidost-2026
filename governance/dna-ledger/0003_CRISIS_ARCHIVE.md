# 0003_CRISIS_ARCHIVE.md

## Kriz Arşivi

Bu dosya, projenin bağışıklık sistemi hafızasıdır. Yaşanan her büyük kriz, gelecekteki krizleri önlemek için buraya kaydedilir.

---

### **[DNA-0011]**
- **Olay:** Toolchain Felaketi
- **Belirti:** `Can not extract resource from com.android.aapt2.aapt2-7.4.2-8324063-windows.jar`
- **Kök neden:** `material-1.11.0` kütüphanesinin `values.xml` dosyasındaki bozuk bir Unicode karakterin, Android build toolchain'ini (AAPT2) çökertmesi.
- **Yanlış hipotezler:** Gradle sürümü, cach'ler, IDE sorunları, donanım hataları.
- **Doğru teşhis:** Unicode zehirlenmesi.
- **Alınan karar:** Problemli kütüphane versiyonu sabitlendi ve toolchain hijyenine yönelik katı kurallar getirildi.
- **Tesis edilen hukuk:** Build & Environment Hijyen Hukuku.

---

### **[DNA-0014]**
- **Olay:** Hilt/KSP Yokluk Krizi
- **Belirti:** `error.NonExistentClass` hatasıyla build'lerin patlaması.
- **Kök neden:** Hilt dependency grafiğinde soyağacının kopması. Bazı `UseCase` veya `ViewModel`lerin ihtiyaç duyduğu `DataSource` gibi bağımlılıkların grafiğe sağlanmaması.
- **Yanlış hipotezler:** KSP bug'ı, Hilt versiyon uyumsuzluğu.
- **Doğru teşhis:** Eksik atalar. Dependency zincirinin kırık olması.
- **Alınan karar:** Tüm Hilt modüllerinin ve enjekte edilen sınıfların soyağacı denetlendi. Eksik olan `HomeDataSource` ve `PostPagingSource` gibi bileşenler eklendi.
- **Tesis edilen hukuk:** Dependency Lineage Control.

---

### **[DNA-0004]**
- **Olay:** Veri Bütünlüğü Krizi
- **Belirti:** `fallbackToDestructiveMigration` kullanımının veri kaybına yol açma potansiyeli.
- **Kök neden:** Veritabanı şema değişikliklerinin kontrolsüz yapılması.
- **Doğru teşhis:** Şema evriminin yönetilmemesi.
- **Alınan karar:** `fallbackToDestructiveMigration` yasaklandı. `exportSchema = true` zorunlu kılındı ve `MIGRATION_1_2` gibi manuel migration'lar yazılarak şema evrimi kontrol altına alındı.
- **Tesis edilen hukuk:** Veri Hukuku (Room Migration).

---

### **[DNA-0007]**
- **Olay:** State Tutarsızlığı Krizi
- **Belirti:** Optimistic UI güncellemeleri sırasında ağ hatası alındığında state'in bozuk kalması, double-submit ile veri tekrarı riski.
- **Kök neden:** Eşzamanlı operasyonların ve state değişikliklerinin atomik olmaması.
- **Doğru teşhis:** Race condition ve rollback mekanizması eksikliği.
- **Alınan karar:** Kritik operasyonlar için `Mutex` tabanlı execution lock mekanizması getirildi. Pessimistic ve `withTransaction` tabanlı rollback mimarisi kuruldu.
- **Tesis edilen hukuk:** State Rollback Hukuku.
