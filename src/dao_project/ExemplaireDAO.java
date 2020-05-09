package dao_project;

import java.sql.Connection;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import objet_Métier.Exemplaire;


public class ExemplaireDAO {

	
	private Statement state;
	private ResultSet result;
	
	public ExemplaireDAO(Connection conn)
	{
		try {
		       if(this.state != null)
		       {
		       System.out.println("statement deja cree !");
		       }else {
		    	   this.state=conn.createStatement();
			       System.out.println("Nouvelle statement cree !");
		       }
		    }catch(Exception e)
		    {
		    	System.out.println("dude !!");
		     	e.getStackTrace();
		    }
	}
	
	public boolean add(Exemplaire ex)
	{
		try {
			
			//préparation de la requete sql
			String query = "INSERT INTO exemplaire SET id_Oeuvre='"+ex.getId_Exemp_Oeuvre()+"', dispo_Exemplaire ="+true+", etat_Exemplaire ='"+ex.getEtat_Exemp()+"';";       
			
			
			System.out.println("INSERT INTO exemplaire SET id_Oeuvre='"+ex.getId_Exemp_Oeuvre()+"', dispo_Exemplaire ="+true+"', etat_Exemplaire ='"+ex.getEtat_Exemp()+"';");
			
			// exécution de la requete
			state.executeUpdate(query);
			System.out.println("requete exécute !!");

			return true;
			
		}catch(SQLException e)
		{
			System.out.println(" error requete !");
			e.getStackTrace();
			return false;
		}finally {
		    try { result.close(); System.out.println("result fermeéééééé"); } catch (Exception e) { /* ignored */ }
		    try { state.close(); System.out.println("state fermeéééééé"); } catch (Exception e) { /* ignored */ }
//		    try { connect.close(); System.out.println("connect fermeéééééé find()"); } catch (Exception e) { /* ignored */ }
		}
		
	}
	
	public boolean update(Exemplaire ex)
	{
		try {
			
			//préparation de la requete sql
			String query = "UPDATE exemplaire SET dispo_Exemplaire ='"+ex.getDispo_Exemp()+"', etat_Exemplaire ='"+ex.getEtat_Exemp()+"' WHERE id_Exemplaire = "+ex.getIdExemplaire()+";";  
			
			// exécution de la requete
			state.executeUpdate(query);
	
			return true;
			
		}catch(SQLException e)
		{
			e.getStackTrace();
			return false;
		}finally {
		    try { result.close(); System.out.println("result fermeéééééé"); } catch (Exception e) { /* ignored */ }
		    try { state.close(); System.out.println("state fermeéééééé"); } catch (Exception e) { /* ignored */ }
//		    try { connect.close(); System.out.println("connect fermeéééééé find()"); } catch (Exception e) { /* ignored */ }
		}
	}

	public boolean delete(int id)
	{
		try {
		
			//préparation de la requete sql
			String query = "DELETE FROM Exemplaire WHERE id_Exemplaire = "+id+";";  
			
			// exécution de la requete
			state.executeUpdate(query);

			System.out.println("Deleting with success");
			return true;
			
		}catch(SQLException e)
		{
			e.getStackTrace();
			return false;
		}finally {
		    try { result.close(); System.out.println("result fermeéééééé"); } catch (Exception e) { /* ignored */ }
		    try { state.close(); System.out.println("state fermeéééééé"); } catch (Exception e) { /* ignored */ }
//		    try { connect.close(); System.out.println("connect fermeéééééé find()"); } catch (Exception e) { /* ignored */ }
		}
	}

	public Exemplaire findById(int id)
	{
		Exemplaire ex = new Exemplaire();
		
		try {
	
			//préparation de la requete sql
			String query = "select exemplaire.id_Exemplaire, oeuvre.titre_oeuvre, dispo_Exemplaire, etat_Exemplaire from oeuvre join exemplaire on oeuvre.id_Oeuvre=exemplaire.id_Oeuvre WHERE id_Exemplaire = "+id+";";  
			
			// exécution de la requete et récupération des données 
             result = state.executeQuery(query);
			
			
			//remplissage de l'objet Usager
			while(result.next())
			{
				ex.setIdExemplaire(result.getInt(1));
				ex.setTitreOeuvre((result.getString(2)));
				ex.setDisponible_Exemp(result.getInt(3));
				ex.setEtat_Exemp(result.getString(4));
			
				
			System.out.println("objet rempli ");
			}

			return ex;
		}catch(SQLException e)
		{
			e.getStackTrace();
			
			return null;
		}finally {
		    try { result.close(); System.out.println("result fermeéééééé"); } catch (Exception e) { /* ignored */ }
		    try { state.close(); System.out.println("state fermeéééééé"); } catch (Exception e) { /* ignored */ }
//		    try { connect.close(); System.out.println("connect fermeéééééé find()"); } catch (Exception e) { /* ignored */ }
		}
	}

	public ArrayList<Exemplaire> find()
	{
		ArrayList<Exemplaire> exs = new ArrayList<Exemplaire>();
		try {
			
			//préparation de la requete sql
			String query = "select exemplaire.id_Exemplaire, oeuvre.titre_oeuvre, dispo_Exemplaire, etat_Exemplaire from oeuvre join exemplaire on oeuvre.id_Oeuvre=exemplaire.id_Oeuvre;";  
			
			// exécution de la requete et récupération des données 
            result = state.executeQuery(query);
			

			while(result.next())
			{
				exs.add(new Exemplaire(result.getInt(1),result.getString(2),result.getInt(3),result.getString(4)));	
			}

			return exs;
			
		}catch(SQLException e)
		{
	        e.getStackTrace();		
	 		return null;
		}finally {
		    try { result.close(); System.out.println("result fermeéééééé"); } catch (Exception e) { /* ignored */ }
		    try { state.close(); System.out.println("state fermeéééééé"); } catch (Exception e) { /* ignored */ }
//		    try { connect.close(); System.out.println("connect fermeéééééé find()"); } catch (Exception e) { /* ignored */ }
		}
	}
	
	public ArrayList<Exemplaire> findByTitle(String title)
	{
		// avec le titre 
		ArrayList<Exemplaire> exs = new ArrayList<Exemplaire>();
		
		try {
			 
			//préparation de la requete sql
			String query = "select exemplaire.id_Exemplaire, oeuvre.titre_oeuvre, dispo_Exemplaire, etat_Exemplaire from oeuvre join exemplaire on oeuvre.id_Oeuvre=exemplaire.id_Oeuvre where oeuvre.titre_oeuvre = '"+title+"' ;";  
			
			System.out.println(query);
			// exécution de la requete et récupération des données 
			result = state.executeQuery(query);
			
			
			
			//remplissage de l'objet Usager
			while(result.next())
			{
				exs.add(new Exemplaire(result.getInt(1),result.getString(2),result.getInt(3),result.getString(4)));	
			}

			return exs;
		}catch(SQLException e)
		{
			e.getStackTrace();
			
			return null;
		}finally {
		    try { result.close(); System.out.println("result fermeéééééé"); } catch (Exception e) { /* ignored */ }
		    try { state.close(); System.out.println("state fermeéééééé"); } catch (Exception e) { /* ignored */ }
//		    try { connect.close(); System.out.println("connect fermeéééééé find()"); } catch (Exception e) { /* ignored */ }
		}
	}

	
}
