# Változtatás van #
  * A nyuszi-interakció szekvenciák kellenek: Nyuszi vs. civil, rendőr, robber
  * Nyuszigenerálás-interakciók (mikor, hol, meddig, merre)
  * Erre kell egy teszteset

## Új mintapélda is van ##
  * http://users.hszk.bme.hu/~si648/minta1.pdf without napló, gyakvezé
  * http://users.hszk.bme.hu/~si648/minta2.pdf régifajta minta

## Új leadandók ##

### Osztályok ferlépítése ###
  * Osztályok és metódusok tervei: több állapotos osztályok: state chart az elejére!
  * UML jelölésű rendszer
  * Activity diagram (?)
  * Getter setter attribútumokat is meg kell szülni

### Tesztesetek részletes esetei ###
  * Mi mit tesztel, hogyan tesztel, satöbbi.
  * Milyen bemenet, milyen kimenet, mit fogunk látni, a program mit fog csinálni
  * **Viszonylag komoly**, ezzel a bemenettel kell összevetni a kimenetünket, pontosan így kell kiírni minden sz@rt, módosítás _lehet_, de törekedjünk a kevés módosításra

# ki mit csinál: #

**Szilveszter:** Anal modell 2, belerakjuk, leadjuk a változtatást; player legyen az aktor, ne a menü, nem szabad elfelejtened a USE CASE diagramot

**Máté:** Időbélyegek javítása (tick, vagy time stamp), lámpa léptetése, robber state chart, bunny lerakása (rendőr lerakás), bunny 20 lépésenként lerakás, lámpa léptetés teszteset, bunny-s teszteset(ek) _ezt a mai pénteki nap folyamán megcsinálja_ a működésspecifikálás pontosítása pl hogy mi alapján hasonlítjuk össze a fájlokat, randomCommand missing

ki kell talalni valami amihey csinalsy activity diagramot (hetfore)

**Balázs:** JavaDoc egy dokumkentumfájlba, class diagram alapján rizsázni majd bebaszni (nem piával, hanem a source code-ba)

**Krisztián:** elvárt tesztesetek kimenetei, és azt is amit a Máté megcsinál (_mára_ (péntek) - (ha bajod van a nyelvezettel, módosíthatod, hogy neked jó legyen E) - ehhez jó lenne, ha kb. a progi már működne, legalábbis tudja ezeket a kiírandókat