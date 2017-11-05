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

class postlogin extends JFrame implements ActionListener
{
	private JButton edit, pwd, crpage, search, requests, suggestfriend, logout, deleteAcc; 
	static String temp_username;
	public postlogin(String user_name)
	{
		setLayout(new GridLayout(4,2));	//setting the layout of the window->GridLayout(rows,columns)

		temp_username = user_name;	//storing username in a temporary string
		
		edit = new JButton("Edit My Profile");	//creating button for edit
		add(edit);			//adding the button to the frame

		pwd = new JButton("Change Password");	//creating button for pwd 
		add(pwd);			//adding the button to the frame

		crpage = new JButton("Create a Page");	//creating button for crpage
		add(crpage);			//adding the button to the frame

		search = new JButton("Search");	//creating button for searchprof	
		add(search);		//adding the button to the frame

		requests = new JButton("Friend Requests");	//creating button for requests
		add(requests);			//adding the button to the frame

		suggestfriend = new JButton("Suggest Friend");	//creating button for suggestfriend
		add(suggestfriend);		//adding the button to the frame
	
		logout = new JButton("Logout");		//creating button for logout
		add(logout);			//adding the button to the frame

		deleteAcc = new JButton("Delete Account");	//creating button for deleteAcc
		add(deleteAcc);			//adding the button to the frame

		edit.addActionListener(this);
		pwd.addActionListener(this);
		crpage.addActionListener(this);
		search.addActionListener(this);
		requests.addActionListener(this);
		suggestfriend.addActionListener(this);
		logout.addActionListener(this);
		deleteAcc.addActionListener(this);
	}

	public void actionPerformed(ActionEvent event)
	{
		if(event.getSource() == edit)		//if edit button is clicked 
		{	
			editProf ep = new editProf(temp_username);
			ep.main();		//then the constructor and main function of the editProf class will be called
		}

		else if(event.getSource() == pwd)	//if pwd button is clicked
		{
			changePWD cpd = new changePWD(temp_username);
			cpd.main();		//then the constructor and the main function of the changePWD class will be called
		}

		else if(event.getSource() == crpage)	//if crpage button is clicked
		{
			createPage cp = new createPage(temp_username);
			cp.main();		//then the constructor and the main function of the createPage class will be called
		}
		
		else if(event.getSource() == search)	//if searchprof button is clicked
		{
			Search sh = new Search(temp_username);
			sh.main();		//then the constructor and the main function of the searchProfile class will be called
		}
		
		else if(event.getSource() == requests)		//if requests button is clicked
		{
			PendingRequests pr = new PendingRequests(temp_username);
			pr.main();		//then the constructor and the main function of PendingRequests class will be called
		}

		else if(event.getSource() == suggestfriend)	//if suggestfriend button is clicked
		{
			SuggestAFriend sf = new SuggestAFriend(temp_username);
			sf.main();		//then the constructor and the main function of SuggestAFriend class will be called 
		}
	
		else if(event.getSource() == logout)	//if logout button is clicked
		{	
			setVisible(false);		//then the window is closed and user logs out from the account
		}
		
		else if(event.getSource() == deleteAcc)	//if deleteAcc button is clicked
		{
			int dialogButton = JOptionPane.showConfirmDialog(null,"Do you want to delete the account?", null, JOptionPane.YES_NO_OPTION);			//then a popup window comes up asking if user wants to delete the account or not
			if(dialogButton == JOptionPane.YES_OPTION)
			{
				DeleteAcc da = new DeleteAcc(temp_username);	//if clicked on yes then account is deleted
				setVisible(false);
			}
			if(dialogButton == JOptionPane.NO_OPTION)
			{}			//if clicked on no then returned to previous window
		}
	}

	public static void main()
	{
		postlogin pl = new postlogin(temp_username);
		pl.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);	//disposes the frame when close(x) button is clicked
		pl.setSize(400,200);		//setting size of the window->setSize(x-axis,y-axis)
		pl.setVisible(true);		//setting frame to be visible
	}
}

