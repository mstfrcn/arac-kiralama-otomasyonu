package AnaFonksiyonlar;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.table.DefaultTableModel;

import GUI.AdminIslemleriGUI;
import YardimciSinif.*;

public class AdminIslemleri {

	String isim;
	String soyIsim;
	String kullaniciAdi;
	String sifre;
	String kurtarmaKodu;
	public AdminIslemleri(String isim, String soyIsim, String kullaniciAdi, String sifre, String kurtarmaKodu) {
		this.isim = isim;
		this.soyIsim = soyIsim;
		this.kullaniciAdi = kullaniciAdi;
		this.sifre = sifre;
		this.kurtarmaKodu = kurtarmaKodu;
	}
	
	public AdminIslemleri() {}
	VeritabaniBaglantilari baglanti = new VeritabaniBaglantilari();
	
	public Boolean adminEkle() {
		baglanti.baglan();
		String sorgu = "INSERT INTO admintablo (isim, soy_isim, kullanici_adi, sifre, kurtarma_kodu) VALUES ('"+isim+"', '"+soyIsim+"','"+kullaniciAdi+"', '"+sifre+"', '"+kurtarmaKodu+"')";
		if(baglanti.veriTabaniVeriEkle(sorgu)) {
			return true;
		}else return false;
		
	}
	
	public DefaultTableModel adminTabloDoldurma() {
		DefaultTableModel dmodel = new DefaultTableModel();
		Object[] kolonlar = {"ID","Ýsim","Soy Ýsim","Kullanýcý Adý","Þifre","Kurtarma Kodu"};
		Object[] satirlar = new Object[6];
		String sorgu = "SELECT * FROM admintablo";
		dmodel.setColumnIdentifiers(kolonlar);
		baglanti.baglan();
		ResultSet rs = baglanti.listele(sorgu);
		try {
			while(rs.next()) {
				satirlar[0] = rs.getInt("id");
				satirlar[1] = rs.getString("isim");
				satirlar[2] = rs.getString("soy_isim");
				satirlar[3] = rs.getString("kullanici_adi");
				satirlar[4] = rs.getString("sifre");
				satirlar[5] = rs.getString("kurtarma_kodu");
				dmodel.addRow(satirlar);
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return dmodel;
		
	}
	
	
}
