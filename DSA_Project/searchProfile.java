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

class searchProfile extends JFrame implements ActionListener
{
	Label l1 = new Label("Search For:");	//creating labels and text fields
	TextField t1 = new TextField();
	Label l2 = new Label(" ");
	Button b = new Button("Go!");		//creating button
	static String temp_usrnm;

	public searchProfile(String username)
	{
		temp_usrnm = username;
		setLayout(new GridLayout(3,2));		//setting the layout of the window->GridLayout(rows,columns)
		add(l1);				//adding labels and text fields to the frame
    		add(t1);
		
		add(b);					//adding button to the frame
		add(l2);	

		b.addActionListener(this);
    		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);	//disposes the frame when close(x) button is clicked
	}

	public void actionPerformed(ActionEvent e)
  	{ 
		search();		//calls the search function when Go button is clicked
	}

	public void search()
	{
		WordTree wt = new WordTree();
		String searchfile = t1.getText();
		File folder = new File("/home/ayam/DSA_Project/Files");		//listOfFiles[i].getName() to get the name of file
		File[] listOfFiles = folder.listFiles();
		
    		for (int i = 0; i < listOfFiles.length; i++) 
		{
			wt.addWord(listOfFiles[i].getName());			//all file names added to the ArrayList
    		}

		List<String> wds = wt.getWordsForPrefix(searchfile);	//gets the words starting with the entered prefix and stores it in a List
		String[] temp = wds.toArray(new String[0]);	//converts the list into String array	
	
		if(wds.isEmpty())		//if the List is empty then it will show that "No users found!" 
		{
			l2.setText("No users found!");
		}	
		else			//else it will call constructor and the main function of search_results class
		{	
			search_results sr = new search_results(temp, temp.length, temp_usrnm);
			sr.main();
		}
		
		
	}
	
	public static void main()
	{
		searchProfile sp = new searchProfile(temp_usrnm);
    		sp.setSize(300,150);			//setting size of the window->setSize(x-axis,y-axis)
    		sp.setTitle("Search Profile");		//Title for GUI window
    		sp.setVisible(true);			//setting frame to be visible
	}
}

class Node 
{    
	private final char ch;
	private boolean end;

    	private LinkedList<Node> children;

    	public Node(char ch) 			//constructor
	{
        	this.ch = ch;
    	}

    	public void addChild(Node node) 	//add child node to the parent node
	{
        	if (children == null) 
		{
            		children = new LinkedList<Node>();	//if no children found then it will create a LinkedList 
        	}
        	children.add(node);		//and then add that node to the LinkedList
    	}

    	public Node getNode(char ch) 		
	{
        	if (children == null) 		//if no children found
		{
            		return null;		//then it will return null
        	}
        	for(Node child : children) 	//runs until children is null
		{
            		if(child.getChar() == ch) 	//if there is any character in the child node 
			{	
                		return child;	//then it will return child node
            		}
        	}
      	  	return null;
    	}

    	public char getChar() 
	{
        	return ch;		//returns the character 
    	}

    	public List<Node> getChildren() 
	{
        	if (this.children == null) 	//if children is null then the list is empty
		{
            		return Collections.emptyList();
        	}
        	return children;
    	}

    	public boolean isEnd() 
	{
        	return end;
    	}

    	public void setEnd(boolean end) 
	{
        	this.end = end;
    	}
}

class WordTree
{
	Node root = new Node(' ');		//defining root node

	public List<String> getWordsForPrefix(String prefix) 
	{
    		if (prefix.length() == 0) 	//if there is no string in the arguement of the function 
		{
        		return Collections.emptyList();	//then it will return that list is empty
    		}
    		
		Node node = getNodeForPrefix(root, prefix);
    		if (node == null) 	
		{
        		return Collections.emptyList();
    		}
    
		List<LinkedList<Character>> chars = collectChars(node);
    		List<String> words = new ArrayList<String>(chars.size());

    		for (LinkedList<Character> charList : chars) 
		{
        		words.add(combine(prefix.substring(0, prefix.length() - 1), charList));
    		}
    		
		return words;
	}


	private String combine(String prefix, List<Character> charList) 
	{
    		StringBuilder sb = new StringBuilder(prefix);
    		for (Character character : charList) 
		{
        		sb.append(character);
    		}
    		return sb.toString();
	}


	private Node getNodeForPrefix(Node node, String prefix) 
	{
    		if (prefix.length() == 0) 
		{
        		return node;
    		}
    
		Node next = node.getNode(prefix.charAt(0));
    		if (next == null) 
		{
        		return null;
    		}
    
		return getNodeForPrefix(next, prefix.substring(1, prefix.length()));
	}


	private List<LinkedList<Character>> collectChars(Node node) 
	{
    		List<LinkedList<Character>> chars = new ArrayList<LinkedList<Character>>();	//creates a List which contains LinkedList of type char

    		if(node.getChildren().size() == 0) 
		{
        		chars.add(new LinkedList<Character>(Collections.singletonList(node.getChar())));
    		} 
		else 
		{
        		if (node.isEnd()) 
			{
            			chars.add(new LinkedList<Character>(Collections.singletonList(node.getChar()))); //if end is found then it give the final result i.e the name
        		}
        		
			List<Node> children = node.getChildren();
        		for (Node child : children) 
			{
            			List<LinkedList<Character>> childList = collectChars(child);
            			for (LinkedList<Character> characters : childList) 
				{
                			characters.push(node.getChar());	//firstly the character is added to the linkedlist
                			chars.add(characters);		//and then to the list
            			}	
        		}			//So if any name starts with the entered prefix will be stored in the list 
    		}
    		return chars;
	}


	public void addWord(String word) 
	{
    		addWord(root, word);		//calls addWord function having two arguements
	}

	private void addWord(Node parent, String word) 
	{
    		if(word.trim().length() == 0) 
		{
        		return;
    		}
    
		Node child = parent.getNode(word.charAt(0));
    		if (child == null) 
		{
        		child = new Node(word.charAt(0));	//if child is null then adds the character to the child node
        		parent.addChild(child);
    		}	
    		if(word.length() == 1) 
		{
        		child.setEnd(true);		//if word contains only 1 character then the child is set as the end
    		} 
		else
		{
        		addWord(child, word.substring(1, word.length()));	//if word is of length more than 1 then it will again call the addWord function having two arguements in which the string will have all the characters after trimming the word. Everytime this function trims the string from the left  
    		}
	}
}
