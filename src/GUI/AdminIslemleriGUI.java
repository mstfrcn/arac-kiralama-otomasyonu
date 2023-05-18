package GUI;

import java.awt.BorderLayout;

import java.awt.EventQueue;

import AnaFonksiyonlar.*;
import YardimciSinif.ComboBoxVerileriCekme;
import YardimciSinif.Mesajlar;
import YardimciSinif.VeritabaniBaglantilari;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JTabbedPane;
import java.awt.SystemColor;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ItemListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ItemEvent;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.LineBorder;
import javax.swing.border.CompoundBorder;
import javax.swing.border.BevelBorder;

public class AdminIslemleriGUI extends JFrame {

	private JPanel contentPane;
	private JTextField txtAdminIsim;
	private JTextField txtAdminSoyIsim;
	private JTextField txtKullaniciAdi;
	private JPasswordField pfSifre;
	private JTextField txtDegistirilcekVeri;
	
	public static String kolonAdi;
	public static String adminVerisiSec;
	
	public static String kolonAdi1;
	public static String adminVerisiSec1;

	// NESNELER
	Mesajlar mesaj = new Mesajlar();
	public JPasswordField pfKurtarmaKodu;
	ComboBoxVerileriCekme comboBoxVerileriCekme = new ComboBoxVerileriCekme();
	VeritabaniBaglantilari baglanti = new VeritabaniBaglantilari();
	AdminIslemleri adminIslemleri = new AdminIslemleri();

	
	// TABLE
	public JTable table;
	public DefaultTableModel dmodel = new DefaultTableModel();
	public Object[] kolonlar = {"ID","Ýsim","Soy Ýsim","Kullanýcý Adý","Þifre","Kurtarma Kodu"};
	public Object[] satirlar = new Object[6];

	// COMBOBOXLAR 
	JComboBox comboBoxFiltreSec;
	JComboBox comboBoxAdminSec;
	JComboBox comboBoxAdminVerisiSec;
	JComboBox comboBoxFiltreSec_1;
	JComboBox comboBoxAdminSec_1;
	
	
	
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AdminIslemleriGUI frame = new AdminIslemleriGUI();
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
	public AdminIslemleriGUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(500, 120, 1313, 856);
		contentPane = new JPanel();
		contentPane.setBackground(Color.LIGHT_GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setFont(new Font("Yu Gothic UI", Font.PLAIN, 17));
		tabbedPane.setBounds(48, 54, 1206, 703);
		contentPane.add(tabbedPane);

		JPanel panel = new JPanel();
		panel.setBackground(new Color(160, 82, 45));
		tabbedPane.addTab("       Admin Ekleme Güncelleme Ve Silme Ýþlemleri       ", null, panel, null);
		panel.setLayout(null);

		JLabel lblAdminIsim = new JLabel("\u0130S\u0130M:");
		lblAdminIsim.setBounds(10, 80, 174, 27);
		lblAdminIsim.setFont(new Font("Yu Gothic UI", Font.PLAIN, 17));
		panel.add(lblAdminIsim);

		txtAdminIsim = new JTextField();
		txtAdminIsim.setBounds(10, 118, 174, 27);
		txtAdminIsim.setFont(new Font("Yu Gothic UI", Font.PLAIN, 17));
		panel.add(txtAdminIsim);
		txtAdminIsim.setColumns(10);

		txtAdminSoyIsim = new JTextField();
		txtAdminSoyIsim.setBounds(10, 203, 174, 27);
		txtAdminSoyIsim.setFont(new Font("Yu Gothic UI", Font.PLAIN, 17));
		txtAdminSoyIsim.setColumns(10);
		panel.add(txtAdminSoyIsim);

		JLabel lblAdminSoyIsim = new JLabel("SOY \u0130S\u0130M:");
		lblAdminSoyIsim.setBounds(10, 165, 174, 27);
		lblAdminSoyIsim.setFont(new Font("Yu Gothic UI", Font.PLAIN, 17));
		panel.add(lblAdminSoyIsim);

		txtKullaniciAdi = new JTextField();
		txtKullaniciAdi.setBounds(10, 290, 174, 27);
		txtKullaniciAdi.setFont(new Font("Yu Gothic UI", Font.PLAIN, 17));
		txtKullaniciAdi.setColumns(10);
		panel.add(txtKullaniciAdi);

		JLabel lblKullancAd = new JLabel("KULLANICI ADI:");
		lblKullancAd.setBounds(10, 252, 174, 27);
		lblKullancAd.setFont(new Font("Yu Gothic UI", Font.PLAIN, 17));
		panel.add(lblKullancAd);

		JLabel lblSifre = new JLabel("SIFRE:");
		lblSifre.setBounds(10, 342, 174, 27);
		lblSifre.setFont(new Font("Yu Gothic UI", Font.PLAIN, 17));
		panel.add(lblSifre);

		pfSifre = new JPasswordField();
		pfSifre.setBounds(10, 379, 174, 27);
		pfSifre.setFont(new Font("Yu Gothic UI", Font.PLAIN, 16));
		panel.add(pfSifre);

		JButton btnYeniAdminEkle = new JButton("KAYDET");
		btnYeniAdminEkle.setBounds(38, 533, 119, 27);
		btnYeniAdminEkle.setFont(new Font("Yu Gothic UI", Font.PLAIN, 17));
		panel.add(btnYeniAdminEkle);

		comboBoxFiltreSec = new JComboBox();
		comboBoxFiltreSec.setMaximumRowCount(5);
		comboBoxFiltreSec.setBounds(1017, 114, 174, 27);
		comboBoxFiltreSec.setToolTipText("");
		comboBoxFiltreSec.setModel(new DefaultComboBoxModel(new String[] {"Filtreleme Se\u00E7imini Yap\u0131n\u0131z", "ID'ye G\u00F6re", "\u0130sime G\u00F6re", "Soy \u0130sime G\u00F6re", "Kullan\u0131c\u0131 Ad\u0131na G\u00F6re"}));
		comboBoxFiltreSec.setFont(new Font("Yu Gothic UI", Font.PLAIN, 17));
		panel.add(comboBoxFiltreSec);

		comboBoxAdminSec = new JComboBox();
		comboBoxAdminSec.setMaximumRowCount(5);
		comboBoxAdminSec.setBounds(1017, 176, 174, 27);
		comboBoxAdminSec.setFont(new Font("Yu Gothic UI", Font.PLAIN, 17));
		panel.add(comboBoxAdminSec);

		txtDegistirilcekVeri = new JTextField();
		txtDegistirilcekVeri.setBounds(1017, 286, 174, 27);
		txtDegistirilcekVeri.setFont(new Font("Yu Gothic UI", Font.PLAIN, 17));
		txtDegistirilcekVeri.setColumns(10);
		panel.add(txtDegistirilcekVeri);

		JButton btnAdminGuncelle = new JButton("G\u00DCNCELLE");
		btnAdminGuncelle.setBounds(1045, 338, 119, 27);
		btnAdminGuncelle.setFont(new Font("Yu Gothic UI", Font.PLAIN, 17));
		panel.add(btnAdminGuncelle);
		
		pfKurtarmaKodu = new JPasswordField();
		pfKurtarmaKodu.setBounds(10, 465, 174, 27);
		pfKurtarmaKodu.setFont(new Font("Yu Gothic UI", Font.PLAIN, 16));
		panel.add(pfKurtarmaKodu);
		
		JLabel lblKurtarmaKodu = new JLabel("\u015E\u0130FRE SIFIRLAMA KODU");
		lblKurtarmaKodu.setBounds(10, 428, 200, 27);
		lblKurtarmaKodu.setFont(new Font("Yu Gothic UI", Font.PLAIN, 17));
		panel.add(lblKurtarmaKodu);
		
		comboBoxAdminVerisiSec = new JComboBox();
		comboBoxAdminVerisiSec.setMaximumRowCount(5);
		comboBoxAdminVerisiSec.setBounds(1017, 236, 174, 27);
		comboBoxAdminVerisiSec.setModel(new DefaultComboBoxModel(new String[] {"G\u00FCncellemek \u0130stedi\u011Finiz Veriyi Se\u00E7iniz", "\u0130sim", "Soy \u0130sim", "Kullan\u0131c\u0131 Ad\u0131", "\u015Eifre", "\u015Eifre S\u0131f\u0131rlama Kodu"}));
		comboBoxAdminVerisiSec.setFont(new Font("Yu Gothic UI", Font.PLAIN, 17));
		panel.add(comboBoxAdminVerisiSec);
		
		JButton btnAdminSil = new JButton("SIL");
		btnAdminSil.setBounds(1045, 516, 119, 27);
		btnAdminSil.setFont(new Font("Yu Gothic UI", Font.PLAIN, 17));
		panel.add(btnAdminSil);
		
		comboBoxFiltreSec_1 = new JComboBox();
		comboBoxFiltreSec_1.setMaximumRowCount(5);
		comboBoxFiltreSec_1.setBounds(1017, 399, 174, 27);
		comboBoxFiltreSec_1.setModel(new DefaultComboBoxModel(new String[] {"Filtreleme Se\u00E7imini Yap\u0131n\u0131z", "ID'ye G\u00F6re", "\u0130sime G\u00F6re", "Soy \u0130sime G\u00F6re", "Kullan\u0131c\u0131 Ad\u0131na G\u00F6re"}));
		comboBoxFiltreSec_1.setToolTipText("");
		comboBoxFiltreSec_1.setFont(new Font("Yu Gothic UI", Font.PLAIN, 17));
		panel.add(comboBoxFiltreSec_1);
		
		comboBoxAdminSec_1 = new JComboBox();
		comboBoxAdminSec_1.setMaximumRowCount(5);
		comboBoxAdminSec_1.setBounds(1017, 461, 174, 27);
		comboBoxAdminSec_1.setFont(new Font("Yu Gothic UI", Font.PLAIN, 17));
		panel.add(comboBoxAdminSec_1);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(194, 81, 813, 525);
		panel.add(scrollPane);
		
		// TABLE MODELI
		table = new JTable();
		table.setFont(new Font("Yu Gothic UI", Font.PLAIN, 17));
		scrollPane.setViewportView(table);	
		dmodel = adminIslemleri.adminTabloDoldurma();
		table.setModel(dmodel);


		
		JButton btnGeri = new JButton("Geri");
		btnGeri.setFont(new Font("Yu Gothic UI", Font.PLAIN, 17));
		btnGeri.setBounds(1066, 21, 89, 26);
		contentPane.add(btnGeri);
		
		JButton btnCikis = new JButton("\u00C7\u0131k\u0131\u015F");
		btnCikis.setFont(new Font("Yu Gothic UI", Font.PLAIN, 17));
		btnCikis.setBounds(1165, 21, 89, 26);
		contentPane.add(btnCikis);

		// EVENTLER
		// YENI ADMIN KAYDET EVENTI
		btnYeniAdminEkle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (txtAdminIsim.getText().length() == 0 || txtAdminSoyIsim.getText().length() == 0
						|| txtKullaniciAdi.getText().length() == 0 || pfSifre.getText().length() == 0) {
					mesaj.mesajYazdir("bosAlan");
				} else {
					AdminIslemleri adminIslemleri = new AdminIslemleri(txtAdminIsim.getText().toUpperCase(),
							txtAdminSoyIsim.getText().toUpperCase(), txtKullaniciAdi.getText(), pfSifre.getText(),pfKurtarmaKodu.getText() );
					if(adminIslemleri.adminEkle()) {
						mesaj.mesajYazdir("onay");
						
						ekraniSifirla();
						
						dmodel = adminIslemleri.adminTabloDoldurma();
						table.setModel(dmodel);
						
					}else {
						mesaj.mesajYazdir("Lütfen Girdiðiniz Verileri Kontrol Edin.\nDikkat Kullanýcý Adý Benzersiz Olmalýdýr!");
						ekraniSifirla();
					}
				}
			}
		});
			
		
		// COmboBOx Dinleme
		comboBoxFiltreSec.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				comboBoxAdminSec.removeAllItems();
				if(comboBoxFiltreSec.getSelectedItem().equals("ID'ye Göre")) kolonAdi = "id";
				if(comboBoxFiltreSec.getSelectedItem().equals("Ýsime Göre")) kolonAdi = "isim";
				if(comboBoxFiltreSec.getSelectedItem().equals("Soy Ýsime Göre")) kolonAdi = "soy_isim";
				if(comboBoxFiltreSec.getSelectedItem().equals("Kullanýcý Adýna Göre")) kolonAdi = "kullanici_adi";
				String sorgu = "SELECT * FROM admintablo ";
				comboBoxVerileriCekme.verileriGetir(comboBoxAdminSec,sorgu , kolonAdi);
			}
		});
		
		comboBoxFiltreSec_1.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				comboBoxAdminSec_1.removeAllItems();
				if(comboBoxFiltreSec_1.getSelectedItem().equals("ID'ye Göre")) kolonAdi1 = "id";
				if(comboBoxFiltreSec_1.getSelectedItem().equals("Ýsime Göre")) kolonAdi1 = "isim";
				if(comboBoxFiltreSec_1.getSelectedItem().equals("Soy Ýsime Göre")) kolonAdi1 = "soy_isim";
				if(comboBoxFiltreSec_1.getSelectedItem().equals("Kullanýcý Adýna Göre")) kolonAdi1 = "kullanici_adi";
				String sorgu = "SELECT * FROM admintablo ";
				comboBoxVerileriCekme.verileriGetir(comboBoxAdminSec_1,sorgu , kolonAdi1);
			}
		});
		
		
		comboBoxAdminVerisiSec.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if(comboBoxAdminVerisiSec.getSelectedItem().equals("Ýsim")) adminVerisiSec = "isim";
				if(comboBoxAdminVerisiSec.getSelectedItem().equals("Soy Ýsim")) adminVerisiSec = "soy_isim";
				if(comboBoxAdminVerisiSec.getSelectedItem().equals("Kullanýcý Adý")) adminVerisiSec = "kullanici_adi";
				if(comboBoxAdminVerisiSec.getSelectedItem().equals("Þifre")) adminVerisiSec = "sifre";
				if(comboBoxAdminVerisiSec.getSelectedItem().equals("Þifre Sýfýrlama Kodu")) adminVerisiSec = "kurtarma_kodu";
				
			}
		});
		
		
		// BUTON GUNCELLE
		btnAdminGuncelle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(comboBoxFiltreSec.getSelectedIndex() == 0 || comboBoxAdminVerisiSec.getSelectedIndex()==0 || comboBoxAdminSec.getSelectedItem() == null || txtDegistirilcekVeri.getText().length()==0 ) {
					mesaj.mesajYazdir("bosAlan");
					
				}else {
					baglanti.baglan();
					String s = "UPDATE admintablo SET "+adminVerisiSec+" = '"+txtDegistirilcekVeri.getText()+"' WHERE "+kolonAdi +" = '"+ (String) comboBoxAdminSec.getSelectedItem()+"' ";
					if(baglanti.veriTabaniVeriEkle(s)) {
						mesaj.mesajYazdir("onay");

						// EKRANI SIFIRLAMA
						ekraniSifirla();
						
						dmodel = adminIslemleri.adminTabloDoldurma();
						table.setModel(dmodel);
						
					}else mesaj.mesajYazdir("Lütfen Girdiðiniz Verileri Kontrol Edin.\nDikkat Kullanýcý Adý Benzersiz Olmalýdýr!");
					
				}
				
			}
		});
		
		// BUTON SIL
		btnAdminSil.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(comboBoxFiltreSec_1.getSelectedIndex() == 0 || comboBoxAdminSec_1.getSelectedItem() == null ) {
					mesaj.mesajYazdir("bosAlan");
					
				}else {
					baglanti.baglan();
					String s = "DELETE FROM admintablo WHERE "+kolonAdi1 +" = '"+ (String) comboBoxAdminSec_1.getSelectedItem()+"' ";
					System.out.println(s);
					if(baglanti.veriTabaniVeriEkle(s)) {
						mesaj.mesajYazdir("onay");

						dmodel = adminIslemleri.adminTabloDoldurma();
						table.setModel(dmodel);
						
						//EKRANI SIFIRLAMA
						ekraniSifirla();
						
						
					}
					
				}
			}
		});
		
		
		
		// BUTON GERI
		btnGeri.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AnaEkranGUI anaEkran = new AnaEkranGUI();
				anaEkran.setVisible(true);
				dispose();
			}
		});
		
		// BUTON CIKIS
		btnCikis.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		
		

	}
	
	public void ekraniSifirla() {
		txtAdminIsim.setText(null);
		txtAdminSoyIsim.setText(null);
		txtKullaniciAdi.setText(null);
		pfSifre.setText(null);
		pfKurtarmaKodu.setText(null);
		comboBoxFiltreSec.setSelectedIndex(0);
		comboBoxAdminVerisiSec.setSelectedIndex(0);
		comboBoxAdminSec.removeAllItems();
		txtDegistirilcekVeri.setText(null);
		comboBoxFiltreSec_1.setSelectedIndex(0);
		comboBoxAdminSec_1.removeAllItems();
	}

}
