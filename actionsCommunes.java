package javaKraft;

public abstract class actionsCommunes extends constantes {

 // ---------------------------------------------------------------------------------------------------------
 // variables -----------------------------------------------------------------------------------------------
	
	protected boolean succesfulParade = true;

    protected int actionChoisie;
    protected int nbPowerUsed = 0;

 // ---------------------------------------------------------------------------------------------------------
 // methodes ------------------------------------------------------------------------------------------------

     // affichage console
    public void sortieConsole( Object objet )
    {
    	System.out.println( objet );
    }
    
    // action contre un ennemi
    public String actionContreEnnemi( personnage ennemi )
    {
    	return actionContreEnnemi( actionChoisie , ennemi );
    }

    // action e realiser face e un ennemi, en fonction de l'action choisie
    public String actionContreEnnemi( int actionChoisie , personnage ennemi )
    {
        String tmpStr = "";
        
        switch( actionChoisie ) {
            case ACTION_ATTAQUER:
                actionAttaquer( ennemi );
                break;
            case ACTION_PARER:
                // la parade est geree directement par le personnage attaque
                break;
            case ACTION_POUVOIR:
                if ( nbPowerUsed == 0 )
                    actionUtiliserPouvoir( ennemi );
                else tmpStr = " ** pouvoir epuise **";
                break;
            case ACTION_METIER:
            	actionMetier( ennemi );
            	break;
            default:
                actionWait();
                break;
        }

        return ( actionToString() + tmpStr + " " + ennemi.getName() + dataToString() );
    }

     // String actionToString()
    public String actionToString()
    {
        return ACTIONS_TO_STRING[ actionChoisie ];
    }

     // String dataToString()
    public String dataToString()
    {
        return "";
    }
    
    // String toMinimalString()
    public String specsToMinimalString( int specPV , int specAtt , int specDef )
    {
    	String tmpStr = "{";
    	
    	 // si un bonus de points de vie existe, on l'ajoute
    	if ( specPV != 0 )
    		tmpStr += " PV " + ( specPV > 0 ? "+" : "" ) + specPV;
    	
    	 // si un bonus de points d'attaque existe, on l'ajoute
    	if ( specAtt != 0 )
    		tmpStr += ( specPV != 0 ? " /" : "" ) 
    					+ " Att " + ( specAtt > 0 ? "+" : "" ) + specAtt;
    	
    	 // si un bonus de points d'attaque existe, on l'ajoute
    	if ( specDef != 0 )
    		tmpStr += ( (( specPV != 0 ) || ( specAtt != 0 )) ? " /" : "" )
    				+ " Def " + ( specDef > 0 ? "+" : "" ) + specDef;
    	
    	tmpStr += " }";
    	
    	return tmpStr;
    }

 // ---------------------------------------------------------------------------------------------------------
 // actions generiques, e surcharger selon les personnages --------------------------------------------------
    
    public void actionAttaquer( personnage ennemi ) {}
    public void actionMetier( personnage ennemi ) {}
    public void actionParer( personnage ennemi ) {}
    public void actionWait() {}

    public void actionUtiliserPouvoir( personnage ennemi )
    {
        nbPowerUsed++;
    }
}