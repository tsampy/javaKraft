package javaKraft;

public class constantes extends boutique {

 // main title ----------------------------------------------------------------------------------------------
	public static final String JAVAKRAFT_TITLE = "JavaCraft version 0.1";
	
	public static final String CENTER_TAGS_CLOSE = "</div></body></html>";
	public static final String CENTER_TAGS_OPEN  = "<html><body><div align='center'>";
	
 // Splash Screen -------------------------------------------------------------------------------------------
	public static final String SPLASH_TITLE = "Bienvenue dans " + JAVAKRAFT_TITLE;
    public static final String SPLASH_TEXT  = CENTER_TAGS_OPEN
            									+ "Bienvenue dans JavaCraft !<br>"
            									+ "Un jeu qui envoie du pete !"
            									+ CENTER_TAGS_CLOSE;

 // le sexe des personnages ---------------------------------------------------------------------------------
    public static final boolean MALE        = true;
    public static final boolean FEMALE      = false;
    public static final boolean DEFAULT_SEX = MALE;

 // Les differentes races disponibles -----------------------------------------------------------------------
    public static final int ELFE   = 0;
    public static final int HUMAIN = 1;
    public static final int ORC    = 2;
    
    public static final String[] PERSONNAGE_TYPES_TO_STRING = { "elfe" , "humain" , "orc" };

 // Delais d'attente pour l'affichage des informations console ----------------------------------------------
    public static final int DELAY_CHAR  = 0; //500;
    public static final int DELAY_ROUND = 0; //500;

 // La main tenant une arme  --------------------------------------------------------------------------------
    public static final int RIGHT_HAND = 0;
    public static final int LEFT_HAND  = 1;

 // Ordonnee du personnage, au sol ou cache dans un arbre ---------------------------------------------------
    public static final int ON_GROUND = 0;
    public static final int IN_TREE   = 1;

 // Limites du nombre d'armes et d'accessoires --------------------------------------------------------------
    public static final int NB_MAXIMUM_EQUIPEMENTS = 4;
    public static final int NB_MAXIMUM_WEAPONS     = 2;

 // Les differentes actions possibles -----------------------------------------------------------------------
    public static final int ACTION_ATTAQUER     = 0;
    public static final int ACTION_PARER    	= 1;
    public static final int ACTION_POUVOIR  	= 2;
    public static final int ACTION_METIER   	= 3;
    public static final int ACTION_INVISIBILITE = 4;
    public static final int ACTION_NULL     	= 5;
    public static final int DEFAULT_ACTION 		= ACTION_ATTAQUER;
    
    public static final String[] ACTIONS_TO_STRING = { "Attaque" , "Parade" , "Utilisation du pouvoir"
    													, "Utilisation du metier" , "Dissimulation" , "Aucune action"};
    
 // Limite de dissimulation des elfes en nombre de tours ----------------------------------------------------
    public static final int MAXIMUM_HIDDEN_ROUNDS = 5;
    
 // Limite du nombre de tours jouables ----------------------------------------------------------------------
    public static final int MAXIMUM_PLAYABLE_ROUNDS = 250;
    
 // La puissance du berserk des orcs ------------------------------------------------------------------------
    public static final int BERSERK_ATTACK_BONUS = 5;
    
 // Le bonus des orcs face e un elfe sans bouclier
    public static final int ORC_ATTACK_BONUS = 50;
    
 // type d'equipements disponibles --------------------------------------------------------------------------
    public static final String TYPE_ACCESSORY = "accessory";
    public static final String TYPE_ARMOR     = "armor";
    public static final String TYPE_BAREHAND  = "barehand";
    public static final String TYPE_SHIELD    = "shield";
    public static final String TYPE_WEAPON    = "weapon";
    
 // constantes liees e la gestion de XP et du niveau des metiers --------------------------------------------
    public static final int DERNIER_SOUFFLE = 10;
    
    public static final int XP_NEEDED_TO_GAIN_CRAFT_LEVEL = 20;
	
	public static final int XP_GAIN_AGAINST_LEVEL_INF = 5;
	public static final int XP_GAIN_AGAINST_LEVEL_SUP = 10;
	public static final int XP_GAIN_EQUALS_CRAFTS     = 10;
	
 // les differents metiers existants ------------------------------------------------------------------------
	public static final String CRAFT_MAGE      = "mage";
	public static final String CRAFT_THIEF     = "voleur";
	public static final String CRAFT_UNDEFINED = "chomeur";
	public static final String CRAFT_WARRIOR   = "guerrier";
	
	public static final String[] CRAFTS_TO_STRING = { CRAFT_MAGE , CRAFT_THIEF , CRAFT_UNDEFINED , CRAFT_WARRIOR };
	
 // les bonus ou malus lies e l'utilisation des metiers -----------------------------------------------------
	public static final int CRAFT_BONUS_MAGE  = 5;
	public static final int CRAFT_MALUS_THIEF = 10;
}