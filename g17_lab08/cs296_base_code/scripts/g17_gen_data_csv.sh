#!/bin/bash
> ./data/g17_lab05data_02.csv
for i in $(seq 1500)
	do 
	for j in $(seq 150)
		do
			data=$(./mybins/cs296_17_exe $i)
			
			str=""
			for a in $data
				do 
				if [[ "$a" =~ ^[0-9]+(\.[0-9]+)?$ ]]
					then
				  		str+=$a","
					fi
				done
			str=${str/$","/",$j,"}
			echo ${str/%,/""} >> ./data/g17_lab05data_02.csv 
		done
	done
