#!/bin/bash

# Ez a szkript lefordítja szkeletont.
# Ubuntu 9.10 alatt tesztelve

# -v parancsori paraméter esetén verbose paraméterrel fordít
VERBOSE=false
# parancssori paraméterek elemzése
while getopts v inst
do
  case $inst in
	(v) VERBOSE=true ;;
  esac
done

# forrás lefordítása
echo "Compiling Skeleton..."
# a "-d ." paraméter azért kell, hogy ide a bin mappába tegye 
# a class fájlokat, ne a forrás mellé
if $VERBOSE; then
javac ../src/skeleton/*.java -verbose -d .
else
javac ../src/skeleton/*.java -d .
fi
echo "Compilation done."
