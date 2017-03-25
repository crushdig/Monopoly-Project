package sprint_Three;
import java.util.ArrayList;

public class Player {
	
	private String name;
	private int position;
	private int balance;
	private int amount;
	private String token;
	private boolean passedGo;
	private ArrayList<Property> properties = new ArrayList<Property>();
	private static final String[] COLOUR_GROUP_NAME = {"brown","light blue", 
	    "pink", "orange", "red", "yellow", "green", "dark blue"};
	private static final int[] NUM_IN_GROUP = {2,3,3,3,3,3,3,2};
	
	Player (String name, String token) {
		this.name = name;
		this.token = token;
		position = 0;
		balance = 0;
		passedGo = false;
	}
	
	public void move (int squares) {
		position = position + squares;
		if (position >= Board.NUM_SQUARES) {
			position = position - Board.NUM_SQUARES;
			passedGo = true;
		} else {
			passedGo = false;
		}
		if (position < 0) {
			position = position + Board.NUM_SQUARES;
		} 
	}
	
	public boolean Monopoly(String colour)
	{
	  int count_The_Colour_Of_Each_Property = 0, i;
	  
	  for(i = 0; i < properties.size(); i++)
	  {
	    if(properties.get(i).getColour() == colour)
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

	public void doTransaction (int amount) {
		balance = balance + amount;
		this.amount = amount;
	}
	
	public int getPosition () {
		return position;
	}
	
	public String getName () {
		return name;
	}
	
	public int getTransaction () {
		return amount;
	}
	
	public int getBalance () {
		return balance;
	}
	
	public boolean passedGo () {
		return passedGo;
	}
	
	public void boughtProperty (Property property) {
		property.setOwner(this);
		properties.add(property);
		return;
	}
	
	public Property getLatestProperty () {
		return properties.get(properties.size()-1);
	}
	
	public ArrayList<Property> getProperties () {
		return properties;
	}
	
	public int getAssets () {
		int assets = balance;
		for (Property property: properties) {
			assets = assets + property.getValue();
		}
		return assets;
	}
	
	public String toString () {
		return name + " (" + token + ")";
	}
}
