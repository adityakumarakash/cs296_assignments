#!/bin/bash
>./data/temp.csv
for i in $(seq 1500)
do
	str=" "
	for j in $(seq 150)
	do
		read line
		var=$(echo $line | awk -F ',' '{print $3}')
		str+=$var" "
	done
		#echo $str
		sd=$(echo $str | sed 's/ /\n/g' | awk '{sum+=$1; sumsq+=$1*$1} END {print sqrt(sumsq/NR - (sum/NR)*(sum/NR))}')
		mean=$(echo $str | sed 's/ /\n/g' | awk '{sum+=$1} END {print sum/NR}')
		echo $i","$mean","$sd >>./data/temp.csv
done < ./data/g17_lab05data_01.csv	
