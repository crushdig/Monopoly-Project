
import java.awt.*;
import javax.swing.*;

public class monopolyBoardandTokens{

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
	
	
	
	
	
	
	private static JLabel monopolyLabel;
	
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
	     
		
		Tokens car = new Tokens(6);
		
		monopolyLabel = new JLabel(new ImageIcon("Monopoly Board.jpg"));

		JButton button = new JButton("Add Interest");
		button.setSize(10,10);
		JLabel label = new JLabel("balance: ");
		
		
		
		JPanel panel = new JPanel();
		
		panel.setLayout(new BorderLayout());
		panel.add(monopolyLabel, BorderLayout.CENTER);
		//panel.add(label, BorderLayout.WEST);
		//panel.add(button, BorderLayout.NORTH);
		
		
		
		JFrame gameFrame = new JFrame();
		gameFrame.setSize(600,600);
		//gameFrame.getContentPane().add(monopolyLabel);
		gameFrame.add(panel);
		gameFrame.add(car);
		gameFrame.setTitle("Monopoly");
		gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		gameFrame.setVisible(true);
	}

}
