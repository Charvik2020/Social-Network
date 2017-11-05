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

class createPage extends JFrame implements ActionListener
{
	static String admin;
	Label l1 = new Label("Page Name");	//creating labels and text fields
	Label l2 = new Label(" ");
	TextField t1 = new TextField();
	Button b = new Button("Create");	//creating button
	public createPage(String userid)
	{
		setLayout(new GridLayout(2,2));		//setting the layout of the window->GridLayout(rows,columns)
		admin = userid;			
		add(l1);			//adding labels and text fields to the frame
    		add(t1);
    		add(b);				//adding button to the frame
    		add(l2);
    		b.addActionListener(this);
    		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}

	public void actionPerformed(ActionEvent e)
	{
		create_page();		//calls create_page when the Create button is clicked
	}

	public void create_page()
	{
		String pagename = t1.getText();	//pagename from the text field is stored in a string
		try
		{
			File file = new File("/home/ayam/DSA_Project/Pages/"+pagename+"");
			
			if(file.exists())	//if page with same name exists then page will not be created
			{
				l2.setText("Page already exists!! Please choose another name");	
			}

			else	//if page doesn't exists 
			{
				FileWriter fw = new FileWriter(file);
				BufferedWriter bw = new BufferedWriter(fw);
				fw.write("Admin:"+admin);	//then it will create a page with that name 
				bw.close();		//and store the admin's name as the username from which he/she is logged in
				l2.setText("Page created successfully!");
				setVisible(false);
			}
		}
	
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

	public static void main()
	{
		createPage cp = new createPage(admin);
		cp.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);	//disposes the frame when close(x) button is clicked
		cp.setSize(300,100);			//setting size of the window->setSize(x-axis,y-axis)
		cp.setVisible(true);			//setting frame to be visible
	}
}

