package ihm.GestionExemplaire;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.HeadlessException;
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
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import dao_project.Database_Connection;
import dao_project.ExemplaireDAO;
import ihm.Authentification.MenuPrincipale;
import objet_Métier.Exemplaire;

public class GestionExemplaire extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	private JTextField Ch_Id;
	private JTextField Ch_titre;
	private JTextField Aj_Id_Exemplaire;
	private JTextField Aj_Etat_Exemplaire;
	private JTable table;
	private DefaultTableModel modelo;
	private JTextField Aj_Disponibilite;
	private JTextField Aj_Id_Oeuvre;

	/**
	 * Launch the application.
	 * 
	 * 
	 */

	public void AffichageComplet() {
		modelo.setRowCount(0);
		ExemplaireDAO exemdao = new ExemplaireDAO(Database_Connection.getConnection());
		ArrayList<Exemplaire> donnees = exemdao.find();
		String dispo = null;

		for (Exemplaire a : donnees) {
			if (a.getDispo_Exemp() == 1)
				dispo = "disponible";
			else
				dispo = "emprunté ";
			modelo.addRow(new Object[] { a.getIdExemplaire(), a.getTitreOeuvre(), dispo, a.getEtat_Exemp() });
		}

	}

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GestionExemplaire frame = new GestionExemplaire();
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
	public GestionExemplaire() {

		modelo = new DefaultTableModel(new Object[][] {},
				new String[] { "Id Exemplaire", "Titre de l'Oeuvre", "Disponibilité", "l'état" });

		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		ImageIcon img = new ImageIcon("C:/Users/lktj/eclipse-workspace/TestProj/img/bibliotheque2.png");
		this.setIconImage(img.getImage());
		setTitle("Gestion des Exemplaires");
		setBounds(100, 100, 836, 482);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblGestionExemplaire = new JLabel("GESTION EXEMPLAIRES");
		lblGestionExemplaire.setHorizontalAlignment(SwingConstants.CENTER);
		lblGestionExemplaire.setForeground(Color.PINK);
		lblGestionExemplaire.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblGestionExemplaire.setBounds(37, 11, 225, 41);
		contentPane.add(lblGestionExemplaire);

		/******************************
		 * Les buttons ajouter modifier et supprimer
		 *********************/

		JButton btnModifier = new JButton("modifier");
		btnModifier.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnModifier.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					ExemplaireDAO exempdao = new ExemplaireDAO(Database_Connection.getConnection());
					Exemplaire user = new Exemplaire(Integer.parseInt(Aj_Id_Exemplaire.getText()),
							 Integer.parseInt(Aj_Disponibilite.getText()),
							Aj_Etat_Exemplaire.getText());

					boolean result = exempdao.update(user);

					if (result) {
						JOptionPane.showMessageDialog(null, "Exemplaire Modifie Avec succées");
						/********** L'affichage du résultat en table *****************/
						AffichageComplet();
						/*************************************************************/

					} else {
						JOptionPane.showMessageDialog(null, "l'Exemplaire est déja emprunté");
					}
				} catch (NumberFormatException e1) {
					JOptionPane.showMessageDialog(null, "la suppression a échoué ");
					e1.printStackTrace();
				} catch (HeadlessException e1) {
					JOptionPane.showMessageDialog(null, "la suppression a échoué ");
					e1.printStackTrace();
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
				try {
					ExemplaireDAO exempdao = new ExemplaireDAO(Database_Connection.getConnection());
					boolean result = exempdao.delete(Integer.parseInt(Aj_Id_Exemplaire.getText()));
					System.out.println("L'ID supprimé  : " + Integer.parseInt(Aj_Id_Exemplaire.getText()));

					System.out.println(result);
					if (result) {
						JOptionPane.showMessageDialog(null, "Exemplaire Supprimé Avec succées");

						/********** L'affichage du résultat en table *****************/

						AffichageComplet();
						/*************************************************************/

					} else {
						JOptionPane.showMessageDialog(null, "la suppression a échoué ");
					}
				} catch (NumberFormatException e1) {
					System.out.println("Suppression echoué");
					e1.printStackTrace();
				} catch (HeadlessException e1) {
					System.out.println("Suppression echoué");

					e1.printStackTrace();
				}
				Aj_Id_Exemplaire.setText("");

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
					ExemplaireDAO exempdao = new ExemplaireDAO(Database_Connection.getConnection());
					Exemplaire user = new Exemplaire(Integer.parseInt(Aj_Id_Oeuvre.getText()),
							Aj_Etat_Exemplaire.getText());

					boolean IsAdded = exempdao.add(user);

					if (IsAdded == true) {
						JOptionPane.showMessageDialog(null, "Exemplaire Ajouter Avec succées");

						/********** L'affichage du résultat en table *****************/

						AffichageComplet();

						/*************************************************************/

					} else {
						JOptionPane.showMessageDialog(null, "l'ajout a échoué !");
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

		/*******************************************************
		 * Chercher Panel
		 **********************************************************/

		JPanel Chercher_Panel = new JPanel();
		Chercher_Panel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Chercher Exemplaire",
				TitledBorder.LEFT, TitledBorder.TOP, null, new Color(0, 0, 0)));
		Chercher_Panel.setBounds(324, 11, 488, 129);
		contentPane.add(Chercher_Panel);
		Chercher_Panel.setLayout(null);

		JButton Ch_btnChercher = new JButton("Chercher");

		JLabel lblRechercherPar = new JLabel("Rechercher par :");
		lblRechercherPar.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblRechercherPar.setBounds(31, 27, 122, 16);
		Chercher_Panel.add(lblRechercherPar);

		JLabel Ch_lblLeMode = new JLabel("Vous n'avez pas encore choisi le mode de recherche");
		Ch_lblLeMode.setFont(new Font("Tahoma", Font.PLAIN, 10));
		Ch_lblLeMode.setBounds(34, 95, 289, 14);
		Chercher_Panel.add(Ch_lblLeMode);

		JComboBox Ch_comboBox = new JComboBox();
		Ch_comboBox.setModel(
				new DefaultComboBoxModel(new String[] { "Choisir le mode recherche", "Identifiant", "Titre" }));
		Ch_comboBox.setBounds(173, 26, 196, 20);
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
		Chercher_Panel.add(Ch_comboBox);

		JLabel lblIdOeuvre_1 = new JLabel("Id Exemplaire :");
		lblIdOeuvre_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblIdOeuvre_1.setBounds(10, 64, 91, 20);
		Chercher_Panel.add(lblIdOeuvre_1);

		Ch_Id = new JTextField();
		Ch_Id.setBounds(111, 64, 86, 20);
		Chercher_Panel.add(Ch_Id);
		Ch_Id.setColumns(10);

		JLabel lblTitreDoeuvre = new JLabel("Titre d'Oeuvre :");
		lblTitreDoeuvre.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblTitreDoeuvre.setBounds(230, 61, 104, 26);
		Chercher_Panel.add(lblTitreDoeuvre);

		Ch_titre = new JTextField();
		Ch_titre.setBounds(344, 65, 110, 20);
		Chercher_Panel.add(Ch_titre);
		Ch_titre.setColumns(10);

		Ch_btnChercher.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				modelo.setRowCount(0);
				String selectedValue = Ch_comboBox.getSelectedItem().toString();
				ExemplaireDAO exemdao = new ExemplaireDAO(Database_Connection.getConnection());

				if (selectedValue == "Titre") {
					ArrayList<Exemplaire> emd = exemdao.findByTitle(Ch_titre.getText());
					String dispo = null;
					for (Exemplaire em : emd) {
						if (em.getDispo_Exemp() == 1) {
							dispo = "disponible";
						} else {
							dispo = "emprunté ";
						}
						modelo.addRow(
								new Object[] { em.getIdExemplaire(), em.getTitreOeuvre(), dispo, em.getEtat_Exemp() });
					}
				} else if (selectedValue == "Identifiant") {
					Exemplaire em = exemdao.findById(Integer.parseInt(Ch_Id.getText()));
					String dispo = null;
					if (em.getDispo_Exemp() == 1) {
						dispo = "disponible";
					} else {
						dispo = "emprunté ";
					}
					modelo.addRow(
							new Object[] { em.getIdExemplaire(), em.getTitreOeuvre(), dispo, em.getEtat_Exemp() });

				}

			}
		});
		Ch_btnChercher.setFont(new Font("Tahoma", Font.BOLD, 12));
		Ch_btnChercher.setBounds(389, 96, 89, 23);
		Ch_btnChercher.setEnabled(false);
		Chercher_Panel.add(Ch_btnChercher);

		/**************************************************
		 * Fin Chercher
		 *************************************************/

		/**************************************************
		 * Affichage Panel
		 **********************************************/

		JPanel Affichage_Panel = new JPanel();
		Affichage_Panel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"),
				"Affichage des Exemplaires", TitledBorder.LEFT, TitledBorder.TOP, null, new Color(0, 0, 0)));
		Affichage_Panel.setBounds(324, 150, 488, 264);
		contentPane.add(Affichage_Panel);
		Affichage_Panel.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 69, 468, 184);
		Affichage_Panel.add(scrollPane);

		table = new JTable();
		scrollPane.setViewportView(table);
		table.setModel(modelo);

		// table = new JTable();
		// table.setBounds(12, 73, 548, 191);

		JButton btn_Afficher = new JButton("Afficher");
		btn_Afficher.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				AffichageComplet();

			}
		});
		btn_Afficher.setFont(new Font("Tahoma", Font.BOLD, 13));
		btn_Afficher.setBounds(389, 35, 89, 23);
		Affichage_Panel.add(btn_Afficher);
		// panel_1.add(table);

		/**************************************************
		 * Fin Affichage Panel
		 **********************************************/

		JPanel Ajouter_Panel = new JPanel();
		Ajouter_Panel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Ajouter Exemplaire",
				TitledBorder.LEFT, TitledBorder.TOP, null, new Color(0, 0, 0)));
		Ajouter_Panel.setBounds(10, 51, 294, 330);
		contentPane.add(Ajouter_Panel);
		Ajouter_Panel.setLayout(null);

		JComboBox Aj_comboBox = new JComboBox();
		Aj_comboBox.setModel(new DefaultComboBoxModel(
				new String[] { "Choisir le cas d'utilisation", "Ajouter", "Modifier", "Supprimer" }));

		JLabel Aj_LeMode = new JLabel("Vous n'avez pas encore choisi le cas d'utilisation");
		Aj_LeMode.setFont(new Font("Tahoma", Font.PLAIN, 10));
		Aj_LeMode.setBounds(38, 83, 223, 14);
		Ajouter_Panel.add(Aj_LeMode);

		JLabel Aj_lbl_IdExemplaire = new JLabel("Id Exemplaire    :");
		Aj_lbl_IdExemplaire.setFont(new Font("Tahoma", Font.BOLD, 13));
		Aj_lbl_IdExemplaire.setBounds(24, 121, 112, 16);
		Ajouter_Panel.add(Aj_lbl_IdExemplaire);

		Aj_Id_Exemplaire = new JTextField();
		Aj_Id_Exemplaire.setBounds(159, 119, 116, 22);
		Ajouter_Panel.add(Aj_Id_Exemplaire);
		Aj_Id_Exemplaire.setColumns(10);

		Aj_Id_Oeuvre = new JTextField();
		Aj_Id_Oeuvre.setBounds(159, 181, 116, 22);
		Ajouter_Panel.add(Aj_Id_Oeuvre);
		Aj_Id_Oeuvre.setColumns(10);

		JLabel Aj_lblIdOeuvre = new JLabel("Id Oeuvre          :");
		Aj_lblIdOeuvre.setFont(new Font("Tahoma", Font.BOLD, 13));
		Aj_lblIdOeuvre.setBounds(24, 184, 112, 14);
		Ajouter_Panel.add(Aj_lblIdOeuvre);

		JLabel Aj_lblEtatExemplaire = new JLabel("Etat Exemplaire :");
		Aj_lblEtatExemplaire.setFont(new Font("Tahoma", Font.BOLD, 13));
		Aj_lblEtatExemplaire.setBounds(24, 278, 116, 16);
		Ajouter_Panel.add(Aj_lblEtatExemplaire);

		Aj_Etat_Exemplaire = new JTextField();
		Aj_Etat_Exemplaire.setBounds(159, 276, 116, 22);
		Ajouter_Panel.add(Aj_Etat_Exemplaire);
		Aj_Etat_Exemplaire.setColumns(10);

		Aj_Disponibilite = new JTextField();
		Aj_Disponibilite.setBounds(159, 230, 116, 22);
		Ajouter_Panel.add(Aj_Disponibilite);
		Aj_Disponibilite.setColumns(10);

		JLabel Aj_lblDisponiblitDexemplaire = new JLabel("Disponiblit\u00E9       :");
		Aj_lblDisponiblitDexemplaire.setFont(new Font("Tahoma", Font.BOLD, 13));
		Aj_lblDisponiblitDexemplaire.setBounds(24, 232, 112, 16);
		Ajouter_Panel.add(Aj_lblDisponiblitDexemplaire);

		Aj_Id_Exemplaire.setEnabled(false);
		Aj_Etat_Exemplaire.setEnabled(false);
		Aj_Disponibilite.setEnabled(false);
		Aj_Id_Oeuvre.setEnabled(false);

		JButton Aj_findById = new JButton("find");

		Aj_comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String valueSelected = Aj_comboBox.getSelectedItem().toString();
				if (valueSelected == "Ajouter") {
					Aj_Id_Exemplaire.setEnabled(false);
					Aj_Etat_Exemplaire.setEnabled(true);
					Aj_Disponibilite.setEnabled(false);
					Aj_Id_Oeuvre.setEnabled(true);

					Aj_Id_Exemplaire.setText("");
					Aj_Etat_Exemplaire.setText("");
					Aj_Disponibilite.setText("");
					Aj_Id_Oeuvre.setText("");

					Aj_findById.setEnabled(false);

					btnAjouter.setEnabled(true);
					btnModifier.setEnabled(false);
					btnSupprimer.setEnabled(false);
					Aj_LeMode.setText("Le mode du recherche : Ajouter");
				} else if (valueSelected == "Modifier") {
					Aj_Id_Exemplaire.setEnabled(true);
					Aj_Etat_Exemplaire.setEnabled(true);
					Aj_Disponibilite.setEnabled(true);
					Aj_Id_Oeuvre.setEnabled(true);

					Aj_findById.setEnabled(true);

					btnModifier.setEnabled(true);
					btnAjouter.setEnabled(false);
					btnSupprimer.setEnabled(false);
					Aj_LeMode.setText("Le mode du recherche : Modifier");
				} else if (valueSelected == "Supprimer") {
					Aj_Id_Exemplaire.setEnabled(true);
					Aj_Etat_Exemplaire.setEnabled(false);
					Aj_Disponibilite.setEnabled(false);
					Aj_Id_Oeuvre.setEnabled(false);

					Aj_findById.setEnabled(false);

					btnModifier.setEnabled(false);
					btnAjouter.setEnabled(false);
					btnSupprimer.setEnabled(true);
					Aj_LeMode.setText("Le mode du recherche : Supprimer");
				} else if (valueSelected == "Choisir le cas d'utilisation") {

					Aj_Id_Exemplaire.setEnabled(false);
					Aj_Etat_Exemplaire.setEnabled(false);
					Aj_Disponibilite.setEnabled(false);
					Aj_Id_Oeuvre.setEnabled(false);

					Aj_findById.setEnabled(false);

					btnModifier.setEnabled(false);
					btnAjouter.setEnabled(false);
					btnSupprimer.setEnabled(false);
					Aj_LeMode.setText("Vous n'avez pas encore choisi le cas d'utilisation");
				}
			}
		});
		Aj_comboBox.setBounds(56, 46, 177, 20);
		Ajouter_Panel.add(Aj_comboBox);

		Aj_findById.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				ExemplaireDAO exempdao = new ExemplaireDAO(Database_Connection.getConnection());
				Exemplaire exemplaire = exempdao.findById(Integer.parseInt(Aj_Id_Exemplaire.getText()));
				if (exemplaire.getIdExemplaire() == 0) {
					JOptionPane.showMessageDialog(null, " Exemplaire introuvé !! ");

				} else {
					Aj_Etat_Exemplaire.setText(exemplaire.getEtat_Exemp());
//					Aj_Disponibilite.setText(String.valueOf(exemplaire.getDispo_Exemp()));
				}

			}
		});
		Aj_findById.setBounds(224, 152, 60, 18);
		Aj_findById.setEnabled(false);
		Ajouter_Panel.add(Aj_findById);

		/********************** Ajouter Panel *************************************/

		JButton btnRetour = new JButton("Retour");
		btnRetour.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				MenuPrincipale menu = new MenuPrincipale();
				menu.setVisible(true);
				dispose();
			}
		});
		btnRetour.setBounds(723, 420, 89, 23);
		contentPane.add(btnRetour);
	}
}
