## Előző heti micsodák ##
  * Ne legyünk poénosak
  * Rendszerkövetelmények nem jók (HSZK gépeken fusson el)
  * Hivatalos doksi!

# Mostani leadandó #
  * Ez az alapja, nagyon fontos
  * A progi vázát határozzuk meg (objektumok, kommunikáció)
  * Nagyon fontos
  * Módosítási lehetőség a dokumentációban: Év végén leadott dokumentáció koherens, nincs benne hiba (!), **tilos** az utolsó hétre hagyni. Le kell követni a hibákat, és majdan kijavítani
  * Változtatott doksit intelligensebb újra leadni
  * Nem kell feltétlenül jelezni egy változtatást (3 beadanós speciális óra: egymás programjait kipróbáljuk, leteszteljük, ledokumentáljuk - ilyenkorra kell a friss doksi)
  * Ragaszkodjunk az e-mailben elküldéshez (_is_)
  * 0. pontnak **_Melléklet_** -ként adjuk meg a változtatásokat!

## Egyes pontkra bontva a beadandót (2.) ##
  * Osztályok leírása _hosszú lista_(összes attribútum, összes függvény, leírás
  * Absztrakt osztályok, interfészek is látszódjanak, ki kinek ősosztálya
  * Objektum-katalógus (mire való, mit reprezentál, _"ez a rablójátékos, stb."_)
  * **Magyar, vagy angol** legyen a programopzás nyelve? (nekünk _angol_ basszus)
  * Statikus struktúra-diagramok: Nagy-nagy osztálydiagramm (class diagram)
  * Legyen konzisztens (mi hol, miért?)
  * **Nyilak iránya** legyen jó!!! Osztály-ősök, függőségek, kompozíció
  * **UML2** kell (UML 1 belefér)   pl. # protected, ilyenek

### Szekvencia-diagramok ###
  * Kb **20 darab** kell (mi a WTF?)
  * Összes felmerülő kérdésre szekvenciadiagram! _pl. amik külön metódushívás, az már egy szekvenciadiagram_
  * Magyarázat, mit, miért, hogyan akarjuk használni (nem bővülhet a specifikáció)
  * ha egy osztály meghív egy másik osztály metódusát - akkor **ISMERNIE kell**

### State-chart ###
  * **Csak egyetlen objektum állapota!**
  * Játék állapotdiagramja TILOS, ez nem egy state chart
  * Konkrétan egyes objektumoké (pl. _közlekedési lámpa_)
  * Relatíve kevés state chart lesz a feladat típusa miatt

### Egyebek ###
  * Napló maradjon, formai előírásoknak megfeleljen
  * Fontos a formaiság

# Modellezés #
  * Mit várunk egy modelltől? Pontozás a gyakvezér által (így nincs koherencia a gyakvezérek között)
  * Csak magának a játékmodellnek a működése (se _menü, highscores, help, egér, mouse controller, bármilyen irányítás_ nem lehet benne) alkotja a modellt, ez kib@&%+#tt fontos - még az se, aki irányítja a játékot (óra)
  * Néhány irányelv: Felejtsük el a _folytonos_ modelleket: pl. nyomva tartom a gombot, gyorsul az autó (ezek grafika!)
  * Sokkal inkább OO szemlélet, diszkrét modell (mező, mezőn az autó, nincsen autó a mezők között)
  * Szálkezelés: Tesztelni kell! Meg kell, hogy legyen határozva, hogy ki mikor milyen módon, hogyan lép (tehát irányítani nem fogunk mi semmit a grafikus verzióig)
  * Sebesség az most mit jelent? _Km/h? Hány lépésenként kapunk lépésutasítást?_
  * **Nincs isten-objektum**! (Jelentése: egyben minden és ő mozgat mindent)
  * Ha van egy autó, csak ő tudja a saját sebességét (ez egy példa az előző pontra)
  * Ha van egy mező, tudja, milyen autó áll rajta, de egyetlen másik autóról sem tud semmit
  * Lehetőleg tesztelésnél négyszögletű kanyarok legyenek, meg ilyenek - így érthető és tiszta a modell és tesztelése (más _valid_ modell is jó)
  * Legyen meg attribútumban, hogy akkor most mi van (3szög alakú pálya, 4szög alakú pálya, anyámkinnya stb.)


# Modell-fejlesztési minták #

## Strategy ##
  * Hogy képzeljük el pl. az autók mozgatását (az autó tudja az irányát és mozgatását. Pl.: kereszteződésbe behajthat-e? Valami külső objektum **NEM** határozhatja meg, kereszteződés vizsgálja meg (nem olyan szép), autó tudja (ez a szép megoldás), mert ugye a rabló sz.rik az egészre
  * **Egyszóval a döntést az autó hozza meg, ne a kereszteződés**
  * Ütközés-detekció hogyan történjen: Autó _dönjti el_, átlépnek másik cellára

## Visitor-acceptor ##
  * Neten ne nézzünk utána, mert bonyolultabb
  * Autó dönti el az ütközést: Rá akar lépni egy mezőre (pl. mező.visit() függvény - ami visszaad valami más objektumot (ami rajta van, ha nincs, akkor pl. null) - meghívja az autó.Accept() függvényt => ebből többféle van, van, ami null-t vár, autót, rendőrt, meg ilyeneket és itt kezeljük le, hogy mit tegyen az előtte lévő objektummal (rendőr rendőrt elüt-e, bankrabló esetén ugye belerongyolunk, az objektum "továbbenged")
  * **INSTANCEOF TILOOOOS** nem szabad, lásd fentebbi megoldást, kiraknak érte a laborról :)
  * minden különbség külön függvényt igényel (és ne legyen _ki vagyok én_ függvény)