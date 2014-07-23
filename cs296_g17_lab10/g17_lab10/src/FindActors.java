package cs296MovieAnalysis;

import java.io.*;
import java.util.*;

/**
 * This class finds the hero , heroine and villain of the movie stats it has.
 */
public class FindActors
{
	/**
	 * This variable stores the stats for the movie for which the work is to be done.
	 */
	MovieStatistics stats;
	/**
	 * These variables store the hero, heroine and the villain of the movie.
	 */
	String hero, heroine, villain;
	
	/**
	 * This is the constructor of the class which initializes the stats object which is a member of this class.
	 * 
	 * @param obj This is the moviestatistics which is needed for finding the ans.
	 */
	public FindActors(MovieStatistics obj)
	{
		stats=obj;
	}
	
	/**
	 * This function finds the hero and prints it as well as.
	 */
	public void findHero()		// finds out the hero of the scipt from the analysis stats of it
	{
		Vector<String> characters=stats.getCharacters();
		Hashtable<String, Integer> characters_count=stats.getCharactersCount();
		Hashtable<String, String> gender_dict=stats.getGenderDict();
		int hcount=0;
		for(String temp:characters)
		{
			if(gender_dict.get(temp).equals("MALE"))
			{
				if(characters_count.get(temp)>hcount)
				{
					hcount=characters_count.get(temp);
					hero=temp;
				}
			}
		}
		
		printHero();
	}
	
	/**
	 * This function finds the heroine and print it.
	 */
	public void findHeroine()		// finds out the heroine of the scipt from the analysis stats of it
	{
		Vector<String> characters=stats.getCharacters();
		Hashtable<String, Integer> characters_count=stats.getCharactersCount();
		Hashtable<String, String> gender_dict=stats.getGenderDict();
		int hcount=0;
		for(String temp:characters)
		{
			if(gender_dict.get(temp).equals("FEMALE"))
			{
				if(characters_count.get(temp)>hcount)
				{
					hcount=characters_count.get(temp);
					heroine=temp;
				}
			}
		}
		
		printHeroine();
	}
	
	/**
	 * This function finds the villain and prints it
	 */
	public void findVillain()		// finds out the heroine of the scipt from the analysis stats of it
	{
		Vector<String> characters=stats.getCharacters();
		Hashtable<String, Integer> characters_count=stats.getCharactersCount();
		Hashtable<String, String> gender_dict=stats.getGenderDict();
		Vector<String> text_doc=stats.getScript();
		
		Hashtable<String, Integer> villain_match=new Hashtable<String, Integer>(250); 		// stores the amount of villain nature this has
		for(String temp:characters)
			villain_match.put(temp, 0);
			
		String speaker="", para="";
		for(String ln: text_doc)
		{
			if(ln.matches(".*[a-z]+.*"))		// if the sentence contains any small letter
			{
				para=para+" "+ln;
			}
			else if(!para.isEmpty())
			{
				String person="";
				for(String temp:characters)		// finding the speaker of the paragraph if there
				{
					if(speaker.contains(temp))
					{
						person=temp;
						break;
					}
				}
				
				if(!person.isEmpty())
				{
					int cnt=0;
					para=para.toLowerCase();
					for(String v:ResourceSupport.vill_words)
					{
						cnt+=StringSupport.countWords(v, para);
					}
					villain_match.put(person, villain_match.get(person)+cnt);
					
					cnt=0;
					for(String v:ResourceSupport.good_words)
					{
						cnt+=StringSupport.countWords(v, para);
					}
					villain_match.put(person, villain_match.get(person)-cnt);
					
				}
				
				speaker=ln;
				para="";
			}
			else 
			{
				speaker=ln;
			}
		}
		
		int vcount=-100; 
		for(String temp:characters)
		{
			if(!(temp.equals(hero) || temp.equals(heroine)))
			{
				if(villain_match.get(temp)>vcount)
				{
					vcount=villain_match.get(temp);
					villain=temp;
				}
			}
		}
		
		printVillain();
	}
	
	/**
	 * this function prints the hero
	 */
	private void printHero()		// print hero
	{
		System.out.println(hero);
	}
	
	/**
	 * This function prints the heroine.
	 */
	private void printHeroine()		// print heroine
	{
		System.out.println(heroine);
	}
	
	/**
	 * This function prints the villain.
	 */
	private void printVillain()		// print villain
	{
		System.out.println(villain);
	}
}
