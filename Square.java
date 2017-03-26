//This class is for the squares and their characteristics
public class Square {
	
	private String name;
	private int type;
	
	public Square(String name, int type){
		this.name = name;
		this.type = type;
	}
	
	
	public int getType(){
		return type;
	}
	
	public String getName(){
		return name;
	}
	
	public String toString () {
		return name;
	}
	
}
