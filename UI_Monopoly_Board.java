package sprint_One;

/* Code written by: lagosBoys
 *A layered pane was used to place all components on the frame at the desired locations 
 */

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;

public class UI_Monopoly_Board extends JFrame
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//Array of coordinates for the position of each square on the board
	Point[] locations = { new Point(630,643), new Point(570,643),new Point(510,643), 
			new Point(450,643),new Point(390,643), new Point(330,643),new Point(270,643), new Point(210,643),
			new Point(150,643), new Point(95,643),new Point(60,643), new Point(60,573),
			new Point(60,503), new Point(60,433),new Point(60,383), new Point(60,323),
			new Point(60,273), new Point(60,213),new Point(60,153), new Point(60,93),new Point(60,33), 
			
			new Point(120,13),new Point(180,13), new Point(230,13),new Point(280,13), 
			new Point(340,13),new Point(400,13), new Point(460,13),new Point(520,13), new Point(580,13)
			,new Point(660,60),
			new Point(660,120),
			new Point(660,160), new Point(660,220),new Point(660,280), new Point(660,340),new Point(660,400),
			new Point(660,460),new Point(660,520), new Point(660,580), new Point(660,640)
		};
	//The default position or starting point which is go
	Point defaultPosition = new Point(600,603);
	
	private int players;
	private Token[] token ;

	private static JPanel infoPanel;
	private static JPanel commandPanel;

	//creates a fixed length for the text field used by the command field
	final static int field_Width = 20;
	private static JTextField commandField = new JTextField(field_Width);
	private static JLabel commandLabel = new JLabel("Enter Command: ");
	
	private Border blackLineBorder;
	private final int ROWS = 35;
	private final int COLUMNS = 40;
	private JTextArea textArea =  new JTextArea(ROWS, COLUMNS);
	private static JLabel echoed_Text_Label = new JLabel();
	private JLayeredPane layeredPane = getLayeredPane(); //The use of a JLayeredPane allows easier and more flexible specification of component positions 
	
	private static JLabel monopolyImageLabel;

	public UI_Monopoly_Board()
	{
		players = 6;
		token = new Token[players];

		int offset = 10;

		//Initialise tokens depending on number of players and spaces them out with offset

		switch(players)
		{
			case 2:	
				token[0] = new Token();
				token[0].setBounds(10, 10, 700, 700);

				token[0].setPosition(600,603);

				token[1] = new Token(Color.red);
			
				token[1].setBounds(10, 10, 700, 700);

				token[1].setPosition(600+offset,603+offset);

				break;

			case 3:	
				token[0] = new Token();
			
				token[0].setBounds(10, 10, 700, 700);

				token[0].setPosition(600,603);

				token[1] = new Token(Color.red);
			
				token[1].setBounds(10, 10, 700, 700);

				token[1].setPosition(600+offset,603+offset);

				offset = offset + 10;
				
				token[2] = new Token(Color.blue);
			
				token[2].setBounds(10, 10, 700, 700);

				token[2].setPosition(600+offset,603+offset);

				break;
			
			case 4:
				token[0] = new Token();
			
				token[0].setBounds(10, 10, 700, 700);

				token[0].setPosition(600,603);

				token[1] = new Token(Color.red);
			
				token[1].setBounds(10, 10, 700, 700);

				token[1].setPosition(600+offset,603+offset);

				offset = offset + 10;
				
				token[2] = new Token(Color.blue);
			
				token[2].setBounds(10, 10, 700, 700);

				token[2].setPosition(600+offset,603+offset);

				offset = offset + 10;

				token[3] = new Token(Color.green);
			
				token[3].setBounds(10, 10, 700, 700);

				token[3].setPosition(600+offset,603+offset);
				break;
			
			case 5:
				token[0] = new Token();
			
				token[0].setBounds(10, 10, 700, 700);

				token[0].setPosition(600,603);

				token[1] = new Token(Color.red);
			
				token[1].setBounds(10, 10, 700, 700);

				token[1].setPosition(600+offset,603+offset);

				offset = offset + 10;

				token[2] = new Token(Color.blue);
			
				token[2].setBounds(10, 10, 700, 700);

				token[2].setPosition(600+offset,603+offset);

				offset = offset + 10;

				token[3] = new Token(Color.green);
			
				token[3].setBounds(10, 10, 700, 700);

				token[3].setPosition(600+offset,603+offset);

				offset = offset + 10;

				token[4] = new Token(Color.yellow);
			
				token[4].setBounds(10, 10, 700, 700);

				token[4].setPosition(600+offset,603+offset);

				break;
			
			case 6:
				token[0] = new Token();
			
				token[0].setBounds(10, 10, 700, 700);

				token[0].setPosition(600,603);

				token[1] = new Token(Color.red);
			
				token[1].setBounds(10, 10, 700, 700);

				token[1].setPosition(600+offset,603+offset);

				offset = offset + 10;

				token[2] = new Token(Color.blue);
			
				token[2].setBounds(10, 10, 700, 700);

				token[2].setPosition(600+offset,603+offset);

				offset = offset + 10;

				token[3] = new Token(Color.green);
				
				token[3].setBounds(10, 10, 700, 700);

				token[3].setPosition(600+offset,603+offset);

				offset = offset + 10;

				token[4] = new Token(Color.yellow);
			
				token[4].setBounds(10, 10, 700, 700);

				token[4].setPosition(600+offset,603+offset);

				offset = offset + 10;

				token[5] = new Token(Color.cyan);
				
				token[5].setBounds(10, 10, 700, 700);

				token[5].setPosition(600+offset,603+offset);

				break;
			
			default:
				System.out.println("Invalid number of players");
		}

		//The location of the image should be specified here
		monopolyImageLabel = new JLabel(new ImageIcon("Monopoly_board.jpg"));
		monopolyImageLabel.setBounds(-50, -30, 800, 750);
		//The image and the tokens are added to the pane at different levels allowing them to overlap
		layeredPane.add(monopolyImageLabel, new Integer(1));
		layeredPane.add(token[0], new Integer(2));
		layeredPane.add(token[1], new Integer(3));
		layeredPane.add(token[2], new Integer(4));
		layeredPane.add(token[3], new Integer(5));
		layeredPane.add(token[4], new Integer(6));
		layeredPane.add(token[5], new Integer(7));

		
		setSize(1500, 750);
		setExtendedState(JFrame.MAXIMIZED_BOTH); //Sets the default window for the JFrame as a maximised
		this.setResizable(false);
		setTitle("Welcome to Monopoly");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //Ensures the JFrame operation ends completely upon exiting the window 
		setVisible(true);
	}
	
	//This method displays the information panel and adds it to the pane
	public void information_Panel()
	{
		infoPanel = new JPanel();
		 
		JScrollPane scrollPane = new JScrollPane(textArea);
		blackLineBorder = BorderFactory.createLineBorder(Color.BLACK);
		TitledBorder title = BorderFactory.createTitledBorder(blackLineBorder, "Information Panel");
	
		infoPanel.setBorder(title);
		infoPanel.add(echoed_Text_Label, BorderLayout.NORTH);
		
		//prevents any information from being added or deleted from the information panel.
		textArea.setEditable(false);
		infoPanel.add(scrollPane);
		infoPanel.setBounds(750, 0, 600, 600); //specifies the desired coordinates of the panel being added to the layered pane
		
		layeredPane.add(infoPanel);
	}
		
	//This method displays the command panel and adds it to the pane
	public void command_Panel()
	{
		commandPanel = new JPanel();
		
		blackLineBorder = BorderFactory.createLineBorder(Color.BLACK);
		
		JButton button = new JButton("Enter");
		
		/* implements the actionlistener interface on the button to help execute a command when the
		 * button is clicked
		 */
		button.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				if(commandField.getText().isEmpty())
				{
					String command = null;
					textArea.append(command);
				}
				
				else
				{
					String command = commandField.getText();
					textArea.append(command + "\n");
				}
			}			
		} );
		
		//This invokes the actionListeners interface for actionPerformed (quick way to implement a key listener on the keyboards Enter button)
		getRootPane().setDefaultButton(button);
		
		commandPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		button.setPreferredSize(new Dimension(65,20));
		commandPanel.add(commandLabel);
		commandPanel.add(commandField);
		commandPanel.add(button);
		commandPanel.setBounds(800, 630, 500, 50); //specifies the desired coordinates of the panel being added to the layered pane
		
		layeredPane.add(commandPanel);
	}
	
	//Method which moves the tokens round the board one at a time
	public void moveTokens() throws InterruptedException
	{
		
		int i, j, offset;
		offset = 0;
		
		for(i = 0; i < token.length; i++)
		{
			for(j = 0; j < locations.length; j++)
			{
				token[i].setPosition(locations[j].x, locations[j].y);
				repaint();
				//controls the movement speed of the tokens across the board allowing for easy detection of their movement
				Thread.sleep(300);
			}
			
			token[i].setPosition(defaultPosition.x + offset, defaultPosition.y+offset);
			offset = offset + 15;
		}
	}
	


	public static void main(String[] args) throws InterruptedException 
	{
		// TODO Auto-generated method stub
		UI_Monopoly_Board obj = new UI_Monopoly_Board();
		obj.information_Panel();
		obj.command_Panel();
		obj.moveTokens();
		
	}

}
