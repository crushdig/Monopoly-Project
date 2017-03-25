package sprint_Three;

public class Property extends Square {

	private boolean isOwned;
	private int value;
	private int rent[];
	private Player owner;
	private String colour;
	
	Property (String name, int value, int[] rent, String colour) {
		super(name);
		this.value = value;
		this.rent = rent;
		this.colour = colour;
		isOwned = false;
	}
	
	public int getValue () {
		return value;
	}
	
	public int getRent () {
		return rent[0];
	}
	
	public boolean isOwned () {
		return isOwned;
	}
	
	public void setOwner (Player Player) {
		owner = Player;
		isOwned = true;
	}
	
	public String getColour()
	{
	  return colour;
	}
	
	public Player getOwner () {
		return owner;
	}
}
