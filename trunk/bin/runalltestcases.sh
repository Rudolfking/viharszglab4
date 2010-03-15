#!/bin/bash

# Ez a szkript lefuttatja a szkeletont, átadva neki az összes talált
# level*.txt fájlt paraméterként.
# Ubuntu 9.10 alatt tesztelve

# -s parancsori paraméter esetén silent paraméterrel futtat
SILENT=false
# parancssori paraméterek elemzése
while getopts s inst
do
  case $inst in
	(s) SILENT=true ;;
  esac
done

# először kilistázzuk a talált txt fájlokat
echo "The following test cases will be run:"
ls ./level*.txt

# majd lefuttatjuk a programot
echo "Running skeleton test cases..."

if $SILENT; then
	for testcase in ./testcases/level*.txt
	do
		java skeleton/Skeleton -s "$testcase"
	done
else
	for testcase in ./testcases/level*.txt
	do
		java skeleton/Skeleton "$testcase"
	done
fi
