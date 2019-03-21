package javaKraft;

import java.lang.Math;

public class personnage extends metier {

 // ---------------------------------------------------------------------------------------------------------
 // variables privees ---------------------------------------------------------------------------------------
	
    private boolean sexe;

    private String criDeGuerre;
    private String name;

    private int pointsAttaque;
    private int pointsDefense;
    private int pointsVie;

    private int remainingHiddenRounds;

    private int typePerso;

    private equipement bodyArmor;
    private equipement[] objectsInHand = new equipement[ NB_MAXIMUM_WEAPONS ];
    private equipement[] accessories   = new equipement[ NB_MAXIMUM_EQUIPEMENTS ];
    private inventaire inventory = new inventaire();

    private int ordonnee;
    
     // les differents bonus propres aux personnages
    private int bonusAttaque = 0;
    private int bonusDefense = 0;
    private int bonusPV      = 0;

 // ---------------------------------------------------------------------------------------------------------
 // constructeurs -------------------------------------------------------------------------------------------
    
     // constructeur avec nom, type de perso et sexe
    personnage( String nom , int typePerso , boolean sexe , String craftName )
    {
        setName( nom );
        setSexe( sexe );
        setTypePerso( typePerso );

         // definition du cri de guerre generique en fonction du sexe
        setCriDeGuerre( sexe ? "A l'attaqqquuue !" : "A l'assaut, mes mignons !" );

        setPointsVie( 100 );
        setPointsAttaque( 10 );
        setPointsDefense( 5 );

        setHauteur( ON_GROUND );
        
        initCraft( craftName );
    }
    
    // constructeur avec nom, type de perso et sexe
    personnage( String nom , int typePerso , boolean sexe )
    {
    	this( nom , typePerso , sexe , CRAFT_UNDEFINED );
    }

     // constructeur sans le sexe du personnage
    personnage( String nom , int typePerso )
    {
        this( nom , typePerso , DEFAULT_SEX );
    }
    
     // constructeur sans le sexe du personnage, avec son metier
    personnage( String nom , int typePerso , String craftName )
    {
        this( nom , typePerso , DEFAULT_SEX , craftName );
    }

     // constructeur sans le sexe du personnage
    personnage( String nom )
    {
        this( nom , HUMAIN );
    }
    
     // constructeur sans le sexe du personnage et avec son metier
    personnage( String nom , String craftName )
    {
        this( nom , HUMAIN , craftName );
    }

     // constructeur avec le sexe du personnage
    personnage( String nom , boolean sexe )
    {
        this( nom , HUMAIN , sexe );
    }
    
     // constructeur avec le sexe du personnage et son metier
    personnage( String nom , boolean sexe , String craftName )
    {
        this( nom , HUMAIN , sexe , craftName );
    }
    
 // ---------------------------------------------------------------------------------------------------------
 // methodes heritees de la classe abstraite metier ---------------------------------------------------------    
    
    public void actionMetier( personnage ennemi )
    {
    	sortieConsole( actionCraftToString( getName() ) );
    	switch( getCraftName() )
    	{
    	case CRAFT_MAGE: // le mage peut recuperer +5 en PV
    		sortieConsole( getName() + " recupere " + CRAFT_BONUS_MAGE + " points de vie" );
    		increasePointsVie( CRAFT_BONUS_MAGE );
    		break;

    	case CRAFT_THIEF: // Le Voleur peut egalement jeter un de.
       	 				  //  => Si le resultat est superieur ou egal e 4, il vole un objet e un autre personnage.
       	 				  //  => Si le resultat est inferieur e 4, il perd 10 point de vie et ne vole rien.
    		int lancerUnDe = ( (int) ( Math.random() * 6 ) );
    		sortieConsole( getName() + " lance un de pour tenter de derober un objet e " + ennemi.getName() );
    		sortieConsole( getName() + " fait un " + lancerUnDe );
    		if ( lancerUnDe >= 4 )
    		{
    			sortieConsole( "Le lancer de de est reussi, en avant, Arsene Lupin !" );
    			deroberEquipement( ennemi );
    		}
    		else
    		{
    			sortieConsole( "La tentative de vol a echoue, " + getName() + " perd 10 points de vie, quel pignouf !" );
    			decreasePointsVie( CRAFT_MALUS_THIEF );
    		}
    		break;

    	case CRAFT_UNDEFINED: // pas de metier defini
    		break;

    	case CRAFT_WARRIOR: // pas d'action metier specifique au guerrier pour l'instant
    		break;

    	default : // par defaut, rien
    		break;
    	}
    }

 // ---------------------------------------------------------------------------------------------------------
 // methodes ------------------------------------------------------------------------------------------------

     // creation d'un String avec tous les accessories portes
    public String accessoriesToString()
    {
        String tmpString = "accessoires de " + getName() + " : ";

        for ( int i = 0 ; i < getNumberEquipements() ; i++ )
            tmpString += accessories[i].toString() + "\n";

        return tmpString;
    }
    
     // attaque d'un ennemi
    public void actionAttaquer( personnage ennemi )
    {
    	sortieConsole( getName() + " attaque " + ennemi.getName() );
    }
    
     // parade contre un ennemi
    public void actionParer( personnage ennemi )
    {
    	sortieConsole( getName() + " lance une parade contre " + ennemi.getName()
    		+ " (" + ennemi.getTypePersoString() + ")"
    		+ " ==> la parade a " + ( succesfulParade ? "reussi" : "echoue" ) );
    	
    	 // apres la parade, l'action est redefinie comme nulle pour ne pas pouvoir parer plusieurs attaques
    	setActionChoisie( ACTION_NULL );
    }
    
     // choisit au hasard une action e effectuer par le personnage
    public void actionRandom()
    {
    	switch( (int)( Math.random() * 5 ) )
    	{
    	case 0 :
    		setActionChoisie( ACTION_PARER );
    		break;
    	case 1:
    		 // utilisation du pouvoir s'il n'a jamais ete utilise, sinon, attaque
    		setActionChoisie( nbPowerUsed == 0 ? ACTION_POUVOIR : ACTION_ATTAQUER );
    		break;
    	case 2:
    		setActionChoisie( ACTION_METIER );
    		break;
    	default:
    		setActionChoisie( ACTION_ATTAQUER );
    		break;
    	}
    }

     // ajout d'un item dans l'inventaire
    public boolean addItemToInventaire( equipement item )
    {
        return inventory.addItem( item );
    }

     // ajout d'un item dans l'inventaire e une place donnee, en renvoyant l'item deje present
    public equipement addItemToInventaire( equipement item , int index )
    {
        return inventory.addItem( item , index );
    }

     // affiche l'action en cours
    public void afficheAction()
    {
    	sortieConsole( getName() + " " + actionToString() + " "
                + " PV : " + getPointsVieAvecBonus()
                + " Att : " + getPointsAttaqueAvecBonus()
                + " Def : " + getPointsDefenseAvecBonus());
    }

     // attaque d'un ennemi designe
    public void attaquerEnnemi( personnage ennemi )
    {
    	int warriorTotalAttackBonus = 0;
    	
         // test des objets tenus en main, pour le guerrier
        if ( getCraftName() == CRAFT_WARRIOR )
        {
        	 // parcours des deux mains
        	for ( int i = 0 ; i < 2 ; i++)
        		 // si le guerrier tient une arme en main, il obtient un bonus attaque +1
        		if (( objectsInHand[ i ] != null ) && ( ( objectsInHand[ i ].getType() == TYPE_WEAPON ) ))
        			warriorTotalAttackBonus++;
        		
        	 // pour toutes les armes dans son inventaire, il obient un bonus attaque +1
    		for ( int i = 0 ; i < inventory.getNbItems() ; i++ )
    			if ( inventory.getItem( i ).getType() == TYPE_WEAPON )
    				warriorTotalAttackBonus++;
        }
        
         // augmentation du bonus d'attaque
        if ( warriorTotalAttackBonus != 0 )
        {
        	increaseCraftBonusAttaque( warriorTotalAttackBonus );
        	sortieConsole( getName() + " porte " + warriorTotalAttackBonus + " armes." ); 
        	sortieConsole( "Il reeoit un bonus d'attaque de <+" + warriorTotalAttackBonus + ">" );
        }
        
        crier();
        if ( isAlive() )
        	if ( ennemi.isAttackable() )
        		if ( objectsInHand[ RIGHT_HAND ] != null )
        			infligeDegats( ennemi , objectsInHand[ RIGHT_HAND ].getName() );
        		else
        			if ( objectsInHand[ LEFT_HAND ] != null )
            			infligeDegats( ennemi , objectsInHand[ LEFT_HAND ].getName() );
        			else infligeDegats( ennemi , "ses mains nues");
        	else
        		sortieConsole( ennemi.getName() + " est cache dans un arbre, " + getName() + " ne peut l'atteindre");
                
         // diminution du bonus d'attaque
        decreaseCraftBonusAttaque( warriorTotalAttackBonus );
    }

     // execute l'action de lancer le cri de guerre
    public void crier()
    {
    	sortieConsole( getName() + " : " + getCriDeGuerre() );
    }
    
     // subir un coup porte
    public void damagesSuffering( personnage ennemi , int degats )
    {
    	degats = ( degats - getPointsDefenseAvecBonus() < 0 ? 0 : degats - getPointsDefenseAvecBonus() );
    		
    	sortieConsole( getName() + " subit " + degats + " points de degats" );
    	decreasePointsVie( degats );
    		
    	if ( getPointsVieAvecBonus() <= 0 )
    		sortieConsole( getName() + " est mort, Jim !" );
    }
    
     // affiche les donnees du personnage : PV, Att et Def
    public String dataToString()
    {
        return " PV " + getPointsVieAvecBonus() + " - "
                + "Att " + getPointsAttaqueAvecBonus() + " - "
                + "Def " + getPointsDefenseAvecBonus();
    }
    
     // debugage
    public void debug()
    {
        sortieConsole( "DEBUG getPointsVie() = " + getPointsVie() );
        sortieConsole( "DEBUG bodyArmor.getBonusPV() = " 
        		+ ( ( bodyArmor != null ) ? bodyArmor.getBonusPV() : 0 ));
        sortieConsole( "DEBUG objectsInHand[RIGHT_HAND].getBonusPV() = " 
        		+ ( ( objectsInHand[RIGHT_HAND] != null ) ? objectsInHand[RIGHT_HAND].getBonusPV() : 0 ));
        sortieConsole( "DEBUG bonusPV = " + bonusPV );
        sortieConsole( "DEBUG craftBonusPV = " + craftBonusPV() );
    }
    
 // augmentation des points d'attaque
    public void decreasePointsAttaque( int pointsAttaque )
    {
    	increasePointsAttaque( ( - 1 ) * pointsAttaque );
    }
    
    // augmentation des points de defense
    public void decreasePointsDefense( int pointsDefense )
    {
    	increasePointsDefense( ( - 1 ) * pointsDefense );
    }
    
     // augmentation des points de vie
    public void decreasePointsVie( int pointsVie )
    {
    	increasePointsVie( ( - 1 ) * pointsVie );
    }
    
     // voler un equipement e l'ennemi
    public void deroberEquipement( personnage ennemi )
    {    	
    	/* 0 armure
    	 * 1 arme gauche
    	 * 2 arme droite
    	 * 3, 4, 5, 6 accessoires
    	 * 7, 8, 9, 10, 11, 12, 13, 14 inventaire
    	 */
    	
    	int objetConvoite;
    	equipement objetDerobe;
    	
    	do
    	{
    		objetConvoite = ( (int) ( Math.random() * 15 ) );
    		
    		switch( objetConvoite )
    		{
    		case 0: objetDerobe = ennemi.removeItemFromBodyArmor();
    			break;
    		case 1:
    		case 2: objetDerobe = ennemi.removeItemFromObjectsInHands( objetConvoite - 1 ); // -1 puisque le case commence e 1
    			break;
    		case 3:
    		case 4:
    		case 5:
    		case 6: objetDerobe = ennemi.removeItemFromAccessories( objetConvoite - 3 ); // -3 puisque le case commence e 3
    			break;
    		case 7:
    		case 8:
    		case 9:
    		case 10:
    		case 11:
    		case 12:
    		case 13:
    		case 14: objetDerobe = ennemi.removeItemFromInventaire( objetConvoite - 7 ); // -7 puisque le case commence e 7
    			break;
    		default: objetDerobe = null;
    			break;
    		}
    	}
    	while( objetDerobe == null );
    	
    	sortieConsole( getName() +  " derobe e " + ennemi.getName() + " : " + objetDerobe.toString() );
    	
    	if ( addItemToInventaire( objetDerobe ) )
    		sortieConsole( getName() + " a ajoute " + objetDerobe.getName() + " dans son sac" );
    	else sortieConsole( "Le sac de " + getName() + " est plein, l'objet derobe est detruit !" );
    	
    	sortieConsole( "\n" + equipementsToString() );
    	sortieConsole( "\n" + ennemi.equipementsToString() );
    }

     // equipe le personnage d'un equipement en le plaeant en derniere position
    public equipement equipeAccessoire( equipement item )
    {
        return equipeAccessoire( item , getNumberEquipements() );
    }

     // equipe le personnage d'un equipement en le plaeant e une position donnee
    public equipement equipeAccessoire( equipement item , int position )
    {
    	equipement accessoireTmp = null;

        // si un accessoire existe deje e la position, on l'echange
        // si aucun accessoire n'existe, on peut l'ajouter si on a moins de NB_MAXIMUM_EQUIPEMENTS
        if ( position < NB_MAXIMUM_EQUIPEMENTS )
            if (( accessories[ position ] != null ) || ( nbWornEquipements() < NB_MAXIMUM_EQUIPEMENTS ))
            {
            	accessoireTmp = accessories[ position ];
                accessories[ position ] = item;
                sortieConsole( getName() + " s'equipe avec " + item.toMinimalString());
            }
            else sortieConsole( "Trop d'equipements <"+nbWornEquipements()+">, impossible d'ajouter " + item.getName() );
        else sortieConsole( "Erreur : la position <" + position + "> de l'item "
                + item.getName() + " est superieur e la limite : " + NB_MAXIMUM_EQUIPEMENTS );

        return accessoireTmp;
    }

     // prendre une arme en main, et renvoyer l'arme en cours
    public equipement equipeArme( equipement arme , int position )
    {
    	equipement armeTmp = null;

        // si une arme existe deje e la position, on l'echange
        // si aucun accessoire n'existe, on peut l'ajouter si on a moins de NB_MAXIMUM_EQUIPEMENTS
        if ( position < NB_MAXIMUM_WEAPONS )
            if (( objectsInHand[ position ] != null ) || ( nbWornEquipements() < NB_MAXIMUM_EQUIPEMENTS ))
            {
            	armeTmp = objectsInHand[ position ];
                objectsInHand[ position ] = arme;
                sortieConsole( getName() + " s'equipe avec l'arme " + arme.toMinimalString());
            }
            else sortieConsole( "Trop d'equipements <"+nbWornEquipements()+">, impossible d'ajouter " + arme.getName() );
        else sortieConsole( "Erreur : la position <" + position
                + "> de l'item est superieur e la limite : " + NB_MAXIMUM_EQUIPEMENTS );

        return armeTmp;
    }

     // equiper un personnage d'une armure, et renvoyer celle deje mise
    public equipement equipeArmure( equipement armure )
    {
    	equipement armureTmp = null;

        // si le personnage a deje une armure, on peut l'echanger
        // si le personnage n'a pas d'armure, on verifie qu'il n'ait pas deje NB_MAXIMUM_EQUIPEMENTS sur lui
        if (( bodyArmor != null ) || (( bodyArmor == null ) && ( nbWornEquipements() < NB_MAXIMUM_EQUIPEMENTS )))
        {
        	armureTmp = bodyArmor;
            bodyArmor = armure;
            sortieConsole( getName() + " s'equipe avec l'armure " + armure.toMinimalString());
        }
        else sortieConsole( "Impossible de mettre l'armure " + armure.toString() + ", " + getName()
                + " est deje equipe de "
                + nbWornEquipements() + " equipements");

        return armureTmp;
    }
    
     // accesseur du toString d'un equipement, avec test d'existence
    public String equipementToString( equipement item )
    {
    	return ( ( item != null ) ? item.toString() : "null" );
    }
    
     // accesseur du toString d'un equipement, avec test d'existence
    public String equipementToString( String categorie , equipement item )
    {
    	return ( ( item != null ) ? categorie + " : " + item.toString() : "null" );
    }
    
     // accesseur du toString d'un equipement, avec test d'existence, formate pour l'affichage
    public String equipementToStringFormatted( String categorie , equipement item )
    {
    	return ( ( item != null ) ? categorie + " : " + item.toString() + "\n" : "" );
    }
    
     // un toString de tous les equipements du personnage
    public String equipementsToString()
    {
    	String tmp = "Equipement de " + getName() + "\n"
    			+ "-------------------------------------------\n"
    			+ equipementToStringFormatted( "armure" , bodyArmor )
    			+ equipementToStringFormatted( "main gauche" , objectsInHand[ LEFT_HAND ] )
    	    	+ equipementToStringFormatted( "main droite" ,  objectsInHand[ RIGHT_HAND ] )
    	    	+ equipementToStringFormatted( "accessoire 1" ,  accessories[ 0 ] )
    	    	+ equipementToStringFormatted( "accessoire 2" ,  accessories[ 1 ] )
    	    	+ equipementToStringFormatted( "accessoire 3" ,  accessories[ 2 ] )
    	    	+ equipementToStringFormatted( "accessoire 4" ,  accessories[ 3 ] )
    	    	+ "nombre d'objets dans l'inventaire : " + inventory.getNbItems() + "\n";

    	for ( int i = 0 ; i < inventory.getNbItems() ; i++ )
    		tmp += "  => " + equipementToString( inventory.getItem( i ) ) + "\n";
    	
    	return ( tmp );
    }

     // accesseur de l'action choisie
    public int getActionChoisie()
    {
        return actionChoisie;
    }
    
     // accesseur du nom de l'action choisie
    public String getActionName()
    {
    	return ACTIONS_TO_STRING[ getActionChoisie() ];
    }
    
     // accesseur du bonus d'attaque propre au personnage
    public int getBonusAttaque()
    {
    	return bonusAttaque;
    }
    
     // accesseur du bonus de defense propre au personnage
    public int getBonusDefense()
    {
    	return bonusDefense;
    }
    
     // accesseur du bonus de points de vie propre au personnage
    public int getBonusPV()
    {
    	return bonusPV;
    }

     // accesseur du cri de guerre
    public String getCriDeGuerre()
    {
        return ( isAlive() ? criDeGuerre : "Arrrrgh !" );
    }
    
     // accesseur de l'ordonnee du personnage
    public int getHauteur()
    {
        return ordonnee;
    }
    
     // accesseur d'un item de l'inventaire e la position index
    public equipement getItemFromAccessories( int index )
    {
        return accessories[ index ];
    }
    
     // accesseur d'un item de l'inventaire e la position index
    public equipement getItemFromBodyArmor()
    {
        return bodyArmor;
    }
    
     // accesseur d'un item de l'inventaire e la position index
    public equipement getItemFromInventaire( int index )
    {
        return inventory.getItem( index );
    }

     // accesseur d'un item de l'inventaire e la position index
    public equipement getItemFromObjectsInHands( int index )
    {
        return objectsInHand[ index ];
    }
    
     // accesseur du nom
    public String getName()
    {
        return name;
    }

     // accesseur du nombre d'equipements dont le personnage est equipe
    public int getNumberEquipements()
    {
        int nb = 0;
        while(( accessories[ nb ] != null ) && ( nb < NB_MAXIMUM_EQUIPEMENTS ))
            nb++;
        return nb;
    }

     // accesseur des points d'attaque
    public int getPointsAttaque()
    {
        return pointsAttaque;
    }

     // calcul des points d'attaque avec les bonus
    public int getPointsAttaqueAvecBonus()
    {
        int bonusaccessories = 0;

        for (equipement accessoire : accessories)
            bonusaccessories += (accessoire != null ? accessoire.getBonusAttaque() : 0);

        // calcul des differents bonus
        return getPointsAttaque() + ( ( bodyArmor != null ) ? bodyArmor.getBonusAttaque() : 0 )
                + ( ( objectsInHand[LEFT_HAND] != null ) ? objectsInHand[LEFT_HAND].getBonusAttaque() : 0 )
                + ( ( objectsInHand[RIGHT_HAND] != null ) ? objectsInHand[RIGHT_HAND].getBonusAttaque() : 0 )
                + bonusaccessories
                + bonusAttaque
                + craftBonusAttaque();
    }

     // accesseur des points de defense
    public int getPointsDefense()
    {
        return pointsDefense;
    }

     // calcul des points de defense avec les bonus
    public int getPointsDefenseAvecBonus()
    {
        int bonusaccessories = 0;

        for ( int i = 0 ; i < accessories.length ; i++ )
            bonusaccessories += ( accessories[i] != null ? accessories[i].getBonusDefense() : 0 );
        // calcul des differents bonus
        return getPointsDefense() + ( ( bodyArmor != null ) ? bodyArmor.getBonusDefense() : 0 )
                + ( ( objectsInHand[LEFT_HAND] != null ) ? objectsInHand[LEFT_HAND].getBonusDefense() : 0 )
                + ( ( objectsInHand[RIGHT_HAND] != null ) ? objectsInHand[RIGHT_HAND].getBonusDefense() : 0 )
                + bonusaccessories
                + bonusDefense
                + craftBonusDefense();
    }

     // accesseur des points de vie
    public int getPointsVie()
    {
        return pointsVie;
    }

     // calcul des points de vie avec bonus
    public int getPointsVieAvecBonus()
    {
        int bonusaccessories = 0;

        for ( int i = 0 ; i < accessories.length ; i++ )
            bonusaccessories += ( accessories[i] != null ? accessories[i].getBonusPV() : 0 );

        // calcul des differents bonus
        return getPointsVie() + ( ( bodyArmor != null ) ? bodyArmor.getBonusPV() : 0 )
                + ( ( objectsInHand[LEFT_HAND] != null ) ? objectsInHand[LEFT_HAND].getBonusPV() : 0 )
                + ( ( objectsInHand[RIGHT_HAND] != null ) ? objectsInHand[RIGHT_HAND].getBonusPV() : 0 )
                + bonusaccessories
                + bonusPV
                + craftBonusPV();
    }
    
     // accesseur du nombre de tours oe l'elfe est cache
    public int getRemainingHiddenRounds()
    {
        return remainingHiddenRounds;
    }

     // accesseur du sexe
    public boolean getSexe()
    {
        return sexe;
    }

     // accesseur du type de personnage
    public int getTypePerso()
    {
        return typePerso;
    }

     // accesseur du String du type de personnage
    public String getTypePersoString()
    {
        return PERSONNAGE_TYPES_TO_STRING[ getTypePerso() ];
    }
    
     // le personnage n'a-t-il aucun bouclier en main ?
    public boolean hasNoShieldInHand()
    {
    	return ( !hasShieldInHand() );
    }
    
     // le personnage n'a-t-il aucune arme en main ?
    public boolean hasNoWeaponInHand()
    {
    	return (( objectsInHand[RIGHT_HAND] == null ) && ( objectsInHand[LEFT_HAND] == null ));
    }
    
    // le personnage tient-il au moins un bouclier en main ?
    public boolean hasShieldInHand()
    {
    	return (( objectsInHand[RIGHT_HAND] != null ) && ( objectsInHand[RIGHT_HAND].getType() == TYPE_SHIELD ))
    			|| (( objectsInHand[LEFT_HAND] != null ) && ( objectsInHand[LEFT_HAND].getType() == TYPE_SHIELD ));
    }
    
     // le personnage tient-il au moins un arme en main ?
    public boolean hasWeaponInHand()
    {
    	return ( !hasWeaponInHand() );
    }
    
     // un personnage vient de tuer son ennemi
    public void hasKilledEnemy( personnage ennemi )
    {
    	sortieConsole( getName() + " vient de tuer " + ennemi.getName() );
    	increaseCraftXP_Fight_Won( getName() , ennemi );
    }
    
     // porter un coup e l'ennemi
    public void infligeDegats( personnage ennemi , String nomArme )
    {
    	 // les degats sont calcules avec les bonus
    	int degats = getPointsAttaqueAvecBonus();
    	
    	 // si le personnage attaque e main nue, il perd ses points d'attaque, ne conserve que les bonus
    	 // ea va devenir tres chaud pour lui !
    	if ( nomArme.indexOf( "main" ) >= 0 )
    		degats -= getPointsAttaque();

        sortieConsole( getName() + " attaque " + ennemi.getName()
                + " avec " + nomArme
                + " : degats infligeables = " + degats );

        ennemi.subitDegats( this , degats );
    }
    
     // augmentation des points d'attaque
    public void increasePointsAttaque( int pointsAttaque )
    {
    	setPointsAttaque( getPointsAttaque() + pointsAttaque );
    }
    
     // augmentation des points de defense
    public void increasePointsDefense( int pointsDefense )
    {
    	setPointsDefense( getPointsDefense() + pointsDefense );
    }
    
     // augmentation des points de vie
    public void increasePointsVie( int pointsVie )
    {
    	setPointsVie( getPointsVie() + pointsVie );
    }

     // accesseur de l'inventaire
    public inventaire inventaire()
    {
        return inventory;
    }

     // le personnage est-il vivant ?
    public boolean isAlive()
    {
        return ( getPointsVieAvecBonus() > 0 );
    }

     // renvoie d'un String indiquant si le personnage est vivant ou mort
    public String isAliveToString()
    {
        return getName() + ( isAlive() ? " est toujours en vie" : " est mort" + ( isFemale() ? "e" : "" ) );
    }

     // le personnage est-il mort ?
    public boolean isDead()
    {
        return ( !isAlive() );
    }

     // le perso est-il feminin ?
    public boolean isFemale()
    {
        return ( getSexe() == FEMALE );
    }

     // le perso est-il masculin ?
    public boolean isMale()
    {
        return ( !isFemale() );
    }

     // le joueur est-il attaquable au combat ?
    public boolean isAttackable()
    {
        return ( getHauteur() == ON_GROUND );
    }

     // test pour voir si l'elfe est toujours dans un arbre
    public boolean isAuSol()
    {
        return ( remainingHiddenRounds == 0 );
    }

     // renvoie le nombre d'accessories portes par le personnage
    public int nbWornAccessories()
    {
        int nombre = 0;

        for ( int i = 0 ; i < accessories.length ; i++ )
            nombre += ( accessories[ i ] != null ? 1 : 0 );

        return nombre;
    }

     // renvoie le nombre d'equipements dont est equipe le personnage
    public int nbWornEquipements()
    {
        int nombre = nbWornAccessories() + ( bodyArmor != null ? 1 : 0 );

        for ( int i = 0 ; i < objectsInHand.length ; i++ )
            nombre += ( objectsInHand[ i ] != null ? 1 : 0 );

        return ( nombre );
    }
    
     // retirer un equipement parmi les accessoires, en le gardant en memoire
    public equipement removeItemFromAccessories( int index )
    {
    	equipement tmp = accessories[ index ];
    	accessories[ index ] = null;
        return tmp;
    }
    
    // retirer l'armure, en le gardant en memoire
    public equipement removeItemFromBodyArmor()
    {
    	equipement tmp = bodyArmor;
    	bodyArmor = null;
        return tmp;
    }
    
    // retirer un equipement de l'inventaire, en le gardant en memoire
    public equipement removeItemFromInventaire( int index )
    {
        return inventory.removeItem( index );
    }
    
    // retirer un equipement des mains du personnage, en le gardant en memoire
    public equipement removeItemFromObjectsInHands( int index )
    {
    	equipement tmp = objectsInHand[ index ];
    	objectsInHand[ index ] = null;
        return tmp;
    }

     // mutateur de l'action e effectuer
    public void setActionChoisie( int actionChoisie )
    {
        this.actionChoisie = actionChoisie;
    }
    
     // mutateur du bonus d'attaque du personnage
    public void setBonusAttaque( int bonusAttaque )
    {
    	this.bonusAttaque = bonusAttaque ;
    }
    
     // mutateur du bonus de defense du personnage
    public void setBonusDefense( int bonusDefense )
    {
    	this.bonusDefense = bonusDefense ;
    }
    
     // mutateur du bonus de points de vie du personnage
    public void setBonusPV( int bonusPV )
    {
    	this.bonusPV = bonusPV ;
    }

     // mutateur du cri de guerre
    public void setCriDeGuerre( String criDeGuerre )
    {
        this.criDeGuerre = criDeGuerre;
    }
    
     // mutateur de la hauteur
    public void setHauteur( int ordonnee )
    {
        this.ordonnee = ordonnee;
    }

     // mutateur du nom
    public void setName( String name )
    {
        this.name = name;
    }

     // mutateur des points d'attaque
    public void setPointsAttaque( int pointsAttaque )
    {
        this.pointsAttaque = pointsAttaque;
    }

     // mutateur des points de defense
    public void setPointsDefense( int pointsDefense )
    {
        this.pointsDefense = pointsDefense;
    }

     // mutateur des points de vie
    public void setPointsVie( int pointsVie )
    {
        this.pointsVie = pointsVie;
    }
    
     // determine combien de tours l'elfe doit encore rester cache
    public void setRemainingHiddenRounds( int remainingHiddenRounds )
    {
    	this.remainingHiddenRounds = ( 0 < remainingHiddenRounds ? remainingHiddenRounds : 0 );
    }

     // mutateur du sexe
    public void setSexe( boolean sexe )
    {
        this.sexe = sexe;
    }

     // mutateur du type de personnage
    public void setTypePerso( int typePerso )
    {
        this.typePerso = typePerso;
    }
    
     // fonction testant si le personnage va subir des degats
    public void subitDegats( personnage ennemi , int degats )
    {
    	 // si le personnage tente une parade
    	if ( getActionChoisie() == ACTION_PARER )
    	{
    		 // tentative de parade
    		actionParer( ennemi );
    		 // en cas d'echec de la parade, le personnage subit des degats
    		if ( !succesfulParade )
    			damagesSuffering( ennemi , degats );
    	}
    	else damagesSuffering( ennemi , degats );
    }

     // String toString()
    public String toString()
    {
        return getName() + " : y=" + getHauteur() + ", "
                + getTypePersoString() + " "
                + ( getSexe() ? "mele" : "femelle" ) + " "
                + specsToMinimalString( getPointsVieAvecBonus() ,
                						getPointsAttaqueAvecBonus() ,
                						getPointsDefenseAvecBonus() ) + ", "
                + getCraftName() + " " + craftSpecsToMinimalString() + ", "
                + "{ PV " + getPointsVieAvecBonus()
                + " - Att " + getPointsAttaqueAvecBonus()
                + " - Def " + getPointsDefenseAvecBonus()
                + " } , cri de guerre : " + getCriDeGuerre();
    }
}