



public class Property extends Square{
	
	private int value;
	private final int rent = 25;
	private boolean owned;
	private Player owner;
	
	
	public Property(int value, String name, int type){
		super(name,type);
		this.value = value;
		this.owned = false;
		this.owner = null;
		
	}
	
	
	public int getValue(){
		return value;
	}
	
	public int getRent(){
		return rent;
	}
	
	public Player getOwner(){
		return this.owner;
	}
	
	public void setOwned(Player person){
		this.owned = true;
		this.owner = person;
	}
	
	public boolean owned(){
		return owned;
	}
	

}
