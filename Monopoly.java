import java.awt.*;
import java.util.ArrayList;


import javax.swing.JOptionPane;
/*
 * This class controls everything that has to do with the gameplay
 */

public class Monopoly {
	private int players;

	private Player currPlayer;
	int diceroll = 0;

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
			+ " 'pay rent' to pay rent \n 'buy' to buy \n 'property' to show properties \n 'balance' to show balance \n "
			+ " 'done' to end turn \n  'quit' to finish early \n  'redeem' to redeem a property \n  'mortgage' to mortgage a property"
			+ "\n  'build' to build on a property \n"
			+ "\n  'demolish to demolish on a property \n"
			+ "\n 'bankrupt' to declare bankrupcy"
			+ "\n 'cheat' to use cheat for testing";


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

	


	//The Dice being used in the game
	private final Dice[] die = new Dice[2] ;


	private ArrayList<Player> player;//All the players
	private Board board;
	boolean gameover = false;

	//The constructor initializes the dice, players and board
	Monopoly () {

		for(int i=0;i<die.length;i++){
			die[i] = new Dice();
		}


		do{
			String players = JOptionPane.showInputDialog("Enter number of players between 2 and 6");

			this.players = Integer.parseInt(players);
		}while((this.players<2) || (this.players>6));

		

		String name;

		player = new ArrayList<Player>();

		for(int i=0;i<this.players;i++){
			name = JOptionPane.showInputDialog("Enter player " + i + "'s" + " name");
			player.add(new Player(name));
		}


		board = new Board();
		


		ui= new UI(player);




		return;


	}

	//Takes in the ArrayList of players and returns the person that goes first

	public Player decideStarter () {
		ArrayList<Player> inPlayers = player, 
				selectedPlayers = new ArrayList<Player>();
		String command="";
		int diceroll = 0;
		boolean tie = false;
		do {
			int highestTotal = 0;
			for (Player p : inPlayers) {


				ui.displayString(p.getName() + " Type roll");
				do{
					ui.displayString("Please type roll");
					command = ui.getCommand();
					ui.displayString(command);
					if(command.equals("roll")) {
						int dice = die[0].roll();
						int dice1 = die[1].roll();
						diceroll = dice + dice1;
						ui.displayString("You rolled " + diceroll);
					}
				}while(!command.equals("roll"));

				if (diceroll > highestTotal) {
					tie = false;
					highestTotal = diceroll;
					selectedPlayers.clear();
					selectedPlayers.add(p);
				} else if (diceroll == highestTotal) {
					tie = true;
					selectedPlayers.add(p);
				}
			}
			if (tie) {
				ui.displayString("Draw");
				inPlayers = new ArrayList<Player>(selectedPlayers);
				selectedPlayers.clear();
			}
		} while (tie);
		currPlayer = selectedPlayers.get(0);
		ui.displayString(currPlayer.getName() + " wins the roll.");
		return currPlayer;
	}

	
	
	private void gameplay() throws InterruptedException{


		ui.displayString("\n" + INSTRUCTIONS);
		int i=0,j;

		ui.displayString("Let's see who goes first");

		//roll to see who goes first
		Player temp = decideStarter ();
		for(j=0;j<player.size();j++){

			if(player.get(j) == temp){
				break;
			}
		}	

		do {
			turn(temp,i);
			if (!gameover) {
				temp = getNextPlayer(temp);
				i++;
			}
			if(i>=player.size()){
				i %= player.size();
			}
			
		} while (!gameover);
		
		//Prints the winner if the game is over
		if(gameover){
			double max_assets = 0;
			int playerIndex = 0;	
			for(i=0;i<player.size();i++){
				ui.displayString(player.get(i).getName() + " is worth " + player.get(i).getWorth());
				if(player.get(i).getWorth()>max_assets){
					max_assets = player.get(i).getWorth();
					playerIndex = i;
				}

			}
			ui.displayString(player.get(playerIndex).getName() + " Wins! :)");
		}

			
		ui.displayGameOver();

		return;
	}
	
	private void showProperties(){
		for (Square s :board.getSquares()) {
			if (s instanceof Property) {
				Property p = (Property) s;
				ui.displayString(p.toString());
			}
		}
	}
	
	private Property getInputProperty(){
		
		String nameOfProperty, command;
		
        do{
        	ui.displayString("Enter the name of the property you want to process");
        	nameOfProperty = ui.getCommand();
        	if(!board.isProperty(nameOfProperty)){
        		ui.displayString("This is not a property, please enter a property, type 'properties' for the list of properties");
        		command = ui.getCommand();
        		if(command.equals("properties")){
        			showProperties();
        		}
        	}
        	
        }while(!board.isProperty(nameOfProperty));
        
        
        ui.displayString(nameOfProperty);
        
        
		return board.getProperty(nameOfProperty);
	}
	
	private int getInputNumber(){
		
		int numOfHousesToBuild;
		ui.displayString("Please specify the number of houses you want to process, you can't process more than 5");
        String housesToBuild = ui.getCommand();
        ui.displayString(housesToBuild);
        numOfHousesToBuild = Integer.parseInt(housesToBuild);
        
        
		return numOfHousesToBuild;
	}
	
	public Player getNextPlayer (Player p) {
		Player nextPlayer;
		int i=0;
		for (i=0;i<player.size();i++) {
			if(player.get(i) == p){
				break;
			}
		}
		nextPlayer = player.get(((i+1)%player.size()));
		return nextPlayer;
	}
	
	
	public void processBankrupt (Player p) {
		ui.displayBankrupt(p);
		Player tempPlayer = getNextPlayer(p);
		
		p.getToken().transparent();
		player.remove(p);
		
		
		p = tempPlayer;
		
		if (player.size()==1) {
			gameover = true;
		}
		
		return;
	}
	
	public void processCheat (Player p) {
		String command = "";
		int cheat = 0;
		do{
		ui.displayString("Which cheat do you want? Type 1 for the property cheat or 2 for the bankrupt cheat");
		
		command = ui.getCommand();
		ui.displayString(command);
		

		}while(!command.equalsIgnoreCase("1") && !command.equalsIgnoreCase("2") );
		
		cheat = Integer.parseInt(command);
		
		switch (cheat) {
			case 1 :       // acquire colour group
				Property property = board.getProperty("Old Kent Rd");
				p.addProperty(property);		
				property = board.getProperty("Whitechapel Rd");
				p.addProperty(property);
				break;
			case 2 :	   // make zero balance
				p.payRent((int)p.getbalance()*2);
				break;
				
		}
		return;
	}
	
	
	private void processBuild (Player p) {
		Property property = getInputProperty();
		if (property.owned() && property.getOwner().equals(p)) {
			if (property instanceof Site) {
				Site site = (Site) property;
				if (p.Monopoly(site)) {
					if (!site.isMortgaged()) {
						int numBuildings = getInputNumber();
						if (numBuildings>0) {
							if (site.canBuild(numBuildings)) {
								int debit = numBuildings*site.getHousePrice();
								if ((int)p.getbalance()>debit) {
									site.build(numBuildings);
									p.payRent(debit);
									ui.displayBuild(p,site,numBuildings);
								} else {
									ui.displayError(UI.ERR_INSUFFICIENT_FUNDS);
								}
							} else {
								ui.displayError(UI.ERR_TOO_MANY_BUILDINGS);
							}
						} else {
							ui.displayError(UI.ERR_TOO_FEW_BUILDINGS);
						}
					} else {
						ui.displayError(UI.SITE_IS_MORTGAGED);
					}
				} else {
					ui.displayError(UI.ERR_DOES_NOT_HAVE_GROUP);
				}
			} else {
				ui.displayError(UI.ERR_NOT_A_SITE);
			}
		} else {
			ui.displayError(UI.ERR_NOT_YOURS);
		}
		return;
	}
	
	private void processDemolish (Player p) {
		Property property = getInputProperty();
		if (property.owned() && property.getOwner().equals(p)) {
			if (property instanceof Site) {
				Site site = (Site) property;
				int numBuildings = getInputNumber();
				if (numBuildings>0) {
					if (site.canDemolish(numBuildings)) {
						site.demolish(numBuildings);
						int credit = numBuildings * site.getHousePrice()/2;
						p.getRent(credit);
						ui.displayDemolish(p,site,numBuildings);
					} else {
						ui.displayError(UI.ERR_TOO_MANY_BUILDINGS);
					}
				} else {
					ui.displayError(UI.ERR_TOO_FEW_BUILDINGS);
				}
			} else {
				ui.displayError(UI.ERR_NOT_A_SITE);
			}
		} else {
			ui.displayError(UI.ERR_NOT_YOURS);
		}
		return;		
	}
	
	public void processMortgage(Player p) {
		Property property = getInputProperty();
		if (property.owned() && property.getOwner().equals(p)) {
			if ((property instanceof Site) && !((Site) property).hasBuildings() || (property instanceof Station) || (property instanceof Utility)) {
				if (!property.isMortgaged()) {
					property.mortgage();
					p.getRent(property.getMortgageValue());
					ui.displayMortgage(p,property);
				} else {
					ui.displayError(UI.ERR_IS_MORTGAGED);
				}
			} else {
				ui.displayError(UI.ERR_HAS_BUILDINGS);
			}
		} else {
			ui.displayError(UI.ERR_NOT_YOURS);
		}
		return;		
	}

	public void processRedeem (Player p) {
		Property property = getInputProperty();
		if (property.owned() && property.getOwner().equals(p)) {
			if (property.isMortgaged()) {
				int price = property.getMortgageRemptionPrice();
				if ((int)p.getbalance() >= price) {
					property.setNotMortgaged();
					p.payRent(price);
					ui.displayMortgageRedemption(p,property);
				} else {
					ui.displayError(UI.ERR_INSUFFICIENT_FUNDS);
				}
			} else {
				ui.displayError(UI.ERR_IS_NOT_MORTGAGED);
			}
		} else {
			ui.displayError(UI.ERR_NOT_YOURS);
		}
		return;			
	}
	
	public void processRent(Player p, boolean paid){
		
		Player person = p;
		
		Property property = board.getProperty(person.getPosition());
		if( property instanceof Property )
		{
			if (property.owned()) {
				if(!property.isMortgaged()){
					if (!property.getOwner().equals(person)) {
						if (!paid) {
							
							switch(board.squareType(person.getPosition())){
							case 1:
								board.getProperty(person.getPosition()).getOwner().getRent(person.payRent(board.getProperty(person.getPosition()).getRent()));
								ui.displayString(person.getName() + " paid rent to " + board.getProperty(person.getPosition()).getOwner().getName());
								paid = true;
								break;
							case 2:
								board.getProperty(person.getPosition()).getOwner().getRent(person.payRent(board.getProperty(person.getPosition()).getRent()));
								ui.displayString(person.getName() + " paid rent to " + board.getProperty(person.getPosition()).getOwner().getName());
								paid = true;
								break;
							case 3:
								board.getProperty(person.getPosition()).getOwner().getRent( person.payRent(diceroll * board.getProperty(person.getPosition()).getRent()));
								ui.displayString(person.getName() + " paid rent to " + board.getProperty(person.getPosition()).getOwner().getName());
								paid = true;
								break;
																
							default:
								break;
							}	
														 
						} else {
							ui.displayError(UI.ERR_RENT_ALREADY_PAID);									
						}
					} else {
						ui.displayError(UI.ERR_SELF_OWNED);								
					}
				}	else{
					ui.displayString("This property has been mortgaged");	
				}
			} else {
				ui.displayError(UI.ERR_NOT_OWNED);							
			}
		}	else {
			ui.displayError(UI.ERR_NOT_A_PROPERTY);
		}
		
		
	}
	
	public void processJail(Player person, int jailCount) throws InterruptedException{
	    String command,command1;
	    
	    if(jailCount==3){
	      ui.displayString("You have been in jail for 3 turns and have to pay");
	      processJailPay(person);
	    }
	    
	    else{
	    ui.displayString("Do you wish to get out of jail?");
	   
	    command = ui.getCommand();
	    ui.displayString(command);

	    
	    	
	      if ((command.equalsIgnoreCase("Yes"))) {
	        ui.displayString("How would you like to leave jail?");
	        ui.displayString("You can only use the commands 'pay bail' to pay bail, 'roll' to try for a double and 'card' to use your jailcard");
	        command1 = ui.getCommand();
	        ui.displayString(command1);

	        if (command1.equalsIgnoreCase("Pay bail")  && (!person.isBankrupt())) {
	          processJailPay(person);
	        }

	       if (command1.equalsIgnoreCase("Roll")) {
	          processJailRoll(person);
	        }
	       
	       if (command1.equalsIgnoreCase("card")) {
	         processGetOutOfJailCard(person);
	       }
	       
	       if (!(command1.equalsIgnoreCase("card")) && !(command1.equalsIgnoreCase("pay bail"))
	           && !(command1.equals("roll"))) {
	         ui.displayString("Invalid command");
	       }
	       
	      }
	      
	      if ((command.equalsIgnoreCase("no"))) return;
	   

	    }
	    
	  }
	  
	  public void processJailPay(Player p) throws InterruptedException{
	    ui.displayString("You paid: " + p.payBail(50) + " to get out of jail");
	    processRoll(p);
	  }
	  
	  public void processJailRoll(Player p) throws InterruptedException{
	    int dice = die[0].roll();
	    int dice1 = die[1].roll();

	  
	    ui.displayString("you rolled a " + (dice + dice1));

	    if (dice == dice1) {
	      p.setJail(false);
	      ui.displayString("Great! You rolled doubles! Feel free to leave");
	      moveToken((dice + dice1), p);
	      movePerson((dice + dice1), p);
	    }
	    
	    else{
	      ui.displayString("Sorry you're still in jail as you didn't roll doubles");
	    }

	  }
	  
	  public void processGetOutOfJailCard(Player p){
	    if(p.hasJailCard()){
	      p.useJailCard();
	      ui.displayString("You've used your card to get out of jail");
	    }
	    else{
	      ui.displayString("You have no get out of jail card");
	    }
	  }
	  
	  public boolean processRoll(Player person) throws InterruptedException{
	    int dice = die[0].roll();
	    int dice1 = die[1].roll();

	    
	    diceroll = dice + dice1;
	   
	    
	    ui.displayString("You rolled " + dice + " and " + dice1 + " total being: " + (dice + dice1));
	    moveToken(diceroll, person);
	    movePerson(diceroll, person);
	    return dice==dice1;
	  }
	
	public void processChanceOrFine(Player p){
		String command = "";
		Chance chance = new Chance("Chance", 6);
		do{
		ui.displayString("You can either collect a chance card or pay a fine, type 'chance' for chance or 'fine' to pay �10 fine" );
		
		command = ui.getCommand();
		ui.displayString(command);
		}while(!command.equalsIgnoreCase("chance") && !command.equalsIgnoreCase("fine"));
		
		if(command.equalsIgnoreCase("fine")) ui.displayString("You paid: " + p.payRent(10));
		
		else chance.processCard(p, this, chance.drawCard());
	}
	
	 public void processTax(Player p, boolean paid)
	  {
	    Player person = p;
	    
	    Tax payTax = board.getTax(person.getPosition());
	    ui.displayString(person.getName() + " has just paid "
	        + board.getTax(person.getPosition()).getName() + " of " +  person.payRent(payTax.getTaxAmount()));
	    paid = true;
	  }
	
	public void processBuy(Player p){
		Player person = p;
		//Checks if you're on a property that can be bought
		Property property = board.getProperty(person.getPosition());
		if( property instanceof Property ){
			//Checks if the property isn't owned
			if(!property.owned()){
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
	
	public String balanceCheck(Player person){
		String command = "";
		do{
			ui.displayString("You are bankrupt, please sell houses by typing 'demolish' if you have no "
					+ "buildings to demolish type 'mortgage' to mortgage, type 'property' to see your properties and their status");
			command = ui.getCommand();
			ui.displayString(command);
			
			if(command.equals("mortgage")){

				processMortgage(person);
			}
			
			if(command.equals("demolish")){
				processDemolish (person);
			}
			
			if(command.equals("property")){
				ui.displayAssets(person);
			}
			
			if(person.allMortgaged() && person.isBankrupt()){
				command = "bankrupt";
			}
			
		}while(!command.equalsIgnoreCase("bankrupt"));
		return command;
	}
	
	//Function contains all the actions a player can perform in their turn
	public void turn(Player person, int person_number) throws InterruptedException{
		String command="";
		boolean rolled = false;
		boolean paid = false;
		
		
		
		ui.displayString(person.getName() + "'s turn");
		
		
		if(!person.getJail()){
		do{
			ui.displayString("Please roll");
			command = ui.getCommand();
			ui.displayString(command);

			if(command.equals("roll")) {
				if(processRoll(person)) person.increaseRollCount();
				rolled = true;
			}
		}while(!rolled);
		}
		

		do{
			
			if(person.isBankrupt()){
				balanceCheck(person);				
			}
			
			
			else{
				
				if(person.getRollCount()==3){
					
					ui.displayString(" You rolled doubles 3 times go to jail :) ");
					 person.getToken().setPosition(60, 643);
			          person.move(10);
			          person.setJail(true);
					
				}
				
				if (board.squareType(person.getPosition()) == 8) {

			          person.getToken().setPosition(60, 643);
			          person.move(10);

			          person.setJail(true);

			          }
			        
			        if ((board.squareType(person.getPosition()) == 6) && (person.getJail())) {
			          ui.displayString(person.getName() + ", you have been placed in jail.");
			        }
				
				if(board.squareType(person.getPosition())==5){
					Chance chance = (Chance) board.getSquare(person.getPosition());
					//chance.advanceToLocation(person, person_number, "Advance to Go.", this);
					chance.processCard(person, this, chance.drawCard());
					
				}
				
				if(board.squareType(person.getPosition())==4){
					CommunityChest community = (CommunityChest) board.getSquare(person.getPosition());
					//chance.advanceToLocation(person, person_number, "Advance to Go.", this);
					community.processCard(person, this, community.drawCard());
					
				}
				
				//Enforces payment of Tax
			      if(board.squareType(person.getPosition()) == 9)
			      {
			        ui.displayString("You need to pay tax.");
			        
			        processTax(person, paid);
			        
			        paid = true;
			      }
				
			      if(person.getJail()){
			          person.increaseJailCount();
			          processJail(person, person.getJailCount());
			          command = "";
			        }
			        
			        else{

			        ui.displayString("Please enter a command");
			        command = ui.getCommand();
			        ui.displayString(command);
			        }
			}

			if(command.equals("roll")) {
				ui.displayString("Stop trying to be sly, you can't roll twice in one turn");
			}

			//Ends the game if someone quits
			if(command.equals("quit")) {
				ui.displayString(person.getName() + " Quit :(");
				this.gameover = true;
				return;
			}

		//Enforces payment of rent
			
			if(board.isProperty(person.getPosition()))
			{
				Property property = board.getProperty(person.getPosition());
				if(property.owned() && !property.getOwner().equals(person) && !paid){
					
						ui.displayString("You have to pay rent");
						processRent(person, paid);
						paid = true;
					
				}
			}

			


			if(command.equals("buy")){
				processBuy(person);
			}
			
			//This get the name of the property from the user, checks if such a property exists, checks if the current player
			//is the owner then lets them build
			if(command.equalsIgnoreCase("Build"))
            {
				processBuild(person);
            }
            
			
          //This get the name of the property from the user, checks if such a property exists, checks if the current player
			//is the owner then lets them mortgage
			if(command.equals("mortgage")){

				processMortgage(person);
			}
			
			
			
			//This get the name of the property from the user, checks if such a property exists, checks if the current player
			//is the owner then lets them demolish
			if(command.equals("demolish")){
				processDemolish (person);
			}

			//This get the name of the property from the user, checks if such a property exists, checks if the current player
			//is the owner then lets them redeem
			if(command.equals("redeem")){
				processRedeem(person);
			}

			if(command.equals("help")){
				ui.displayString(INSTRUCTIONS);
			}

			if(command.equals("balance")){
				ui.displayString(person.getBalance());
			}

			if(command.equals("property")){
				ui.displayAssets(person);
			}
	
			
			
			if(command.equals("bankrupt")){
				processBankrupt(person);
				command = "done";
			}
			
			if(command.equals("cheat")){
				processCheat(person);
				
			}
			
			if(person.getJail()){
		        command = "done";
		      }
			
			if(command.equals("done")){
				if(person.isBankrupt()){
					command = balanceCheck(person);
				}
			}

			//Displays message for invalid commands
			if(!(command.equalsIgnoreCase("cheat"))&&!(command.equalsIgnoreCase("demolish")) 
					 &&!(command.equalsIgnoreCase("build")) 
					&&!(command.equals("bankrupt")) &&!(command.equals("redeem")) &&!(command.equals("mortgage")) 
					&&!(command.equals("property")) && !(command.equals("balance")) 
					&& !(command.equals("help")) && !(command.equals("pay rent"))
					&& !(command.equals("quit")) && !(command.equals("done")) && !(command.equals("buy")) && !(command.equals("roll"))){
				ui.displayString("Invalid command, type 'help' for help");
			}

			
		}while((!command.equals("done") && !command.equals("quit")) &&!gameover);
	}

	public Board getBoard(){
		return board;
	}
	
	public UI getUI(){
		return ui;
	}

	public void moveToken(int m, Player p) throws InterruptedException {

		int  j, offset, position=0;
		offset = 0;

		//Find position of token
		for(int i =0;i<locations.length;i++){
			if((locations[i].x== p.getToken().getX()) && (locations[i].y== p.getToken().getY())){
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

				p.getToken().setPosition(locations[position].x, locations[position].y);

				position++;

				ui.getBoardPanel().repaint();

				// controls the movement speed of the tokens across the board allowing for easy detection of
				// their movement
				Thread.sleep(100);

			}
		}


		else
		{
			for (j = 0; j < m; j++) {
		
				position++;

				if((position)>=locations.length){
					position = position%locations.length;

				}

				p.getToken().setPosition(locations[position].x, locations[position].y);
				
				

				ui.getBoardPanel().repaint();

				// controls the movement speed of the tokens across the board allowing for easy detection of
				// their movement
				Thread.sleep(100);

			}
		}


		offset = offset + 15;
		ui.getBoardPanel().repaint();

	}
	
	public void collectFromAllPlayers(Player luckyPerson){
		int amount=0;
		for (Player p : player) {
			if(!p.equals(luckyPerson))
			amount += p.payRent(10);
		}
		
		ui.displayString(luckyPerson.getName() +  " got " + luckyPerson.getRent(amount) + " from everyone");
	}
	
	
	public void moveTokenBackwardByThree(Player p){
		int  position=0;
		

		//Find position of token
		for(int i =0;i<locations.length;i++){
			if((locations[i].x== p.getToken().getX()) && (locations[i].y== p.getToken().getY())){
				position = i;
			}
		}
		
		p.getToken().setPosition(locations[position-3].x, locations[position-3].y);
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
			//Give 200 if you pass GO
			if(board.squareType(person.getPosition()) == 0){
				ui.displayString("You passed GO! Collect 200!");
				person.getRent(200);
			}

		}
		ui.displayString(board.squareInfo(person.getPosition()));
	}

	//Launches the game
	public static void main (String args[]) throws InterruptedException {	
		Monopoly game = new Monopoly();		


		game.gameplay();

		return;
	}
}
