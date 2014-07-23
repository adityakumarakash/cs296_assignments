#!/bin/bash
#echo "Select one option"
#echo "1) rename all .cpp file to .cpp.bak in one command"
option=0
if test -z "$1"; then
echo "Please provide input"
else
option="$1"
fi
if test $option = "1"; then
	if test -e ./src/main.cpp; then
	rename  's/\.cpp/.cpp.bak/' ./src/*.cpp
	fi
echo "All files renamed from .cpp to .cpp.bak using rename"
elif test $option = "2";then
	if test -e ./src/main.cpp; then
	for file in `find ./src/ -name *.cpp` ; do mv "$file" "${file}".bak; done
	fi
echo "All files renamed from .cpp to .cpp.bak using for"
elif test $option = "3";then
	if test -e ./src/main.cpp.bak; then
	rename  's/\.cpp.bak/.cpp/' ./src/*.cpp.bak
	fi
echo "All files renamed back from .cpp.bak to .cpp using rename"
else
echo "wrong input"
fi
