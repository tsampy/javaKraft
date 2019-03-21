package javaKraft;

 // classe gerant un equipement
public class equipement {

 // ---------------------------------------------------------------------------------------------------------
 // variables privees ---------------------------------------------------------------------------------------
	
    private String name;
    private String description;
    private String type;

    private int bonusAttaque;
    private int bonusDefense;
    private int bonusPV;

 // ---------------------------------------------------------------------------------------------------------
 // constructeurs -------------------------------------------------------------------------------------------
    
    equipement( String name , String description , String type , int bonusAttaque , int bonusDefense , int bonusPV )
    {
        setBonusAttaque( bonusAttaque );
        setBonusDefense( bonusDefense );
        setBonusPV( bonusPV );
        setDescription( description );
        setName( name );
        setType( type );
    }
    
    equipement( String name , String description , int bonusAttaque , int bonusDefense , int bonusPV )
    {
    	this( name , description , "undefined" , bonusAttaque , bonusDefense , bonusPV );
    }
    
 // ---------------------------------------------------------------------------------------------------------
 // methodes ------------------------------------------------------------------------------------------------

     // accesseur du bonus d'attaque
    public int getBonusAttaque()
    {
        return bonusAttaque;
    }

     // accesseur du bonus de defense
    public int getBonusDefense()
    {
        return bonusDefense;
    }

     // accesseur du bonus de points de vie
    public int getBonusPV()
    {
        return bonusPV;
    }

     // accesseur de la description
    public String getDescription()
    {
        return description;
    }

     // accesseur du nom
    public String getName()
    {
        return name;
    }
    
     // accesseur du type d'equipement : (String)accessory, armor, shield, undefined ou weapon
    public String getType()
    {
    	return type;
    }

     // mutateur du bonus d'attaque
    public void setBonusAttaque(int bonusAttaque)
    {
        this.bonusAttaque = bonusAttaque;
    }

     // mutateur du bonus de defense
    public void setBonusDefense(int bonusDefense)
    {
        this.bonusDefense = bonusDefense;
    }

     // mutateur du bonus de points de vie
    public void setBonusPV(int bonusPV)
    {
        this.bonusPV = bonusPV;
    }

     // mutateur de la description
    public void setDescription(String description)
    {
        this.description = description;
    }

     // mutateur du nom
    public void setName(String name)
    {
        this.name = name;
    }
    
     // mutateur du type
    public void setType(String type)
    {
    	this.type = type;
    }
    
     // String toMinimalString()
    public String toMinimalString()
    {
    	String tmpStr = getName() + " : " + getDescription() + " {";
    	
    	 // si un bonus de points de vie existe, on l'ajoute
    	if ( getBonusPV() != 0 )
    		tmpStr += " PV " + ( getBonusPV() > 0 ? "+" : "" ) + getBonusPV();
    	
    	 // si un bonus de points d'attaque existe, on l'ajoute
    	if ( getBonusAttaque() != 0 )
    		tmpStr += ( getBonusPV() != 0 ? " /" : "" ) 
    					+ " Att " + ( getBonusAttaque() > 0 ? "+" : "" ) + getBonusAttaque();
    	
    	 // si un bonus de points d'attaque existe, on l'ajoute
    	if ( getBonusDefense() != 0 )
    		tmpStr += ( (( getBonusPV() != 0 ) || ( getBonusAttaque() != 0 )) ? " /" : "" )
    				+ " Def " + ( getBonusDefense() > 0 ? "+" : "" ) + getBonusDefense();
    	
    	tmpStr += " }";
    	
    	return tmpStr;
    }

     // String toString()
    public String toString()
    {
    	return getName() + " : " + getDescription() + " { PV " + getBonusPV()
        + " / Att " + getBonusAttaque()
        + " / Def " + getBonusDefense() + " }";
    }
}