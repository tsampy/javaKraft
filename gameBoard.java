package javaKraft;

import java.util.ArrayList;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class gameBoard extends constantes {
	
 // ---------------------------------------------------------------------------------------------------------
 // variables -----------------------------------------------------------------------------------------------
	
	//private ArrayList<equipement> equipementsUtilisables;
	private ArrayList<personnage> personnages;
    
	private int currentRound = 0;
    
	private int nbPersonnages;
    
 // ---------------------------------------------------------------------------------------------------------
 // constructeurs -------------------------------------------------------------------------------------------
	
	gameBoard()
	{
		setNbPersonnages( 0 );
	}
	
 // ---------------------------------------------------------------------------------------------------------
 // methodes ------------------------------------------------------------------------------------------------
    
     // sortie console
    public void affiche( Object objet )
    {
    	System.out.println( objet );
    }
    
     // delai en millisecondes
    private void delay( int milliSeconds )
    {
        try
        {
            Thread.sleep( milliSeconds );
        }
        catch(InterruptedException ex)
        {
            Thread.currentThread().interrupt();
        }
    }
    
     // affichage d'une boite de dialogue contenant un message
    public void display( String message )
    {
    	display( message , JAVAKRAFT_TITLE );
    }
    
     // affichage d'une boite de dialogue contenant un message et un titre
    public void display( String message , String title )
    {
    	JOptionPane.showMessageDialog( null , message , title , JOptionPane.PLAIN_MESSAGE );
    }
    
     // affichage d'une boete de dialogue contenant un message centre
    public void displayCentered( String message )
    {
    	displayCentered( message , JAVAKRAFT_TITLE );
    }
    
     // affichage d'une boite de dialogue contenant un message centre et un titre
    public void displayCentered( String message , String title )
    {
    	display( CENTER_TAGS_OPEN + message + CENTER_TAGS_CLOSE , title );
    }
    
     // affiche une boite de dialogue avec les statistiques des personnages
    public void displayPersoStats()
    {
    	String tmpStr = "";
    	
    	for ( int i = 0 ; i < personnages.size() ; i++ )
    		tmpStr += personnages.get( i ).toString() + "<br>";
    	
    	displayCentered( tmpStr );
    }
    
     // equiper un personnage d'objets aleatoirement
    private void equiperPersonnage( personnage perso )
    {
    	 // copie des accessoires en boutique
    	//equipementsUtilisables = new ArrayList<>();
    	//accessoiresEnBoutique.forEach( ( itemEnVente ) -> equipementsUtilisables.add( itemEnVente ) );
    	
    	int choixAleatoire;
    	
    	 // un personnage peut choisir des accessoires, mais pas deux fois le meme
    	 // chaque accessoire choisi est donc retire de la liste
    	for ( int i = 0 ; i < ( NB_MAXIMUM_EQUIPEMENTS - NB_MAXIMUM_WEAPONS ) ; i++ )
    	{
    		 // determination aleatoire d'un accessoire, et mise en place
    		choixAleatoire = getRandom( accessoiresEnBoutique.size() );
    		perso.equipeAccessoire( accessoiresEnBoutique.get( choixAleatoire ));
    		 // retrait de la liste des accessoires disponibles
    		accessoiresEnBoutique.remove( choixAleatoire );
    	}
    	
    	 // les armes
    	choixAleatoire = getRandom( armesEnBoutique.size());
        perso.equipeArme( armesEnBoutique.get( choixAleatoire ), 0 );
        armesEnBoutique.remove( choixAleatoire );
    	
         // les armures
    	choixAleatoire = getRandom( armuresEnBoutique.size());
        perso.equipeArmure( armuresEnBoutique.get( choixAleatoire ));
        armuresEnBoutique.remove( choixAleatoire );
    }
    
     // accesseur de currentRound
    public int getCurrentRound()
    {
    	return currentRound;
    }
    
     // accesseur du nombre de personnages
    public int getNbPersonnages()
    {
    	return nbPersonnages;
    }
    
    // accesseur de la liste des personnages dans le jeu
    public ArrayList<personnage> getPersonnages()
    {
    	return personnages;
    }

     // renvoie d'un chiffre au hasard en fonction d'une borne
    private int getRandom( int borne )
    {
        return (int)( Math.random() * borne );
    }
    
     // determination de l'ennemi : tirage d'un nombre aleatoirement parmi les personnages presents
	 // jusqu'e ce que le nombre soit different du numero du personnage en cours
    private int getRandomEnemy( int indexPersonnage , int borneMax )
    {
    	int randomEnemy;
   	
    	do
    		randomEnemy = getRandom( borneMax );
    	while( randomEnemy == indexPersonnage );
   	
    	return randomEnemy;
    }
    
     // accesseurs des noms des survivants
    public String getSurvivorsNames()
    {
    	String tmp = "";
    	
    	for ( int i = 0 ; i < ( getPersonnages().size() - 1 ) ; i++ )
    		tmp += getPersonnages().get( i ).getName() + ", ";
    	
    	tmp += getPersonnages().get( getPersonnages().size() - 1 ).getName();
    	
    	return ( tmp );
    }
    
     // determination du gagnant
    public personnage getWinner()
    {
    	return getPersonnages().get( 0 );
    }
    
     // accesseur du nom du gagnant
    public String getWinnerName()
    {
    	return ( getWinner() != null ? getWinner().getName() : "" );
    }
    
     // incremenation du nombre de tours joues
    public void increaseRounds()
    {
    	setCurrentRound( getCurrentRound() + 1 );
    }
    
     // initialisation du nombre de tours joues
    public void initRounds()
    {
    	setCurrentRound( 0 );
    }
    
     // accesseur du nombre de personnages restant en jeu
    public int nbPersonnagesVivants()
    {
    	return nbPersonnagesVivants( getPersonnages() );
    }
    
     // renvoie le nombre de personnages encore vivants
    private int nbPersonnagesVivants( ArrayList<personnage> personnages )
    {
    	int nb = 0;
    	
    	for ( int i = 0 ; i < personnages.size() ; i++ )
    		nb += ( personnages.get( i ).isAlive() ? 1 : 0 );
    	
    	return nb;
    }
    
    public void run( ArrayList<personnage> personnages , boolean splashScreenVisible )
    {
    	int index;
    	
        if ( splashScreenVisible )
        	splashScreen();
        
        this.setPersonnages( personnages );

    	affiche( "Les personnages engages dans la bataille : " );
        for ( index = 0 ; index < personnages.size() ; index++ )
        	affiche( " * " + personnages.get( index ).toString() );

        affiche( "-------------------------------------------------------------" );
        
         // affichage d'une messageBox avec les statistiques des personnages
        displayPersoStats();

        for ( index = 0 ; index < personnages.size() ; index++ )
        {
        	equiperPersonnage( personnages.get( index ) );
        	affiche( "-------------------------------------------------------------" );
        }

        affiche( "Les personnages avec leur equipement : " );
        for ( index = 0 ; index < personnages.size() ; index++ )
        	affiche( " * " + personnages.get( index ).toString() );
        
         // BASTON !!!!!!
        runArena( personnages );

        for ( index = 0 ; index < personnages.size() ; index++ )
        	affiche( personnages.get( index ).isAliveToString() );
        
        affiche( "Le combat s'est termine en " + currentRound + " tours" );
    }
    
     // fonction de lancement de la partie
    public void run( ArrayList<personnage> personnages )
    {
    	run( personnages , false );
    }

     // fonction de lancement de la partie
    public void run( boolean splashScreenVisible )
    {
    	personnages = new ArrayList<>();
    	
    	personnages.add( new elfe( "Stef" , FEMALE , CRAFT_THIEF ) );
    	personnages.add( new orc( "Arnaud" , MALE , CRAFT_WARRIOR ) );
    	personnages.add( new humain( "Franck" , MALE , CRAFT_MAGE ) );
    	personnages.add( new elfe( "Sebastien" , MALE , CRAFT_WARRIOR ) );
    	personnages.add( new orc( "Muriel" , FEMALE , CRAFT_MAGE ) );
    	personnages.add( new humain( "Cindy" , FEMALE , CRAFT_THIEF ) );
    	
    	run( personnages , splashScreenVisible );
    }
    
     // fonction generique de lancement de la partie
    public void run()
    {
    	run( false );
    }
    
     // gestion d'un combat de personnages, avec passage de la liste des personnages en parametre,
     // ce qui permettra d'implementer facilement des sauvegardes/chargements de parties
    public void runArena( ArrayList<personnage> personnages )
    {
    	int index;

    	initRounds();
    	
    	affiche( "" );
    	affiche( "--------------------------------------------------------------------------------------------" );
    	affiche( "--------------------------------------------------------------------------------------------" );
    	affiche( "                    L'arene est ouverte, les combats peuvent commencer !" );
    	affiche( "--------------------------------------------------------------------------------------------" );
    	affiche( "--------------------------------------------------------------------------------------------" );
    	affiche( "" );

         // la melee continue s'il y a au moins 2 personnages
         // et que la partie ne depasse pas le nombre de tours maximum
        while ( ( nbPersonnagesVivants( personnages ) > 1 ) && ( getCurrentRound() < MAXIMUM_PLAYABLE_ROUNDS ) )
        {
        	increaseRounds();
            affiche( "Tour ne" + getCurrentRound() );

             // determination des actions e effectuer, choisie aleatoirement
            for ( index = 0 ; index < personnages.size() ; index++ )
            {
            	personnages.get( index ).actionRandom();
            	affiche( "Action choisie pour " + personnages.get( index ).getName()
            					+ " : " + personnages.get( index ).actionToString() );
            }
            
             // parcours de la liste de personnages, et execution d'une action en fonction d'un ennemi random
            for ( index = 0 ; index < personnages.size() ; index++ )
            	 // au cas oe...
            	if ( personnages.get( index ).isAlive() )
            	{
            		 // determination d'un ennemi par son index
            		int randomEnemyIndex = getRandomEnemy( index , personnages.size());
            		personnage randomEnemy = personnages.get( randomEnemyIndex );
            		
            		 // action
            		personnages.get( index ).actionContreEnnemi( randomEnemy );
            		
            		 // verification de la mort de l'ennemi
            		if ( randomEnemy.isDead() )
            		{
            			 // l'ennemi est mort, appel de la methode hasKilledEnemy du personnage
            			personnages.get( index ).hasKilledEnemy( randomEnemy );
            			
            			 // le dernier souffle du guerrier ?
            			if (( randomEnemy.getCraftName() == CRAFT_WARRIOR )
            					&& ( randomEnemy.getPointsVieAvecBonus() == 0))
            				randomEnemy.setPointsVie( DERNIER_SOUFFLE );
            			 // sinon retrait du personnage decede du jeu
            			else personnages.remove( randomEnemyIndex );
            		}
            	}
            
             // affichage des personnages encore vivants
            affiche( "------------------------" );
            for ( index = 0 ; index < personnages.size() ; index++ )
            	affiche( personnages.get( index ).toString() );

            affiche( "------------------------" );
            affiche( "" );
            affiche( "-------------------------------------------------------------" );
        }
    }
    
     // mutateur de currentRound
    public void setCurrentRound( int currentRound )
    {
    	this.currentRound = currentRound;
    }
    
     // mutateur du nombre de personnages
    public void setNbPersonnages( int nbPersonnages )
    {
    	this.nbPersonnages = nbPersonnages;
    }
    
     // mutateur de la liste de personnages en jeu
    public void setPersonnages( ArrayList<personnage> personnages )
    {
    	this.personnages = personnages;
    }
    
     // y'a-t-il un gagnant ?
    public boolean someoneWins()
    {
    	return (getPersonnages().size() == 1);
    }
    
     // affichage du splash screen
    private void splashScreen()
    {
        JOptionPane.showMessageDialog( null , new JLabel(SPLASH_TEXT) , SPLASH_TITLE , JOptionPane.PLAIN_MESSAGE );
    }
}