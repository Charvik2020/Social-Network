import java.awt.GridLayout;
import java.awt.Button;
import java.awt.TextField;
import java.awt.Label;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;
import java.util.*;

class BanUsers extends JFrame implements ActionListener
{
	JLabel[] l;
	JButton[] b1;
	ArrayList<String> fileArray;

	public BanUsers()
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
			b1 = new JButton[fileArray.size()];
			for(int i = 0; i < fileArray.size(); i++)
			{
				if(!fileArray.get(i).equals("admin"))
				{
					l[i] = new JLabel(fileArray.get(i)+"");
					add(l[i]);
					b1[i] = new JButton("Ban");
					add(b1[i]);
					b1[i].addActionListener(this);
				}
			}
			setLayout(new GridLayout((fileArray.size() - 1),2));	
		}
		catch(Exception err)
		{
			err.printStackTrace();
		}
	}

	public void actionPerformed(ActionEvent event)
	{
		JButton src = (JButton)event.getSource();

		for(int i = 0; i < fileArray.size(); i++)
		{
			if(src == b1[i])
			{
				try
				{	
					File file1 = new File("/home/ayam/DSA_Project/Files/"+fileArray.get(i)+"");
					BufferedReader br = null;
					br = new BufferedReader(new FileReader(file1));

					StringBuilder sb = new StringBuilder();	//if exists then reads the file 
					String line = br.readLine();		//and stores the data of the file in a string
					System.out.println(line+"");					
	
					File file = new File("/home/ayam/DSA_Project/Files/"+fileArray.get(i)+"");
					FileWriter fw = new FileWriter(file);
					BufferedWriter bw = new BufferedWriter(fw);
				
					String[] string1 = line.split(",");	//then the string is splitted by "," and stored in a string array
					
					string1[6] = "*";

					fw.write(string1[0]+","+string1[1]+","+string1[2]+","+string1[3]+","+string1[4]+","+string1[5]+","+string1[6]);
					bw.close();

					File file2 = new File("/home/ayam/DSA_Project/DSAProject/bannedusers");
					FileWriter fw1 = new FileWriter(file2,true);
					BufferedWriter bw1 = new BufferedWriter(fw1);					
					
					fw1.append(fileArray.get(i)+" ");
					bw1.close();

					fileArray.remove(i);
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
		BanUsers bu = new BanUsers();
		bu.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		bu.setSize(300,300);
		bu.setVisible(true);
	}
}
