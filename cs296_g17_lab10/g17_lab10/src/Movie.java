package cs296MovieAnalysis;

import java.io.*;
import java.util.*;

/**
 * The class Movie inherits from a class ReadTextFile which is meant to read a textfile.
 * The inheriting is because of the fact that Movie class will alsor ead a movie script.
 */
public class Movie extends ReadTextFile
{
	/**
	 * This variable stores script with null lines removed and lines trimmed
	 */
	private Vector<String> script;  	// script with all null lines removed and the lines trimmed
	/**
	 * The variable title stores the title for movie and the author stores the author of the script
	 */
	private String title, author;		// title and the script author
	/**
	 * The variable ln_no is a temporary variable of usage in this class.
	 */
	private int ln_no;					// a temporary variable storing the number of lines and useful
	
	/**
	 * Constructor for the movie class which initializes the variables.
	 * This also finds out the title and author as well as process the raw data read by the ReadTextFile.
	 * 
	 * @param filename This is the name of the file to be read along with the path
	 */
	public Movie(String filename)
	{
		super(filename);
		ln_no=0;
		script=new Vector<String>(); 
		findTitle();
		findAuthor();
		processReadData();
	}
	
	/**
	 * This function reads the text file line by line and removes empty lines and as well as trims the lines.
	 */
	private void processReadData()		// converts the text_doc into the required script format
	{
		String line;
		int len=text_doc.size();
		for(; ln_no < len; ln_no++)
		{
			line=text_doc.elementAt(ln_no).trim().replaceAll("[ ]+"," ");
			if(!line.isEmpty())
			{
				script.add(line);
			}
		}
	}
	
	/**
	 * This function finds the title of the script and stores in the title variable.
	 */
	private void findTitle()		// internal method to find the title
	{
		String temp;
		for(;; ln_no++)
		{
			temp=text_doc.elementAt(ln_no).trim();
			if(!temp.isEmpty())
			{
				ln_no++;
				title=temp;
				break;
			}
		}
	}
	
	/**
	 * This function finds the author of the script and stores in the author variable.
	 */
	private void findAuthor()		// internal method to find the author
	{
		String temp;
		for(;; ln_no++)
		{
			temp=text_doc.elementAt(ln_no).trim().toLowerCase();
			if(!temp.isEmpty())
			{
				if(temp.matches(".*by.*"))
				{
					ln_no++;
					for(;; ln_no++)
					{
						temp=text_doc.elementAt(ln_no).trim();
						if(!temp.isEmpty())
						{
							ln_no++;
							author=temp;
							break;
						}
					}
					break;
				}
			}
		}
	}
	
	/**
	 * This is a getter method defined to obtain processed script.
	 */
	public Vector<String> getScript()		// getter method for the script variable
	{
		return script;
	}
	
	/**
	 * This function returns the movie title.
	 */
	public String getMovieTitle()		// return the title of the given script
	{
		return title;
	}
	
	/**
	 * This function returns the movie author.
	 */
	public String getMovieAuthor()		// return the author of the given script
	{
		return author;
	}
	
	/**
	 * This function prints the movie title.
	 */
	public void printMovieTitle()		// print the movie title
	{
		System.out.println(title);
	}
	
	/**
	 * This function prints the movie author.
	 */
	public void printMovieAuthor()		// print the movie author
	{
		System.out.println(author);
	}
	
	/**
	 * This function returns all the words in the movie.
	 */
	public String[] getMovieWords()		// returns an array of all movie words not only unique
	{
		String[] toKeep={"mr.","mrs.","dr.","prof.","int.","ext.","cont."};
		Vector<String> words=new Vector<String>();
		for(String temp:text_doc)
		{
			temp=temp.trim().replaceAll("[/()&\"]"," ");
			temp=temp.trim().replaceAll("[.][.]+"," ");
			temp=temp.trim().replaceAll("[ ]+"," ");
			
			String[] temp_arr=temp.split(" ");
			for(String temp1:temp_arr)
			{
				if(StringSupport.isPresent(toKeep, temp1.toLowerCase()))
				{
					words.add(temp1);
				}
				else
				{
					temp1=temp1.replaceAll("([a-z A-Z0-9]*)[.;,?!:=-]?", "$1");
					if(!temp1.isEmpty())
						words.add(temp1);
				}
			}
		}
		int count=words.size();
		String[] wrds=new String[count];
		for(int i=0; i<count; i++)
		{
			wrds[i]=words.elementAt(i);
		}
		
		return wrds;
	} 
	
} // class end
