
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import javax.swing.text.DefaultCaret;


public class InfoPanel extends JPanel{
	
	private static final long serialVersionUID = 1L;
	private static final int FONT_SIZE = 14;

	private Border blackLineBorder;
	private final int ROWS = 33;
	  private final int COLUMNS = 40;
	  private JTextArea textArea = new JTextArea(ROWS, COLUMNS);
	JScrollPane scrollPane = new JScrollPane(textArea);
	DefaultCaret caret = (DefaultCaret)textArea.getCaret();
	
	InfoPanel () {
		
		blackLineBorder = BorderFactory.createLineBorder(Color.BLACK);
		TitledBorder title = BorderFactory.createTitledBorder(blackLineBorder, "Information Panel");

	    setBorder(title);
		textArea.setEditable(false);
		textArea.setFont(new Font("Times New Roman", Font.PLAIN, FONT_SIZE));
		textArea.setLineWrap(true);
		textArea.setWrapStyleWord(true);
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
		//setLayout(new BorderLayout());
		add(scrollPane, BorderLayout.CENTER);
	    setBounds(750, 0, 600, 600); // specifies the desired coordinates of the panel being
	                                           // added to the layered pane
	}

	public void addText (String text) {
		textArea.setText(textArea.getText()+"\n"+text + "\n");
	}

	
}
