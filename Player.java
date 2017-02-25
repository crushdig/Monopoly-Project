import java.util.ArrayList;

public class Player {
	
	private String name;
	private int balance;
	private int position;
	ArrayList<Property> assets;
	
	public Player(String name){
		this.name = name;
		this.balance = 1500;
		this.position = 0;
		this.assets = new ArrayList<Property>();
	}

	public String getName(){
		return this.name;
	}
	
	public void buy(Property property){
		balance = balance - property.getValue();
		assets.add(property);
		property.setOwned(this);
	}
	
	public void getRent(int rent){
		balance = balance + rent;
	}
	
	public int payRent(int rent){
		balance = balance - rent;
		return rent;
	}
	
	
	public String getBalance(){
		return "Balance is\t" + this.balance;
	}
	
	public String getProperties(){
		
		int i;
		
		if(assets.size() == 0) return "No properties. ";
		
		String summary = "Properties are:\n";
		
		for(i=0;i<assets.size();i++){
			summary = summary + assets.get(i).getName() + "\n";
		}
		return summary;
	}
	
	public int getWorth(){
		int i, worth = 0;
		
		for(i=0;i<assets.size();i++){
			worth = worth + assets.get(i).getValue();
		}
		System.out.println(worth + this.balance);
		return worth + this.balance;
	}
	
	public void move(int position){
		this.position = position;
	}
	
	public int getPosition(){
		return position;
	}
	

}
