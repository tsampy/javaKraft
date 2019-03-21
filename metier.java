package javaKraft;

 // un metier -----------------------------------------------------------------------------------------------
public abstract class metier extends actionsCommunes {
	
 // ---------------------------------------------------------------------------------------------------------
 // variables privees ---------------------------------------------------------------------------------------
	
	private int craftBonusAttaque = 0;
	private int craftBonusDefense = 0;
	private int craftBonusPV      = 0;
	
	private int craftLevel;
	private int craftXP;
	
	private String craftName;
	
 // ---------------------------------------------------------------------------------------------------------
 // methodes ------------------------------------------------------------------------------------------------
	
	 // accesseur du bonus d'attaque
	public int craftBonusAttaque()
	{
		return craftBonusAttaque;
	}
	
	 // accesseur du bonus de defense
	public int craftBonusDefense()
	{
		return craftBonusDefense;
	}
	
	 // accesseur du bonus de points de vie
	public int craftBonusPV()
	{
		return craftBonusPV;
	}
	
	 // diminution du bonus d'attaque du metier
	public void decreaseCraftBonusAttaque( int bonusAttaque )
	{
		increaseCraftBonusAttaque( ( -1 ) * bonusAttaque );
	}
		
	 // diminution du bonus de defense du metier
	public void decreaseCraftBonusDefense( int bonusDefense )
	{
		increaseCraftBonusDefense( ( -1 ) * bonusDefense );
	}
			
	 // diminution du bonus de points de vie du metier
	public void decreaseCraftBonusPV( int bonusPV )
	{
		increaseCraftBonusPV( ( -1 ) * bonusPV );
	}
	
	 // accesseur du niveau
	public int getCraftLevel()
	{
		return craftLevel;
	}
	
	 // accesseur du nom de metier
	public String getCraftName()
	{
		return craftName;
	}
	
	 // accesseur de XP
	public int getCraftXP()
	{
		return craftXP;
	}
	
	 // augmentation du bonus d'attaque du metier
	public void increaseCraftBonusAttaque( int bonusAttaque )
	{
		setCraftBonusAttaque( craftBonusAttaque() + bonusAttaque );
	}
	
	 // augmentation du bonus de defense du metier
	public void increaseCraftBonusDefense( int bonusDefense )
	{
		setCraftBonusDefense( craftBonusDefense() + bonusDefense );
	}
		
	 // augmentation du bonus de points de vie du metier
	public void increaseCraftBonusPV( int bonusPV )
	{
		setCraftBonusPV( craftBonusPV() + bonusPV );
	}
	
	 // augmentation de XP
	public void increaseCraftXP( String persoName , int boostXP )
	{
		sortieConsole( "XP de " + persoName + " augmente de " + boostXP
						+ " et passe de " + getCraftXP() + " e " + ( getCraftXP() + boostXP ) );

		setCraftXP( getCraftXP() + boostXP );
	}
	
	 // augmentation de XP apres une victoire au combat
	public void increaseCraftXP_Fight_Won( String persoName , personnage ennemi )
	{
		 // si le niveau de l'ennemi est superieur e celui du personnage,
		if ( ennemi.getCraftLevel() > getCraftLevel() )
			increaseCraftXP( persoName , XP_GAIN_AGAINST_LEVEL_SUP );
		else increaseCraftXP( persoName , XP_GAIN_AGAINST_LEVEL_INF );
		 // Bonus, si les deux personnages ont le meme metier
		if ( ennemi.getCraftName() == getCraftName() )
			increaseCraftXP( persoName , XP_GAIN_EQUALS_CRAFTS );
		
	}
	
	 // initialisation d'un metier
	public void initCraft( String craftName )
	{
		initCraft( craftName , 0 );
	}
		
	 // initialisation d'un metier, en fonction d'une XP donnee
	public void initCraft( String craftName , int craftXP )
	{
		switch(craftName)
		{
		case CRAFT_THIEF:
			setCraftBonusAttaque( +2 );
			setCraftBonusPV( -10 );
			break;
		case CRAFT_WARRIOR:
			setCraftBonusAttaque( +1 );
			break;
		default: break;
		}
		
		setCraftName( craftName );
		initCraftXP( craftXP );
	}
	
	 // initialisation de XP du metier
	public void initCraftXP()
	{
		initCraftXP( 0 );
	}
	
	 // initialisation de XP du metier en fonction d'une XP passee en parametre
	public void initCraftXP( int craftXP )
	{
		setCraftXP( craftXP );
	}
	
	 // mutateur du bonus d'attaque
	public void setCraftBonusAttaque( int craftBonusAttaque )
	{
		this.craftBonusAttaque = craftBonusAttaque;
	}
		
	 // mutateur du bonus de defense
	public void setCraftBonusDefense( int craftBonusDefense )
	{
		this.craftBonusDefense = craftBonusDefense;
	}
		
	 // mutateur du bonus de points de vie
	public void setCraftBonusPV( int craftBonusPV )
	{
		this.craftBonusPV = craftBonusPV;
	}
	
	 // mutateur du niveau
	public void setCraftLevel( int craftLevel )
	{
		this.craftLevel = craftLevel;
	}
	
	 // mutateur du nom de metier
	public void setCraftName( String craftName )
	{
		this.craftName = craftName;
	}

	 // mutateur de XP, avec calcul du niveau
	public void setCraftXP( int craftXP )
	{
		this.craftXP = craftXP;
		setCraftLevel( ( craftXP / XP_NEEDED_TO_GAIN_CRAFT_LEVEL ) + 1 );
	}
	
	 // toString()
	public String craftToString()
	{
		return ( getCraftName() + ", niveau " + getCraftLevel() + ", XP " + getCraftXP() );
	}
	
	 // utilisation d'une action-metier
	public String actionCraftToString( String currentName )
	{
		return currentName + " : Au travail , " + getCraftName();
	}
	
	 // String toMinimalString()
    public String craftSpecsToMinimalString()
    {
    	return super.specsToMinimalString( craftBonusPV() , craftBonusAttaque() , craftBonusDefense() );
    }
	
 // ---------------------------------------------------------------------------------------------------------
 // methodes abstraites -------------------------------------------------------------------------------------
	
	public abstract void actionMetier( personnage ennemi );
}