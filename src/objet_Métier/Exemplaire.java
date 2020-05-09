package objet_Métier;

public class Exemplaire {

	private int id_Exemplaire ;
	private int id_Oeuvre;
	private int dispo_Exemplaire;
	private String etat_Exemplaire;
	private String titre_Oeuvre;
	
	
	//Pour récupérer depuis la base des donnees selon la describtion de la table Exemplaire
	public Exemplaire(int idExemplaire, int idOeuvre, int dispo, String etat ) {
		this.id_Exemplaire=idExemplaire;
		this.id_Oeuvre=idOeuvre;
		this.dispo_Exemplaire=dispo;
		this.etat_Exemplaire=etat;
	}
	
	//pour Modifier
	public Exemplaire(int id, int dispo, String etat)
	{
		this.id_Exemplaire=id;
		this.dispo_Exemplaire=dispo;
		this.etat_Exemplaire=etat;
	}
	
	//pour ajouter un exemplaire
	public Exemplaire(int idOeuvre, String etat) {
		this.id_Oeuvre=idOeuvre;
		this.etat_Exemplaire=etat;
	}
	
	//pour la jointure avec la table oeuvre 
	public Exemplaire(int idExemplaire, String titre, int dispo, String etat)
	{
		this.id_Exemplaire=idExemplaire;
		this.titre_Oeuvre=titre;
		this.dispo_Exemplaire=dispo;
		this.etat_Exemplaire=etat;
	}
	
	public Exemplaire() {
	}
	
	public int getIdExemplaire()
	{
		return this.id_Exemplaire;
	}
	
	public int getId_Exemp_Oeuvre()
	{
		return this.id_Oeuvre;
	}
	
	public int getDispo_Exemp()
	{
		return this.dispo_Exemplaire;
	}
	
	public String getEtat_Exemp()
	{
		return this.etat_Exemplaire;
	}

	public String getTitreOeuvre()
	{
		return this.titre_Oeuvre;
	}
	
	public void setIdExemplaire(int idExemplaire)
	{
		this.id_Exemplaire=idExemplaire;
	}
	
	public void setId_Exemp_Oeuvre(int idOeuvre)
	{
		this.id_Oeuvre=idOeuvre;
	}
	
	public void setDisponible_Exemp(int dispo)
	{
		this.dispo_Exemplaire=dispo;
	}
	
	public void setEtat_Exemp(String etat)
	{
		this.etat_Exemplaire=etat;
	}

	public void setTitreOeuvre(String titre)
	{
		this.titre_Oeuvre=titre;
	}
	
	
    public String toString()
    {
    	return " Exemplaire : id= "+this.id_Exemplaire+", id_Oeuvre = "+this.id_Oeuvre+", Est-ce Dispo : "+this.dispo_Exemplaire+", Etat : "+this.etat_Exemplaire+", Titre d'Ouvrage : "+this.titre_Oeuvre;
    }
    
    
}
