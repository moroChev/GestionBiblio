package ihm.Authentification;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import dao_project.Database_Connection;

public class Auth extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private imagePan pan;
	private JTextField username;
	private JPasswordField password;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Auth frame = new Auth();
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
	public Auth() {
		this.setSize(900, 700);
		this.setTitle("Authentification");
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		ImageIcon img = new ImageIcon("C:/Users/lktj/eclipse-workspace/TestProj/img/bibliotheque2.png");
		this.setIconImage(img.getImage());
		this.setResizable(true);
		pan = new imagePan();
		this.setContentPane(pan);
		pan.setLayout(null);
		
		JLabel lblMotDutilisateurOu = new JLabel("Mot d'utilisateur ou mot de passe incorrect");
		lblMotDutilisateurOu.setForeground(new Color(255, 0, 0));
		lblMotDutilisateurOu.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblMotDutilisateurOu.setBounds(403, 581, 385, 43);
		lblMotDutilisateurOu.setVisible(false);
		pan.add(lblMotDutilisateurOu);

		username = new JTextField();
		username.setFont(new Font("Comic Sans MS", Font.BOLD, 18));
		username.setBounds(605, 352, 208, 50);
		pan.add(username);
		username.setColumns(10);

		password = new JPasswordField();
		password.setFont(new Font("Tahoma", Font.BOLD, 18));
		password.setBounds(605, 425, 208, 50);
		pan.add(password);

		JLabel lblUsername = new JLabel("Mot d'Utilisateur :");
		lblUsername.setForeground(SystemColor.text);
		lblUsername.setBounds(380, 361, 203, 33);
		pan.add(lblUsername);
		lblUsername.setFont(new Font("Sitka Small", Font.BOLD | Font.ITALIC, 20));

		JLabel lblPassword = new JLabel("Mot de Passe        :");
		lblPassword.setForeground(SystemColor.text);
		lblPassword.setBounds(380, 434, 193, 24);
		lblPassword.setFont(new Font("Sitka Small", Font.BOLD | Font.ITALIC, 20));

		pan.add(lblPassword);

		JButton se_connecter = new JButton("se connecter");
		se_connecter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				try {

					Statement stmt = Database_Connection.getConnection().createStatement();
					String sql = "Select * from users where username='" + username.getText() + "' and password='"
							+ password.getText().toString() + "'";
					ResultSet rs = stmt.executeQuery(sql);
					if (rs.next()) {

						JOptionPane.showMessageDialog(null, "Login succés");
						MenuPrincipale menu2 = new MenuPrincipale();
						menu2.setVisible(true);
						dispose();

					} else {
						lblMotDutilisateurOu.setVisible(true);
					}

					rs.close();
					stmt.close();
					username.setText("");
					password.setText("");

				} catch (Exception e) {
					System.out.println(e);
				}

			}

		});
		se_connecter.setBackground(Color.WHITE);
		se_connecter.setBounds(677, 524, 169, 33);
		se_connecter.setForeground(SystemColor.textText);
		// btnSeConnecter.setBounds(610, 398, 244, 44);
		se_connecter.setFont(new Font("Tahoma", Font.BOLD, 19));
		pan.add(se_connecter);
		

	}
}
