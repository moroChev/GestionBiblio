package objet_Métier;

public class Usager {

	private int id_usager;
	private String nom_usager;
	private String prenom_usager;
	private String CIN_usager;
	private String CNE_usager;
	
	public Usager(int id, String nom, String prenom, String CIN, String CNE)
	{
		this.id_usager=id;
		this.nom_usager=nom;
		this.prenom_usager=prenom;
		this.CIN_usager=CIN;
		this.CNE_usager=CNE;
	}
	
	public Usager(String nom, String prenom, String CIN, String CNE) {
		
		this.nom_usager=nom;
		this.prenom_usager=prenom;
		this.CIN_usager=CIN;
		this.CNE_usager=CNE;
	}
	
	public Usager() {
		
	}
	
	public int getIdUsager()
	{
		return this.id_usager;
	}
	
	public String getNomUsager()
	{
		return this.nom_usager;
	}
	
	public String getPrenomUsager()
	{
		return this.prenom_usager;
	}
	
	public String getCINUsager()
	{
		return this.CIN_usager;
	}
	
	public String getCNEUsager()
	{
		return this.CNE_usager;
	}
	
	public void setIdUsager(int id)
	{
		this.id_usager=id;
	}
	
	public void setNomUsager(String nom)
	{
		this.nom_usager=nom;
	}
	
	public void setPrenomUsager(String prenom)
	{
		this.prenom_usager=prenom;
	}
 
    public void setCINUsager(String CIN)
    {
    	this.CIN_usager=CIN;
    }
    
    public void setCNEUsager(String CNE)
    {
    	this.CNE_usager=CNE;
    }
    
    public String toString() {
    	
    	return " Usager :  id = "+this.id_usager+" nom = "+this.nom_usager+" prenom = "+this.prenom_usager+" CIN = "+this.CIN_usager+" CNE = "+this.CNE_usager;                                                     
    }
    
}
