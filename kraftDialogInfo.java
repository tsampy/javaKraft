package javaKraft;

 // boete de dialogue permettant de creer un personnage
public class kraftDialogInfo {
	
 // variables privees -----------------------------------------------------------------------------
	private String nom , metier , race , sexe ;

 // constructeurs ---------------------------------------------------------------------------------
	public kraftDialogInfo()
	{
	}
	
	public kraftDialogInfo( String metier , String nom , String race , String sexe )
	{
		setMetier( metier );
		setNom( nom );
		setRace( race );
		setSexe( sexe );
	}

 // methodes --------------------------------------------------------------------------------------
	 // accesseur du metier
	public String getMetier()
	{
		return metier;
	}
	
	 // accesseur du nom
	public String getNom()
	{
		return nom;
	}
	
	 // accesseur de la race
	public String getRace()
	{
		return race;
	}
	
	 // accesseur du sexe
	public String getSexe()
	{
		return sexe;
	}
	
	 // mutateur du metier
	public void setMetier( String metier )
	{
		this.metier = metier;
	}
	
	 // mutateur du nom
	public void setNom( String nom )
	{
		this.nom = nom;
	}
	
	 // mutateur de la race
	public void setRace( String race )
	{
		this.race = race;
	}
	
	 // mutateur du sexe
	public void setSexe( String sexe )
	{
		this.sexe = sexe;
	}
	
	 // toString
	public String toString()
	{
		return ( getNom() != null ? getNom() : "" )
				+ ( getRace() != null ? " " + getRace() : "")
				+ ( getSexe() != null ? " " + getSexe() : "" )
				+ ( getMetier() != null ? " " + getMetier() : "" );
	}
}