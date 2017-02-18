package sprint_One;

class Players
{
    private String name;
    private int balance;
    
    public void storeDetails(String playerName, int startingBalance)
    {
        name = playerName;
        balance = startingBalance;
    }
    
//    constructor
    public Players()
    {
        
    }
    
    public Players(String playerName, int startingBalance)
    {
        this();
        name = playerName;
        balance = startingBalance;
        
    }
    
    public String empty()
    {
        return name = "";
    }
    
    public String getDetails()
    {
        return name + " ";
    }
    
    public int getBalance()
    {
      return balance;
    }
}