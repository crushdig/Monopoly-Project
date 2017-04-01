import java.util.ArrayList;

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
	public static final int ERR_NOT_A_NAME = 10;
	public static final int ERR_TOO_MANY_BUILDINGS = 11;
	public static final int ERR_NOT_A_SITE = 12;
	public static final int ERR_NOT_YOURS = 13;
	public static final int ERR_TOO_FEW_BUILDINGS = 14;
	public static final int ERR_DOES_NOT_HAVE_GROUP = 15;
	public static final int ERR_DUPLICATE = 16;
	public static final int ERR_HAS_BUILDINGS = 17;
	public static final int ERR_IS_MORTGAGED = 18;
	public static final int ERR_IS_NOT_MORTGAGED = 19;
	public static final int SITE_IS_MORTGAGED = 20;
	
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
			"Error: You owe rent.",
			"Error: Not a name.",
			"Error: Too many units.",
			"Error: That property is not a site.",
			"Error: You do not own that property.",
			"Error: Must be one or more units",
			"Error: You do not own the whole colour group.",
			"Error: Duplicate name.",
			"Error: The site has units on it.",
			"Error: The property has already been mortgaged.",
			"Error: The property has not been mortgaged.",
			"Error: The property has been mortgaged."
		};
	
	private BoardPanel boardPanel;
	private InfoPanel infoPanel;
	
	public UI(ArrayList<Token> tokens, int numberOfTokens){
		
		boardPanel = new BoardPanel(tokens, numberOfTokens);
		infoPanel = boardPanel.getIpanel();
		
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
	
	public void displayGameOver () {
		boardPanel.getIpanel().displayString("GAME OVER");
		return;
	}
	
	public void displayAssets (Player player) {
		ArrayList<Property> propertyList = player.getProperties();
		if (propertyList.size() == 0) {
			boardPanel.getIpanel().displayString(player + " owns no property.");
		} else {
			boardPanel.getIpanel().displayString(player + " owns the following property...");
			for (Property p : propertyList) {
				String mortgageStatus = "";
				if (p.isMortgaged()) {
					mortgageStatus = ", is mortgaged";
				}
				if (p instanceof Site) {
					Site site = (Site) p;
					String buildStatus = "";
					if (site.getNumBuildings()==0) {
						buildStatus = "with no buildings";
					} else if (site.getNumBuildings()==1) {
						buildStatus = "with 1 house";
					} else if (site.getNumBuildings()<5) {
						buildStatus = "with " + site.getNumBuildings() + " houses";
					} else if (site.getNumBuildings()==5) {
						buildStatus = "with a hotel";
					}
					boardPanel.getIpanel().displayString(site + " (" + site.getColourGroup().getName() + "), rent " + site.getRent() + ", " + buildStatus + mortgageStatus + ".");		
				} else if (p instanceof Station) {
					boardPanel.getIpanel().displayString(p + ", rent " + p.getRent()  + mortgageStatus + ".");	
				} else if (p instanceof Utility) {
					boardPanel.getIpanel().displayString(p + ", rent " + ((Utility) p).getRentMultiplier() + " times dice" + mortgageStatus + ".");
				}
			}
		}
	}
	
	public void displayBuild (Player player, Site site, int numUnits) {
		if (numUnits==1) {
			infoPanel.displayString(player + " builds 1 unit on " + site);			
		} else {
			infoPanel.displayString(player + " builds " + numUnits + " units on " + site);
		}
		return;
	}
	
	public void displayDemolish (Player player, Site site, int numUnits) {
		if (numUnits==1) {
			infoPanel.displayString(player + " demolishes 1 unit on " + site);			
		} else {
			infoPanel.displayString(player + " demolishes " + numUnits + " units on " + site);
		}
		return;
	}	
	
	public void displayBankrupt (Player player) {
		infoPanel.displayString(player + " is bankrupt. They have mortgaged all properties and sold all buildings and are still in debt");
		boardPanel.repaint();
		return;
	}
	
	public void displayMortgage (Player player, Property property) {
		infoPanel.displayString(player + " mortgages " + property + " for " + property.getMortgageValue());
		return;				
	}
	
	public void displayMortgageRedemption (Player player, Property property) {
		infoPanel.displayString(player + " redeems " + property + " for " + property.getMortgageRemptionPrice());
		return;
	}

}
