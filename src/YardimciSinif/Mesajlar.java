package YardimciSinif;

import javax.swing.JOptionPane;

public class Mesajlar {
	public static void mesajYazdir(String basilacak) {
		String mesaj;
		if (basilacak.equals("bosAlan")) {
			mesaj = "Lütfen Gerekli Alanlarý Doldurunuz!";
		} else if (basilacak.equals("bilgilerYanlis")) {
			mesaj = "Lutfen gecerli bir kullanici adi ve sifre giriniz";
		} else if (basilacak.equals("onay")) {
			mesaj="Ýþlem Baþarýyla Gerçekleþtirildi!";
		} else
			mesaj = basilacak;

		JOptionPane.showMessageDialog(null, mesaj, "Mesaj", JOptionPane.INFORMATION_MESSAGE);
	}

}
