/*
 * Chaque type de personnage possede des caracteristiques particulieres :
 * 	> Les Humains ont un bonus de +1 sur tous type d'arme.
 */

package javaKraft;

public class humain extends personnage implements race {
	
 // ---------------------------------------------------------------------------------------------------------
 // variables -----------------------------------------------------------------------------------------------
	
    //private final int bonusArme = 1;

 // ---------------------------------------------------------------------------------------------------------
 // constructeurs -------------------------------------------------------------------------------------------
	
	humain( String name , boolean sexe , String craftName )
    {
        super( name , HUMAIN , sexe , craftName );
        setCriDeGuerre( sexe ? "Chat-bite !" : "Viens te battre, espece d'encule !" );
    }
	
	humain( String name , boolean sexe )
	{
		this( name , sexe , CRAFT_UNDEFINED );
	}

    humain( String name )
    {
        this( name , DEFAULT_SEX );
    }
    
    humain( String name , String craftName )
    {
    	this( name , DEFAULT_SEX , craftName );
    }
    
 // ---------------------------------------------------------------------------------------------------------
 // methodes ------------------------------------------------------------------------------------------------
 // methodes heritee de l'interface race --------------------------------------------------------------------
    
     // attaque de l'humain
    public void attaquer( personnage ennemi )
    {
    	 // sans arme en main, l'humain gagne +3 en attaque
    	if ( hasNoWeaponInHand() )
    		setBonusAttaque( getBonusAttaque() + 3 );
    	
    	 // attaque de l'ennemi
    	attaquerEnnemi( ennemi );
    	
    	 // fin de l'attaque, le bonus d'attaque est reevalue e son ancienne valeur
    	if ( hasNoWeaponInHand() )
    		setBonusAttaque( getBonusAttaque() - 3 );
    }
    
     // parade de l'humain : 100% de reussite contre un elfe, 50% contre un orc
    public void parer( personnage ennemi )
    {
    	 // jet de de simule si l'ennemi est un orc
    	if ( ennemi.getTypePersoString().equals( "orc" ))
    		succesfulParade = ((int)(Math.random() * 2) == 0 );
    }
    
     // le pouvoir d'un humain : bonus +20 pour ses points de vie
    public void utiliserPouvoir( personnage ennemi )
    {
    	sortieConsole( getName() + " invoque son pouvoir : Par le pouvoir du Crene Ancestral !" );
    	setPointsVie( getPointsVie() + 20 );
    }
    
 // ---------------------------------------------------------------------------------------------------------
 // methodes heritee de la classe actionsCommunes -----------------------------------------------------------
    
     // l'humain attaque
    public void actionAttaquer( personnage ennemi )
    {
    	super.actionAttaquer( ennemi );
    	attaquer( ennemi );
    }
    
     // parade de l'humain
    public void actionParer( personnage ennemi )
    {
    	parer( ennemi );
        super.actionParer( ennemi );
    }
    
     // l'humain utilise son pouvoir
    public void actionUtiliserPouvoir( personnage ennemi )
    {
    	super.actionUtiliserPouvoir( ennemi );
    	utiliserPouvoir( ennemi );
    }
    
     // rien
    public void actionWait()
    {}
}