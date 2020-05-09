package dao_project;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;


import objet_Métier.Usager;


public class UsagerDAO {	
	

	private Connection connect;
	private Statement state;
	private ResultSet result;
	
	public UsagerDAO(Connection conn)
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

	public boolean add(Usager user)
	{
		try {
	 			
			//préparation de la requete sql
			String query = "INSERT INTO usager SET nom_usager='"+user.getNomUsager()+"', prenom_usager ='"+user.getPrenomUsager()+"', CIN_usager ='"+user.getCINUsager()+"', CNE_usager ='"+user.getCNEUsager()+"';";       
			System.out.println("la requete est : "+query);
			// exécution de la requete
			state.executeUpdate(query);

			System.out.println("la déconnexion a la base ");
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
	
	public boolean update(Usager user)
	{
		try {
			 
			
			//préparation de la requete sql
			String query = "UPDATE usager SET nom_usager='"+user.getNomUsager()+"', prenom_usager ='"+user.getPrenomUsager()+"', CIN_usager ='"+user.getCINUsager()+"', CNE_usager ='"+user.getCNEUsager()+"' WHERE id_usager = "+user.getIdUsager()+";";  
			System.out.println(query);
			
			// exécution de la requete
			state.executeUpdate(query);
			System.out.println("requete exécutéé");

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
			System.out.println("La fonction DELETE"); 		
			
			//préparation de la requete sql
			String query = "DELETE FROM usager WHERE id_usager = "+id+";";  
			
			System.out.println(query);
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

	public Usager findById(int id)
	{
		Usager user = new Usager();
		
		try {
			 			
			//préparation de la requete sql
			String query = "select * FROM usager WHERE id_usager = "+id+";";  
			
			// exécution de la requete et récupération des données 
			result = state.executeQuery(query);
			
			//remplissage de l'objet Usager
			while(result.next())
			{
			user.setIdUsager(result.getInt(1));
			user.setNomUsager(result.getString(2));
			user.setPrenomUsager(result.getString(3));
			user.setCINUsager(result.getString(4));
			user.setCNEUsager(result.getString(5));
			}
				
			return user;
			
		}catch(SQLException e)
		{
			e.getStackTrace();
			
			return null;
		}
		finally {
		    try {System.out.println("result pas encore fermé");  result.close(); System.out.println("result fermeéééééé"); } catch (Exception e) { /* ignored */ }
		    try {System.out.println("statement pas encore fermé"); state.close(); System.out.println("state fermeéééééé"); } catch (Exception e) { /* ignored */ }
		    try {
		    	System.out.println("connect pas encore fermé");  
		            if (connect != null) {
		    	System.out.println("connect != null donc pas encore fermé "); connect.close();
			        }else {
			        	System.out.println("connect == null");}
			System.out.println("connect fermeéééééé find()"); 
			} catch (Exception e) { /* ignored */ }
		}
	}

	public ArrayList<Usager> find()
	{
		ArrayList<Usager> users = new ArrayList<Usager>();
		
		try {
			
			//préparation de la requete sql
			String query = "select * FROM usager"; 
			
			System.out.println("la requete est : "+query);
			
			// exécution de la requete et récupération des données 
			result = state.executeQuery(query);
			System.out.println("requete execute avec succes !");
			

			while(result.next())
			{
				users.add(new Usager(result.getInt(1),result.getString(2),result.getString(3),result.getString(4),result.getString(5)));		
			}
				
			return users;
			
		}catch(SQLException e)
		{
	        e.getStackTrace();	
	        System.out.println(" dddddddddd");
	 		return null;
		}finally {
		    try { result.close(); System.out.println("result fermeéééééé"); } catch (Exception e) { /* ignored */ }
		    try { state.close(); System.out.println("state fermeéééééé"); } catch (Exception e) { /* ignored */ }
		    try { connect.close(); System.out.println("connect fermeéééééé find()"); } catch (Exception e) { /* ignored */ }
		}
	}
	
	public Usager findByName(String Fname,String Pname)
	{
        Usager user = new Usager();
		
		try {
			
			//préparation de la requete sql
			String query = "select * FROM usager WHERE nom_usager = '"+Fname+"' and prenom_usager = '"+Pname+"';";  
			System.out.println("la requete est : "+query);
			
			// exécution de la requete et récupération des données 
			result = state.executeQuery(query);
			System.out.println("requete execute avec succes !");
			
			
			//remplissage de l'objet Usager
			while(result.next())
			{
			user.setIdUsager(result.getInt(1));
			user.setNomUsager(result.getString(2));
			user.setPrenomUsager(result.getString(3));
			user.setCINUsager(result.getString(4));
			user.setCNEUsager(result.getString(5));
			}
			System.out.println("objet rempli ");
						
			return user;
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
	
	public boolean isExist(int id)
	{
		boolean res = false;
		try {
			System.out.println("La fonction isExist");

			String query = "select * from usager where id_usager="+id+";";
		
			result = state.executeQuery(query);
			
			if(result.getInt(1) != 0) res = true;
			else res = false;
			
			return res;
			
		}catch(SQLException e)
		{
			System.out.println("Error isExist");
			e.getMessage();
			return res;
		}finally {
		    try { result.close(); System.out.println("result fermeéééééé"); } catch (Exception e) { /* ignored */ }
		    try { state.close(); System.out.println("state fermeéééééé"); } catch (Exception e) { /* ignored */ }
		    try { connect.close(); System.out.println("connect fermeéééééé find()"); } catch (Exception e) { /* ignored */ }
		}
	}
}
