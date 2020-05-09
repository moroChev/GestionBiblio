package objet_Métier;

public class Oeuvre {

	private int id_Oeuvre;
	private String titre_Oeuvre;
	private String auteur_Oeuvre;
	private int nbr_Exempl_Oeuvre;
	private int nbr_disponible_Oeuvre;
	private String categorie_Oeuvre;
	
	public Oeuvre(int id, String titre, String auteur, int nbrEx, int nbrdispo, String categ) {
	//Pour l'affichage
		this.id_Oeuvre=id;
		this.titre_Oeuvre=titre;
		this.auteur_Oeuvre=auteur;
		this.nbr_Exempl_Oeuvre=nbrEx;
		this.nbr_disponible_Oeuvre=nbrdispo;
		this.categorie_Oeuvre=categ;
	}
	
	public Oeuvre(int id, String titre, String auteur, int nbrEx, String categ) {
		//Pour modification
		this.id_Oeuvre=id;
		this.titre_Oeuvre=titre;
		this.auteur_Oeuvre=auteur;
		this.nbr_Exempl_Oeuvre=nbrEx;
		this.categorie_Oeuvre=categ;
	}
	
	public Oeuvre(String titre, String auteur, int nbrEx, String categ)
	{
		//
		this.titre_Oeuvre=titre;
		this.auteur_Oeuvre=auteur;
		this.nbr_Exempl_Oeuvre=nbrEx;
		this.nbr_disponible_Oeuvre= nbrEx;
		this.categorie_Oeuvre=categ;
	}
	
	public Oeuvre() {}
	
	public int getIdOeuvre()
	{
		return this.id_Oeuvre;
	}
	
	public String getTitreOeuvre()
	{
		return this.titre_Oeuvre;
	}
	
	public String getAuteurOeuvre()
	{
		return this.auteur_Oeuvre;
	}
	
	public int getNbrExemplOeuvre()
	{
		return this.nbr_Exempl_Oeuvre;
	}
	
	public int getNbrDispOeuvre()
	{
		return this.nbr_disponible_Oeuvre;
	}
	
	public String getCategorieOeuvre()
	{
		return this.categorie_Oeuvre;
	}

	public void setIdOeuvre(int id)
	{
		this.id_Oeuvre=id;
	}

	public void setTitreOeuvre(String titre)
	{
		this.titre_Oeuvre=titre;	
	}

	public void setAuteurOeuvre(String auteur)
	{
		this.auteur_Oeuvre=auteur;
	}
	
	public void setNbrExempOeuvre(int nbrEx)
	{
		this.nbr_Exempl_Oeuvre=nbrEx;
	}
	
	public void setNbrDisponibleOeuvre(int nbrdispo)
	{
		this.nbr_disponible_Oeuvre=nbrdispo;
	}
	
	public void setCategorieOeuvre(String categ)
	{
		this.categorie_Oeuvre=categ;
	}

	public String toString()
	{
		return " Oeuvre : id = "+this.id_Oeuvre+" titre : "+this.titre_Oeuvre+" auteur : "+this.auteur_Oeuvre+" nbr Exemplaire : "+this.nbr_Exempl_Oeuvre+" nbr dispo : "+this.nbr_disponible_Oeuvre+" Categorie = "+this.categorie_Oeuvre;
	}
	
}
