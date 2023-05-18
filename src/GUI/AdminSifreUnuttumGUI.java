package GUI;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

import YardimciSinif.*;

public class AdminSifreUnuttumGUI extends JFrame {

	private JPanel contentPane;
	private JTextField txtKullaniciAdi;
	private JTextField txtKurtarmaKodu;
	private JPasswordField pfYeniSifre;
	
	public boolean islemGerceklestimi;
	
	// NESNELER
	Mesajlar mesaj = new Mesajlar();
	VeritabaniBaglantilari baglanti = new VeritabaniBaglantilari();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AdminSifreUnuttumGUI frame = new AdminSifreUnuttumGUI();
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
	public AdminSifreUnuttumGUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(790, 330, 509, 371);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblKullaniciAdi = new JLabel("KULLANICI ADI:");
		lblKullaniciAdi.setFont(new Font("Yu Gothic UI", Font.PLAIN, 16));
		lblKullaniciAdi.setBounds(67, 130, 139, 29);
		contentPane.add(lblKullaniciAdi);
		
		txtKullaniciAdi = new JTextField();
		txtKullaniciAdi.setFont(new Font("Yu Gothic UI", Font.PLAIN, 19));
		txtKullaniciAdi.setBounds(255, 130, 165, 29);
		contentPane.add(txtKullaniciAdi);
		txtKullaniciAdi.setColumns(10);
		
		JLabel lblYeniSifre = new JLabel("YEN\u0130 \u015E\u0130FRE:");
		lblYeniSifre.setFont(new Font("Yu Gothic UI", Font.PLAIN, 16));
		lblYeniSifre.setBounds(67, 190, 139, 29);
		contentPane.add(lblYeniSifre);
		
		JLabel lblKurtarmaKodu = new JLabel("\u015E\u0130FRE SIFIRLAMA KODU:");
		lblKurtarmaKodu.setFont(new Font("Yu Gothic UI", Font.PLAIN, 16));
		lblKurtarmaKodu.setBounds(67, 70, 178, 29);
		contentPane.add(lblKurtarmaKodu);
		
		txtKurtarmaKodu = new JTextField();
		txtKurtarmaKodu.setFont(new Font("Yu Gothic UI", Font.PLAIN, 19));
		txtKurtarmaKodu.setColumns(10);
		txtKurtarmaKodu.setBounds(255, 73, 165, 29);
		contentPane.add(txtKurtarmaKodu);
		
		pfYeniSifre = new JPasswordField();
		pfYeniSifre.setFont(new Font("Yu Gothic UI", Font.PLAIN, 19));
		pfYeniSifre.setBounds(255, 193, 165, 29);
		contentPane.add(pfYeniSifre);
		
		JButton btnSifreDegistir = new JButton("\u015E\u0130FRE G\u00DCNCELLE");
		btnSifreDegistir.setFont(new Font("Yu Gothic UI", Font.PLAIN, 16));
		btnSifreDegistir.setBounds(156, 255, 159, 29);
		contentPane.add(btnSifreDegistir);
		
		JButton btnNewButton = new JButton("\u00C7\u0131k\u0131\u015F");
		btnNewButton.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		btnNewButton.setBounds(414, 11, 69, 23);
		contentPane.add(btnNewButton);
		
		
		// EVENTLER
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		
		
		btnSifreDegistir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(txtKurtarmaKodu.getText().length()==0 || txtKullaniciAdi.getText().length()==0 || pfYeniSifre.getText().length()==0) {
					mesaj.mesajYazdir("bosAlan");
					txtKurtarmaKodu.setText(null);
					txtKullaniciAdi.setText(null);
					pfYeniSifre.setText(null);
				}else {
					try {
						islemGerceklestimi = false;
						String sorgu = "SELECT * FROM admintablo ";
						baglanti.baglan();
						ResultSet rs = baglanti.listele(sorgu);
						while(rs.next()) {
							if(rs.getString("kurtarma_kodu").equals(txtKurtarmaKodu.getText()) && rs.getString("kullanici_adi").equals(txtKullaniciAdi.getText())) {
								String sorgu2 = "UPDATE admintablo SET sifre = '"+pfYeniSifre.getText()+"' WHERE kurtarma_kodu = '"+txtKurtarmaKodu.getText()+"' AND kullanici_adi = '"+txtKullaniciAdi.getText()+"'";
								if(baglanti.veriTabaniVeriGuncelle(sorgu2)) {
									mesaj.mesajYazdir("onay");
									txtKurtarmaKodu.setText(null);
									txtKullaniciAdi.setText(null);
									pfYeniSifre.setText(null);
									dispose();
									islemGerceklestimi = true;
								}
							}
						}
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
					if(islemGerceklestimi == false) {
						mesaj.mesajYazdir("Hata Þifre Güncellenemedi. Lütfen Bilgileri Doðru Girdiðinizden Emin Olun.\nEðer Bilgilerinizde Hata Olmadýðýný Düþünüyorsanýz Lütfen Yöneticinize Baþvurun.");
						txtKurtarmaKodu.setText(null);
						txtKullaniciAdi.setText(null);
						pfYeniSifre.setText(null);
					}
					
				}
			}
		});
		
		
		
	}
}
