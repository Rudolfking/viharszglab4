=======================================
Szoftver labor 4
GUI beadandó
vihar csapat
2010 tavasz
=======================================

---------------------------------------
a játék célja:
---------------------------------------

A bankrablót irányítva eljutni a bankból a rejtekhelyig.

---------------------------------------
irányítás:
---------------------------------------

nyilak:
  bal-jobb:  annak a kiválasztása, hogy a következő kereszteződésből merre menjen tovább az autó
  fel:       sebesség növelése
  le:        sebesség csökkentése
backspace:   megfordulás (megartja a sebességet)

---------------------------------------
állapot kijelzése:
---------------------------------------
Azt, hogy a következő kereszteződésből merre fog továbbmenni az autónk, a kiválasztott út mellett két vékony sárga csík jelzi.
Az aktuális sebességet a képernyő bal oldalán egy fekete sávban piros csík jelzi. A sebességnek (a megállással együtt) összesen 5 fokozata van.
Ha a húsvéti nyuszi elütése következtében autónk "immunitást" élvez a rendőrrel szemben, azt egy sárga ötágú csillag jelzi közvetlenül a sebességjelző alatt.

---------------------------------------
a játéktér elemei:
---------------------------------------
csomópontok: (szürke kör)
  > fehér körvonallal: bejárat a városba (innen érkeznek autók)
  > fekete körvonallal: kijárat a városból (az ide érkező autók elhagyják a várost)
  > sárga körvonallal: kereszteződés
  > piros körvonallal: bank
  > kék körvonallal: rejtekhely
utak: (a csomópontokat összekötő szürke téglalapok)
  Az úton fehér nyilak jelzik, hogy hány mezőből áll az út, illetve, hogy melyik irányba egyirányú.
járművek:
  A járművek haladási irányának megállapítását az elejükön elhelyezett két sárga, a hátuljukon pedig két piros lámpa segíti.
  > fehér autó: civil. 
    - más autó mögé kerülve nyugodtan várakozik annak továbbhaladására
    - az utak egyirányúságának megfelelően közlekedik
    - stop tábla előtt egy kis időre megáll
    - a jelzőlámpán csak zöld jelzés esetén halad át
  > kék autó: a rendőr. 
    - ha egy utcába kerül a bankrablóval, elkapja
    - más autó mögé kerülve nyugodtan várakozik annak továbbhaladására
    - az utak egyirányúságának megfelelően közlekedik
    - a közlekedési jelzéseket (tábla, lámpa) NEM veszi figyelembe
  > piros autó: a bankrabló. 
    - őt irányítjuk.
    - a szabályokat (közlekedési jelzéseket és az utak egyirányúságát) nem kell figyelembe vennie
    - más autóval ütközhet, ez esetben elveszítjük a játékot
közlekedési jelzések:
  Minden közlekedési jelzés egy út végén, az út jobb oldalán található.
  > piros tábla szürke oszlopon: stop tábla
  > fekete, két állapotú jelzőlámpa
egyéb:
  > húsvéti nyuszi (három kicsi fehér ellipszis)
    - ha a bankrabló elüti, egy időre immunitást élvez a rendőr elkapási kísérletei ellen, de az autókkal való ütközés ellen NEM!
