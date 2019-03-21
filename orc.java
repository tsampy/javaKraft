/*
 * La langue des orcs : http://clansombrevol.xoo.it/t5-LA-LANGUE-DES-ORCS.htm
 */

package javaKraft;

public class orc extends personnage {
	
	private int berserkState = 0;

 // ---------------------------------------------------------------------------------------------------------
 // constructeurs -------------------------------------------------------------------------------------------
	
    orc( String name , boolean sexe , String craftName )
    {
        super( name , ORC , sexe , craftName );
        setPointsAttaque( getPointsAttaque() + 2 );
        setPointsDefense( getPointsDefense() + 2 );
        setPointsVie( getPointsVie() - 10 );
        setCriDeGuerre( sexe ? "LokeNolosh !" : "Lok'Gregal Negar !" );
    }
    
    orc( String name , boolean sexe )
	{
		this( name , sexe , CRAFT_UNDEFINED );
	}

    orc( String name )
    {
        this( name , DEFAULT_SEX );
    }
    
    orc( String name , String craftName )
    {
    	this( name , DEFAULT_SEX , craftName );
    }
    
 // ---------------------------------------------------------------------------------------------------------
 // methodes ------------------------------------------------------------------------------------------------
 // methodes heritees de l'interface race -------------------------------------------------------------------
     
      // attaque de l'humain
     public void attaquer( personnage ennemi )
     {
    	  // si l'ennemi est un elfe et qu'il n'a pas de bouclier, l'orc gagne ORC_ATTACK_BONUS (+50) en attaque
    	 if (( ennemi.getTypePersoString() == "elfe" ) && ( ennemi.hasNoShieldInHand() ))
    	 {
    		 setBonusAttaque( getBonusAttaque() + ORC_ATTACK_BONUS );
    		 sortieConsole( getName() + " attaque un elfe sans bouclier, +" + ORC_ATTACK_BONUS + " attaque" );
    	 }
    		 
    	 super.actionAttaquer( ennemi );
    	 attaquerEnnemi( ennemi );
    	 
    	  // si l'ennemi est un elfe et qu'il n'a pas de bouclier, on retire le bonus ajoute avant l'attaque
    	 if (( ennemi.getTypePersoString() == "elfe" ) && ( ennemi.hasNoShieldInHand() ))
    		 setBonusAttaque( getBonusAttaque() - ORC_ATTACK_BONUS );
     }
     
      // l'orc peut parer n'importe quel coup, mais perd 2 points de defense
     public void parer( personnage ennemi )
     {
    	// retrait des points de defense, si ils sont superieurs e 2, sinon on tombe e zero
         setPointsDefense( 2 < getPointsDefense() ? getPointsDefense() - 2 : 0 );
     }
     
      // le pouvoir d'un orc : devenir enrage et donner un bonus de +5 d'attaque sur le prochain combat uniquement
     public void utiliserPouvoir( personnage ennemi )
     {
    	 sortieConsole( getName() + " invoque son pouvoir : Gol'Kosh !" );
    	 sortieConsole( getName() + " passe en mode BERSERK !!! Attaque +5 au prochain combat !"  );
    	 berserkState++;
    	 setBonusAttaque( 5 );
     }
     
 // ---------------------------------------------------------------------------------------------------------
 // methodes heritee de la classe actionsCommunes -----------------------------------------------------------
     
      // l'orc passe e l'attaque
     public void actionAttaquer( personnage ennemi )
     {
    	  // attaque en cours
    	 attaquer( ennemi );
    	 
    	  // si l'orc etait en mode BERSERK, il repasse en mode normal
    	 if ( berserkState != 0 )
    	 {
    		 berserkState--;
    		 setBonusAttaque( 0 );
    	 }
     }
     
      // parade de l'orc
     public void actionParer( personnage ennemi )
     {
    	 parer( ennemi );
         super.actionParer( ennemi );
     }
     
      // choisit au hasard une action e effectuer par l'orc
     public void actionRandom()
     {
    	  // en mode berserk, l'orc attaque forcement
     	 if ( isBerserker() )
     		 setActionChoisie( ACTION_ATTAQUER );
     	 else super.actionRandom();
     }
     
      // utilisation du pouvoir de l'orc
     public void actionUtiliserPouvoir( personnage ennemi )
     {
    	 super.actionUtiliserPouvoir( ennemi );
    	 utiliserPouvoir( ennemi );
     }
     
      // rien
     public void actionWait()
     {}
     
      // test de l'etat Berserk de l'orc
     public boolean isBerserker()
     {
    	 return ( berserkState != 0 );
     }
     
      // test de l'etat Berserk de l'orc
     public boolean isNotBerserker()
     {
    	 return ( !isBerserker() );
     }
}