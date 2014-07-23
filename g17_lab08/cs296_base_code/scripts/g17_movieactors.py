import os.path
from collections import deque
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

# now we identify the character gender 

# list which can be said to be male or female straight away
male_list=["male","man","soldier","boy","scout","father","grandfather","husband","brother","mr.","mr","prince","sergeant"]
female_list=["female","girl","girls","lady","sister","wife","maid","mother","woman","grandmother","mrs","mrs.","princess","witch"]

common_males=["waiter"]
common_females=["waitress"]

male_list=male_list+common_males
female_list=female_list+common_females

# first breaking into the capitals and then paragraphs

male_resolvers_back=["he","his","him","himself","he's","him.","himself.","his.","sir","sir.","brother"]
female_resolvers_back=["she","her","herself","she's","her.","herself.","madam","maam","sister"]

male_resolvers_forward=["mr","mr.","mister"]
female_resolvers_forward=["mrs","mrs."]

male_first_person_address=["boy","man","male","brother","father"]
female_first_person_address=["girl","woman","female","sister","mother"]

# deciding on basis of context
para=""
speaker=""

gender_factor={}
for i in cast:	 
	gender_factor[i]=0

for ln in lines[:]:
	
	raw_ln=ln.strip()
	match=re.search('[a-z]+',raw_ln)
	
	if raw_ln=="":
		pass
	elif not match:
		if not para.strip()=="":
			para=para.lower()
			#print(speaker)
			#print(para)
			#print("\n")
			p_split=para.split()
			#print(p_split)
			
			# processing the paragraph here
			male_resolvers_back_list=[]
			male_resolvers_forward_list=[]
			female_resolvers_back_list=[]
			female_resolvers_forward_list=[]
			pos=0
			for wrd in p_split:
				if wrd in male_resolvers_back:
					male_resolvers_back_list.append(pos)
				if wrd in male_resolvers_forward:
					male_resolvers_forward_list.append(pos)
				if wrd in female_resolvers_back:
					female_resolvers_back_list.append(pos)
				if wrd in female_resolvers_forward:
					female_resolvers_forward_list.append(pos)
				pos+=1
			
			# now a list of position of occuring of charcters 
			
			pos=0
			resolvers_list=male_resolvers_back_list+female_resolvers_back_list
			resolvers_list.sort()
			
			start=0
			pos=0
			for pos_res in resolvers_list:
				#while pos<pos_res:
				#	if p_split[pos] in cast:
				#		if pos_res in male_resolvers_back_list:
				#			gender_factor[p_split[pos]]+=2
				#		else:
				#			gender_factor[p_split[pos]]-=2
				#	pos+=1
				while pos<pos_res:
					for temp in cast:
						if p_split[pos] in temp.split():
							if pos_res in male_resolvers_back_list:
								gender_factor[temp]+=2
								break
							else:
								gender_factor[temp]-=2
								break
					pos+=1
			
			for pos_res in male_resolvers_forward_list:
				pos_least=len(para)
				latest_name=""
				for name in cast:
					temp=para.find(name, pos_res)
					if temp!=-1:
						if temp < pos_least:
							pos_least=temp
							latest_name=name
				if latest_name!="":
					gender_factor[latest_name]+=10			
			
			for pos_res in female_resolvers_forward_list:
				pos_least=len(para)
				latest_name=""
				for name in cast:
					temp=para.find(name, pos_res)
					if temp!=-1:
						if temp < pos_least:
							pos_least=temp
							latest_name=name
				if latest_name!="":
					gender_factor[latest_name]-=10	
		
			p_split=para.split(".")
			person=""
			speaker=speaker.lower()
			
			for per in cast:
				if speaker.find(per)!=-1:
					person=per
					break
			
			if person!="":
				for lne in p_split:
					lne=lne.strip()
					wrd_split=lne.split()
					if len(wrd_split)>0 and (wrd_split[0]=="i" or wrd_split[0]=="i'm"):
						val1=False
						val2=False
						for frst in male_first_person_address:
							if frst in wrd_split:
								val1=True
								break
						for frst in female_first_person_address:
							if frst in wrd_split:
								val2=True
								break
								
						if val1 and val2:
							pass
						elif val1:
							gender_factor[person]+=5
						elif val2:
							gender_factor[person]-=5
							
		if not re.search('^\(.*\)', raw_ln):		
			speaker=raw_ln
		para=""
	else:
		#print(raw_ln)
		para=para + " " + raw_ln
		
		
		
		
x=list(gender_factor)
x.sort()
gender_dict={}

for y in x:
	if gender_factor[y]==0:
		gender_dict[y.upper()]="Undetermined"
	elif gender_factor[y]>5:
		gender_dict[y.upper()]="Male"
	else:
		gender_dict[y.upper()]="Female"


# deciding the gender based on male_list and female_list
for i in keys:
	wrds=i.split()
	for j in wrds:
		j=j.lower()
		if j in male_list:
			gender_dict[i]="Male"
			break
		elif j in female_list:
			gender_dict[i]="Female"
			break

for y in x:
	temp=y.upper()
	print(temp,'\t',characters[temp],'\t',gender_dict[temp])
		
		
# deciding the heroes and villains
vill_words=["evil","death","kill you","weapon","detonator","murder","strike","negative","kill","fear","torture","hurt","harm","harmful","loot","rob","shoot","brutal","thrash","rape","destroy","burn","violence","vengeance","frighten"]
good_words=["love","peace","hero","help","save","repect","great","honest","smile","beloved","sweetheart","thank","thankyou","sorry","joy","promise"]

villain_match={}
for mem in cast:
	villain_match[mem]=0
			
para=""
speaker=""

for ln in lines[:]:
	
	raw_ln=ln.strip()
	match=re.search('[a-z]+',raw_ln)
	
	if raw_ln=="":
		pass
	elif not match:
		if not para.strip()=="":
			para=para.lower()
			
			person=""
			speaker=speaker.lower()
			
			for per in cast:
				if speaker.find(per)!=-1:
					person=per
					break
			if person!="":
				for temp in vill_words:
					pos=0
					while True:
						pos=para.find(temp, pos)
						if pos!=-1:
							villain_match[per]+=1
							pos+=1
						else:
							break
						
				for temp in good_words:
					pos=0
					while True:
						pos=para.find(temp, pos)
						if pos!=-1:
							villain_match[per]-=1
							pos+=1
						else:
							break
						
			
		if not re.search('^\(.*\)', raw_ln):		
			speaker=raw_ln
		para=""
	else:
		para=para+" "+raw_ln

actor=""
actress=""

for i in sorted(characters,key=characters.get):
	if gender_dict[i]=="Male" :
		actor=i
	if gender_dict[i]=="Female":
		actress=i

print("Main hero of story as predicted : ",actor)
print("Main heroine of story as predicted : ",actress)	

villan=""
for i in sorted(villain_match,key=villain_match.get):
	if i!=actor and i!=actress:
		villan=i

print("Main antagonist of story as predicted : ",villan.upper())	

