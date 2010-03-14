#!/bin/bash

# Ez a szkript lefuttatja a szkeletont, átadva neki az összes talált
# txt fájlt paraméterként.
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
# a teljes automatizálás még nem működik
# java skeleton/Skeleton -s ./level*.txt
java skeleton/Skeleton -s ./level0.txt
java skeleton/Skeleton -s ./level1.txt
else
# a teljes automatizálás még nem működik
# java skeleton/Skeleton ./level*.txt
java skeleton/Skeleton ./level0.txt
java skeleton/Skeleton ./level1.txt
fi
