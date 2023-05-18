package YardimciSinif;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import GUI.*;

public class VeritabaniBaglantilari {
	static String url = "jdbc:postgresql://localhost:5432/GaleriOtomasyon";
	static Connection Conn = null;

	public static void baglan() {
		try {
			Conn = DriverManager.getConnection(url, "postgres", "1234");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static ResultSet listele(String sorgu) {
		baglan();
		try {
			Statement st = Conn.createStatement();
			ResultSet rs = st.executeQuery(sorgu);
			return rs;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}finally {
			
		}

	}

	public static Boolean veriTabaniVeriEkle(String sorgu) {
		try {
			Statement st = Conn.createStatement();
			int rs = st.executeUpdate(sorgu);
			return true;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}

	}

	public static Boolean veriTabaniVeriSil(String sorgu) {
		Statement st;
		try {
			st = Conn.createStatement();
			int rs = st.executeUpdate(sorgu);
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}

	}

	public static Boolean veriTabaniVeriGuncelle(String sorgu) {
		try {
			Statement st = Conn.createStatement();
			boolean rs = st.execute(sorgu);
			return true;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}

	}

	public static Integer veriTabaniIDGetirme(String tabloAdi, String sutunAdi, String deger) {
		baglan();
		String s = "SELECT * FROM " + tabloAdi + " WHERE " + sutunAdi + " = '" + deger + "'";
		try {
			Statement st = Conn.createStatement();
			ResultSet rs = st.executeQuery(s);
			while (rs.next()) {
				return rs.getInt("id");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		return null;
	}

}
