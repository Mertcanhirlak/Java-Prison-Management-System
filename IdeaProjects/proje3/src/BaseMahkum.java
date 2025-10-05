// abstract class sınıfı
abstract class BaseMahkum {
    //class ve alt sınıflarına özel değişkenler
    protected String id;
    protected String adSoyad;

    // yapıcı metodumuz yardımıyla direkt isim ve id alıp alt sınıflara aktarıyoruz
    public BaseMahkum(String id, String adSoyad) {
        //alınan bilgileri bu sınıftaki değişkenlere aktardık
        this.id = id;
        this.adSoyad = adSoyad;
    }

    //yukarda set ettiğimiz değişkenleri
    //get ederek kullanabiliyoruz
    //id getiren metot
    public String getId() {
        return id;
    }
    //ad soyad getiren metot
    public String getAdSoyad() {
        return adSoyad;
    }
    //alt sınıfın override edip kullanabilmesi için bilgi döndüren metot
    public void mahkumSucBilgisi() {
        System.out.println("Mahkum suç bilgisi henüz bilinmiyor");
    }
    //aynı işlevi gören abstract metotumuz (gövdesiz)
    public abstract void mahkumKogusBilgisi();

    //aynı işlevi gören abstract metotumuz (gövdesiz)
    public abstract void mahkumZamanBilgisi();
}
