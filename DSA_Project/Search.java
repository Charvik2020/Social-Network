import java.awt.GridLayout;
import java.awt.Button;
import java.awt.TextField;
import java.awt.Label;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;
import java.util.*;

class Search extends JFrame implements ActionListener
{
	Button b = new Button("Search profile");		//creating button
	Button b1 = new Button("Search page");
	static String tempusr;

	public Search(String username)
	{
		tempusr = username;
		setLayout(new GridLayout(2,1));
		
		add(b);
		add(b1);

		b.addActionListener(this);
		b1.addActionListener(this);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}

	public void actionPerformed(ActionEvent event)
	{
		if(event.getSource() == b)
		{
			searchProfile sp = new searchProfile(tempusr);
			sp.main();
		}
	
		else if(event.getSource() == b1)
		{
			SearchPage sg = new SearchPage(tempusr);
			sg.main();			
		}
	}
	
	public static void main()
	{
		Search sh = new Search(tempusr);
		sh.setSize(150,150);
		sh.setVisible(true);
	}
}
