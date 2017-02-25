import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JOptionPane;


public class Monopoly {
	private int players;
	private Token[]  token;

	private UI ui;
	// Array of coordinates for the position of each square on the board
	Point[] locations = {new Point(570, 643), new Point(510, 643), new Point(450, 643),
			new Point(390, 643), new Point(330, 643), new Point(270, 643), new Point(210, 643),
			new Point(150, 643), new Point(95, 643), new Point(60, 643), new Point(60, 573),
			new Point(60, 503), new Point(60, 433), new Point(60, 383), new Point(60, 323),
			new Point(60, 273), new Point(60, 213), new Point(60, 153), new Point(60, 93),
			new Point(60, 33), new Point(120, 13),

			new Point(180, 13), new Point(230, 13), new Point(280, 13), new Point(340, 13),
			new Point(400, 13), new Point(460, 13), new Point(520, 13), new Point(580, 13),
			new Point(660, 60), new Point(660, 120), new Point(660, 160), new Point(660, 220),
			new Point(660, 280), new Point(660, 340), new Point(660, 400), new Point(660, 460),
			new Point(660, 520), new Point(660, 580), new Point(660, 640)};
	// The default position or starting point which is go
	Point defaultPosition = new Point(600, 603);


	//Square types
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

	private static final String INSTRUCTIONS= "The commands are: 'roll' to roll \n 'help' for instructions \n "
			+ "'pay rent' to pay rent \n 'buy' to buy \n 'property' to show properties \n 'balance' to show balance \n "
			+ " 'done' to end turn \n  'quit' to finish early";
	
	
	//Array of Square types

	private static final int[] SQUARE_TYPES = {
			TYPE_GO, TYPE_SITE, TYPE_COMMUNITY, TYPE_SITE, TYPE_TAX, TYPE_STATION,
			TYPE_SITE, TYPE_CHANCE, TYPE_SITE, TYPE_SITE,
			TYPE_JAIL, TYPE_SITE, TYPE_UTILITY, TYPE_SITE, TYPE_SITE, TYPE_STATION,
			TYPE_SITE, TYPE_COMMUNITY, TYPE_SITE, TYPE_SITE,
			TYPE_PARKING, TYPE_SITE, TYPE_CHANCE, TYPE_SITE, TYPE_SITE,
			TYPE_STATION, TYPE_SITE, TYPE_SITE, TYPE_UTILITY, TYPE_SITE,
			TYPE_GOTO_JAIL, TYPE_SITE, TYPE_SITE, TYPE_COMMUNITY, TYPE_SITE,
			TYPE_STATION, TYPE_CHANCE, TYPE_SITE, TYPE_TAX, TYPE_SITE };

	private final Dice[] die = new Dice[2] ;
	
	
	private ArrayList<Player> player;
	private Board board;
	boolean gameover = false;


	Monopoly () {
		
		for(int i=0;i<die.length;i++){
			die[i] = new Dice();
		}
		

		do{
			String players = JOptionPane.showInputDialog("Enter number of players between 2 and 6");

			this.players = Integer.parseInt(players);
		}while((this.players<2) || (this.players>6));

		token = new Token[this.players];

		String name;

		player = new ArrayList<Player>();

		for(int i=0;i<this.players;i++){
			name = JOptionPane.showInputDialog("Enter player name");
			player.add(new Player(name));
		}

		//		for (int i=0; i<player.size() ; i++) {
		//
		//			System.out.println(player.get(i).getName() +" has " + player.get(i).getPropertiesAndBalance());
		//
		//		}

		board = new Board(player);


		ui= new UI(token);




		return;


	}

	
	public Player whoGoesFirst(ArrayList<Player> person){
		
		String command="";
	
		int[] diceroll = new int[person.size()];
		
		//lets everyone roll
		for(int j=0;j<person.size();j++){
			ui.displayString(player.get(j).getName() + " Type roll");
			do{
				ui.displayString("Please type roll");
				command = ui.getCommand();
				ui.displayString(command);
			if(command.equals("roll")) {
				int dice = die[0].roll();
				int dice1 = die[1].roll();
				diceroll[j] = dice + dice1;
				ui.displayString("You rolled " + diceroll[j]);
			}
			}while(!command.equals("roll"));
		}
		
		//Finds the highest roll
		int max = diceroll[0];
		int playerIndex = 0;
		for(int k = 1;k<diceroll.length;k++){
			if(diceroll[k]>max){
				max = diceroll[k];
				playerIndex = k;
			}
			
		}
		
		ui.displayString(person.get(playerIndex).getName() + " goes first :)");
		
		return person.get(playerIndex);
	}

	private void gameplay() throws InterruptedException{

		
		ui.displayString("These are the commands \n" + INSTRUCTIONS);
		int i=0,j;
		
		ui.displayString("Let's see who goes first");
		
		//roll to see who goes first
		Player temp = whoGoesFirst(player);
		for(j=0;j<player.size();j++){
			
			if(player.get(j) == temp){
				break;
			}
		}	
		
		while(!gameover){
			//j is the index of the person that starts
			i=j;
			
			//Takes turns based on the player that starts
			switch(i){
			
			case 0:
				for(i=j;i<player.size();i++){
					
					if(gameover) break;
					turn(player.get(i), i);
				}
				break;
				
			case 1:
				for(i=j;i<player.size();i++){
					if(gameover) break;
					turn(player.get(i), i);
					
				}
				for(int k=0;k<i;k++){
					if(gameover) break;
					turn(player.get(k), k);
				}
				break;
			
			case 2:
				for(i=j;i<player.size();i++){
					if(gameover) break;
					turn(player.get(i), i);
				}
				for(int k=0;k<i;k++){
					if(gameover) break;
					turn(player.get(k), k);
				}
				break;
			case 3:
				for(i=j;i<player.size();i++){
					if(gameover) break;
					turn(player.get(i), i);
				}
				for(int k=0;k<i;k++){
					if(gameover) break;
					turn(player.get(k), k);
				}
				break;
			
			case 4:
				for(i=j;i<player.size();i++){
					if(gameover) break;
					turn(player.get(i), i);
				}
				for(int k=0;k<i;k++){
					if(gameover) break;
					turn(player.get(k), k);
				}
				break;
			
			case 5:
				for(i=j;i<player.size();i++){
					if(gameover) break;
					turn(player.get(i), i);
				}
				for(int k=0;k<i;k++){
					if(gameover) break;
					turn(player.get(k), k);
				}
				break;				
			
			
			}
//			for(i=j;i<player.size();i++){
//				
//				if(gameover) break;
//				turn(player.get(i), i);
//			}
			if(gameover){
			int max_assets = 0, playerIndex = 0;	
				for(i=0;i<player.size();i++){
					ui.displayString(player.get(i).getName() + " is worth " + player.get(i).getWorth());
					if(player.get(i).getWorth()>max_assets){
						max_assets = player.get(i).getWorth();
						playerIndex = i;
					}
			
				}
				
				ui.displayString(player.get(playerIndex).getName() + " Wins");
				
			}
			
			
		}
		
		ui.displayString("Game is over");
		
		return;
	}

	public void turn(Player person, int person_number) throws InterruptedException{
		String command="";
		boolean rolled = false;
		ui.displayString(person.getName() + "'s turn");
		
		
		do{
			ui.displayString("Please roll");
			command = ui.getCommand();
			ui.displayString(command);
			
			if(command.equals("roll")) {
				int dice = die[0].roll();
				int dice1 = die[1].roll();
				
				//Roll again if you roll doubles
				if(dice == dice1){
					do{
						ui.displayString("You rolled doubles! Please roll again");
						command = ui.getCommand();
						if(command.equals("roll")) {
							dice = die[0].roll();
							dice1 = die[1].roll();
						}
					}while(dice == dice1);
				}
		
				rolled = true;
				ui.displayString("you rolled a " +(dice + dice1));
				moveToken((dice + dice1),person_number);
				movePerson((dice + dice1),person);
			}
		}while(!rolled);
		
		boolean paid = false;
		do{
			command = ui.getCommand();
			ui.displayString(command);
			
			
			if(command.equals("roll")) {
				ui.displayString("Stop trying to be sly, you can't roll twice in one turn");
			}
			//Give 200 if you pass GO
			if(board.squareType(person.getPosition()) == 0){
				ui.displayString("You passed GO! Collect 200!");
				person.getRent(200);
			}
			
			if(command.equals("quit")) {
				ui.displayString(person.getName() + " Quit :(");
				this.gameover = true;
				return;
			}
			
			//Fix rent
			if( (board.squareType(person.getPosition()) == 1) || (board.squareType(person.getPosition()) == 2) || (board.squareType(person.getPosition()) == 3) )
			{
				
				//Checks if the property is owned and the owner isn't this person
				if(board.getProperty(person.getPosition()).owned()  && (board.getProperty(person.getPosition()).getOwner() != person)){
					
					
					do{
					ui.displayString("You have to pay rent, please type pay rent\n");
					
					if(command.equals("pay rent")){
						//Get the owner and pay the rent to the owner
						board.getProperty(person.getPosition()).getOwner().getRent(person.payRent(board.getProperty(person.getPosition()).getRent()));
						
						ui.displayString(person.getName() + " paid rent to " + board.getProperty(person.getPosition()).getOwner().getName());
					}
					
					paid = true;
					}while(!paid);
					
				
				}
			}
			
			if(command.equals("buy")){
				if( (board.squareType(person.getPosition()) == 1) || (board.squareType(person.getPosition()) == 2) || (board.squareType(person.getPosition()) == 3) )
				{
					
					if(!board.getProperty(person.getPosition()).owned()){
						person.buy(board.getProperty(person.getPosition()));
						ui.displayString("You bought " + board.getProperty(person.getPosition()).getName());
					}
					
					else{
						ui.displayString("This is owned");
					}
				}
				
				else{
					
					ui.displayString("This cannot be bought");
					
				}
			}
			
			if(command.equals("help")){
				ui.displayString(INSTRUCTIONS);
			}
			
			if(command.equals("balance")){
				ui.displayString(person.getBalance());
			}
			
			if(command.equals("property")){
				ui.displayString(person.getProperties());
			}
			
			if(!(command.equals("property")) && !(command.equals("balance")) && !(command.equals("help")) && !(command.equals("pay rent"))
					&& !(command.equals("quit")) && !(command.equals("done")) && !(command.equals("buy")) && !(command.equals("roll"))){
				ui.displayString("Invalid command, type 'help' for help");
			}
			
			
		}while(!command.equals("done") && !command.equals("quit"));
	}
	
	
	public int roll(){

		Random randomGenerator = new Random();
		int randomInt = randomGenerator.nextInt(6 - 1 + 1) + 1;


		return randomInt;
	}


	private void moveToken(int m, int person) throws InterruptedException {

		int  j, offset, position=0;
		offset = 0;

		//Find position of token
		for(int i =0;i<locations.length;i++){
			if((locations[i].x== token[person].getX()) && (locations[i].y== token[person].getY())){
				position = i;
			}
		}

		//Move token according to that position

		if(position == 0)
		{
			for (j = 0; j < m; j++) {

				if((position)>=locations.length){
					position = position%locations.length;
				}
				
				token[person].setPosition(locations[position].x, locations[position].y);

				position++;

				ui.getBoardPanel().repaint();

				// controls the movement speed of the tokens across the board allowing for easy detection of
				// their movement
				Thread.sleep(300);

			}
		}


		else
		{
			for (j = 0; j < m; j++) {

				position++;

				if((position)>=locations.length){
					position = position%locations.length;
					
				}

				token[person].setPosition(locations[position].x, locations[position].y);

				ui.getBoardPanel().repaint();

				// controls the movement speed of the tokens across the board allowing for easy detection of
				// their movement
				Thread.sleep(300);

			}
		}

		
		offset = offset + 15;
		ui.getBoardPanel().repaint();

	}

	
	
	public void movePerson(int m, Player person){
		int j;
		int position =0;		
		
		
		//Find current position of person
		for(int i=0;i<SQUARE_TYPES.length;i++){
			if( person.getPosition() == i){
				position=i;
			}
		}
		
		//Move player to required position
		for (j = 0; j < m; j++) {
			position++;
				if((position)>=SQUARE_TYPES.length){
					position = position%SQUARE_TYPES.length;
			}
				
				person.move(position);
		
		}
		ui.displayString(board.squareInfo(person.getPosition()));
	}


	public static void main (String args[]) throws InterruptedException {	
		Monopoly game = new Monopoly();		


		game.gameplay();

		return;
	}
}
