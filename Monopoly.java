import java.awt.*;

public class Monopoly {
	private int players =  2;
	private Token[]  token;
	
	private UI ui;
	  // Array of coordinates for the position of each square on the board
	  Point[] locations = {new Point(630, 643), new Point(570, 643), new Point(510, 643),
	      new Point(450, 643), new Point(390, 643), new Point(330, 643), new Point(270, 643),
	      new Point(210, 643), new Point(150, 643), new Point(95, 643), new Point(60, 643),
	      new Point(60, 573), new Point(60, 503), new Point(60, 433), new Point(60, 383),
	      new Point(60, 323), new Point(60, 273), new Point(60, 213), new Point(60, 153),
	      new Point(60, 93), new Point(60, 33),

	      new Point(120, 13), new Point(180, 13), new Point(230, 13), new Point(280, 13),
	      new Point(340, 13), new Point(400, 13), new Point(460, 13), new Point(520, 13),
	      new Point(580, 13), new Point(660, 60), new Point(660, 120), new Point(660, 160),
	      new Point(660, 220), new Point(660, 280), new Point(660, 340), new Point(660, 400),
	      new Point(660, 460), new Point(660, 520), new Point(660, 580), new Point(660, 640)};
	  // The default position or starting point which is go
	  
	  Point defaultPosition = new Point(600, 603);
	 
	  Monopoly () {
		  
		  token = new Token[players];
		  ui= new UI(token);
		  return;
		  
	    
	  }
	
	  private void tour() throws InterruptedException {

		    int i, j, offset;
		    offset = 0;

		    for (i = 0; i < token.length; i++) {
		      for (j = 0; j < locations.length; j++) {
		        token[i].setPosition(locations[j].x, locations[j].y);
		        ui.getBoardPanel().repaint();
		        // controls the movement speed of the tokens across the board allowing for easy detection of
		        // their movement
		        Thread.sleep(300);
		      }

		      token[i].setPosition(defaultPosition.x + offset, defaultPosition.y + offset);
		      offset = offset + 15;
		    }
		  }
	  
	  private void echo () {
			String command;
			
			ui.displayString("ECHO MODE");
			do {
				command = ui.getCommand();
				ui.displayString(command);
			} while (!command.equals("quit"));
			return;
		}

	  
	  public static void main (String args[]) throws InterruptedException {	
			Monopoly game = new Monopoly();		
			game.tour();
			game.echo();
			return;
		}
}
