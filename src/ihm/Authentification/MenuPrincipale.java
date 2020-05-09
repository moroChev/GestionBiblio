package ihm.Authentification;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import ihm.GestionEmprunt.GestionEmpruntF;
import ihm.GestionExemplaire.GestionExemplaire;
import ihm.GestionOeuvre.GestionOeuvreF;
import ihm.GestionUsager.GestionUsagerF;

public class MenuPrincipale extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private imagePan pan;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MenuPrincipale frame = new MenuPrincipale();
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
	public MenuPrincipale() {
		this.setSize(700,500);
		this.setTitle("Bienvnue dans le menu principal");
		this.setLocationRelativeTo(getParent());
		ImageIcon img = new ImageIcon("C:/Users/lktj/eclipse-workspace/TestProj/img/bibliotheque2.png");
		this.setIconImage(img.getImage());
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(true);
	  	pan=new imagePan();
		this.setContentPane(pan);
		pan.setLayout(null);
		
//		List a = new ArrayList<Emprunt>();
		
		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		panel.setBackground(new Color(205, 133, 63));
		panel.setBounds(416, 0, 268, 461);
		pan.add(panel);
		panel.setLayout(null);
		
		JButton gestion_adherant = new JButton("Gestion Adh\u00E9rent");
		gestion_adherant.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				GestionUsagerF gestionUsers = new GestionUsagerF();
				gestionUsers.setVisible(true);
				dispose();
			}
		});
		gestion_adherant.setBounds(52, 30, 164, 45);
		gestion_adherant.setFont(new Font("Tahoma", Font.BOLD, 13));
		panel.add(gestion_adherant);
		
		JButton gestion_oeuvre = new JButton("Gestion Oeuvre");
		gestion_oeuvre.setFont(new Font("Tahoma", Font.BOLD, 13));
		gestion_oeuvre.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				GestionOeuvreF gestionOeuvre = new GestionOeuvreF();
				gestionOeuvre.setVisible(true);
				dispose();
			}
		});
		gestion_oeuvre.setBounds(52, 120, 164, 45);
		panel.add(gestion_oeuvre);
		
		JButton gestion_exemplaire = new JButton("Gestion Exemplaire");
		gestion_exemplaire.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GestionExemplaire exemplaire = new GestionExemplaire();
				exemplaire.setVisible(true);
				dispose();
			}
		});
		gestion_exemplaire.setFont(new Font("Tahoma", Font.BOLD, 13));
		gestion_exemplaire.setBounds(52, 210, 164, 45);
		panel.add(gestion_exemplaire);
		
		JButton gestion_emprunt = new JButton("Gestion Emprunt");
		gestion_emprunt.setFont(new Font("Tahoma", Font.BOLD, 13));
		gestion_emprunt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GestionEmpruntF emprunt = new GestionEmpruntF();
				emprunt.setVisible(true);
				dispose();
			}
		});
		gestion_emprunt.setBounds(52, 300, 164, 45);
		panel.add(gestion_emprunt);
		
		JButton ajouter_admin = new JButton("Ajouter Admin");
		ajouter_admin.setFont(new Font("Tahoma", Font.BOLD, 13));
		ajouter_admin.setBounds(52, 390, 164, 45);
		panel.add(ajouter_admin);
		
		JPanel title_Panel = new JPanel();
		title_Panel.setBorder(new LineBorder(new Color(0, 0, 0), 3));
		title_Panel.setBackground(new Color(205, 133, 63));
		title_Panel.setBounds(10, 274, 396, 125);
		pan.add(title_Panel);
		title_Panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Bienvenue dans Biblioth\u00E9que ENSAH");
		lblNewLabel.setBounds(21, 49, 369, 25);
		title_Panel.add(lblNewLabel);
		lblNewLabel.setBackground(new Color(240, 248, 255));
		lblNewLabel.setForeground(new Color(0, 0, 0));
		lblNewLabel.setFont(new Font("Century Gothic", Font.BOLD, 20));
	}
}
