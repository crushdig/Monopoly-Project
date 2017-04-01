
public class Utility extends Property{

	public Utility(int value, String name, int type, int[] rent, int mortgage_value) {
		super(value, name, type, rent, mortgage_value);
		// TODO Auto-generated constructor stub
	}
	
	public int getRent(Player person){		
		return getRentMultiplier();
	}
	
	public int getRentMultiplier () {
		return rent[super.getOwner().getNumUtilitiesOwned()-1];
	}

}
