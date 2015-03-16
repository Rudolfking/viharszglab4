# Grafikus rendszer #
  * Pár screenshot

## Tervek ##
  * Új objektumokat tartalmazza egy új class diagram
  * Ki kivel, mi mivel kommunikál (nem az egészet, csak az újat, és azt, hogy ki kivel szövegel)

## Működési elv ##
  * Tilos: Van egy osztály, aki mindenkit kirajzol (meg lehetne, de **NE**)
  * Minden osztálynak egy kirajzoló osztályt írni: Mittom _VehicleDrawer_
  * Nem az autó rajzolja ki magát, hanem egy más osztályba!
  * A változtatás legyen minimális: Pl egy elem tárol egy referenciát arról, hogy ki mit rajzol ki (ősosztályon vagy máson keresztül - dinamikusan változtatható!)

## Grafikus objektumok felsorolása ##
  * Osztályok, felelősségek, ősosztályok, interfészek, attribútumok, _csak az újakat!_
  * CSak az új találmányokat, módosításokat se

## Kapcsolat az alk. rendszerrel ##
  * Hogyan rajzolódik ki, stb. Szekvenciadiagramok, ilyesmik kellenek ide
  * Logikusan megcsinált dolgok: nem kellm, hogy látszódjanak

Hogyan rajzoljuk ki a megfelelő helyre, xy koordinátákkal. Minden osztályhoz külön osztályban a rajzoló függvények. Ha valami változik: Csak a változást rajzoljuk ki. Töröl-visszarajzol-újrakirajzol. Tulajdonképpen ennyi.