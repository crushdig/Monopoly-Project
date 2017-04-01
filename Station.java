
public class Station extends Property{

	public Station(int value, String name, int type, int[] rent, int mortgage_value) {
		super(value, name, type, rent, mortgage_value);
		// TODO Auto-generated constructor stub
	}
	
	public int getRent(Player person){
		return rent[super.getOwner().getNumStationsOwned()-1];
	}


}
