# Hibák #
Játékos -> irányítás helyett Rabló -> események
Use-casek melyik tesztpályán tesztelődnek?
Jövő héten másik csapat tesztel, ha nem működik ők pontoznak le. Egyértelműnek kell lennie a tesztelésnek. Szekvenciákat társítani kell tesztpályához -> ezzel melyik use case?
Példa hogy néz ki egy leírás, hogyan tud a tesztelő interakcióba lépni a skeletonnal. menük, opciók, példa kimenet.

# Tudnivalók #
Most először programozni kell. Programot kell leadni és doksit.
- Programot herculesen keresztül lehet feltölteni, vannak formai követelmények, csak zipben, forráskód min 20% comment, nem eclipse project, csak az SRC mappa, vagyis csak forrás. Javadoc-t kell használni. Kedd tíz óra előtt KELL leadni MINDKETTŐT. Programot netre, doksit az ib28 mögött. Ha van csúszás naponta 10%. MAXIMUM 3 feltöltés herculesre. Késés esetén: iit titkárság, vagy István. Szólni Istvánnak

# Doksiba #
Fordítási útmutató (ne importálj eclipse és fordítsd le) helyette bat file, ami lefordítja java-val a cuccot. Itt a hszkban kell lefordulnia!
Fájl lista:
fájlnév, utolsó módosítás, mi micsoda
Egyeznie kell a feltöltött listával.
Fordítás: fordítási paraméterek, inkább bat fájl! //eclipse kiírja a fordítási paramétereket, ezt használhatjuk a bat-ban /fordítás: javac futtatás: java pl: java Skeleton
Futtatás: Milyen szekvenciákat hogy tesztelünk, menü leírása, menüpontok összekötése szekvenciával.
Értékelés: Most először kell, ki mit csinált, ki mennyi %-ot fog kapni. Hogy ne legyen csalás ALÁ KELL ÍRNI.

# Jövő heti labor, tesztelés #
Jövő héten minden csapat érkezzen időben, MINDEN csapattag. Random mindenki megkapja egy másik csapat doksiját. Analízis modellek tesztelése. Lesz egy sablon, amit ki kell tölteni. Ha bármi változott az analízis modellben, vagy másban, akkor fel kell tüntetni a változtatást! A pontokat az alapján kapjuk, hogy a végső analízis modell mennyire egyezik a szkeletonnal. A tesztelő csapatnak tudni kell, hol keresse az analízis modellt, meg az esetleges változtatásokat.


# Programming particles #
Log4j helyett saját log osztályt kell írni. (kész?)

Ki mit csinál:
Szilveszter:
- rabló use case diagram
- usecasek leírása 5.1.2 kibaszott nagy hiány jel
Krisztián:
- Lekódolja az összes függvényt, hogy fordítható legyen.
- Minden függvény meghívja loggert, ha szükséges.
- robber vehicle game road policeman osztály legyen kész szombat estére, azért, hogy a Máté ez alapján tudja csinálni a tesztesetek és szekvenciadiagramok összehangolását, és Szilveszter megnézhesse és ellenőrizhesse és esetleg hozzáfűzhessen dolgokat.
- 5.3 nál kell egy példa, hogy hogyan hívódik meg a logger
- ahol elágazás van kérdezze meg a felhasználótól, hogy melyik eset legyen. Pl: trafficlight isBlocking() kérdezze meg, hogy mi a visszatérési értéke.
Máté:
- 5.4 hiánya ergo az összes szekvenciadiagram melyik tesztpálya.
- Skeleton osztály készítése, menü minden tesztpályához. Több szekvenciadiagramhoz több eset
- Egyes tesztpályák összerakása Krisztián által implementált osztályokból.