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

class changePWD extends JFrame implements ActionListener
{
	static String temp_username;

	Label l1 = new Label("New Password");		//creating labels and textfields
	Label l2 = new Label("Confirm Password");
	Label l3 = new Label(" ");
	TextField t1 = new TextField();
	TextField t2 = new TextField();

	Button b = new Button("Confirm");		//creating button 

	public changePWD(String user_id)
  	{ 
		super("Change Password");		//Title for GUI window
		setLayout(new GridLayout(4,2));		//setting the layout of the window->GridLayout(rows,columns)
		temp_username = user_id;		//storing username in a temporary string

		add(l1);			//adding labels and text fields to the frame
		add(t1);
		add(l2);
		add(t2);
		add(l3);
		add(b);				//adding button to the frame

		t1.setEchoChar('*');		//will show the '*' in the text field instead of text
		t2.setEchoChar('*');		//will show the '*' in the text field instead of text
		b.addActionListener(this);
	}

	public void actionPerformed(ActionEvent e)
	{
		changepwd();		//calls changepwd function when confirm button is clicked
	}
	
	public void changepwd()
	{
		String temp1 = t1.getText(); 	//storing text fields in different strings
		String temp2 = t2.getText();
		try
		{
			if(temp1.equals(temp2))		//if both the text fields are same  
			{
				File file = new File("/home/ayam/DSA_Project/Files/"+temp_username+"");
				BufferedReader br = null;		
				br = new BufferedReader(new FileReader(file));
			
				StringBuilder sb = new StringBuilder();
				String line = br.readLine();
				String[] string1 = line.split(",");

				string1[3] = t1.getText();	//then the password will be changed from the file and new password will be saved

				FileWriter fw = new FileWriter("/home/ayam/DSA_Project/Files/"+temp_username+"");
				BufferedWriter bw = new BufferedWriter(fw);
			
				fw.write(string1[0]+","+string1[1]+","+string1[2]+","+string1[3]+","+string1[4]+","+string1[5]);
				bw.close();

				//l3.setText(string1[0]+", You have changed your password successfully");
				setVisible(false);				
			}

			else
			{
				l3.setText("Passwords in both the fields didn't match!");
			}
		}
	
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

	public static void main()
	{
		changePWD cpwd = new changePWD(temp_username);
		cpwd.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);	//disposes the frame when close(x) button is clicked
		cpwd.setSize(400,200);			//setting size of the window->setSize(x-axis,y-axis)
		cpwd.setVisible(true);			//setting frame to be visible
	}
}

