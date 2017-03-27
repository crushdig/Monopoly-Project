import java.awt.*;
import java.util.ArrayList;


import javax.swing.JOptionPane;
/*
 * This class controls everything that has to do with the gameplay
 */

public class Monopoly {
	private int players;
	private Token[]  token;
	private Player currPlayer;

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

	private static final String[] PROPERTY_SHORT_NAMES = {
			  "Old Kent Rd","Whitechapel Rd","The Angel Islington","Euston Rd","Pentonville Rd",
			  "Pall Mall","Whitehall","Northumberland Ave","Bow St","MarlboroughSt","Vine St",
			  "Strand","Fleet St","Trafalgar Sq","Leicester Sq","CoventrySt","Piccadilly",
			  "Regent St","Oxford St","Bond St","Park Lane","Mayfair","King's Cross Station",
			  "Marylebone Station",
			  "Fenchurch St Station",
			  "Liverpool St Station","Electric Co","Water Works"};


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

		token = new Token[this.players];

		String name;

		player = new ArrayList<Player>();

		for(int i=0;i<this.players;i++){
			name = JOptionPane.showInputDialog("Enter player " + i + "'s" + " name");
			player.add(new Player(name));
		}


		board = new Board();
		//board = new Board(player); testing


		ui= new UI(token);




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

	//
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


		while(!gameover){
			//j is the index of the person that starts
			int index_Of_Player_That_Starts=j;

			//Takes turns based on the player that starts
			switch(index_Of_Player_That_Starts){

			//Turns are taken as normal if person 0 starts
			case 0:
				for(i=j;i<player.size();i++){

					if(gameover) break;
					turn(player.get(i), i);
				}
				break;
				//If person 1 and above starts, turns are taken in ascending order then the person 0 takes their turn
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


		}

		ui.displayString("Game is over");

		return;
	}

	//Function contains all the actions a player can perform in their turn
	public void turn(Player person, int person_number) throws InterruptedException{
		String command="";
		boolean rolled = false;
		boolean paid = false;
		int diceroll = 0;

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
				diceroll = dice + dice1;

				rolled = true;
				ui.displayString("you rolled a " +(dice + dice1));
				moveToken((dice + dice1),person_number);
				movePerson((dice + dice1),person);
			}
		}while(!rolled);


		do{
			ui.displayString("Please enter a command");
			command = ui.getCommand();
			ui.displayString(command);


			if(command.equals("roll")) {
				ui.displayString("Stop trying to be sly, you can't roll twice in one turn");
			}

			//Ends the game if someone quits
			if(command.equals("quit")) {
				ui.displayString(person.getName() + " Quit :(");
				this.gameover = true;
				return;
			}

		
			if( (board.squareType(person.getPosition()) == 1) || (board.squareType(person.getPosition()) == 2) || (board.squareType(person.getPosition()) == 3) )
			{
				Property property = board.getProperty(person.getPosition());
				if(property.owned() && !property.getOwner().equals(person) && !paid){
					do{
						ui.displayString("You have to pay rent, please type 'pay rent' ");
						System.out.println("here");
						command = ui.getCommand();
						ui.displayString(command);
					}while(!command.equals("pay rent") );
				}
			}

			if(command.equals("pay rent")){
				if( (board.squareType(person.getPosition()) == 1) || (board.squareType(person.getPosition()) == 2) || (board.squareType(person.getPosition()) == 3) )
				{
					Property property = board.getProperty(person.getPosition());

					if (property.owned()) {
						if(!property.isMortgaged()){
							if (!property.getOwner().equals(person)) {
								if (!paid) {
																if (person.getbalance() >= property.getRent(property.getOwner())) {
									switch(board.squareType(person.getPosition())){
									case 1:
										board.getProperty(person.getPosition()).getOwner().getRent(person.payRent(board.getProperty(person.getPosition()).getRent(board.getProperty(person.getPosition()).getOwner())));
										ui.displayString(person.getName() + " paid rent to " + board.getProperty(person.getPosition()).getOwner().getName());
										paid = true;
										break;
									case 2:
										board.getProperty(person.getPosition()).getOwner().getRent(person.payRent(board.getProperty(person.getPosition()).getRent(board.getProperty(person.getPosition()).getOwner())));
										ui.displayString(person.getName() + " paid rent to " + board.getProperty(person.getPosition()).getOwner().getName());
										paid = true;
										break;
									case 3:
										board.getProperty(person.getPosition()).getOwner().getRent( person.payRent(diceroll * board.getProperty(person.getPosition()).getRent(board.getProperty(person.getPosition()).getOwner())));
										ui.displayString(person.getName() + " paid rent to " + board.getProperty(person.getPosition()).getOwner().getName());
										paid = true;
										break;
																		
									default:
										break;
									}	
																} else {
																	ui.displayError(UI.ERR_INSUFFICIENT_FUNDS);										
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

				} else {
					ui.displayError(UI.ERR_NOT_A_PROPERTY);
				}
			}

			if(command.equals("buy")){
				//Checks if you're on a property that can be bought
				if( (board.squareType(person.getPosition()) == 1) || (board.squareType(person.getPosition()) == 2) || (board.squareType(person.getPosition()) == 3) )
				{
					//Checks if the property isn't owned
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
			
			
			if(command.equalsIgnoreCase("Build House"))
            {
              ui.displayString("Enter the name of the property you want to build a house on");
              
              boolean found = false;
              
              String nameOfProperty = ui.getCommand();
              ui.displayString(nameOfProperty);
              
              int i = 0;
              int j;
              
              while(!found && i < PROPERTY_SHORT_NAMES.length)
              {
                if(nameOfProperty.equalsIgnoreCase(PROPERTY_SHORT_NAMES[i]))
                {
                  found = true;
                }
              }
              i++;
              
              if(found)
              {
                boolean ownerOfProperty = false;
                for(j = 0; j < person.assets.size() && !ownerOfProperty; j++)
                {
                  if(nameOfProperty.equalsIgnoreCase(person.assets.get(j).getName()))
                  {
                    ownerOfProperty = true;
                    break;
                  }
                }
                
                if(ownerOfProperty && person.assets.get(j).getType()==1)
                {
                  Site property = (Site) person.assets.get(j);
                  if(person.Monopoly(property.getColour()))
                  {
                    int numOfHousesToBuild = 0;
                    do{
                    	 ui.displayString("This property has " + property.getHouses() + " houses");
                      ui.displayString("Please specify the number of houses you want to build, you can't build more than 4");
                      String housesToBuild = ui.getCommand();
                      ui.displayString(housesToBuild);
                      numOfHousesToBuild = Integer.parseInt(housesToBuild);
                      property.buildHouse(numOfHousesToBuild, property.getOwner());
                    }while((numOfHousesToBuild < 1 || numOfHousesToBuild > 4) && property.getHouses()<4);
                    
                    
                    
                    ui.displayString("Your " + numOfHousesToBuild + " houses have been built.");
                  } else{
                	  ui.displayString("You don't have a monopoly on that color group");
                  }
                } else{
                	 ui.displayString("You can only build on a site and it has to be yours ");
                }
              } 
            }
            
            if(command.equalsIgnoreCase("Build Hotel"))
            {
              int i = 0;
              
              
                ui.displayString("Enter the name of the property you want to build a hotel on");
                
                boolean found = false;
                
                String nameOfProperty = ui.getCommand();
                ui.displayString(nameOfProperty);
                
                int j;
                
                while(!found && i < PROPERTY_SHORT_NAMES.length)
                {
                  if(nameOfProperty.equalsIgnoreCase(PROPERTY_SHORT_NAMES[i]))
                  {
                    found = true;
                  }
                }
                i++;
                
                if(found)
                {
                  boolean ownerOfProperty = false;
                  for(j = 0; j < person.assets.size() && !ownerOfProperty; j++)
                  {
                    if(nameOfProperty.equalsIgnoreCase(person.assets.get(j).getName()))
                    {
                      ownerOfProperty = true;
                      break;
                    }
                  }
                  
                  if(ownerOfProperty && person.assets.get(j).getType()==1)
                  {
                    Site property = (Site) person.assets.get(j);
                    if(person.Monopoly(property.getColour()))
                    {
                    	if(property.getHouses() == 4)
                        {
                      int numOfHotelsToBuild = 0;
                      do{
                    	  ui.displayString("This property has " + property.getHotels() + " hotels");
                          ui.displayString("Please specify the number of hotels you want to build, you can't build more than 2");
                        String housesToBuild = ui.getCommand();
                        ui.displayString(housesToBuild);
                        numOfHotelsToBuild = Integer.parseInt(housesToBuild);
                        property.buildHotel(numOfHotelsToBuild, property.getOwner());
                      }while((numOfHotelsToBuild < 1 || numOfHotelsToBuild > 2)  && property.getHotels()<2);
                      
                      
                      ui.displayString("Your " + numOfHotelsToBuild + " houses have been built.");
                        } else{
                        	ui.displayString("You need 4 houses before you can build a hotel");
                        }
                    }else{
                  	  ui.displayString("You don't have a monopoly on that color group");
                    }
                  }else{
                 	 ui.displayString("You can only build on a site and it has to be yours ");
                  }
                }
              
            }
			

			if(command.equals("mortgage")){

				ui.displayString("Enter the name of the property you want mortgage");
				boolean found = false;
				String name = ui.getCommand();
				ui.displayString(name);
				
				int i =0;
				int j;
				while(!found && i<PROPERTY_SHORT_NAMES.length){
					if(name.equalsIgnoreCase(PROPERTY_SHORT_NAMES[i])){
						found = true;
					}
					i++;
				}
				
				if(found){
					boolean own_property = false;
					for(j = 0; j<person.assets.size() && !own_property; j++){
						if(name.equalsIgnoreCase(person.assets.get(j).getName())){
							own_property = true;
							break;
						}
					}
					
					if(own_property){
						Property property = person.assets.get(j);
						person.mortgage(property);
						ui.displayString(property.getName() + " has been mortgaged");
					}
					
					else{
						ui.displayString("You don't own this property");
					}
				}
				
				
				else{
					ui.displayString("You didn't enter a property name, please type 'property' to see your property names");
				}
				
			}
			
			
			if(command.equals("demolish hotel")){
				ui.displayString("Enter the name of the property you want to demolish a hotel on");
	              
	              boolean found = false;
	              
	              String nameOfProperty = ui.getCommand();
	              ui.displayString(nameOfProperty);
	              
	              int i = 0;
	              int j;
	              
	              while(!found && i < PROPERTY_SHORT_NAMES.length)
	              {
	                if(nameOfProperty.equalsIgnoreCase(PROPERTY_SHORT_NAMES[i]))
	                {
	                  found = true;
	                }
	              }
	              i++;
	              
	              if(found)
	              {
	                boolean ownerOfProperty = false;
	                for(j = 0; j < person.assets.size() && !ownerOfProperty; j++)
	                {
	                  if(nameOfProperty.equalsIgnoreCase(person.assets.get(j).getName()))
	                  {
	                    ownerOfProperty = true;
	                    break;
	                  }
	                }
	                
	                if(ownerOfProperty && person.assets.get(j).getType()==1)
	                {
	                  Site property = (Site) person.assets.get(j);
	                  if(person.Monopoly(property.getColour()))
	                  {
	                    int numOfhotelsTodemolish = 0;
	                    if(property.getHotels()!=0){
	                    	
	                    do{
	                    	 ui.displayString("This property has " + property.getHotels() + " hotels");
	                      ui.displayString("Please specify the number of hotels you want to demolish, you can't demolish more than 2");
	                      String hotelsTodemolish = ui.getCommand();
	                      ui.displayString(hotelsTodemolish);
	                      numOfhotelsTodemolish = Integer.parseInt(hotelsTodemolish);
	                      property.demolishHouse(numOfhotelsTodemolish, property.getOwner());
	                    }while((numOfhotelsTodemolish < 1 || numOfhotelsTodemolish > 2) && property.getHotels()>0);
	                    
	                    
	                    
	                    ui.displayString("Your " + numOfhotelsTodemolish + " hotels have been demolished.");
	                    	
	                    } else{
	                    	ui.displayString("You have to no hotels");
	                    }
	                  } else{
	                	  ui.displayString("You don't have a monopoly on that color group");
	                  }
	                } else{
	                	 ui.displayString("You can only demolish on a site and it has to be yours ");
	                }
	              } 
			}
			
			
			if(command.equals("demolish house")){
				ui.displayString("Enter the name of the property you want to demolish a house on");
	              
	              boolean found = false;
	              
	              String nameOfProperty = ui.getCommand();
	              ui.displayString(nameOfProperty);
	              
	              int i = 0;
	              int j;
	              
	              while(!found && i < PROPERTY_SHORT_NAMES.length)
	              {
	                if(nameOfProperty.equalsIgnoreCase(PROPERTY_SHORT_NAMES[i]))
	                {
	                  found = true;
	                }
	              }
	              i++;
	              
	              if(found)
	              {
	                boolean ownerOfProperty = false;
	                for(j = 0; j < person.assets.size() && !ownerOfProperty; j++)
	                {
	                  if(nameOfProperty.equalsIgnoreCase(person.assets.get(j).getName()))
	                  {
	                    ownerOfProperty = true;
	                    break;
	                  }
	                }
	                
	                if(ownerOfProperty && person.assets.get(j).getType()==1)
	                {
	                  Site property = (Site) person.assets.get(j);
	                  if(person.Monopoly(property.getColour()))
	                  {
	                    int numOfHousesTodemolish = 0;
	                    if(property.getHotels()!=0){
	                    	if(property.getHouses()!=0){
	                    do{
	                    	 ui.displayString("This property has " + property.getHouses() + " houses");
	                      ui.displayString("Please specify the number of houses you want to demolish, you can't demolish more than 4");
	                      String housesTodemolish = ui.getCommand();
	                      ui.displayString(housesTodemolish);
	                      numOfHousesTodemolish = Integer.parseInt(housesTodemolish);
	                      property.demolishHouse(numOfHousesTodemolish, property.getOwner());
	                    }while((numOfHousesTodemolish < 1 || numOfHousesTodemolish > 4) && property.getHouses()>0);
	                    
	                    
	                    
	                    ui.displayString("Your " + numOfHousesTodemolish + " houses have been demolished.");
	                    	} else{
	                    		ui.displayString("You have no houses");
	                    	}
	                    } else{
	                    	ui.displayString("You have to demolish hotels first");
	                    }
	                  } else{
	                	  ui.displayString("You don't have a monopoly on that color group");
	                  }
	                } else{
	                	 ui.displayString("You can only demolish on a site and it has to be yours ");
	                }
	              } 
			}

			if(command.equals("redeem")){
				ui.displayString("Enter the name of the property you want redeem");
				boolean found = false;
				String name = ui.getCommand();
				ui.displayString(name);
				
				int i =0;
				int j;
				while(!found && i<PROPERTY_SHORT_NAMES.length){
					if(name.equalsIgnoreCase(PROPERTY_SHORT_NAMES[i])){
						found = true;
					}
					i++;
				}
				
				if(found){
					boolean own_property = false;
					for(j = 0; j<person.assets.size() && !own_property; j++){
						if(name.equalsIgnoreCase(person.assets.get(j).getName())){
							own_property = true;
							break;
						}
					}
					
					if(own_property){				
						Property property = person.assets.get(j);
						
						if(property.isMortgaged()){
						person.redeem(property);
						ui.displayString(property.getName() + " has been redeemed");
						}
						
						else{
							ui.displayString("This hasn't been mortgaged so you can't redeem it");
						}
											
					}
					
					else{
						ui.displayString("You don't own this property");
					}
				}
				
				
				else{
					ui.displayString("You didn't enter a property name, please type 'property' to see your property names");
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
			
			if(!paid){
				ui.displayString("You must declare  bankruptcy");
				
				do{
					ui.displayString("You must declare  bankruptcy, please type 'bankrupt' ");
					System.out.println("here");
					command = ui.getCommand();
					ui.displayString(command);
				}while(!command.equals("bankrupt") );
			}
			
			if(command.equals("bankrupt")){
				ui.displayString("You are out");

				for(int i =0; i<person.assets.size();i++){
					person.assets.get(i).free();
				}
				command = "quit";
			}

			//Displays message for invalid commands
			if(!(command.equals("redeem")) &&!(command.equals("mortgage")) &&!(command.equals("property")) && !(command.equals("balance")) && !(command.equals("help")) && !(command.equals("pay rent"))
					&& !(command.equals("quit")) && !(command.equals("done")) && !(command.equals("buy")) && !(command.equals("roll"))){
				ui.displayString("Invalid command, type 'help' for help");
			}




		}while((!command.equals("done") && !command.equals("quit")));
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
