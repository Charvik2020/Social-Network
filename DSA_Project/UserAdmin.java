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

class UserAdmin extends JFrame implements ActionListener
{
	JButton user, admin;
	
	public UserAdmin()
	{
		super("HRU");			//Title for GUI window
		setLayout(new GridLayout(2,1));	//setting layout type of the window

		admin = new JButton("Admin");	//creating JButton and naming it "Signup"
		add(admin);			//add the button to the frame
	
		user = new JButton("User");	//creating JButton and naming it "Login"
		add(user);			//add the button to the frame

		admin.addActionListener(this);	//performs certain operation when the Signup button is clicked
		user.addActionListener(this);	//performs certain operation when the Login button is clicked		
	}
	
	public void actionPerformed(ActionEvent event)
	{
		if(event.getSource() == admin)	//if Signup button is clicked then 
		{	
			AdminPassword ap = new AdminPassword();	//it will call the constructor of the Signup class and main function of that class
			ap.main();
		}

		else if(event.getSource() == user)	//if Login button is clicked then
		{
			Signup_Login sl = new Signup_Login();	//it will call the constructor of the Login class and main function of that class
			sl.main();  
		}
	}
	public static void main(String[] args)
	{
		UserAdmin ua = new UserAdmin();	
		ua.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	//Exits the whole program when close(x) button is clicked
		ua.setSize(250,150);		//setting size of the window->setSize(x-axis,y-axis)
		ua.setVisible(true);		//setting frame to be visible
	}
}
