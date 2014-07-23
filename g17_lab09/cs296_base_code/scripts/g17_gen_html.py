import time
import re
inpfile=open("./doc/g17_prof_report.tex")
outfile=open("./doc/g17_lab09_report.html",'w')
print("Generating HTML report")
outfile.write("<html><head><title>Lab09 Report</title><link rel=\"stylesheet\" href=\"./style.css\"></head> <body>")
bracesstr={"title":"h1","section":"h2","subsection":"h3","subsection\*":"h3","textbf":"b","emph":"i","texttt":"span style=\"font-family:monospace\"","date":"date","caption":"div class=\"img-caption\""}
lineextractor={"item":"li"}
replaceable={'author{':"<div class=\"author\"><b>By:</b><br><br>","maketitle":"</div>","and":"<br>"}
removables=["documentclass","usepackage","centering"]
beginstr={"document":"div","enumerate":"ul","figure":"div class=\"image\"","lstlisting":"code","singlespace":"div"}

alllines=inpfile.readlines()

for line in alllines[1:]:
	line = re.sub('\\\\today',time.strftime("%d/%m/%Y"),line)
	for key in replaceable:
		val=replaceable[key]
		if re.match('.*\\\\'+key,line):
			line = re.sub('\\\\'+key,val,line)
	for key in bracesstr:
		val=bracesstr[key]
		if re.match('.*\\\\'+key+'{.*?}',line):
			line = re.sub('\\\\'+key+'{.*?}',"<"+val+">"+line[line.index('{') + 1:line.index("}")]+"</"+val+">",line)
			
	for key in removables:
			line = re.sub('\\\\'+key+'.*\n',"",line)
	
	for key in lineextractor:
		val=lineextractor[key]
		if re.match('.*\\\\'+key+' .*?',line):
			line = re.sub('\\\\'+key+' .*?\n',"<"+val+">"+line.split(key)[-1]+"</"+val+">",line)
	
	
	if re.match('.*\\\\begin{.*?}.*',line):
		stri=line[line.index("{") + 1:line.index("}")]
		if stri.strip() in beginstr:
			line = re.sub('\\\\begin{.*?}.*',"<"+beginstr[stri]+">",line)
	
	if re.match('.*\\\\end{.*?}',line):
		stri=line[line.index("{") + 1:line.index("}")]
		if stri.strip() in beginstr:
			line = re.sub('\\\\end{.*?}',"</"+beginstr[stri]+">",line)
			
	#for finding images
	if re.search('.*\\\\includegraphics.*{.*}',line):
		imgsrc=line[line.index('{') + 1:line.index("}")]
		line = re.sub('.*\\\\includegraphics.*{.*?}',"<img src=\""+imgsrc+"\" alt=\""+imgsrc+"\">",line)
		
	line = re.sub(r'\\\\',"<br>",line)
	line = re.sub(r'\\%','%',line)
	line = re.sub(r'\\_',"_",line)
	
	if line == "\n" or line == "}\n":
		outfile.write("<br>")
	elif "\\" in line:
		outfile.write(line)
	else:
		outfile.write(line)
		
	match = re.search('\\\\',line)
	
	if match:
		outfile.write("<br>")
	
outfile.write("</body></html>")	
print("html report generated in doc/g17_lab09_report.html")
