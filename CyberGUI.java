package allTogether;
import javax.swing.*;

import java.awt.event.*;
import java.io.IOException;
import java.sql.SQLException;
import java.awt.*;

public class CyberGUI {

	public static void main(String[] args) throws IOException {
		Database db = new Database();
		@SuppressWarnings("unused")
		boolean useless = db.truncateDatabase();
		db = Foundation.process1();
		
		Search cyberSearch = new Search();
		GUIFrame guiFrame = new GUIFrame(cyberSearch, db); // pass in search class.
		guiFrame.setSize(1200,800);
		guiFrame.setVisible(true);
		guiFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		guiFrame.setTitle("Cyberminer");
	}
}

class Engine 
{
	public void pressedOperator(char value) 
	{
		System.out.println(value + " button pressed");
	}

	public void pressedClearAll() 
	{
		System.out.println("PRESSEDCLEARALL");
		System.out.println("Clear All button pressed");
	}
}

class GUIFrame extends JFrame 
{
	static final long serialVersionUID = 1;
	MainPanel mainPanel;
	JButton blankButton;

	public GUIFrame(Search sear, Database db) 
	{
		mainPanel = new MainPanel(sear, db);
		add(mainPanel);
	}
}

class MainPanel extends JPanel 
{
	static final long serialVersionUID = 1;

	MainPanel(Search cyberSearch, Database db) 
	{
		GroupLayout layout = new GroupLayout(this);
		setLayout(layout);
		layout.setAutoCreateGaps(true);
		layout.setAutoCreateContainerGaps(true);

		ImbeddedPanel imbeddedPanel = new ImbeddedPanel(cyberSearch, db);
		layout.setHorizontalGroup(
				   layout.createSequentialGroup()
					  .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
							   .addComponent(imbeddedPanel)));
		layout.setVerticalGroup(layout.createSequentialGroup().addComponent(imbeddedPanel));
	}
}

class ImbeddedPanel extends JPanel implements ActionListener 
{
	static final long serialVersionUID = 1;
	SearchPanel searchPanel = new SearchPanel();
	ResultsPanel resultsPanel = new ResultsPanel();
	Search sch;
	Database datab;

	ImbeddedPanel(Search cyberSearch, Database db) 
	{
		sch = cyberSearch;
		datab = db;
		search = new JButton("Search"); 
		search.addActionListener(this);

		GroupLayout layout = new GroupLayout(this);
		setLayout(layout);
		layout.setAutoCreateGaps(true);
		layout.setAutoCreateContainerGaps(true);
		
		layout.setHorizontalGroup(
				   layout.createSequentialGroup()
					  .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
						   .addComponent(searchPanel).addComponent(search)
						   .addComponent(resultsPanel)));
		layout.setVerticalGroup(layout.createSequentialGroup().addComponent(searchPanel,160,160,160)
				.addComponent(search).addComponent(resultsPanel));
	}
	// Declarations

	JButton search;

	@SuppressWarnings("static-access")
	public void actionPerformed(ActionEvent ae) 
	{
		if (ae.getSource() == search) 
		{
			System.out.println("Search button was pressed");
			resultsPanel.lbl.setText("We are searching for \n" + searchPanel.lbl.getText());
			try 
			{
				resultsPanel.lbl.setText(((sch.search(datab, searchPanel.lbl.getText(), null, true)).get(0)[0]));
			} 
			catch (SQLException e) 
			{
				resultsPanel.lbl.setText("No results were found for " + searchPanel.lbl.getText() + 
						".\n\nPlease enter another search phrase.");
			}
			// pressing the search button makes stuff happen here
		}
	}
}

class SearchPanel extends JPanel 
{
	static final long serialVersionUID = 1;
	public JTextField lbl;
	int base = 10;

	SearchPanel() 
	{
		setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		setBackground(Color.GREEN);
		setLayout(new GridLayout(1, 1));
		lbl = new JTextField();
		lbl.setFont(new Font("Comic Sans MS", Font.PLAIN, 24));
		add(lbl);
	}

	void setBase(int value) 
	{
		base = value;
	}
}

class ResultsPanel extends JPanel 
{
	static final long serialVersionUID = 1;
	public JTextArea lbl;
	private JScrollPane jScrollPane1;
	
	ResultsPanel() 
	{
		setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		setBackground(Color.YELLOW);
		setLayout(new GridLayout(1, 1));
		lbl = new JTextArea();
		lbl.setLineWrap(true);
		lbl.setWrapStyleWord(true);
		lbl.setEditable(false);
		jScrollPane1 = new JScrollPane(lbl);
		add(jScrollPane1);
	}
}