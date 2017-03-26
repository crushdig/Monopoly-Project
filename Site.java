
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
	
	public void buildHouse(){
		houses++;
	}
	
	public void buildHotel(){
		hotels++;
	}
	
	public int getHotelPrice(){
		return hotel_price;
	}
	
	public int getHousePrice(){
		return house_price;
	}
	
	public int getRent(){
		return rent[this.houses];
	}

	public String toString () {
		return this.getName() + " " + this.getRent() + " " + this.getColour();
	}
}
