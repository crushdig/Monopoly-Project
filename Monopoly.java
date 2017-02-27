import java.awt.Point;
import java.util.ArrayList;

import javax.swing.JOptionPane;
/*
 * This class controls everything that has to do with the game play
 */


public class Monopoly {
  private int players;
  private Token[] token;

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


  // Square types
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

  private static final String INSTRUCTIONS = " 'roll' to roll \n 'help' for instructions \n "
      + "'pay rent' to pay rent \n 'buy' to buy \n 'property' to show properties \n 'balance' to show balance \n "
      + "'done' to end turn \n 'quit' to finish early and end the game";


  // Array of Square types

  private static final int[] SQUARE_TYPES = {TYPE_GO, TYPE_SITE, TYPE_COMMUNITY, TYPE_SITE,
      TYPE_TAX, TYPE_STATION, TYPE_SITE, TYPE_CHANCE, TYPE_SITE, TYPE_SITE, TYPE_JAIL, TYPE_SITE,
      TYPE_UTILITY, TYPE_SITE, TYPE_SITE, TYPE_STATION, TYPE_SITE, TYPE_COMMUNITY, TYPE_SITE,
      TYPE_SITE, TYPE_PARKING, TYPE_SITE, TYPE_CHANCE, TYPE_SITE, TYPE_SITE, TYPE_STATION,
      TYPE_SITE, TYPE_SITE, TYPE_UTILITY, TYPE_SITE, TYPE_GOTO_JAIL, TYPE_SITE, TYPE_SITE,
      TYPE_COMMUNITY, TYPE_SITE, TYPE_STATION, TYPE_CHANCE, TYPE_SITE, TYPE_TAX, TYPE_SITE};

  // The Dice being used in the game
  private final Dice[] die = new Dice[2];


  private ArrayList<Player> player;// All the players
  private Board board;
  boolean gameover = false;


  public Monopoly() {

    JOptionPane.showMessageDialog(null,
        "Hello, Prepare to ruin your friendship with a wonderful game of monopoly!");

    for (int i = 0; i < die.length; i++) {
      die[i] = new Dice();
    }


    do {
      String players =
          JOptionPane.showInputDialog("Please enter the number of players between 2 and 6");

      this.players = Integer.parseInt(players);
    } while ((this.players < 2) || (this.players > 6));

    token = new Token[this.players];

    String playerName;

    player = new ArrayList<Player>();

    for (int i = 1; i <= this.players; i++) {
      playerName = JOptionPane.showInputDialog("Player " + i + " please enter your name");
      player.add(new Player(playerName));
    }


    board = new Board(player);


    ui = new UI(token);

  }

  private void gameplay() throws InterruptedException {


    ui.displayString("Below are a list of valid commands: \n" + INSTRUCTIONS);
    ui.displayString(
        "If you forget what the valid commands are type in the keyword 'Help' to see a list of valid commands.");

    int i = 0, j;

    ui.displayString("Let's see who starts first!");

    // roll to see who goes first
    Player temp = whoGoesFirst(player);
    for (j = 0; j < player.size(); j++) {

      if (player.get(j) == temp) {
        break;
      }
    }


    while (!gameover) {
      // j is the index of the person that starts
      int index_Of_Player_That_Starts = j;

      // Takes turns based on the player that starts
      switch (index_Of_Player_That_Starts) {

        // Turns are taken as normal if person 0 starts
        case 0:
          for (i = j; i < player.size(); i++) {

            if (gameover)
              break;
            turn(player.get(i), i);
          }
          break;
        // If person 1 and above starts, turns are taken in ascending order then the person 0 takes
        // their turn
        case 1:
          for (i = j; i < player.size(); i++) {
            if (gameover)
              break;
            turn(player.get(i), i);

          }

          for (int k = 0; k < i; k++) {
            if (gameover)
              break;
            turn(player.get(k), k);
          }
          break;

        case 2:
          for (i = j; i < player.size(); i++) {
            if (gameover)
              break;
            turn(player.get(i), i);
          }
          for (int k = 0; k < i; k++) {
            if (gameover)
              break;
            turn(player.get(k), k);
          }
          break;
        case 3:
          for (i = j; i < player.size(); i++) {
            if (gameover)
              break;
            turn(player.get(i), i);
          }
          for (int k = 0; k < i; k++) {
            if (gameover)
              break;
            turn(player.get(k), k);
          }
          break;

        case 4:
          for (i = j; i < player.size(); i++) {
            if (gameover)
              break;
            turn(player.get(i), i);
          }
          for (int k = 0; k < i; k++) {
            if (gameover)
              break;
            turn(player.get(k), k);
          }
          break;

        case 5:
          for (i = j; i < player.size(); i++) {
            if (gameover)
              break;
            turn(player.get(i), i);
          }
          for (int k = 0; k < i; k++) {
            if (gameover)
              break;
            turn(player.get(k), k);
          }
          break;


      }


      // Prints the winner if the game is over
      if (gameover) {
        int max_assets = 0, playerIndex = 0;
        for (i = 0; i < player.size(); i++) {
          ui.displayString(player.get(i).getName() + " is worth " + player.get(i).getWorth());
          if (player.get(i).getWorth() > max_assets) {
            max_assets = player.get(i).getWorth();
            playerIndex = i;
          }

        }

        ui.displayString(player.get(playerIndex).getName() + " Wins! :)");

      }


    }

    ui.displayString("The game is over.");
  }

  // Takes in the ArrayList of players and returns the person that goes first
  public Player whoGoesFirst(ArrayList<Player> person) {

    String command;

    int[] diceroll = new int[person.size()];

    // lets everyone roll
    for (int j = 0; j < person.size(); j++) {
      ui.displayString(player.get(j).getName() + " To see who starts first please type 'roll'. ");
      do {
        command = ui.getCommand();
        ui.displayString(command);
        if (command.equalsIgnoreCase("Roll")) {
          int dice = die[0].roll();
          int dice1 = die[1].roll();
          diceroll[j] = dice + dice1;
          ui.displayString("You rolled " + diceroll[j]);
        } else {
          ui.displayString("Invalid command. Please type 'roll'. ");
        }
      } while (!command.equalsIgnoreCase("Roll"));
    }

    // Finds the highest roll
    int max = diceroll[0];
    int playerIndex = 0;
    for (int k = 1; k < diceroll.length; k++) {
      if (diceroll[k] > max) {
        max = diceroll[k];
        playerIndex = k;
      }

    }

    ui.displayString(person.get(playerIndex).getName() + " you get to start first! :)");
    // Returns the person with the highest roll
    return person.get(playerIndex);
  }


  // Function contains all the actions a player can perform in their turn
  public void turn(Player person, int person_number) throws InterruptedException {
    String command;
    boolean rolled = false;
    ui.displayString(person.getName() + "'s turn");


    do {
      ui.displayString("Please roll to proceed");
      command = ui.getCommand();
      ui.displayString(command);

      if (command.equalsIgnoreCase("roll")) {
        int dice = die[0].roll();
        int dice1 = die[1].roll();

        // Roll again if you roll doubles
        if (dice == dice1) {
          do {
            ui.displayString("You rolled a double! Please roll again");
            command = ui.getCommand();
            if (command.equalsIgnoreCase("roll")) {
              dice = die[0].roll();
              dice1 = die[1].roll();
            }
          } while (dice == dice1);
        }

        rolled = true;
        ui.displayString("you rolled a " + (dice + dice1));
        moveToken((dice + dice1), person_number);
        movePerson((dice + dice1), person);
      }
    } while (rolled == false);

    boolean paid = false;
    do {
      command = ui.getCommand();
      ui.displayString(command);


      if (command.equalsIgnoreCase("roll")) {
        ui.displayString("Stop trying to be sly, you can't roll twice in one turn.");
      }

      // Ends the game if someone quits
      if (command.equalsIgnoreCase("quit")) {
        ui.displayString(person.getName() + " Quit :(");
        this.gameover = true;
        return;
      }

      // Checks if you're on a property that can require rent
      if ((board.squareType(person.getPosition()) == 1)
          || (board.squareType(person.getPosition()) == 2)
          || (board.squareType(person.getPosition()) == 3)) {

        // Checks if the property is owned and the owner isn't this person
        if (board.getProperty(person.getPosition()).owned()
            && (board.getProperty(person.getPosition()).getOwner() != person)) {


          do {
            ui.displayString("You have rent to pay, please type 'pay rent' \n");

            if (command.equalsIgnoreCase("pay rent")) {
              // Get the owner and pay the rent to the owner
              board.getProperty(person.getPosition()).getOwner()
                  .getRent(person.payRent(board.getProperty(person.getPosition()).getRent()));

              ui.displayString(person.getName() + " has paid rent to "
                  + board.getProperty(person.getPosition()).getOwner().getName());
            }

            paid = true;
          } while (paid == false);


        }
      }

      if (command.equalsIgnoreCase("buy")) {
        // Checks if you're on a property that can be bought
        if ((board.squareType(person.getPosition()) == 1)
            || (board.squareType(person.getPosition()) == 2)
            || (board.squareType(person.getPosition()) == 3)) {
          // Checks if the property isn't owned
          if (!board.getProperty(person.getPosition()).owned()) {
            person.buy(board.getProperty(person.getPosition()));
            ui.displayString("You bought " + board.getProperty(person.getPosition()).getName());
          }

          else {
            ui.displayString("This property is owned");
          }
        }

        else {

          ui.displayString("This property cannot be bought");

        }
      }

      if (command.equalsIgnoreCase("help")) {
        ui.displayString("Possible game commands: \n" + INSTRUCTIONS);
      }

      if (command.equalsIgnoreCase("balance")) {
        ui.displayString(person.getBalance());
      }

      if (command.equalsIgnoreCase("property")) {
        ui.displayString(person.getProperties());
      }

      // Displays message for invalid commands
      if (!(command.equalsIgnoreCase("property")) && !(command.equalsIgnoreCase("balance"))
          && !(command.equalsIgnoreCase("help")) && !(command.equalsIgnoreCase("pay rent"))
          && !(command.equalsIgnoreCase("quit")) && !(command.equalsIgnoreCase("done"))
          && !(command.equalsIgnoreCase("buy")) && !(command.equalsIgnoreCase("roll"))) {
        ui.displayString("Invalid command, type 'help' for help");
      }


    } while (!command.equalsIgnoreCase("done") && !command.equalsIgnoreCase("quit"));
  }



  private void moveToken(int m, int person) throws InterruptedException {

    int j, offset, position = 0;
    offset = 0;

    // Find position of token
    for (int i = 0; i < locations.length; i++) {
      if ((locations[i].x == token[person].getX()) && (locations[i].y == token[person].getY())) {
        position = i;
      }
    }

    // Move token according to that position

    if (position == 0) {
      for (j = 0; j < m; j++) {

        if ((position) >= locations.length) {
          position = position % locations.length;
        }

        token[person].setPosition(locations[position].x, locations[position].y);

        position++;

        ui.getBoardPanel().repaint();

        // controls the movement speed of the tokens across the board allowing for easy detection of
        // their movement
        Thread.sleep(300);

      }
    }


    else {
      for (j = 0; j < m; j++) {

        position++;

        if ((position) >= locations.length) {
          position = position % locations.length;

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



  public void movePerson(int m, Player person) {
    int j;
    int position = 0;


    // Find current position of person
    for (int i = 0; i < SQUARE_TYPES.length; i++) {
      if (person.getPosition() == i) {
        position = i;
      }
    }

    // Move player to required position
    for (j = 0; j < m; j++) {
      position++;
      if ((position) >= SQUARE_TYPES.length) {
        position = position % SQUARE_TYPES.length;
      }

      person.move(position);
      // Give 200 if you pass GO
      if (board.squareType(person.getPosition()) == 0) {
        ui.displayString("You passed GO! Collect 200!");
        person.getRent(200);
      }

    }
    ui.displayString(board.squareInfo(person.getPosition()));
  }

  // Launches the game
  public static void main(String args[]) throws InterruptedException {
    Monopoly game = new Monopoly();


    game.gameplay();

    return;
  }
}
