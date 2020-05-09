package objet_Métier;

public class Emprunt {

	private int id_Exemplaire;
	private int id_Emprunt;
	private String titre;
	private String nom_Usager;
	private String prenom_Usager;
	private String date_Emprunt;
	private String dateRetour_theo;
	private String dateRetour_Eff;
	
	// pour l'ajout d'un emprunt
	private int id_Usager;
	
	//pour le retour depuis la base des données
	public Emprunt (int idEmprunt, int idExemplaire, String titre ,String nom,String prenom ,String date_Emprunt, String dateRe_theo, String dateRe_Eff )
	{
		this.id_Emprunt=idEmprunt;
		this.id_Exemplaire=idExemplaire;
		this.titre=titre;
		this.nom_Usager=nom;
		this.prenom_Usager = prenom;
		this.date_Emprunt=date_Emprunt;
		this.dateRetour_theo=dateRe_theo;
		this.dateRetour_Eff=dateRe_Eff;
	}
	
	// pour la modification d'un emprunt
	public Emprunt(int idEmprunt,int idExemplaire, int idUsager, String date_Emprunt)
	{
		this.id_Emprunt=idEmprunt;
		this.id_Exemplaire=idExemplaire;
		this.id_Usager=idUsager;
		this.dateRetour_Eff=date_Emprunt;
	}
	
	// pour l'ajout d'un emprunt
		public Emprunt(int idExemplaire, int idUsager, String date_Emprunt)
		{
			
			this.id_Exemplaire=idExemplaire;
			this.id_Usager=idUsager;
			this.date_Emprunt=date_Emprunt;
			this.dateRetour_theo=Utilities.DateIncrementer(Utilities.DateTransformer(date_Emprunt), 7);
			
		}
//	
	public Emprunt() {
		
	}
	
	public int getId_Emprunt()
	{
		return this.id_Emprunt;
	}
	
	public int getId_Emprunt_Exemplaire()
	{
		return this.id_Exemplaire;
	}
	
	public String getTitre()
	{
		return this.titre;
	}
	
	public String getNom()
	{
		return this.nom_Usager;
	}
	
	public String getPrenom()
	{
		return this.prenom_Usager;
	}
	
	public String getDateEmprunt()
	{
		return this.date_Emprunt;
	}
	
	public String getDateRetour_theo()
	{
		return this.dateRetour_theo;
	}
	
	public String getDateRetour_Eff()
	{
		return this.dateRetour_Eff;
	}

	public int getId_Emprunt_Usager()
	{
		return this.id_Usager;
	}
	
	public void setIdEmprunt(int idEmprunt)
	{
		this.id_Emprunt=idEmprunt;
	}
	
	public void setId_Emprunt_Exemplaire(int idOeuvre)
	{
		this.id_Exemplaire=idOeuvre;
	}
	
	public void setTitre(String titre)
	{
		this.titre=titre;
	}
	
	public void setNom(String nom)
	{
		this.nom_Usager = nom;
	}
	
	public void setPrenom(String prenom)
	{
		this.prenom_Usager=prenom;
	}
	
	public void setDateEmprunt(String date_Emprunt)
	{
		this.date_Emprunt=date_Emprunt;
	}
	
	public void setDateRetour_theo(String dateRe_theo)
	{
		this.dateRetour_theo=dateRe_theo;
	}

	public void setDateRetour_Eff(String dateRe_Eff)
	{
		this.dateRetour_Eff=dateRe_Eff;
	}
	
	public void setId_Emprunt_Usager(int idUsager) 
	{
		this.id_Usager=idUsager;
	}
	
	public String toString()
	{
		return " Emprunt : id_Emprunt = "+this.id_Emprunt+" id_Oeuvre = "+this.id_Exemplaire+", titre= "+this.titre+", nom="+this.nom_Usager+", prenom="+this.prenom_Usager+", Date d'Emprunt = "+this.date_Emprunt+", Date retour theorique = "+this.dateRetour_theo+", Date retour Effective = "+this.dateRetour_Eff;                                                             
	}
	

//  id_Usager = "+this.id_Usager+",
}
