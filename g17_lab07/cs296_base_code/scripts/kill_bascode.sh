#!/bin/bash
process=$(ps aux| grep "[m]ybins/cs296_17_exe")
b=0;
if test -z $process; then
	echo "Basecode is not running"
else
	for i in $process; 
		do 
		if test $b -eq 0; then uname=$i; fi;
		if test $b -eq 1; then pid=$i; fi;
		b=$(($b+1));
	done
	echo "Base code is running with pid $pid and $uname is running it"; 
	if test $uname = $(whoami);then
		echo "do you want to kill the process(y/n) : "
		read line
		if test $line = "y";then
		kill $pid
		fi
	else
		echo "You dont have permission to kill this";
	fi
fi

