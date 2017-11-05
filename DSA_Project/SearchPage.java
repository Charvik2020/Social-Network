import java.awt.GridLayout;
import java.awt.Button;
import java.awt.TextField;
import java.awt.Label;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;
import java.util.*;

class SearchPage extends JFrame implements ActionListener
{
	Label l1 = new Label("Search For:");	//creating labels and text fields
	TextField t1 = new TextField();
	Label l2 = new Label(" ");
	Button b = new Button("Go!");		//creating button
	static String tempusr;

	public SearchPage(String username)
	{
		tempusr = username;
		setLayout(new GridLayout(3,2));		//setting the layout of the window->GridLayout(rows,columns)
		add(l1);				//adding labels and text fields to the frame
    		add(t1);
		
		add(b);					//adding button to the frame
		add(l2);	

		b.addActionListener(this);
    		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);	//disposes the frame when close(x) button is clicked
	}

	public void actionPerformed(ActionEvent event)
	{
		WordTree wt = new WordTree();
		String searchpage = t1.getText();
		File folder = new File("/home/ayam/DSA_Project/Pages");		//listOfFiles[i].getName() to get the name of file
		File[] listOfFiles = folder.listFiles();
		
    		for (int i = 0; i < listOfFiles.length; i++) 
		{
			wt.addWord(listOfFiles[i].getName());			//all file names added to the ArrayList
    		}

		List<String> wds = wt.getWordsForPrefix(searchpage);	//gets the words starting with the entered prefix and stores it in a List
		String[] temp = wds.toArray(new String[0]);	//converts the list into String array	
	
		if(wds.isEmpty())		//if the List is empty then it will show that "No users found!" 
		{
			l2.setText("No pages found!");
		}	
	
		else if(listOfFiles == null)
		{
			l2.setText("There are no pages in the network!");
		}
		else			//else it will call constructor and the main function of search_results class
		{	
			PageResults pr = new PageResults(temp, temp.length, tempusr);
			pr.main();
		}
	}

	public static void main()
	{
		SearchPage sg = new SearchPage(tempusr);
		sg.setSize(300,150);
		sg.setVisible(true);
	}
}
