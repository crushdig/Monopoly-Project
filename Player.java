
import java.util.ArrayList;

//This class is for the player and their characteristics
public class Player {
	
	private String name;
	private int balance;
	private int position;
	ArrayList<Property> assets;//Stores the players assets
	private static final String[] COLOUR_GROUP_NAME = {"brown","light blue", 
		    "pink", "orange", "red", "yellow", "green", "dark blue"};
		private static final int[] NUM_IN_GROUP = {2,3,3,3,3,3,3,2};
	
	public Player(String name){
		this.name = name;
		this.balance = 1500;
		this.position = 0;
		this.assets = new ArrayList<Property>();
	}

	public String getName(){
		return this.name;
	}
	
	public void buy(Property property){
		balance = balance - property.getValue();
		assets.add(property);
		property.setOwned(this);
	}
	
	public void getRent(int rent){
		balance = balance + rent;
	}
	
	public int payRent(int rent){
		balance = balance - rent;
		return rent;
	}
	
	
	public String getBalance(){
		return "Balance is\t" + this.balance;
	}
	
	public String getProperties(){
		
		int i;
		
		if(assets.size() == 0) return "No properties. ";
		
		String summary = "Properties are:\n";
		
		for(i=0;i<assets.size();i++){
			summary = summary + assets.get(i).getName() + "\n";
		}
		return summary;
	}
	
	//Function for calculating player worth at the end of the game
	public int getWorth(){
		int i, worth = 0;
		
		for(i=0;i<assets.size();i++){
			worth = worth + assets.get(i).getValue();
		}
		return worth + this.balance;
	}
	
	public boolean Monopoly(String colour)
	{
	  int count_The_Colour_Of_Each_Property = 0, i;
	  
	  for(i = 0; i < assets.size(); i++)
	  {
	    if(assets.get(i).getColour() == colour)
	    {
	      count_The_Colour_Of_Each_Property++;
	    }
	  }
	  
	  for(i = 0; i < COLOUR_GROUP_NAME.length; i++)
	  {
	    if(COLOUR_GROUP_NAME[i] == colour)
	    {
	      break;
	    }
	  }
	  
	  if(count_The_Colour_Of_Each_Property == NUM_IN_GROUP[i])
	  { 
	    return true;
	  }
	  
	  return false;
	}
	
	public void mortgage(Property property){
	property.mortgage();
	balance += property.getMortgageValue();
	}
	
	public void redeem(Property property){
		property.redeem();
		balance -= property.getMortgageValue();
	}
	
	public void move(int position){
		this.position = position;
	}
	
	public int getPosition(){
		return position;
	}
	

}
