# FAZ 1 & 2 KAPANIŞ DENETİM CHECKLIST'İ
## Paket 001: AUTH & KİMLİK HUKUKU

**Amaç:** Sistemin kimlik omurgasının bütünlüğünü, güvenliğini ve anayasaya uygunluğunu denetlemek ve mühürlemek. Bu kapı mühürlenmeden başka hiçbir denetim paketi başlatılamaz.

---

### GATE A1.1 — KİMLİK ÜRETİMİ (IDENTITY GENESIS)

| Test Adımı | Beklenen Davranış | Kanıt Yöntemi | Mühür Kriteri (PASS/FAIL) |
| --- | --- | --- | --- |
| **1. Email/Şifre ile Kayıt** | Kullanıcı, geçerli bir email ve şifre ile kayıt olduğunda Firebase Auth'da yeni bir kullanıcı oluşturulur ve Firestore `users` koleksiyonuna ilgili doküman eklenir. | Firebase Auth Console, Firestore `users` collection | **PASS:** Her iki platformda da kayıt tutarlıdır. **FAIL:** Kayıtlardan biri eksik veya hatalıysa. |
| **2. Google/Facebook ile Kayıt** | OAuth akışı başarıyla tamamlanır, Firebase Auth'da kullanıcı oluşturulur ve Firestore'a doküman eklenir. İsim, email, fotoğraf gibi temel bilgiler senkronize edilir. | Firebase Auth Console, Firestore `users` collection | **PASS:** OAuth bilgileri ile Firestore dokümanı eşleşiyor. **FAIL:** Akış başarısız olur veya veri tutarsızlığı yaşanırsa. |
| **3. Telefon OTP ile Doğrulama** | Kullanıcı telefon numarasını girer, SMS ile gelen OTP'yi doğrular. Bu doğrulama Firebase Auth'da `phone_number` alanını ve `verificationLevel`'ı günceller. | Firebase Auth Console'da `phone_number` alanı, Firestore'daki `verificationLevel` alanı. | **PASS:** Telefon numarası doğrulanır ve `verificationLevel` yükselir. **FAIL:** OTP gelmez, yanlış OTP kabul edilir veya `verificationLevel` güncellenmezse. |

---

### GATE A1.2 — OTURUM HUKUKU (SESSION LAW)

| Test Adımı | Beklenen Davranış | Kanıt Yöntemi | Mühür Kriteri (PASS/FAIL) |
| --- | --- | --- | --- |
| **1. Uygulama Kapatılıp Açılma** | Kullanıcı login olduktan sonra uygulamayı tamamen kapatıp (kill) açtığında, otomatik olarak login olmuş şekilde devam eder. Tekrar şifre sorulmaz. | Logcat'te geçerli bir token ile session'ın restore edildiğini görmek. UI'ın ana ekrana yönlenmesi. | **PASS:** Session sorunsuz devam ediyor. **FAIL:** Kullanıcıdan tekrar login istenirse veya crash olursa. |
| **2. Token Süresinin Dolması & Yenilenmesi** | Access token'ın süresi dolduğunda (örn: 1 saat), yapılacak ilk API isteğinde `TokenAuthenticator` (veya interceptor) 401 hatasını yakalar, refresh token ile sessizce yeni bir access token alır ve başarısız olan isteği yeni token ile tekrarlar. | Network Inspector (Charles, Flipper, vb.) veya Logcat. 401 -> yeni token isteği -> 200 yanıtı -> orijinal isteğin 200 ile tamamlanması. | **PASS:** Kullanıcı akışı kesintiye uğramaz. **FAIL:** Kullanıcıya hata gösterilir, logout olur veya akış bozulursa. |
| **3. Geçersiz Session** | Refresh token da geçersiz olduğunda (örn: başka cihazdan logout olma), `TokenAuthenticator` yenileme yapamaz ve kullanıcıyı login ekranına yönlendirir. Lokal session bilgileri temizlenir. | Logcat, API yanıtında 401, UI'ın login ekranına yönlenmesi. | **PASS:** Kullanıcı güvenli bir şekilde logout edilir ve bilgilendirilir. **FAIL:** Kullanıcı hatalı bir state'te kalırsa. |

---

### GATE A1.3 — YETKİ SINIRI (AUTHORIZATION BOUNDARY)

| Test Adımı | Beklenen Davranış | Kanıt Yöntemi | Mühür Kriteri (PASS/FAIL) |
| --- | --- | --- | --- |
| **1. Başkasının Kaynağına Yazma** | Kullanıcı A'nın token'ı ile, Kullanıcı B'ye ait bir pet'i düzenlemeye çalışan API isteği sunucu tarafından reddedilir (403 Forbidden). | API yanıt logları (403 Forbidden veya benzeri yetki hatası), Firestore Security Rules logları. | **PASS:** İstek net bir şekilde reddedilir. **FAIL:** İstek başarılı olursa (KRİTİK GÜVENLİK AÇIĞI). |
| **2. Logout Olmuş Token ile Yazma** | Kullanıcı logout olduktan sonra, eski token'ı ile yapılan herhangi bir yazma (post, comment, vb.) isteği sunucu tarafından reddedilir (401 Unauthorized). | API yanıt logları (401 Unauthorized). | **PASS:** Tüm istekler reddedilir. **FAIL:** Herhangi bir isteğin başarılı olması (KRİTİK GÜVENLİK AÇIĞI). |

---

### GATE A1.4 — SİLME ÖN TESTİ (DELETION PRE-AUDIT)

| Test Adımı | Beklenen Davranış | Kanıt Yöntemi | Mühür Kriteri (PASS/FAIL) |
| --- | --- | --- | --- |
| **1. "Hesabı Sil" Tetikleme** | Kullanıcı hesabını silme işlemini başlattığında, Firebase Auth'daki kullanıcı ya hemen silinir ya da 'disabled' durumuna getirilir. Kullanıcı anında logout edilir. | Firebase Auth Console'da kullanıcının durumunun değişmesi, Logcat'te logout logları. | **PASS:** Kullanıcının auth state'i anında geçersiz kılınır. **FAIL:** Kullanıcı işlem sonrası aktif kalmaya devam ederse. |
| **2. Silme Sonrası Token Geçersizliği** | Silme işlemi sonrası, eski access ve refresh token'lar ile yapılan tüm API istekleri sunucu tarafından reddedilir (401 Unauthorized). | API yanıt logları (401 Unauthorized). | **PASS:** Eski token'lar tamamen işlevsizdir. **FAIL:** Eski token ile herhangi bir okuma/yazma işlemi başarılı olursa (KRİTİK GÜVENLİK AÇIĞI). |
| **3. Lokal Verinin Temizlenmesi** | Hesap silme başarılı olduğunda, cihazdaki kullanıcıya ait tüm lokal veriler (session, cache, DataStore/SharedPreferences) tamamen temizlenir. | Uygulama verilerini inceleme araçları, uygulamanın bir sonraki açılışta ilk kurulum ekranını göstermesi. | **PASS:** Cihazda kullanıcıya ait hiçbir iz kalmaz. **FAIL:** Eski verilerin kalması ve potansiyel olarak yeni kullanıcıya sızması. |
