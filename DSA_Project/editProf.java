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
import java.text.NumberFormat;

class editProf extends JFrame implements ActionListener
{
	static String temp_username;	
	
	Label l1=new Label("Name");			//creating labels and text fields
  	Label l2=new Label("Email-id");
	Label l3=new Label("Contact Number");
  	Label l4=new Label("Birthday");
	Label l5 = new Label(" ");

	//NumberFormat numberFormat = NumberFormat.getIntegerInstance();
	DateFormat format = new SimpleDateFormat("dd/MM/yyyy"); 

  	TextField t1=new TextField();
  	TextField t2=new TextField();
	TextField t3=new TextField();
	JFormattedTextField t4=new JFormattedTextField(format);

 	Button b= new Button("Done");			//creating button

	public editProf(String user_id)
	{
		setLayout(new GridLayout(6,2));		//setting the layout of the window->GridLayout(rows,columns)
		temp_username = user_id;	
		
		add(l1);				//adding labels and text fields to the frame
    		add(t1);
    		add(l2);
    		add(t2);
    		add(l3);
		add(t3);
		add(l4);
		add(t4);
		add(l5);		
	
		add(b);					//adding button to the frame

    		b.addActionListener(this);
    		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);	//disposes the frame when close(x) button is clicked	
	}

	public void actionPerformed(ActionEvent e)
  	{ 
		editData();		//calls editData function when Done button is clicked
	}

	public void editData()
	{
		String fname = t1.getText();		//storing text fields in different strings
		String email_id = t2.getText();
		String contact = t3.getText();
		String dob = t4.getText();

		try
		{
			File file = new File("/home/ayam/DSA_Project/Files/"+temp_username+"");	//searches for the file
			BufferedReader br = null;
			br = new BufferedReader(new FileReader(file));
			
			StringBuilder sb = new StringBuilder();	//if found then the data will be stored in a string
			String line = br.readLine();	
			String[] string1 = line.split(",");	//that string is converted to string array by splitting the string
		
			FileWriter fw = new FileWriter("/home/ayam/DSA_Project/Files/"+temp_username+"");
			BufferedWriter bw = new BufferedWriter(fw);
			
			if(fname.length() != 0 && email_id.length() == 0 && contact.length() == 0 && dob.length() == 0)
			{
				fw.write(fname+","+temp_username+","+string1[2]+","+string1[3]+","+string1[4]+","+string1[5]+",-");	
				bw.close();	//overwrites the data in the file as we are editing the data and then closes the file 
				setVisible(false);
			}
			else if(fname.length() == 0 && email_id.length() != 0 && contact.length() == 0 && dob.length() == 0)
			{
				fw.write(string1[0]+","+temp_username+","+email_id+","+string1[3]+","+string1[4]+","+string1[5]+",-");	
				bw.close();	//overwrites the data in the file as we are editing the data and then closes the file 
				setVisible(false);
			}
			else if(fname.length() == 0 && email_id.length() == 0 && contact.length() != 0 && dob.length() == 0)
			{
				fw.write(string1[0]+","+temp_username+","+string1[2]+","+string1[3]+","+contact+","+string1[5]+",-");	
				bw.close();	//overwrites the data in the file as we are editing the data and then closes the file 
				setVisible(false);
			}
			else if(fname.length() == 0 && email_id.length() == 0 && contact.length() == 0 && dob.length() != 0)
			{
				fw.write(string1[0]+","+temp_username+","+string1[2]+","+string1[3]+","+string1[4]+","+dob+",-");	
				bw.close();	//overwrites the data in the file as we are editing the data and then closes the file 
				setVisible(false);
			}
			else if(fname.length() != 0 && email_id.length() != 0 && contact.length() == 0 && dob.length() == 0)
			{
				fw.write(fname+","+temp_username+","+email_id+","+string1[3]+","+string1[4]+","+string1[5]+",-");	
				bw.close();	//overwrites the data in the file as we are editing the data and then closes the file 
				setVisible(false);
			}
			else if(fname.length() != 0 && email_id.length() == 0 && contact.length() != 0 && dob.length() == 0)
			{
				fw.write(fname+","+temp_username+","+string1[2]+","+string1[3]+","+contact+","+string1[5]+",-");	
				bw.close();	//overwrites the data in the file as we are editing the data and then closes the file 
				setVisible(false);
			}
			else if(fname.length() != 0 && email_id.length() == 0 && contact.length() == 0 && dob.length() != 0)
			{
				fw.write(fname+","+temp_username+","+string1[2]+","+string1[3]+","+string1[4]+","+dob+",-");	
				bw.close();	//overwrites the data in the file as we are editing the data and then closes the file 
				setVisible(false);
			}
			else if(fname.length() == 0 && email_id.length() != 0 && contact.length() != 0 && dob.length() == 0)
			{
				fw.write(string1[0]+","+temp_username+","+email_id+","+string1[3]+","+contact+","+string1[5]+",-");	
				bw.close();	//overwrites the data in the file as we are editing the data and then closes the file 
				setVisible(false);
			}
			else if(fname.length() == 0 && email_id.length() != 0 && contact.length() == 0 && dob.length() != 0)
			{
				fw.write(fname+","+temp_username+","+email_id+","+string1[3]+","+string1[4]+","+dob+",-");	
				bw.close();	//overwrites the data in the file as we are editing the data and then closes the file 
				setVisible(false);
			}
			else if(fname.length() == 0 && email_id.length() == 0 && contact.length() != 0 && dob.length() != 0)
			{
				fw.write(string1[0]+","+temp_username+","+string1[2]+","+string1[3]+","+contact+","+dob+",-");	
				bw.close();	//overwrites the data in the file as we are editing the data and then closes the file 
				setVisible(false);
			}
			else
			{
				fw.write(fname+","+temp_username+","+email_id+","+string1[3]+","+contact+","+dob+",-");	
				bw.close();	//overwrites the data in the file as we are editing the data and then closes the file 

				setVisible(false);		//disposes the frame after the process is done
			}
		}
	
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

	public static void main()
	{
		editProf ep = new editProf(temp_username);
    		ep.setSize(500,300);			//setting size of the window->setSize(x-axis,y-axis)
    		ep.setTitle("Edit Profile");		//Title for GUI window
    		ep.setVisible(true);			//setting frame to be visible
	}
}

