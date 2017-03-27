
public class Site extends Property{

	private String color;
	private int house_price;
	private final int hotel_price = 250;
	private int houses;
	private int hotels;
	private static final String[] COLOUR_GROUP_NAME = {"brown","lightblue","pink","orange","red","yellow","green","dark blue"};
	
	public Site(int value, String name, int type, int[] rent, int color, int house_price, int mortgage_value) {
		super(value, name, type, rent, mortgage_value);
		this.color = COLOUR_GROUP_NAME[color];
		houses = 0;
		hotels = 0;
		this.house_price = house_price;
		// TODO Auto-generated constructor stub
	}
	
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
	
	public int getRent(Player person){
		int count = houses + hotels;
		return rent[count];
	}

	public String toString () {
		return this.getName() + " " + this.getColour();
	}
}
