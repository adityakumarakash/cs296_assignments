READ ME for Lab8---

Group number - 17

members - 
	1. Aditya Kumar Akash	- 120050046
	2. Prateek Chandan	- 120050042
	3. Naveen Sagar		- 120050026

All work that we are submitting for this assignment is our own work and we have not plagiarized it from anywhere.

Algorithms used for doing the project are

For scriptinfo - 
The Title and the name of the author is found out using the fact that 
-- the title in any script is the first capital words in the script
-- the author name comes after the title and followed by "written by " or "by" -- The capitals are the author name

For moviechars -
For this part a first observation tells that the character's names are present in bold and caiptal in single
line in the place where someone delivers the dialogue

So we have used the regex to find out the words in capitals which are of the form of 
1) only capitals with spaces
2) capitals with numbers for police 1 etc.
3) for MR. mrs. prof. etc followed by capitals

when we get to find these we are certain they are characters. 
A dict is maintained for maintaining the names for uniqueness

Now for each characters we find the number of occurences

For Gender --- 
We mark the occurence of his , her, he, she,, etc.. Now the proper noun whihc occurs just before them are the 
resolvers of these means these are used for the,, in general. So weights are assigned for each occurence +1
for male type and -1 for female.


Based on final values male females are found. The 0 values emans non determinism

For Hero -- 
The max occured male is hero and the amx occured female is heroine.

For villain -- 
A list of negative words is maintained..
The character which has max of these associated with is the villain

For genre -- 

A list of common words for each genre is made form 5 file
Now the common words from different genre is removed.

Then a probailistic matching is done with each genre . the high prob means that genre.

CITATIONS-- 

We referred to the online documentation of python

Other Sources which we made use for our study include -- 
	1.Python Manual
	2.stackoverflow
	3.google.com

