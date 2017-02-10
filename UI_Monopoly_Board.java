//package sprint_One;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;


public class UI_Monopoly_Board
{	
	//gets the path for particular file of choice
	private static String image = "C:\\Users\\student\\workspace\\Test2\\src\\Monopoly_board.jpg";
//	private static String image = "C:\\Users\\Guest 2\\workspace\\Java\\Software Engineering\\sprint_One\\Monopoly_board.jpg";
	//Creates a file object for the file that's to be opened
	private static File inputImage = new File(image);
	private static BufferedImage load_Image_Data; 
	private static JLabel imageLabel;
	private static JFrame imageFrame = new JFrame();
	private static JPanel panel = new JPanel();
	final static int field_Width = 90;
	private static JTextField commandField = new JTextField(field_Width);
	private static JLabel commandLabel = new JLabel("Enter Command: ");
	private static JLabel resultLabel = new JLabel();
	private Border blacklineBorder;
	private final int ROWS = 10;
	private final int COLUMNS = 10;
	private JTextArea textArea =  new JTextArea(ROWS, COLUMNS);
	
	public UI_Monopoly_Board() throws IOException
	{	
		/* Loads all the properties of the image by making use of bufferedImages's colorModel. 
		 * Image is located using an image reader and then read in as a file. */ 
		load_Image_Data = ImageIO.read(inputImage); 
		
		/* Creating a JLable object which takes in an ImageIcon object that loads the image 
		 * being used. The JLabel loads the image as a label in the JFrame. */
		imageLabel = new JLabel(new ImageIcon(load_Image_Data));
		
		imageFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		imageFrame.getContentPane().add(imageLabel);
		imageFrame.setTitle("Welcome to Monopoly");
		imageFrame.setSize(300, 400);
	
		JPanel panel1 = new JPanel();		
		panel1.setBackground(Color.black);
		panel1.add(imageLabel);
		
//		panel.setBackground(Color.black);
		panel.setLayout(new BorderLayout());
		panel.add(panel1, BorderLayout.CENTER);
		
		imageFrame.add(panel);
		
		imageFrame.pack();
		imageFrame.setVisible(true);
		
	}
	
	public void InformationPanel()
	{
		JPanel panel2 = new JPanel();
		JScrollPane scrollPane = new JScrollPane(textArea);
		blacklineBorder = BorderFactory.createLineBorder(Color.BLACK);
		TitledBorder title = BorderFactory.createTitledBorder(blacklineBorder, "Information Panel");
		scrollPane.getPreferredSize();
		panel2.setBorder(title);
		panel2.add(resultLabel, BorderLayout.NORTH);
		textArea.setEditable(false);
		panel2.add(scrollPane);
		
		
		panel.add(panel2, BorderLayout.EAST);
	}
	
	public void commandPanel_With_ActionPerformed()
	{	
		blacklineBorder = BorderFactory.createLineBorder(Color.BLACK);
		TitledBorder title = BorderFactory.createTitledBorder(blacklineBorder, "Command Panel");
		
		JButton button = new JButton("Enter");
		button.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent listener) 
			{
				String command = commandField.getText();
				textArea.append(command + "\n");
			}
			
		} );
		
		JPanel panel3 = new JPanel();
		panel3.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		panel3.setBorder(title);
		button.setPreferredSize(new Dimension(65,20));
		panel3.add(commandLabel);
		panel3.add(commandField);
		panel3.add(button);
		
		panel.add(panel3, BorderLayout.SOUTH);
	}
	
	public static void main(String[] args) throws IOException
	{	
		UI_Monopoly_Board obj = new UI_Monopoly_Board();
		
		obj.InformationPanel();
		obj.commandPanel_With_ActionPerformed();
	}

}
