 package dao_project;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import objet_Métier.Emprunt;

public class EmpruntDAO {

	
	private Connection connect;
	private Statement state;
	private ResultSet result;
	
	public EmpruntDAO(Connection conn)
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
	
	public boolean add(Emprunt em)
	{
		try {
	 	
			//Enregistrement de l'emprunt dans la base des données 
			
			
			
			String queryAjoutEmprunt= "INSERT INTO emprunt SET  id_Exemplaire="+em.getId_Emprunt_Exemplaire() +", id_usager ="+em.getId_Emprunt_Usager()+", dateEmprunt ='"+em.getDateEmprunt()+"', dateRetour_theo ='"+em.getDateRetour_theo()+"';"   ;       
			System.out.println(queryAjoutEmprunt);
			
			state.executeUpdate(queryAjoutEmprunt);
			System.out.println("Enregistrement de l'emprunt dans la base des données  !!!!DONE!!!!");
			
			
			
			// Décrmentation du nombre des exemplaires disponible dans la table oeuvre
			String queryDecNbrExem_Oeuvre = "UPDATE Oeuvre SET nbr_disponible_Oeuvre = nbr_disponible_Oeuvre-1 WHERE id_Oeuvre=(SELECT id_Oeuvre FROM Exemplaire WHERE id_Exemplaire ="+em.getId_Emprunt_Exemplaire()+");";
			state.executeUpdate(queryDecNbrExem_Oeuvre);
			System.out.println("Décrmentation du nombre des exemplaires disponible dans la table oeuvre !!!!DONE!!!!");
			
			
			
			//Enregistrement de l'indisponiblité de l'exemeplaire dans la table Exemplaire 
			String queryDispo_ExemplaireFalse = "UPDATE Exemplaire SET Dispo_Exemplaire = Dispo_Exemplaire-1 WHERE id_Exemplaire="+em.getId_Emprunt_Exemplaire()+";";
			state.executeUpdate(queryDispo_ExemplaireFalse);
			System.out.println("Enregistrement de l'indisponiblité de l'exemeplaire dans la table Exemplaire  !!!!DONE!!!!");
			
			
			return true;
			
		}catch(SQLException e)
		{
			System.out.println("error requete ");
			e.getStackTrace();
			return false;
			
		}finally {
		    try { result.close(); System.out.println("result fermeéééééé"); } catch (Exception e) { /* ignored */ }
		    try { state.close(); System.out.println("state fermeéééééé"); } catch (Exception e) { /* ignored */ }
		    try { connect.close(); System.out.println("connect fermeéééééé find()"); } catch (Exception e) { /* ignored */ }
		}
		
	}

	public boolean update(Emprunt em)
	{
		try {
			 	
			System.out.println("UPDATE est làààà");
			//préparation de la requete sql
			String query = "UPDATE emprunt SET id_Exemplaire='"+em.getId_Emprunt_Exemplaire() +"', id_Usager ='"+em.getId_Emprunt_Usager()+"', dateRetour_Eff ='"+em.getDateRetour_Eff()+"' WHERE id_Emprunt ='"+em.getId_Emprunt()+"';";  
				
			System.out.println(query);
			// exécution de la requete
			state.executeUpdate(query);
			
			if(em.getDateRetour_Eff() != null)
			{
				String queryToUpdateExemplaire = "update exemplaire set dispo_Exemplaire = '1' ";
				state.executeUpdate(queryToUpdateExemplaire);
			}
			
			return true;
			
		}catch(SQLException e)
		{
			e.getStackTrace();
			return false;
		}finally {
		    try { result.close(); System.out.println("result fermeéééééé"); } catch (Exception e) { /* ignored */ }
		    try { state.close(); System.out.println("state fermeéééééé"); } catch (Exception e) { /* ignored */ }
		    try { connect.close(); System.out.println("connect fermeéééééé find()"); } catch (Exception e) { /* ignored */ }
		}
	}

	public boolean delete(int id)
	{
		try {

			//préparation de la requete sql
			String query = "DELETE FROM emprunt WHERE id_Emprunt = "+id+";";  
			
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
		    try { connect.close(); System.out.println("connect fermeéééééé find()"); } catch (Exception e) { /* ignored */ }
		}
	}

	public ArrayList<Emprunt> find()
	{
		ArrayList<Emprunt> ems = new ArrayList<Emprunt>();
		try {

			//préparation de la requete sql
			String query = "select em.id_Emprunt, em.id_Exemplaire,oe.titre_Oeuvre,user.nom_Usager,user.prenom_Usager,em.dateEmprunt,em.dateRetour_theo,em.dateRetour_Eff from emprunt as em, oeuvre as Oe, usager as user, exemplaire as ex where em.id_Exemplaire=ex.id_Exemplaire and Oe.id_Oeuvre=ex.id_Oeuvre and user.id_Usager=em.id_Usager;";  
			
			// exécution de la requete et récupération des données 
			ResultSet result = state.executeQuery(query);
			

			while(result.next())
			{
				ems.add(new Emprunt(result.getInt(1),result.getInt(2),result.getString(3),result.getString(4),result.getString(5),result.getString(6),result.getString(7),result.getString(8)));	
			}
			
			return ems;
			
		}catch(SQLException e)
		{
	        e.getStackTrace();		
	 		return null;
		}finally {
		    try { result.close(); System.out.println("result fermeéééééé"); } catch (Exception e) { /* ignored */ }
		    try { state.close(); System.out.println("state fermeéééééé"); } catch (Exception e) { /* ignored */ }
		    try { connect.close(); System.out.println("connect fermeéééééé find()"); } catch (Exception e) { /* ignored */ }
		}
	}
	
	public ArrayList<Emprunt> findByUserId(int id)
	{
		ArrayList<Emprunt> em = new ArrayList<Emprunt>();
		try {
			System.out.println("findByUserId est la");
			//préparation de la requete sql
			String query = "select em.id_Emprunt, em.id_Exemplaire,oe.titre_Oeuvre,user.nom_Usager,user.prenom_Usager,em.dateEmprunt,em.dateRetour_theo,em.dateRetour_Eff from emprunt as em, oeuvre as Oe, usager as user, exemplaire as ex where em.id_Exemplaire=ex.id_Exemplaire and Oe.id_Oeuvre=ex.id_Oeuvre and user.id_Usager=em.id_Usager and em.id_Usager="+id+";";  
			
			// exécution de la requete et récupération des données 
			ResultSet result = state.executeQuery(query);
			

			while(result.next())
			{
				em.add(new Emprunt(result.getInt(1),result.getInt(2),result.getString(3),result.getString(4),result.getString(5),result.getString(6),result.getString(7),result.getString(8)));	
			}
			
			return em;
			
		}catch(SQLException e)
		{
			e.getStackTrace();
			
			return null;
		}finally {
		    try { result.close(); System.out.println("result fermeéééééé"); } catch (Exception e) { /* ignored */ }
		    try { state.close(); System.out.println("state fermeéééééé"); } catch (Exception e) { /* ignored */ }
		    try { connect.close(); System.out.println("connect fermeéééééé find()"); } catch (Exception e) { /* ignored */ }
		}
	}

	public ArrayList<Emprunt> findByUserName(String nom, String prenom)
	{
		ArrayList<Emprunt> em = new ArrayList<Emprunt>();
		try {

			System.out.println("findByUserName est la");
			//préparation de la requete sql
			String query = "select em.id_Emprunt, em.id_Exemplaire,oe.titre_Oeuvre,user.nom_Usager,user.prenom_Usager,em.dateEmprunt,em.dateRetour_theo,em.dateRetour_Eff from emprunt as em, oeuvre as Oe, usager as user, exemplaire as ex where em.id_Exemplaire=ex.id_Exemplaire and Oe.id_Oeuvre=ex.id_Oeuvre and user.id_Usager=em.id_Usager and user.nom_Usager="+nom+" and user.prenom_Usager="+prenom+";";  
			
			// exécution de la requete et récupération des données 
			ResultSet result = state.executeQuery(query);
			

			while(result.next())
			{
				em.add(new Emprunt(result.getInt(1),result.getInt(2),result.getString(3),result.getString(4),result.getString(5),result.getString(6),result.getString(7),result.getString(8)));	
			}
			
			return em;
			
		}catch(SQLException e)
		{
			e.getStackTrace();
			
			return null;
		}finally {
		    try { result.close(); System.out.println("result fermeéééééé"); } catch (Exception e) { /* ignored */ }
		    try { state.close(); System.out.println("state fermeéééééé"); } catch (Exception e) { /* ignored */ }
		    try { connect.close(); System.out.println("connect fermeéééééé find()"); } catch (Exception e) { /* ignored */ }
		}
	}
	
	public ArrayList<Emprunt> findByIdEmprunt(int id)
	{
		ArrayList<Emprunt> em = new ArrayList<Emprunt>();
		try {

			System.out.println("findByIdEmprunt est la");
			//préparation de la requete sql
			String query = "select em.id_Emprunt, em.id_Exemplaire,oe.titre_Oeuvre,user.nom_Usager,user.prenom_Usager,em.dateEmprunt,em.dateRetour_theo,em.dateRetour_Eff from emprunt as em, oeuvre as Oe, usager as user, exemplaire as ex where em.id_Exemplaire=ex.id_Exemplaire and Oe.id_Oeuvre=ex.id_Oeuvre and user.id_Usager=em.id_Usager and em.id_Emprunt="+id+";";  
			
			// exécution de la requete et récupération des données 
			ResultSet result = state.executeQuery(query);
			

			while(result.next())
			{
				em.add(new Emprunt(result.getInt(1),result.getInt(2),result.getString(3),result.getString(4),result.getString(5),result.getString(6),result.getString(7),result.getString(8)));	
			}
			
			return em;
			
		}catch(SQLException e)
		{
			e.getStackTrace();
			
			return null;
		}finally {
		    try { result.close(); System.out.println("result fermeéééééé"); } catch (Exception e) { /* ignored */ }
		    try { state.close(); System.out.println("state fermeéééééé"); } catch (Exception e) { /* ignored */ }
		    try { connect.close(); System.out.println("connect fermeéééééé find()"); } catch (Exception e) { /* ignored */ }
		}
	}
	
	public ArrayList<Emprunt> findByExemplaire(int id)
	{
		ArrayList<Emprunt> em = new ArrayList<Emprunt>();
		try {

			System.out.println("findByExemplaire est la");
			//préparation de la requete sql
			String query = "select em.id_Emprunt, em.id_Exemplaire,oe.titre_Oeuvre,user.nom_Usager,user.prenom_Usager,em.dateEmprunt,em.dateRetour_theo,em.dateRetour_Eff from emprunt as em, oeuvre as Oe, usager as user, exemplaire as ex where em.id_Exemplaire=ex.id_Exemplaire and Oe.id_Oeuvre=ex.id_Oeuvre and user.id_Usager=em.id_Usager and em.id_Exemplaire="+id+";";  
			
			// exécution de la requete et récupération des données 
			ResultSet result = state.executeQuery(query);
			

			while(result.next())
			{
				em.add(new Emprunt(result.getInt(1),result.getInt(2),result.getString(3),result.getString(4),result.getString(5),result.getString(6),result.getString(7),result.getString(8)));	
			}
			
			return em;
			
		}catch(SQLException e)
		{
			e.getStackTrace();
			
			return null;
		}finally {
		    try { result.close(); System.out.println("result fermeéééééé"); } catch (Exception e) { /* ignored */ }
		    try { state.close(); System.out.println("state fermeéééééé"); } catch (Exception e) { /* ignored */ }
		    try { connect.close(); System.out.println("connect fermeéééééé find()"); } catch (Exception e) { /* ignored */ }
		}
	}
	
	public Emprunt findByIdEmpruntForUpdate(int id)
	{
		Emprunt em = new Emprunt();
		try {

			System.out.println("findByIdEmprunt est la");
			//préparation de la requete sql
			String query = "select * from emprunt where id_Emprunt="+id+";";  
			
			// exécution de la requete et récupération des données 
			ResultSet result = state.executeQuery(query);
			

			while(result.next())
			{
				em = new Emprunt(result.getInt(1),result.getInt(2),result.getInt(3),result.getString(6));	
			}
			
			return em;
			
		}catch(SQLException e)
		{
			e.getStackTrace();
			
			return null;
		}finally {
		    try { result.close(); System.out.println("result fermeéééééé"); } catch (Exception e) { /* ignored */ }
		    try { state.close(); System.out.println("state fermeéééééé"); } catch (Exception e) { /* ignored */ }
		    try { connect.close(); System.out.println("connect fermeéééééé find()"); } catch (Exception e) { /* ignored */ }
		}
	}
	
}
