import java.awt.Color;
import java.awt.Dimension;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
/*
 * This class deals mainly with setting up the Graphics of the board
 */
public class BoardPanel extends JFrame{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final int FRAME_WIDTH = 1500;
	private static final int FRAME_HEIGHT = 750;
	 private JLayeredPane layeredPane = getLayeredPane(); // The use of a JLayeredPane allows easier
     														// and more flexible specification of
	 														// component positions
	private static JLabel monopolyImageLabel;
	
	private InfoPanel infoPanel;
	private CommandPanel commandPanel;
	
	
	public BoardPanel(ArrayList<Token> token, int numberOfTokens){
		
		
		
		int offset = 10;
		//The tokens are initialised and added to the board
		switch (numberOfTokens) {
	      case 2:
	        token.add(new Token());
	        

	        token.get(0).setPosition(600, 603);

	        
	        token.add(new Token(Color.red));

	       
	        token.get(1).setPosition(600 + offset, 603 + offset);
	        
	        layeredPane.add(token.get(0), new Integer(2));
		    layeredPane.add(token.get(1), new Integer(3));

	        break;

	      case 3:
	    	  token.add(new Token());
		        

		        token.get(0).setPosition(600, 603);

		        
		        token.add(new Token(Color.red));

		       
		        token.get(1).setPosition(600 + offset, 603 + offset);


	        offset = offset + 10;

	        	        
	        token.add(new Token(Color.blue));

		       
	        token.get(2).setPosition(600 + offset, 603 + offset);
	        
	        layeredPane.add(token.get(0), new Integer(2));
		    layeredPane.add(token.get(1), new Integer(3));
		    layeredPane.add(token.get(2), new Integer(4));

	        break;

	      case 4:
	    	  token.add(new Token());
		        

		        token.get(0).setPosition(600, 603);

		        
		        token.add(new Token(Color.red));

		       
		        token.get(1).setPosition(600 + offset, 603 + offset);


	        offset = offset + 10;

	        	        
	        token.add(new Token(Color.blue));

		       
	        token.get(2).setPosition(600 + offset, 603 + offset);

	        offset = offset + 10;

	        
	        token.add(new Token(Color.green));

		       
	        token.get(3).setPosition(600 + offset, 603 + offset);
	        
	        layeredPane.add(token.get(0), new Integer(2));
		    layeredPane.add(token.get(1), new Integer(3));
		    layeredPane.add(token.get(2), new Integer(4));
		    layeredPane.add(token.get(3), new Integer(5));
		    
		    
	        break;

	      case 5:
	    	  token.add(new Token());
		        

		        token.get(0).setPosition(600, 603);

		        
		        token.add(new Token(Color.red));

		       
		        token.get(1).setPosition(600 + offset, 603 + offset);


	        offset = offset + 10;

	        	        
	        token.add(new Token(Color.blue));

		       
	        token.get(2).setPosition(600 + offset, 603 + offset);

	        offset = offset + 10;

	        
	        token.add(new Token(Color.green));

		       
	        token.get(3).setPosition(600 + offset, 603 + offset);
	        
	        offset = offset + 10;
	        
	        token.add(new Token(Color.yellow));

		       
	        token.get(4).setPosition(600 + offset, 603 + offset);

	        
	        layeredPane.add(token.get(0), new Integer(2));
		    layeredPane.add(token.get(1), new Integer(3));
		    layeredPane.add(token.get(2), new Integer(4));
		    layeredPane.add(token.get(3), new Integer(5));
		    layeredPane.add(token.get(4), new Integer(6));

	        break;

	      case 6:
	    	  token.add(new Token());
		        

		        token.get(0).setPosition(600, 603);

		        
		        token.add(new Token(Color.red));

		       
		        token.get(1).setPosition(600 + offset, 603 + offset);


	        offset = offset + 10;

	        	        
	        token.add(new Token(Color.blue));

		       
	        token.get(2).setPosition(600 + offset, 603 + offset);

	        offset = offset + 10;

	        
	        token.add(new Token(Color.green));

		       
	        token.get(3).setPosition(600 + offset, 603 + offset);
	        
	        offset = offset + 10;
	        
	        token.add(new Token(Color.yellow));

		       
	        token.get(4).setPosition(600 + offset, 603 + offset);

	        offset = offset + 10;

	        
	        token.add(new Token(Color.cyan));

		       
	        token.get(5).setPosition(600 + offset, 603 + offset);
	        
	        layeredPane.add(token.get(0), new Integer(2));
		    layeredPane.add(token.get(1), new Integer(3));
		    layeredPane.add(token.get(2), new Integer(4));
		    layeredPane.add(token.get(3), new Integer(5));
		    layeredPane.add(token.get(4), new Integer(6));
		    layeredPane.add(token.get(5), new Integer(7));

	        break;

	      default:
	        System.out.println("Invalid number of players");
	    }
		
		commandPanel = new CommandPanel();
		infoPanel = new InfoPanel();
		
		
		monopolyImageLabel =
		        new JLabel(new ImageIcon(this.getClass().getResource("Board.jpg")));
		monopolyImageLabel.setBounds(-50, -30, 800, 750);
		
		
		// The image and the tokens are added to the pane at different levels allowing them to overlap
	    layeredPane.add(monopolyImageLabel, new Integer(1));
	   
	   
	    layeredPane.add(commandPanel);
	    layeredPane.add(infoPanel);
	    
	    setSize(new Dimension(FRAME_WIDTH, FRAME_HEIGHT));
		setExtendedState(JFrame.MAXIMIZED_BOTH); // Sets the default window for the JFrame as a
        // maximised
		setBackground(Color.BLACK);
		
		 this.setResizable(false);
		    setTitle("Welcome to Monopoly");
		    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Ensures the JFrame operation ends completely
		                                                    // upon exiting the window
		    setVisible(true);
	    
		return;
	}
	
	
	 public InfoPanel getIpanel(){
		 return this.infoPanel;
	 }
	 
	 public CommandPanel getCpanel(){
		 return this.commandPanel;
	 }
}
