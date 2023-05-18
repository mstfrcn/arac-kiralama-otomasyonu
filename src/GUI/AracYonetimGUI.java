package GUI;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import AnaFonksiyonlar.SilmeIslemleri;
import AnaFonksiyonlar.AracIslemleri;
import AnaFonksiyonlar.AracKiralamaSatisIslemleri;
import YardimciSinif.ComboBoxVerileriCekme;

import YardimciSinif.Mesajlar;
import YardimciSinif.VeritabaniBaglantilari;

import javax.swing.JTabbedPane;
import java.awt.Font;
import javax.swing.JScrollPane;
import java.awt.Color;
import java.awt.SystemColor;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ItemListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ItemEvent;
import javax.swing.JTable;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.ScrollPaneConstants;

public class AracYonetimGUI extends JFrame {

	private JPanel contentPane;
	JTextField txtKiralamaUcreti;
	JFormattedTextField txtUretimYili;
	JTextField txtPlaka;
	JTextField txtFiyat;
	JTextField txtMotorKapasitesi;
	JTextField txtMotorGucu;
	JTextField txtKm;
	JTextField txtSaseNumarasi;

	// TABLOLAR
	DefaultTableModel aracListesiTablo = new DefaultTableModel();
	DefaultTableModel kiralananAraclarTablo = new DefaultTableModel();
	DefaultTableModel kiralikAraclarTablo = new DefaultTableModel();
	DefaultTableModel SatilikAraclarTablo = new DefaultTableModel();
	DefaultTableModel SatilanAraclarTablo = new DefaultTableModel();

	// ARAC KIRALAMA DEGISKENLERI
	String aracKiralamaKolonAdi;
	String aracKiralamaKolonAdi_1;

	// ARAC SATIS DEGISKENLERI
	String aracSatisKolonAdi;
	String aracSatisKolonAdi_1;

	// ComboBoxlar
	JComboBox comboBoxAracDurum;
	JComboBox comboBoxMarka;
	JComboBox comboBoxModel;
	JComboBox comboBoxRenk;
	JComboBox comboBoxYakitTuru;
	JComboBox comboBoxSanzimanTipi;
	static JComboBox comboBox;
	JComboBox comboBoxAracSec;
	JComboBox comboBoxKiralanacakAracFiltre;
	JComboBox comboBoxKiralanacakAracFiltre_1;
	JComboBox comboBoxKýralýkAracSecim;
	JComboBox comboBoxKýralýkAracSecim_1;
	JComboBox comboBoxFiltreSec;
	JComboBox comboBoxAracSilmeSecim;
	JComboBox comboBoxAracSilmeFiltre;
	JComboBox comboBoxSatilikAracFiltre;
	JComboBox comboBoxSatilikAracSecim;
	JComboBox comboBoxSatilikAracFiltre_1;
	JComboBox comboBoxSatilikAracSecim_1;

	JFormattedTextField ftKiralamaBaslangicTarihi;
	JFormattedTextField ftAracKiralamaBitisTarihi;

	// Yeni OzellikEkleme GUI COmboBox
	static JComboBox comboBoxModelIcinmarkaVerisi;
	
	// ARAC GUNCELLEME
	String kolonAdi;
	String aracVerisiSec;

	String aracSilmeFiltresi;
	Object silinecekAracBilgisi;

	static String tablo;
	static String colon;
	String labelBasligi;

	// Nesneler
	ComboBoxVerileriCekme al = new ComboBoxVerileriCekme();
	Mesajlar msj = new Mesajlar();
	AracIslemleri aracIslemleri = new AracIslemleri();
	YeniOzellikEklemeGUI yeniOzellikEklemeGUI = new YeniOzellikEklemeGUI();
	VeritabaniBaglantilari baglanti = new VeritabaniBaglantilari();
	AracKiralamaSatisIslemleri aracKiralamaSatisislemleri = new AracKiralamaSatisIslemleri();

	// TABLOLAR
	JTable tableAracListele;
	JTable tableKiralananAraclar;
	JTable tableKiralikAraclar;
	JTextField txtSatýsFýyat;
	JTable tableSatilikArac;
	JTable tableSatilanArac;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AracYonetimGUI frame = new AracYonetimGUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public AracYonetimGUI() {
		setTitle("Arac Yonetim Islemleri");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(500, 120, 1313, 871);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.activeCaptionBorder);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JTabbedPane tabbedAracYonetim = new JTabbedPane(JTabbedPane.TOP);
		tabbedAracYonetim.setFont(new Font("Tahoma", Font.PLAIN, 17));
		tabbedAracYonetim.setBounds(24, 42, 1251, 762);
		contentPane.add(tabbedAracYonetim);

		JPanel panelAracSatisIslemleri = new JPanel();
		tabbedAracYonetim.addTab("      Arac Satis Islemleri     ", null, panelAracSatisIslemleri, null);
		tabbedAracYonetim.setBackgroundAt(0, new Color(70, 130, 180));
		panelAracSatisIslemleri.setBackground(new Color(70, 130, 180));
		panelAracSatisIslemleri.setLayout(null);

		JButton btnSatýsIslemý = new JButton("Sat\u0131\u015F\u0131 Ger\u00E7ekle\u015Ftir");
		btnSatýsIslemý.setFont(new Font("Yu Gothic UI", Font.PLAIN, 16));
		btnSatýsIslemý.setBounds(21, 472, 195, 33);
		panelAracSatisIslemleri.add(btnSatýsIslemý);

		txtSatýsFýyat = new JTextField();
		txtSatýsFýyat.setFont(new Font("Yu Gothic UI", Font.PLAIN, 17));
		txtSatýsFýyat.setColumns(10);
		txtSatýsFýyat.setBounds(21, 434, 195, 27);
		panelAracSatisIslemleri.add(txtSatýsFýyat);

		JLabel lblFiyat_1 = new JLabel("Fiyat:");
		lblFiyat_1.setFont(new Font("Yu Gothic UI", Font.PLAIN, 17));
		lblFiyat_1.setBackground(Color.WHITE);
		lblFiyat_1.setBounds(21, 396, 195, 27);
		panelAracSatisIslemleri.add(lblFiyat_1);

		comboBoxSatilikAracSecim_1 = new JComboBox();
		comboBoxSatilikAracSecim_1.setFont(new Font("Yu Gothic UI", Font.PLAIN, 17));
		comboBoxSatilikAracSecim_1.setBounds(21, 334, 195, 27);
		panelAracSatisIslemleri.add(comboBoxSatilikAracSecim_1);

		comboBoxSatilikAracFiltre_1 = new JComboBox();
		comboBoxSatilikAracFiltre_1
				.setModel(new DefaultComboBoxModel(new String[] { "L\u00FCtfen Se\u00E7im Yap\u0131n\u0131z",
						"T.C Numaras\u0131na G\u00F6re", "\u0130sime G\u00F6re", "Soy \u0130sime G\u00F6re",
						"Telefon Numaras\u0131na G\u00F6re", "Adresine G\u00F6re", "E-Mail Adresine G\u00F6re" }));
		comboBoxSatilikAracFiltre_1.setFont(new Font("Yu Gothic UI", Font.PLAIN, 18));
		comboBoxSatilikAracFiltre_1.setBounds(21, 296, 195, 27);
		panelAracSatisIslemleri.add(comboBoxSatilikAracFiltre_1);

		JLabel lblKullaniciId_1_3 = new JLabel("M\u00FC\u015Fteri Verisi:");
		lblKullaniciId_1_3.setFont(new Font("Yu Gothic UI", Font.PLAIN, 17));
		lblKullaniciId_1_3.setBackground(Color.WHITE);
		lblKullaniciId_1_3.setBounds(21, 258, 195, 27);
		panelAracSatisIslemleri.add(lblKullaniciId_1_3);

		comboBoxSatilikAracSecim = new JComboBox();
		comboBoxSatilikAracSecim.setFont(new Font("Yu Gothic UI", Font.PLAIN, 17));
		comboBoxSatilikAracSecim.setBounds(21, 204, 195, 27);
		panelAracSatisIslemleri.add(comboBoxSatilikAracSecim);

		comboBoxSatilikAracFiltre = new JComboBox();
		comboBoxSatilikAracFiltre.setModel(new DefaultComboBoxModel(
				new String[] { "L\u00FCtfen Se\u00E7im Yap\u0131n\u0131z", "ID Numaras\u0131na G\u00F6re",
						"Plaka Verisine G\u00F6re", "\u015Ease Numaras\u0131na G\u00F6re" }));
		comboBoxSatilikAracFiltre.setFont(new Font("Yu Gothic UI", Font.PLAIN, 18));
		comboBoxSatilikAracFiltre.setBounds(21, 166, 195, 27);
		panelAracSatisIslemleri.add(comboBoxSatilikAracFiltre);

		JLabel lblKullaniciId_1_2_1 = new JLabel("Ara\u00E7 Verisi:");
		lblKullaniciId_1_2_1.setFont(new Font("Yu Gothic UI", Font.PLAIN, 17));
		lblKullaniciId_1_2_1.setBackground(Color.WHITE);
		lblKullaniciId_1_2_1.setBounds(21, 128, 195, 27);
		panelAracSatisIslemleri.add(lblKullaniciId_1_2_1);

		JLabel lblNewLabel_2_2 = new JLabel("SATILAN ARA\u00C7LAR");
		lblNewLabel_2_2.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_2_2.setBounds(236, 24, 152, 23);
		panelAracSatisIslemleri.add(lblNewLabel_2_2);

		JScrollPane scrollPane_1_1 = new JScrollPane();
		scrollPane_1_1.setBounds(236, 47, 1000, 313);
		panelAracSatisIslemleri.add(scrollPane_1_1);
		
		JLabel lblNewLabel_2_1_1 = new JLabel("SATILIK ARA\u00C7LAR");
		lblNewLabel_2_1_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_2_1_1.setBounds(236, 371, 133, 23);
		panelAracSatisIslemleri.add(lblNewLabel_2_1_1);

		JScrollPane scrollPane_2_1 = new JScrollPane();
		scrollPane_2_1.setBounds(236, 395, 1000, 290);
		panelAracSatisIslemleri.add(scrollPane_2_1);
		
		
		// SATILAN ARAC TABLO DOLDUR
		tableSatilanArac = new JTable();
		scrollPane_1_1.setViewportView(tableSatilanArac);
		tableSatilanArac.setFont(new Font("Yu Gothic UI", Font.PLAIN, 16));
		
		SatilanAraclarTablo = aracKiralamaSatisislemleri.satilanAraclarTableDoldur();
		tableSatilanArac.setModel(SatilanAraclarTablo);

		
		// SATILIK ARACLAR TABL DOLDUR
		tableSatilikArac = new JTable();
		tableSatilikArac.setFont(new Font("Yu Gothic UI", Font.PLAIN, 16));
		scrollPane_2_1.setViewportView(tableSatilikArac);

		SatilikAraclarTablo = aracKiralamaSatisislemleri.satilikAracTabloDoldurma();
		tableSatilikArac.setModel(SatilikAraclarTablo);
		
		JButton btnNewButton = new JButton("Arac\u0131 Geri Al");
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnNewButton.setBounds(1130, 11, 106, 23);
		panelAracSatisIslemleri.add(btnNewButton);
		

		JPanel panelAracKiralama = new JPanel();
		tabbedAracYonetim.addTab("     Arac Kiralama Islemleri     ", null, panelAracKiralama, null);
		tabbedAracYonetim.setBackgroundAt(1, new Color(46, 139, 87));
		panelAracKiralama.setBackground(new Color(46, 139, 87));
		panelAracKiralama.setLayout(null);

		JLabel lblKullaniciId_1 = new JLabel("M\u00FC\u015Fteri Verisi:");
		lblKullaniciId_1.setFont(new Font("Yu Gothic UI", Font.PLAIN, 17));
		lblKullaniciId_1.setBackground(Color.WHITE);
		lblKullaniciId_1.setBounds(10, 198, 195, 27);
		panelAracKiralama.add(lblKullaniciId_1);

		JLabel lbl = new JLabel("Kiralama Baslangic Tarihi:");
		lbl.setFont(new Font("Yu Gothic UI", Font.PLAIN, 17));
		lbl.setBackground(Color.WHITE);
		lbl.setBounds(10, 330, 195, 27);
		panelAracKiralama.add(lbl);

		JButton btnKiralamaIslemi = new JButton("Kiralamayi Gerceklestir");
		btnKiralamaIslemi.setFont(new Font("Yu Gothic UI", Font.PLAIN, 16));
		btnKiralamaIslemi.setBounds(10, 606, 195, 33);
		panelAracKiralama.add(btnKiralamaIslemi);

		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(215, 43, 1021, 299);
		panelAracKiralama.add(scrollPane_1);

		// KIRALANAN ARACLAR TABLO DOLDUR
		tableKiralananAraclar = new JTable();
		tableKiralananAraclar.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		scrollPane_1.setViewportView(tableKiralananAraclar);

		kiralananAraclarTablo = aracKiralamaSatisislemleri.kiralananAraclarTableDoldur();
		tableKiralananAraclar.setModel(kiralananAraclarTablo);

		JLabel lblKiralamaBitisTarihi = new JLabel("Kiralama Bitis Tarihi:");
		lblKiralamaBitisTarihi.setFont(new Font("Yu Gothic UI", Font.PLAIN, 17));
		lblKiralamaBitisTarihi.setBackground(Color.WHITE);
		lblKiralamaBitisTarihi.setBounds(10, 426, 195, 27);
		panelAracKiralama.add(lblKiralamaBitisTarihi);

		JLabel lblKiralamaUcret = new JLabel("Kiralama Ucreti");
		lblKiralamaUcret.setFont(new Font("Yu Gothic UI", Font.PLAIN, 17));
		lblKiralamaUcret.setBackground(Color.WHITE);
		lblKiralamaUcret.setBounds(10, 530, 195, 27);
		panelAracKiralama.add(lblKiralamaUcret);

		txtKiralamaUcreti = new JTextField();
		txtKiralamaUcreti.setFont(new Font("Yu Gothic UI", Font.PLAIN, 17));
		txtKiralamaUcreti.setColumns(10);
		txtKiralamaUcreti.setBounds(10, 568, 195, 27);
		panelAracKiralama.add(txtKiralamaUcreti);

		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane_2.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollPane_2.setBounds(215, 377, 1021, 304);
		panelAracKiralama.add(scrollPane_2);
		
		// KIRALIK ARACLAR TABLO DOLDURMA
		tableKiralikAraclar = new JTable();
		tableKiralikAraclar.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		scrollPane_2.setViewportView(tableKiralikAraclar);

		kiralikAraclarTablo = aracKiralamaSatisislemleri.kiralikAracTabloDoldurma();
		tableKiralikAraclar.setModel(kiralikAraclarTablo);;

		JLabel lblNewLabel_2 = new JLabel("K\u0130RALANAN ARA\u00C7LAR");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_2.setBounds(263, 19, 152, 23);
		panelAracKiralama.add(lblNewLabel_2);

		JLabel lblNewLabel_2_1 = new JLabel("K\u0130RALIK ARA\u00C7LAR");
		lblNewLabel_2_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_2_1.setBounds(263, 353, 133, 23);
		panelAracKiralama.add(lblNewLabel_2_1);

		comboBoxKiralanacakAracFiltre = new JComboBox();
		comboBoxKiralanacakAracFiltre.setModel(new DefaultComboBoxModel(
				new String[] { "L\u00FCtfen Se\u00E7im Yap\u0131n", "ID Numaras\u0131na G\u00F6re",
						"Plaka Verisine G\u00F6re", "\u015Ease Numaras\u0131na G\u00F6re" }));
		comboBoxKiralanacakAracFiltre.setFont(new Font("Yu Gothic UI", Font.PLAIN, 18));
		comboBoxKiralanacakAracFiltre.setBounds(10, 106, 195, 27);
		panelAracKiralama.add(comboBoxKiralanacakAracFiltre);

		comboBoxKýralýkAracSecim = new JComboBox();
		comboBoxKýralýkAracSecim.setMaximumRowCount(5);
		comboBoxKýralýkAracSecim.setFont(new Font("Yu Gothic UI", Font.PLAIN, 17));
		comboBoxKýralýkAracSecim.setBounds(10, 144, 195, 27);
		panelAracKiralama.add(comboBoxKýralýkAracSecim);

		JButton btnAracTeslimAl = new JButton("Kiralamay\u0131 Sonland\u0131r");
		btnAracTeslimAl.setFont(new Font("Yu Gothic UI", Font.PLAIN, 15));
		btnAracTeslimAl.setBounds(1059, 6, 177, 30);
		panelAracKiralama.add(btnAracTeslimAl);

		JLabel lblKullaniciId_1_2 = new JLabel("Ara\u00E7 Verisi:");
		lblKullaniciId_1_2.setFont(new Font("Yu Gothic UI", Font.PLAIN, 17));
		lblKullaniciId_1_2.setBackground(Color.WHITE);
		lblKullaniciId_1_2.setBounds(10, 68, 195, 27);
		panelAracKiralama.add(lblKullaniciId_1_2);

		comboBoxKiralanacakAracFiltre_1 = new JComboBox();
		comboBoxKiralanacakAracFiltre_1.setMaximumRowCount(5);
		comboBoxKiralanacakAracFiltre_1.setModel(new DefaultComboBoxModel(new String[] {"L\u00FCtfen Se\u00E7im Yap\u0131n", "ID Numaras\u0131na G\u00F6re", "T.C Numaras\u0131na G\u00F6re", "\u0130sime G\u00F6re", "Soy \u0130sime G\u00F6re", "Telefon Numaras\u0131na G\u00F6re", "Adresine G\u00F6re", "E-Mail Adresine G\u00F6re"}));
		comboBoxKiralanacakAracFiltre_1.setFont(new Font("Yu Gothic UI", Font.PLAIN, 18));
		comboBoxKiralanacakAracFiltre_1.setBounds(10, 236, 195, 27);
		panelAracKiralama.add(comboBoxKiralanacakAracFiltre_1);

		comboBoxKýralýkAracSecim_1 = new JComboBox();
		comboBoxKýralýkAracSecim_1.setMaximumRowCount(5);
		comboBoxKýralýkAracSecim_1.setFont(new Font("Yu Gothic UI", Font.PLAIN, 17));
		comboBoxKýralýkAracSecim_1.setBounds(10, 274, 195, 27);
		panelAracKiralama.add(comboBoxKýralýkAracSecim_1);
		// KIRALAMA BASLANGIC TARIHI FORMAT

		ftKiralamaBaslangicTarihi = new JFormattedTextField(aracIslemleri.format("####-##-##"));
		ftKiralamaBaslangicTarihi.setFont(new Font("Yu Gothic UI", Font.PLAIN, 17));
		ftKiralamaBaslangicTarihi.setBounds(10, 377, 195, 27);
		panelAracKiralama.add(ftKiralamaBaslangicTarihi);

		JLabel lblNewLabel_1 = new JLabel("(YYYY-AA-GG)");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_1.setBounds(10, 362, 82, 14);
		panelAracKiralama.add(lblNewLabel_1);

		JLabel lblNewLabel_1_2 = new JLabel("(YYYY-AA-GG)");
		lblNewLabel_1_2.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_1_2.setBounds(10, 461, 82, 14);
		panelAracKiralama.add(lblNewLabel_1_2);

		ftAracKiralamaBitisTarihi = new JFormattedTextField(aracIslemleri.format("####-##-##"));
		ftAracKiralamaBitisTarihi.setFont(new Font("Tahoma", Font.PLAIN, 17));
		ftAracKiralamaBitisTarihi.setBounds(10, 474, 195, 27);
		panelAracKiralama.add(ftAracKiralamaBitisTarihi);

		JPanel panelYeniAracEkle = new JPanel();
		tabbedAracYonetim.addTab("     Yeni Arac Ekle Ve Sil     ", null, panelYeniAracEkle, null);
		tabbedAracYonetim.setBackgroundAt(2, new Color(0, 139, 139));
		panelYeniAracEkle.setBackground(new Color(95, 158, 160));
		panelYeniAracEkle.setLayout(null);

		JLabel lblNewLabel_1_1 = new JLabel("Marka:");
		lblNewLabel_1_1.setFont(new Font("Yu Gothic UI", Font.PLAIN, 17));
		lblNewLabel_1_1.setBackground(Color.WHITE);
		lblNewLabel_1_1.setBounds(49, 11, 195, 27);
		panelYeniAracEkle.add(lblNewLabel_1_1);

		JLabel lblKullaniciId_1_1 = new JLabel("Model:");
		lblKullaniciId_1_1.setFont(new Font("Yu Gothic UI", Font.PLAIN, 17));
		lblKullaniciId_1_1.setBackground(Color.WHITE);
		lblKullaniciId_1_1.setBounds(315, 11, 195, 27);
		panelYeniAracEkle.add(lblKullaniciId_1_1);

		// FORMAT ARAAC YILI
		txtUretimYili = new JFormattedTextField(aracIslemleri.format("####"));
		txtUretimYili.setFont(new Font("Yu Gothic UI", Font.PLAIN, 17));
		txtUretimYili.setColumns(10);
		txtUretimYili.setBounds(980, 48, 195, 27);
		panelYeniAracEkle.add(txtUretimYili);

		JLabel lblYil = new JLabel("\u00DCretim Y\u0131l\u0131:");
		lblYil.setFont(new Font("Yu Gothic UI", Font.PLAIN, 17));
		lblYil.setBackground(Color.WHITE);
		lblYil.setBounds(980, 19, 195, 27);
		panelYeniAracEkle.add(lblYil);

		JButton btnAracSil = new JButton("Se\u00E7ili Ara\u00E7 Sil");
		btnAracSil.setFont(new Font("Yu Gothic UI", Font.PLAIN, 18));
		btnAracSil.setBounds(1088, 686, 148, 30);
		panelYeniAracEkle.add(btnAracSil);

		JLabel lblPlaka = new JLabel("Plaka");
		lblPlaka.setFont(new Font("Yu Gothic UI", Font.PLAIN, 17));
		lblPlaka.setBackground(Color.WHITE);
		lblPlaka.setBounds(545, 11, 195, 27);
		panelYeniAracEkle.add(lblPlaka);

		txtPlaka = new JTextField();
		txtPlaka.setFont(new Font("Yu Gothic UI", Font.PLAIN, 17));
		txtPlaka.setColumns(10);
		txtPlaka.setBounds(545, 40, 195, 27);
		panelYeniAracEkle.add(txtPlaka);

		JLabel lblAracDurum = new JLabel("Ara\u00E7 Durumu:");
		lblAracDurum.setFont(new Font("Yu Gothic UI", Font.PLAIN, 17));
		lblAracDurum.setBackground(Color.WHITE);
		lblAracDurum.setBounds(317, 153, 195, 27);
		panelYeniAracEkle.add(lblAracDurum);

		JLabel lblRenk = new JLabel("Renk:");
		lblRenk.setFont(new Font("Yu Gothic UI", Font.PLAIN, 17));
		lblRenk.setBackground(Color.WHITE);
		lblRenk.setBounds(49, 82, 195, 27);
		panelYeniAracEkle.add(lblRenk);

		JLabel lblFiyat = new JLabel("Fiyat:");
		lblFiyat.setFont(new Font("Yu Gothic UI", Font.PLAIN, 17));
		lblFiyat.setBackground(Color.WHITE);
		lblFiyat.setBounds(545, 153, 195, 27);
		panelYeniAracEkle.add(lblFiyat);

		txtFiyat = new JTextField();
		txtFiyat.setFont(new Font("Yu Gothic UI", Font.PLAIN, 17));
		txtFiyat.setColumns(10);
		txtFiyat.setBounds(545, 182, 195, 27);
		panelYeniAracEkle.add(txtFiyat);

		JButton btnAracEkle = new JButton("Yeni Ara\u00E7 Ekle");
		btnAracEkle.setFont(new Font("Yu Gothic UI", Font.PLAIN, 16));
		btnAracEkle.setBounds(775, 176, 195, 33);
		panelYeniAracEkle.add(btnAracEkle);

		JLabel lblYakitTuru = new JLabel("Yak\u0131t T\u00FCr\u00FC:");
		lblYakitTuru.setFont(new Font("Yu Gothic UI", Font.PLAIN, 17));
		lblYakitTuru.setBackground(Color.WHITE);
		lblYakitTuru.setBounds(49, 153, 195, 27);
		panelYeniAracEkle.add(lblYakitTuru);

		JLabel lblSanzimanTipi = new JLabel("\u015Eanz\u0131man Tipi:");
		lblSanzimanTipi.setFont(new Font("Yu Gothic UI", Font.PLAIN, 17));
		lblSanzimanTipi.setBackground(Color.WHITE);
		lblSanzimanTipi.setBounds(315, 82, 195, 27);
		panelYeniAracEkle.add(lblSanzimanTipi);

		txtMotorKapasitesi = new JTextField();
		txtMotorKapasitesi.setFont(new Font("Yu Gothic UI", Font.PLAIN, 17));
		txtMotorKapasitesi.setColumns(10);
		txtMotorKapasitesi.setBounds(760, 115, 195, 27);
		panelYeniAracEkle.add(txtMotorKapasitesi);

		JLabel lblMotorKapasitesi = new JLabel("Motor Kapasitesi:");
		lblMotorKapasitesi.setFont(new Font("Yu Gothic UI", Font.PLAIN, 17));
		lblMotorKapasitesi.setBackground(Color.WHITE);
		lblMotorKapasitesi.setBounds(760, 82, 195, 27);
		panelYeniAracEkle.add(lblMotorKapasitesi);

		txtMotorGucu = new JTextField();
		txtMotorGucu.setFont(new Font("Yu Gothic UI", Font.PLAIN, 17));
		txtMotorGucu.setColumns(10);
		txtMotorGucu.setBounds(545, 115, 195, 27);
		panelYeniAracEkle.add(txtMotorGucu);

		JLabel lblMotorGucu = new JLabel("Motor G\u00FCc\u00FC:");
		lblMotorGucu.setFont(new Font("Yu Gothic UI", Font.PLAIN, 17));
		lblMotorGucu.setBackground(Color.WHITE);
		lblMotorGucu.setBounds(545, 86, 195, 27);
		panelYeniAracEkle.add(lblMotorGucu);

		txtKm = new JTextField();
		txtKm.setFont(new Font("Yu Gothic UI", Font.PLAIN, 17));
		txtKm.setColumns(10);
		txtKm.setBounds(980, 115, 195, 27);
		panelYeniAracEkle.add(txtKm);

		JLabel lblaseNumaras = new JLabel("\u015Ease Numaras\u0131:");
		lblaseNumaras.setFont(new Font("Yu Gothic UI", Font.PLAIN, 17));
		lblaseNumaras.setBackground(Color.WHITE);
		lblaseNumaras.setBounds(760, 19, 195, 27);
		panelYeniAracEkle.add(lblaseNumaras);

		txtSaseNumarasi = new JTextField();
		txtSaseNumarasi.setFont(new Font("Yu Gothic UI", Font.PLAIN, 17));
		txtSaseNumarasi.setColumns(10);
		txtSaseNumarasi.setBounds(760, 48, 195, 27);
		panelYeniAracEkle.add(txtSaseNumarasi);

		JLabel lblKm = new JLabel("Km:");
		lblKm.setFont(new Font("Yu Gothic UI", Font.PLAIN, 17));
		lblKm.setBackground(Color.WHITE);
		lblKm.setBounds(980, 86, 195, 27);
		panelYeniAracEkle.add(lblKm);

		JButton btnMarkaEkle = new JButton("...");
		btnMarkaEkle.setBounds(10, 44, 27, 27);
		panelYeniAracEkle.add(btnMarkaEkle);

		JButton btnRenkEkle = new JButton("...");
		btnRenkEkle.setBounds(10, 115, 27, 27);
		panelYeniAracEkle.add(btnRenkEkle);

		JButton btnYakitTuruEkle = new JButton("...");
		btnYakitTuruEkle.setBounds(10, 182, 27, 27);
		panelYeniAracEkle.add(btnYakitTuruEkle);

		JButton btnAracDurumuEkle = new JButton("...");
		btnAracDurumuEkle.setBounds(278, 182, 27, 27);
		panelYeniAracEkle.add(btnAracDurumuEkle);

		JButton btnModelEkle = new JButton("...");
		btnModelEkle.setBounds(278, 44, 27, 27);
		panelYeniAracEkle.add(btnModelEkle);

		JButton btnSansizmanTipiEkle = new JButton("...");
		btnSansizmanTipiEkle.setBounds(278, 118, 27, 27);
		panelYeniAracEkle.add(btnSansizmanTipiEkle);

		comboBoxAracDurum = new JComboBox();
		comboBoxAracDurum.setMaximumRowCount(4);
		comboBoxAracDurum
				.setModel(new DefaultComboBoxModel(new String[] { "L\u00FCtfen Ara\u00E7 Durumu Se\u00E7iniz" }));
		comboBoxAracDurum.setFont(new Font("Yu Gothic UI", Font.PLAIN, 16));
		comboBoxAracDurum.setBounds(317, 182, 195, 27);
		panelYeniAracEkle.add(comboBoxAracDurum);

		comboBoxMarka = new JComboBox();
		comboBoxMarka.setModel(new DefaultComboBoxModel(new String[] { "L\u00FCtfen Marka Se\u00E7iniz" }));
		comboBoxMarka.setFont(new Font("Yu Gothic UI", Font.PLAIN, 16));
		comboBoxMarka.setBounds(49, 44, 195, 27);
		comboBoxMarka.setMaximumRowCount(4);
		panelYeniAracEkle.add(comboBoxMarka);

		comboBoxModel = new JComboBox();
		comboBoxModel.setModel(new DefaultComboBoxModel(new String[] { "L\u00FCtfen Model Se\u00E7iniz" }));
		comboBoxModel.setFont(new Font("Yu Gothic UI", Font.PLAIN, 16));
		comboBoxModel.setBounds(315, 44, 195, 27);
		comboBoxModel.setMaximumRowCount(4);
		panelYeniAracEkle.add(comboBoxModel);

		comboBoxRenk = new JComboBox();
		comboBoxRenk.setModel(new DefaultComboBoxModel(new String[] { "L\u00FCtfen Renk Se\u00E7iniz" }));
		comboBoxRenk.setFont(new Font("Yu Gothic UI", Font.PLAIN, 16));
		comboBoxRenk.setBounds(49, 115, 195, 27);
		comboBoxRenk.setMaximumRowCount(4);
		panelYeniAracEkle.add(comboBoxRenk);

		comboBoxYakitTuru = new JComboBox();
		comboBoxYakitTuru.setModel(
				new DefaultComboBoxModel(new String[] { "L\u00FCtfen Yak\u0131t T\u00FCr\u00FC Se\u00E7iniz" }));
		comboBoxYakitTuru.setFont(new Font("Yu Gothic UI", Font.PLAIN, 16));
		comboBoxYakitTuru.setBounds(49, 182, 195, 27);
		comboBoxYakitTuru.setMaximumRowCount(4);
		panelYeniAracEkle.add(comboBoxYakitTuru);

		comboBoxSanzimanTipi = new JComboBox();
		comboBoxSanzimanTipi.setModel(
				new DefaultComboBoxModel(new String[] { "L\u00FCtfen \u015Eanz\u0131man Tipi Se\u00E7iniz" }));
		comboBoxSanzimanTipi.setFont(new Font("Yu Gothic UI", Font.PLAIN, 16));
		comboBoxSanzimanTipi.setBounds(315, 117, 195, 27);
		comboBoxSanzimanTipi.setMaximumRowCount(4);
		panelYeniAracEkle.add(comboBoxSanzimanTipi);

		comboBoxAracSilmeFiltre = new JComboBox();
		comboBoxAracSilmeFiltre.setMaximumRowCount(4);
		comboBoxAracSilmeFiltre.setFont(new Font("Yu Gothic UI", Font.PLAIN, 18));
		comboBoxAracSilmeFiltre.setModel(new DefaultComboBoxModel(new String[] {
				"L\u00FCtfen Se\u00E7im Yap\u0131n\u0131z...", "Araç Þase Numarasý", "Araç Plaka", "Araç ID" }));
		comboBoxAracSilmeFiltre.setBounds(626, 688, 205, 27);
		panelYeniAracEkle.add(comboBoxAracSilmeFiltre);

		comboBoxAracSilmeSecim = new JComboBox();
		comboBoxAracSilmeSecim.setMaximumRowCount(4);
		comboBoxAracSilmeSecim
				.setModel(new DefaultComboBoxModel(new String[] { "L\u00FCtfen Ara\u00E7 Se\u00E7iniz" }));
		comboBoxAracSilmeSecim.setFont(new Font("Yu Gothic UI", Font.PLAIN, 17));
		comboBoxAracSilmeSecim.setBounds(856, 689, 205, 27);
		panelYeniAracEkle.add(comboBoxAracSilmeSecim);

		JButton btnGeri = new JButton("Geri");
		btnGeri.setBounds(1056, 11, 89, 23);
		contentPane.add(btnGeri);
		btnGeri.setFont(new Font("Yu Gothic UI", Font.PLAIN, 15));

		comboBoxFiltreSec = new JComboBox();
		comboBoxFiltreSec.setModel(new DefaultComboBoxModel(new String[] { "L\u00FCtfen Se\u00E7im Yap\u0131n\u0131z",
				"ID Numaras\u0131na G\u00F6re", "Plaka Verisine G\u00F6re", "\u015Ease Numaras\u0131na G\u00F6re" }));
		comboBoxFiltreSec.setToolTipText("");
		comboBoxFiltreSec.setMaximumRowCount(4);
		comboBoxFiltreSec.setFont(new Font("Yu Gothic UI", Font.PLAIN, 17));
		comboBoxFiltreSec.setBounds(10, 290, 205, 27);
		panelYeniAracEkle.add(comboBoxFiltreSec);

		comboBoxAracSec = new JComboBox();
		comboBoxAracSec.setMaximumRowCount(5);
		comboBoxAracSec.setFont(new Font("Yu Gothic UI", Font.PLAIN, 17));
		comboBoxAracSec.setBounds(245, 290, 205, 27);
		panelYeniAracEkle.add(comboBoxAracSec);

		JButton btnAracGuncelle = new JButton("G\u00DCNCELLE");
		btnAracGuncelle.setFont(new Font("Yu Gothic UI", Font.PLAIN, 17));
		btnAracGuncelle.setBounds(479, 290, 119, 27);
		panelYeniAracEkle.add(btnAracGuncelle);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 328, 1226, 347);
		panelYeniAracEkle.add(scrollPane);

		// ARAC LISTESI TABLE DOLDURMA
		tableAracListele = new JTable();
		tableAracListele.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		scrollPane.setViewportView(tableAracListele);
		aracListesiTablo = aracIslemleri.aracTabloDoldurma();
		tableAracListele.setModel(aracListesiTablo);


		JButton btnCikis = new JButton("\u00C7\u0131k\u0131\u015F");
		btnCikis.setFont(new Font("Yu Gothic UI", Font.PLAIN, 15));
		btnCikis.setBounds(1156, 11, 89, 23);
		contentPane.add(btnCikis);

		// Marka Verisi Cekme
		String markaSorgu = "SELECT marka FROM markatablo ";
		al.verileriGetir(comboBoxMarka, markaSorgu, "marka");
		// Model Verisi Cekme
		comboBoxMarka.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				comboBoxModel.removeAllItems();
				comboBoxModel.addItem("Lütfen Model Seçiniz");
				String marka = (String) comboBoxMarka.getSelectedItem();
				String modelSorgu = "SELECT model FROM modeltablo INNER JOIN markatablo ON modeltablo.markatablo_id = markatablo.id WHERE marka = '"
						+ marka + "'";
				al.verileriGetir(comboBoxModel, modelSorgu, "model");
			}
		});

		// Renk Verisi Cekme
		String renkSorgu = "SELECT renk FROM renktablo ";
		al.verileriGetir(comboBoxRenk, renkSorgu, "renk");

		// Yakit Turu Verisi Cekme
		String yakitTuruSorgu = "SELECT yakit_turu FROM yakitturutablo ";
		al.verileriGetir(comboBoxYakitTuru, yakitTuruSorgu, "yakit_turu");

		// Sanziman Verisi Cekme
		String sanzimanTipiSorgu = "SELECT sanziman_tipi FROM sanzimantipitablo ";
		al.verileriGetir(comboBoxSanzimanTipi, sanzimanTipiSorgu, "sanziman_tipi");

		// AracDurum Verisi Cekme
		String aracDurumSorgu = "SELECT arac_durumu FROM aracdurumtablo ";
		al.verileriGetir(comboBoxAracDurum, aracDurumSorgu, "arac_durumu");

		// Arac Silme Verisi Cekme Filtre ComboBoxu Dinleme
		comboBoxAracSilmeFiltre.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				comboBoxAracSilmeSecim.removeAllItems();
				comboBoxAracSilmeSecim.addItem("Lütfen Araç Seçiniz");
				String silmeSorgusu;
				if (comboBoxAracSilmeFiltre.getSelectedItem().equals("Araç ID")) {
					silmeSorgusu = "SELECT id FROM aractablo";
					al.verileriGetir(comboBoxAracSilmeSecim, silmeSorgusu, "id");
					aracSilmeFiltresi = "id";
				} else if (comboBoxAracSilmeFiltre.getSelectedItem().equals("Araç Plaka")) {
					silmeSorgusu = "SELECT plaka FROM aractablo";
					aracSilmeFiltresi = "plaka";
					al.verileriGetir(comboBoxAracSilmeSecim, silmeSorgusu, "plaka");
				} else if (comboBoxAracSilmeFiltre.getSelectedItem().equals("Araç Þase Numarasý")) {
					silmeSorgusu = "SELECT sase_numarasi FROM aractablo";
					al.verileriGetir(comboBoxAracSilmeSecim, silmeSorgusu, "sase_numarasi");
					aracSilmeFiltresi = "sase_numarasi";
				}
			}
		});

		// Silinecek Arac Verisi Dinleme
		comboBoxAracSilmeSecim.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				silinecekAracBilgisi = comboBoxAracSilmeSecim.getSelectedItem();
			}
		});

		// EVENTLER
		// Geri Butonu Eventi
		btnGeri.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AnaEkranGUI anaEkran = new AnaEkranGUI();
				anaEkran.setVisible(true);
				dispose();
			}
		});
		// Cikis BUTONU EVENTI
		btnCikis.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});

		// Arac Silme Butonu Eventi
		btnAracSil.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if ((comboBoxAracSilmeFiltre.getSelectedItem() == null
						|| comboBoxAracSilmeFiltre.getSelectedIndex() == 0)
						|| (comboBoxAracSilmeSecim.getSelectedItem() == null
								|| comboBoxAracSilmeSecim.getSelectedIndex() == 0)) {
					msj.mesajYazdir("bosAlan");
				} else {

					msj.mesajYazdir("onay");
					SilmeIslemleri silmeIslemleri = new SilmeIslemleri();
					String silmeSorgusu = "DELETE FROM aractablo where " + aracSilmeFiltresi + " = '"
							+ silinecekAracBilgisi + "'";
					silmeIslemleri.kayitSil(silmeSorgusu);
					msj.mesajYazdir("onay");

					// DIGERLERINI DE GUNCELLEME
					comboBoxAracSilmeSecim.removeAllItems();
					comboBoxAracSilmeSecim.addItem("Lütfen Araç Seçiniz");
					comboBoxAracSilmeFiltre.setSelectedIndex(0);
					if (comboBoxAracSilmeFiltre.getSelectedItem().equals("Araç ID")) {
						al.verileriGetir(comboBoxAracSilmeSecim, "SELECT id FROM aractablo", "id");
						aracSilmeFiltresi = "id";
					} else if (comboBoxAracSilmeFiltre.getSelectedItem().equals("Araç Plaka")) {
						aracSilmeFiltresi = "plaka";
						al.verileriGetir(comboBoxAracSilmeSecim, "SELECT plaka FROM aractablo", "plaka");
					} else if (comboBoxAracSilmeFiltre.getSelectedItem().equals("Araç Þase Numarasý")) {
						al.verileriGetir(comboBoxAracSilmeSecim, "SELECT sase_numarasi FROM aractablo",
								"sase_numarasi");
						aracSilmeFiltresi = "sase_numarasi";
					}
					
					// KIRALANAN ARACLAR TABLO DOLDUR
					kiralananAraclarTablo = aracKiralamaSatisislemleri.kiralananAraclarTableDoldur();
					tableKiralananAraclar.setModel(kiralananAraclarTablo);

					// KIRALIK ARACLAR TABLO DOLDURMA
					kiralikAraclarTablo = aracKiralamaSatisislemleri.kiralikAracTabloDoldurma();
					tableKiralikAraclar.setModel(kiralikAraclarTablo);
					
					// ANA EKRAN ARAC GUNCELLEME
					aracListesiTablo = aracIslemleri.aracTabloDoldurma();
					tableAracListele.setModel(aracListesiTablo);
					
					// ARAC SATIS TABLO YEJNILEME
					SatilanAraclarTablo = aracKiralamaSatisislemleri.satilanAraclarTableDoldur();
					tableSatilanArac.setModel(SatilanAraclarTablo);
					SatilikAraclarTablo = aracKiralamaSatisislemleri.satilikAracTabloDoldurma();
					tableSatilikArac.setModel(SatilikAraclarTablo);
					
					// EKRANLARI SIFIRALMA
					aracEklemeEkraniSifirla();
					aracKiralamaVeSatisEkranSifirla();
				}

			}
		});

		// Arac Ekleme Butonu Eventi
		btnAracEkle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (txtUretimYili.getText().length() == 0 || txtPlaka.getText().length() == 0
						|| txtMotorGucu.getText().length() == 0 || txtMotorKapasitesi.getText().length() == 0
						|| txtKm.getText().length() == 0 || txtFiyat.getText().length() == 0
						|| comboBoxModel.getSelectedItem() == null) {
					msj.mesajYazdir("bosAlan");

				}

				else {

					VeritabaniBaglantilari baglanti = new VeritabaniBaglantilari();
					String uretimYili = txtUretimYili.getText().toUpperCase();
					String plaka = txtPlaka.getText().toUpperCase();
					String motorGucu = txtMotorGucu.getText().toUpperCase();
					String motorKapasitesi = txtMotorKapasitesi.getText().toUpperCase();
					String km = txtKm.getText().toUpperCase();
					String fiyat = txtFiyat.getText().toUpperCase();
					String saseNumarasi = txtSaseNumarasi.getText().toUpperCase();

					Integer yakitTuru = baglanti.veriTabaniIDGetirme("yakitturutablo", "yakit_turu",
							(String) comboBoxYakitTuru.getSelectedItem());
					Integer sanzimanTipi = baglanti.veriTabaniIDGetirme("sanzimantipitablo", "sanziman_tipi",
							(String) comboBoxSanzimanTipi.getSelectedItem());
					Integer marka = baglanti.veriTabaniIDGetirme("markatablo", "marka",
							(String) comboBoxMarka.getSelectedItem());
					Integer model = baglanti.veriTabaniIDGetirme("modeltablo", "model",
							(String) comboBoxModel.getSelectedItem());
					Integer renk = baglanti.veriTabaniIDGetirme("renktablo", "renk",
							(String) comboBoxRenk.getSelectedItem());
					Integer aracDurum = baglanti.veriTabaniIDGetirme("aracdurumtablo", "arac_durumu",
							(String) comboBoxAracDurum.getSelectedItem());
					AracIslemleri aracIslemleri = new AracIslemleri(uretimYili, plaka, motorGucu, motorKapasitesi, km,
							fiyat, yakitTuru, sanzimanTipi, marka, model, renk, aracDurum, saseNumarasi);
					if (aracIslemleri.aracEkle()) {
						msj.mesajYazdir("onay");
						comboBoxFiltreSec.setSelectedIndex(0);
						comboBoxAracSilmeSecim.removeAllItems();
						comboBoxAracSilmeSecim.addItem("Lütfen Araç Seçiniz");
						comboBoxAracSilmeFiltre.setSelectedIndex(0);
						
						// KIRALANAN ARACLAR TABLO DOLDUR
						kiralananAraclarTablo = aracKiralamaSatisislemleri.kiralananAraclarTableDoldur();
						tableKiralananAraclar.setModel(kiralananAraclarTablo);

						// KIRALIK ARACLAR TABLO DOLDURMA
						kiralikAraclarTablo = aracKiralamaSatisislemleri.kiralikAracTabloDoldurma();
						tableKiralikAraclar.setModel(kiralikAraclarTablo);
						
						// ANA EKRAN ARAC GUNCELLEME
						aracListesiTablo = aracIslemleri.aracTabloDoldurma();
						tableAracListele.setModel(aracListesiTablo);
						
						// ARAC SATIS TABLO YEJNILEME
						SatilanAraclarTablo = aracKiralamaSatisislemleri.satilanAraclarTableDoldur();
						tableSatilanArac.setModel(SatilanAraclarTablo);
						SatilikAraclarTablo = aracKiralamaSatisislemleri.satilikAracTabloDoldurma();
						tableSatilikArac.setModel(SatilikAraclarTablo);
						
						// EKRANLARI GUNELLEME
						aracEklemeEkraniSifirla();
						aracKiralamaVeSatisEkranSifirla();

					} else {
						msj.mesajYazdir("Lütfen Girdiðiniz Verileri Kontrol Edin.\nDikkat Araç Plaka ve Þase Numarasý Benzersiz Olmalýdýr!");
						
					}

				}
			}
		});

		// Buton Marka Ekleme Eventi
		btnMarkaEkle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				yeniOzellikEklemeGUI.comboBoxSilinecekVeri.removeAllItems();
				yeniOzellikEklemeGUI.comboBoxGuncellenecekVeri.removeAllItems();
				tablo = "markatablo";
				colon = "marka";
				comboBox = comboBoxMarka;
				String markaSorgu = "SELECT marka FROM markatablo ";
				al.verileriGetir(yeniOzellikEklemeGUI.comboBoxSilinecekVeri, markaSorgu, "marka");
				al.verileriGetir(yeniOzellikEklemeGUI.comboBoxGuncellenecekVeri, markaSorgu, "marka");
				yeniOzellikEklemeGUI.setVisible(true);

			}
		});

		// Model Ekleme Eventi
		btnModelEkle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JLabel lblLtfenEklemekIstediiniz = new JLabel(
						"L\u00FCtfen Eklemek \u0130stedi\u011Finiz Markay\u0131 Se\u00E7iniz");
				lblLtfenEklemekIstediiniz.setFont(new Font("Yu Gothic UI", Font.PLAIN, 17));
				lblLtfenEklemekIstediiniz.setBounds(35, 52, 333, 23);
				yeniOzellikEklemeGUI.panelEkleme.add(lblLtfenEklemekIstediiniz);

				comboBoxModelIcinmarkaVerisi = new JComboBox();
				comboBoxModelIcinmarkaVerisi.setMaximumRowCount(5);
				comboBoxModelIcinmarkaVerisi.setFont(new Font("Yu Gothic UI", Font.PLAIN, 17));
				comboBoxModelIcinmarkaVerisi.setBounds(69, 84, 247, 27);
				yeniOzellikEklemeGUI.panelEkleme.add(comboBoxModelIcinmarkaVerisi);
				yeniOzellikEklemeGUI.txtYeniOzellikGirisAlani.setBounds(69, 179, 247, 27);
				yeniOzellikEklemeGUI.lblNewLabel.setBounds(38, 140, 300, 27);
				yeniOzellikEklemeGUI.btnYeniOzellikEkle.setBounds(146, 230, 89, 27);
				yeniOzellikEklemeGUI.setVisible(true);

				yeniOzellikEklemeGUI.comboBoxSilinecekVeri.removeAllItems();
				yeniOzellikEklemeGUI.comboBoxGuncellenecekVeri.removeAllItems();
				tablo = "modeltablo";
				colon = "model";
				comboBox = comboBoxModel;
				String modelSorgu = "SELECT model FROM modeltablo ";
				String markaSorgu = "SELECT marka FROM markatablo ";
				al.verileriGetir(yeniOzellikEklemeGUI.comboBoxSilinecekVeri, modelSorgu, "model");
				al.verileriGetir(yeniOzellikEklemeGUI.comboBoxGuncellenecekVeri, modelSorgu, "model");
				al.verileriGetir(comboBoxModelIcinmarkaVerisi, markaSorgu, "marka");

			}
		});

		// Buton Renk Ekleme Eventi
		btnRenkEkle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				yeniOzellikEklemeGUI.comboBoxSilinecekVeri.removeAllItems();
				yeniOzellikEklemeGUI.comboBoxGuncellenecekVeri.removeAllItems();
				tablo = "renktablo";
				colon = "renk";
				comboBox = comboBoxRenk;
				String renkSorgu = "SELECT renk FROM renktablo ";
				al.verileriGetir(yeniOzellikEklemeGUI.comboBoxSilinecekVeri, renkSorgu, "renk");
				al.verileriGetir(yeniOzellikEklemeGUI.comboBoxGuncellenecekVeri, renkSorgu, "renk");
				yeniOzellikEklemeGUI.setVisible(true);
			}
		});

		// Sanziman Tipi Ekleme Eventi
		btnSansizmanTipiEkle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				yeniOzellikEklemeGUI.comboBoxSilinecekVeri.removeAllItems();
				yeniOzellikEklemeGUI.comboBoxGuncellenecekVeri.removeAllItems();
				tablo = "sanzimantipitablo";
				colon = "sanziman_tipi";
				comboBox = comboBoxSanzimanTipi;
				String sanzimanSorgu = "SELECT sanziman_tipi FROM sanzimantipitablo ";
				al.verileriGetir(yeniOzellikEklemeGUI.comboBoxSilinecekVeri, sanzimanSorgu, "sanziman_tipi");
				al.verileriGetir(yeniOzellikEklemeGUI.comboBoxGuncellenecekVeri, sanzimanSorgu, "sanziman_tipi");
				yeniOzellikEklemeGUI.setVisible(true);
			}
		});

		// Yakit Turu EKleme Eventi
		btnYakitTuruEkle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				yeniOzellikEklemeGUI.comboBoxSilinecekVeri.removeAllItems();
				yeniOzellikEklemeGUI.comboBoxGuncellenecekVeri.removeAllItems();
				tablo = "yakitturutablo";
				colon = "yakit_turu";
				comboBox = comboBoxYakitTuru;
				String yakitSorgu = "SELECT yakit_turu FROM yakitturutablo ";
				al.verileriGetir(yeniOzellikEklemeGUI.comboBoxSilinecekVeri, yakitSorgu, "yakit_turu");
				al.verileriGetir(yeniOzellikEklemeGUI.comboBoxGuncellenecekVeri, yakitSorgu, "yakit_turu");
				yeniOzellikEklemeGUI.setVisible(true);
			}
		});

		// ARAC DURUMU EVENTI
		btnAracDurumuEkle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				yeniOzellikEklemeGUI.comboBoxSilinecekVeri.removeAllItems();
				yeniOzellikEklemeGUI.comboBoxGuncellenecekVeri.removeAllItems();
				tablo = "aracdurumtablo";
				colon = "arac_durumu";
				comboBox = comboBoxAracDurum;
				String aracDurumSorgu = "SELECT arac_durumu FROM aracdurumtablo ";
				al.verileriGetir(yeniOzellikEklemeGUI.comboBoxSilinecekVeri, aracDurumSorgu, "arac_durumu");
				al.verileriGetir(yeniOzellikEklemeGUI.comboBoxGuncellenecekVeri, aracDurumSorgu, "arac_durumu");
				yeniOzellikEklemeGUI.setVisible(true);
			}
		});

		// COmboBOx Dinleme
		comboBoxFiltreSec.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				comboBoxAracSec.removeAllItems();
				if (comboBoxFiltreSec.getSelectedItem().equals("ID Numarasýna Göre"))
					kolonAdi = "id";
				if (comboBoxFiltreSec.getSelectedItem().equals("Plaka Verisine Göre"))
					kolonAdi = "plaka";
				if (comboBoxFiltreSec.getSelectedItem().equals("Þase Numarasýna Göre"))
					kolonAdi = "sase_numarasi";
				String sorgu = "SELECT * FROM aractablo ";
				comboBoxAracSec.addItem("Lütfen Seçim Yapýn");
				al.verileriGetir(comboBoxAracSec, sorgu, kolonAdi);

			}
		});

		comboBoxAracSec.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if (comboBoxAracSec.getSelectedIndex() <= 0) {
				} else {
					String yenikolonAdi;
					if (kolonAdi.equals("id"))
						yenikolonAdi = "aractablo.id";
					else
						yenikolonAdi = kolonAdi;
					String sorgu = "SELECT * FROM aractablo INNER JOIN markatablo ON aractablo.markatablo_id = markatablo.id "
							+ "INNER JOIN modeltablo ON aractablo.modeltablo_id = modeltablo.id "
							+ "INNER JOIN renktablo ON aractablo.renktablo_id = renktablo.id "
							+ "INNER JOIN yakitturutablo ON aractablo.yakitturutablo_id = yakitturutablo.id "
							+ "INNER JOIN sanzimantipitablo ON aractablo.sanzimantipitablo_id = sanzimantipitablo.id "
							+ "INNER JOIN aracdurumtablo ON aractablo.aracdurumtablo_id = aracdurumtablo.id " + "WHERE "
							+ yenikolonAdi + " = '" + comboBoxAracSec.getSelectedItem() + "'";

					ResultSet rs = baglanti.listele(sorgu);
					try {
						while (rs.next()) {
							comboBoxMarka.setSelectedItem(rs.getString("marka"));
							comboBoxModel.setSelectedItem(rs.getString("model"));
							comboBoxRenk.setSelectedItem(rs.getString("renk"));
							comboBoxSanzimanTipi.setSelectedItem(rs.getString("sanziman_tipi"));
							txtPlaka.setText(rs.getString("plaka"));
							txtSaseNumarasi.setText(rs.getString("sase_numarasi"));
							txtMotorGucu.setText(rs.getString("motor_gucu"));
							txtMotorKapasitesi.setText(rs.getString("motor_kapasitesi"));
							comboBoxYakitTuru.setSelectedItem(rs.getString("yakit_turu"));
							txtUretimYili.setText(rs.getString("uretim_yili"));
							txtKm.setText(rs.getString("km"));
							txtFiyat.setText(rs.getString("fiyat"));
							comboBoxAracDurum.setSelectedItem(rs.getString("arac_durumu"));
						}
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		});

		// GUNCELLEME BUTONU
		btnAracGuncelle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (txtUretimYili.getText().length() == 0 || txtPlaka.getText().length() == 0
						|| txtMotorGucu.getText().length() == 0 || txtMotorKapasitesi.getText().length() == 0
						|| txtKm.getText().length() == 0 || txtFiyat.getText().length() == 0
						|| comboBoxModel.getSelectedItem() == null) {
					msj.mesajYazdir("bosAlan");

				}

				else {

					VeritabaniBaglantilari baglanti = new VeritabaniBaglantilari();
					String uretimYili = txtUretimYili.getText().toUpperCase();
					String plaka = txtPlaka.getText().toUpperCase();
					String motorGucu = txtMotorGucu.getText().toUpperCase();
					String motorKapasitesi = txtMotorKapasitesi.getText().toUpperCase();
					String km = txtKm.getText().toUpperCase();
					String fiyat = txtFiyat.getText().toUpperCase();
					String saseNumarasi = txtSaseNumarasi.getText().toUpperCase();

					Integer yakitTuru = baglanti.veriTabaniIDGetirme("yakitturutablo", "yakit_turu",
							(String) comboBoxYakitTuru.getSelectedItem());
					Integer sanzimanTipi = baglanti.veriTabaniIDGetirme("sanzimantipitablo", "sanziman_tipi",
							(String) comboBoxSanzimanTipi.getSelectedItem());
					Integer marka = baglanti.veriTabaniIDGetirme("markatablo", "marka",
							(String) comboBoxMarka.getSelectedItem());
					Integer model = baglanti.veriTabaniIDGetirme("modeltablo", "model",
							(String) comboBoxModel.getSelectedItem());
					Integer renk = baglanti.veriTabaniIDGetirme("renktablo", "renk",
							(String) comboBoxRenk.getSelectedItem());
					Integer aracDurum = baglanti.veriTabaniIDGetirme("aracdurumtablo", "arac_durumu",
							(String) comboBoxAracDurum.getSelectedItem());
					AracIslemleri aracIslemleri = new AracIslemleri(uretimYili, plaka, motorGucu, motorKapasitesi, km,
							fiyat, yakitTuru, sanzimanTipi, marka, model, renk, aracDurum, saseNumarasi);
					if (aracIslemleri.aracGuncelle(kolonAdi, (String) comboBoxAracSec.getSelectedItem()) ) {
						msj.mesajYazdir("onay");
						
						// KIRALANAN ARACLAR TABLO DOLDUR
						kiralananAraclarTablo = aracKiralamaSatisislemleri.kiralananAraclarTableDoldur();
						tableKiralananAraclar.setModel(kiralananAraclarTablo);

						// KIRALIK ARACLAR TABLO DOLDURMA
						kiralikAraclarTablo = aracKiralamaSatisislemleri.kiralikAracTabloDoldurma();
						tableKiralikAraclar.setModel(kiralikAraclarTablo);
						
						// ANA EKRAN ARAC GUNCELLEME
						aracListesiTablo = aracIslemleri.aracTabloDoldurma();
						tableAracListele.setModel(aracListesiTablo);
						
						// ARAC SATIS TABLO YEJNILEME
						SatilanAraclarTablo = aracKiralamaSatisislemleri.satilanAraclarTableDoldur();
						tableSatilanArac.setModel(SatilanAraclarTablo);
						SatilikAraclarTablo = aracKiralamaSatisislemleri.satilikAracTabloDoldurma();
						tableSatilikArac.setModel(SatilikAraclarTablo);
						
						// Anaekrani sifirla
						aracKiralamaVeSatisEkranSifirla();
						aracEklemeEkraniSifirla();

					} else {
						msj.mesajYazdir("Lütfen Girdiðiniz Verileri Kontrol Edin.\nDikkat Araç Plaka ve Þase Numarasý Benzersiz Olmalýdýr!");

					}

				}

			}
		});

		// AracKiralama Kýralanacak Arac ComboboxFiltre Dinleme
		comboBoxKiralanacakAracFiltre.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				comboBoxKýralýkAracSecim.removeAllItems();
				if (comboBoxKiralanacakAracFiltre.getSelectedItem().equals("ID Numarasýna Göre"))
					aracKiralamaKolonAdi = "id";
				if (comboBoxKiralanacakAracFiltre.getSelectedItem().equals("Plaka Verisine Göre"))
					aracKiralamaKolonAdi = "plaka";
				if (comboBoxKiralanacakAracFiltre.getSelectedItem().equals("Þase Numarasýna Göre"))
					aracKiralamaKolonAdi = "sase_numarasi";
				String sorgu = "SELECT * FROM aractablo INNER JOIN aracdurumtablo ON aractablo.aracdurumtablo_id = aracdurumtablo.id WHERE arac_durumu = 'KÝRALIK'";
				comboBoxKýralýkAracSecim.addItem("Lütfen Seçim Yapýn");
				al.verileriGetir(comboBoxKýralýkAracSecim, sorgu, aracKiralamaKolonAdi);
			}
		});

		// AracKiralama Kýralayacak Musteri ComboboxFiltre Dinleme
		comboBoxKiralanacakAracFiltre_1.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				comboBoxKýralýkAracSecim_1.removeAllItems();
				if (comboBoxKiralanacakAracFiltre_1.getSelectedItem().equals("ID Numarasýna Göre"))
					aracKiralamaKolonAdi_1 = "id";
				if (comboBoxKiralanacakAracFiltre_1.getSelectedItem().equals("T.C Numarasýna Göre"))
					aracKiralamaKolonAdi_1 = "tc_no";
				if (comboBoxKiralanacakAracFiltre_1.getSelectedItem().equals("Ýsime Göre"))
					aracKiralamaKolonAdi_1 = "isim";
				if (comboBoxKiralanacakAracFiltre_1.getSelectedItem().equals("Soy Ýsime Göre"))
					aracKiralamaKolonAdi_1 = "soy_isim";
				if (comboBoxKiralanacakAracFiltre_1.getSelectedItem().equals("Telefon Numarasýna Göre"))
					aracKiralamaKolonAdi_1 = "telefon_no";
				if (comboBoxKiralanacakAracFiltre_1.getSelectedItem().equals("Adresine Göre"))
					aracKiralamaKolonAdi_1 = "adres";
				if (comboBoxKiralanacakAracFiltre_1.getSelectedItem().equals("E-Mail Adresine Göre"))
					aracKiralamaKolonAdi_1 = "email";
				comboBoxKýralýkAracSecim_1.addItem("Lütfen Seçim Yapýn");
				String sorgu = "SELECT * FROM musteritablo ";
				al.verileriGetir(comboBoxKýralýkAracSecim_1, sorgu, aracKiralamaKolonAdi_1);
			}
		});

		// ARAC KIRALAMA BUTONU
		btnKiralamaIslemi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(comboBoxKiralanacakAracFiltre.getSelectedIndex()==0 || comboBoxKiralanacakAracFiltre_1.getSelectedIndex()==0 || comboBoxKýralýkAracSecim.getSelectedIndex()==0|| comboBoxKýralýkAracSecim_1.getSelectedIndex()==0 || ftKiralamaBaslangicTarihi.getText().length()==0 || ftAracKiralamaBitisTarihi.getText().length()==0 || txtKiralamaUcreti.getText().length()==0 ) {
					msj.mesajYazdir("bosAlan");
					aracKiralamaVeSatisEkranSifirla();
				}else {
				Integer aracId = baglanti.veriTabaniIDGetirme("aractablo", aracKiralamaKolonAdi,
						(String) comboBoxKýralýkAracSecim.getSelectedItem());
				Integer musteriId = baglanti.veriTabaniIDGetirme("musteritablo", aracKiralamaKolonAdi_1,
						(String) comboBoxKýralýkAracSecim_1.getSelectedItem());
				String kiralamaBaslangicTarihi = ftKiralamaBaslangicTarihi.getText();
				String kiralamaBitisTarihi = ftAracKiralamaBitisTarihi.getText();
				String kiralamaUcreti = txtKiralamaUcreti.getText();
				Integer aracDurum = 5;
				aracKiralamaSatisislemleri.aracKirala(aracId, musteriId, kiralamaBaslangicTarihi, kiralamaBitisTarihi,
						kiralamaUcreti, aracDurum);
				baglanti.veriTabaniVeriGuncelle("UPDATE aractablo SET aracdurumtablo_id = 5 WHERE id = " + aracId);
				msj.mesajYazdir("onay");
				}
				// KIRALANAN ARACLAR TABLO DOLDUR
				kiralananAraclarTablo = aracKiralamaSatisislemleri.kiralananAraclarTableDoldur();
				tableKiralananAraclar.setModel(kiralananAraclarTablo);

				// KIRALIK ARACLAR TABLO DOLDURMA
				kiralikAraclarTablo = aracKiralamaSatisislemleri.kiralikAracTabloDoldurma();
				tableKiralikAraclar.setModel(kiralikAraclarTablo);
				// ANA EKRAN ARAC GUNCELLEME
				aracListesiTablo = aracIslemleri.aracTabloDoldurma();
				tableAracListele.setModel(aracListesiTablo);
				// AracKiralama Ekrani Sifirla
				aracKiralamaVeSatisEkranSifirla();
				
			}
		});

		// ARAC TESLIMALMA BUTONU
		btnAracTeslimAl.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(5>5){
					msj.mesajYazdir("bosAlan");
				}else {
				String teslimAlincakArac = ((String) (tableKiralananAraclar
						.getValueAt(tableKiralananAraclar.getSelectedRow(), 1)));
				baglanti.veriTabaniVeriSil("DELETE FROM arackiralamatablo WHERE id= "
						+ (tableKiralananAraclar.getValueAt(tableKiralananAraclar.getSelectedRow(), 0)));
				baglanti.veriTabaniVeriGuncelle(
						"UPDATE aractablo SET aracdurumtablo_id = 1 WHERE plaka = '" + teslimAlincakArac + "' ");
					msj.mesajYazdir("onay");
				}
				// KIRALANAN ARACLAR TABLO DOLDUR
				kiralananAraclarTablo = aracKiralamaSatisislemleri.kiralananAraclarTableDoldur();
				tableKiralananAraclar.setModel(kiralananAraclarTablo);
				
				
				// KIRALIK ARACLAR TABLO DOLDURMA
				kiralikAraclarTablo = aracKiralamaSatisislemleri.kiralikAracTabloDoldurma();
				tableKiralikAraclar.setModel(kiralikAraclarTablo);
				// ANA EKRAN ARAC GUNCELLEME
				aracListesiTablo = aracIslemleri.aracTabloDoldurma();
				tableAracListele.setModel(aracListesiTablo);
				// AracKiralama Ekrani Sifirla
				aracKiralamaVeSatisEkranSifirla();

			}
		});

		// AracSATIS SATILACAK Arac ComboboxFiltre Dinleme
		comboBoxSatilikAracFiltre.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				comboBoxSatilikAracSecim.removeAllItems();
				if (comboBoxSatilikAracFiltre.getSelectedItem().equals("ID Numarasýna Göre"))
					aracSatisKolonAdi = "id";
				if (comboBoxSatilikAracFiltre.getSelectedItem().equals("Plaka Verisine Göre"))
					aracSatisKolonAdi = "plaka";
				if (comboBoxSatilikAracFiltre.getSelectedItem().equals("Þase Numarasýna Göre"))
					aracSatisKolonAdi = "sase_numarasi";
				String sorgu = "SELECT * FROM aractablo INNER JOIN aracdurumtablo ON aractablo.aracdurumtablo_id = aracdurumtablo.id WHERE arac_durumu = 'SATILIK'";
				comboBoxSatilikAracSecim.addItem("Lütfen Seçim Yapýn");
				al.verileriGetir(comboBoxSatilikAracSecim, sorgu, aracSatisKolonAdi);
			}
		});

		// AracSATIS SATILACAK ARAC Musteri ComboboxFiltre Dinleme
		comboBoxSatilikAracFiltre_1.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				comboBoxSatilikAracSecim_1.removeAllItems();
				if (comboBoxSatilikAracFiltre_1.getSelectedItem().equals("T.C Numarasýna Göre"))
					aracSatisKolonAdi_1 = "tc_no";
				if (comboBoxSatilikAracFiltre_1.getSelectedItem().equals("Ýsime Göre"))
					aracSatisKolonAdi_1 = "isim";
				if (comboBoxSatilikAracFiltre_1.getSelectedItem().equals("Soy Ýsime Göre"))
					aracSatisKolonAdi_1 = "soy_isim";
				if (comboBoxSatilikAracFiltre_1.getSelectedItem().equals("Telefon Numarasýna Göre"))
					aracSatisKolonAdi_1 = "telefon_no";
				if (comboBoxSatilikAracFiltre_1.getSelectedItem().equals("Adresine Göre"))
					aracSatisKolonAdi_1 = "adres";
				if (comboBoxSatilikAracFiltre_1.getSelectedItem().equals("E-Mail Adresine Göre"))
					aracSatisKolonAdi_1 = "email";
				comboBoxSatilikAracSecim.addItem("Lütfen Seçim Yapýn");
				String sorgu = "SELECT * FROM musteritablo ";
				al.verileriGetir(comboBoxSatilikAracSecim_1, sorgu, aracSatisKolonAdi_1);
			}
		});

		// ARAC SATIS BUTONU

		btnSatýsIslemý.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(comboBoxSatilikAracFiltre.getSelectedIndex()==0 ||comboBoxSatilikAracFiltre_1.getSelectedIndex()==0 || comboBoxSatilikAracSecim.getSelectedIndex()==0 || comboBoxSatilikAracSecim.getSelectedIndex()==0 || txtSatýsFýyat.getText().length()==0) {
					msj.mesajYazdir("bosAlan");
				}else {
				Integer aracId1 = baglanti.veriTabaniIDGetirme("aractablo", aracSatisKolonAdi,
						(String) comboBoxSatilikAracSecim.getSelectedItem());
				Integer musteriId1 = baglanti.veriTabaniIDGetirme("musteritablo", aracSatisKolonAdi_1,
						(String) comboBoxSatilikAracSecim_1.getSelectedItem());
				String satisUcreti1 = txtSatýsFýyat.getText();
				Integer aracDurum1 = 6;
				aracKiralamaSatisislemleri.aracSat(aracId1, musteriId1, satisUcreti1, aracDurum1);
				baglanti.veriTabaniVeriGuncelle("UPDATE aractablo SET aracdurumtablo_id = 6 WHERE id = " + aracId1);
				msj.mesajYazdir("onay");
				}
				// ANA EKRAN ARAC TABLO GUNCELLEME
				aracListesiTablo = aracIslemleri.aracTabloDoldurma();
				tableAracListele.setModel(aracListesiTablo);
				
				// ARAC SATIS TABLO YEJNILEME
				SatilanAraclarTablo = aracKiralamaSatisislemleri.satilanAraclarTableDoldur();
				tableSatilanArac.setModel(SatilanAraclarTablo);
				SatilikAraclarTablo = aracKiralamaSatisislemleri.satilikAracTabloDoldurma();
				tableSatilikArac.setModel(SatilikAraclarTablo);
				
				// ANA EKRAN COMBOBOXLAR TEMIZLE
				aracKiralamaVeSatisEkranSifirla();
				aracEklemeEkraniSifirla();
				
				
				
			}
		});
		
		
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(5>5){
					msj.mesajYazdir("bosAlan");
				}else {
				String teslimAlincakArac = ((String) (tableSatilanArac.getValueAt(tableSatilanArac.getSelectedRow(), 1)));
				baglanti.veriTabaniVeriSil("DELETE FROM aracsatistablo WHERE id= "+ tableSatilanArac.getValueAt(tableSatilanArac.getSelectedRow(), 0));
				baglanti.veriTabaniVeriGuncelle("UPDATE aractablo SET aracdurumtablo_id = 2 WHERE plaka = '" + teslimAlincakArac + "' ");
					msj.mesajYazdir("onay");
				}
				// SATILAN ARACLAR TABLO DOLDUR
				SatilanAraclarTablo = aracKiralamaSatisislemleri.satilanAraclarTableDoldur();
				tableSatilanArac.setModel(SatilanAraclarTablo);
				
				// SATILIK ARACLAR TABLO DOLDURMA
				SatilikAraclarTablo = aracKiralamaSatisislemleri.satilikAracTabloDoldurma();
				tableSatilikArac.setModel(SatilikAraclarTablo);
				// ANA EKRAN ARAC GUNCELLEME
				aracListesiTablo = aracIslemleri.aracTabloDoldurma();
				tableAracListele.setModel(aracListesiTablo);
				// AracKiralama Ekrani Sifirla
				aracKiralamaVeSatisEkranSifirla();
			}
		});
		
		
		
		
		
		
		
		

	}

	public void aracKiralamaVeSatisEkranSifirla() {
		// COMBOBOXLARI SIFIRLAMA
		comboBoxKiralanacakAracFiltre.setSelectedIndex(0);
		comboBoxKýralýkAracSecim.removeAllItems();
		comboBoxKiralanacakAracFiltre_1.setSelectedIndex(0);
		comboBoxKýralýkAracSecim_1.removeAllItems();
		ftKiralamaBaslangicTarihi.setText(null);
		ftAracKiralamaBitisTarihi.setText(null);
		txtKiralamaUcreti.setText(null);
		comboBoxSatilikAracFiltre.setSelectedIndex(0);
		comboBoxSatilikAracSecim.removeAllItems();
		comboBoxSatilikAracFiltre_1.setSelectedIndex(0);
		comboBoxSatilikAracSecim_1.removeAllItems();
		txtSatýsFýyat.setText(null);
		

	}

	public void aracEklemeEkraniSifirla() {
		txtUretimYili.setText(null);
		txtPlaka.setText(null);
		txtMotorGucu.setText(null);
		txtMotorKapasitesi.setText(null);
		txtKm.setText(null);
		txtFiyat.setText(null);
		txtSaseNumarasi.setText(null);
		comboBoxAracDurum.setSelectedIndex(0);
		comboBoxMarka.setSelectedIndex(0);
		comboBoxYakitTuru.setSelectedIndex(0);
		comboBoxSanzimanTipi.setSelectedIndex(0);
		comboBoxRenk.setSelectedIndex(0);
		comboBoxAracDurum.setSelectedIndex(0);

		comboBoxAracSec.removeAllItems();
		comboBoxAracSec.addItem("Lütfen Seçim Yapýn");
		comboBoxAracSec.setSelectedIndex(0);

		comboBoxFiltreSec.setSelectedIndex(0);

		comboBoxFiltreSec.setSelectedIndex(0);
		comboBoxAracSilmeSecim.removeAllItems();
		comboBoxAracSilmeSecim.addItem("Lütfen Araç Seçiniz");
		comboBoxAracSilmeFiltre.setSelectedIndex(0);
	}
}
