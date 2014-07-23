import re

prepositions=["ABOUT","ABOVE","ACROSS","AFTER","AGAINST","ALONG","AMONG","AROUND","AT","BEFORE","BEHIND","BELOW","BENEATH","BESIDE","BETWEEN","BEYOND","BUT","BY","DESPITE","DOWN","DURING","EXCEPT","FOR","FROM","IN","INSIDE","INTO","LIKE","NEAR","OF","OFF","ON","ONTO","OUT","OUTSIDE","OVER","PAST","SINCE","THROUGH","THROUGHOUT","TILL","TO","TOWARD","UNDER","UNDERNEATH","UNTIL","UP","UPON","WITH","WITHIN","WITHOUT"]
conjunctions=["AND","THAT","BUT","OR","AS","IF","WHEN","THAN","BECAUSE","WHILE","WHERE","AFTER","SO","THOUGH","SINCE","UNTIL","WHETHER","BEFORE","ALTHOUGH","NOR","LIKE","ONCE","UNLESS","NOW","EXCEPT"]
pronouns=["YOU","ALL","EVERYONE","EVERYBODY","IT","HIS","HE","SHE","HIM","HER","NEARBY"]
script_words=["VOICE","RINGS","END","DETAIL","SHOOT","FADE","CONTINUED","CONTINUOUS","START","CREDITS"]
months=["JANUARY","FEBRUARY","MARCH","APRIL","MAY","JUNE","JULY","AUGUST","SEPTEMBER","OCTOBER","NOVEMBER","DECEMBER"]
day_time=["MORNING","NIGHT","AFTERNOON"]
common_nouns=["HILLS","SOLDIERS","LOVERS","BATTLE","ARMY","DOGS","PEOPLE","CROWD","FOLKS","CHILDREN","SILENCE"]
common_places=["ROOM","KITCHEN","CAMP","BATHROOM"]
equipments=["TV","CAR","BOOTH"]
inanimate=["BRIDGE","UPSTAIRS","RIVER","ICE","MINUTES","HOURS","CORRIDOR","RESUME"]
places=["LONDON"]
spverbs=["READS","SAYS","DISCOVERING"]

TOT=prepositions+conjunctions+pronouns+script_words+months+day_time+common_nouns+common_places+equipments+inanimate+places+spverbs;

from os import listdir
allfiles=listdir('./movie')
print("Scripts file that are currently present:")
c=1
for i in allfiles:
	print(c,". ",end='')
	print(i )
	c+=1
	
filename=input("Enter a path name :")

inpfile=open("./movie/"+filename,'r')
data=inpfile.read()
lines=data.split('\n')
characters=dict()

for line in lines:
	
	raw_line=line.strip()
	match1=re.search('^[A-Z ]+$',raw_line)
	match2=re.search('^[A-Z ]+ [0-9]+$',raw_line)
	
	if match1 or match2:
		raw_line_list=raw_line.split()
		for word in raw_line_list:
			if word in TOT:
				break 
		else:	
			if raw_line in characters:
				characters[raw_line]=characters[raw_line]+1
			else:
				characters[raw_line]=1
									
for i in characters:
	match1=re.search('.*[A,Y,I]$',i.split(" ")[0])
	if match1:
		characters[i]=-2	
	else:
		characters[i]=0

def read_words(words_file):
    return [word.lower() for line in open(words_file, 'r') for word in line.split()]

allwords=read_words("./movie/"+filename)

for i in range(0,len(allwords)):
	if allwords[i].upper() in characters:
		j=-10
		while i+j >= 0 and i+j < len(allwords) and j<=10:
			cur=allwords[i+j]
			if cur=="he" or cur=="his" or cur=="him" or cur=="mr":
				characters[allwords[i].upper()]+=1
			elif cur=="she" or cur=="her" or cur=="mrs" or cur=="miss":
				characters[allwords[i].upper()]-=1
			j+=1

for i in sorted(characters, key=characters.get):
	if characters[i]>0:
		print(i , " : male ",characters[i])
	elif characters[i]<0:
		print(i , " : female ",characters[i])
	else:
		print(i , " : undetermined ")
