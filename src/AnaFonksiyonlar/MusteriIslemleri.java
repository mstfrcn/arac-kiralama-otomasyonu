package AnaFonksiyonlar;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;

import YardimciSinif.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.MaskFormatter;

public class MusteriIslemleri {
	
	VeritabaniBaglantilari baglanti = new VeritabaniBaglantilari();
	
	
	String isim;
	String soyIsim;
	String telefonNumarasi;
	String email;
	String adres;
	String tc;
	public MusteriIslemleri(String isim, String soyIsim, String telefonNumarasi, String email, String adres, String tc) {
		this.isim = isim;
		this.soyIsim = soyIsim;
		this.telefonNumarasi = telefonNumarasi;
		this.email = email;
		this.adres = adres;
		this.tc = tc;
	}
	
	public MusteriIslemleri() {}
	
	public Boolean adminEkle() {
		baglanti.baglan();
		String sorgu = "INSERT INTO musteritablo (isim, soy_isim, telefon_no, email, adres, tc_no) VALUES ('"+isim+"', '"+soyIsim+"','"+telefonNumarasi+"', '"+email+"', '"+adres+"', '"+tc+"' )";
		if(baglanti.veriTabaniVeriEkle(sorgu)) {
			return true;
		}else return false;
		
	}
	
	
	public DefaultTableModel adminTabloDoldurma() {
		DefaultTableModel dmodel = new DefaultTableModel();
		Object[] kolonlar = {"ID","Ýsim","Soy Ýsim","T.C No","Telefon No","E-Mail","Adres"};
		Object[] satirlar = new Object[7];
		String sorgu = "SELECT * FROM musteritablo";
		dmodel.setColumnIdentifiers(kolonlar);
		baglanti.baglan();
		ResultSet rs = baglanti.listele(sorgu);
		try {
			while(rs.next()) {
				satirlar[0] = rs.getInt("id");
				satirlar[1] = rs.getString("isim");
				satirlar[2] = rs.getString("soy_isim");
				satirlar[3] = rs.getString("tc_no");
				satirlar[4] = rs.getString("telefon_no");
				satirlar[5] = rs.getString("email");
				satirlar[6] = rs.getString("adres");
				dmodel.addRow(satirlar);
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return dmodel;
	}
	
	public MaskFormatter format(String formatDeseni) {
		MaskFormatter format = null;
		try {
			format = new MaskFormatter(formatDeseni);
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return format;
	}
	
	
	
	
	
}
