#!/bin/bash
> ./data/g17_lab05data_random.csv
for i in $(seq 1500)
	do
		str=""
		for x in $(seq 15)
	        do
	      	     str+=$(((RANDOM % 150) + 1))" "
	       	done
#str=$(sort -g <<< "${str// /$'\n'}")
#echo $str
		for j in $(seq 150)
		do
			read line;
			for k in $str
				do 
				if [ "$k" == "$j" ];
				then 
					echo $line >> ./data/g17_lab05data_random.csv
				fi
				done
		done 
	done < ./data/g17_lab05data_02.csv
