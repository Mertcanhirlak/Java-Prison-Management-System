import java.util.Scanner;
// bu sınıfımız bütün işlemlerin döneceği yerdir
//ana menü burda çalışır ve kullanıcın seçeceği işlem burda alınır
//alınan işleme göre diğer sınıflara yönlendirme işlemi de burda yapılır

class KullaniciArayuzu implements IKullaniciArayuzu {

    //kullanıcıdan girdi alabilmek için scanner
    private Scanner scanner = new Scanner(System.in);

    //MahkumManager sınfıındaki işlemleri kullanabilmemiz için referansımız
    private MahkumManager mahkumManager;

    //CezaManager sınfıındaki işlemleri kullanabilmemiz için referansımız
    private CezaManager cezaManager;

    //sınıf çalıştırıldığında otomatik olarak bu tür değişkenlerimiz oluşturulacaktır
    public KullaniciArayuzu() {
        this.mahkumManager = new MahkumManager();
        this.cezaManager = new CezaManager(mahkumManager.getMahkumListesi());

    }
    //ana menü sistemimiz
    // bu sistem yardımıyla konsol tabanlı bir arayüz elde ediyoruz
    public void anaMenu() {
        int secim;
        //ana menümüzün kullanıcı isteği dışında kapanmasını engellemek için
        // do while döngüsüne sokuyoruz
        do {
            //menümüzde yapılabilecek işlemleri yazdırıyoruz
            System.out.println("\n--- Ana Menü ---");
            System.out.println("1- Mahkum Ekle");
            System.out.println("2- Mahkum Bilgilerini Göster");
            System.out.println("3- Mahkumların Kalan Ceza Süreleri");
            System.out.println("4- Mahkum Tahliye Et");
            System.out.println("5- Mahkum koğuş değişikliği");
            System.out.println("6- Günün Tarihi ve Saati");
            System.out.println("0- Çıkış");
            System.out.print("Seçiminiz: ");
            secim = scanner.nextInt();
            scanner.nextLine(); // Boşluğu temizle

            switch (secim) {
                case 1:
                    //lambda isareti yardımıyla islem tekrarla metodumuzu da sisteme entegre ettik
                    //ekleme işlemi bittiğinde tekrar yapmak istenirse ana menüye dönülmeden hızla eklenebilir
                    islemTekrarla(() -> mahkumManager.mahkumEkle(), "Mahkum ekleme işlemi");
                    break;
                case 2:
                    //aynı şekilde bilgileri tekrar göstermek istendiğinde kolayca gösterilebilir
                    islemTekrarla(() -> mahkumManager.mahkumlariGoster(), "Mahkum bilgilerini gösterme işlemi");
                    break;
                case 3:
                    //kalan ceza sürelerini göstermek için referansımızı çağırıyoruz
                    islemTekrarla(() -> cezaManager.kalanCezaSureleriniGoster(), "Kalan ceza sürelerini gösterme işlemi");
                    break;
                case 4:
                    do {
                        mahkumManager.mahkumTahliyeEt(); // Tahliye etme işlemi çağrıyoryz
                        System.out.print("\nİşlemi tekrar yapmak için 1, ana menüye dönmek için 0 giriniz: ");
                        int cevap = scanner.nextInt();
                        scanner.nextLine(); // Boşluğu temizlemek için
                        if (cevap == 0) {
                            break; // Ana menüye dönmemizi sağlar
                            //farklı bir yöntemle sürekliliği sağlamak için bunu kullandık
                        }
                    } while (true);
                    break;
                case 5:
                    //koğuş değiştirmek için managerdaki metodumuzu kullanıyoruz
                  mahkumManager.kogusDegistir();
                    break;
                case 6:
                    //bugünün tarihini ve saatini yazdıran metodumuzu oluşturup gönderiyoruz
                    ZamanBilgisi zaman = new ZamanBilgisi();
                    zaman.zamanBilgisi();
                      break;
                case 0:
                    //eğer ki menü kapatılmak istenirse 0 a basılması taktirde sistem kapatılcak
                    System.out.println("Çıkış yapılıyor...");
                    return;
                default:
                    //olmayan bir rakam veya harf girildiğinde verilecek hata
                    System.out.println("Hatalı seçim! Lütfen tekrar deneyin.");
            }
        } while (true);
    }

    //bu işlemi tekrarlamak için kullandığımız metottur
    //runnable olarak tanımlamak bir iş parçacığını çalıştırmak için kullanmak istememizdendir
    private void islemTekrarla(Runnable islem, String islemAciklamasi) {
        do {
            System.out.println("\n--- " + islemAciklamasi + " ---");
            islem.run(); // İşlemi gerçekleştiririz
            //kullanıcıya iki seçenek sunarız
            System.out.print("\nİşlemi tekrar yapmak için 1, ana menüye dönmek için 0 seçiniz: ");
            int cevap = scanner.nextInt();
            scanner.nextLine(); // Boşluğu temizlemek için kullanırız
            //eğer 0 ı seçerse o işlem biter ve ana menüye tekrar dönülür
            if (cevap == 0) {
                System.out.println("Ana menüye dönülüyor...\n");
                break;
            } else if (cevap != 1) {
                //eğer işlem birden farklı bir şeyse hata verip tekrar girmesini ister
                System.out.println("Hatalı giriş yaptınız! Lütfen 0 veya 1 giriniz.");
            }
            //o dönene kadar işlem devam eder
        } while (true);
    }
}
