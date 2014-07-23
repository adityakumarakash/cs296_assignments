package cs296MovieAnalysis;

import java.io.*;
import java.util.*;


/**
 * This class finds the genre of the movie from the script.
 */
public class FindGenre 
{
	// this class decides the genre of the given script
	/**
	 * This variable is the movie for which the genre is to be decided.
	 */
	Movie movie;		// stores the movie whose genre is to decided
	/**
	 * This variable stores the genre in order of weight
	 */
	String[] genreList;		// stores the genre
	/**
	 * This stores the percentage a correspoinding indexed genre has.
	 */
	int[] genrePer;
	
	/**
	 * This is the constructor of the class which initializes the member variables.
	 * @param obj The movie parameter for which genre is to be found.
	 */
	public FindGenre(Movie obj)
	{
		movie=obj;
		genreList=new String[8];
		genrePer=new int[8];
	}
	
	/**
	 * This function returns the sum of frequency of each words in category array
	 * @param category this parameter is the list of words specific for genre.
	 */
	private int frequencyOfWords(String[] category)		// return the percentage of the words matched from the given category
	{
		String[] script=movie.getMovieWords();
		int count=0;
		for(String temp:category)
		{
			count+=StringSupport.countPrefix(script, temp);
		}
		return count;
	}
	
	/**
	 * this function finds the genre and prints it.
	 */
	public void findGenre()		// finds out the genre of the movie
	{
		int freq[]=new int[8];
		int temp[]=new int[8];
		String[] temp_string={"Sci-fi", "Romace", "Adventure", "Horror", "Fantasy", "Comdey", "Drama", "Action"};
		
		temp[0]=freq[0]=frequencyOfWords(ResourceSupport.sci);
		temp[1]=freq[1]=frequencyOfWords(ResourceSupport.romance);
		temp[2]=freq[2]=frequencyOfWords(ResourceSupport.adventure);
		temp[3]=freq[3]=frequencyOfWords(ResourceSupport.horror);
		temp[4]=freq[4]=frequencyOfWords(ResourceSupport.fantasy);
		temp[5]=freq[5]=frequencyOfWords(ResourceSupport.comedy);
		temp[6]=freq[6]=frequencyOfWords(ResourceSupport.drama);
		temp[7]=freq[7]=frequencyOfWords(ResourceSupport.action);
		
		
		int sum=0;
		Vector<Integer> vec=new Vector<Integer>();
		for(int i:freq)
		{
			vec.add(i);
			sum+=i;
		}	
		
		Collections.sort(vec);
		
		for(int i=0; i<8; i++)
		{
			temp[7-i]=vec.elementAt(i);
		}
		
		for(int i=0; i<8; i++)
		{
			for(int j=0; j<8; j++)
			{
				if(freq[j]==temp[i])
				{
					genreList[i]=temp_string[j];
					freq[j]=-1;
					genrePer[i]=temp[i]*100/sum;
					break;
				}
			}
		}
		
		printGenre();
	}
	
	/**
	 * This function prints the genre top 3.
	 */
	private void printGenre()   		// print the genre of the movie
	{
		for(int i=0; i<3; i++)
		{
			System.out.println(genreList[i]+" : "+genrePer[i]+" %");
		}
	}
	
}

