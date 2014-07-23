package cs296MovieAnalysis;

import java.io.*;
import java.util.*;

/**
 * This class is meant to obtain the various data related to movie script which it defines.
 * Data as all characters of the movie, the count of each character, and the gender is found in this class
 */
public class MovieStatistics
{
	/**
	 * This variable defines the member movie for which statistics is obtained.
	 */
	private Movie movie;		// the movie whose stats is to be obtained
	/**
	 * This variable hashes the characters to their count in the movie script.
	 */
	private Hashtable<String, Integer> characters_count;		// hash table which stores the count of each charactes
	/**
	 * This variable hashes the characters to their gender.
	 */
	private Hashtable<String, String> gender_dict;				// hash table which stores the gender of each characters
	/**
	 * This variable stores the character names.
	 */
	private Vector<String> characters;
	
	/**
	 * The constructor takes the movie object for which stats are to be found. 
	 * The constructor calls the find characters , sort them and finds the gender as well as their count.
	 * 
	 * @param obj this is the movie parameter
	 */
	public MovieStatistics(Movie obj)
	{
		movie=obj;
		characters_count=new Hashtable<String, Integer>(250);
		gender_dict=new Hashtable<String, String>(250);
		characters=new Vector<String>();
		findCharacters();
		sortMovieCharacters();
		findCharactersCount();
		determineCharGender();
	}
	
	/**
	 * This function find the number of words in the script.
	 */
	public int countWords()		// count the number of words in the movie
	{
		return movie.getMovieWords().length;
	}
	
	/**
	 * This function finds the characters in the script of the movie.
	 */
	private void findCharacters()
	{
		Vector<String> toRemove=new Vector<String>();		// this string vector is the one to ve excluded in character finding
		
		StringSupport.add(toRemove, ResourceSupport.prepositions);
		StringSupport.add(toRemove, ResourceSupport.conjunctions);
		StringSupport.add(toRemove, ResourceSupport.pronouns);
		StringSupport.add(toRemove, ResourceSupport.script_words);
		StringSupport.add(toRemove, ResourceSupport.months);
		StringSupport.add(toRemove, ResourceSupport.days);
		StringSupport.add(toRemove, ResourceSupport.day_time);
		StringSupport.add(toRemove, ResourceSupport.common_nouns);
		StringSupport.add(toRemove, ResourceSupport.common_places);
		StringSupport.add(toRemove, ResourceSupport.equipments);
		StringSupport.add(toRemove, ResourceSupport.inanimate);
		StringSupport.add(toRemove, ResourceSupport.places);
		StringSupport.add(toRemove, ResourceSupport.spverbs);
		
		Vector<String> script=movie.getScript();
		String pattern0="^[A-Z][A-Z0-9 .'¯]*[^.!-:;'?]$"; 
		
		for(String temp:script)
		{	
			if(temp.matches(pattern0)) 			// only all capitals and the numbers match this
			{
				temp=temp.replaceAll("[.]([^ ])",". $1");
				String[] line_split=temp.split(" ");
				if(!StringSupport.isCommon(toRemove, line_split))
				{
					if(!characters_count.containsKey(temp))
						characters_count.put(temp, 0);
				}
			}
		}
		
		Enumeration names;
		names = characters_count.keys();
		
		while(names.hasMoreElements()) 		// extract the names from enum to the characters vector
		{
			String str = (String) names.nextElement();
			characters.add(str);
		}
		
	}
	
	/**
	 * This function finds the number of occurences of each character in the script.
	 */
	private void findCharactersCount()		// counts the number of occurences of the each characters in the script
	{
		Vector<String> script=movie.getScript();
		for(String i:characters)
		{
			for(String j:script)
			{
				characters_count.put(i, characters_count.get(i) + StringSupport.countWords(i.toLowerCase(), j.toLowerCase()));
			}
		}
	}
	
	/**
	 * This function sorts the Movie characters alphabetically.
	 */
	private void sortMovieCharacters()		// sort the characters alphabetically
	{
		Collections.sort(characters);
	}
	
	/**
	 * This function finds the number of characters in the movie.
	 */
	public int countMovieCharacters()		// return the count of number of characters
	{
		return characters.size();
	}
	
	/**
	 * This function finds the gender of each character in the movie.
	 */
	private void determineCharGender()		// determine the gender of each character
	{
		Vector<String> text_doc=movie.getScript();
		Hashtable<String, Integer> gender_factor=new Hashtable<String, Integer>(200);  // wrt to each character a gender factor is 
																						// associated
		for(String temp:characters)
		{
			gender_factor.put(temp, 0);
		}
		
		String para="", speaker="";
		for(String ln:text_doc)
		{
			if(ln.matches(".*[a-z]+.*"))		// if the sentence contains any small letter
			{
				para=para+" "+ln;
			}
			else if(!para.isEmpty())
			{
				para=para.toLowerCase();
				
				Vector<Integer> male_resolvers_list=new Vector<Integer>();
				Vector<Integer> female_resolvers_list=new Vector<Integer>();
				Vector<Integer> resolvers_list=new Vector<Integer>();
				
				int pos=0;
				String[] p_split=para.split(" ");
				for(String temp:p_split)
				{
					if(StringSupport.isPresent(ResourceSupport.male_resolvers_back, temp))
					{
						male_resolvers_list.add(pos);
						resolvers_list.add(pos);
					}
					else if(StringSupport.isPresent(ResourceSupport.female_resolvers_back, temp))
					{
						female_resolvers_list.add(pos);
						resolvers_list.add(pos);
					}
					else if(StringSupport.isPresent(ResourceSupport.male_resolvers_forward, temp))
					{
						String name_temp=p_split[pos+1].toUpperCase();
						name_temp=name_temp.replaceAll("([A-Z ]*)[.?!,;]?","$1");
						if(gender_factor.containsKey(name_temp))
							gender_factor.put(name_temp, gender_factor.get(name_temp)+10);
					}
					else if(StringSupport.isPresent(ResourceSupport.female_resolvers_forward, temp))
					{
						String name_temp=p_split[pos+1].toUpperCase();
						name_temp=name_temp.replaceAll("([A-Z ]*)[.?!,;]?","$1");
						if(gender_factor.containsKey(name_temp))
							gender_factor.put(name_temp, gender_factor.get(name_temp)-10);
					}
					
					pos++;
				}
				
				Collections.sort(resolvers_list);
				
				pos=0;
				for(int pos_res:resolvers_list)
				{
					while(pos < pos_res)
					{
						for(String temp:characters)
						{
							if(StringSupport.isPresent(temp.toLowerCase().split(" "), p_split[pos]))
							{
								if(StringSupport.isPresent(male_resolvers_list, pos_res))
									gender_factor.put(temp, gender_factor.get(temp)+2);
								else
									gender_factor.put(temp, gender_factor.get(temp)-2);
							}
						}
						pos++;
					}
				}
				
				// now deciding based on the first person speech
				String person="";
				for(String temp:characters)
				{
					if(speaker.contains(temp))
					{
						person=temp;
						break;
					}
				}
				
				if(!person.isEmpty())
				{
					String[] p_split_sentence=para.split("[.]");
					for(String temp_ln:p_split_sentence)
					{
						temp_ln=temp_ln.trim();
						if(temp_ln.length()>1)
						{
							String[] temp_ln_split=temp_ln.split(" ");
							if((temp_ln_split[0].equals("i") && (temp_ln_split[1].equals("m") || temp_ln_split[1].equals("am"))) || temp_ln_split[0].equals("i'm"))
							{
								boolean val1=false, val2=false;
								val1=StringSupport.isCommon(temp_ln_split, ResourceSupport.male_first_person_address);
								val2=StringSupport.isCommon(temp_ln_split, ResourceSupport.female_first_person_address);
								if(val1 && val2);
								else if(val1) gender_factor.put(person, gender_factor.get(person)+5);
								else if(val2) gender_factor.put(person, gender_factor.get(person)-5);
							}
						}
					}
				}
				
				speaker=ln;
				para="";
			}
			else 
			{
				speaker=ln;
			}
		}
		
		// deciding the gender based on the given data obtained from gender factor
		
		for(String temp:characters)
		{
			int temp_val=gender_factor.get(temp);
			if(temp_val==0)	gender_dict.put(temp, "UNDETERMINED");
			else if(temp_val<5)	gender_dict.put(temp, "FEMALE");
			else gender_dict.put(temp, "MALE");
			
			// deciding on basis of common name presence
			
			if(StringSupport.isCommon(ResourceSupport.male_list, temp.toLowerCase().split(" ")))
				gender_dict.put(temp, "MALE");
			else if(StringSupport.isCommon(ResourceSupport.female_list, temp.toLowerCase().split(" ")))
				gender_dict.put(temp, "FEMALE");
				
			//System.out.println(temp + " : " +gender_dict.get(temp)+":"+characters_count.get(temp));
		}
	}
	
	/**
	 * This function prints the characters and their counts
	 */
	public void printCharCounts()		// print the characters with their counts
	{
		for(String temp:characters)
		{
			System.out.println(temp+" : "+characters_count.get(temp));
		}
	}
	
	/**
	 * This function prints the characters with their count and gender.
	 */
	public void printCharCountsWithGender()		// print the characters with counts and their gender
	{
		for(String temp:characters)
		{
			System.out.println(temp+" : "+characters_count.get(temp)+" : "+gender_dict.get(temp));
		}
	}
	
	/**
	 * this is a getter method for characters.
	 */
	public Vector<String> getCharacters()		// getter method for getting characters
	{
		return characters;
	}
	
	/**
	 * This is a getter method for characters_count.
	 */
	public Hashtable<String, Integer> getCharactersCount()		// getter method for getting characters_count
	{
		return characters_count;
	}
	
	/**
	 * This is a getter method for gender_dict.
	 */
	public Hashtable<String, String> getGenderDict()		// getter method for getting characters_gender_dict
	{
		return gender_dict;
	}
	
	/**
	 * This function is getter for script.
	 */
	public Vector<String> getScript()		// returns the script over which stats was made
	{
		return movie.getScript();
	}
	
}	// end of class
