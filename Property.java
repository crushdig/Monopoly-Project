
//This class is for the properties and their characteristics, it inherits Square as a Property is a Square
public class Property extends Square{
	
	private int value;
	protected int[] rent;
	private boolean owned;
	private Player owner;
	protected boolean mortgaged;
	protected int mortgage_value;
	
	
	public Property(int value, String name, int type, int[] rent, int mortgage_value){
		super(name,type);
		this.value = value;
		this.owned = false;
		this.mortgaged = false;
		this.mortgage_value = mortgage_value;
		this.owner = null;
		this.rent = rent;
		
		
	}
	
	
	public int getValue(){
		return value;
	}
	
	public int getRent(){
		return rent[0];
	}
	
	public Player getOwner(){
		return this.owner;
	}
	
	public void setOwned(Player person){
		this.owned = true;
		this.owner = person;
	}
	
	public boolean owned(){
		return owned;
	}
	
	public boolean isMortgaged(){
		return mortgaged;
	}
	
	public void mortgage(){
	mortgaged = true;
	}
	
	public void redeem(){
		mortgaged = false;
	}
	
	public int getMortgageValue(){
		return this.mortgage_value;
	}
	
	public String getColour()
	{
	  return null;
	}
	
	public String toString () {
		return this.getName() + " " + this.getRent();
	}
	

}
