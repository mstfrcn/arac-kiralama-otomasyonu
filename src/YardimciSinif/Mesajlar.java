package YardimciSinif;

import javax.swing.JOptionPane;

public class Mesajlar {
	public static void mesajYazdir(String basilacak) {
		String mesaj;
		if (basilacak.equals("bosAlan")) {
			mesaj = "L�tfen Gerekli Alanlar� Doldurunuz!";
		} else if (basilacak.equals("bilgilerYanlis")) {
			mesaj = "Lutfen gecerli bir kullanici adi ve sifre giriniz";
		} else if (basilacak.equals("onay")) {
			mesaj="��lem Ba�ar�yla Ger�ekle�tirildi!";
		} else
			mesaj = basilacak;

		JOptionPane.showMessageDialog(null, mesaj, "Mesaj", JOptionPane.INFORMATION_MESSAGE);
	}

}
