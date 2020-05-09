package ihm.GestionOeuvre;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import dao_project.Database_Connection;
import dao_project.OeuvreDAO;
import ihm.Authentification.MenuPrincipale;
import objet_Métier.Oeuvre;

public class GestionOeuvreF extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField Aj_Id;
	private JTextField Aj_auteur;
	private JTextField Aj_titre;
	private JTextField Aj_nbrExem;
	private JTextField Ch_Id;
	private JTextField Ch_titre;
	private JTextField Aj_Cat;
	private JTable Ach_table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GestionOeuvreF frame = new GestionOeuvreF();
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
	public GestionOeuvreF() {
		setResizable(false);
		setTitle("Gestion des Ouvrages");
		ImageIcon img = new ImageIcon("C:/Users/lktj/eclipse-workspace/TestProj/img/bibliotheque2.png");
		this.setIconImage(img.getImage());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		setBounds(100, 100, 835, 483);
		setBounds(100, 100, 836, 482);
		setLocationRelativeTo(getParent());
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		DefaultTableModel modelo = new DefaultTableModel(new Object[][] {}, new String[] { "Id", "Titre Oeuvre",
				"Auteur Oeuvre", "Nombre d'exemplaire", "Nombre disponible", "Categorie " });

		/*****************************
		 * Les buttons Ajouter Supprimer et modifer
		 **************************************************/

		JButton btnAjouter = new JButton("Ajouter");
		btnAjouter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					// appele du model
					OeuvreDAO userdao = new OeuvreDAO(Database_Connection.getConnection());
					Oeuvre oeu = new Oeuvre(Aj_titre.getText(), Aj_auteur.getText(),
							Integer.parseInt(Aj_nbrExem.getText()), Aj_Cat.getText());
					System.out.println(oeu);
					boolean result = userdao.add(oeu);

					OeuvreDAO userdao2 = new OeuvreDAO(Database_Connection.getConnection());
					ArrayList<Oeuvre> oeu2 = userdao2.find();

					System.out.println(result);
					if (result) {

						modelo.setRowCount(0);
						for (Oeuvre a : oeu2) {
							modelo.addRow(new Object[] { a.getIdOeuvre(), a.getTitreOeuvre(), a.getAuteurOeuvre(),
									a.getNbrExemplOeuvre(), a.getNbrDispOeuvre(), a.getCategorieOeuvre() });
						}

						JOptionPane.showMessageDialog(null, " Oeuvre ajouté avec succés !! ");

					} else {
						JOptionPane.showMessageDialog(null, " Ajout oeuvre echoué !! ");
					}
				} catch (Exception a) {
					a.getStackTrace();
				}
			}
		});
		btnAjouter.setBounds(214, 395, 89, 23);
		btnAjouter.setEnabled(false);
		contentPane.add(btnAjouter);

		JButton btnModifier = new JButton("Modifier");
		btnModifier.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				OeuvreDAO userdao = new OeuvreDAO(Database_Connection.getConnection());
				Oeuvre oeu = new Oeuvre(Integer.parseInt(Aj_Id.getText()), Aj_titre.getText(), Aj_auteur.getText(),
						Integer.parseInt(Aj_nbrExem.getText()), Aj_Cat.getText());
				boolean result = userdao.update(oeu);

				OeuvreDAO userdao2 = new OeuvreDAO(Database_Connection.getConnection());
				ArrayList<Oeuvre> oeu2 = userdao2.find();

				System.out.println(result);
				if (result) {

					modelo.setRowCount(0);

					for (Oeuvre a : oeu2) {
						modelo.addRow(new Object[] { a.getIdOeuvre(), a.getTitreOeuvre(), a.getAuteurOeuvre(),
								a.getNbrExemplOeuvre(), a.getNbrDispOeuvre(), a.getCategorieOeuvre() });
					}

					JOptionPane.showMessageDialog(null, " Modification Oeuvre avec succes !! ");
				} else {

					JOptionPane.showMessageDialog(null, " Modifaction oeuvre echoue !! ");
				}
			}
		});
		btnModifier.setBounds(115, 395, 89, 23);
		btnModifier.setEnabled(false);
		contentPane.add(btnModifier);

		JButton btnSupprimer = new JButton("Supprimer");
		btnSupprimer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				OeuvreDAO userdao = new OeuvreDAO(Database_Connection.getConnection());
				boolean result = userdao.delete(Integer.parseInt(Aj_Id.getText()));
				System.out.println("l'appel de la methode delete est fait correctement!");
				System.out.println(result);

				OeuvreDAO userdao2 = new OeuvreDAO(Database_Connection.getConnection());
				ArrayList<Oeuvre> oeu2 = userdao2.find();

				if (result) {

					modelo.setRowCount(0);

					for (Oeuvre a : oeu2) {
						modelo.addRow(new Object[] { a.getIdOeuvre(), a.getTitreOeuvre(), a.getAuteurOeuvre(),
								a.getNbrExemplOeuvre(), a.getNbrDispOeuvre(), a.getCategorieOeuvre() });
					}

					JOptionPane.showMessageDialog(null, " Oeuvre supprimé avec succes !! ");
				} else {
					System.out.println("malheur a echoue why !!");
//					int idExemplaire = userdao.findExemplaireEmprunte(Integer.parseInt(Aj_Id.getText()));
					JOptionPane.showMessageDialog(null,
							"La Suppression a echoué car l'oeuvre possede un exemplaire en etat d'emprunt !! ");
				}

			}
		});
		btnSupprimer.setBounds(10, 395, 95, 23);
		btnSupprimer.setEnabled(false);
		contentPane.add(btnSupprimer);

		/******************************************
		 * Fin Traitemnts
		 **************************************************************/

		/******************************************
		 * Ajouter Oeuvre
		 *****************************************************/
		JPanel AjouterOeuvre = new JPanel();
		AjouterOeuvre.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Ajouter Oeuvre",
				TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		AjouterOeuvre.setBounds(10, 63, 294, 327);
		contentPane.add(AjouterOeuvre);
		AjouterOeuvre.setLayout(null);

		JComboBox Aj_comboBox = new JComboBox();
		Aj_comboBox.setModel(new DefaultComboBoxModel(
				new String[] { "Choisir le cas d'utilisation", "Ajouter", "Modifier", "Supprimer" }));

		JLabel Aj_LeMode = new JLabel("Vous n'avez pas encore choisi le cas d'utilisation");
		Aj_LeMode.setFont(new Font("Tahoma", Font.PLAIN, 10));
		Aj_LeMode.setBounds(39, 63, 223, 14);
		AjouterOeuvre.add(Aj_LeMode);

		Aj_Id = new JTextField();
		Aj_Id.setBounds(169, 102, 86, 20);
		AjouterOeuvre.add(Aj_Id);
		Aj_Id.setColumns(10);

		JLabel lblIdOeuvre = new JLabel("Id          :");
		lblIdOeuvre.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblIdOeuvre.setBounds(22, 101, 137, 20);
		AjouterOeuvre.add(lblIdOeuvre);

		JButton Aj_findById = new JButton("find");
		Aj_findById.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				OeuvreDAO oeudao = new OeuvreDAO(Database_Connection.getConnection());
				Oeuvre oeu = oeudao.findById(Integer.parseInt(Aj_Id.getText()));
				if (oeu == null) {

					JOptionPane.showMessageDialog(null, " Oeuvre introuvé !! ");
				} else {
					Aj_auteur.setText(oeu.getAuteurOeuvre());
					Aj_titre.setText(oeu.getTitreOeuvre());
					Aj_nbrExem.setText(Integer.toString(oeu.getNbrExemplOeuvre()));
					Aj_Cat.setText(oeu.getCategorieOeuvre());
				}
			}

		});
		Aj_findById.setBounds(224, 130, 60, 18);
		Aj_findById.setEnabled(false);
		AjouterOeuvre.add(Aj_findById);

		JLabel lblAuteur = new JLabel("Auteur    :");
		lblAuteur.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblAuteur.setBounds(22, 157, 137, 17);
		AjouterOeuvre.add(lblAuteur);

		Aj_auteur = new JTextField();
		Aj_auteur.setBounds(169, 156, 86, 20);
		AjouterOeuvre.add(Aj_auteur);
		Aj_auteur.setColumns(10);

		JLabel lblNomOeuvre = new JLabel("Titre        :");
		lblNomOeuvre.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNomOeuvre.setBounds(22, 201, 137, 14);
		AjouterOeuvre.add(lblNomOeuvre);

		Aj_titre = new JTextField();
		Aj_titre.setBounds(169, 199, 86, 20);
		AjouterOeuvre.add(Aj_titre);
		Aj_titre.setColumns(10);

		JLabel lblNombreOeuvre = new JLabel("Nombre d'Exemplaires :");
		lblNombreOeuvre.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNombreOeuvre.setBounds(10, 245, 149, 14);
		AjouterOeuvre.add(lblNombreOeuvre);

		Aj_nbrExem = new JTextField();
		Aj_nbrExem.setBounds(169, 242, 86, 20);
		AjouterOeuvre.add(Aj_nbrExem);
		Aj_nbrExem.setColumns(10);

		JLabel lblCatgorie = new JLabel("Cat\u00E9gorie :");
		lblCatgorie.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblCatgorie.setBounds(22, 287, 137, 14);
		AjouterOeuvre.add(lblCatgorie);

		Aj_Cat = new JTextField();
		Aj_Cat.setBounds(169, 285, 86, 20);
		AjouterOeuvre.add(Aj_Cat);
		Aj_Cat.setColumns(10);

		Aj_comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String valueSelected = Aj_comboBox.getSelectedItem().toString();
				if (valueSelected == "Ajouter") {
					Aj_Id.setEnabled(false);
					Aj_titre.setEnabled(true);
					Aj_auteur.setEnabled(true);
					Aj_nbrExem.setEnabled(true);
					Aj_Cat.setEnabled(true);

					Aj_auteur.setText("");
					Aj_titre.setText("");
					Aj_nbrExem.setText("");
					Aj_Cat.setText("");

					Aj_findById.setEnabled(false);

					btnAjouter.setEnabled(true);
					btnModifier.setEnabled(false);
					btnSupprimer.setEnabled(false);
					Aj_LeMode.setText("Le mode du recherche : Ajouter");
				} else if (valueSelected == "Modifier") {
					Aj_Id.setEnabled(true);
					Aj_titre.setEnabled(true);
					Aj_auteur.setEnabled(true);
					Aj_nbrExem.setEnabled(true);
					Aj_Cat.setEnabled(true);

					Aj_findById.setEnabled(true);

					btnModifier.setEnabled(true);
					btnAjouter.setEnabled(false);
					btnSupprimer.setEnabled(false);
					Aj_LeMode.setText("Le mode du recherche : Modifier");
				} else if (valueSelected == "Supprimer") {
					Aj_Id.setEnabled(true);
					Aj_titre.setEnabled(false);
					Aj_auteur.setEnabled(false);
					Aj_nbrExem.setEnabled(false);
					Aj_Cat.setEnabled(false);

					Aj_findById.setEnabled(false);

					btnModifier.setEnabled(false);
					btnAjouter.setEnabled(false);
					btnSupprimer.setEnabled(true);
					Aj_LeMode.setText("Le mode du recherche : Supprimer");
				} else if (valueSelected == "Choisir le cas d'utilisation") {

					Aj_Id.setEnabled(false);
					Aj_titre.setEnabled(false);
					Aj_auteur.setEnabled(false);
					Aj_nbrExem.setEnabled(false);
					Aj_Cat.setEnabled(false);

					Aj_findById.setEnabled(false);

					btnModifier.setEnabled(false);
					btnAjouter.setEnabled(false);
					btnSupprimer.setEnabled(false);
					Aj_LeMode.setText("Vous n'avez pas encore choisi le cas d'utilisation");
				}
			}
		});
		Aj_comboBox.setBounds(57, 28, 177, 20);
		AjouterOeuvre.add(Aj_comboBox);

		/***************************************************************************************************************************/

		/***************************************************
		 * Chercher Oeuvre
		 ******************************************************/

		JPanel ChercherOeuvre = new JPanel();
		ChercherOeuvre.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Chercher Oeuvre",
				TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		ChercherOeuvre.setBounds(324, 11, 488, 129);
		contentPane.add(ChercherOeuvre);
		ChercherOeuvre.setLayout(null);

		JLabel Ch_lblLeMode = new JLabel("Vous n'avez pas encore choisi le mode de recherche");
		Ch_lblLeMode.setFont(new Font("Tahoma", Font.PLAIN, 10));
		Ch_lblLeMode.setBounds(34, 95, 289, 14);
		ChercherOeuvre.add(Ch_lblLeMode);

		JButton Ch_btnChercher = new JButton("Chercher");
		// actionPerformed pour le button chercher ligne 390
		Ch_btnChercher.setBounds(389, 96, 89, 23);
		Ch_btnChercher.setEnabled(false);
		ChercherOeuvre.add(Ch_btnChercher);

		JComboBox Ch_comboBox = new JComboBox();
		Ch_comboBox.setModel(
				new DefaultComboBoxModel(new String[] { "Choisir le mode recherche", "Identifiant", "Titre" }));
		Ch_comboBox.setBounds(155, 24, 196, 20);
		Ch_comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String valueSelected = Ch_comboBox.getSelectedItem().toString();
				if (valueSelected == "Identifiant") {
					Ch_titre.setText("");
					Ch_Id.setText("");
					Ch_titre.setEnabled(false);
					Ch_Id.setEnabled(true);
					Ch_btnChercher.setEnabled(true);
					Ch_lblLeMode.setText("Le mode du recherche : Par Identifiant");
				} else if (valueSelected == "Titre") {
					Ch_titre.setText("");
					Ch_Id.setText("");
					Ch_Id.setEnabled(false);
					Ch_titre.setEnabled(true);
					Ch_btnChercher.setEnabled(true);
					Ch_lblLeMode.setText("Le mode du recherche : Par Titre");
				} else if (valueSelected == "Choisir le mode recherche") {
					Ch_titre.setText("");
					Ch_Id.setText("");
					Ch_Id.setEnabled(false);
					Ch_titre.setEnabled(false);
					Ch_btnChercher.setEnabled(false);
					Ch_lblLeMode.setText("Vous n'avez pas encore choisi le mode de recherche");

				}
			}
		});
		ChercherOeuvre.add(Ch_comboBox);

		JLabel lblIdOeuvre_1 = new JLabel("Id Oeuvre :");
		lblIdOeuvre_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblIdOeuvre_1.setBounds(10, 64, 91, 20);
		ChercherOeuvre.add(lblIdOeuvre_1);

		Ch_Id = new JTextField();
		Ch_Id.setBounds(99, 65, 86, 20);
		ChercherOeuvre.add(Ch_Id);
		Ch_Id.setColumns(10);

		JLabel lblTitreDoeuvre = new JLabel("Titre d'Oeuvre :");
		lblTitreDoeuvre.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblTitreDoeuvre.setBounds(222, 61, 104, 26);
		ChercherOeuvre.add(lblTitreDoeuvre);

		Ch_titre = new JTextField();
		Ch_titre.setBounds(344, 65, 110, 20);
		ChercherOeuvre.add(Ch_titre);
		Ch_titre.setColumns(10);

		Ch_btnChercher.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				OeuvreDAO oeudao = new OeuvreDAO(Database_Connection.getConnection());
				Oeuvre oeu = new Oeuvre();
				String valueSelected = Ch_comboBox.getSelectedItem().toString();
				if (valueSelected == "Identifiant") {
					modelo.setRowCount(0);
					oeu = oeudao.findById(Integer.parseInt(Ch_Id.getText()));
					System.out.println(oeu);
					modelo.addRow(new Object[] { oeu.getIdOeuvre(), oeu.getTitreOeuvre(), oeu.getAuteurOeuvre(),
							oeu.getNbrExemplOeuvre(), oeu.getNbrDispOeuvre(), oeu.getCategorieOeuvre() });
				} else if (valueSelected == "Titre") {
					modelo.setRowCount(0);
					oeu = oeudao.findByTitle(Ch_titre.getText());
					System.out.println("l'import de la base des donnees fait !!");
					System.out.println(oeu);
					modelo.addRow(new Object[] { oeu.getIdOeuvre(), oeu.getTitreOeuvre(), oeu.getAuteurOeuvre(),
							oeu.getNbrExemplOeuvre(), oeu.getNbrDispOeuvre(), oeu.getCategorieOeuvre() });
				}
			}
		});

		/********************************************************************************************************************/

		/*******************************************
		 * Affichage Oeuvre
		 ********************************************************/

		JPanel AfficherOeuvre = new JPanel();
		AfficherOeuvre.setBorder(
				new TitledBorder(null, "Affichage Oeuvres", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		AfficherOeuvre.setBounds(324, 150, 488, 264);
		contentPane.add(AfficherOeuvre);
		AfficherOeuvre.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 69, 468, 184);
		AfficherOeuvre.add(scrollPane);

		Ach_table = new JTable();
		Ach_table.setModel(modelo);
		scrollPane.setViewportView(Ach_table);

		JButton btnAfficher = new JButton("Afficher");
		btnAfficher.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				modelo.setRowCount(0);
				OeuvreDAO oeudao = new OeuvreDAO(Database_Connection.getConnection());
				ArrayList<Oeuvre> oeu = oeudao.find();
				for (Oeuvre a : oeu) {
					modelo.addRow(new Object[] { a.getIdOeuvre(), a.getTitreOeuvre(), a.getAuteurOeuvre(),
							a.getNbrExemplOeuvre(), a.getNbrDispOeuvre(), a.getCategorieOeuvre() });
				}
			}
		});
		btnAfficher.setBounds(389, 35, 89, 23);
		AfficherOeuvre.add(btnAfficher);

		/********************************************************************************************************************/

		JLabel lblGestionOeuvre = new JLabel("Gestion Oeuvre ");
		lblGestionOeuvre.setForeground(Color.PINK);
		lblGestionOeuvre.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblGestionOeuvre.setBounds(54, 11, 167, 41);
		contentPane.add(lblGestionOeuvre);

		JButton btnRetour = new JButton("Retour");
		btnRetour.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MenuPrincipale menu = new MenuPrincipale();
				menu.setVisible(true);
				dispose();
			}
		});
		btnRetour.setBounds(723, 420, 89, 23);
		contentPane.add(btnRetour);
	}
}
