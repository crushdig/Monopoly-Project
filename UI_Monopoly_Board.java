package sprint_One;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;

public class UI_Monopoly_Board extends JFrame
{

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
	
	Point defaultPosition = new Point(600,603);
	
	private int players;
	private Token[] token ;

	private static JPanel infopanel;
	private static JPanel commandpanel ;
//	private static JPanel basepanel;

	final static int field_Width = 20;
	private static JTextField commandField = new JTextField(field_Width);
	private static JLabel commandLabel = new JLabel("Enter Command: ");
	
	private Border blacklineBorder;
	private final int ROWS = 35;
	private final int COLUMNS = 40;
	private JTextArea textArea =  new JTextArea(ROWS, COLUMNS);
	private static JLabel resultLabel = new JLabel();
	private JLayeredPane lp = getLayeredPane();
	
//	private static JLabel monopolyLabel;

	public UI_Monopoly_Board()
	{
		players = 6;
		token = new Token[players];

		int offset = 10;

		//Initialise tokens and spaces them out with offset

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


		JLabel monopolyLabel = new JLabel(new ImageIcon("Monopoly_board.jpg"));
		monopolyLabel.setBounds(-50, -30, 800, 750);

		lp.add(monopolyLabel, new Integer(1));
		lp.add(token[0], new Integer(2));
		lp.add(token[1], new Integer(3));
		lp.add(token[2], new Integer(4));
		lp.add(token[3], new Integer(5));
		lp.add(token[4], new Integer(6));
		lp.add(token[5], new Integer(7));

		
		setSize(1500, 750);
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setTitle("Welcome to Monopoly");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}
		
	public void information_Panel()
	{
		infopanel = new JPanel();
		 
		JScrollPane scrollPane = new JScrollPane(textArea);
		blacklineBorder = BorderFactory.createLineBorder(Color.BLACK);
		TitledBorder title = BorderFactory.createTitledBorder(blacklineBorder, "Information Panel");
		
		scrollPane.getPreferredSize();
		infopanel.setBorder(title);
		infopanel.add(resultLabel, BorderLayout.NORTH);
		
		textArea.setEditable(false);
		infopanel.add(scrollPane);
		infopanel.setBounds(750, 0, 600, 600);
		
		lp.add(infopanel);
	}
		
	
	public void command_Panel()
	{
		commandpanel = new JPanel();
		
		blacklineBorder = BorderFactory.createLineBorder(Color.BLACK);
		
		JButton button = new JButton("Enter");
		button.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				if(commandField.getText().isEmpty())
				{
					
				}
				
				else
				{
					String command = commandField.getText();
					textArea.append(command + "\n");
				}
			}			
		} );
		
		getRootPane().setDefaultButton(button);
		

					
		commandpanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		button.setPreferredSize(new Dimension(65,20));
		commandpanel.add(commandLabel);
		commandpanel.add(commandField);
		commandpanel.add(button);
		commandpanel.setBounds(800, 650, 500, 50);
					
//					basepanel = new JPanel();
//					basepanel.setLayout(new BorderLayout());
//					basepanel.add(infopanel,BorderLayout.EAST);
//					basepanel.add(commandpanel,BorderLayout.SOUTH);
//					basepanel.setBounds(0, 0, 1500, 1500);
		
		lp.add(commandpanel);

	}
	
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
				Thread.sleep(150);
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
