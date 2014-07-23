import os.path
filename=input("Enter a path name :")
if not os.path.isfile(filename):
	print ("Wrong file.. try running script again with correct file")
	exit()

f = open(filename, 'r')
i=0
title=""
author=""
state=1
for a in f:
	if(a.strip() != ""):
		a=a.lower()
		if i==0:
			title=a.strip()
		elif i==1:
			words=a.strip().split(" ")
			if  words[-1].lower()!="by" and 'by' in words:
				author=""
				for w in words[words.index('by')+1:]:
					author += w + " "
				i=10
			elif words[-1].lower()=="by":
				state=2					
		elif i==2:
			words=a.strip().split(" ")
			if state==2:
				author=a.strip()
				state=3
			elif  words[-1].lower()!="by" and 'by' in words:
				author=""
				for w in words[words.index('by')+1:]:
					author += w + " "
				i=10
			elif words[-1].lower()=="by":
				state=2	
		elif i==3:
			words=a.strip().split(" ")
			if state==2:
				author=a.strip()
			elif  words[-1].lower()!="by" and 'by' in words:
				author=""
				for w in words[words.index('by')+1:]:
					author += w + " "
				i=10
	
		elif i>3:
			athor = "Not Found"
			break
		else:
			pass
		i+=1

print("Title: " , title)
print("Author: ",author)
			

