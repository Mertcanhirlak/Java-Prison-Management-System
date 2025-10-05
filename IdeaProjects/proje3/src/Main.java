public class Main {
    public static void main(String[] args) {
        //kullanıcıya ilk karşılama mesajı veriyoruz
        System.out.println("Hapishane yönetim sistemine hoş geldiniz!");
        //arayüz newleyerek işlemimizi gerçekleştirecek elemanı oluşturduk
        KullaniciArayuzu arayuz = new KullaniciArayuzu();
        //kullanıcıya ne yapması gerektiği hakkında bilgi verdik
        System.out.println("Ana menü getiriliyor yapmak istediğiniz işlemi seçin:");
        //burdan ana menü döngüsüne yönlendiriyoruz
        arayuz.anaMenu();
    }
}