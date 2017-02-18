import java.awt.Color;
import java.awt.Dimension;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;

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
	
	 
	
	public BoardPanel(Token[] token){
		
		
		int offset = 10;
		
		switch (token.length) {
	      case 2:
	        token[0] = new Token();
	        token[0].setBounds(10, 10, 700, 700);

	        token[0].setPosition(600, 603);

	        token[1] = new Token(Color.red);

	        token[1].setBounds(10, 10, 700, 700);

	        token[1].setPosition(600 + offset, 603 + offset);
	        
	        layeredPane.add(token[0], new Integer(2));
		    layeredPane.add(token[1], new Integer(3));

	        break;

	      case 3:
	        token[0] = new Token();

	        token[0].setBounds(10, 10, 700, 700);

	        token[0].setPosition(600, 603);

	        token[1] = new Token(Color.red);

	        token[1].setBounds(10, 10, 700, 700);

	        token[1].setPosition(600 + offset, 603 + offset);

	        offset = offset + 10;

	        token[2] = new Token(Color.blue);

	        token[2].setBounds(10, 10, 700, 700);

	        token[2].setPosition(600 + offset, 603 + offset);
	        
	        layeredPane.add(token[0], new Integer(2));
		    layeredPane.add(token[1], new Integer(3));
		    layeredPane.add(token[2], new Integer(4));
		    

	        break;

	      case 4:
	        token[0] = new Token();

	        token[0].setBounds(10, 10, 700, 700);

	        token[0].setPosition(600, 603);

	        token[1] = new Token(Color.red);

	        token[1].setBounds(10, 10, 700, 700);

	        token[1].setPosition(600 + offset, 603 + offset);

	        offset = offset + 10;

	        token[2] = new Token(Color.blue);

	        token[2].setBounds(10, 10, 700, 700);

	        token[2].setPosition(600 + offset, 603 + offset);

	        offset = offset + 10;

	        token[3] = new Token(Color.green);

	        token[3].setBounds(10, 10, 700, 700);

	        token[3].setPosition(600 + offset, 603 + offset);
	        
	        layeredPane.add(token[0], new Integer(2));
		    layeredPane.add(token[1], new Integer(3));
		    layeredPane.add(token[2], new Integer(4));
		    layeredPane.add(token[3], new Integer(5));
		    
		    
	        break;

	      case 5:
	        token[0] = new Token();

	        token[0].setBounds(10, 10, 700, 700);

	        token[0].setPosition(600, 603);

	        token[1] = new Token(Color.red);

	        token[1].setBounds(10, 10, 700, 700);

	        token[1].setPosition(600 + offset, 603 + offset);

	        offset = offset + 10;

	        token[2] = new Token(Color.blue);

	        token[2].setBounds(10, 10, 700, 700);

	        token[2].setPosition(600 + offset, 603 + offset);

	        offset = offset + 10;

	        token[3] = new Token(Color.green);

	        token[3].setBounds(10, 10, 700, 700);

	        token[3].setPosition(600 + offset, 603 + offset);

	        offset = offset + 10;

	        token[4] = new Token(Color.yellow);

	        token[4].setBounds(10, 10, 700, 700);

	        token[4].setPosition(600 + offset, 603 + offset);
	        
	        layeredPane.add(token[0], new Integer(2));
		    layeredPane.add(token[1], new Integer(3));
		    layeredPane.add(token[2], new Integer(4));
		    layeredPane.add(token[3], new Integer(5));
		    layeredPane.add(token[4], new Integer(6));

	        break;

	      case 6:
	        token[0] = new Token();

	        token[0].setBounds(10, 10, 700, 700);

	        token[0].setPosition(600, 603);

	        token[1] = new Token(Color.red);

	        token[1].setBounds(10, 10, 700, 700);

	        token[1].setPosition(600 + offset, 603 + offset);

	        offset = offset + 10;

	        token[2] = new Token(Color.blue);

	        token[2].setBounds(10, 10, 700, 700);

	        token[2].setPosition(600 + offset, 603 + offset);

	        offset = offset + 10;

	        token[3] = new Token(Color.green);

	        token[3].setBounds(10, 10, 700, 700);

	        token[3].setPosition(600 + offset, 603 + offset);

	        offset = offset + 10;

	        token[4] = new Token(Color.yellow);

	        token[4].setBounds(10, 10, 700, 700);

	        token[4].setPosition(600 + offset, 603 + offset);

	        offset = offset + 10;

	        token[5] = new Token(Color.cyan);

	        token[5].setBounds(10, 10, 700, 700);

	        token[5].setPosition(600 + offset, 603 + offset);
	        
	        layeredPane.add(token[0], new Integer(2));
		    layeredPane.add(token[1], new Integer(3));
		    layeredPane.add(token[2], new Integer(4));
		    layeredPane.add(token[3], new Integer(5));
		    layeredPane.add(token[4], new Integer(6));
		    layeredPane.add(token[5], new Integer(7));

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
	
	
	 public void refresh () {
			revalidate();
			repaint();
			return;
	    }
	
	 public InfoPanel getIpanel(){
		 return this.infoPanel;
	 }
	 
	 public CommandPanel getCpanel(){
		 return this.commandPanel;
	 }
}
