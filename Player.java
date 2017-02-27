import java.util.ArrayList;

//This class is for the player and their characteristics
public class Player {
    
    private String name;
    private int balance;
    private int position;
    ArrayList<Property> assets;//Stores the players assets
    
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
        return "Your Balance is\t" + this.balance;
    }
    
    public String getProperties(){
        
        int i;
        
        if(assets.size() == 0) return "You have no properties to your name. ";
        
        String summary = "Your properties are:\n";
        
        for(i=1;i<=assets.size();i++){
            summary = summary + assets.get(i).getName() + "\n";
        }
        return summary;
    }
    
    //Function for calculating player worth at the end of the game
    public int getWorth(){
        int i, worth = 0;
        
        for(i=1;i<=assets.size();i++){
            worth = worth + assets.get(i).getValue();
        }
        return worth + this.balance;
    }
    
    public void move(int position){
        this.position = position;
    }
    
    public int getPosition(){
        return position;
    }
    

}