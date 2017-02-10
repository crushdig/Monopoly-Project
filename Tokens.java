import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Shape;
import java.io.File;
import java.io.IOException;


import javax.imageio.ImageIO;
import javax.swing.JComponent;
import javax.swing.JFrame; 

public class Tokens extends JComponent{
	
	private int lenght;
	private int breadth;
	private int x;
	private int y;
	private int players = 2;
	private Shape[] shape = new Shape[players];
	
	Point[] locations = { new Point(400,400), new Point(10,10)};
	
	public Tokens(){ 
		this.lenght = 20;
		this.breadth =30;
		this.x = 5;
		this.y = 5;
		int offset = 0;
		int i;
		for(i=0;i<players;i++){
		this.shape[i] = new Rectangle(this.x+offset,this.y+offset, this.lenght, this.breadth);
		offset = offset +50;
		}
	}
	
	public Tokens(int numberOfPlayers){
		this();
		this.players = numberOfPlayers;
		int i;
		for(i=0;i<players;i++){
			this.shape[i] = new Rectangle(this.x,this.y, this.lenght, this.breadth);
			}
	}
	
	public int getX(){
		return x;
	}
	
	public int getY(){
		return y;
	}
	
	public void changePosition(int x, int y){
		this.x = x;
		this.y = y;
	}
	
	public void paintComponent(Graphics g) {
	 	 Graphics2D g2 = (Graphics2D) g;
	 	 int i;
	 	super.paintComponent(g);
	 	try {
			g2.drawImage(ImageIO.read(new File("Monopoly Board.jpg")), 0, 0, null);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	 	g2.setColor(Color.red);
	 	 g2.draw(this.shape[0]);
	 	 
	 	 switch(players){
	 	 case 2 :	
	 		 for(i=1;i<players;i++){
	 			g2.setColor(Color.red);
	 		 	 g2.draw(this.shape[i]);
	 		 }
	 		 break;
	 	 
	 	 case 3 :	
	 		 g2.fillOval(20, 20, this.breadth, this.lenght);
	 		 g2.setColor(Color.blue);
	 		 g2.fillOval(30, 30, this.breadth, this.lenght);
	 		 break;
	 	 case 4 :
	 		 g2.fillOval(10, 10, this.breadth, this.lenght);
	 		 g2.setColor(Color.blue);
	 		 g2.fillOval(20, 20, this.breadth, this.lenght);
	 		 g2.setColor(Color.yellow);
	 		 g2.fillOval(30, 30, this.breadth, this.lenght);
	 		 break;
	 	 case 5:
	 		 g2.fillOval(10, 10, this.breadth, this.lenght);
	 		 g2.setColor(Color.blue);
	 		 g2.fillOval(20, 20, this.breadth, this.lenght);
	 		g2.setColor(Color.yellow);
	 		 g2.fillOval(30, 30, this.breadth, this.lenght);
	 		g2.setColor(Color.green);
	 		 g2.fillOval(40, 40, this.breadth, this.lenght);
	 		 break;
 		 case 6:
 			 g2.fillOval(10, 10, this.breadth, this.lenght);
 			g2.setColor(Color.blue);
	 		 g2.fillOval(20, 20, this.breadth, this.lenght);
	 		g2.setColor(Color.yellow);
	 		 g2.fillOval(30, 30, this.breadth, this.lenght);
	 		g2.setColor(Color.green);
	 		 g2.fillOval(40, 40, this.breadth, this.lenght);
	 		g2.setColor(Color.black);
	 		 g2.fillOval(50, 50, this.breadth, this.lenght);
	 		 break;
 		 default :
            System.out.println("Invalid number of players");
	 	 }
	 	
	}
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Tokens car = new Tokens(2);
		
		JFrame f = new JFrame();
	       f.setTitle("");
	       f.setSize(1000,1000);
	       f.setVisible(true);
	       f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	       
	      
	       f.add(car);
	      // car.changePosition(50, 50);
	      // car.changePosition(20, 50);
	       
		
	}

}
