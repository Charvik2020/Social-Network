import java.awt.GridLayout;
import java.awt.Button;
import java.awt.TextField;
import java.awt.Label;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;
import java.util.*;

class BannedUsers extends JFrame implements ActionListener
{
	JLabel[] l;
	JButton[] b1;
	ArrayList<String> myList;
	String[] arr = null;

	public BannedUsers()
	{
		try
		{
			File file = new File("/home/ayam/DSA_Project/DSAProject/bannedusers");
			BufferedReader br = null;
			br = new BufferedReader(new FileReader(file));

			StringBuilder sb = new StringBuilder();	//if exists then reads the file 
			String line = br.readLine();		//and stores the data of the file in a string
			arr = line.split(" ");	//then the string is splitted by " " and stored in a string array
		
			l = new JLabel[arr.length];
			b1 = new JButton[arr.length];
			for(int i = 0; i < arr.length; i++)
			{
				l[i] = new JLabel(arr[i]+"");
				add(l[i]);
				b1[i] = new JButton("Remove Ban");
				add(b1[i]);
				b1[i].addActionListener(this);
			}
			setLayout(new GridLayout(5,3));	
		}
		catch(Exception err)
		{
			err.printStackTrace();
		}
	}

	public void actionPerformed(ActionEvent event)
	{
		JButton src = (JButton)event.getSource();

		for(int i = 0; i < arr.length; i++)
		{
			if(src == b1[i])
			{
				try
				{	
					File file1 = new File("/home/ayam/DSA_Project/Files/"+arr[i]+"");
					BufferedReader br = null;
					br = new BufferedReader(new FileReader(file1));

					StringBuilder sb = new StringBuilder();	//if exists then reads the file 
					String line = br.readLine();		//and stores the data of the file in a string
					String[] string1 = line.split(",");	//then the string is splitted by "," and stored in a string array
					
					File file = new File("/home/ayam/DSA_Project/Files/"+arr[i]+"");
					FileWriter fw = new FileWriter(file);
					BufferedWriter bw = new BufferedWriter(fw);
					
					string1[6] = "-";

					fw.write(string1[0]+","+string1[1]+","+string1[2]+","+string1[3]+","+string1[4]+","+string1[5]+","+string1[6]);
					bw.close();
	
					myList = new ArrayList<String>();
					for(int j = 0; j < arr.length; j++)
					{
						myList.add(arr[j]);				
					}

					myList.remove(i);
		
					File file2 = new File("/home/ayam/DSA_Project/DSAProject/bannedusers");
	
					File file3 = new File("/home/ayam/DSA_Project/DSAProject/temp");
					FileWriter fw1 = new FileWriter(file3,true);
					BufferedWriter bw1 = new BufferedWriter(fw1); 

					for(int k = 0; k < myList.size(); k++)
					{
						fw1.append(myList.get(k)+" ");					
					}
					bw1.close();

					file2.delete();
					file3.renameTo(file2);
				}
				catch(Exception err)
				{
					err.printStackTrace();
				}
			}
		}
	}
		
	public static void main()
	{
		BannedUsers bu = new BannedUsers();
		bu.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		bu.setSize(300,200);
		bu.setVisible(true);
	}
}
