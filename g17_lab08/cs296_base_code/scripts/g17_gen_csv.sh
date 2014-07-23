#!/bin/bash
> ./data/g17_lab05data_01.csv
for i in $(seq 1500)
	do
	for j in $(seq 150)
	do
		str=""
		while read line; do
			b=""
			c=""
			for a in $line
				do
					c=$b
					b=$a
				done
		if !(test -z $c) then
			str+=$c","
			fi
		done < ../data/g17-$i-$j.txt
		str=${str/$":,"/"$i,$j,"}
		echo ${str/%,/""} >> ./data/g17_lab05data_01.csv
	done
done
