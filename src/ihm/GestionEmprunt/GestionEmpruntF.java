package ihm.GestionEmprunt;

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
import dao_project.EmpruntDAO;
import dao_project.ExemplaireDAO;
import dao_project.UsagerDAO;
import ihm.Authentification.MenuPrincipale;
import objet_Métier.Emprunt;
import objet_Métier.Exemplaire;
import objet_Métier.Usager;

public class GestionEmpruntF extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField Aj_idExemplaire;
	private JTextField Aj_idAdherent;
	private JTextField Aj_dateRetour;
	private JTextField Aj_dateEmprunt;
	private JTextField Ch_nom;
	private JTextField Ch_prenom;
	private JTable table;
	private JTextField Ch_txtId;
	private JTextField Aj_IdEmprunt;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GestionEmpruntF frame = new GestionEmpruntF();
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

	/**
	 * Travail à faire : lors d'ajout, message d'errer si l'exemplaire manque ou
	 * adhérent manque lors du modification , find byId emprunt
	 * 
	 * Aussi voir le probléme d'incrémentation lors de l'ajout
	 * 
	 * 
	 */

	public GestionEmpruntF() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 836, 482);
		ImageIcon img = new ImageIcon("C:/Users/lktj/eclipse-workspace/TestProj/img/bibliotheque2.png");
		this.setIconImage(img.getImage());
		setLocationRelativeTo(getParent());
		setTitle("Gestion Emprunt");
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblGestionEmprunt = new JLabel("Gestion Emprunt ");
		lblGestionEmprunt.setForeground(Color.PINK);
		lblGestionEmprunt.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblGestionEmprunt.setBounds(53, 11, 185, 41);
		contentPane.add(lblGestionEmprunt);

		DefaultTableModel modelEm = new DefaultTableModel(new Object[][] {},
				new String[] { "id Emprunt", "id Exemplaire", "Titre", "Nom", "Prenom", "date d'emprunt",
						"Date Retour Th\u00E9orique", "DateRetour Effective" });

		/*******************************************************************************************************/

		/****************************************************
		 * Ajouter Emprunt
		 ******************************************************/
		JPanel AjouterEmprunt = new JPanel();
		AjouterEmprunt.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Ajouter Emprunt",
				TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		AjouterEmprunt.setBounds(10, 51, 294, 330);
		contentPane.add(AjouterEmprunt);
		AjouterEmprunt.setLayout(null);

		Aj_idExemplaire = new JTextField();
		Aj_idExemplaire.setBounds(169, 156, 86, 20);
		Aj_idExemplaire.setEnabled(false);
		AjouterEmprunt.add(Aj_idExemplaire);
		Aj_idExemplaire.setColumns(10);

		Aj_idAdherent = new JTextField();
		Aj_idAdherent.setBounds(169, 199, 86, 20);
		Aj_idAdherent.setEnabled(false);
		AjouterEmprunt.add(Aj_idAdherent);
		Aj_idAdherent.setColumns(10);

		Aj_dateRetour = new JTextField();
		Aj_dateRetour.setBounds(169, 242, 86, 20);
		Aj_dateRetour.setEnabled(false);
		AjouterEmprunt.add(Aj_dateRetour);
		Aj_dateRetour.setColumns(10);

		JLabel lblIdExemplaire = new JLabel("Id Exemplaire         :");
		lblIdExemplaire.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblIdExemplaire.setBounds(22, 157, 137, 17);
		AjouterEmprunt.add(lblIdExemplaire);

		JLabel lblIdAdhrent = new JLabel("Id Adh\u00E9rent          :");
		lblIdAdhrent.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblIdAdhrent.setBounds(22, 201, 137, 14);
		AjouterEmprunt.add(lblIdAdhrent);

		JLabel lblDateDemprunt = new JLabel("Date d'Emprunt    :");
		lblDateDemprunt.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblDateDemprunt.setBounds(22, 287, 137, 14);
		AjouterEmprunt.add(lblDateDemprunt);

		Aj_dateEmprunt = new JTextField();
		Aj_dateEmprunt.setBounds(169, 285, 86, 20);
		Aj_dateEmprunt.setEnabled(false);
		AjouterEmprunt.add(Aj_dateEmprunt);
		Aj_dateEmprunt.setColumns(10);

		JLabel Aj_LeMode = new JLabel("Vous n'avez pas encore choisi le cas d'utilisation");
		Aj_LeMode.setFont(new Font("Tahoma", Font.PLAIN, 10));
		Aj_LeMode.setBounds(30, 67, 220, 14);
		AjouterEmprunt.add(Aj_LeMode);

		JLabel lblDateRetour = new JLabel("Date de Retour Eff:");
		lblDateRetour.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblDateRetour.setBounds(22, 245, 137, 14);
		AjouterEmprunt.add(lblDateRetour);

		Aj_IdEmprunt = new JTextField();
		Aj_IdEmprunt.setBounds(169, 102, 86, 20);
		Aj_IdEmprunt.setEnabled(false);
		AjouterEmprunt.add(Aj_IdEmprunt);
		Aj_IdEmprunt.setColumns(10);

		JLabel Aj_lblIdEmprunt = new JLabel("Id Emprunt             :");
		Aj_lblIdEmprunt.setFont(new Font("Tahoma", Font.BOLD, 12));
		Aj_lblIdEmprunt.setBounds(22, 101, 137, 20);
		AjouterEmprunt.add(Aj_lblIdEmprunt);

		/***********************************
		 * les buttons Ajouter /modifier/ supprimer
		 ************************************/

		JButton btnAjouter = new JButton("Ajouter");
		btnAjouter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					EmpruntDAO emdao = new EmpruntDAO(Database_Connection.getConnection());

					int idUsager = Integer.parseInt(Aj_idAdherent.getText());
					int idexemplaire = Integer.parseInt(Aj_idExemplaire.getText());

					Emprunt em = new Emprunt(idexemplaire, idUsager, Aj_dateEmprunt.getText());
					
					System.out.println("l'emprunt a enregistré est :::::: " + em);
					boolean result = emdao.add(em);
					if (result) {
						JOptionPane.showMessageDialog(null, " Emprunt ajouté avec succés !! ");
					} else {

						ExemplaireDAO exemplairedao = new ExemplaireDAO(Database_Connection.getConnection());
						Exemplaire ex = exemplairedao.findById(idexemplaire);
						UsagerDAO userDAO = new UsagerDAO(Database_Connection.getConnection());
						Usager user = userDAO.findById(idUsager);

						if (ex.getIdExemplaire() == 0 && user.getIdUsager() == 0) {
							JOptionPane.showMessageDialog(null, " l'exemplaire et l'adhérent n'existent pas !! ");

						} else if (ex.getIdExemplaire() == 0) {
							JOptionPane.showMessageDialog(null, " cet exemplaire n'existe pas !! ");

						} else if (user.getIdUsager() == 0) {
							JOptionPane.showMessageDialog(null, " cet adhérent n'existe pas !! ");
						}
					}
				} catch (Exception a) {
					JOptionPane.showMessageDialog(null, " Emprunt n'est pas enregistré !! ");
					a.getStackTrace();
				}
			}
		});
		btnAjouter.setBounds(215, 391, 95, 23);
		btnAjouter.setEnabled(false);
		contentPane.add(btnAjouter);

		JButton btnModifier = new JButton("Modifier");
		btnModifier.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				EmpruntDAO emdao = new EmpruntDAO(Database_Connection.getConnection());
				Emprunt em = new Emprunt(Integer.parseInt(Aj_IdEmprunt.getText()),
						Integer.parseInt(Aj_idExemplaire.getText()), Integer.parseInt(Aj_idAdherent.getText()),
						Aj_dateRetour.getText());
				System.out.println("L'emprunt passé au DAO : "+em);
				boolean result = emdao.update(em);
				if (result) {
					JOptionPane.showMessageDialog(null, " Modification faite avec succees ");
				} else {
					JOptionPane.showMessageDialog(null, " Modification a echoué !!!");
				}

			}
		});
		btnModifier.setBounds(112, 391, 95, 23);
		btnModifier.setEnabled(false);
		contentPane.add(btnModifier);

		JButton btnSupprimer = new JButton("Supprimer");
		btnSupprimer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				EmpruntDAO emdao = new EmpruntDAO(Database_Connection.getConnection());
				int id = Integer.parseInt(Aj_IdEmprunt.getText());
				boolean result = emdao.delete(id);
				if (result) {
					JOptionPane.showMessageDialog(null, " Emprunt a été supprime avec succes ");
				} else {
					JOptionPane.showMessageDialog(null, " La suppression a échoué !! ");
				}
			}
		});
		btnSupprimer.setEnabled(false);
		btnSupprimer.setBounds(10, 391, 95, 23);
		contentPane.add(btnSupprimer);
		
		JButton Aj_findById = new JButton("Find");
		Aj_findById.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EmpruntDAO empdao = new EmpruntDAO(Database_Connection.getConnection());
				Emprunt em = empdao.findByIdEmpruntForUpdate(Integer.parseInt(Aj_IdEmprunt.getText()));

				Aj_idAdherent.setText(String.valueOf(em.getId_Emprunt_Usager()));
				Aj_idExemplaire.setText(String.valueOf(em.getId_Emprunt_Exemplaire()));
				Aj_dateRetour.setText(String.valueOf(em.getDateRetour_Eff()));

				if (em.getId_Emprunt() == 0) {
					JOptionPane.showMessageDialog(null, " Emprunt n'existe ");
				}

			}
		});
		Aj_findById.setBounds(224, 130, 60, 18);
		Aj_findById.setEnabled(false);
		AjouterEmprunt.add(Aj_findById);

		/************************************************
		 * fin traitements buttons
		 *****************************************/

		JComboBox Aj_comboBox = new JComboBox(new DefaultComboBoxModel(
				new String[] { "Choisir le cas d'utilisation", "Ajouter", "Modifier", "Supprimer" }));
		Aj_comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				String valueSelected = Aj_comboBox.getSelectedItem().toString();
				if (valueSelected == "Ajouter") {

					Aj_IdEmprunt.setText("");
					Aj_idAdherent.setText("");
					Aj_idExemplaire.setText("");
					Aj_dateRetour.setText("");
					Aj_dateEmprunt.setText("");

					Aj_IdEmprunt.setEnabled(false);
					Aj_idExemplaire.setEnabled(true);
					Aj_idAdherent.setEnabled(true);
					Aj_dateEmprunt.setEnabled(true);
					Aj_dateRetour.setEnabled(false);
					
					Aj_findById.setEnabled(false);

					btnAjouter.setEnabled(true);
					btnModifier.setEnabled(false);
					btnSupprimer.setEnabled(false);
					
					
					Aj_LeMode.setText("Le mode du recherche : Ajouter");

				} else if (valueSelected == "Modifier") {

					Aj_IdEmprunt.setEnabled(true);
					Aj_idExemplaire.setEnabled(true);
					Aj_idAdherent.setEnabled(true);
					Aj_dateEmprunt.setEnabled(false);
					Aj_dateRetour.setEnabled(true);

					Aj_findById.setEnabled(true);

					btnModifier.setEnabled(true);
					btnAjouter.setEnabled(false);
					btnSupprimer.setEnabled(false);
					Aj_LeMode.setText("Le mode du recherche : Modifier");

				} else if (valueSelected == "Supprimer") {

					Aj_IdEmprunt.setEnabled(true);
					Aj_idExemplaire.setEnabled(false);
					Aj_idAdherent.setEnabled(false);
					Aj_dateEmprunt.setEnabled(false);
					Aj_dateRetour.setEnabled(false);
					
					Aj_findById.setEnabled(false);

					btnModifier.setEnabled(false);
					btnAjouter.setEnabled(false);
					btnSupprimer.setEnabled(true);
					Aj_LeMode.setText("Le mode du recherche : Supprimer");
				} else if (valueSelected == "Choisir le cas d'utilisation") {
					
					Aj_IdEmprunt.setEnabled(false);
					Aj_idExemplaire.setEnabled(false);
					Aj_idAdherent.setEnabled(false);
					Aj_dateEmprunt.setEnabled(false);
					Aj_dateRetour.setEnabled(false);
					
					Aj_findById.setEnabled(false);

					btnModifier.setEnabled(false);
					btnAjouter.setEnabled(false);
					btnSupprimer.setEnabled(false);
					Aj_LeMode.setText("Vous n'avez pas encore choisi le cas d'utilisation");
				}

			}
		});
		Aj_comboBox.setBounds(50, 29, 173, 20);
		AjouterEmprunt.add(Aj_comboBox);
		
		JLabel lblYyyymmdd = new JLabel("yyyy-mm-dd");
		lblYyyymmdd.setBounds(213, 305, 71, 14);
		AjouterEmprunt.add(lblYyyymmdd);


		/***************************************************************************************************************************/

		/**********************************************
		 * Cherhcer Emprunt
		 **********************************************************/

		JPanel ChercherEmprunt = new JPanel();
		ChercherEmprunt.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Chercher Emprunt",
				TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		ChercherEmprunt.setBounds(324, 11, 488, 129);
		contentPane.add(ChercherEmprunt);
		ChercherEmprunt.setLayout(null);

		JLabel Ch_lblLeMode = new JLabel("Vous n'avez pas encore choisi le mode de recherche");
		Ch_lblLeMode.setFont(new Font("Tahoma", Font.PLAIN, 10));
		Ch_lblLeMode.setBounds(43, 104, 289, 14);
		ChercherEmprunt.add(Ch_lblLeMode);

		JLabel Ch_lblId = new JLabel("Id Emprunt    ");
		Ch_lblId.setFont(new Font("Tahoma", Font.BOLD, 12));
		Ch_lblId.setBounds(22, 26, 93, 14);
		ChercherEmprunt.add(Ch_lblId);

		Ch_txtId = new JTextField();
		Ch_txtId.setBounds(132, 24, 86, 20);
		Ch_txtId.setEnabled(false);
		ChercherEmprunt.add(Ch_txtId);
		Ch_txtId.setColumns(10);

		Ch_nom = new JTextField();
		Ch_nom.setBounds(132, 68, 86, 20);
		ChercherEmprunt.add(Ch_nom);
		Ch_nom.setColumns(10);

		JLabel Ch_lblNomAdherent = new JLabel("Nom Adh\u00E9rent  :");
		Ch_lblNomAdherent.setFont(new Font("Tahoma", Font.BOLD, 12));
		Ch_lblNomAdherent.setBounds(22, 69, 104, 17);
		ChercherEmprunt.add(Ch_lblNomAdherent);

		JLabel lblIdAdherent = new JLabel("Prenom Adherent   :");
		lblIdAdherent.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblIdAdherent.setBounds(244, 69, 130, 17);
		ChercherEmprunt.add(lblIdAdherent);

		Ch_prenom = new JTextField();
		Ch_prenom.setBounds(379, 68, 86, 20);
		Ch_prenom.setEnabled(false);
		Ch_nom.setEnabled(false);
		ChercherEmprunt.add(Ch_prenom);
		Ch_prenom.setColumns(10);
		
		JButton btnChercher = new JButton("Chercher");

		JComboBox Ch_comboBox = new JComboBox(new DefaultComboBoxModel(new String[] { "Choisir le mode du recherche",
				"Par ID Adherent", "Par Nom Adherent", "Par Exemplaire", "Par Emprunt" }));
		Ch_comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String valueSelected = Ch_comboBox.getSelectedItem().toString();
				if (valueSelected == "Par Nom Adherent") {
					Ch_lblId.setText("Id");
					Ch_txtId.setEnabled(false);
					Ch_txtId.setText("");
					Ch_prenom.setText("");
					Ch_nom.setText("");
					Ch_nom.setEnabled(true);
					Ch_prenom.setEnabled(true);
					btnChercher.setEnabled(true);
					Ch_lblLeMode.setText("Le mode du recherche : Par Nom Adherent");
				} else if (valueSelected == "Par ID Adherent") {
					Ch_lblId.setText("Id Adhérent");
					Ch_txtId.setEnabled(true);
					Ch_prenom.setText("");
					Ch_nom.setText("");
					Ch_prenom.setEnabled(false);
					Ch_nom.setEnabled(false);
					btnChercher.setEnabled(true);
					Ch_lblLeMode.setText("Le mode du recherche : Par ID Adherent");
				} else if (valueSelected == "Par Exemplaire") {
					Ch_lblId.setText("Id Exemplaire");
					Ch_txtId.setEnabled(true);
					Ch_prenom.setText("");
					Ch_nom.setText("");
					Ch_prenom.setEnabled(false);
					Ch_nom.setEnabled(false);
					Ch_lblLeMode.setText("Le mode du recherche : Par Id Exemplaire");
				} else if (valueSelected == "Par Emprunt") {
					Ch_txtId.setEnabled(true);
					Ch_lblId.setText("Id Emprunt");
					Ch_prenom.setText("");
					Ch_nom.setText("");
					Ch_prenom.setEnabled(false);
					Ch_nom.setEnabled(false);
					btnChercher.setEnabled(true);
					Ch_lblLeMode.setText("Le mode du recherche : Par Id Emprunt");
				} else if (valueSelected == "Choisir le mode du recherche") {
					Ch_lblId.setText("Id");
					Ch_txtId.setEnabled(false);
					Ch_txtId.setText("");
					Ch_prenom.setText("");
					Ch_nom.setText("");
					Ch_nom.setEnabled(false);
					Ch_prenom.setEnabled(false);
					btnChercher.setEnabled(false);
					Ch_lblLeMode.setText("Le mode du recherche : Aucun");
				}
			}
		});
		Ch_comboBox.setBounds(284, 24, 194, 20);
		ChercherEmprunt.add(Ch_comboBox);

		btnChercher.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EmpruntDAO empdao = new EmpruntDAO(Database_Connection.getConnection());
				ArrayList<Emprunt> emp = new ArrayList<Emprunt>();
				String valueSelected = Ch_comboBox.getSelectedItem().toString();

				if (valueSelected == "Par Nom Adherent") {
					System.out.println("interface Par Nom Adherent");
					emp = empdao.findByUserName(Ch_nom.getText(), Ch_prenom.getText());
				} else if (valueSelected == "Par ID Adherent") {
					System.out.println("interface ID Adherent");
					emp = empdao.findByUserId(Integer.parseInt(Ch_txtId.getText()));
				} else if (valueSelected == "Par Exemplaire") {
					System.out.println("interface Par Exemplaire");
					emp = empdao.findByExemplaire(Integer.parseInt(Ch_txtId.getText()));
				} else if (valueSelected == "Par Emprunt") {
					System.out.println("interface Par Emprunt");
					emp = empdao.findByIdEmprunt(Integer.parseInt(Ch_txtId.getText()));
				}

				System.out.println(emp);
				modelEm.setRowCount(0);
				System.out.println("probléme ");
				if (emp != null) {
					for (Emprunt em : emp) {
						System.out.println(em);
						modelEm.addRow(new Object[] { em.getId_Emprunt(), em.getId_Emprunt_Exemplaire(), em.getTitre(),
								em.getNom(), em.getPrenom(), em.getDateEmprunt(), em.getDateRetour_theo(),
								em.getDateRetour_Eff() });
					}
					System.out.println("modelEM no probleme");
				} else {
					JOptionPane.showMessageDialog(null, " Emprunt n'existe pas !! ");
				}
			}
		});
		btnChercher.setBounds(389, 95, 89, 23);
		btnChercher.setEnabled(false);
		ChercherEmprunt.add(btnChercher);

		JLabel label = new JLabel(":");
		label.setFont(new Font("Tahoma", Font.BOLD, 12));
		label.setBounds(122, 27, 4, 14);
		ChercherEmprunt.add(label);

		/***********************************************************************************************************************************/
		/***********************************************************
		 * Afficher Emprunt
		 ****************************************************/

		JPanel AfficherEmprunt = new JPanel();
		AfficherEmprunt.setBorder(
				new TitledBorder(null, "Affichage Emprunt", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		AfficherEmprunt.setBounds(324, 150, 488, 256);
		contentPane.add(AfficherEmprunt);
		AfficherEmprunt.setLayout(null);

		JScrollPane Affichage_scrollPane = new JScrollPane();
		Affichage_scrollPane.setBounds(10, 80, 468, 150);
		AfficherEmprunt.add(Affichage_scrollPane);

		table = new JTable();
		table.setModel(modelEm);
		Affichage_scrollPane.setViewportView(table);

		JButton btnAfficher = new JButton("Afficher");
		btnAfficher.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				EmpruntDAO empdao = new EmpruntDAO(Database_Connection.getConnection());
				ArrayList<Emprunt> emp = empdao.find();
				System.out.println("probléme ");
				modelEm.setRowCount(0);

				for (Emprunt em : emp) {
					System.out.println(em);
					modelEm.addRow(new Object[] { em.getId_Emprunt(), em.getId_Emprunt_Exemplaire(), em.getTitre(),
							em.getNom(), em.getPrenom(), em.getDateEmprunt(), em.getDateRetour_theo(),
							em.getDateRetour_Eff() });
				}

				System.out.println("modelEM no probleme");
			}
		});
		btnAfficher.setBounds(377, 46, 89, 23);
		AfficherEmprunt.add(btnAfficher);

		JButton btnRetour = new JButton("Retour");
		btnRetour.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				MenuPrincipale menu = new MenuPrincipale();
				menu.setVisible(true);
				dispose();
			}
		});
		btnRetour.setBounds(723, 409, 89, 23);
		contentPane.add(btnRetour);
		/*******************************************************
		 * Fin Ajouter Panel
		 *************************************************/

	}
}
