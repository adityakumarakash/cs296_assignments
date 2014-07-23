#!/bin/bash

if [ $1 == 1 ]
then 
	tar -zcf ./src.tar.gz src
elif [ $1 == 3 ]
then
	tar -cf ./src.tar src
	bzip2  ./src.tar
	rm src.tar
elif [ $1 == 2]
then
	gzip ./src/*
	tar -cf temp ./src/*.gz
	gunzip ./src/*
else
	echo "No Input provided"
fi
