//bu sınıf için gerekli olacak kütüphaneler
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.List;

//bu sınıfımızda ceza süreleri zaman üzerinden hesaplanacak
//tüm zaman işlemleri bu sınıfta ayarlanacak
class CezaManager {
    //yeni bir list ekledik
    private List<MahkumBilgileri> mahkumListesi;

    //yapıcı metotumuz çalışırlen mahkumlistesine bilgileri otomatik olarak ekleyecek
    public CezaManager(List<MahkumBilgileri> mahkumListesi) {
        this.mahkumListesi = mahkumListesi;
    }

    //mahkumların girdikleri tarih ve aldıkları ceza miktarına göre
    //otomatik olarak çıkış tarihini hesaplayan
    //ve bugünün tarihini de hesaba katarak mahkumların kalan
    //ceza sürelerini de bu metot sayesinde hesaplayabiliyoruz
    public void kalanCezaSureleriniGoster() {
        //eğer mahkum listesi boş ise metottan çıkılıyor
        if (mahkumListesi == null || mahkumListesi.isEmpty()) {
            System.out.println("Hiç mahkum bulunmuyor.");
            return;
        }

        // metotta kullanacağımız gerekli referansları tanımlıyoruz
        //tarih formatımızı tanımlıyoruz
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        //bugünün tarihini alan referansımızı tanımlıyoruz
        LocalDate bugun = LocalDate.now();

        // her mahkum için tek tek hesaplamak için for döngüsüne sokuyoruz
        for (MahkumBilgileri mahkum : mahkumListesi) {
            //özellikle tarihlerde sıkça hatalı giriş olmasından dolayı
            //try catch yardımıyla kullanıcıyı format hakkında bilgilendirip
            //programında çalışma esnasında patlamamasını sağlıyoruz
            try {
                //localdate türünde bir değişken oluşturuyoruz
                LocalDate tahliyeTarihi;
                try {
                    //bu işlem değişkenimize string olarak girilmiş değerden tarihe dönüştürülmüş halini yazar
                    tahliyeTarihi = LocalDate.parse(mahkum.getCikisTarihi(), formatter);
                }
                //olası bir hatada hatayı yakalayan catch metotumuz
                catch (Exception e) {
                    //eğer yanlış girilmesi durumunda hata kodumuza yönlendirilir ve hata yazdırılır
                    throw new TarihHatasi("Geçersiz tarih formatı: " + mahkum.getCikisTarihi());
                }
                // bu ifade yardımıyla iki tarih arasında kalan gün sayısı hesaplanır
                //böylece mahkumun kaç gün cezası kaldığı da bulunmuş olur
                long kalanGun = ChronoUnit.DAYS.between(bugun, tahliyeTarihi);

                //mahkum bilgilerini yazdırıyoruz
                System.out.println("Mahkum ID: " + mahkum.getId());
                System.out.println("Ad Soyad: " + mahkum.getAdSoyad());
                //cezaya göre oluşturulmuş tahliye tarihimizi yazdırıyoruz
                System.out.println("Tahliye Tarihi: " + tahliyeTarihi.format(formatter));
                if (kalanGun > 0) {
                    //eğer mahkum eskiden girip çıkış günü geçmediyse kalan gün hesaplanır
                    System.out.println("Kalan Ceza Süresi: " + kalanGun + " gün\n");
                } else {
                    //eğerki eskiden girip çıkış tarihi geçmişse kullancııya bilgi verilir
                    System.out.println("Mahkum tahliye edilmiştir.\n");
                }
            }
            //özel olarak tanımladığımız tarih exception ı kullanıyoruz hataları yakalamak için
            catch (TarihHatasi e) {
                System.out.println("Hata: " + mahkum.getId() + " için tahliye tarihi hatalı. " + e.getMessage());
            }
            //diğer hataları yakalamk içinse genel hataları yakalayan catchimiz
            catch (Exception e) {
                System.out.println("Hata: " + mahkum.getId() + " için kalan süre hesaplanamadı. " + e.getMessage());
            }
        }



    }
    //çıkış tarihini hesaplayan metodumuz
    public String cikisTarihiniHesapla(String girisTarihi, int cezaSuresi) {
        //burda kullanıcı istekli olabilecek tarih formatını değiştirme işlemini gerçekleştiriyoruz
        //input ve output değişkenleri ayarlıyoruz formatları giriyoruz
        DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy"); // Kullanıcıdan alınan format
        DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd"); // Sistem için döndürülen format
        //giriş formatını girdiye alıyoruz
        LocalDate giris = LocalDate.parse(girisTarihi, inputFormatter);
        //çıkış formatını ayarlıyoruz
        LocalDate cikis = giris.plusDays(cezaSuresi);
        //çıkış formatını yazdırıyoruz
        return cikis.format(outputFormatter);
    }

}