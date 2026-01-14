# Patidost - Play Store Policy Anayasası v1

Bu belge, Patidost uygulamasının Google Play Store üzerindeki tüm finansal işlemleri için bağlayıcı olan anayasal kuralları ve mimari sınırları tanımlar.

## MADDE 1: ŞEFFAFLIK VE KULLANICI BİLGİLENDİRME

1.1. **Fiyatlandırma:** Tüm abonelik (Gold Üyelik) ve tek seferlik ürün (Pati Puanı paketleri) fiyatları, vergiler dahil olmak üzere, satın alma ekranında net bir şekilde gösterilecektir.
1.2. **Abonelik Koşulları:** Aboneliklerin süresi, yenileme periyodu ve toplam maliyeti, satın alma akışından önce kullanıcıya açıkça beyan edilecektir.
1.3. **İptal Politikası:** Kullanıcılar, Google Play abonelikler menüsünden veya uygulama içinden sağlanacak kolay erişilebilir bir bağlantı ile aboneliklerini istedikleri zaman iptal edebilirler. İptal durumunda, mevcut fatura döneminin sonuna kadar ayrıcalıklardan yararlanmaya devam edecekleri belirtilecektir.

## MADDE 2: SATIN ALMA VE DOĞRULAMA

2.1. **Backend Doğrulaması (ZORUNLU):** Her satın alma işlemi (`Purchase Token`), sahtekarlığı ve tekrar kullanımını önlemek için Patidost backend'i üzerinden Google Play Developer API'si aracılığıyla doğrulanmalıdır. Client tarafında yapılan hiçbir doğrulama nihai kabul edilemez.
2.2. **Onaylama (Acknowledgement):** Backend doğrulaması başarıyla tamamlandıktan ve kullanıcıya ilgili hak (Gold üyelik, Pati Puanı) tanındıktan sonra, satın alma işlemi 3 gün içinde `acknowledgePurchase()` çağrısı ile onaylanmalıdır. Bu sorumluluk backend'e aittir.
2.3. **Güvenli Token Yönetimi:** `Purchase Token`, client tarafından alınır alınmaz backend'e güvenli bir kanal üzerinden iletilmeli ve client tarafında saklanmamalıdır.

## MADDE 3: TEK SEFERLİK ÜRÜNLER (Pati Puanı)

3.1. **Tüketim (Consumption):** Satın alınan Pati Puanı paketleri, kullanıcıya hak olarak tanındıktan sonra, aynı paketin tekrar satın alınabilmesi için `consumeAsync()` çağrısı ile tüketilmelidir. Bu sorumluluk, backend doğrulaması sonrası client'a aittir.

## MADDE 4: TEST

4.1. **Kapalı Test (ZORUNLU):** Gerçek parayla yapılan işlemlerden önce, tüm satın alma akışları (başarılı, kullanıcı tarafından iptal, hatalı ödeme, ağ hatası) Google Play Console'da tanımlanmış "license testers" ve test kartları kullanılarak kapalı bir test ortamında doğrulanmalıdır.

## MADDE 5: YASAKLANAN DAVRANIŞLAR

5.1. **Yanıltıcı Davranış:** Satın alma akışı, kullanıcıyı yanıltacak, gizli maliyetler içeren veya abonelik koşulları hakkında şeffaf olmayan hiçbir "dark pattern" içeremez.
5.2. **Fonksiyon Kilitleme:** Uygulamanın temel fonksiyonları (örneğin, swipe etme, profil görme), bir satın alma işlemi yapmaya zorlayacak şekilde kilitlenemez. Gold üyelik, sadece ek faydalar ("value-add") sunabilir.
