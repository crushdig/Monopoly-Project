

public class UI {
	
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

}
