import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

//bu sınıfımız ise günümüz tarih ve zamanını bulmaya yarar
public class ZamanBilgisi {
    public void zamanBilgisi() {
        //tarih değişkeni oluşturup şimdiki zamanı alıyoruz
        LocalDateTime simdikiZaman = LocalDateTime.now();

        // Tarih ve saat formatını belirliyoruz
        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

        // Formatlanmış tarih ve saati kaydediyoruz
        String formatlanmisTarihSaat = simdikiZaman.format(format);

        // burda da bilgileri yazdırıyoruz
        System.out.println("Bugünün Tarihi ve Saat: " + formatlanmisTarihSaat);
    }

}
