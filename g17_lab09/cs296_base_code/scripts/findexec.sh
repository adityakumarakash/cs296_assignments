#!/bin/bash

path="$1"
if test -z $path;
then
	echo "path not specified"
else
a="`find $path -type f | wc -l`"
b="`find $path -type d | wc -l`"
echo "Total no of files and directories in this path is $(($a+$b))"
echo "Listing all executables in the path .. "
find $path  -type f -executable
fi 

