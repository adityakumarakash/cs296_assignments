package cs296MovieAnalysis;

import java.io.*;
import java.util.*;

/**
 * this class is meant to read a text file into a vector of string
 */
public class ReadTextFile
{
	// this class reads a text file into an string vector
	/**
	 * this variable stores the read file
	 */
	protected Vector<String> text_doc;
	
	// initializes the data into text_doc
	/**
	 * This constructor initializes the members and calls the readdata function
	 */
	public ReadTextFile(String filename)
	{
		text_doc=new Vector<String>();
		readData(filename);
	}
	
	/**
	 * This fucntion reads the data from the given file name
	 */
	private void readData(String filename)		// reads and intialize the data from the filename given
	{
		try
		{
			BufferedReader br=new BufferedReader(new FileReader(filename));
			String line;
			while((line=br.readLine())!=null)
			{
				text_doc.add(line);
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	/**
	 * This is a getter method for text_doc
	 */
	public Vector<String> getTextArray()
	{
		return text_doc;
	}
}
