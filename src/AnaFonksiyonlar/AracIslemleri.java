package AnaFonksiyonlar;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;

import javax.swing.table.DefaultTableModel;
import javax.swing.text.MaskFormatter;

import YardimciSinif.*;

public class AracIslemleri {
	
	String uretimYili;
	String plaka;
	String motorGucu;
	String motorKapasitesi;
	String km;
	String fiyat;
	String saseNumarasi;
	Integer yakitTuru;
	Integer sanzimanTipi;
	Integer marka;
	Integer model;
	Integer renk;
	Integer aracDurum;
	
	VeritabaniBaglantilari baglanti = new VeritabaniBaglantilari();
	
	public AracIslemleri(String uretimYili, String plaka, String motorGucu, String motorKapasitesi, String km,
			String fiyat, Integer yakitTuru, Integer sanzimanTipi, Integer marka, Integer model, Integer renk,
			Integer aracDurum, String saseNumarasi) {
		this.uretimYili = uretimYili;
		this.plaka = plaka;
		this.motorGucu = motorGucu;
		this.motorKapasitesi = motorKapasitesi;
		this.km = km;
		this.fiyat = fiyat;
		this.yakitTuru = yakitTuru;
		this.sanzimanTipi = sanzimanTipi;
		this.marka = marka;
		this.model = model;
		this.renk = renk;
		this.aracDurum = aracDurum;
		this.saseNumarasi = saseNumarasi;
	}
	public AracIslemleri() {}


	public Boolean aracEkle() {
		baglanti.baglan();
		String sorgu = "INSERT INTO aractablo (markatablo_id, modeltablo_id, renktablo_id, uretim_yili, plaka, yakitturutablo_id, motor_gucu, motor_kapasitesi, km, sanzimantipitablo_id, fiyat, aracdurumtablo_id, sase_numarasi) VALUES ("
				+ marka +","+ model+","+renk+","+"'"+uretimYili+"','"+plaka+"',"+yakitTuru+",'"+motorGucu+"','"+motorKapasitesi+"','"+
				km+"',"+sanzimanTipi+",'"+fiyat+"',"+aracDurum+",'"+saseNumarasi+"')";
		if(baglanti.veriTabaniVeriEkle(sorgu)) {
			return true;
		}
		else return false;
		
	}
	
	public Boolean aracGuncelle(String kolon, String veri) {
		baglanti.baglan();
		String sorgu = "UPDATE aractablo SET markatablo_id = "+marka+
				" , modeltablo_id = "+model+
				" , renktablo_id = "+renk+
				" , uretim_yili = '"+uretimYili+"' "+
				" , plaka = '"+plaka+"' "+
				" , yakitturutablo_id = "+yakitTuru+
				" , motor_gucu = '"+motorGucu+"' "+
				" , motor_kapasitesi = '"+motorKapasitesi+"' "+
				" , km = '"+km+"' "+ 
				" , sanzimantipitablo_id = "+sanzimanTipi+
				" , fiyat = '"+fiyat+"' "+
				" , aracdurumtablo_id = "+aracDurum+
				" , sase_numarasi = '"+saseNumarasi+"' "+
				"WHERE "+kolon+" = '"+veri+"'";
		if(baglanti.veriTabaniVeriEkle(sorgu)) {
			return true;
		}
		else return false;
		
	}
	
	
	
	
	
	public DefaultTableModel aracTabloDoldurma() {
		DefaultTableModel dmodel = new DefaultTableModel();
		Object[] kolonlar = {"ID","Plaka","Marka","Model","Renk","Þase Numarasý","Üretim Yýlý","Yakýt Türü","KM","Motor Kapasitesi","Motor Gücü","Þanzýman Tipi","Fiyat","Araç Durum"};
		Object[] satirlar = new Object[14];
		String sorgu =
				 "SELECT * FROM aractablo INNER JOIN markatablo ON aractablo.markatablo_id = markatablo.id " +
				 "INNER JOIN modeltablo ON aractablo.modeltablo_id = modeltablo.id " +
				 "INNER JOIN renktablo ON aractablo.renktablo_id = renktablo.id " +
				 "INNER JOIN yakitturutablo ON aractablo.yakitturutablo_id = yakitturutablo.id " +
				 "INNER JOIN sanzimantipitablo ON aractablo.sanzimantipitablo_id = sanzimantipitablo.id " +
				 "INNER JOIN aracdurumtablo ON aractablo.aracdurumtablo_id = aracdurumtablo.id ";
		dmodel.setColumnIdentifiers(kolonlar);
		baglanti.baglan();
		ResultSet rs = baglanti.listele(sorgu);
		try {
			while(rs.next()) {
				satirlar[0] = rs.getInt("id");
				satirlar[1] = rs.getString("plaka");
				satirlar[2] = rs.getString("marka");
				satirlar[3] = rs.getString("model");
				satirlar[4] = rs.getString("renk");
				satirlar[5] = rs.getString("sase_numarasi");
				satirlar[6] = rs.getString("uretim_yili");
				satirlar[7] = rs.getString("yakit_turu");
				satirlar[8] = rs.getString("km");
				satirlar[9] = rs.getString("motor_kapasitesi");
				satirlar[10] = rs.getString("motor_gucu");
				satirlar[11] = rs.getString("sanziman_tipi");
				satirlar[12] = rs.getString("fiyat");
				satirlar[13] = rs.getString("arac_durumu");

				dmodel.addRow(satirlar);
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return dmodel;
	}
	
	public DefaultTableModel aracFiltreTabloDoldurma(String aramaFiltreSorgusu) {
		DefaultTableModel dmodel = new DefaultTableModel();
		Object[] kolonlar = {"ID","Plaka","Marka","Model","Renk","Þase Numarasý","Üretim Yýlý","Yakýt Türü","KM","Motor Kapasitesi","Motor Gücü","Þanzýman Tipi","Fiyat","Araç Durum"};
		Object[] satirlar = new Object[14];
		String sorgu =
				 "SELECT * FROM aractablo INNER JOIN markatablo ON aractablo.markatablo_id = markatablo.id " +
				 "INNER JOIN modeltablo ON aractablo.modeltablo_id = modeltablo.id " +
				 "INNER JOIN renktablo ON aractablo.renktablo_id = renktablo.id " +
				 "INNER JOIN yakitturutablo ON aractablo.yakitturutablo_id = yakitturutablo.id " +
				 "INNER JOIN sanzimantipitablo ON aractablo.sanzimantipitablo_id = sanzimantipitablo.id " +
				 "INNER JOIN aracdurumtablo ON aractablo.aracdurumtablo_id = aracdurumtablo.id "+aramaFiltreSorgusu;
		dmodel.setColumnIdentifiers(kolonlar);
		baglanti.baglan();
		ResultSet rs = baglanti.listele(sorgu);
		try {
			while(rs.next()) {
				satirlar[0] = rs.getInt("id");
				satirlar[1] = rs.getString("plaka");
				satirlar[2] = rs.getString("marka");
				satirlar[3] = rs.getString("model");
				satirlar[4] = rs.getString("renk");
				satirlar[5] = rs.getString("sase_numarasi");
				satirlar[6] = rs.getString("uretim_yili");
				satirlar[7] = rs.getString("yakit_turu");
				satirlar[8] = rs.getString("km");
				satirlar[9] = rs.getString("motor_kapasitesi");
				satirlar[10] = rs.getString("motor_gucu");
				satirlar[11] = rs.getString("sanziman_tipi");
				satirlar[12] = rs.getString("fiyat");
				satirlar[13] = rs.getString("arac_durumu");

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
