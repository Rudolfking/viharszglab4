# Feladatleírás #

## Mi legyen a  játék ##

  * Irányítjuk a bankrablót, senki mást
  * El kell jutnia a bankból a rejtekhelyre
  * Ütközés: A többi autóval ütközhet, vagy a rendőrrel
  * Bankrabló és rendőr megfordulhat az úton
  * Rendőr van a közelben (az utcában), meghalsz (mittom le tud lőni duma)
  * Ütközés esetén Game Over
  * Győzelem, ha eléri a rejtekhelyet (1 db). Győzelem után új játék, kilépés
  * Felhasználótól bekéri: Hány út, hány autó, nyelők, források
  * Térkép: (átfedéssel)Előre megírt + random (egyszerű): ezen belül van "városhatár", azaz nyelők és források is
  * Indításkor egy "menüpont": Új játék

## Térkép felépítése ##

  * Vannak csomópontok (forrás, nyelő, kereszteződés)
  * Út: cellákból áll (ez adja ki a hosszát), és összeköt két csomópontot
  * Csomópontnál
  * Vannak táblák az út végén (egy út végén egy kresz-szabály tábla (lámpa vagy stop tábla))
  * Bejárható minden csomópont, mindenhonnan

## Közlekedési KRESZesek működése ##

  * Ha piros: Megáll az illető, ha zöld: Továbbmegy, ha szabad a kereszteződés
  * Stop táblánál: Megáll egy időre, (majd ha szabad) tovább indul
  * Utcák egyirányúak (ezt nem jelöli tábla)

## Bankrabló ##

  * Menekül a rejtkhelyre, mi irányítjuk
  * Visszafordulhat és megállhat (csomópontban is)
  * Nem áll meg semmi KRESZ korlátozásnál
  * Meghal, ha ütközés vagy rendőr-környezet van (egy utcában vannak)
  * A bankrabló a saját sebességével halad
  * A torlódás esetén hátulról nem rongyolhat bele az autóba, csak szemből

## Rendőr ##

  * _Sima autó_
  * Egy úton a bankrablóval elkapja őt (Game over, lelövi)

## Speciális helyek ##

  * Legyen ez egy csomópont (nem nyelő, nem forrás, kereszteződés)

## Irányítás ##

  * Billentyűzetről (PacMan style), külön megfordulás, külön megállás gomb
  * 2 menüpont azt csá