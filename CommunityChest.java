import java.util.Random;

public class CommunityChest extends Square{
	
	String[] Cards = {"Advance to Go."+
			"Go back to Old Kent Road."+
			"Go to jail. Move directly to jail. Do not pass Go. Do not collect £200."+
			"Pay hospital £100."+
			"Doctor's fee. Pay £50."+
			"Pay your insurance premium £50."+
			"Bank error in your favour. Collect £200."+
			"Annuity matures. Collect £100."+
			"You inherit £100."+
			"From sale of stock you get £50."+
			"Receive interest on 7% preference shares: £25."+
			"Income tax refund. Collect £20."+
			"You have won second prize in a beauty contest. Collect £10."+
			"It is your birthday. Collect £10 from each player."+
			"Get out of jail free. This card may be kept until needed or sold."+
			"Pay a £10 fine or take a Chance."};

	public CommunityChest(String name, int type) {
		super(name, type);
		// TODO Auto-generated constructor stub
	}
	
	
	public String drawCard(){
		int number;
		Random Generate = new Random();
		number = Generate.nextInt(Cards.length - 0 + 0) + 0;
		return Cards[number];
	}

}
