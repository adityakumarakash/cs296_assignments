#!/bin/bash 

print_sec() #prints the sec as week days hrs
{
	local sec_=$1
	local weeks_=$(($sec_/(3600*24*7)))
	sec_=$(($sec_%(3600*24*7)))
	local days_=$(($sec_/(3600*24)))
	sec_=$(($sec_%(3600*24)))
	local hrs_=$(($sec_/3600))
	echo "There are "$weeks_" weeks "$days_" days and "$hrs_" hours left for the birthday of ""$2"
}

print_date() # suppose the bday is in month-date
{
	local date_=$(date +"%Y")"-"$1
	local sec_=$(($(date -d"$date_" +"%s") - $(date +"%s")))
	
	if test 0 -gt $sec_
	then
		date_=$($(date +"%Y")+1)"-"$1
		sec_=$(($(date -d"$date_" +"%s") - $(date +"%s")))
	fi

	print_sec $sec_ "$2"
}

echo "Today is" $(date +"%A, %d %B, %Y")

print_date "11-03" "aditya Kumar akash"
print_date "03-15" "prateek chandan"
print_date "06-22" "naveen sagar"

echo "Thank you for asking "$(whoami)

a=$(uptime | sed 's|.*up *\(.*\), *[0-9]* *users.*|\1|' | sed 's|\(:.*\)|\1 hours|')
echo "Your system has been running for $a."


a=($(df|head -n 2|tail -n 1))
echo "The current disk on which your home folder is located is ${a[0]} and is ${a[4]} full"
a=(`free `);
b=0;
totmem=${a[7]}
usedmem=${a[8]} 
echo "You are running `lsb_release -i | awk '{print $3}'` and `lsb_release -c | awk '{print $2}'` with Kernel `uname -r`"
echo "Your have $(($totmem/(1024*1024))).$((($totmem/1024)%1000)) GB RAM. Of which $((($usedmem*100)/$totmem))% is in use"
echo "The script has $(less ./scripts/myinfo.sh | wc -l) lines $(less ./scripts/myinfo.sh | wc -w) words $(less ./scripts/myinfo.sh | tr -d "\n \t\v" | wc -c) characters"
