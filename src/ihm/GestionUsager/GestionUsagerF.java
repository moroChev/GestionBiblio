package ihm.GestionUsager;

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
import dao_project.UsagerDAO;
import ihm.Authentification.MenuPrincipale;
import objet_Métier.Usager;

public class GestionUsagerF extends JFrame {

	private JPanel contentPane;
	private JTextField Aj_nom;
	private JTextField Aj_prenom;
	private JTextField Aj_CIN;
	private JTextField Aj_CNE;
	private JTable table;
	private JTextField Ch_nom;
	private JTextField Ch_prenom;
	private JTextField Ch_id;
	private JTextField Aj_Id;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GestionUsagerF frame = new GestionUsagerF();
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
	public GestionUsagerF() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 836, 482);
		setTitle("Gestion des adhérents");
		ImageIcon img = new ImageIcon("C:/Users/lktj/eclipse-workspace/TestProj/img/bibliotheque2.png");
		this.setIconImage(img.getImage());
		setLocationRelativeTo(getParent());
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblGestionEmprunt = new JLabel("Gestion Adh\u00E9rent ");
		lblGestionEmprunt.setForeground(Color.PINK);
		lblGestionEmprunt.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblGestionEmprunt.setBounds(53, 11, 185, 41);
		contentPane.add(lblGestionEmprunt);

		DefaultTableModel modelo = new DefaultTableModel(new Object[][] {},
				new String[] { "Id Usager", "Nom", "Prenom", "CIN", "CNE" });

		/****************************************
		 * Afficher Panel
		 ****************************************/

		JPanel Afficher_Panel = new JPanel();
		Afficher_Panel.setBorder(
				new TitledBorder(null, "Liste des Usagers", TitledBorder.LEFT, TitledBorder.TOP, null, Color.BLACK));
		Afficher_Panel.setBounds(324, 150, 488, 264);
		contentPane.add(Afficher_Panel);
		Afficher_Panel.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 69, 468, 184);
		Afficher_Panel.add(scrollPane);

		table = new JTable();
		scrollPane.setViewportView(table);
		table.setModel(modelo);

		JButton Aff_btnAfficher = new JButton("Afficher");
		Aff_btnAfficher.setFont(new Font("Tahoma", Font.BOLD, 13));
		Aff_btnAfficher.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				modelo.setRowCount(0);
				UsagerDAO userDAO = new UsagerDAO(Database_Connection.getConnection());
				ArrayList<Usager> user = userDAO.find();
				if (user == null) {
					System.out.println("nulllll ");
				}
				for (Usager a : user) {
					modelo.addRow(new Object[] { a.getIdUsager(), a.getNomUsager(), a.getPrenomUsager(),
							a.getCINUsager(), a.getCNEUsager() });
				}
				System.out.println(" le nombre des lignes : " + modelo.getRowCount());
				System.out.println("_______________________________________________________________________");

			}
		});
		Aff_btnAfficher.setBounds(389, 35, 89, 23);
		Afficher_Panel.add(Aff_btnAfficher);

		/*********************************
		 * Fin Afficher Panel
		 ****************************************/

		/******************************
		 * Les buttons ajouter modifier et supprimer
		 *********************/

		JButton btnModifier = new JButton("modifier");
		btnModifier.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnModifier.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UsagerDAO usagerdao = new UsagerDAO(Database_Connection.getConnection());
				Usager user = new Usager(Integer.parseInt(Aj_Id.getText()), Aj_nom.getText(), Aj_prenom.getText(),
						Aj_CIN.getText(), Aj_CNE.getText());

				boolean result = usagerdao.update(user);
				UsagerDAO user2dao = new UsagerDAO(Database_Connection.getConnection());
				ArrayList<Usager> user2 = user2dao.find();
				System.out.println("nulllll waaaaaaaaaaaaaaaaayli");

				
				if (result) {

					/********** L'affichage du résultat en table *****************/

					System.out.println("else donc ... ");
					modelo.setRowCount(0);
					System.out.println("Le nombre des lignes 0 ... ");

					for (Usager a : user2) {
						modelo.addRow(new Object[] { a.getIdUsager(), a.getNomUsager(), a.getPrenomUsager(),
								a.getCINUsager(), a.getCNEUsager() });
					}
					System.out.println("Affichage modier fin ... ");

					/*************************************************************/

					JOptionPane.showMessageDialog(null, "Adhérents Modifie Avec succées");
				} else {
					JOptionPane.showMessageDialog(null, "la modification n'est pas faite");
				}
			}
		});
		btnModifier.setBounds(115, 395, 89, 23);
		btnModifier.setEnabled(false);
		contentPane.add(btnModifier);

		JButton btnSupprimer = new JButton("supprimer");
		btnSupprimer.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnSupprimer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UsagerDAO usagerdao = new UsagerDAO(Database_Connection.getConnection());
				boolean result = usagerdao.delete(Integer.parseInt(Aj_Id.getText()));
				System.out.println("L'ID supprimé  : " + Integer.parseInt(Aj_Id.getText()));

				UsagerDAO userdao = new UsagerDAO(Database_Connection.getConnection());
				ArrayList<Usager> user = userdao.find();
				System.out.println("nulllll waaaaaaaaaaaaaaaaayli");

				System.out.println(result);
				if (result) {
					JOptionPane.showMessageDialog(null, "Adhérents Supprimé Avec succées");

					/********** L'affichage du résultat en table *****************/

					if (user == null) {
						System.out.println("nulllll ");
					} else {
						System.out.println("else donc ... ");
						modelo.setRowCount(0);
						for (Usager a : user) {
							modelo.addRow(new Object[] { a.getIdUsager(), a.getNomUsager(), a.getPrenomUsager(),
									a.getCINUsager(), a.getCNEUsager() });
						}
					}
					/*************************************************************/

				} else {
					JOptionPane.showMessageDialog(null, "l'usager à déja un emprunt");
				}
				Aj_Id.setText("");

			}
		});
		btnSupprimer.setBounds(10, 395, 95, 23);
		btnSupprimer.setEnabled(false);
		contentPane.add(btnSupprimer);

		JButton btnAjouter = new JButton("ajouter");
		btnAjouter.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnAjouter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					UsagerDAO userdao = new UsagerDAO(Database_Connection.getConnection());
					Usager user = new Usager(Aj_nom.getText(), Aj_prenom.getText(), Aj_CIN.getText(), Aj_CNE.getText());
					boolean IsAdded = userdao.add(user);

					UsagerDAO user2dao = new UsagerDAO(Database_Connection.getConnection());
					ArrayList<Usager> user2 = user2dao.find();

					if (IsAdded == true) {

						/********** L'affichage du résultat en table *****************/

						System.out.println("else donc ... ");
						modelo.setRowCount(0);
						for (Usager a : user2) {
							modelo.addRow(new Object[] { a.getIdUsager(), a.getNomUsager(), a.getPrenomUsager(),
									a.getCINUsager(), a.getCNEUsager() });
						}

						/*************************************************************/

						JOptionPane.showMessageDialog(null, "usager ajouté avec succés");
					} else {
						JOptionPane.showMessageDialog(null, "ajout usager échoué ");
					}
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}
		});
		btnAjouter.setBounds(214, 395, 89, 23);
		btnAjouter.setEnabled(false);
		contentPane.add(btnAjouter);

		/********************************
		 * Fin triattements buttons
		 *******************************************/

		/***********************************
		 * Ajouter Panel
		 ************************************/

		JPanel Ajouter_Panel = new JPanel();
		Ajouter_Panel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Ajouter Usager",
				TitledBorder.LEFT, TitledBorder.TOP, null, new Color(0, 0, 0)));
		Ajouter_Panel.setBounds(10, 51, 294, 330);
		contentPane.add(Ajouter_Panel);
		Ajouter_Panel.setLayout(null);

		JComboBox Aj_comboBox = new JComboBox();
		Aj_comboBox.setModel(new DefaultComboBoxModel(
				new String[] { "Choisir le cas d'utilisation", "Ajouter", "Modifier", "Supprimer" }));

		JLabel Aj_LeMode = new JLabel("Vous n'avez pas encore choisi le cas d'utilisation");
		Aj_LeMode.setFont(new Font("Tahoma", Font.PLAIN, 10));
		Aj_LeMode.setBounds(39, 63, 223, 14);
		Ajouter_Panel.add(Aj_LeMode);

		Aj_Id = new JTextField();
		Aj_Id.setBounds(169, 102, 86, 20);
		Ajouter_Panel.add(Aj_Id);
		Aj_Id.setColumns(10);

		JLabel Aj_lblId = new JLabel("        Identifiant     :");
		Aj_lblId.setFont(new Font("Tahoma", Font.BOLD, 13));
		Aj_lblId.setBounds(10, 101, 149, 20);
		Ajouter_Panel.add(Aj_lblId);

		JButton Aj_findById = new JButton("find");
		Aj_findById.setBounds(224, 130, 60, 18);
		Aj_findById.setEnabled(false);
		Ajouter_Panel.add(Aj_findById);

		JLabel Aj_lblNom = new JLabel("  Nom de famille           :");
		Aj_lblNom.setFont(new Font("Tahoma", Font.BOLD, 12));
		Aj_lblNom.setBounds(10, 157, 149, 17);
		Ajouter_Panel.add(Aj_lblNom);

		Aj_nom = new JTextField();
		Aj_nom.setBounds(169, 156, 86, 20);
		Ajouter_Panel.add(Aj_nom);
		Aj_nom.setColumns(10);

		JLabel Aj_lblPrenom = new JLabel("        Prenom               :");
		Aj_lblPrenom.setFont(new Font("Tahoma", Font.BOLD, 12));
		Aj_lblPrenom.setBounds(10, 201, 149, 14);
		Ajouter_Panel.add(Aj_lblPrenom);

		Aj_prenom = new JTextField();
		Aj_prenom.setBounds(169, 199, 86, 20);
		Ajouter_Panel.add(Aj_prenom);
		Aj_prenom.setColumns(10);

		JLabel Aj_lblCIN = new JLabel("Carte d'Identit\u00E9 National :");
		Aj_lblCIN.setFont(new Font("Tahoma", Font.BOLD, 11));
		Aj_lblCIN.setBounds(10, 245, 149, 14);
		Ajouter_Panel.add(Aj_lblCIN);

		Aj_CIN = new JTextField();
		Aj_CIN.setBounds(169, 242, 86, 20);
		Ajouter_Panel.add(Aj_CIN);
		Aj_CIN.setColumns(10);

		JLabel Aj_lblCNE = new JLabel("Code National d'Etudiant :");
		Aj_lblCNE.setFont(new Font("Tahoma", Font.BOLD, 11));
		Aj_lblCNE.setBounds(10, 287, 149, 14);
		Ajouter_Panel.add(Aj_lblCNE);

		Aj_CNE = new JTextField();
		Aj_CNE.setBounds(169, 285, 86, 20);
		Ajouter_Panel.add(Aj_CNE);
		Aj_CNE.setColumns(10);

		Aj_findById.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UsagerDAO userdao = new UsagerDAO(Database_Connection.getConnection());
				Usager oeu = userdao.findById(Integer.parseInt(Aj_Id.getText()));
				if (oeu.getIdUsager() == 0) {

					JOptionPane.showMessageDialog(null, " Oeuvre introuvé !! ");
				} else {
					Aj_nom.setText(oeu.getNomUsager());
					Aj_prenom.setText(oeu.getPrenomUsager());
					Aj_CNE.setText(oeu.getCNEUsager());
					Aj_CIN.setText(oeu.getCINUsager());
				}
			}

		});

		Aj_comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String valueSelected = Aj_comboBox.getSelectedItem().toString();
				if (valueSelected == "Ajouter") {
					Aj_Id.setEnabled(false);
					Aj_nom.setEnabled(true);
					Aj_prenom.setEnabled(true);
					Aj_CIN.setEnabled(true);
					Aj_CNE.setEnabled(true);

					Aj_nom.setText("");
					Aj_prenom.setText("");
					Aj_CIN.setText("");
					Aj_CNE.setText("");

					Aj_findById.setEnabled(false);

					btnAjouter.setEnabled(true);
					btnModifier.setEnabled(false);
					btnSupprimer.setEnabled(false);
					Aj_LeMode.setText("Le mode du recherche : Ajouter");
				} else if (valueSelected == "Modifier") {
					Aj_Id.setEnabled(true);
					Aj_nom.setEnabled(true);
					Aj_prenom.setEnabled(true);
					Aj_CNE.setEnabled(true);
					Aj_CIN.setEnabled(true);

					Aj_findById.setEnabled(true);
//
					btnModifier.setEnabled(true);
					btnAjouter.setEnabled(false);
					btnSupprimer.setEnabled(false);
					Aj_LeMode.setText("Le mode du recherche : Modifier");
				} else if (valueSelected == "Supprimer") {
					Aj_Id.setEnabled(true);
					Aj_nom.setEnabled(false);
					Aj_prenom.setEnabled(false);
					Aj_CNE.setEnabled(false);
					Aj_CIN.setEnabled(false);

					Aj_findById.setEnabled(false);

					btnModifier.setEnabled(false);
					btnAjouter.setEnabled(false);
					btnSupprimer.setEnabled(true);
					Aj_LeMode.setText("Le mode du recherche : Supprimer");
				} else if (valueSelected == "Choisir le cas d'utilisation") {

					Aj_Id.setEnabled(false);
					Aj_nom.setEnabled(false);
					Aj_prenom.setEnabled(false);
					Aj_CIN.setEnabled(false);
					Aj_CNE.setEnabled(false);

					Aj_findById.setEnabled(false);

					btnModifier.setEnabled(false);
					btnAjouter.setEnabled(false);
					btnSupprimer.setEnabled(false);
					Aj_LeMode.setText("Vous n'avez pas encore choisi le cas d'utilisation");
				}
			}
		});
		Aj_comboBox.setBounds(57, 28, 177, 20);
		Ajouter_Panel.add(Aj_comboBox);

		/*********************************
		 * Fin Ajouter Panel
		 ****************************************/

		/**************************
		 * chercher Panel
		 **********************************************/

		JPanel Chercher_Panel = new JPanel();
		Chercher_Panel.setBorder(
				new TitledBorder(null, "Rechercher", TitledBorder.LEFT, TitledBorder.TOP, null, Color.BLACK));
		Chercher_Panel.setBounds(324, 11, 488, 129);
		contentPane.add(Chercher_Panel);
		Chercher_Panel.setLayout(null);

		JLabel Ch_lblLeMode = new JLabel("Vous n'avez pas encore choisi le mode de recherche");
		Ch_lblLeMode.setFont(new Font("Tahoma", Font.PLAIN, 10));
		Ch_lblLeMode.setBounds(43, 104, 289, 14);
		Chercher_Panel.add(Ch_lblLeMode);

		JLabel Ch_lblId = new JLabel("Id Adhérent    ");
		Ch_lblId.setFont(new Font("Tahoma", Font.BOLD, 12));
		Ch_lblId.setBounds(22, 26, 93, 14);
		Chercher_Panel.add(Ch_lblId);

		Ch_id = new JTextField();
		Ch_id.setBounds(132, 24, 86, 20);
		Ch_id.setEnabled(false);
		Chercher_Panel.add(Ch_id);
		Ch_id.setColumns(10);

		Ch_nom = new JTextField();
		Ch_nom.setBounds(132, 68, 86, 20);
		Chercher_Panel.add(Ch_nom);
		Ch_nom.setColumns(10);

		JLabel Ch_lblNomAdherent = new JLabel("Nom Adh\u00E9rent  :");
		Ch_lblNomAdherent.setFont(new Font("Tahoma", Font.BOLD, 12));
		Ch_lblNomAdherent.setBounds(22, 69, 104, 17);
		Chercher_Panel.add(Ch_lblNomAdherent);

		JLabel lblIdAdherent = new JLabel("Prenom Adherent   :");
		lblIdAdherent.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblIdAdherent.setBounds(244, 69, 130, 17);
		Chercher_Panel.add(lblIdAdherent);

		Ch_prenom = new JTextField();
		Ch_prenom.setBounds(379, 68, 86, 20);
		Ch_prenom.setEnabled(false);
		Ch_nom.setEnabled(false);
		Chercher_Panel.add(Ch_prenom);
		Ch_prenom.setColumns(10);

		JButton Ch_Chercher = new JButton("Chercher");
		Ch_Chercher.setFont(new Font("Tahoma", Font.BOLD, 11));

		JComboBox Ch_comboBox = new JComboBox(new DefaultComboBoxModel(
				new String[] { "Choisir le mode du recherche", "Par ID Adherent", "Par Nom Adherent" }));
		Ch_comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String valueSelected = Ch_comboBox.getSelectedItem().toString();
				if (valueSelected == "Par Nom Adherent") {
					Ch_lblId.setText("Id");
					Ch_id.setEnabled(false);
					Ch_id.setText("");
					Ch_prenom.setText("");
					Ch_nom.setText("");
					Ch_nom.setEnabled(true);
					Ch_prenom.setEnabled(true);
					Ch_Chercher.setEnabled(true);
					Ch_lblLeMode.setText("Le mode du recherche : Par Nom Adherent");
				} else if (valueSelected == "Par ID Adherent") {
					Ch_lblId.setText("Id Adhérent");
					Ch_id.setEnabled(true);
					Ch_prenom.setText("");
					Ch_nom.setText("");
					Ch_prenom.setEnabled(false);
					Ch_nom.setEnabled(false);
					Ch_Chercher.setEnabled(true);
					Ch_lblLeMode.setText("Le mode du recherche : Par ID Adherent");

				} else if (valueSelected == "Choisir le mode du recherche") {
					Ch_lblId.setText("Id");
					Ch_id.setEnabled(false);
					Ch_id.setText("");
					Ch_prenom.setText("");
					Ch_nom.setText("");
					Ch_nom.setEnabled(false);
					Ch_prenom.setEnabled(false);
					Ch_Chercher.setEnabled(false);
					Ch_lblLeMode.setText("Le mode du recherche : Aucun");
				}
			}
		});
		Ch_comboBox.setBounds(284, 24, 194, 20);
		Chercher_Panel.add(Ch_comboBox);

		Ch_Chercher.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				modelo.setRowCount(0);
				UsagerDAO userDAO = new UsagerDAO(Database_Connection.getConnection());
				String selectedValue = Ch_comboBox.getSelectedItem().toString();
				if (selectedValue == "Par Nom Adherent") {
					Usager r = userDAO.findByName(Ch_nom.getText(), Ch_prenom.getText());

					if (r.getIdUsager() != 0) {
						modelo.addRow(new Object[] { r.getIdUsager(), r.getNomUsager(), r.getPrenomUsager(),
								r.getCINUsager(), r.getCNEUsager() });
					}
					if (r.getIdUsager() == 0) {
						JOptionPane.showMessageDialog(null, " Usager n'existe pas !! ");
					}

				} else if (selectedValue == "Par ID Adherent") {
					Usager r = userDAO.findById(Integer.parseInt(Ch_id.getText()));

					if (r != null) {
						modelo.addRow(new Object[] { r.getIdUsager(), r.getNomUsager(), r.getPrenomUsager(),
								r.getCINUsager(), r.getCNEUsager() });
					}
					if (r.getIdUsager() == 0) {
						JOptionPane.showMessageDialog(null, " Usager n'existe pas !! ");
					}
				}
			}
		});
		Ch_Chercher.setBounds(389, 95, 89, 23);
		Ch_Chercher.setEnabled(false);
		Chercher_Panel.add(Ch_Chercher);

		JLabel label = new JLabel(":");
		label.setFont(new Font("Tahoma", Font.BOLD, 12));
		label.setBounds(122, 27, 4, 14);
		Chercher_Panel.add(label);

		/***************************************
		 * Fin Ajouter Panel
		 ****************************************/

	

		JButton btnRetour = new JButton("Retour");
		btnRetour.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MenuPrincipale menu = new MenuPrincipale();
				menu.setVisible(true);
				dispose();
			}
		});
		btnRetour.setBounds(731, 419, 89, 23);
		contentPane.add(btnRetour);

	}
}
