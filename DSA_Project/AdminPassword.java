import java.awt.GridLayout;
import java.awt.Button;
import java.awt.TextField;
import java.awt.Label;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;
import java.util.*;

class AdminPassword extends JFrame implements ActionListener
{
	Label l1 = new Label("Password");
	Label l2 = new Label(" ");
	TextField t1 = new TextField();
	Button b= new Button("Login");		//creating button "Login"

	public AdminPassword()
	{
		setLayout(new GridLayout(2,2));	//setting the layout of the window->GridLayout(rows,columns)
		add(l1);			//adding labels and text fields to the frame
    		add(t1);	
		add(b);
		add(l2);
		t1.setEchoChar('*');
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);	//disposes the frame when close(x) button is clicked
		
		b.addActionListener(this);
	}
	
	public void actionPerformed(ActionEvent e)
  	{ 
		String password = t1.getText();

		try
		{
			File file = new File("/home/ayam/DSA_Project/Files/admin");	
			BufferedReader br = null;		
			br = new BufferedReader(new FileReader(file));

			String line = br.readLine();
	
			if(password.equals(line))
			{
				AdminLogin al = new AdminLogin();
				al.main();
				setVisible(false);
			}

			else
			{
				l2.setText("Wrong Password!");
			}	
		}
		catch(Exception err)
		{
			err.printStackTrace();
		}	
	}

	public static void main()
	{
		AdminPassword ap = new AdminPassword();
		ap.setSize(300,150);
		ap.setVisible(true);
	}
}
