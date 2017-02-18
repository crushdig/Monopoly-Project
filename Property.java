



public class Property {
	
	private int value;
	private final int rent = 25;
	private String name;
	private boolean owned;
	private String type;
	
	
	
	public Property(int value, String name, String type){
		
		this.value = value;
		this.name = name;
		this.owned = false;
		this.type = type;
		
	}
	
	
	public int getValue(){
		return value;
	}
	
	public int getRent(){
		return rent;
	}
	
	
	public String getName(){
		return name;
	}
	
	
	public boolean owned(){
		return owned;
	}
	
	public String getType(){
		return type;
	}
}
