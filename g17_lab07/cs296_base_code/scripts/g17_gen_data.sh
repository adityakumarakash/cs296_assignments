#!/bin/bash

for i in $(seq 1500)
	do
	for j in $(seq 150)
		do
		./mybins/cs296_17_exe $i > ./data/g17-$i-$j.txt 
		done
	done

