package AnaFonksiyonlar;
import YardimciSinif.*;
public class SilmeIslemleri {
	
	VeritabaniBaglantilari baglanti = new VeritabaniBaglantilari();
	


	public Boolean kayitSil(String sorgu) {
		baglanti.baglan();
		return baglanti.veriTabaniVeriSil(sorgu);
	}

	

	
}
