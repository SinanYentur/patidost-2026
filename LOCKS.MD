# LOCKS.MD - QUANTUM EVOLUTION: OPENED LOCKS LOG

Bu belge, AI'nın evrim sürecinde karşılaştığı "Kilitleri" ve bu kilitleri açmak için kullandığı kanıta dayalı "Anahtarları" kaydeder. Her kilit, bir gelişim fırsatıdır. Her anahtar, o gelişimin kanıtıdır.

---

**KİLİT ID:** 003

**TESPİT EDİLEN GÖREV:** GÖREV-004

**KİLİT TANIMI:** Proje için modern, mobil uyumlu ve kanıta dayalı bir backend teknoloji yığınının seçilmesi gerekiyordu.

**ANAHTAR (ÇÖZÜM):** Ktor ve Spring Boot karşılaştırıldı. Kotlin-native, hafif ve asenkron yapısı nedeniyle Ktor, mobil DNA ile en uyumlu teknoloji olarak seçildi.

**KANIT REFERANSI (BRAIN.MD LOG):** V1.9

---

**KİLİT ID:** 002

**TESPİT EDİLEN GÖREV:** GÖREV-003B

**KİLİT TANIMI:** Rule 300.4 (Watchdog Protocol) ihlal edildi. Projede standart bir ANR izleme mekanizması bulunmuyordu.

**ANAHTAR (ÇÖZÜM):** Endüstri standardı olan `com.github.anrwatchdog:anrwatchdog` kütüphanesi entegre edildi.

**KANIT REFERANSI (BRAIN.MD LOG):** V1.6

---

**KİLİT ID:** 001

**TESPİT EDİLEN GÖREV:** GÖREV-003A

**KİLİT TANIMI:** Rule 300.2 (Schema Safety) ihlal edildi. Projede Room veritabanı geçişlerini doğrulayan bir test altyapısı bulunmuyordu.

**ANAHTAR (ÇÖZÜM):** `androidx.room:room-testing` kütüphanesi ve `MigrationTestHelper` sınıfı kullanılarak bir test altyapısı kuruldu.

**KANIT REFERANSI (BRAIN.MD LOG):** V1.4
