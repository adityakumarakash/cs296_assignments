#!/bin/bash

if test -z $1
then echo "Please provide the backup path"
else
	cp -rf -u -v ./src $1 
fi
