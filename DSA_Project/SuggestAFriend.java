import java.awt.GridLayout;
import java.awt.Button;
import java.awt.TextField;
import java.awt.Label;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;
import java.util.*;

class SuggestAFriend extends JFrame implements ActionListener
{
	JLabel[] l;
	JLabel l2 = new JLabel(" ");
	JButton[] b1;
	static boolean[][] matrix = null;			//matrix for reading from the file 
	static String tempuser, temp;				//temporary string
	static ArrayList<String> fileArray, suggestion;		//fileArray for storing name of files and suggestion for storing names which are to be suggested
	static String[] vals;

	public SuggestAFriend(String username)			//constructor
	{
		tempuser = username;
		try
		{
			File folder = new File("/home/ayam/DSA_Project/Files");	//opening folder and counting number of files
		
			if(folder.isDirectory())
			{
				File[] listOfFiles = folder.listFiles();		//stores the files in file array
				fileArray = new ArrayList<String>();			
				for(int i = 0; i < listOfFiles.length; i++)
				{
					if(listOfFiles[i].isFile())
					{
						fileArray.add(listOfFiles[i].getName());	//stores the name of the file in th e arraylist
					}
				}
			}
			
			suggestion = new ArrayList<String>();

		 	suggest();			//calling suggest function
			
			l = new JLabel[suggestion.size()];	//creating label array and initializing the size of the label array
			b1 = new JButton[suggestion.size()];	//creating button array and initializing the size of the button array
			for(int i = 0; i < suggestion.size(); i++)
			{
				if(suggestion.get(i) == null)
				{}
				else
				{
					temp = suggestion.get(i);	//storing the name of suggested user in a temporary string			
					l[i] = new JLabel(suggestion.get(i)+"");	//label with the suggested name
					b1[i] = new JButton("Send Friend Request");	//button to send friend request
					add(l[i]);		//adding the label and button to the window
					add(b1[i]);
					b1[i].addActionListener(this);
				}
			}
		
			setLayout(new GridLayout(fileArray.size(),2));
			setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);	//Disposes window on clicking close button
		}
		catch(Exception err)
		{
			err.printStackTrace();
		}
		
	}

	public void actionPerformed(ActionEvent event)
	{	
		JButton src = (JButton)event.getSource();

		for(int i = 0; i < suggestion.size(); i++)
		{
			if(src == b1[i])	//if send button is clicked then a friend request is sent to the user
			{
				FriendRequest fr = new FriendRequest(tempuser, temp);
				suggestion.remove(i);		//removing the name of the suggested user from the arraylist
				l2.setText("Friend Request sent!");
			}
		}
	}

	public void suggest()
	{
		read();			//calling read function

		for(int i = 0; i < fileArray.size(); i++)	
		{
			if(fileArray.get(i).equals(tempuser))		//checks if the username and the name in the arraylist matches
			{
				int[] index = new int[fileArray.size()];
				for(int j = 0; j < fileArray.size(); j++)	//if matches then checks for "true" in that row 
				{
					if(matrix[i][j] == true && i != j)
					{
						for(int k = 0; k < fileArray.size(); k++)	//goes to the jth row and checks for "true" that satisfies the below if condition
						{
							if(matrix[j][k] == true && j != k && k != i && matrix[i][k] == false)
							{
								//System.out.println(fileArray.get(k)+" ");
								suggestion.add(fileArray.get(k));	//adds the name corresponding to that index number which is in fileArray, to suggestion arraylist		
							}				
						}
					}
				}
			}
		}
	}

	public void read()
	{
		try
		{
			File file = new File("Graph");		//searches for Graph file and opens it
			BufferedReader br = null;
			br = new BufferedReader(new FileReader(file));

			String line;
			int row = 0;
		
			while((line = br.readLine()) != null)	//reads from the file till the end of the file
			{
				vals = line.split("\t");	//splits the line string and stores it in string array
				if(matrix == null)
				{
					matrix = new boolean[vals.length][vals.length];	//initializing the matrix size
				}				
				for(int col = 0; col < vals.length; col++)
				{
					matrix[row][col] = Boolean.parseBoolean(vals[col]);	//reads from the file and stores it in matrix 			
				}
				row++;		//runs for reading every row from the file
			}
		
		}
		catch(Exception err)
		{
			err.printStackTrace();
		}		
		
	}

	public static void main()
	{
		SuggestAFriend sf= new SuggestAFriend(tempuser);
		sf.setSize(300,200);			//setting size of the window->setSize(x-axis,y-axis)
    		sf.setTitle("Suggest friends");		//Title for GUI window
		sf.setVisible(true);
	}

}
