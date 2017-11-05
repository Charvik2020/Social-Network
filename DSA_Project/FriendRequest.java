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

class FriendRequest
{
	static String tempuser1, tempuser2;
	public FriendRequest(String username1, String username2)
	{
		tempuser1 = username1;
		tempuser2 = username2;

		try
		{
			File file = new File("/home/ayam/DSA_Project/ReqFiles/"+tempuser2);
			FileWriter fw = new FileWriter(file, true);
			BufferedWriter bw = new BufferedWriter(fw);
			BufferedReader br = null;
			br = new BufferedReader(new FileReader(file));

			if(br.readLine() == null)
			{
				fw.write(tempuser1+"");
				bw.close();
			}
			else
			{
				fw.append(","+tempuser1);
				bw.close();
			}
		}
		catch(Exception err)
		{
			err.printStackTrace();		
		}
	}
}


