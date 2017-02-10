
import java.awt.*;
import javax.swing.*;

public class monopolyBoardandTokens extends JFrame{



	Point[] locations = { new Point(630,643), new Point(587,643),new Point(544,643), new Point(501,643),new Point(458,643), new Point(415,643),new Point(372,643), new Point(329,643),
			new Point(286,643), new Point(243,643),new Point(243,643), new Point(10,10),new Point(630,643), new Point(10,10),new Point(630,643), new Point(10,10),
			new Point(630,643), new Point(10,10),new Point(630,643), new Point(10,10),new Point(630,643), new Point(10,10),new Point(630,643), new Point(10,10),new Point(630,643), new Point(10,10),new Point(630,643), new Point(10,10),new Point(630,643), new Point(10,10),new Point(630,643), new Point(10,10),
			new Point(630,643), new Point(10,10),new Point(630,643), new Point(10,10),new Point(630,643), new Point(10,10),new Point(630,643), new Point(10,10),
			new Point(630,643), new Point(10,10),new Point(630,643), new Point(10,10),new Point(630,643), new Point(10,10),new Point(630,643), new Point(10,10)};
	public static final int NUM_SQUARES = 40;
	private static final int NUM_PROPERTIES = 28;
	private static final String[] PROPERTY_NAMES = {
			"Old Kent Rd","Whitechapel Rd","King's Cross Station","The AngelIslington",
			"Euston Rd","Pentonville Rd",
			"Pall Mall","Electric Co","Whitehall","Northumberland Ave","Marylebone Station"
			,"Bow St","Marlborough St","Vine St",
			"Strand","Fleet St","Trafalgar Sq","Fenchurch St Station","Leicester Sq",
			"Coventry St","Water Works","Piccadilly",
			"Regent St","Oxford St","Bond St","Liverpool St Station","Park Lane",
	"Mayfair"};
	private static final int[] PROPERTY_VALUES = {
			60,60,200,100,100,120,
			140,150,140,160,200,180,180,200,
			220,220,240,200,260,260,150,280,
			300,300,320,200,350,400};
	private static final int[][] PROPERTY_RENTS = {
			{2,10,30,90,160,250},{4,20,60,180,320,450},{25,50,100,200,200,200},{6,30,90,270,400,550},
			{6,30,90,270,400,550},{8,40,100,300,450,600},
			{10,50,150,450,625,750},{4,10,0,0,0,0},{10,50,150,450,625,750},{12,60,180,500,
				700,900},{14,70,200,550,750,950},{25,50,100,200,200,200},{14,70,200,550,750,950}
				,{16,80,220,600,800,1000},
				{18,90,250,700,875,1050},{4,10,0,0,0,0},{18,90,250,700,875,1050},{25,50,100,200,200,200},
				{20,100,300,750,925,1100},{25,50,100,200,200,200},{22,110,330,800,975,1150}
				,{22,110,330,800,975,1150},{4,10,0,0,0,0},{22,120,360,850,1025,1200},
				{26,130,390,900,1100,1275},{26,130,390,900,1100,1275},{28,150,450,1000,1200,1400},
				{25,50,100,200,200,200},{35,175,500,1100,1300,1500}};
	private static final int TYP_GO = 0;
	private static final int TYP_SITE = 1;
	private static final int TYP_STATION = 2;
	private static final int TYP_UTILITY = 3;
	private static final int TYP_COMMUNITY = 4;
	private static final int TYP_CHANCE = 5;
	private static final int TYP_JAIL = 6;
	private static final int TYP_PARKING = 7;
	private static final int TYP_GOTO_JAIL = 8;
	private static final int TYP_TAX = 9;
	private static final int[] SQUARE_TYPES = {
			TYP_GO, TYP_SITE, TYP_COMMUNITY, TYP_SITE, TYP_TAX, TYP_STATION, TYP_SITE,
			TYP_CHANCE, TYP_SITE, TYP_SITE,
			TYP_JAIL, TYP_SITE, TYP_UTILITY, TYP_SITE, TYP_SITE, TYP_STATION, TYP_SITE,
			TYP_COMMUNITY, TYP_SITE, TYP_SITE,
			TYP_PARKING, TYP_SITE, TYP_CHANCE, TYP_SITE, TYP_SITE, TYP_STATION, TYP_SITE,
			TYP_SITE, TYP_UTILITY, TYP_SITE,
			TYP_GOTO_JAIL, TYP_SITE, TYP_SITE, TYP_COMMUNITY, TYP_SITE, TYP_STATION,
			TYP_CHANCE, TYP_SITE, TYP_TAX, TYP_SITE};


	private int players;
	private Token[] token ;



	private static JLabel monopolyLabel;

	public monopolyBoardandTokens() throws InterruptedException{

		players = 6;
		token = new Token[players];

		int offset=10;

		//Initialise tokens and spaces them out with offset


		switch(players){
		case 2 :	
			token[0] = new Token();
			token[0].setBounds(10, 10, 700, 700);
			token[0].setPosition(670,643);

			token[1] = new Token(Color.red);

			token[1].setBounds(10, 10, 700, 700);

			token[1].setPosition(670+offset,643+offset);

			break;

		case 3 :	
			token[0] = new Token();
			
			token[0].setBounds(10, 10, 700, 700);

			token[0].setPosition(670,643);

			token[1] = new Token(Color.red);
			
			token[1].setBounds(10, 10, 700, 700);

			token[1].setPosition(670+offset,643+offset);
			
			System.out.println(token[1].getX());

			offset =offset+10;

			token[2] = new Token(Color.blue);
			
			token[2].setBounds(10, 10, 1000, 1000);

			token[2].setPosition(670+offset,643+offset);
			
			System.out.println(token[2].getX());

			break;
			
		case 4:
			token[0] = new Token();
			
			token[0].setBounds(10, 10, 700, 700);

			token[0].setPosition(670,643);

			token[1] = new Token(Color.red);
			
			token[1].setBounds(10, 10, 700, 700);

			token[1].setPosition(670+offset,643+offset);

			offset =offset+15;

			token[2] = new Token(Color.blue);
			
			token[2].setBounds(10, 10, 700, 700);

			token[2].setPosition(670+offset,643+offset);

			offset =offset+10;

			token[3] = new Token(Color.green);
			
			token[3].setBounds(10, 10, 700, 700);

			token[3].setPosition(670+offset,643+offset);
			break;
			
		case 5 :
			token[0] = new Token();
			
			token[0].setBounds(10, 10, 700, 700);

			token[0].setPosition(670,643);

			token[1] = new Token(Color.red);
			
			token[1].setBounds(10, 10, 700, 700);

			token[1].setPosition(670+offset,643+offset);

			offset =offset+10;

			token[2] = new Token(Color.blue);
			
			token[2].setBounds(10, 10, 700, 700);

			token[2].setPosition(670+offset,643+offset);

			offset =offset+10;

			token[3] = new Token(Color.green);
			
			token[3].setBounds(10, 10, 700, 700);

			token[3].setPosition(670+offset,643+offset);

			offset =offset+10;

			token[4] = new Token(Color.yellow);
			
			token[4].setBounds(10, 10, 700, 700);

			token[4].setPosition(670+offset,643+offset);

			break;
		case 6:
			token[0] = new Token();
			
			token[0].setBounds(10, 10, 700, 700);

			token[0].setPosition(670,643);

			token[1] = new Token(Color.red);
			
			token[1].setBounds(10, 10, 700, 700);

			token[1].setPosition(670+offset,643+offset);

			offset =offset+10;

			token[2] = new Token(Color.blue);
			
			token[2].setBounds(10, 10, 700, 700);

			token[2].setPosition(670+offset,643+offset);

			offset =offset+10;

			token[3] = new Token(Color.green);
			
			token[3].setBounds(10, 10, 700, 700);

			token[3].setPosition(670+offset,643+offset);

			offset =offset+10;

			token[4] = new Token(Color.yellow);
			
			token[4].setBounds(10, 10, 700, 700);

			token[4].setPosition(670+offset,643+offset);

			offset =offset+10;

			token[5] = new Token(Color.cyan);
			
			token[5].setBounds(10, 10, 700, 700);

			token[5].setPosition(670+offset,643+offset);

			break;
		default :
			System.out.println("Invalid number of players");
		}


		JLabel monopolyLabel = new JLabel(new ImageIcon("Monopoly Board.jpg"));
		monopolyLabel.setBounds(0, 0, 800, 750);

		Token car = new Token(Color.orange);
				
				car.setBounds(10, 10, 700, 700);
			   
				
				 car.setPosition(673,643);
		
		
		JLayeredPane lp = getLayeredPane();


		lp.add(monopolyLabel, new Integer(0));
		lp.add(token[0],new Integer(1));
		lp.add(token[1],new Integer(2));
		lp.add(token[2],new Integer(3));
		lp.add(token[3],new Integer(4));
		lp.add(token[4],new Integer(5));
		lp.add(token[5],new Integer(6));


		lp.add(car,new Integer(2));

		
		setSize(1500,1500);
		setTitle("Monopoly");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		int i;
		for(i=0;i<11;i++){
			car.setPosition(locations[i].x, locations[i].y);
			Thread.sleep(300);
			System.out.println(locations[i].x);
		}


	}
	
//	public void moveToken(Token T){
//		int i;
//		for(i=0;i<locations.length;i++){
//			T.setPosition(locations[0].x, locations[0].y);
//		}
//	}

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub


		monopolyBoardandTokens m = new monopolyBoardandTokens();
		
		
	}

}
