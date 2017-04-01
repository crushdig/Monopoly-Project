
public class Site extends Property{

	private static int MAX_NUM_UNITS=5;
	
	private String color;
	private int house_price;
	private final int hotel_price = 250;
	private int houses;
	private int hotels;
	private ColourGroup[] SITE_COLORS ={new ColourGroup("brown"),new ColourGroup("light blue"),new ColourGroup("pink"),
			 new ColourGroup("orange"),new ColourGroup("red"),new ColourGroup("yellow"),
			 new ColourGroup("green"),new ColourGroup("dark blue")};
	
	//Adding from Chris
	private ColourGroup colourGroup;
	private int numBuildings;
	//
	
	public Site(int value, String name, int type, int[] rent, int colorGroup, int house_price, int mortgage_value) {
		super(value, name, type, rent, mortgage_value);
		this.colourGroup = SITE_COLORS[colorGroup];
		this.color = this.colourGroup.getName();
		houses = 0;
		hotels = 0;
		this.house_price = house_price;
		numBuildings = 0;
		colourGroup.addMember(this);
		// TODO Auto-generated constructor stub
	}
	
	//Adding from Chris
	public boolean canBuild (int numToBuild) {
		 return (numBuildings+numToBuild)<=MAX_NUM_UNITS;
	}
	
	public void build (int numToBuild) {
		if (canBuild(numToBuild)) {
			numBuildings = numBuildings + numToBuild;
		}
		return;
	}
	
	public boolean canDemolish (int numToDemolish) {
		return (numBuildings-numToDemolish)>=0;
	}
	
	public void demolish (int numToDemolish) {
		if (canDemolish(numToDemolish)) {
			numBuildings = numBuildings - numToDemolish;
		}
	}
	
	public void demolishAll () {
		numBuildings = 0;
		return;
	}
	
	public int getNumBuildings () {
		return numBuildings;
	}
	
	public boolean hasBuildings () {
		return numBuildings > 0;
	}
	
	// METHODS DEALING WITH COLOUR GROUPS
	
		public ColourGroup getColourGroup () {
			return colourGroup;
		}
	////////////////////
	
	public String getColour()
	{
	  return color;
	}
	
	public int getHouses(){
		return houses;
	}
	
	public int getHotels(){
		return hotels;
	}
	
	public void buildHouse(int number_of_houses, Player person){
		houses += number_of_houses;
		person.payRent(number_of_houses * house_price);
	}
	
	public void buildHotel(int number_of_hotels,  Player person){
		hotels += number_of_hotels;
		person.payRent(number_of_hotels * hotel_price);
	}
	
	public void demolishHotel(int number_of_hotels ,  Player person){
		houses -= number_of_hotels;
		person.getRent(number_of_hotels * hotel_price * 0.5);
	}
	
	public void demolishHouse(int number_of_houses ,  Player person){
		houses -= number_of_houses;
		person.getRent(number_of_houses * (hotel_price * 0.5));
	}
	
	public int getHotelPrice(){
		return hotel_price;
	}
	
	public int getHousePrice(){
		return house_price;
	}
	
	public int getRent(){
		int rent;
		if (numBuildings==0 && super.getOwner().Monopoly(this)) {
			rent = this.rent[0];
		} else if (numBuildings==0 && super.getOwner().Monopoly(this)) {
			rent = 2*this.rent[0]; 
		} else {
			rent = this.rent[numBuildings];
		}
		return rent;
	}

	public String toString () {
		return this.getName() + " " + this.getColour();
	}
}
