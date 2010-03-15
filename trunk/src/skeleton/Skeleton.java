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
						while (fileName.equals(""))
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
            logger.setSilent(false);
			logger.logMessage("***");
			logger.logMessage("Test case 1: Initialization");
			logger.logMessage("***");
            logger.setSilent(mainSilent);
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
            logger.setSilent(false);
			logger.logMessage("***");
			logger.logMessage("Test case 2: Check blocking signs");
			logger.logMessage("***");
            logger.setSilent(mainSilent);
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
                String str2 = input.readLine();
                if (str2.compareTo("1") == 0) {
                    car0 = new Policeman("policeman0", cell0, 10, logger, input);
                } else {
                    car0 = new CivilCar("civilcar0", cell0, 10, logger, input);
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
            logger.setSilent(false);
			logger.logMessage("***");
			logger.logMessage("Test case 3: Policeman catches robber");
			logger.logMessage("***");
            logger.setSilent(mainSilent);
			logger.logMessage("Generating test map");
			//logger.setSuperSilent(true);
			//logger.setSilent(true);
			Intersection i1 = new Intersection("i1", logger, input);
			Intersection i2 = new Intersection("i2", logger, input);
			Road r = new Road("r", i1, i2, 2, false, logger, input);
			Policeman p = new Policeman("p",null,10,logger,input);
			i1.setVehicle(p);
			p.setCell(i1);
			Robber robber = new Robber("robber",null,10,logger,input);
			r.placeCar(robber,1);
			robber.setCell(i2);
			
			break;
		// =============================================================================
		// negyedik teszteset: új autó érkezése
		case 4:
            logger.setSilent(false);
			logger.logMessage("***");
			logger.logMessage("Test case 4: Replace vanished car");
			logger.logMessage("***");
            logger.setSilent(mainSilent);
			logger.logMessage("Generating test map");
			logger.setSuperSilent(true);
			logger.setSilent(true);
			game = new Game("game", logger, input);     //tesztpalya epitese
			logger.setSilent(mainSilent);
			logger.setSuperSilent(false);
			logger.logCall(game,game,"regenerateKilledVehicles()");
            game.regenerateKilledVehicles();                             //visszatesszuk az eltavozott autokat a varoshatarra
            logger.logReturn(game,game,"regenerateKilledVehicles()",null);
			break;
		// =============================================================================
		// kilencedik teszteset: Car removes itself from its cell
		case 9:
                logger.setSilent(false);
            	logger.logMessage("***");
		    	logger.logMessage("Test case 9: Car removes itself from its cell");
		    	logger.logMessage("***");
                logger.setSilent(mainSilent);
                logger.logMessage("Generating test map");
                logger.setSuperSilent(true);
                logger.setSilent(true);
                cell0 = new RoadCell("cell0",null,false,logger,input);   //teszt cella letrehozasa
                NamedObject o= new NamedObject("object0",logger,input);  //hivo object letrehozasa
                logger.setSilent(mainSilent);
                logger.logMessage("Policeman or CivilCar?");         //Policeman vagy CivilCar?
                logger.logMessage("0 - CivilCar");
                logger.logMessage("1 - Policeman");                
                    str2 = input.readLine();
                    if (str2.compareTo("1") == 0) {
                        car0 = new Policeman("policeman0", cell0, 10, logger, input);
                    } else {
                        car0 = new CivilCar("civilcar0", cell0, 10, logger, input);
                    }                
                cell0.setVehicle(car0);
                logger.setSuperSilent(false);
                logger.logCall(o,car0,"die()");
                car0.die();                                 //auto megolese
                logger.logReturn(o,car0,"die()",null);
                break;
            // =============================================================================
            // tizedik teszteset: Car slows down behind an other one
            case 10:
                logger.setSilent(false);
                logger.logMessage("***");
                logger.logMessage("Test case 10: Car slows down behind an other one");
                logger.logMessage("***");
                logger.setSilent(mainSilent);
                logger.logMessage("Generating test map");
                logger.setSuperSilent(true);
                logger.setSilent(true);
                cell0 = new RoadCell("cell0", null, false, logger, input);   //elso cella letrehozasa
                cell1 = new RoadCell("cell1", null, false, logger, input);   //masodik cella letrehozasa
                cell0.setNeighbourCells(null, cell1);                        //szomszedsag beallitasa
                cell1.setNeighbourCells(cell0, null);
                logger.setSilent(mainSilent);
                Vehicle car1;
                logger.logMessage("Car on cell0 is Policeman or CivilCar?");  //elso auto rendor vagy civil?
                logger.logMessage("0 - CivilCar");
                logger.logMessage("1 - Policeman");                
                    str2 = input.readLine();
                    if (str2.compareTo("1") == 0) {
                        car0 = new Policeman("cell0_policeman", cell0, 10, logger, input);
                    } else {                                                                  //auto letrehozasa
                        car0 = new CivilCar("cell0_civilcar", cell0, 10, logger, input);
                    }                
                logger.logMessage("Car on cell1 is Policeman or CivilCar?");    //masodik auto rendor vagy civil?
                logger.logMessage("0 - CivilCar");
                logger.logMessage("1 - Policeman");                
                    str2 = input.readLine();
                    if (str2.compareTo("1") == 0) {
                        car1 = new Policeman("cell1_policeman", cell0, 10, logger, input);
                    } else {                                                                   //auto letrehozasa
                        car1 = new CivilCar("cell1_civilcar", cell0, 10, logger, input);
                    }                
                cell0.setVehicle(car0);                             //autok elhelyezese a cellan
                cell1.setVehicle(car1);
                logger.setSuperSilent(false);
                logger.logCall(car0,cell1,"getVehicle()");
                Vehicle v=cell1.getVehicle();                       //kovetkezo cellan allo esetleges auto lekerdezese
                logger.logReturn(car0,cell1,"getVehicle()",v);
                logger.logCall(car0,car0,"accept(Vehicle v)");
                car0.accept(v);                                    //auto megnezi, hogy áll-e ott valaki. (vagy null vagy auto)
                logger.logReturn(car0,car0,"accept(Vehicle v)",null);
                break;
            // =============================================================================
            // érvénytelen választás
            default:
                logger.logMessage("There is no such test case.");
			break;
		}
	}
}
