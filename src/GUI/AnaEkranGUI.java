package GUI;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import java.awt.Color;
import java.awt.SystemColor;

public class AnaEkranGUI extends JFrame {

	private JPanel paneAnaEkran;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AnaEkranGUI frame = new AnaEkranGUI();
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
	public AnaEkranGUI() {
		setTitle("ANA EKRAN | ");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(675, 320, 700, 415);
		paneAnaEkran = new JPanel();
		paneAnaEkran.setBackground(Color.LIGHT_GRAY);
		paneAnaEkran.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(paneAnaEkran);
		paneAnaEkran.setLayout(null);
		
		JButton btnAracYonetimi = new JButton("ARA\u00C7 Y\u00D6NET\u0130M\u0130");
		btnAracYonetimi.setBackground(SystemColor.inactiveCaption);
		btnAracYonetimi.setFont(new Font("Yu Gothic UI", Font.PLAIN, 17));
		btnAracYonetimi.setBounds(32, 145, 196, 102);
		paneAnaEkran.add(btnAracYonetimi);
		
		JButton btnMusteriIslemleri = new JButton("M\u00DC\u015ETER\u0130 \u0130\u015ELEMLER\u0130");
		btnMusteriIslemleri.setBackground(SystemColor.inactiveCaption);
		btnMusteriIslemleri.setFont(new Font("Yu Gothic UI", Font.PLAIN, 17));
		btnMusteriIslemleri.setBounds(246, 145, 200, 102);
		paneAnaEkran.add(btnMusteriIslemleri);
		
		JButton btnAdminPaneli = new JButton("ADM\u0130N PANEL\u0130");
		btnAdminPaneli.setBackground(SystemColor.inactiveCaption);
		btnAdminPaneli.setFont(new Font("Yu Gothic UI", Font.PLAIN, 17));
		btnAdminPaneli.setBounds(463, 145, 189, 102);
		paneAnaEkran.add(btnAdminPaneli);
		
		JLabel lblNewLabel = new JLabel("Galeri Otomasyonuna Hos Geldiniz");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel.setBounds(82, 39, 535, 34);
		paneAnaEkran.add(lblNewLabel);
		
		JLabel lblLutfenYapmakIstedginiz = new JLabel("Lutfen Yapmak Istediginiz Islemi Seciniz...");
		lblLutfenYapmakIstedginiz.setHorizontalAlignment(SwingConstants.CENTER);
		lblLutfenYapmakIstedginiz.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblLutfenYapmakIstedginiz.setBounds(82, 305, 535, 34);
		paneAnaEkran.add(lblLutfenYapmakIstedginiz);
		
		JButton btnCikis = new JButton("\u00C7IKI\u015E");
		btnCikis.setFont(new Font("Yu Gothic UI", Font.PLAIN, 18));
		btnCikis.setBounds(563, 24, 89, 23);
		paneAnaEkran.add(btnCikis);
		
		// EVENTLER
		btnAracYonetimi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AracYonetimGUI aracYonetim = new AracYonetimGUI();
				aracYonetim.setVisible(true);
				dispose();
				
			}
		});
		
		btnMusteriIslemleri.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MusteriIliskileriGUI musteriIliskileriyonetim = new MusteriIliskileriGUI();
				musteriIliskileriyonetim.setVisible(true);
				dispose();
			}
		});
		
		
		btnAdminPaneli.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AdminIslemleriGUI kullaniciIslemleri = new AdminIslemleriGUI();
				kullaniciIslemleri.setVisible(true);
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
}
