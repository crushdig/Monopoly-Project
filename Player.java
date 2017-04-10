
import java.util.ArrayList;

//This class is for the player and their characteristics
public class Player {

	private String name;
	private double balance;
	boolean bankrupt = false;
	boolean inJail = false;
	private int jailCard = 1;
	private int jailCount =0;
	private int rollCount = 0;
	Token token;
	private int position;
	ArrayList<Property> assets;//Stores the players assets
	
	  
	  

	public Player(String name){
		this.name = name;
		this.balance = 1500;
		this.position = 0;
		this.assets = new ArrayList<Property>();
		this.token = new Token();
	}

	Player (Player player) {   // copy constructor
		this(player.getName());
	}
	
	public void increaseJailCount(){
	    jailCount++;
	  }
	  
	  public int getJailCount(){
	    return jailCount;
	  }
	  
	  public void increaseRollCount(){
		    rollCount++;
		  }
		  
		  public int getRollCount(){
		    return rollCount;
		  }
	  
	  public boolean hasJailCard(){
	     return !(jailCard==0);
	  }
	  
	  public void addJailCard(){
	    jailCard++;
	  }
	  
	  public void useJailCard(){
	    jailCard--;
	    setJail(false);
	  }
	  
	  public void setJail(boolean status)
	  {
	    inJail =  status;
	    jailCount = 0;
	    rollCount = 0;
	  }
	  
	  public boolean getJail()
	  {
	    return inJail;
	  }
	
	  public int payBail(int bail)
	  {
	    balance = balance - bail;
	    inJail = false;
	    return bail;
	  }
	
	public void setToken(Token token){
		this.token = token;
	}
	
	public Token getToken(){
		return this.token;
	}
	
	public String getName(){
		return this.name;
	}
	
	public boolean isBankrupt(){
		if(this.getbalance()<0){
			bankrupt = true;
		}
		return bankrupt;
	}
	
	public void move(int position){
		this.position = position;
	}
	
	public void moveForward(){
		position++;
		if((position)>=40){
			position = position%40;
		}
	}
	
	public void moveBackward(){
		position--;
		if((position)<0){
			position = position%40;
		}
	}

	public int getPosition(){
		return position;
	}

	public void buy(Property property){
		balance = balance - property.getValue();
		assets.add(property);
		property.setOwned(this);
	}

	public double getRent(double d){
		balance = balance + d;
		return d;
	}

	public int payRent(int rent){
		balance = balance - rent;
		return rent;
	}

	public Property getLatestProperty () {
		return assets.get(assets.size()-1);
	}

	public String getBalance(){
		return "Balance is\t" + this.balance;
	}

	public double getbalance(){
		return this.balance;
	}

	
	public ArrayList<Property> getProperties () {
		return assets;
	}

	//Function for calculating player worth at the end of the game
	public double getWorth(){
		int worth = 0;
		
		for (Property property: assets) {
			worth = worth + property.getValue();
		}
		
		return worth + this.balance;
	}

	public boolean Monopoly(Site site)
	{	
		boolean ownsAll = true;
		ColourGroup colourGroup = site.getColourGroup();
		for (Site s : colourGroup.getMembers()) {
			if (!s.owned() || (s.owned() && s.getOwner() != this))
				ownsAll = false;
		}
		return ownsAll;
		
	}
	
	public int getNumStationsOwned () {
		int numOwned = 0;
		for (Property p : assets) {
			if (p instanceof Station) {
				numOwned++;
			}
		}
		return numOwned;
	}
	
	public int getNumUtilitiesOwned () {
		int numOwned = 0;
		for (Property p : assets) {
			if (p instanceof Utility) {
				numOwned++;
			}
		}
		return numOwned;
	}

	public void mortgage(Property property){
		property.mortgage();
		balance += property.getMortgageValue();
	}

	public void redeem(Property property){
		property.setNotMortgaged();;
		balance -= property.getMortgageValue();
	}

	public boolean equals (String name) {
		return this.name.toLowerCase() == name;
	}
	
	public String toString(){
		return this.name;
	}
	
	public boolean allMortgaged(){
		for (Property p : assets) {
			if (!p.isMortgaged()) {
				return false;
			}
		}
		return true;
	}
	
	public int numberOfHouses(){
		int houses = 0;
		for (Property p : assets) {
			if (p instanceof Site) {
				if(((Site) p).getNumBuildings()<=4)
				houses += ((Site) p).getNumHouses();
			}
		}
		return houses;
	}
	
	public int numberOfHotels(){
		int hotels = 0;
		for (Property p : assets) {
			if (p instanceof Site) {
				hotels += ((Site) p).getNumHotels();
			}
		}
		return hotels;
	}
	
	//for the cheat
	public void addProperty (Property property) {
		property.setOwned(this);
		assets.add(property);
		return;
	}
	

}
