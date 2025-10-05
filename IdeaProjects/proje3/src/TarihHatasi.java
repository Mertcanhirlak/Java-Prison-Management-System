//tarih hatası alınırken oluşabilecek hatalar nedeniyle
//özel olarak kendimiz bir exception sınıfı oluşturuyoruz
public class TarihHatasi extends Exception {
    //burda kendi oluşturduğumuz exception metodunu yazdırıyoruz
    public TarihHatasi(String message) {
        super(message);
    }
}
