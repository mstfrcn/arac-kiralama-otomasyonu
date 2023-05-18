package YardimciSinif;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JComboBox;
public class ComboBoxVerileriCekme {
	VeritabaniBaglantilari baglanti = new VeritabaniBaglantilari();	
	public void verileriGetir(JComboBox comboBox, String sorgu, String colonAdi) {
		baglanti.baglan();
	
		ResultSet rs = baglanti.listele(sorgu);
		try {
			while(rs.next()) {
				comboBox.addItem(rs.getString(colonAdi));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public Integer verileriGetir(String sorgu, String colonAdi) {
		baglanti.baglan();
	
		ResultSet rs = baglanti.listele(sorgu);
		String sonuc;
		try {
			while(rs.next()) {
				
				return rs.getInt(colonAdi);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}

