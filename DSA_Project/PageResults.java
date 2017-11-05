import java.awt.GridLayout;
import java.awt.Button;
import java.awt.TextField;
import java.awt.Label;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;
import java.util.*;

class PageResults extends JFrame implements ActionListener
{
	JLabel[] l1;			//array of labels
	JButton[] b1;			//array of buttons
	static String[] temp;
	static int templength;
	static String tempusr;

	public PageResults(String[] temp1, int length, String username)
	{
		l1 = new JLabel[length];
		b1 = new JButton[length];		//defining the size of button array
				
		templength = length;
		temp = temp1;
		tempusr = username;

		setLayout(new GridLayout(templength,2));	//setting the layout of the window->GridLayout(rows,columns)
			
		for(int i = 0; i < templength; i++)		//creating buttons and adding them to the frame
		{
			l1[i] = new JLabel(temp[i]+"");
			add(l1[i]);
			b1[i] = new JButton("Follow");
			add(b1[i]);
			b1[i].addActionListener(this);
		}
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);	//disposes the frame when close(x) button is clicked
	}

	public void actionPerformed(ActionEvent event)
	{
		JButton src = (JButton)event.getSource();

		for(int i = 0; i < templength; i++)
		{
			if(src == b1[i])
			{
				try
				{
					File file = new File("/home/ayam/DSA_Project/PageFollowers/"+l1[i].getText()+"");
					FileWriter fw = new FileWriter(file,true);
					BufferedWriter bw = new BufferedWriter(fw);

					if(file.exists())
					{
						fw.append(","+tempusr);
						bw.close();
					}
			
					else
					{
						fw.write(tempusr+"");
						bw.close();		
					}
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
		PageResults pr = new PageResults(temp,templength,tempusr);
		pr.setSize(300,200);
		pr.setVisible(true);
	}
}
