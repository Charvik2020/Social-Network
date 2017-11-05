import java.awt.GridLayout;
import java.awt.Button;
import java.awt.TextField;
import java.awt.Label;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;
import java.util.*;

class DeleteAcc  
{
	public DeleteAcc(String username)
	{
		try
		{
			File file = new File("/home/ayam/DSA_Project/Files/"+username);		//finds the file and opens it
			FileWriter fw = new FileWriter(file,false);		//appending disabled
			BufferedWriter bw = new BufferedWriter(fw);

			fw.write("");				//overwriting the file and making it empty

			file.delete();			//after emptying the file, deletion is done
		}

		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
}
