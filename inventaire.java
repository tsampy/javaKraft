package javaKraft;

public class inventaire {

 // constantes ----------------------------------------------------------------------------------------------
 // ---------------------------------------------------------------------------------------------------------
	
    private static final int INVENTAIRE_DEFAULT_SIZE = 8;

    private static final String INVENTAIRE_DEFAULT_DESCRIPTION = "Un sac ordinaire de 8 espaces";
    private static final String INVENTAIRE_DEFAULT_NAME        = "Sac";

 // ---------------------------------------------------------------------------------------------------------
 // variables privees ---------------------------------------------------------------------------------------
    
    private equipement[] sac;

    private int nbEmplacements;

    private String name;
    private String description;

 // ---------------------------------------------------------------------------------------------------------
 // constructeurs -------------------------------------------------------------------------------------------
    
     // constructeur generique
    inventaire()
    {
        this( INVENTAIRE_DEFAULT_SIZE );
    }

     // constructeur parametre par la taille
    inventaire( int size )
    {
        this( new equipement[ size ] );
    }

     // constructeur parametre par un sac d'equipements
    inventaire( equipement[] sac )
    {
        this( sac , INVENTAIRE_DEFAULT_NAME , INVENTAIRE_DEFAULT_DESCRIPTION );
    }

     // constructeur parametre par une taille de sac, avec nom et description
    inventaire( int size , String name , String description )
    {
        this( new equipement[ size ] , name , description );
    }

     // constructeur parametre par un sac d'equipements, avec nom et description
    inventaire( equipement[] sac , String name , String description )
    {
        setSac( sac );
        setDescription( description );
        setNom( name );
    }

 // ---------------------------------------------------------------------------------------------------------
 // methodes ------------------------------------------------------------------------------------------------

     // ajout d'un item dans l'inventaire, sans place definie
    public boolean addItem( equipement item )
    {
    	boolean ajoutPossible = false;
    	
         // on ne peut ajouter un objet si le sac est deje plein
        if ( !isFull() )
        {
             // parcours du sac jusqu'e trouve un emplacement libre
            int i = 0;

            while ( ( i < getNbEmplacements() ) && ( sac[ i ] != null ) )
                i++;

            sac[ i ] = item;
            
            ajoutPossible = true;
        }

        return ( ajoutPossible );
    }

     // ajout d'un item e une position donnee, avec extraction et renvoie de l'ancien item
    public equipement addItem( equipement item , int index )
    {
    	equipement tmp = getItem( index );
        setItem( item , index );
        return tmp;
    }

     // accesseur de la description du sac
    public String getDescription()
    {
        return description;
    }

     // accesseur d'un item du sac en fonction d'un index
    public equipement getItem( int index )
    {
        return ( index < getNbEmplacements() ? sac[ index ] : null );
    }

     // nombre d'items presents dans le sac
    public int getNbItems()
    {
        int nbItems = 0;

        for ( int i = 0 ; i < getNbEmplacements() ; i++ )
            nbItems += ( sac[ i ] != null ? 1 : 0 );

        return nbItems;
    }

     // accesseur du nom du sac
    public String getName()
    {
        return name;
    }

     // accesseur du sac entier
    public equipement[] getSac()
    {
        return sac;
    }

     // taille du sac
    public int getNbEmplacements()
    {
        return nbEmplacements;
    }

     // test de remplissage du sac
    public boolean isFull()
    {
        return ( getNbItems() == getNbEmplacements() );
    }

     // test de l'existence d'un objet e un index donne
    public boolean isItemExists( int index )
    {
        return ( ( index < getNbEmplacements() ) && ( sac[ index ] != null ) );
    }

     // retire et memorise un objet dans le sac e une position donnee, en le renvoyant en resultat
    public equipement removeItem( int position )
    {
    	equipement itemRetire;

        if ( position < getNbEmplacements() )
        {
            itemRetire   = sac[ position ];
            sac[ position ] = null;
        }
        else itemRetire = null;

        return itemRetire;
    }

     // mutateur de la description du sac
    public void setDescription( String description )
    {
        this.description = description;
    }

     // mutateur d'un item dans le sac, e une position donne
     // attention, aucune verification : copie et ecrasement !
    public void setItem( equipement item , int position )
    {
        if ( position < getNbEmplacements() )
            sac[ position ] = item;
    }

     // mutateur du nom du sac
    public void setNom( String name )
    {
        this.name = name;
    }

     // mutateur du sac avec mise e jour de la taille
    public void setSac( equipement[] sac )
    {
        this.sac = sac;
        setNbEmplacements( sac.length );
    }

     // redimensionnement du sac existant
    public void setNbEmplacements( int nbEmplacements )
    {
        this.nbEmplacements = nbEmplacements;

         // copie des items du sac existant, en fonction de la nouvelle taille, si elle varie
         // attention, avec un sac plus petit, le surplus d'items sera detruit !
        if ( nbEmplacements != sac.length )
        {
        	equipement[] items = new equipement[ nbEmplacements ];

            int maxSize = ( nbEmplacements <= sac.length ? nbEmplacements : sac.length ) ;

            for ( int i = 0 ; i < maxSize ; i++ )
                items[ i ] = sac[ i ];

            sac = items;
        }
    }

     // echange de place de deux objets
    public void swapItems( int position1 , int position2 )
    {
    	equipement item = sac[ position1 ];
        sac[ position1 ] = sac[ position2 ];
        sac[ position2 ] = item;
    }

     // toString
    public String toString()
    {
        return toStringSac() + "\n" + toStringItems();
    }

     // toString des caracteristiques du sac
    public String toStringSac()
    {
        return getName() + " : " + getDescription() +
                " - Taille du sac : " + getNbEmplacements() +
                " - Nombres d'items : " + getNbItems() +
                " - Places disponibles : " + ( getNbEmplacements() - getNbItems() );
    }

     // toString des items
    public String toStringItems()
    {
        String tmp = "";

        for ( int i = 0 ; i < getNbEmplacements() ; i++ )
            tmp = tmp.concat( "Item " + i + " : " + ( isItemExists( i ) ? getItem( i ).toString() : null ) + "\n" );

        return tmp;
    }
}