import java.awt.GridLayout;
import java.awt.Button;
import java.awt.TextField;
import java.awt.Label;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;
import java.util.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import javax.swing.JFormattedTextField;

class search_results extends JFrame implements ActionListener
{
	JButton[] b1;			//array of buttons
	static String[] temp;
	static int templength;
	int i;
	static String temp_usrnm, text;

	public search_results(String[] temp1, int length, String username)
	{
		temp_usrnm = username;
		b1 = new JButton[length];		//defining the size of button array
				
		templength = length;
		temp = temp1;

		setLayout(new GridLayout(templength,2));	//setting the layout of the window->GridLayout(rows,columns)
			
		for(i = 0; i < templength; i++)		//creating buttons and adding them to the frame
		{
			b1[i] = new JButton(temp[i]);
			text = b1[i].getText().toString();
			
			if(!temp_usrnm.equals(text))
			{				
				add(b1[i]);
				b1[i].addActionListener(this);
			}
		}
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);	//disposes the frame when close(x) button is clicked
	} 
	
	public void actionPerformed(ActionEvent e)
	{
		JButton src = (JButton)e.getSource();	//sees for which button is clicked from the array of buttons
		
   		for(int i = 0; i < templength; i++)
		{	
			if(src == b1[i])
				showProfile(i);		//showProfile function for that button is called
		}
	}
	
	public void showProfile(int i)
	{
		try
		{
	   		File file = new File("/home/ayam/DSA_Project/Files/"+temp[i]+"");	//finds the file with the name which is stored in the temp array
			BufferedReader br = new BufferedReader(new FileReader(file));
			StringBuilder sb = new StringBuilder();
			String line = br.readLine();
			String[] string1 = line.split(",");	//splits the string and stores into string array
			
			show_profile shp = new show_profile(string1, temp_usrnm);	//calls constructor and main function of show_profile class
			shp.main();				
		}
		catch(Exception err)
		{
			err.printStackTrace();
		}
	}

	public static void main()
	{
		search_results sr = new search_results(temp, templength, temp_usrnm);
    		sr.setSize(300,200);			//setting size of the window->setSize(x-axis,y-axis)
    		sr.setTitle("Search Profile");		//Title for GUI window
    		sr.setVisible(true);			//setting frame to be visible
	}
}
