import java.awt.GridLayout;
import java.awt.Button;
import java.awt.TextField;
import java.awt.Label;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;
import java.util.*;

class AdminLogin extends JFrame implements ActionListener
{
	private JButton userlist, banuser, banneduser;
	public AdminLogin()
	{
		setLayout(new GridLayout(3,1));	//setting the layout of the window->GridLayout(rows,columns)
	
		userlist = new JButton("List of users");	
		add(userlist);			

		banuser = new JButton("Ban user");	 
		add(banuser);

		banneduser = new JButton("Banned user");
		add(banneduser);

		userlist.addActionListener(this);
		banuser.addActionListener(this);
		banneduser.addActionListener(this);
	}
	
	public void actionPerformed(ActionEvent event)
	{
		if(event.getSource() == userlist)
		{
			UserList ul = new UserList();
			ul.main();
		}

		else if(event.getSource() == banuser)
		{
			BanUsers bu = new BanUsers();
			bu.main();
		}
		
		else if(event.getSource() == banneduser)
		{
			BannedUsers bdu = new BannedUsers();
			bdu.main();
		}
	}
	public static void main()
	{
		AdminLogin al = new AdminLogin();
		al.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);	//disposes the frame when close(x) button is clicked
		al.setSize(250,150);		//setting size of the window->setSize(x-axis,y-axis)
		al.setVisible(true);		//setting frame to be visible
	}
}
