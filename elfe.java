/*
 * > Ils attaquent avec dexterite. Bonus de +2 d'attaque.
 * > Ils peuvent parer n'importe quel coup mais perdent +1 d'attaque e chaque parade.
 * > L'Elfe utilise son pouvoir pour fuir n'importe quel combat. L'attaquant ne peux plus le poursuivre.
 */

package javaKraft;

public class elfe extends personnage implements race {

 // ---------------------------------------------------------------------------------------------------------
 // constructeurs -------------------------------------------------------------------------------------------
	
    elfe( String name , boolean sexe , String craftName )
    {
        super( name , ELFE , sexe , craftName );
        setPointsAttaque( getPointsAttaque() + 2 );
        setCriDeGuerre( sexe ? "A l'attaque !" : "A l'assaut, mes mignons !" );
    }
    
    elfe( String name , boolean sexe )
    {
        this( name , sexe , CRAFT_UNDEFINED );
    }

    elfe( String name )
    {
        this( name , DEFAULT_SEX );
    }
    
    elfe( String name , String craftName )
    {
    	this( name , DEFAULT_SEX , craftName );
    }

 // ---------------------------------------------------------------------------------------------------------
 // methodes ------------------------------------------------------------------------------------------------

     // gestion specifique de l'action de l'elfe contre l'ennemi
    public String actionContreEnnemi( int actionChoisie , personnage ennemi )
    {
        if ( !isAuSol() )
            actionChoisie = ACTION_NULL;

        return super.actionContreEnnemi( actionChoisie , ennemi );
    }
    
     // gestion specifique de l'action de l'elfe contre l'ennemi
    public String actionContreEnnemi( personnage ennemi )
    {
    	return super.actionContreEnnemi( getActionChoisie() , ennemi );
    }

     // l'elfe passe e l'attaque
    public void actionAttaquer( personnage ennemi )
    {
        attaquer( ennemi );
    }
    
     // SURCHARGE - choisit au hasard une action e effectuer par l'elfe
    public void actionRandom()
    {
    	if ( isAuSol() )
    		super.actionRandom();
        else
        	setActionChoisie( ACTION_NULL );
    }

     // parade de l'elfe
    public void actionParer( personnage ennemi )
    {
    	parer( ennemi );
        super.actionParer( ennemi );
    }

     // l'elfe utilise son pouvoir pour fuir le combat
    public void actionUtiliserPouvoir( personnage ennemi )
    {
        super.actionUtiliserPouvoir( ennemi );
        utiliserPouvoir( ennemi );
    }

     // action surchargee
    public void actionWait()
    {
        // si l'elfe est cache dans un arbre
        if ( !isAuSol() )
        {
            // la duree de la protection diminue e chaque tour
        	setRemainingHiddenRounds( getRemainingHiddenRounds() - 1 );
            // l'elfe revient dans le combat s'il n'est plus cache
            if ( getRemainingHiddenRounds() == 0 )
            	revenirAuCombat();
        }
    }

     // l'elfe attaque
    public void attaquer( personnage ennemi )
    {
    	super.actionAttaquer( ennemi );
    	attaquerEnnemi( ennemi );
    }

     // l'elfe prend la fuite en montant dans un arbre, et est protege pour 5 tours
    public void fuir( personnage ennemi )
    {
    	sortieConsole( getName() + " se cache dans un arbre" );
        setHauteur( IN_TREE );
        setRemainingHiddenRounds( 5 );
    }

     // l'elfe pare n'importe quel coup, mais perd un point d'attaque
    public void parer( personnage ennemi ) 
    {
         // retrait du point d'attaque, si les points d'attaque sont superieurs e 0
        setPointsAttaque( 0 < getPointsAttaque() ? getPointsAttaque() - 1 : getPointsAttaque() );
    }

     // l'elfe redescent de l'arbre, et revient dans le combat
    public void revenirAuCombat()
    {
    	sortieConsole( getName() + " descend de son arbre" );
        setHauteur( ON_GROUND );
    }

     // utilisation du pouvoir specifique e l'elfe
    public void utiliserPouvoir( personnage ennemi )
    {
    	sortieConsole( getName() + " invoque son pouvoir : Par Norelenilia, reine des elfes de Lunelbar !" );
    	fuir( ennemi );
    }
}