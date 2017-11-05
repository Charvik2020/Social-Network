import java.awt.GridLayout;
import java.awt.Button;
import java.awt.TextField;
import java.awt.Label;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;
import java.util.*;

class UserList extends JFrame
{
	JLabel[] l;
	ArrayList<String> fileArray;

	public UserList()
	{
		try
		{
			File folder = new File("/home/ayam/DSA_Project/Files");
	
			if(folder.isDirectory())
			{
				File[] listOfFiles = folder.listFiles();
				fileArray = new ArrayList<String>();
				for(int i = 0; i < listOfFiles.length; i++)
				{
					if(listOfFiles[i].isFile())
					{
						fileArray.add(listOfFiles[i].getName());
					}
				}
			}
			
			l = new JLabel[fileArray.size()];
			for(int i = 0; i < fileArray.size(); i++)
			{
				if(!fileArray.get(i).equals("admin"))
				{
					l[i] = new JLabel(fileArray.get(i)+"");
					add(l[i]);
				}
			}
			setLayout(new GridLayout((fileArray.size() - 1),1));	
		}
		catch(Exception err)
		{
			err.printStackTrace();
		}
	}
	
	public static void main()
	{
		UserList ul = new UserList();
		ul.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		ul.setSize(300,150);
		ul.setVisible(true);
	}
}
