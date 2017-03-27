//This class is for the User Interface

public class UI {
	
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
	
	private BoardPanel boardPanel;
	
	public UI(Token[] tokens){
		
		boardPanel = new BoardPanel(tokens);
		
		return;
	}
	
	public BoardPanel getBoardPanel(){
		return boardPanel;
		
	}
	
	public String getCommand () {
		return boardPanel.getCpanel().getCommand();
	}
	
	
	public void displayString (String string) {
		boardPanel.getIpanel().addText(string);
		return;
	}
	
	public void displayError (int errorId) {
		boardPanel.getIpanel().displayString(errorMessages[errorId]);
		return;
	}
	

}
