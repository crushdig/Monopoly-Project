import java.util.Random;

public class rolladie {
  
  public static void main(String[]  args){
    
    Random r = new Random();
    int result = 0;
    for (int i = 0; i < 6; i++){
    result = r.nextInt(6);
    result++;
    
    System.out.println("you rolled a " +result);
  }
  
  
  
  }

}
