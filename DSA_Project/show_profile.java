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

class show_profile extends JFrame implements ActionListener
{
	Label l1 = new Label(" ");		//creating empty labels
	Label l2 = new Label(" ");
	Label l3 = new Label(" ");
	Label l4 = new Label(" ");
	Label l5 = new Label(" ");
	JButton button = new JButton("Send friend Request");
	static String[] string;
	static String tempuser;

	public show_profile(String[] string1, String username)
	{
		tempuser = username;
		setLayout(new GridLayout(3,2));		//setting the layout of the window->GridLayout(rows,columns)
		string = string1;
		add(l1);				//adding labels and text fields to the frame
		add(l2);
		add(l3);
		add(l4);
		add(l5);
		add(button);
		l1.setText(string[0]+"");		//setting some text in the empty text field
		l2.setText(string[2]+"");
		l3.setText(string[4]+"");
		l4.setText(string[5]+"");

		button.addActionListener(this);		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);	//disposes the frame when close(x) button is clicked
	}
				
	public void actionPerformed(ActionEvent e)
	{
		FriendRequest fr = new FriendRequest(tempuser, string[1]);
		l5.setText("Friend Request sent!");
	}
	public static void main()
	{
		show_profile shp = new show_profile(string, tempuser);
    		shp.setSize(350,200);			//setting size of the window->setSize(x-axis,y-axis)
    		shp.setTitle("Searched Profile");	//Title for GUI window
    		shp.setVisible(true);			//setting frame to be visible
	}
}

