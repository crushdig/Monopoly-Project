
public class Station extends Property{

	public Station(int value, String name, int type, int[] rent, int mortgage_value) {
		super(value, name, type, rent, mortgage_value);
		// TODO Auto-generated constructor stub
	}
	
	public int getRent(Player person){
		int count = -1;
		
		for(int i=0; i<person.assets.size();i++){
			if(person.assets.get(i) instanceof Station){
				count++;
			}
		}
		
		return rent[count];
	}


}
