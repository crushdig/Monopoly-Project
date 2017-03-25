package sprint_Three;
import java.awt.BorderLayout;

import javax.swing.JFrame;

import java.util.ArrayList;

public class UI {

	private static final int FRAME_WIDTH = 1200;
	private static final int FRAME_HEIGHT = 800;
	private static final String CURRENCY = " pounds";
	
	public static final int CMD_QUIT = 0;
	public static final int CMD_DONE = 1;
	public static final int CMD_ROLL = 2;
	public static final int CMD_BUY = 3;
	public static final int CMD_PAY_RENT = 4;
	public static final int CMD_AUCTION = 5;
	public static final int CMD_PROPERTY = 6;
	public static final int CMD_BALANCE = 7;
	public static final int CMD_BANKRUPT = 8;
	public static final int CMD_HELP = 9;
	public static final int CMD_BUILD_HOUSE = 10;
	
	public static final int ERR_SYNTAX = 0;
	public static final int ERR_DOUBLE_ROLL = 1;
	public static final int ERR_NO_ROLL = 2;
	public static final int ERR_INSUFFICIENT_FUNDS = 3;
	public static final int ERR_NOT_A_PROPERTY = 4;
	public static final int ERR_RENT_ALREADY_PAID = 5;
	public static final int ERR_NOT_OWNED = 6;
	public static final int ERR_IS_OWNED = 7;
	public static final int ERR_SELF_OWNED = 8;
	public static final int ERR_RENT_OWED= 9;
	
	private final String[] errorMessages = {
		"Error: Not a valid command.",
		"Error: Too many rolls this turn.",
		"Error: You have a roll to play.",
		"Error: You don't have enough money.",
		"Error: This square is not a property.",
		"Error: You have already paid the rent.",
		"Error: The property is not owned.",
		"Error: The property is already owned.",
		"Error: You own the property.",
		"Error: You owe rent."
	};
	
	private JFrame frame = new JFrame();
	private BoardPanel boardPanel;	
	private InfoPanel infoPanel = new InfoPanel();
	private CommandPanel commandPanel = new CommandPanel();
	private String string;
	private boolean done;
	private int commandId;

	public UI (ArrayList<Player> players) {
		boardPanel = new BoardPanel(players);
		frame.setSize(FRAME_WIDTH, FRAME_HEIGHT);
		frame.setTitle("Monopoly");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(boardPanel, BorderLayout.LINE_START);
		frame.add(infoPanel, BorderLayout.LINE_END);
		frame.add(commandPanel,BorderLayout.PAGE_END);
		frame.setResizable(false);
		frame.setVisible(true);
	}

// INPUT METHODS
	
	public void inputName (int numPlayers) {
		if (numPlayers == 0) {
			infoPanel.addText("Enter new player name (" + boardPanel.getTokenName(numPlayers) + "):");			
		} else {
			infoPanel.addText("Enter new player name (" + boardPanel.getTokenName(numPlayers)  +  ") or done:");
		}
		commandPanel.getCommand();
		string = commandPanel.getCommand();
		if ( (numPlayers > 0) && (string.trim().toLowerCase().equals("done")) ) {
			done = true;
		} else {
			done = false;
		}
		
		infoPanel.addText("> " + string);
	}
	
	public void inputCommand (Player player) {
		boolean inputValid = false;
		do {
			infoPanel.addText(player + " type your command:");
			string = commandPanel.getCommand();
			infoPanel.addText("> " + string);
			string = commandPanel.getCommand();
			string = string.toLowerCase();
			string = string.trim();
			string = string.replaceAll("( )+", " ");
			switch (string) {
				case "quit" :
					commandId = CMD_QUIT;
					inputValid = true;
					break;
				case "done" :
					commandId = CMD_DONE;
					inputValid = true;
					break;
				case "roll" :
					commandId = CMD_ROLL;
					inputValid = true;
					break;
				case "buy" :
					commandId = CMD_BUY;				
					inputValid = true;
					break;
				case "pay rent" :
					commandId = CMD_PAY_RENT;
					inputValid = true;
					break;
				case "auction" :
					commandId = CMD_AUCTION;
					inputValid = true;
					break;
				case "property" :
					commandId = CMD_PROPERTY;
					inputValid = true;
					break;
				case "balance" :
					commandId = CMD_BALANCE;
					inputValid = true;
					break;
				case "bankrupt" :
					commandId = CMD_BANKRUPT;
					inputValid = true;
					break;
				case "help" :
					commandId = CMD_HELP;
					inputValid = true;
					break;
				default:
					inputValid = false;
				}
			if (!inputValid) {
				displayError(ERR_SYNTAX);
			}
		} while (!inputValid);
		if (commandId == CMD_DONE) {
			done = true;
		} else {
			done = false;
		}		
		return;
	}
	
	public String getString () {
		return string; 
	}
	
	public String getTokenName (int tokenId) {
		return boardPanel.getTokenName(tokenId);
	}
	
	public int getCommandId () {
		return commandId;
	}
	
	public boolean isDone () {
		return done;
	}
	
	
// OUTPUT METHODS
	
	public void display () {
		boardPanel.refresh();
		return;
	}
	
	public void displayString (String string) {
		infoPanel.addText(string);
	}
	
	public void displayBankTransaction (Player player) {
		if (player.getTransaction() >= 0) {
			infoPanel.addText(player + " receives " + player.getTransaction() + CURRENCY + " from the bank.");
		} else {
			infoPanel.addText(player + " pays " + (-player.getTransaction()) + CURRENCY + " to the bank.");			
		}
	}
	
	public void displayTransaction (Player fromPlayer, Player toPlayer) {
		infoPanel.addText(fromPlayer + " pays " + toPlayer.getTransaction() + CURRENCY + " to " + toPlayer);
	}
	
	public void displayDice (Player player, Dice dice) {
		infoPanel.addText(player + " rolls " + dice + ".");
	}
	
	public void displayRollDraw () {
		infoPanel.addText("Draw");
	}
	
	public void displayRollWinner (Player player) {
		infoPanel.addText(player + " wins the roll.");
	}
	
	public void displayGameOver () {
		infoPanel.addText("GAME OVER");
	}
	
	public void displayCommandHelp () {
		infoPanel.addText("Available commands: roll, pay rent, buy, property, balance, done, quit. ");
	}
	
	public void displayBalance (Player player) {
		infoPanel.addText(player + "'s balance is " + player.getBalance() + CURRENCY);
	}
	
	public void displayError (int errorId) {
		infoPanel.addText(errorMessages[errorId]);
	}
	
	public void displayPassedGo (Player player) {
		infoPanel.addText(player + " passed Go.");
	}
	
	public void displayLatestProperty (Player player) {
		infoPanel.addText(player + " bought " + player.getLatestProperty());
		return;
	}
	
	public void displayProperty (Player player) {
		ArrayList<Property> propertyList = player.getProperties();
		if (propertyList.size() == 0) {
			infoPanel.addText(player + " owns no property.");
		} else {
			infoPanel.addText(player + " owns the following property...");
			for (Property p : propertyList) {
				infoPanel.addText(p.getName() + ", rent " + p.getRent());				
			}
		}
	}
	
	public void displaySquare (Player player, Board board) {
		infoPanel.addText(player + " arrives at " + board.getSquare(player.getPosition()).getName() + ".");
		if (board.isProperty(player.getPosition())) {
			Property property = board.getProperty(player.getPosition());
			if (property.isOwned()) {
				infoPanel.addText("The property is owned by " + property.getOwner() + ". Rent is " + property.getRent() + CURRENCY + ".");				
			} else {
				infoPanel.addText("The property is not owned. Rent is " + property.getRent() + CURRENCY + ".");								
			}
		}
		return;
	}
	
	public void displayAssets (Player player) {
		infoPanel.addText(player + " has assets of " + player.getAssets() + CURRENCY);
		return;
	}
	
	public void displayWinner (Player player) {
		infoPanel.addText("The winner is " + player);
		return;
	}
	
	public void displayDraw (ArrayList<Player> players) {
		infoPanel.addText("The following players drew the game " + players);
		return;
	}
}
