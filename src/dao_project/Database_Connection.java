package dao_project;

import java.sql.Connection;
import java.sql.DriverManager;

public class Database_Connection {

	private static String url = "jdbc:mysql://localhost:3306/biblio?autoReconnect=true&useSSL=false" ;
	private static String user = "root";
	private static String passwd = "";
	private static String driver = "com.mysql.jdbc.Driver";
	private static Connection conn;

	public Database_Connection() {
		
	}
	
	public static Connection getConnection() {
		if( conn != null )
		{
		System.out.println("connexion déja faite !!");
		return conn;
		}
		
		try {

			Class.forName(driver);
			
			conn = DriverManager.getConnection(url, user, passwd);
			
			System.out.println("connexion sans probléme La classe DataBase !!!");
		}
		catch(Exception e)
		{
			e.printStackTrace();	
		}
		
		return conn;	
	}
	
	
}
