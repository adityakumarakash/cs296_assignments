import os.path
filename=input("Enter a path name :")
if not os.path.isfile(filename):
	print ("Wrong file.. try running script again with correct file")
	exit()
	
import re

prepositions=["ABOUT","ABOVE","ACROSS","AFTER","AGAINST","ALONG","AMONG","AROUND","AT","BEFORE","BEHIND","BELOW","BENEATH","BESIDE","BETWEEN","BEYOND","BUT","BY","DESPITE","DOWN","DURING","EXCEPT","FOR","FROM","IN","INSIDE","INTO","LIKE","NEAR","OF","OFF","ON","ONTO","OUT","OUTSIDE","OVER","PAST","SINCE","THROUGH","THROUGHOUT","TILL","TO","TOWARD","UNDER","UNDERNEATH","UNTIL","UP","UPON","WITH","WITHIN","WITHOUT"]
conjunctions=["AND","THAT","BUT","OR","AS","IF","WHEN","THAN","BECAUSE","WHILE","WHERE","AFTER","SO","THOUGH","SINCE","UNTIL","WHETHER","BEFORE","ALTHOUGH","NOR","LIKE","ONCE","UNLESS","NOW","EXCEPT"]
pronouns=["YOU","ALL","EVERYONE","EVERYBODY","IT","HIS","HE","SHE","HIM","HER","NEARBY"]
script_words=["VOICE","RINGS","END","DETAIL","SHOOT","FADE","CONTINUED","CONTINUOUS","START","CREDITS","SCENE","COMPLETE","DISPATCH","OMITTED","ORDERLY","CROSSING"]
months=["JANUARY","FEBRUARY","MARCH","APRIL","MAY","JUNE","JULY","AUGUST","SEPTEMBER","OCTOBER","NOVEMBER","DECEMBER"]
days=["SUNDAY","MONDAY","TUESDAY","WEDNESDAY","THURSDAY","FRIDAY","SATURDAY","DAY"]
day_time=["MORNING","NIGHT","AFTERNOON"]
common_nouns=["HILLS","SOLDIERS","LOVERS","FORCES","UNIFORM","BATTLE","ARMY","DOGS","PEOPLE","CROWD","FOLKS","CHILDREN","OTHERS","SILENCE"]
common_places=["ROOM","KITCHEN","CAMP","BATHROOM"]
equipments=["TV","CAR","BOOTH","TELEPHONE"]
inanimate=["BRIDGE","UPSTAIRS","RIVER","ICE","MINUTES","HOURS","CORRIDOR","RESUME"]
places=["LONDON"]
spverbs=["READS","SAYS","DISCOVERING"]

TOT=prepositions+conjunctions+pronouns+script_words+months+day_time+common_nouns+common_places+equipments+inanimate+places+spverbs+days;

inpfile=open(filename,'r')
data=inpfile.read()
lines=data.split('\n')
characters=dict()

for line in lines[10:]:
	
	raw_line=line.strip()
	match1=re.search('^[A-Z ]+$',raw_line)
	match3=re.search('^MR. [A-Z ]+$',raw_line)
	match4=re.search('^MRS. [A-Z ]+$',raw_line)
	match6=re.search('^PROF. [A-Z ]+$',raw_line)
	match2=re.search('^[A-Z ]+ [0-9#]+$',raw_line)
	match5=re.search("^[A-Z]+'S [A-Z]+$",raw_line)
	
	if match1 or match2 or match3 or match4 or match5 or match6:
		raw_line_list=raw_line.split()
		for word in raw_line_list:
			if word in TOT:
				break 
		else:	
			if raw_line not in characters:
				#characters[raw_line]=characters[raw_line]+1
				#else:
				characters[raw_line]=0						

# keys contains the characters names
keys = list(characters)
keys.sort()
cast=[]
print("All the characters present in script are :")
for i in keys:
	cast.append(i.lower())
	
data_lowercase=data.lower()
for member in cast:
	pos=0
	while True:
		pos=data_lowercase.find(member, pos)
		if pos!=-1:
			characters[member.upper()]+=1
			pos+=1
		else:
			break

for i in keys:
	print(i, characters[i])



			

