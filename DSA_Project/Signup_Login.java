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

class Signup_Login extends JFrame implements ActionListener		//main class
{
	private JButton signup;			//button declaration for signup
	private JButton login;			//button declaration for login
	

	Signup su;				//instantiating the Signup class
	Login ln;				//instantiating the Login class
	
	public Signup_Login()			//constructor
	{
		super("HRU");			//Title for GUI window
		setLayout(new GridLayout(2,1));	//setting layout type of the window

		signup = new JButton("Signup");	//creating JButton and naming it "Signup"
		add(signup);			//add the button to the frame
	
		login = new JButton("Login");	//creating JButton and naming it "Login"
		add(login);			//add the button to the frame

		signup.addActionListener(this);	//performs certain operation when the Signup button is clicked
		login.addActionListener(this);	//performs certain operation when the Login button is clicked
	}

	public void actionPerformed(ActionEvent event)
	{
		if(event.getSource() == signup)	//if Signup button is clicked then 
		{	
			su = new Signup();	//it will call the constructor of the Signup class and main function of that class
			su.main();
		}

		else if(event.getSource() == login)	//if Login button is clicked then
		{
			ln = new Login();	//it will call the constructor of the Login class and main function of that class
			ln.main();  
		}

	}
	
	public static void main()
	{
		Signup_Login go = new Signup_Login();	
		go.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);	//Exits the whole program when close(x) button is clicked
		go.setSize(250,150);		//setting size of the window->setSize(x-axis,y-axis)
		go.setVisible(true);		//setting frame to be visible
	}
}

class Signup extends JFrame implements ActionListener	
{ 
	Label l1=new Label("Name");		//Creating labels
  	Label l2=new Label("Username");
	Label l3=new Label("Email-id");
	Label l4=new Label("Password");
	Label l5=new Label("Contact Number");
  	Label l6=new Label("Birthday");
	Label l7 = new Label(" ");
	int row, col;
	boolean[][] amat;

	//NumberFormat numberFormat = NumberFormat.getInstance();
	DateFormat format = new SimpleDateFormat("dd/MM/yyyy");  	
	TextField t1=new TextField();		//Creating text fields
  	TextField t2=new TextField();
	TextField t3=new TextField();
	TextField t4=new TextField();
	TextField t5=new TextField();
	JFormattedTextField t6=new JFormattedTextField(format);
	

 	Button b= new Button("Submit");		//Button for submit

 	public Signup()
  	{ 
		setLayout(new GridLayout(8,2));	//setting the layout of the window->GridLayout(rows,columns)
		add(l1);			//adding the labels to the frame
    		add(t1);			//adding the text fields to the frame
    		add(l2);
    		add(t2);
    		add(l3);
		add(t3);
		add(l4);
		add(t4);
		add(l5);
		add(t5);
		add(l6);
		add(t6);
		add(l7);		
	
		add(b);				//adding the button to the frame

    		b.addActionListener(this);	
    		t4.setEchoChar('*');		//will show the '*' in the text field instead of text
    		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);	//Disposes the frame when close(x) button is clicked 
  	}

  	public void actionPerformed(ActionEvent e)
  	{ 
		storeData();		//calls the function storeData when Submit button is clicked
	}

	public void storeData()
	{
		String fname = t1.getText();		//storing the text fields in different strings
		String username = t2.getText();
		String email = t3.getText();
		String passwd = t4.getText();
		String contact = t5.getText();
		String dob = t6.getText();

		try
		{
			if(fname.length() == 0 || username.length() == 0 || email.length() == 0 || passwd.length() == 0 || contact.length() == 0 || dob.length() == 0)
			{
				l7.setText("All the fields are mandatory!");	
			}
			else			
			{
				File file = new File("/home/ayam/DSA_Project/Files/"+username+"");	
			
				if(!file.exists())		//checks whether the file with the same username exists or not
				{
					FileWriter fw = new FileWriter(file);	//if doesn't exist then it will create a file 
					BufferedWriter bw = new BufferedWriter(fw);	//and store the basic info in that file
				
					fw.write(fname+","+username+","+email+","+passwd+","+contact+","+dob+",-");
					bw.close();		//closes the file

					readMatrix();

					setVisible(false);	//as signup is successful, it will dispose the window

					postlogin pl = new postlogin(email);	//calling the constructor of postlogin class 
					pl.main();				//and its main function
				}
			
				else				//if file exists then it will show that user exists and will tell to change the username
				{
					l7.setText("This user exists! Please use different username!");
				}
			}
		}
	
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

	public void readMatrix()
	{
		try
		{	
			File file = new File("Graph");
			BufferedReader br = null;
			br = new BufferedReader(new FileReader(file));

			String[] vals = null;
			String line;
			row = 0;	
		
			while((line = br.readLine()) != null)
			{
				vals = line.split("\t");
				if(amat == null)
				{
					amat = new boolean[vals.length + 1][vals.length + 1];
				}				
				for(col = 0; col < vals.length; col++)
				{
					amat[row][col] = Boolean.parseBoolean(vals[col]);
				}
				row++;
				amat[vals.length][vals.length] = true;
			}
			storeMatrix(vals.length + 1);
		}
		catch(Exception err)
		{
			err.printStackTrace();
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

	public static void main()
  	{ 
		Signup l=new Signup();
    		l.setSize(500,300);		//setting size of the window->setSize(x-axis,y-axis)
    		l.setTitle("Signup");		//Title for GUI window
    		l.setVisible(true);		//setting frame to be visible
  	}
}

class Login extends JFrame implements ActionListener
{ 
	Label l1=new Label("Username");		//creating labels 
  	Label l2=new Label("Password");
  	Label l3=new Label(" ");
  	TextField t1=new TextField();		//creating text fields
  	TextField t2=new TextField();
 	Button b= new Button("Login");		//creating button "Login"

 	public Login()
  	{ 
		setLayout(new GridLayout(3,2));	//setting the layout of the window->GridLayout(rows,columns)
		add(l1);			//adding labels and text fields to the frame
    		add(t1);
    		add(l2);
    		add(t2);
    		add(b);				//adding button to the frame
    		add(l3);
    		b.addActionListener(this);
    		t2.setEchoChar('*');		//will show the '*' in the text field instead of text
    		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);	//disposes the frame when close(x) button is clicked
  	}

  	public void actionPerformed(ActionEvent e)
  	{ 
		showData();		//calls showData function when Login button is clicked
	}

	public void showData()
	{
		String username = t1.getText();		//storing the values of text fields in different strings
		String passwd = t2.getText();

		try
		{
			File file = new File("/home/ayam/DSA_Project/Files/"+username+"");	
			BufferedReader br = null;		
			br = new BufferedReader(new FileReader(file));
			
			if(file.exists())	//checks if the file exists or not
			{
				StringBuilder sb = new StringBuilder();	//if exists then reads the file 
				String line = br.readLine();		//and stores the data of the file in a string
				String[] string1 = line.split(",");	//then the string is splitted by "," and stored in a string array

				
				if(passwd.equals(string1[3]) && string1[6].equals("-"))		//if the passwd textfield and the data at the 3rd index of the string array matches
				{
					postlogin pl = new postlogin(username);	//then the login is successful and will call constructor
					pl.main();		//and main function of the login class
					setVisible(false);	//and then disposes the frame
				}
				else if((!passwd.equals(string1[3]) && string1[6].equals("-")) || (!passwd.equals(string1[3]) && string1[6].equals("*")))		//if not then prints the below statement
				{
					l3.setText("Incorrect email-id or password");
				}
			
				else if(passwd.equals(string1[3]) && string1[6].equals("*"))
				{
					l3.setText("You are banned by admin");
				}
			}
		}
	
		catch(Exception e)
		{
			l3.setText("No such user found!");
		}
	}

	public static void main()
  	{ 
		Login l=new Login();
    		l.setSize(400,200);		//setting size of the window->setSize(x-axis,y-axis)
    		l.setTitle("Login");		//Title for GUI window
    		l.setVisible(true);		//setting frame to be visible
  	}
}

