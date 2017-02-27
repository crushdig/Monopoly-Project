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
              
              private static final String[] UTILITY_NAMES = {"Electric Co","Water Works"};

              private static final int UTILITY_PRICE = 150;
    
    public Board(ArrayList<Player> players){
        
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
                squares.add(new Property(SITE_PRICES[a], SITE_NAMES[a], SQUARE_TYPES[i]));
                a++;
                break;
            case 2:
                squares.add(new Property(STATION_PRICE, STATION_NAMES[b], SQUARE_TYPES[i]));
                b++;
                break;
            case 3:
                squares.add(new Property(UTILITY_PRICE, UTILITY_NAMES[c], SQUARE_TYPES[i]));
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

 