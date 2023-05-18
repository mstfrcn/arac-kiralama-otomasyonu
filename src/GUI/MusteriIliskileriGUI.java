package GUI;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.MaskFormatter;

import AnaFonksiyonlar.AdminIslemleri;
import AnaFonksiyonlar.MusteriIslemleri;
import YardimciSinif.*;

import javax.swing.JTabbedPane;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.text.ParseException;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JFormattedTextField;
import javax.swing.JComboBox;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ListSelectionModel;

public class MusteriIliskileriGUI extends JFrame {

	private JPanel paneMusteriIliskileri;
	private JTextField txtMusteriIsim;
	private JTextField txtMusteriSoyIsim;
	private JTextField txtMusteriEmail;
	private JTextField txtDegistirilcekVeri;

	// TABLE
	private JTable tableMusteri;
	DefaultTableModel dmodel = new DefaultTableModel();
	// COMBOBOX
	JComboBox comboBoxFiltreSec;
	JComboBox comboBoxMusteriSec;
	JComboBox comboBoxMusteriVerisiSec;
	JComboBox comboBoxFiltreSec_1;
	JComboBox comboBoxMusteriSec_1;
	// NESNELER
	MusteriIslemleri musteriIslemleri = new MusteriIslemleri();
	Mesajlar mesaj = new Mesajlar();
	ComboBoxVerileriCekme comboBoxVerileriCekme = new ComboBoxVerileriCekme();
	VeritabaniBaglantilari baglanti = new VeritabaniBaglantilari();
	// DEGISKENLER
	String kolonAdi;
	String kolonAdi1;
	String musteriVerisiSec;
	// COMPONENTLER
	JLabel lblTc;
	// FORMATTEDTEXT
	JFormattedTextField ftMusteriTc;
	JFormattedTextField ftMusteriTelefonNo;

	private JTextField txtAdres;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MusteriIliskileriGUI frame = new MusteriIliskileriGUI();
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
	public MusteriIliskileriGUI() {
		setTitle("Musteri Ilsikileri Yonetimi");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(500, 120, 1313, 856);
		paneMusteriIliskileri = new JPanel();
		paneMusteriIliskileri.setBackground(SystemColor.activeCaptionBorder);
		paneMusteriIliskileri.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(paneMusteriIliskileri);
		paneMusteriIliskileri.setLayout(null);

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBackground(SystemColor.control);
		tabbedPane.setFont(new Font("Yu Gothic UI", Font.PLAIN, 17));
		tabbedPane.setBounds(46, 59, 1206, 681);
		paneMusteriIliskileri.add(tabbedPane);

		JPanel panel = new JPanel();
		panel.setBackground(SystemColor.activeCaption);
		tabbedPane.addTab("      Müþteri Ekleme Güncelleme Ve Silme Ýþlemleri      ", null, panel, null);
		panel.setLayout(null);

		JLabel lblMusteriIsim = new JLabel("\u0130S\u0130M:");
		lblMusteriIsim.setFont(new Font("Yu Gothic UI", Font.PLAIN, 17));
		lblMusteriIsim.setBounds(10, 55, 174, 27);
		panel.add(lblMusteriIsim);

		txtMusteriIsim = new JTextField();
		txtMusteriIsim.setFont(new Font("Yu Gothic UI", Font.PLAIN, 17));
		txtMusteriIsim.setColumns(10);
		txtMusteriIsim.setBounds(10, 93, 174, 27);
		panel.add(txtMusteriIsim);

		JLabel lblMusteriSoyIsim = new JLabel("SOY \u0130S\u0130M:");
		lblMusteriSoyIsim.setFont(new Font("Yu Gothic UI", Font.PLAIN, 17));
		lblMusteriSoyIsim.setBounds(10, 140, 174, 27);
		panel.add(lblMusteriSoyIsim);

		txtMusteriSoyIsim = new JTextField();
		txtMusteriSoyIsim.setFont(new Font("Yu Gothic UI", Font.PLAIN, 17));
		txtMusteriSoyIsim.setColumns(10);
		txtMusteriSoyIsim.setBounds(10, 178, 174, 27);
		panel.add(txtMusteriSoyIsim);

		JLabel lblMusteriTelefonNumarasi = new JLabel("TELEFON NO:");
		lblMusteriTelefonNumarasi.setFont(new Font("Yu Gothic UI", Font.PLAIN, 17));
		lblMusteriTelefonNumarasi.setBounds(10, 299, 174, 27);
		panel.add(lblMusteriTelefonNumarasi);

		JLabel lblEmail = new JLabel("E-MAIL:");
		lblEmail.setFont(new Font("Yu Gothic UI", Font.PLAIN, 17));
		lblEmail.setBounds(10, 389, 174, 27);
		panel.add(lblEmail);

		JLabel lblAdres = new JLabel("ADRES:");
		lblAdres.setFont(new Font("Yu Gothic UI", Font.PLAIN, 17));
		lblAdres.setBounds(10, 475, 200, 27);
		panel.add(lblAdres);

		JButton btnYeniMusteriEkle = new JButton("KAYDET");
		btnYeniMusteriEkle.setFont(new Font("Yu Gothic UI", Font.PLAIN, 17));
		btnYeniMusteriEkle.setBounds(38, 580, 119, 27);
		panel.add(btnYeniMusteriEkle);

		// TELEFON NO FORMAT
		ftMusteriTelefonNo = new JFormattedTextField(musteriIslemleri.format("(05##) ### ## ##"));
		ftMusteriTelefonNo.setFont(new Font("Yu Gothic UI", Font.PLAIN, 17));
		ftMusteriTelefonNo.setBounds(10, 337, 174, 27);
		panel.add(ftMusteriTelefonNo);

		txtMusteriEmail = new JTextField();
		txtMusteriEmail.setFont(new Font("Yu Gothic UI", Font.PLAIN, 17));
		txtMusteriEmail.setColumns(10);
		txtMusteriEmail.setBounds(10, 426, 174, 27);
		panel.add(txtMusteriEmail);

		lblTc = new JLabel("T.C:");
		lblTc.setFont(new Font("Yu Gothic UI", Font.PLAIN, 17));
		lblTc.setBounds(10, 217, 174, 27);
		panel.add(lblTc);

		// TC NO FORMAT
		ftMusteriTc = new JFormattedTextField(musteriIslemleri.format("###########"));
		ftMusteriTc.setFont(new Font("Yu Gothic UI", Font.PLAIN, 17));
		ftMusteriTc.setBounds(10, 261, 174, 27);
		panel.add(ftMusteriTc);

		comboBoxFiltreSec = new JComboBox();
		comboBoxFiltreSec.setMaximumRowCount(4);
		comboBoxFiltreSec.setModel(new DefaultComboBoxModel(
				new String[] { "L\u00FCtfen Se\u00E7im Yap\u0131n!", "ID Numaras\u0131na G\u00F6re",
						"T.C Numaras\u0131na G\u00F6re", "\u0130sime G\u00F6re", "Soy \u0130sime G\u00F6re",
						"Telefon Numaras\u0131na G\u00F6re", "Adresine G\u00F6re", "E-Mail Adresine G\u00F6re" }));
		comboBoxFiltreSec.setToolTipText("");
		comboBoxFiltreSec.setFont(new Font("Yu Gothic UI", Font.PLAIN, 17));
		comboBoxFiltreSec.setBounds(1017, 116, 174, 27);
		panel.add(comboBoxFiltreSec);

		comboBoxMusteriSec = new JComboBox();
		comboBoxMusteriSec.setMaximumRowCount(5);
		comboBoxMusteriSec.setFont(new Font("Yu Gothic UI", Font.PLAIN, 17));
		comboBoxMusteriSec.setBounds(1017, 178, 174, 27);
		panel.add(comboBoxMusteriSec);

		comboBoxMusteriVerisiSec = new JComboBox();
		comboBoxMusteriVerisiSec.setModel(new DefaultComboBoxModel(new String[] {
				"L\u00FCtfen G\u00FCncellenecek Veriyi Se\u00E7in!", "ID Numaras\u0131", "T.C Numaras\u0131",
				"\u0130sim", "Soy \u0130sim", "Telefon Numaras\u0131", "Adresi", "E-Mail Adresi" }));
		comboBoxMusteriVerisiSec.setMaximumRowCount(5);
		comboBoxMusteriVerisiSec.setFont(new Font("Yu Gothic UI", Font.PLAIN, 17));
		comboBoxMusteriVerisiSec.setBounds(1017, 238, 174, 27);
		panel.add(comboBoxMusteriVerisiSec);

		txtDegistirilcekVeri = new JTextField();
		txtDegistirilcekVeri.setFont(new Font("Yu Gothic UI", Font.PLAIN, 17));
		txtDegistirilcekVeri.setColumns(10);
		txtDegistirilcekVeri.setBounds(1017, 288, 174, 27);
		panel.add(txtDegistirilcekVeri);

		JButton btnMusteriGuncelle = new JButton("G\u00DCNCELLE");
		btnMusteriGuncelle.setFont(new Font("Yu Gothic UI", Font.PLAIN, 17));
		btnMusteriGuncelle.setBounds(1045, 340, 119, 27);
		panel.add(btnMusteriGuncelle);

		comboBoxFiltreSec_1 = new JComboBox();
		comboBoxFiltreSec_1.setMaximumRowCount(4);
		comboBoxFiltreSec_1.setModel(new DefaultComboBoxModel(
				new String[] { "L\u00FCtfen Se\u00E7im Yap\u0131n!", "ID Numaras\u0131na G\u00F6re",
						"T.C Numaras\u0131na G\u00F6re", "\u0130sime G\u00F6re", "Soy \u0130sime G\u00F6re",
						"Telefon Numaras\u0131na G\u00F6re", "Adresine G\u00F6re", "E-Mail Adresine G\u00F6re" }));
		comboBoxFiltreSec_1.setToolTipText("");
		comboBoxFiltreSec_1.setFont(new Font("Yu Gothic UI", Font.PLAIN, 17));
		comboBoxFiltreSec_1.setBounds(1017, 401, 174, 27);
		panel.add(comboBoxFiltreSec_1);

		comboBoxMusteriSec_1 = new JComboBox();
		comboBoxMusteriSec_1.setFont(new Font("Yu Gothic UI", Font.PLAIN, 17));
		comboBoxMusteriSec_1.setBounds(1017, 463, 174, 27);
		panel.add(comboBoxMusteriSec_1);

		JButton btnMusteriSil = new JButton("SIL");
		btnMusteriSil.setFont(new Font("Yu Gothic UI", Font.PLAIN, 17));
		btnMusteriSil.setBounds(1045, 518, 119, 27);
		panel.add(btnMusteriSil);

		JButton btnGeri = new JButton("Geri");
		btnGeri.setFont(new Font("Yu Gothic UI", Font.PLAIN, 17));
		btnGeri.setBounds(1064, 25, 89, 23);
		paneMusteriIliskileri.add(btnGeri);

		JButton btnCikis = new JButton("\u00C7\u0131k\u0131\u015F");
		btnCikis.setFont(new Font("Yu Gothic UI", Font.PLAIN, 17));
		btnCikis.setBounds(1163, 25, 89, 23);
		paneMusteriIliskileri.add(btnCikis);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(194, 55, 813, 555);
		panel.add(scrollPane);

		// TABLE DOLDURMA
		tableMusteri = new JTable();
		tableMusteri.setFont(new Font("Yu Gothic UI", Font.PLAIN, 15));
		scrollPane.setViewportView(tableMusteri);
		dmodel = musteriIslemleri.adminTabloDoldurma();
		tableMusteri.setModel(dmodel);
		txtAdres = new JTextField();
		txtAdres.setFont(new Font("Yu Gothic UI", Font.PLAIN, 17));
		txtAdres.setColumns(10);
		txtAdres.setBounds(10, 513, 174, 27);
		panel.add(txtAdres);

		// EVENTLER
		// YENI Musteri Ekleme
		btnYeniMusteriEkle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (txtMusteriIsim.getText().length() == 0 || txtMusteriSoyIsim.getText().length() == 0
						|| ftMusteriTc.getText().length() < 11 || ftMusteriTelefonNo.getText().length() == 0
						|| txtMusteriEmail.getText().length() == 0 || txtAdres.getText().length() == 0) {
					mesaj.mesajYazdir("bosAlan");
				} else {
					MusteriIslemleri adminIslemleri = new MusteriIslemleri(txtMusteriIsim.getText().toUpperCase(),
							txtMusteriSoyIsim.getText().toUpperCase(), ftMusteriTelefonNo.getText(),
							txtMusteriEmail.getText(), txtAdres.getText(), ftMusteriTc.getText());
					if (adminIslemleri.adminEkle() == false) {
						mesaj.mesajYazdir(
								"Lütfen Verileri Doðru Giriniz.\nDikkat Farklý bir T.C no, Telefon no Veya E-Mail adresi girdiðinizden Emin Olunuz!");
					} else {
						mesaj.mesajYazdir("onay");
					}

					// EKRANI SIFIRLA
					ekraniSifirla();

					dmodel = musteriIslemleri.adminTabloDoldurma();
					tableMusteri.setModel(dmodel);

				}
			}
		});

		/*
		 * ID Numarasýna Göre T.C Numarasýna Göre Ýsime Göre Soy Ýsime Göre Telefon
		 * Numarasýna Göre Adresine Göre E-Mail Adresine Göre
		 */

		// COmboBOx Dinleme
		comboBoxFiltreSec.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				comboBoxMusteriSec.removeAllItems();
				if (comboBoxFiltreSec.getSelectedItem().equals("ID Numarasýna Göre"))
					kolonAdi = "id";
				if (comboBoxFiltreSec.getSelectedItem().equals("T.C Numarasýna Göre"))
					kolonAdi = "tc_no";
				if (comboBoxFiltreSec.getSelectedItem().equals("Ýsime Göre"))
					kolonAdi = "isim";
				if (comboBoxFiltreSec.getSelectedItem().equals("Soy Ýsime Göre"))
					kolonAdi = "soy_isim";
				if (comboBoxFiltreSec.getSelectedItem().equals("Telefon Numarasýna Göre"))
					kolonAdi = "telefon_no";
				if (comboBoxFiltreSec.getSelectedItem().equals("Adresine Göre"))
					kolonAdi = "adres";
				if (comboBoxFiltreSec.getSelectedItem().equals("E-Mail Adresine Göre"))
					kolonAdi = "email";

				String sorgu = "SELECT * FROM musteritablo ";
				comboBoxVerileriCekme.verileriGetir(comboBoxMusteriSec, sorgu, kolonAdi);
			}
		});

		comboBoxFiltreSec_1.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				comboBoxMusteriSec_1.removeAllItems();
				if (comboBoxFiltreSec_1.getSelectedItem().equals("ID Numarasýna Göre"))
					kolonAdi1 = "id";
				if (comboBoxFiltreSec_1.getSelectedItem().equals("T.C Numarasýna Göre"))
					kolonAdi1 = "tc_no";
				if (comboBoxFiltreSec_1.getSelectedItem().equals("Ýsime Göre"))
					kolonAdi1 = "isim";
				if (comboBoxFiltreSec_1.getSelectedItem().equals("Soy Ýsime Göre"))
					kolonAdi1 = "soy_isim";
				if (comboBoxFiltreSec_1.getSelectedItem().equals("Telefon Numarasýna Göre"))
					kolonAdi1 = "telefon_no";
				if (comboBoxFiltreSec_1.getSelectedItem().equals("Adresine Göre"))
					kolonAdi1 = "adres";
				if (comboBoxFiltreSec_1.getSelectedItem().equals("E-Mail Adresine Göre"))
					kolonAdi1 = "email";
				String sorgu = "SELECT * FROM musteritablo ";
				comboBoxVerileriCekme.verileriGetir(comboBoxMusteriSec_1, sorgu, kolonAdi1);
			}
		});

		comboBoxMusteriVerisiSec.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if (comboBoxMusteriVerisiSec.getSelectedItem().equals("ID Numarasý"))
					musteriVerisiSec = "id";
				if (comboBoxMusteriVerisiSec.getSelectedItem().equals("T.C Numarasý"))
					musteriVerisiSec = "tc_no";
				if (comboBoxMusteriVerisiSec.getSelectedItem().equals("Ýsim"))
					musteriVerisiSec = "isim";
				if (comboBoxMusteriVerisiSec.getSelectedItem().equals("Soy Ýsim"))
					musteriVerisiSec = "soy_isim";
				if (comboBoxMusteriVerisiSec.getSelectedItem().equals("Telefon Numarasý"))
					musteriVerisiSec = "telefon_no";
				if (comboBoxMusteriVerisiSec.getSelectedItem().equals("Adresi"))
					musteriVerisiSec = "adres";
				if (comboBoxMusteriVerisiSec.getSelectedItem().equals("E-Mail Adresi"))
					musteriVerisiSec = "email";

			}
		});

		// BUTON GUNCELLE
		btnMusteriGuncelle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (comboBoxFiltreSec.getSelectedIndex() == 0 || comboBoxMusteriVerisiSec.getSelectedIndex() == 0
						|| comboBoxMusteriSec.getSelectedItem() == null
						|| txtDegistirilcekVeri.getText().length() == 0) {
					mesaj.mesajYazdir("bosAlan");

				} else {
					baglanti.baglan();
					String s = "UPDATE musteritablo SET " + musteriVerisiSec + " = '" + txtDegistirilcekVeri.getText()
							+ "' WHERE " + kolonAdi + " = '" + (String) comboBoxMusteriSec.getSelectedItem() + "' ";
					System.out.println(s);
					if (baglanti.veriTabaniVeriEkle(s) == false) {
						mesaj.mesajYazdir(
								"Lütfen Verileri Doðru Giriniz.\nDikkat Farklý bir T.C no, Telefon no Veya E-Mail adresi girdiðinizden Emin Olunuz!");
					} else {

						mesaj.mesajYazdir("onay");

						// EKRANI SIFIRLA
						ekraniSifirla();

						dmodel = musteriIslemleri.adminTabloDoldurma();
						tableMusteri.setModel(dmodel);

					}

				}

			}
		});

		// BUTON SIL
		btnMusteriSil.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (comboBoxFiltreSec_1.getSelectedIndex() == 0 || comboBoxMusteriSec_1.getSelectedItem() == null) {
					mesaj.mesajYazdir("bosAlan");

				} else {
					baglanti.baglan();
					String s = "DELETE FROM musteriTablo WHERE " + kolonAdi1 + " = '"
							+ (String) comboBoxMusteriSec_1.getSelectedItem() + "' ";

					baglanti.veriTabaniVeriEkle(s);

					mesaj.mesajYazdir("onay");

					// EKRANI SIFIRALAMA
					ekraniSifirla();

					dmodel = musteriIslemleri.adminTabloDoldurma();
					tableMusteri.setModel(dmodel);

				}
			}

		});

		// Geri Butonu Eventi
		btnGeri.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AnaEkranGUI anaEkran = new AnaEkranGUI();
				anaEkran.setVisible(true);
				dispose();
			}
		});

		// Cikis Butonu Eventi
		btnCikis.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});

	}

	public void ekraniSifirla() {
		txtMusteriIsim.setText(null);
		txtMusteriSoyIsim.setText(null);
		ftMusteriTelefonNo.setText(null);
		txtMusteriEmail.setText(null);
		txtAdres.setText(null);
		ftMusteriTc.setText(null);
		comboBoxFiltreSec.setSelectedIndex(0);
		comboBoxMusteriVerisiSec.setSelectedIndex(0);
		comboBoxMusteriSec.removeAllItems();
		txtDegistirilcekVeri.setText(null);
		comboBoxMusteriSec_1.removeAllItems();
	}

}
