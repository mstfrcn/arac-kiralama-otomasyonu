package GUI;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import AnaFonksiyonlar.AracIslemleri;

import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.ActionEvent;

import YardimciSinif.*;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JTabbedPane;
import java.awt.SystemColor;

public class YeniOzellikEklemeGUI extends JFrame {

	JPanel paneYeniOzellikEkle;
	JTextField txtYeniOzellikGirisAlani;
	static JComboBox comboBoxSilinecekVeri;
	static JComboBox comboBoxGuncellenecekVeri;
	static JPanel panelEkleme;
	static JButton btnYeniOzellikEkle;
	JLabel lblNewLabel;
	DefaultTableModel table = new DefaultTableModel();

	VeritabaniBaglantilari baglanti = new VeritabaniBaglantilari();
	Mesajlar mesaj = new Mesajlar();
	public static AracYonetimGUI aracYonetimGUI = new AracYonetimGUI();
	public static ComboBoxVerileriCekme comboBoxYenile = new ComboBoxVerileriCekme();
	private JTextField txtGuncellenecekVeri;
	
	AracIslemleri aracIslemleri = new AracIslemleri();
	

	/**
	 * Launch the application.
	 */

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					YeniOzellikEklemeGUI frame = new YeniOzellikEklemeGUI();
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
	public YeniOzellikEklemeGUI() {
		setTitle("OZELLIKLERI DUZENLE");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(780, 260, 420, 413);
		paneYeniOzellikEkle = new JPanel();
		paneYeniOzellikEkle.setBackground(Color.WHITE);
		paneYeniOzellikEkle.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(paneYeniOzellikEkle);
		paneYeniOzellikEkle.setLayout(null);

		JButton btnYeniOzellikEkleCikis = new JButton("\u00C7\u0131k\u0131\u015F");
		btnYeniOzellikEkleCikis.setFont(new Font("Yu Gothic UI", Font.PLAIN, 15));
		btnYeniOzellikEkleCikis.setBounds(316, 9, 77, 23);
		paneYeniOzellikEkle.add(btnYeniOzellikEkleCikis);

		JTabbedPane tabbedOzellikIslemleri = new JTabbedPane(JTabbedPane.TOP);
		tabbedOzellikIslemleri.setFont(new Font("Yu Gothic UI", Font.PLAIN, 16));
		tabbedOzellikIslemleri.setBounds(10, 43, 383, 322);
		paneYeniOzellikEkle.add(tabbedOzellikIslemleri);

		JPanel panelGuncelleme = new JPanel();
		panelGuncelleme.setBackground(Color.PINK);
		tabbedOzellikIslemleri.addTab("   Güncelleme   ", null, panelGuncelleme, null);
		panelGuncelleme.setLayout(null);

		comboBoxGuncellenecekVeri = new JComboBox();
		comboBoxGuncellenecekVeri.setFont(new Font("Yu Gothic UI", Font.PLAIN, 17));
		comboBoxGuncellenecekVeri.setBounds(67, 85, 247, 27);
		panelGuncelleme.add(comboBoxGuncellenecekVeri);

		JPanel panelSilme = new JPanel();
		panelSilme.setBackground(SystemColor.info);
		tabbedOzellikIslemleri.addTab("   Silme   ", null, panelSilme, null);
		panelSilme.setLayout(null);

		JLabel lblLtfenSilmekIstediiniz = new JLabel("L\u00FCtfen Silmek \u0130stedi\u011Finiz Veriyi Se\u00E7iniz");
		lblLtfenSilmekIstediiniz.setBounds(42, 78, 300, 23);
		panelSilme.add(lblLtfenSilmekIstediiniz);
		lblLtfenSilmekIstediiniz.setFont(new Font("Yu Gothic UI", Font.PLAIN, 17));

		comboBoxSilinecekVeri = new JComboBox();
		comboBoxSilinecekVeri.setMaximumRowCount(4);
		comboBoxSilinecekVeri.setFont(new Font("Yu Gothic UI", Font.PLAIN, 16));
		comboBoxSilinecekVeri.setBounds(71, 127, 247, 27);
		panelSilme.add(comboBoxSilinecekVeri);

		JButton btnOzellýkSýl = new JButton("Sil");
		btnOzellýkSýl.setBounds(140, 185, 89, 30);
		panelSilme.add(btnOzellýkSýl);
		btnOzellýkSýl.setFont(new Font("Yu Gothic UI", Font.PLAIN, 15));

		JLabel lblLtfenSilmekIstediiniz_1 = new JLabel("G\u00FCncellemek \u0130stedi\u011Finiz Veriyi Se\u00E7iniz");
		lblLtfenSilmekIstediiniz_1.setBounds(45, 51, 291, 23);
		panelGuncelleme.add(lblLtfenSilmekIstediiniz_1);
		lblLtfenSilmekIstediiniz_1.setFont(new Font("Yu Gothic UI", Font.PLAIN, 17));

		JLabel lblLtfenSilmekIstediiniz_1_1 = new JLabel("G\u00FCncellemek \u0130stedi\u011Finiz Veriyi Giriniz");
		lblLtfenSilmekIstediiniz_1_1.setFont(new Font("Yu Gothic UI", Font.PLAIN, 17));
		lblLtfenSilmekIstediiniz_1_1.setBounds(45, 142, 280, 23);
		panelGuncelleme.add(lblLtfenSilmekIstediiniz_1_1);

		txtGuncellenecekVeri = new JTextField();
		txtGuncellenecekVeri.setFont(new Font("Yu Gothic UI", Font.PLAIN, 17));
		txtGuncellenecekVeri.setBounds(67, 176, 247, 27);
		panelGuncelleme.add(txtGuncellenecekVeri);
		txtGuncellenecekVeri.setColumns(10);

		JButton btnNewButton = new JButton("G\u00FCncelle");
		btnNewButton.setFont(new Font("Yu Gothic UI", Font.PLAIN, 17));
		btnNewButton.setBounds(128, 240, 109, 35);
		panelGuncelleme.add(btnNewButton);

		panelEkleme = new JPanel();
		panelEkleme.setBackground(SystemColor.activeCaption);
		tabbedOzellikIslemleri.addTab("   Ekleme   ", null, panelEkleme, null);
		panelEkleme.setLayout(null);

		lblNewLabel = new JLabel("L\u00FCtfen Eklemek \u0130stedi\u011Finiz Veriyi Giriniz.");
		lblNewLabel.setBounds(32, 78, 300, 23);
		panelEkleme.add(lblNewLabel);
		lblNewLabel.setFont(new Font("Yu Gothic UI", Font.PLAIN, 17));

		txtYeniOzellikGirisAlani = new JTextField();
		txtYeniOzellikGirisAlani.setFont(new Font("Yu Gothic UI", Font.PLAIN, 17));
		txtYeniOzellikGirisAlani.setBounds(62, 125, 247, 27);
		panelEkleme.add(txtYeniOzellikGirisAlani);
		txtYeniOzellikGirisAlani.setColumns(10);

		btnYeniOzellikEkle = new JButton("Ekle");
		btnYeniOzellikEkle.setBounds(137, 185, 89, 27);
		panelEkleme.add(btnYeniOzellikEkle);
		btnYeniOzellikEkle.setFont(new Font("Yu Gothic UI", Font.PLAIN, 15));

		// EVENTLER

		// EKLEME BUTONU
		btnYeniOzellikEkle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (txtYeniOzellikGirisAlani.getText().length() == 0) {
					mesaj.mesajYazdir("bosAlan");
				} else {
					baglanti.baglan();
					if (aracYonetimGUI.tablo.equals("modeltablo")) {
						Integer markaId = baglanti.veriTabaniIDGetirme("markatablo", "marka",
								(String) aracYonetimGUI.comboBoxModelIcinmarkaVerisi.getSelectedItem());
						String sorgu = "INSERT INTO " + AracYonetimGUI.tablo + " ( model, markatablo_id) VALUES ('"
								+ txtYeniOzellikGirisAlani.getText().toUpperCase() + "', " + markaId + ")";
						baglanti.veriTabaniVeriEkle(sorgu);
					} else {
						String sorgu = "INSERT INTO " + AracYonetimGUI.tablo + "(" + AracYonetimGUI.colon
								+ ") VALUES ('" + txtYeniOzellikGirisAlani.getText().toUpperCase() + "')";
						baglanti.veriTabaniVeriEkle(sorgu);
					}
					mesaj.mesajYazdir("onay");
					txtYeniOzellikGirisAlani.setText(null);
					// ISLEMDEN SONRA TEMIZLEME
					comboBoxSilinecekVeri.removeAllItems();
					comboBoxGuncellenecekVeri.removeAllItems();
					AracYonetimGUI.comboBox.removeAllItems();
					// ISLEMDEN SONRA TEKRAR GUNCELLEME
					String denemSorgu1 = "SELECT " + AracYonetimGUI.colon + " FROM " + AracYonetimGUI.tablo;
					comboBoxYenile.verileriGetir(comboBoxSilinecekVeri, denemSorgu1, AracYonetimGUI.colon);
					comboBoxYenile.verileriGetir(comboBoxGuncellenecekVeri, denemSorgu1, AracYonetimGUI.colon);
					// ISLEMDEN SONRA ANA PANELI TEKRAR GUNCELLEME
					String sorgu2 = "SELECT " + AracYonetimGUI.colon + " FROM " + AracYonetimGUI.tablo;
					comboBoxYenile.verileriGetir(AracYonetimGUI.comboBox, sorgu2, AracYonetimGUI.colon);
					
				}
			}
		});

		// SILME BUTONU EVENTI
		btnOzellýkSýl.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (comboBoxSilinecekVeri.getSelectedItem() == null) {
					mesaj.mesajYazdir("bosAlan");
				} else {
					baglanti.baglan();
					String sorgu = "DELETE FROM " + AracYonetimGUI.tablo + " WHERE " + AracYonetimGUI.colon + " = '"
							+ comboBoxSilinecekVeri.getSelectedItem() + "'";
					if (baglanti.veriTabaniVeriSil(sorgu)) {
						mesaj.mesajYazdir("onay");
					}
				}

				// ISLEMDEN SONRA TEMIZLEME
				comboBoxSilinecekVeri.removeAllItems();
				comboBoxGuncellenecekVeri.removeAllItems();
				AracYonetimGUI.comboBox.removeAllItems();
				// ISLEMDEN SONRA TEKRAR GUNCELLEME
				String denemSorgu1 = "SELECT " + AracYonetimGUI.colon + " FROM " + AracYonetimGUI.tablo;
				comboBoxYenile.verileriGetir(comboBoxSilinecekVeri, denemSorgu1, AracYonetimGUI.colon);
				comboBoxYenile.verileriGetir(comboBoxGuncellenecekVeri, denemSorgu1, AracYonetimGUI.colon);

			}
		});

		// GUNCELLEME BUTONU EVENTI
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (comboBoxGuncellenecekVeri.getSelectedItem() == null
						|| txtGuncellenecekVeri.getText().length() == 0) {
					mesaj.mesajYazdir("bosAlan");

				} else {
					String guncellenecekVeri = txtGuncellenecekVeri.getText().toUpperCase();
					String sorgu3 = "UPDATE " + AracYonetimGUI.tablo + " SET " + AracYonetimGUI.colon + " = '"
							+ guncellenecekVeri + "' WHERE " + AracYonetimGUI.colon + " = '"
							+ (String) comboBoxGuncellenecekVeri.getSelectedItem() + "'";

					baglanti.baglan();
					baglanti.veriTabaniVeriGuncelle(sorgu3);
					System.out.println(sorgu3);
					mesaj.mesajYazdir("onay");
					txtGuncellenecekVeri.setText(null);

					// ISLEMDEN SONRA TEMIZLEME
					comboBoxSilinecekVeri.removeAllItems();
					comboBoxGuncellenecekVeri.removeAllItems();
					AracYonetimGUI.comboBox.removeAllItems();
					// ISLEMDEN SONRA TEKRAR GUNCELLEME
					String denemSorgu1 = "SELECT " + AracYonetimGUI.colon + " FROM " + AracYonetimGUI.tablo;
					comboBoxYenile.verileriGetir(comboBoxSilinecekVeri, denemSorgu1, AracYonetimGUI.colon);
					comboBoxYenile.verileriGetir(comboBoxGuncellenecekVeri, denemSorgu1, AracYonetimGUI.colon);
					// ISLEMDEN SONRA ANA PANELI TEKRAR GUNCELLEME
					String sorgu2 = "SELECT " + AracYonetimGUI.colon + " FROM " + AracYonetimGUI.tablo;
					comboBoxYenile.verileriGetir(AracYonetimGUI.comboBox, sorgu2, AracYonetimGUI.colon);

				}

			}
		});

		// CIKIS BUTONU EVENTI
		btnYeniOzellikEkleCikis.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				// ANA EKRAN ARAC GUNCELLEME
				table = aracIslemleri.aracTabloDoldurma();
				aracYonetimGUI.tableAracListele.setModel(table);
				
			}
		});

	}
}
