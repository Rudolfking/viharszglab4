# Következő feladat #
  * A **Skeleton** következik
  * Jelentése: Megvan az egész program váza, az összes osztály, az összes metódus, attribútum nem kell...
  * De: nincs benne _algoritmus_, implementáció
  * Az a szerepe, hogy helyes-e az elképzelés, amit mi analízis modellben kidolgoztunk
  * Maximum egy kiírás lehet, nem lehet az osztálynak belső állapota
  * Bármilyen belső döntés esetén (mit ad vissza): a felhasználótól bekéri
  * Kiír valamit (ezt szépen **kell** megcsinálni)! Kit hívott meg, ki, milyen paraméterekkel, valamint be lehet tabolni mélységileg
  * Egyezés a szekvenciadiagramokkal 100%-os legyen!


---


## Tippek az elkészítéshez ##
  * Generálni, vagy szerkezetet írni
  * Metódusok létrehozása
  * Metódusokhoz létrehozunk egy Logger, Napló... osztályt, amit ha meghívunk, kiírja képernyőre, fájlba
  * Metódusok belsejébe: CSAK kiloggol valamit
  * döntések helyére kérdéseket tesz fel a felhasználó felé (menü, melyik döntés mit jelent, ő meg választhat)
  * Új osztály, mini metódus: Skeleton. Ebben felépítünk egy kis játékrészt (**KICSIT!**), ott pedig lejátszunk valamit (2 mező, autó lép egyet, aztán ennyi)
  * Eszerint az összes szekvenciát és use-case-t tesztelni kell


---


### A szkeleton modell valóságos use-case-ei ###
  * Haonlít a valóságra, nem egyszerű
  * Időzítő, vehicle, működések + leírás

### Architektúra ###
  * Hogyan a szálak, mi a szinkronizálás (?)
  * Tesztpályák, azoknak a leírása
  * A program mindent megkérdez, Te választasz, és akkor ő cselekszik
  * Akár még azt is megkérdezi, hogy azon a mezőn van-e valami
  * 1-2-5-3-1-2 lenyomásokra pl. működik-e a szekvenciadiagram
  * Egy másik megoldás: Előjön egy menü, melyik tesztet szeretnénk tesztelni - meg van adva, ki mit fog adni: ebben az esetben le kell dokumentálni, milyen pályán mik vannak

### Kezelőfelület, tervdialógusok ###
  * Mik a bemenetei, konzol, menük, mit lehet leütni, na éés a kimenet: milyen kimenetet várunk - pl: " ` ->a lepj() ... ` "

### Szekvenciadiagramok belső működése ###
  * Use-case-knél mit tesztelünk
  * Itt ezt hogyan lehet előhozni, ez a szekvenciák tesztelése, amit korábban megoldottunk

Pontozás: Terv (0-20 pont), aztán a beadás (0-20 pont) ahol a saját specifikációnak kell tökéletesen megfelelni

---


---

# Feladatok: #

**Szilveszter**: _"Én nem csinálok semmit!"_ amúgy hiba-javítások, Use Case szerkesztése

**Krisztián**: -

**Balázs**: Kódgenerálás, rizsafőzés, előző doksi javítása

**Máté**: Ősszes teszteset részletes, pontos kidolgozása az előre specifikáltaknak megfeleően minden szekvenciára lefedve, valamint kódol

Időjárás: Sok hó esik