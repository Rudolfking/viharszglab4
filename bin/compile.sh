#!/bin/bash

# Ubuntu 9.10 alatt tesztelve

echo "Compiling Prototype..."
if javac ../src/proto/*.java -d . -nowarn ; then
	echo "Compilation done."
else
	echo "There were some errors during compilation!"
fi 
