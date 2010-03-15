package skeleton;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Skeleton {
    /**
	 * Szkeleton main függvénye <br>
	 * Egy menüt jelenít meg, ahol kiválaszthatjuk a tesztelés bemenetét és
	 * a tesztesetet. Paraméterként kapott fájl esetén indítja az automatikus tesztelést.
	 * 
	 * @param args
	 *            parancssori argumentumok - silent mód kapcsoló, input fájl neve
	 */
    public static void main(String[] args) {
        // ezt a loggert használjuk az egész program során
        Logger logger = new ConsoleLogger();
        // saját bemeneti olvasó osztály: konzolról és fájlból is tud sort olvasni, kommenteket (#-val kezdődő sor) nem veszi figyelembe
        CustomReader input = new CustomReader(logger);
        // parancssori paraméterek elemzése
        boolean silent = false; // silent módban automatikus tesztelésnél nem írja ki a kérdéseket
        String fileName = ""; // tesztelés bemeneteként használt input fájlok neve
        if (args.length > 0) for (String str : args) {
            if (str.charAt(0) == '-') {
                if ((str.length() > 1) && (str.charAt(1) == 's')) silent = true;
            } else {
                fileName = str;
            }
        }
        // program fejléc kiírása
        logger.logMessage("Software laboratory 4");
        logger.logMessage("Team vihar");
        logger.logMessage("Skeleton");
        logger.logMessage("---");

        // input forrásának meghatározása
        try {
            // ha adtunk meg parancssori paraméterként fájlt, beállítjuk
            // bemenetnek
            if (fileName.compareTo("") != 0) {
                logger.logMessage("Input file given as command line argument: " + fileName);
                input.setInput(new BufferedReader(new FileReader(fileName)));
                if (!silent) input.setEcho(true);
                // ha nem, akkor választhatunk konzol és fájl közül
            } else {
                // a választ konzolról olvassuk, ehhez a reader
                BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
                // menü megjelenítése
                logger.logMessage("Please choose input stream:");
                logger.logMessage("1 - console");
                logger.logMessage("2 - file");
                // válasz beolvasása
                String str = in.readLine();
                // alapértelmezett választás a konzol, érvénytelen válasz esetén
                // is
                input.setInput(new BufferedReader(new InputStreamReader(System.in)));
                if (str.compareTo("1") == 0) {
                    // ha konzolt választottunk
                    System.out.println("You've chosen to give input manually from command line.");
                    silent = false;
                } else if (str.compareTo("2") == 0) {
                    // ha fájlt választottunk, bekérjük a nevét
                    System.out.println("You've chosen to give input via a text file.");
                    System.out.print("Please enter the name of the input file: ");
                    try {
						// fájlnév beolvasása
						while (fileName == "")
							fileName = in.readLine();
						// beállítás bemenetnek
						input.setInput(new BufferedReader(new FileReader(
								fileName)));
						// ha a kérdéseket megjelenítjük, a válaszokat is: echo mód
						if (!silent)
							input.setEcho(true);
					} catch (IOException e) {
						e.printStackTrace();
						System.exit(1);
					}
                } else {
                    // ha érvénytelen (nem 1 v 2) választ ütöttünk be, a
                    // következő üzenetet jelenítjük meg
                    logger.logMessage("Your choice is not valid.");
                    logger.logMessage("Console assumed.");
                }
            }
            logger.setSilent(silent);

            // teszteset kiválasztása
            logger.logMessage("Please choose a test case:");
            logger.logMessage("1 - Initialize game");
            logger.logMessage("2 - Check blocking signs");
            logger.logMessage("3 - Policeman catches robber");
            logger.logMessage("4 - Replace vanished car");
            logger.logMessage("5 - Robber chooses next cell");
            logger.logMessage("6 - Robber crashes into a car");
            logger.logMessage("7 - Robber reaches hiding place");
            logger.logMessage("8 - Car chooses next cell");
            logger.logMessage("9 - Car removes itself from its cell");
            logger.logMessage("10 - Car slows down behind an other one");
            logger.logMessage("11 - Car moves to next cell");
            logger.logMessage("12 - Car exits the city");
            // teszteset lefuttatása
            test(Integer.valueOf(input.readLine()), logger, input);
            logger.setSilent(false);
            logger.logMessage("---");
            logger.logMessage("Skeleton test finished succesfully!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

	/**
	 * Kiválasztott teszteset lefuttatása.
	 * 
	 * @param testCase Teszteset sorszáma
     * @param logger   logger, amibe az üzeneteket írjuk.
     * @param input    stream, ahonnan az üzeneteket olvassuk.
     * @throws IOException beolvasási hibánál
	 */
	private static void test(int testCase, Logger logger, CustomReader input)
			throws IOException {
		logger.setSuperSilent(false);
		boolean mainSilent = logger.getSilent();
		switch (testCase) {
		// =============================================================================
		// első teszteset: inicializálás
		case 1:
			logger.logMessage("***");
			logger.logMessage("Test case 1: Initialization");
			logger.logMessage("***");
			// játék objektum létrehozása
			logger.logMessage("Creating game object...");
			Game game = new Game("game", logger, input);
			logger.logMessage("[" + game.getName() + "|Game|" + game.hashCode()
					+ "] successfully created.");

			// pálya adatainak bekérése

			// bejáratok száma
			logger.logMessage("Please specify the number of city entries:");
			int nEntries = input.readInt();
			// kijáratok száma
			logger.logMessage("Please specify the number of city exits:");
			int nExits = Integer.valueOf(input.readLine());
			// útelágazások száma
			logger.logMessage("Please specify the number of intersections:");
			int nIntersections = Integer.valueOf(input.readLine());
			// utak száma
			logger.logMessage("Please specify the number of roads:");
			int nRoads = Integer.valueOf(input.readLine());
			// autók száma
			logger.logMessage("Please specify the number of civil cars:");
			int nCivilCars = Integer.valueOf(input.readLine());

			// pálya legenerálása
			logger
					.logMessage("Calling game.generateLevel(int nEntries, int nExits, int nIntersections, int nRoads, int nCivilCars)");
			game.generateLevel(nEntries, nExits, nIntersections, nRoads,
					nCivilCars);
			break;
		// =============================================================================
		// második teszteset: tábla/lámpa blokkolásának ellenőrzése
		case 2: 
			logger.logMessage("***");
			logger.logMessage("Test case 2: Check blocking signs");
			logger.logMessage("***");
			logger.logMessage("Generating test map");     //tesztpályát generálunk
            logger.setSuperSilent(true);
            RoadCell cell0 = new RoadCell("cell0", null, false, logger, input);  //egy üres cella
            RoadCell cell1 = new RoadCell("cell1", null, true, logger, input);    //egy táblás cella
            cell0.setNeighbourCells(null, cell1);
            cell1.setNeighbourCells(cell0, null);
            logger.logMessage("Policeman or CivilCar?");      //választhatunk, hogy rendőr, vagy civil
            logger.logMessage("0 - CivilCar");
            logger.logMessage("1 - Policeman");
            Vehicle car0;
            try {
                String str2 = input.readLine();
                if (str2.compareTo("1") == 0) {
                    car0 = new Policeman("policeman0", cell0, 10, logger, input);
                } else {
                    car0 = new CivilCar("civilcar0", cell0, 10, logger, input);
                }
            } catch (IOException e) {
                e.printStackTrace();
                car0 = null;
            }
            logger.setSuperSilent(false);
            logger.logCall(car0, cell1, "getSign()");
            ISign sign = cell1.getSign();                   //lekérjük a cella tábláját.
            logger.logReturn(car0, cell1, "getSign()", sign);
            if (sign != null) {
                logger.logCall(car0, sign, "isBlocking()");
                boolean blocking = sign.isBlocking();         //ha van blokkol-e
                logger.logReturn(car0, sign, "isBlocking()", new NamedObject((Boolean.valueOf(blocking)).toString(), logger, input));
                if (blocking) logger.logMessage("Cell is blocked");
                else logger.logMessage("Cell is free.");
            } else {
                logger.logMessage("No blocking signs on next cell");
            }
            break;
		// =============================================================================
		// harmadik teszteset: bankrabló elkapása
		case 3:
			logger.logMessage("***");
			logger.logMessage("Test case 3: Policeman catches robber");
			logger.logMessage("***");
			logger.logMessage("Generating test map");
			//logger.setSuperSilent(true);
			//logger.setSilent(true);
			Intersection intersection1 = new Intersection("intersection1", logger, input);
			Intersection intersection2 = new Intersection("intersection2", logger, input);
			Road road = new Road("road", intersection1, intersection2, 2, false, logger, input);
			break;
		// =============================================================================
		// negyedik teszteset: új autó érkezése
		case 4:
			logger.logMessage("***");
			logger.logMessage("Test case 4: Replace vanished car");
			logger.logMessage("***");
			logger.logMessage("Generating test map");
			logger.setSuperSilent(true);
			logger.setSilent(true);
			game = new Game("game", logger, input);
			game.generateLevel(1, 0, 1, 0, 0);
			logger.setSilent(mainSilent);
			logger.setSuperSilent(false);
			logger.logCall(game,game,"regenerateKilledVehicles()");
            game.regenerateKilledVehicles();
            logger.logReturn(game,game,"regenerateKilledVehicles()",null);
			break;
		// =============================================================================
		// kilencedik teszteset: Car removes itself from its cell
		case 9:
                logger.logMessage("Generating test map");
                logger.setSuperSilent(true);
                logger.setSilent(true);
                cell0 = new RoadCell("cell0",null,false,logger,input);
                NamedObject o= new NamedObject("object0",logger,input);
                logger.setSilent(mainSilent);
                                logger.logMessage("Policeman or CivilCar?");
                logger.logMessage("0 - CivilCar");
                logger.logMessage("1 - Policeman");
                try {
                    String str2 = input.readLine();
                    if (str2.compareTo("1") == 0) {
                        car0 = new Policeman("policeman0", cell0, 10, logger, input);
                    } else {
                        car0 = new CivilCar("civilcar0", cell0, 10, logger, input);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                    car0 = null;
                }
                cell0.setVehicle(car0);
                logger.setSuperSilent(false);
                logger.logCall(o,car0,"die()");
                car0.die();
                logger.logReturn(o,car0,"die()",null);
                break;
		// =============================================================================
		// érvénytelen választás
		default:
			logger.logMessage("There is no such test case.");
			break;
		}
	}
}
