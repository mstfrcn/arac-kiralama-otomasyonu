package AnaFonksiyonlar;

import java.sql.ResultSet;
import java.sql.SQLException;
import YardimciSinif.*;
import javax.swing.table.DefaultTableModel;

public class AracKiralamaSatisIslemleri {
	 VeritabaniBaglantilari baglanti = new VeritabaniBaglantilari();
	public DefaultTableModel kiralananAraclarTableDoldur() {
		DefaultTableModel dmodel = new DefaultTableModel();
		Object[] kolonlar = {"ID","Plaka","Marka","Model","T.C No","Ýsim","Soy Ýsim","Kiralama Baþlangýç","Kiralama Bitiþ","Ücret","Ýþlem Tarihi"};
		Object[] satirlar = new Object[11];
		String sorgu =
				 "SELECT * FROM arackiralamatablo INNER JOIN aractablo ON arackiralamatablo.aractablo_id = aractablo.id " +
				 "INNER JOIN markatablo ON aractablo.markatablo_id = markatablo.id "+
				 "INNER JOIN modeltablo ON aractablo.modeltablo_id = modeltablo.id "+
				 "INNER JOIN aracdurumtablo ON arackiralamatablo.aracdurumtablo_id = aracdurumtablo.id "+
				 "INNER JOIN musteritablo ON arackiralamatablo.musteritablo_id = musteritablo.id WHERE aractablo.aracdurumtablo_id = '5' ";
				 
		dmodel.setColumnIdentifiers(kolonlar);
		baglanti.baglan();
		ResultSet rs = baglanti.listele(sorgu);
		try {
			while(rs.next()) {
				satirlar[0] = rs.getInt("id");
				satirlar[1] = rs.getString("plaka");
				satirlar[2] = rs.getString("marka");
				satirlar[3] = rs.getString("model");
				satirlar[4] = rs.getString("tc_no");
				satirlar[5] = rs.getString("isim");
				satirlar[6] = rs.getString("soy_isim");
				satirlar[7] = rs.getString("kiralama_baslangic");
				satirlar[8] = rs.getString("kiralama_bitis");
				satirlar[9] = rs.getString("fiyat");
				satirlar[10] = rs.getString("islem_tarihi");

				dmodel.addRow(satirlar);
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return dmodel;
		
	}
	

	public DefaultTableModel satilanAraclarTableDoldur() {
		DefaultTableModel dmodel = new DefaultTableModel();
		Object[] kolonlar = {"ID","Plaka","Marka","Model","T.C No","Ýsim","Soy Ýsim","Ýþlem Tarihi","Fiyat"};
		Object[] satirlar = new Object[9];
		String sorgu =
				 "SELECT * FROM aracsatistablo INNER JOIN aractablo ON aracsatistablo.aractablo_id = aractablo.id " +
				 "INNER JOIN markatablo ON aractablo.markatablo_id = markatablo.id "+
				 "INNER JOIN modeltablo ON aractablo.modeltablo_id = modeltablo.id "+
				 "INNER JOIN aracdurumtablo ON aracsatistablo.aracdurumtablo_id = aracdurumtablo.id "+
				 "INNER JOIN musteritablo ON aracsatistablo.musteritablo_id = musteritablo.id WHERE aractablo.aracdurumtablo_id = '6'";
				 
		dmodel.setColumnIdentifiers(kolonlar);
		baglanti.baglan();
		ResultSet rs = baglanti.listele(sorgu);
		try {
			while(rs.next()) {
				satirlar[0] = rs.getInt("id");
				satirlar[1] = rs.getString("plaka");
				satirlar[2] = rs.getString("marka");
				satirlar[3] = rs.getString("model");
				satirlar[4] = rs.getString("tc_no");
				satirlar[5] = rs.getString("isim");
				satirlar[6] = rs.getString("soy_isim");
				satirlar[7] = rs.getString("islem_tarihi");
				satirlar[8] = rs.getString("fiyat");

				dmodel.addRow(satirlar);
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return dmodel;
		
	}
	
	
	
	public DefaultTableModel kiralikAracTabloDoldurma() {
		DefaultTableModel dmodel = new DefaultTableModel();
		Object[] kolonlar = {"ID","Plaka","Marka","Model","Renk","Þase Numarasý","Üretim Yýlý","Yakýt Türü","KM","Motor Kapasitesi","Motor Gücü","Þanzýman Tipi","Fiyat","Araç Durum"};
		Object[] satirlar = new Object[14];
		String sorgu =
				 "SELECT * FROM aractablo INNER JOIN markatablo ON aractablo.markatablo_id = markatablo.id " +
				 "INNER JOIN modeltablo ON aractablo.modeltablo_id = modeltablo.id " +
				 "INNER JOIN renktablo ON aractablo.renktablo_id = renktablo.id " +
				 "INNER JOIN yakitturutablo ON aractablo.yakitturutablo_id = yakitturutablo.id " +
				 "INNER JOIN sanzimantipitablo ON aractablo.sanzimantipitablo_id = sanzimantipitablo.id " +
				 "INNER JOIN aracdurumtablo ON aractablo.aracdurumtablo_id = aracdurumtablo.id WHERE aractablo.aracdurumtablo_id = '1'";
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
	
	
	
	
	
	
	
	
	public DefaultTableModel satilikAracTabloDoldurma() {
		DefaultTableModel dmodel = new DefaultTableModel();
		Object[] kolonlar = {"ID","Plaka","Marka","Model","Renk","Þase Numarasý","Üretim Yýlý","Yakýt Türü","KM","Motor Kapasitesi","Motor Gücü","Þanzýman Tipi","Fiyat","Araç Durum"};
		Object[] satirlar = new Object[14];
		String sorgu =
				 "SELECT * FROM aractablo INNER JOIN markatablo ON aractablo.markatablo_id = markatablo.id " +
				 "INNER JOIN modeltablo ON aractablo.modeltablo_id = modeltablo.id " +
				 "INNER JOIN renktablo ON aractablo.renktablo_id = renktablo.id " +
				 "INNER JOIN yakitturutablo ON aractablo.yakitturutablo_id = yakitturutablo.id " +
				 "INNER JOIN sanzimantipitablo ON aractablo.sanzimantipitablo_id = sanzimantipitablo.id " +
				 "INNER JOIN aracdurumtablo ON aractablo.aracdurumtablo_id = aracdurumtablo.id WHERE aractablo.aracdurumtablo_id = 2";
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
	

	
	public Boolean aracKirala(Integer aracId, Integer musteriId, String kiralamaBaslangic, String kiralamaBitis, String fiyat, Integer aracdurum) {
		baglanti.baglan();
		String sorgu1 = "INSERT INTO arackiralamatablo (aractablo_id, musteritablo_id, kiralama_baslangic, kiralama_bitis, fiyat, aracdurumtablo_id) VALUES ("
				+ aracId+","
				+ musteriId+", "
				+ " '"+kiralamaBaslangic+"' ,"
				+ " '"+kiralamaBitis+"' ,"
				+ " '"+fiyat+"' , "
				+ aracdurum+ ")";System.out.println(sorgu1);
		return baglanti.veriTabaniVeriEkle(sorgu1);
	}
	
	
	
	
	
	public Boolean aracSat(Integer aracId, Integer musteriId,String fiyat, Integer aracdurum) {
		baglanti.baglan();
		String sorgu1 = "INSERT INTO aracsatistablo (aractablo_id, musteritablo_id, fiyat, aracdurumtablo_id) VALUES ("
				+ aracId+","
				+ musteriId+", "
				+ " '"+fiyat+"' , "
				+ aracdurum+ ")";System.out.println(sorgu1);
		return baglanti.veriTabaniVeriEkle(sorgu1);
	}
	
	
	
	
	
	
	
}
