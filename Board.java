import java.util.ArrayList;

//This class deals with setting up the board with required Squares
public class Board {
	
	
	public static final int NUM_SQUARES = 40;
	public static final int TYPE_GO = 0;
	public static final int TYPE_SITE = 1;
	public static final int TYPE_STATION = 2;
	public static final int TYPE_UTILITY = 3;
	public static final int TYPE_COMMUNITY = 4;
	public static final int TYPE_CHANCE = 5;
	public static final int TYPE_JAIL = 6;
	public static final int TYPE_PARKING = 7;
	public static final int TYPE_GOTO_JAIL = 8;
	public static final int TYPE_TAX = 9;
	
	private static final int COL_BROWN = 0;
	private static final int COL_LIGHT_BLUE = 1;
	private static final int COL_PINK = 2;
	private static final int COL_ORANGE = 3;
	private static final int COL_RED = 4;
	private static final int COL_YELLOW = 5;
	private static final int COL_GREEN = 6;
	private static final int COL_DARK_BLUE = 7;

	private static final int[] SITE_COLOURS = {
	COL_BROWN, COL_BROWN, COL_LIGHT_BLUE, COL_LIGHT_BLUE, COL_LIGHT_BLUE,
	COL_PINK, COL_PINK, COL_PINK, COL_ORANGE, COL_ORANGE, COL_ORANGE,
	COL_RED, COL_RED, COL_RED, COL_YELLOW, COL_YELLOW, COL_YELLOW,
	COL_GREEN, COL_GREEN, COL_GREEN, COL_DARK_BLUE, COL_DARK_BLUE};
	
	private static final int[] STATION_RENTS = {25,50,100,200,200,200};
	
	private static final int[] UTILITY_RENTS = {4,10};
	
	private static final int[][] SITE_RENTS = {
			{2,10,30,90,160,250},{4,20,60,180,320,450},{6,30,90,270,400,550},{6,30,90,270,
			400,550},{8,40,100,300,450,600},
			{10,50,150,450,625,750},{10,50,150,450,625,750},{12,60,180,500,700,900},{14,70
			,200,550,750,950},{25,50,100,200,200,200},{14,70,200,550,750,950},{16,80,220,600,800,1000},
			{18,90,250,700,875,1050},{4,10,0,0,0,0},{18,90,250,700,875,1050},{25,50,100,200,200,200},
			{20,100,300,750,925,1100},{25,50,100,200,200,200},{22,110,330,800,975,1150}
			,{22,110,330,800,975,1150},{22,120,360,850,1025,1200},
			{26,130,390,900,1100,1275},{26,130,390,900,1100,1275},{28,150,450,1000,1200,1400},
			{25,50,100,200,200,200},{35,175,500,1100,1300,1500}};
	
	
	private static final int[] HOUSE_PRICE = {
			50,50,50,50,50,
			100,100,100,100,100,100,
			150,50,150,150,150,150,150,
			200,200,200,200,200};
	
	private static final int[] SQUARE_TYPES = {
	TYPE_GO, TYPE_SITE, TYPE_COMMUNITY, TYPE_SITE, TYPE_TAX, TYPE_STATION,
	TYPE_SITE, TYPE_CHANCE, TYPE_SITE, TYPE_SITE,
	TYPE_JAIL, TYPE_SITE, TYPE_UTILITY, TYPE_SITE, TYPE_SITE, TYPE_STATION,
	TYPE_SITE, TYPE_COMMUNITY, TYPE_SITE, TYPE_SITE,
	TYPE_PARKING, TYPE_SITE, TYPE_CHANCE, TYPE_SITE, TYPE_SITE,
	TYPE_STATION, TYPE_SITE, TYPE_SITE, TYPE_UTILITY, TYPE_SITE,
	TYPE_GOTO_JAIL, TYPE_SITE, TYPE_SITE, TYPE_COMMUNITY, TYPE_SITE,
	TYPE_STATION, TYPE_CHANCE, TYPE_SITE, TYPE_TAX, TYPE_SITE};
	
	private static final String GO_NAME = "GO";
	
	 
	 private ArrayList<Square> squares;
	 
	
	  
	  private static final String[] SITE_NAMES = {
			  "Old Kent Rd","Whitechapel Rd","The Angel Islington","Euston Rd","Pentonville Rd",
			  "Pall Mall","Whitehall","Northumberland Ave","Bow St","MarlboroughSt","Vine St",
			  "Strand","Fleet St","Trafalgar Sq","Leicester Sq","CoventrySt","Piccadilly",
			  "Regent St","Oxford St","Bond St","Park Lane","Mayfair"};
	  
	  private static final int[] SITE_PRICES = {
			  60,60,100,100,120,
			  140,140,160,180,180,200,
			  220,220,240,260,260,280,
			  300,300,320,350,400};
	  
	  private static final int[] SITE_MORTGAGE_VALUE = {
			  50,50,50,50,60,
			  70,70,80,90,90,100,
			  110,110,120,150,150,150,
			  200,200,200,175,200};
	  	  
			
	  private static final String COMMUNITY_NAME = "Community Chest";
	  private static final String CHANCE_NAME = "Chance";
	  private static final String JAIL_NAME = "Jail (Just Visiting)";
	  private static final String PARKING_NAME = "Free Parking";
	  private static final String GOTO_JAIL_NAME = "Go To Jail";
			  
						  
			  private static final String[] STATION_NAMES = {
			  "King's Cross Station",
			  "Marylebone Station",
			  "Fenchurch St Station",
			  "Liverpool St Station"};
			 
			  
			  private static final int STATION_PRICE = 200;
			  
			  private static final int STATION_MORTGAGE_VALUE = 100;
			  
			  private static final String[] UTILITY_NAMES = {"Electric Co","Water Works"};

			  private static final int UTILITY_PRICE = 150;
			  
			  private static final int UTILITY_MORTGAGE_VALUE = 75;
	
	public Board(){
		
		squares = new ArrayList<Square>();	
		 int i;
		 int a=0,b=0,c =0;
		  //Initialising the squares
		 
		 for(i=0; i<SQUARE_TYPES.length ; i++){
			 	 
			 switch(SQUARE_TYPES[i]){
			case 0:
				squares.add(new Square(GO_NAME, SQUARE_TYPES[i]));
				break;
			case 1:
				squares.add(new Site(SITE_PRICES[a], SITE_NAMES[a], SQUARE_TYPES[i], SITE_RENTS[a], SITE_COLOURS[a], HOUSE_PRICE[a], SITE_MORTGAGE_VALUE[a]));
				a++;
				break;
			case 2:
				squares.add(new Station(STATION_PRICE, STATION_NAMES[b], SQUARE_TYPES[i], STATION_RENTS, STATION_MORTGAGE_VALUE));
				b++;
				break;
			case 3:
				squares.add(new Utility(UTILITY_PRICE, UTILITY_NAMES[c], SQUARE_TYPES[i], UTILITY_RENTS, UTILITY_MORTGAGE_VALUE));
				c++;
				break;
			case 4:
				squares.add(new Square(COMMUNITY_NAME, SQUARE_TYPES[i]));
				break;
			case 5:
				squares.add(new Square(CHANCE_NAME, SQUARE_TYPES[i]));
				break;
			case 6:
				squares.add(new Square(JAIL_NAME, SQUARE_TYPES[i]));
				break;
			case 7:
				squares.add(new Square(PARKING_NAME, SQUARE_TYPES[i]));
				break;
			case 8:
				squares.add(new Square(GOTO_JAIL_NAME, SQUARE_TYPES[i]));
				break;
			case 9:
				squares.add(new Square("Tax", SQUARE_TYPES[i]));
				break;	 
			 
			 }
			 		
		 }
		 
		 		 
	}
	
	//Returns information about a square
	public String squareInfo(int position){
		 
		if( (squares.get(position).getType() == 1) || (squares.get(position).getType() == 2) || squares.get(position).getType() == 3)
		{
			return squares.get(position).getName() + " " + ((Property) squares.get(position)).getValue();
		}
		
		else return squares.get(position).getName() ;
		
	}
	
	//Returns the type of the square
	public int squareType(int position){
		return squares.get(position).getType();
	}
	
	//Returns the property on the square at this position
	public Property getProperty(int position){
				
		return (Property) squares.get(position);
	}
	
	
}

 
