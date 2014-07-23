import re
import os.path

prepositions=["ABOUT","ABOVE","ACROSS","AFTER","AGAINST","ALONG","AMONG","AROUND","AT","BEFORE","BEHIND","BELOW","BENEATH","BESIDE","BETWEEN","BEYOND","BUT","BY","DESPITE","DOWN","DURING","EXCEPT","FOR","FROM","IN","INSIDE","INTO","LIKE","NEAR","OF","OFF","ON","ONTO","OUT","OUTSIDE","OVER","PAST","SINCE","THROUGH","THROUGHOUT","TILL","TO","TOWARD","UNDER","UNDERNEATH","UNTIL","UP","UPON","WITH","WITHIN","WITHOUT"]
conjunctions=["AND","THAT","BUT","OR","AS","IF","WHEN","THAN","BECAUSE","WHILE","WHERE","AFTER","SO","THOUGH","SINCE","UNTIL","WHETHER","BEFORE","ALTHOUGH","NOR","LIKE","ONCE","UNLESS","NOW","EXCEPT"]
pronouns=["YOU","ALL","EVERYONE","EVERYBODY","IT","HIS","HE","SHE","HIM","HER","NEARBY","THOSE","THEIR"]

spverbs=["READS","SAYS","DISCOVERING"]

TOT=prepositions+conjunctions+pronouns+spverbs;

science=["space","alien","future","world","computer","robot","science","radio"]
romance=["romance","love","affection","girl","sweet","kiss","beautiful","aroma","splendid"]
adventure=["adventure","war","fight","superhero","ambition","thrill","explore","army","force","military"]
comedy=["fun","laugh","comedy","laughing"]
horror=["horror","dead","kill","ghost","scream","blood"]

	
##########3
fi=open("./movie/sci", 'r')
scifi=fi.read().split(" ")
fi=open("./movie/rom", 'r')
rom=fi.read().split(" ")
fi=open("./movie/adv", 'r')
adv=fi.read().split(" ")
fi=open("./movie/com", 'r')
com=fi.read().split(" ")
fi=open("./movie/hor", 'r')
hor=fi.read().split(" ")
#########				
filename=input("Enter a path name :")
if not os.path.isfile(filename):
	print ("Wrong file.. try running script again with correct file")
	exit()
fi=open(filename, 'r')
s=fi.read()
all_words = re.findall(r"\w+",s)
weight={'science_fiction':0,'romance':0,'adventure':0,'comedy':0,'horror':0}
sciw=[x for x in scifi if x not in rom  and x not in  adv and x not in  com and x not in  hor]
romw=[x for x in rom if x not in scifi and x not in  adv and x not in  com and x not in  hor]
advw=[x for x in adv if x not in scifi and x not in  rom and x not in  com and x not in  hor]
comw=[x for x in com if x not in scifi and x not in  rom and x not in  adv and x not in  hor]
horw=[x for x in hor if x not in scifi and x not in  rom and x not in  adv and x not in  com]

for words in all_words:
	if words in science:
		weight['science_fiction']+=5
	elif words in sciw:
		weight['science_fiction']+=1
	elif words in romance:
		weight['romance']+=5
	elif words in romw:
		weight['romance']+=1
	elif words in adventure:
		weight['adventure']+=5
	elif words in advw:
		weight['adventure']+=1
	elif words in comedy:
		weight['comedy']+=5
	elif words in comw:
		weight['comedy']+=1
	elif words in horror:
		weight['horror']+=5
	elif words in horw:
		weight['horror']+=1
	
	
print("Genres found in order of decreasing probability")	
		
for w in sorted(weight, key=weight.get):
	if weight[w]>100:
		print (w)


			

