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

class PendingRequests extends JFrame implements ActionListener
{
	JLabel[] l;
	JButton[] b1, b2;
	static String tempuser;
	boolean[][] amat;
	static ArrayList<String> myList, fileArray;
	
	public PendingRequests(String username)
	{
		tempuser = username;
		try
		{

			File file = new File("/home/ayam/DSA_Project/ReqFiles/"+tempuser+"");
			BufferedReader br = null;
			br = new BufferedReader(new FileReader(file));
			String line = br.readLine();
			
			myList = new ArrayList<String>(Arrays.asList(line.split(",")));

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

			readMatrix();	

			l = new JLabel[myList.size()];
			b1 = new JButton[myList.size()];
			b2 = new JButton[myList.size()];

			for(int i = 0; i < myList.size(); i++)
			{
				l[i] = new JLabel(myList.get(i)+"");
				add(l[i]);
				b1[i] = new JButton("Yes");
				b2[i] = new JButton("No");
				add(b1[i]);
				add(b2[i]);
				b1[i].addActionListener(this);
				b2[i].addActionListener(this);
			}
			setLayout(new GridLayout(myList.size(),3));
			setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		}

		catch(Exception err)
		{
			System.out.println("No pending requests");
		}
	}

	public void actionPerformed(ActionEvent e)
	{
		JButton src = (JButton)e.getSource();

		for(int i = 0; i < myList.size(); i++)
		{
			if(src == b1[i])
			{
				try 
				{
					File file1 = new File("/home/ayam/DSA_Project/ReqFiles/"+tempuser+"");
					BufferedReader br = new BufferedReader(new FileReader(file1));
					File file2 = new File("/home/ayam/DSA_Project/ReqFiles/temp");
					FileWriter fw = new FileWriter(file2,true);
					BufferedWriter bw = new BufferedWriter(fw);

					myList.remove(i);

					addEdges(fileArray.indexOf(tempuser), fileArray.indexOf(l[i].getText()));
					storeMatrix(fileArray.size());					
					showMatrix(fileArray.size());	
						
					for(int j = 0; j < myList.size(); j++)
					{
						fw.append(myList.get(j)+",");
					}
					bw.close();
					file1.delete();
					file2.renameTo(file1);
				}
				catch(Exception err)
				{
					err.printStackTrace();	
				}	
			}

			else if(src == b2[i])
			{
				try 
				{
					File file1 = new File("/home/ayam/DSA_Project/ReqFiles/"+tempuser+"");
					BufferedReader br = new BufferedReader(new FileReader(file1));
					File file2 = new File("/home/ayam/DSA_Project/ReqFiles/temp");
					FileWriter fw = new FileWriter(file2,true);
					BufferedWriter bw = new BufferedWriter(fw);

					myList.remove(i);
		
					for(int j = 0; j < myList.size(); j++)
					{
						fw.append(myList.get(j)+",");
					}
					bw.close();
					file1.delete();
					file2.renameTo(file1);
				}
				catch(Exception err)
				{
					err.printStackTrace();	
				}
			}
		}
	}

	public void addEdges(int i, int j)
	{
		readMatrix();
		if(i >= 0 && j >= 0)
		{
			amat[i][j] = true;
			amat[j][i] = true;
		}
	}

	public void showMatrix(int size)
	{
		for(int i = 0; i < size; i++)
		{
			for(int j = 0; j < size; j++)
			{
				System.out.print(amat[i][j]+"\t");
			}
			System.out.println("");
		}	
	}

	public void storeMatrix(int size)
	{
		try
		{
			File file  = new File("Graph");
			FileWriter fw = new FileWriter(file, false);
			BufferedWriter bw = new BufferedWriter(fw);

			for(int i = 0; i < size; i++)
			{
				for(int j = 0; j < size; j++)
				{
					bw.write(amat[i][j]+"\t");
				}
				bw.newLine();
			}
			bw.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

	public boolean[][] readMatrix()
	{
		try
		{	
			File file = new File("Graph");
			BufferedReader br = null;
			br = new BufferedReader(new FileReader(file));

			String line;
			int row = 0;
		
			while((line = br.readLine()) != null)
			{
				String[] vals = line.split("\t");
				if(amat == null)
				{
					amat = new boolean[vals.length][vals.length];
				}				
				for(int col = 0; col < vals.length; col++)
				{
					amat[row][col] = Boolean.parseBoolean(vals[col]);
				}
				row++;
			}
		
		}
		catch(Exception err)
		{
			err.printStackTrace();
		}		
		
		return amat;
	}
	
	public static void main()
	{
		PendingRequests pr = new PendingRequests(tempuser);
		pr.setSize(300,200);			//setting size of the window->setSize(x-axis,y-axis)
    		pr.setTitle("Accept Requests?");		//Title for GUI window
		pr.setVisible(true);
	}
}
