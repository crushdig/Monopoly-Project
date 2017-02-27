
//This class is for the Tokens and their characteristics
import java.awt.*;
import javax.swing.JComponent;

/*Each token has variables for location, dimension and shape, there's a constructor that
 * allows the user to specify the colour of the shape
 * the necessary accessory and mutator functions are provided
 */
public class Token extends JComponent{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int length;
	private int breadth;
	private int x;
	private int y;
	private Shape shape;
	private Color color;
	public Token(){ 
		super();
		setVisible(true);
		setBounds(10, 10, 700, 700);
		this.length = 15;
		this.breadth =15;
		this.x = 5;
		this.y = 5;
		this.shape = new Rectangle(this.x,this.y, this.length, this.breadth);
		this.color = Color.BLACK;
		
	}
	
	public Token(Color color){
		this();
		this.color = color;
	}
	
	public int getX(){
		return this.x;
	}
	
	public int getY(){
		return this.y;
	}
	
	//Method which specifies the x and y coordinates of the tokens
	public void setPosition(int x, int y){
		this.x = x;
		this.y = y;
	}

	public void paintComponent(Graphics g) {
	 	 Graphics2D g2 = (Graphics2D) g;
	 	super.paintComponent(g);
	 	
	 	g2.setColor(color);
	 	g2.fill(shape); //fills the shape with the colour specified
	 
	 	 g2.draw(this.shape);
	}
	
}