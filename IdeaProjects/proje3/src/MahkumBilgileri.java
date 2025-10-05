// abstract classı miras alan sınıfımız
//bu sınıf mahkum bilgilerini yazdırmak için tasarlanmıştır
class MahkumBilgileri extends BaseMahkum {
    //classa özel gizli kullanacağımız değişkenleri tanımladık
    private String sucBilgisi;
    private String kogusBilgisi;
    private int cezaSuresi; // Ceza süresi (gün cinsinden)
    private String girisTarihi;
    private String cikisTarihi; // Yeni özellik

    //sınıfın yapıcı metoduyla özellşkler alınıp değilkenlere ekleniyor
    public MahkumBilgileri(String id, String adSoyad, String sucBilgisi, String kogusBilgisi, int cezaSuresi, String girisTarihi, String cikisTarihi) {
        super(id, adSoyad);
        this.sucBilgisi = sucBilgisi;
        this.kogusBilgisi = kogusBilgisi;
        this.cezaSuresi = cezaSuresi;
        this.girisTarihi = girisTarihi;
        this.cikisTarihi = cikisTarihi; // Yeni özellik atanıyor
    }

    //miras aldığımız sınıfta tanımlı olan metodu override ederek kullanıyoruz
    @Override
    public void mahkumSucBilgisi() {
        System.out.println("Suç Bilgisi: " + sucBilgisi);
    }
    //aynı şekilde gövdesiz tanımladığımız metotlarıda override ederek kullanıyoruz
    @Override
    public void mahkumKogusBilgisi() {
        System.out.println("Koğuş Bilgisi: " + kogusBilgisi);
    }
    //son gövdesiz metodumuzu da kullandık
    @Override
    public void mahkumZamanBilgisi() {
        System.out.println("Ceza Süresi: " + cezaSuresi + " gün");
    }

    //mahkumun çıkış tarihi hesaplandıktan sonra döndüren getiren metot
    public String getCikisTarihi() {
        return cikisTarihi;
    }
    //bu metot sayesinde tüm bilgileri tek bir metotta toplayıp yazdırabiliyoruz
    public void mahkumBilgisi() {
        System.out.println(" ID: " + id );
        System.out.println(" Ad Soyad: " + adSoyad);
        System.out.println(" Suç: " + sucBilgisi+ " Koğuş: " + kogusBilgisi);
        System.out.println(" Ceza Süresi: " + cezaSuresi +" Gün");
        System.out.println(" Giriş Tarihi: " + girisTarihi );
        System.out.println(" Çıkış Tarihi: " + cikisTarihi);
        System.out.println(" * * * * * * * * * * *");
    }

    //eğer koğuş bilgisi değiştirilmek istenirse bu metot yardımıyla yeni koğuş bilgisi alınır
    public void setKogusBilgisi(String yeniKogus) {
        this.kogusBilgisi = yeniKogus;
    }
}
