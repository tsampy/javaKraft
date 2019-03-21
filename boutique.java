package javaKraft;

import java.util.ArrayList;
import java.util.Arrays;

 // boutique avec differents equipements( nom, description, type, attaque, defense, PV )
public class boutique {

 // ---------------------------------------------------------------------------------------------------------
 // les accessoires -----------------------------------------------------------------------------------------
	
	static equipement amulette     = new equipement( "Amulette" , "Une amulette protectrice" , "accessory" , 0 , 5 , 5 );
	static equipement anneauSimple = new equipement( "Anneau" , "Un simple anneau" , "accessory" , 0 , 0 , 2 );
	static equipement anneauUnique = new equipement( "Anneau Unique" , "l'anneau du Mordor" , "accessory" , 250 , 50 , 50 );
	static equipement broche       = new equipement( "Broche" , "Une broche de protection" , "accessory" , 0 , 0 , 6 );
    static equipement pendentif    = new equipement( "Pendentif" , "Un joli pendentif" , "accessory" , 0 , 0 , 8 );

 // ---------------------------------------------------------------------------------------------------------
 // les armes -----------------------------------------------------------------------------------------------

    
    static equipement hache         = new equipement( "Hache" , "Une simple hache" , "weapon" , 20 , 0 , 0 );
    static equipement hacheDurandil = new equipement( "Hache Durandil" , "Par la barbe de Goltor l'intrepide !" , "weapon" , 25 , 0 , 0 );

    static equipement dague         = new equipement( "Dague" , "Une vulgaire dague" , "weapon" , 17 , 0 , 0 );
    static equipement dagueRouillee = new equipement( "Dague rouillee" , "Une dague rouillee, le tetanos assure" , "weapon" , 15 , 0 , -1 );
    static equipement epee          = new equipement( "Epee" , "Une simple epee" , "weapon" , 20 , 0 , 0 );
    static equipement epeeChantante = new equipement( "Epee chantante" , "Quelle belle melodie" , "weapon" , 25 , 0 , 0 );

    static equipement shotgun      = new equipement( "Shotgun" , "Un fusil e pompe" , "weapon" , 35 , 0 , 0 );
    static equipement doubleBarrel = new equipement( "Double barrel" , "Le juxtapose de DOOM" , "weapon" , 85 , 0 , 0 );
    
    static equipement chainsaw = new equipement( "Chainsaw" , "Une troneonneuse" , "weapon" , 35 , 0 , 0 );
    static equipement BFG9000  = new equipement( "BFG9000" , "! Big Fucking Gun !" , "weapon" , 9000 , 0 , 0 );

 // ---------------------------------------------------------------------------------------------------------
 // les armures ---------------------------------------------------------------------------------------------
    
    static equipement armureSimple  = new equipement( "Armure" , "Une simple armure" , "armor" , 0 , 8 , 0 );
    static equipement armureEnOr    = new equipement( "Armure en or" , "Une armure en or" , "armor" , 0 , 5 , 0 );
    static equipement armureTicaire = new equipement( "Armure Ticaire" , "Une armure qui pique" , "armor" , 0 , 10 , -10 );
    static equipement armureMithril = new equipement( "Armure en mithril" , "Une armure super resistante" , "armor" , 0 , 20 , 0 );

 // ---------------------------------------------------------------------------------------------------------
 // les boucliers -------------------------------------------------------------------------------------------
    
    static equipement bouclierSimple  = new equipement( "Bouclier" , "Un simple bouclier" , "shield" , 0 , 8 , 0 );
    static equipement bouclierEnOr    = new equipement( "Bouclier en or" , "Un bouclier en or" , "shield" , 0 , 5 , 0 );
    static equipement bouclierLektrik = new equipement( "Bouclier Lektrik" , "Un bouclier qui fait des etincelles" , "shield" , 0 , 10 , -10 );
    static equipement bouclierMithril = new equipement( "Bouclier en mithril" , "Un bouclier super resistant" , "shield" , 0 , 20 , 0 );
    
 // ---------------------------------------------------------------------------------------------------------
 // listes d'accessoires, d'armes, d'armures et de boucliers ------------------------------------------------
    
    public static ArrayList<equipement> accessoiresEnBoutique;
    static ArrayList<equipement> armesEnBoutique;
    static ArrayList<equipement> armuresEnBoutique;
    static ArrayList<equipement> boucliersEnBoutique;
    
 // methodes ------------------------------------------------------------------------------------------------
    
     // preparation de la boutique
    public static void prepareBoutique( int nbPersonnages )
    {
    	 // ajout d'une marge : les objets les plus puissants seront plus rares e obtenir
    	nbPersonnages += 20;
    	
    	 // les items en exemplaire unique
    	accessoiresEnBoutique = new ArrayList<>(Arrays.asList( anneauUnique ));
    	armesEnBoutique       = new ArrayList<>(Arrays.asList( doubleBarrel , doubleBarrel , BFG9000 ));
    	armuresEnBoutique     = new ArrayList<>();
    	boucliersEnBoutique   = new ArrayList<>();
    	
    	 // les items en exemplaires multiples
    	for ( int i = 0 ; i < nbPersonnages ; i++ )
    	{
    		accessoiresEnBoutique.add( anneauSimple );
    		accessoiresEnBoutique.add( amulette );
    		accessoiresEnBoutique.add( broche );
    		accessoiresEnBoutique.add( pendentif );

    		armesEnBoutique.add( hache );
    		armesEnBoutique.add( hacheDurandil );
    		armesEnBoutique.add( dague );
    		armesEnBoutique.add( dagueRouillee );
    		armesEnBoutique.add( epee );
    		armesEnBoutique.add( epeeChantante );
    		armesEnBoutique.add( shotgun );
    		armesEnBoutique.add( chainsaw );
    		
    		armuresEnBoutique.add( armureSimple );
    		armuresEnBoutique.add( armureEnOr );
    		armuresEnBoutique.add( armureTicaire );
    		armuresEnBoutique.add( armureMithril );
    		
    		boucliersEnBoutique.add( bouclierSimple );
    		boucliersEnBoutique.add( bouclierEnOr );
    		boucliersEnBoutique.add( bouclierLektrik );
    		boucliersEnBoutique.add( bouclierMithril );
    	}
    }
}