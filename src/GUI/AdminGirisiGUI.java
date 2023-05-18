package GUI;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import YardimciSinif.*;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.SystemColor;

public class AdminGirisiGUI extends JFrame {

	private JPanel paneKullaniciGirisi;
	VeritabaniBaglantilari veriTabaniBaglantisi = new VeritabaniBaglantilari();
	public Integer hak = 3;
	private JPasswordField pfSifre;
	
	// NESNELER
	Mesajlar mesaj = new Mesajlar();
	VeritabaniBaglantilari baglanti = new VeritabaniBaglantilari();
	AdminSifreUnuttumGUI adminSifreUnuttumGUI = new AdminSifreUnuttumGUI();
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AdminGirisiGUI frame = new AdminGirisiGUI();
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
	public AdminGirisiGUI() {
		setBackground(Color.WHITE);
		setTitle("KULLANICI GIRISI");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(675, 320, 700, 400);
		paneKullaniciGirisi = new JPanel();
		paneKullaniciGirisi.setBackground(new Color(85, 107, 47));
		paneKullaniciGirisi.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(paneKullaniciGirisi);
		paneKullaniciGirisi.setLayout(null);

		JLabel lblKullanaciAdi = new JLabel("Kullan\u0131c\u0131 Ad\u0131:");
		lblKullanaciAdi.setBackground(Color.WHITE);
		lblKullanaciAdi.setFont(new Font("Yu Gothic UI", Font.PLAIN, 17));
		lblKullanaciAdi.setBounds(188, 107, 152, 26);
		paneKullaniciGirisi.add(lblKullanaciAdi);

		JTextField txtKullaniciAdi = new JTextField();
		txtKullaniciAdi.setFont(new Font("Yu Gothic UI", Font.PLAIN, 17));
		txtKullaniciAdi.setBounds(350, 107, 152, 26);
		paneKullaniciGirisi.add(txtKullaniciAdi);
		txtKullaniciAdi.setColumns(10);

		JLabel lblSifre = new JLabel("\u015Eifre:");
		lblSifre.setBackground(Color.WHITE);
		lblSifre.setFont(new Font("Yu Gothic UI", Font.PLAIN, 17));
		lblSifre.setBounds(188, 157, 152, 26);
		paneKullaniciGirisi.add(lblSifre);

		JButton btnGirisYap = new JButton("Giri\u015F Yap");
		btnGirisYap.setFont(new Font("Yu Gothic UI", Font.PLAIN, 13));
		btnGirisYap.setForeground(SystemColor.desktop);
		btnGirisYap.setBackground(SystemColor.controlHighlight);
		btnGirisYap.setBounds(350, 210, 152, 34);
		paneKullaniciGirisi.add(btnGirisYap);

		JLabel lblUyari = new JLabel();
		lblUyari.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblUyari.setBounds(188, 265, 314, 34);
		paneKullaniciGirisi.add(lblUyari);
		
		pfSifre = new JPasswordField();
		pfSifre.setBounds(350, 158, 152, 26);
		paneKullaniciGirisi.add(pfSifre);
		
		JButton btnSifreSifirla = new JButton("\u015Eifremi Unuttum");
		btnSifreSifirla.setFont(new Font("Yu Gothic UI", Font.PLAIN, 14));
		btnSifreSifirla.setForeground(SystemColor.desktop);
		btnSifreSifirla.setBackground(SystemColor.controlHighlight);
		btnSifreSifirla.setBounds(188, 210, 152, 34);
		paneKullaniciGirisi.add(btnSifreSifirla);
		
		JPanel panel = new JPanel();
		panel.setBounds(86, 53, 511, 247);
		paneKullaniciGirisi.add(panel);

		// BUTON EVENTLERI
		btnGirisYap.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(txtKullaniciAdi.getText().length()==0 || pfSifre.getText().length()==0) {
					mesaj.mesajYazdir("bosAlan");
					txtKullaniciAdi.setText(null);
					pfSifre.setText(null);
				}else {
					baglanti.baglan();
					String sorgu = "SELECT * FROM admintablo ";
					ResultSet rs = baglanti.listele(sorgu);
					String kullaniciAdi = txtKullaniciAdi.getText();
					String sifre = pfSifre.getText();
					Boolean giris = false;
					try {
						while(rs.next()) {
							if(rs.getString("kullanici_adi").equals(kullaniciAdi) && rs.getString("sifre").equals(sifre)) {
								giris = true;
								AnaEkranGUI anaEkranGUI = new AnaEkranGUI();
								anaEkranGUI.setVisible(true);
								dispose();
							}
						}
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					if(giris == false) {
						mesaj.mesajYazdir("Kullanýcý Adý Veya Þifre Hatalý!");
						txtKullaniciAdi.setText(null);
						pfSifre.setText(null);
					}
				}
			}
		});
		
		// SIFREMI UNUTTUM BUTONU EVENTI
		btnSifreSifirla.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				adminSifreUnuttumGUI.setVisible(true);
			}
		});
		
		

	}
}
