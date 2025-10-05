//kullanılacak kütüphanelerimiz
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

//bu sınıfımızda ise mahkumla ilgili yapılacak ve değiştirilecek işlemler gerçekleştirilir
public class MahkumManager implements IKullaniciArayuzu {
    //buraya özel bir list tanımlıyoruz
    private List<MahkumBilgileri> mahkumListesi;

    public MahkumManager() {
        //burda arraylist list değişkenini implemente ettiği için
        //mahkumlistesi değişkenine atayabiliyoruz
        this.mahkumListesi = new ArrayList<>();
    }
    //mahkum listesini getiren metodumuz
    public List<MahkumBilgileri> getMahkumListesi() {
        return mahkumListesi;
    }

    // eğer arayüzden mahkum ekleme işlemi seçilirse
    //burdaki metot çalıştırılacaktır
    public void mahkumEkle() {
        //alıcımızı tanımlıyoruz
        Scanner scanner = new Scanner(System.in);
        System.out.println("Yeni mahkum eklemek için bilgileri giriniz:");

        //mahkum idsini alıyoruz
        String id;
        while (true) {
            System.out.print("Mahkum ID: ");
            //id yi düzgün bir şekilde almak için sona da trim ekliyoruz
            id = scanner.nextLine().trim();
            //eğer id girilmezse ve boş kalırsa bu hatayı döndürüp tekrar istenir
            if (!id.isEmpty()) {
                break;
            } else {
                System.out.println("ID boş bırakılamaz. Lütfen geçerli bir giriş yapın:");
            }
        }

       //ad soyad bilgisi alınır
        String adSoyad;
        while (true) {
            System.out.print("Ad Soyad: ");
            //scanner yardımıyla ad soyad bilgisini alıyoruz
            adSoyad = scanner.nextLine().trim();
            //boş girilmediyse işlem tamamlanır boş girildiyse tekrar girilmesi istenir
            if (!adSoyad.isEmpty()) {
                break;
            } else {
                System.out.println("Ad Soyad boş bırakılamaz. Lütfen geçerli bir giriş yapın.");
            }
        }

        // Mahkumun işlemiş olduğu suçu kullanıcıdan alınmasını sağlar
        String sucBilgisi;
        while (true) {
            System.out.print("Suç Bilgisi: ");
            //scanner yardımıyla suç alınır
            sucBilgisi = scanner.nextLine().trim();
            //suç kısmına boş girildiyse kullanıcıdan tekrar bilgi alınmasını sağlar
            if (!sucBilgisi.isEmpty()) {
                break;
            } else {
                System.out.println("Suç bilgisi boş bırakılamaz. Lütfen geçerli bir giriş yapın.");
            }
        }

        // Koğuş bilgisi alınır
        String kogusBilgisi;
        while (true) {
            System.out.print("Koğuş Bilgisi: ");
            //scanner yardımıyla koğus bilgisi alınır
            kogusBilgisi = scanner.nextLine().trim();
            //eğer boş girildiyse tekrar istenir
            if (!kogusBilgisi.isEmpty()) {
                break;
            } else {
                System.out.println("Koğuş bilgisi boş bırakılamaz. Lütfen geçerli bir giriş yapın.");
            }
        }

        // Ceza süresi alınır bu metotta
        int cezaSuresi;
        while (true) {
            System.out.print("Ceza Süresi (gün): ");
            //ceza süresi yanlış girilebileceğinden dolayı try catch e sokuyoruz
            try {
                //kullanıcıdan string olarak girilen değeri tam sayı türüne dönüştürtürülür
                cezaSuresi = Integer.parseInt(scanner.nextLine().trim());
                //ceza süresi düzgün girilirse kaydedilir girilmezse tekrar istenir
                if (cezaSuresi > 0) {
                    break;
                } else {
                    System.out.println("Ceza süresi pozitif bir sayı olmalıdır. Lütfen tekrar giriniz: ");
                }
            }
            //hatayı tutan catchimiz
            catch (NumberFormatException e) {
                System.out.println("Geçersiz giriş: Ceza süresi bir sayı olmalıdır. Lütfen tekrar giriniz: ");
            }
        }

        // Giriş Tarihi alınır
        String girisTarihi;
        while (true) {
            //kullanıcıya girmesi gereken formatı gösteriyoruz
            System.out.print("Giriş Tarihi (dd/MM/yyyy formatında): ");
            //girdiği tarihi alıyoruz
            girisTarihi = scanner.nextLine().trim();
            //yanlış girilebileceğinden dolayı try catch kullanıyoruz
            try {
                //burda metin türümdeki girdiyi tarihi türe çevirir
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                LocalDate.parse(girisTarihi, formatter); //
                break;
            } catch (Exception e) {
                System.out.println("Geçersiz giriş: Tarih formatı 'dd/MM/yyyy' olmalıdır. Lütfen tekrar deneyin.");
            }
        }

        // Çıkış tarihini cezamanager de yazmış olduğumuz metot yardımıyla hesaplatıyoruz
        CezaManager cezaManager = new CezaManager(mahkumListesi);
        String cikisTarihi = cezaManager.cikisTarihiniHesapla(girisTarihi, cezaSuresi);

        // yeni mahkum eklemek için mahkum bilgileri sınıfından bir referans tanımlayıp
        //o sınıftaki metodu bu şekilde kullanarak mahkum ekliyoruz
        MahkumBilgileri yeniMahkum = new MahkumBilgileri(id, adSoyad, sucBilgisi, kogusBilgisi, cezaSuresi, girisTarihi, cikisTarihi);
        mahkumListesi.add(yeniMahkum);
        System.out.println("Mahkum başarıyla eklendi.");
    }

    // Mahkumları listeleme işlemini çalıştırıoyurz
    public void mahkumlariGoster() {
        //eğer ki kayıtlı mahkum yoksa bilgi mesajı veriyoruz
        if (mahkumListesi.isEmpty()) {
            System.out.println("Hiç mahkum bulunmuyor.");
            return;
        }
        // eğer mahkumlar kayıtlıysa teker teker bilgileri yazdırıyoruz
        for (MahkumBilgileri mahkum : mahkumListesi) {
            mahkum.mahkumBilgisi();
        }
    }

    // Mahkum koğuş değişikliği işlemi için kullanıyoruz
    public void kogusDegistir() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Koğuş değişikliği yapmak istediğiniz mahkumun ID'sini girin: ");
        String id = scanner.nextLine();

        //burda bütün mahkum listesini gezerek id karşılaştırması yapar
        // istenilen mahkumu bulduktan sonra koğuş değişikliği yapılır
        for (MahkumBilgileri mahkum : mahkumListesi) {
            if (mahkum.getId().equals(id)) {
                //eğer id ye sahip mahkum bulunursa koğuş düzenlenebilir
                System.out.print("Yeni koğuş bilgisi: ");
                String yeniKogus = scanner.nextLine();
                mahkum.setKogusBilgisi(yeniKogus);
                System.out.println("Koğuş bilgisi başarıyla güncellendi.");
                return;
            }
        }
        //eğer olmayan bir id girilirse böyle bir hata verilir
        System.out.println("Belirtilen ID ile eşleşen mahkum bulunamadı.");
    }

    //eğer olası bir kod hatasında burdan anamenüye tekrar dönülme olaanağı sağlanır
    @Override
    public void anaMenu() {
        System.out.println("Ana menü çağrılıyor...");
        KullaniciArayuzu arayuzu = new KullaniciArayuzu();
        arayuzu.anaMenu();
    }

    // Mahkum tahliye etme işlemi için kullanılan metodumuz
    public void mahkumTahliyeEt() {
        if (mahkumListesi.isEmpty()) {
            System.out.println("Tahliye edilecek mahkum bulunmuyor.");
            return;
        }
        //kullanıcıdan tahliye edilmesini istediği mahkum id sini giriyoruz
        Scanner scanner = new Scanner(System.in);
        System.out.println("Tahliye etmek istediğiniz mahkumun ID'sini giriniz:");
        String id = scanner.nextLine();
        //ilerde bulunan mahkumu kaydetmek için değişkenimizi oluşturduk
        MahkumBilgileri mahkumBulunan = null;

        //mahkum arama işlemi
        for (MahkumBilgileri mahkum : mahkumListesi) {
            // eğer bulunursa mahkumu kaydediyoruz değişkenimize
            if (mahkum.getId().equals(id)) {
                mahkumBulunan = mahkum;
                break;
            }
        }

        //mahkum eğer bulunamayıp null olarak kalmadıysa işlem gerçekleşir
        if (mahkumBulunan != null) {
            mahkumListesi.remove(mahkumBulunan);
            System.out.println("Mahkum ID: " + id + " başarıyla tahliye edilmiştir.");
        } else {
            //eğer mahkum değişkeni null olarak kaldıysa hata verilir

        }

        //eğer yukardaki kodda olası bir hatada
        //bu kod onun yerine çalışarak mahkumu tahliye etmesi sağlanır
        for (int i = 0; i < mahkumListesi.size(); i++) {
            if (mahkumListesi.get(i).getId().equals(id)) {
                mahkumListesi.remove(i);
                System.out.println("Mahkum başarıyla tahliye edildi.");
                return;
            }
        }
        System.out.println("Belirtilen ID ile eşleşen mahkum bulunamadı.");
    }
}
