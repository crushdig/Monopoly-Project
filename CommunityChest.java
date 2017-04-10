import java.util.Random;

public class CommunityChest extends Square{
	
	private String[] Cards = {"Advance to Go.",
			"Go back to Old Kent Road.",
			"Go to jail. Move directly to jail. Do not pass Go. Do not collect £200.",
			"Pay hospital £100.",
			"Doctor's fee. Pay £50.",
			"Pay your insurance premium £50.",
			"Bank error in your favour. Collect £200.",
			"Annuity matures. Collect £100.",
			"You inherit £100.",
			"From sale of stock you get £50.",
			"Receive interest on 7% preference shares: £25.",
			"Income tax refund. Collect £20.",
			"You have won second prize in a beauty contest. Collect £10.",
			"It is your birthday. Collect £10 from each player.",
			"Get out of jail free. This card may be kept until needed or sold.",
			"Pay a £10 fine or take a Chance."};

	public CommunityChest(String name, int type) {
		super(name, type);
		// TODO Auto-generated constructor stub
	}
	
	
	public String drawCard(){
		int number;
		Random Generate = new Random();
		number = Generate.nextInt((Cards.length-1) - 0 + 0) + 0;
		return Cards[number];
	}
	
	public void processCard(Player person, Monopoly m, String cardInstruction){
		m.getUI().displayString(cardInstruction);
		
		switch(cardInstruction){
		
		case "Advance to Go.":
			advanceToPosition(person,m,0);
			break;
		
		case "Go to jail. Move directly to jail. Do not pass Go. Do not collect £200.":
			break;
			
		case "Go back to Old Kent Road.":
			advanceToPosition(person,m,1);
			break;
			
		case "Pay hospital £100.":
			m.getUI().displayString("You paid: " + person.payRent(100));
			break;
			
		case "Doctor's fee. Pay £50.":
			m.getUI().displayString("You paid: " + person.payRent(50));
			break;
			
		case "Pay your insurance premium £50.":
			m.getUI().displayString("You paid: " + person.payRent(50));
			break;
		
		case "Bank error in your favour. Collect £200.":
			m.getUI().displayString("You got: " + person.getRent(200));
			break;
			
		case "Annuity matures. Collect £100.":
			m.getUI().displayString("You got: " + person.getRent(100));
			break;
			
		case "You inherit £100.":
			m.getUI().displayString("You got: " + person.getRent(100));
			break;
			
		case "From sale of stock you get £50.":
			m.getUI().displayString("You got: " + person.getRent(50));
			break;
			
		case "Receive interest on 7% preference shares: £25.":
			m.getUI().displayString("You got: " + person.getRent(25));
			break;
			
		case "Income tax refund. Collect £20.":
			m.getUI().displayString("You got: " + person.getRent(20));
			break;
			
		case "You have won second prize in a beauty contest. Collect £10.":
			m.getUI().displayString("You got: " + person.getRent(10));
			break;
			
		case "It is your birthday. Collect £10 from each player.":
			m.collectFromAllPlayers(person);
			break;
			
		case "Pay a £10 fine or take a Chance.":
			m.processChanceOrFine(person);
			break;
			
		case "Get out of jail free. This card may be kept until needed or sold.":
			break;
			
		default:
			break;
		}
		
	}
	
	public void advanceToPosition(Player person, Monopoly m, int position){
		int count=0;
		while(!(person.getPosition()==position))
		{
			person.moveForward();
			if(person.getPosition() == 0){
				m.getUI().displayString("You passed GO! Collect 200!");
				person.getRent(200);
			}
			count++;
		}
		try {
			m.moveToken(count, person);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		m.getUI().displayString(m.getBoard().squareInfo(person.getPosition()));
		
	}
	

}
