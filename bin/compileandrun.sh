#!/bin/bash

# Ez a szkript a szkeletont lefordítja, majd az összes talált
# teszteset-fájlra lefuttatja.
# Ubuntu 9.10 alatt tesztelve

# felhasználjuk a két külön szkriptet
sh ./compile.sh
sh ./runalltestcases.sh
