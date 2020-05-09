package dao_project;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import objet_Métier.Oeuvre;

public class OeuvreDAO {

	private Connection connect;
	private Statement state;
	private ResultSet result;

	public OeuvreDAO(Connection conn) {
		try {

			if (this.state != null) {
				System.out.println("statement deja cree !");
			} else {
				this.state = conn.createStatement();
				System.out.println("Nouvelle statement cree !");
			}
		} catch (Exception e) {
			System.out.println("dude !!");
			e.getStackTrace();
		}
	}

	public boolean add(Oeuvre oeu) {
		try {

			// préparation de la requete sql
			String query = "INSERT INTO oeuvre SET titre_Oeuvre=\"" + oeu.getTitreOeuvre() + "\", auteur_Oeuvre =\""
					+ oeu.getAuteurOeuvre() + "\", nbr_Exempl_Oeuvre ='" + oeu.getNbrExemplOeuvre()
					+ "',nbr_disponible_Oeuvre = " + oeu.getNbrExemplOeuvre() + ", categorie_Oeuvre =\""
					+ oeu.getCategorieOeuvre() + "\";";

			System.out.println(query);
			// exécution de la requete
			state.executeUpdate(query);

			System.out.println("2" + query);

			return true;

		} 
		catch (SQLException e) {
			e.getMessage();
			e.getStackTrace();
			return false;
		} finally {
			try {
				result.close();
				System.out.println("result fermeéééééé");
			} catch (Exception e) {
				System.out.println("result NOt fermeéééééé");
			}
			try {
				state.close();
				System.out.println("state fermeéééééé");
			} catch (Exception e) {
				System.out.println("state NoOT fermeéééééé");
			}
			try {
				connect.close();
				System.out.println("connect fermeéééééé find()");
			} catch (Exception e) {
				System.out.println("connect not ferme find()");
			}
		}

	}

	public boolean delete(int id) {
		try {

			// préparation de la requete sql
			String query1 = "DELETE FROM exemplaire where id_Oeuvre = " + id + ";";
			String query2 = "DELETE FROM oeuvre WHERE id_Oeuvre = " + id + ";";

			// exécution de la requete
			state.executeUpdate(query1);
			System.out.println(query1);
			state.executeUpdate(query2);
			System.out.println(query2);

			return true;

		} catch (SQLException e) {
			e.getStackTrace();
			return false;
		} finally {
			try {
				result.close();
				System.out.println("result fermeéééééé");
			} catch (Exception e) {
				/* ignored */ }
			try {
				state.close();
				System.out.println("state fermeéééééé");
			} catch (Exception e) {
				/* ignored */ }
			try {
				connect.close();
				System.out.println("connect fermeéééééé find()");
			} catch (Exception e) {
				/* ignored */ }
		}
	}

	public boolean update(Oeuvre oeu) {
		try {

			// préparation de la requete sql
			String query = "UPDATE oeuvre SET titre_Oeuvre=\"" + oeu.getTitreOeuvre() + "\", auteur_Oeuvre =\""
					+ oeu.getAuteurOeuvre() + "\", nbr_Exempl_Oeuvre ='" + oeu.getNbrExemplOeuvre()
					+ "',categorie_Oeuvre=\"" + oeu.getCategorieOeuvre() + "\" WHERE id_Oeuvre = " + oeu.getIdOeuvre()
					+ ";";

			// exécution de la requete
			System.out.println(query);
			state.executeUpdate(query);

			System.out.println(query);
			return true;

		} catch (SQLException e) {
			e.getStackTrace();
			return false;
		} finally {
			try {
				result.close();
				System.out.println("result fermeéééééé");
			} catch (Exception e) {
				/* ignored */ }
			try {
				state.close();
				System.out.println("state fermeéééééé");
			} catch (Exception e) {
				/* ignored */ }
			try {
				connect.close();
				System.out.println("connect fermeéééééé find()");
			} catch (Exception e) {
				/* ignored */ }
		}
	}

	public ArrayList<Oeuvre> find() {
		ArrayList<Oeuvre> oeus = new ArrayList<Oeuvre>();

		try {

			// préparation de la requete sql
			String query = "select * FROM oeuvre ;";

			// exécution de la requete et récupération des données
			result = state.executeQuery(query);

			while (result.next()) {
				oeus.add(new Oeuvre(result.getInt(1), result.getString(2), result.getString(3), result.getInt(4),
						result.getInt(5), result.getString(6)));
			}
			return oeus;

		} catch (SQLException e) {
			e.getStackTrace();
			return null;
		} finally {
			try {
				result.close();
				System.out.println("result fermeéééééé");
			} catch (Exception e) {
				/* ignored */ }
			try {
				state.close();
				System.out.println("state fermeéééééé");
			} catch (Exception e) {
				/* ignored */ }
			try {
				connect.close();
				System.out.println("connect fermeéééééé find()");
			} catch (Exception e) {
				/* ignored */ }
		}

	}

	public Oeuvre findById(int id) {
		Oeuvre oeu = new Oeuvre();

		try {

			// préparation de la requete sql
			String query = "select * FROM oeuvre WHERE id_Oeuvre = " + id + ";";

			// exécution de la requete et récupération des données
			result = state.executeQuery(query);

			// remplissage de l'objet Usager
			while (result.next()) {

				oeu.setIdOeuvre(result.getInt(1));
				oeu.setAuteurOeuvre(result.getString(3));
				oeu.setTitreOeuvre(result.getString(2));
				oeu.setNbrExempOeuvre(result.getInt(4));
				oeu.setNbrDisponibleOeuvre(result.getInt(5));
				oeu.setCategorieOeuvre(result.getString(6));

				System.out.println("objet rempli ");
			}

			return oeu;
		} catch (SQLException e) {
			e.getStackTrace();

			return null;
		} finally {
			try {
				result.close();
				System.out.println("result fermeéééééé");
			} catch (Exception e) {
				/* ignored */ }
			try {
				state.close();
				System.out.println("state fermeéééééé");
			} catch (Exception e) {
				/* ignored */ }
			try {
				connect.close();
				System.out.println("connect fermeéééééé find()");
			} catch (Exception e) {
				/* ignored */ }
		}
	}

	public Oeuvre findByTitle(String titre) {
		Oeuvre oeu = new Oeuvre();
		try {

			// préparation de la requete sql
			String query = "select * FROM oeuvre WHERE titre_Oeuvre = \"" + titre + "\";";

			System.out.println(query);
			// exécution de la requete et récupération des données
			result = state.executeQuery(query);

			// remplissage de l'objet Usager
			while (result.next()) {

				oeu.setIdOeuvre(result.getInt(1));
				oeu.setAuteurOeuvre(result.getString(3));
				oeu.setTitreOeuvre(result.getString(2));
				oeu.setNbrExempOeuvre(result.getInt(4));
				oeu.setNbrDisponibleOeuvre(result.getInt(5));
				oeu.setCategorieOeuvre(result.getString(6));

				System.out.println("objet rempli ");
			}

			return oeu;
		} catch (SQLException e) {
			e.getStackTrace();

			return null;
		} finally {

			try {
				result.close();
				System.out.println("result fermeéééééé");
			} catch (Exception e) {
				/* ignored */ }
			try {
				state.close();
				System.out.println("state fermeéééééé");
			} catch (Exception e) {
				/* ignored */ }
			try {
				connect.close();
				System.out.println("connect fermeéééééé findbytitle()");
			} catch (Exception e) {
				/* ignored */ }
		}
	}

	public boolean isExist(Oeuvre oeu) {
		boolean exist = false;
		try {

			// préparation de la requete sql
			String query = "select * FROM oeuvre WHERE id_Oeuvre = " + oeu.getIdOeuvre() + ";";

			// exécution de la requete et récupération des données
			result = state.executeQuery(query);

			// verification
			while (result.next()) {
				if (result.getInt(1) == oeu.getIdOeuvre()) {
					exist = true;
				}
			}

			return exist;
		} catch (SQLException e) {
			System.out.println(" probélme isExistOeuvre !!");
			return exist;
		} finally {
			try {
				result.close();
				System.out.println("result fermeéééééé");
			} catch (Exception e) {
				/* ignored */ }
			try {
				state.close();
				System.out.println("state fermeéééééé");
			} catch (Exception e) {
				/* ignored */ }
			try {
				connect.close();
				System.out.println("connect fermeéééééé find()");
			} catch (Exception e) {
				/* ignored */ }
		}
	}

	public int findExemplaireEmprunte(int idOeuvre) {
		int idExmplaire = 0;
		try {

			// préparation de la requete sql
			String query = "select * FROM exemplaire WHERE id_Oeuvre = " + idOeuvre + " and dispo_Exemplaire = 1 ;";
			System.out.println(query);

			// exécution de la requete et récupération des données
			result = state.executeQuery(query);
			System.out.println("executeeeee !");

			// remplissage de l'objet Usager
			while (result.next()) {

				idExmplaire = result.getInt(1);
				System.out.println(result.getInt(1));
			}

			return idExmplaire;
		} catch (SQLException e) {
			e.getStackTrace();

			return idExmplaire;
		} finally {
			try {
				result.close();
				System.out.println("result fermeéééééé");
			} catch (Exception e) {
				/* ignored */ }
			try {
				state.close();
				System.out.println("state fermeéééééé");
			} catch (Exception e) {
				/* ignored */ }
			try {
				connect.close();
				System.out.println("connect fermeéééééé find()");
			} catch (Exception e) {
				/* ignored */ }
		}

	}
}
