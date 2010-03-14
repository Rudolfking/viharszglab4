package skeleton;

import java.io.*;

public class Skeleton {

	/**
	 * Szkeleton main függvénye <br>
	 * Konzolon vagy fájlból adott bemenetek alapján teszteli a különböző
	 * eseteket.
	 * 
	 * @param args
	 *            parancssori argumentumok - input fájl neve
	 * @return
	 */
	public static void main(String[] args) {

		// ezt a loggert használjuk az egész program során
		Logger logger = new ConsoleLogger();
		// saját bemeneti olvasó osztály: konzolról és fájlból is tud sort
		// olvasni,
		// kommenteket (#-val kezdődő sor) nem veszi figyelembe
		CustomReader input = new CustomReader(logger);

		// parancssori paraméterek elemzése
		boolean silent = false; // silent módban automatikus tesztelésnél nem
		// írja ki a kérdéseket
		String fileName = ""; // tesztelés bemeneteként használt input fájlok neve
		if (args.length > 0)
			for (String str : args) {
				if (str.charAt(0) == '-') {
					if ((str.length() > 1) && (str.charAt(1) == 's'))
						silent = true;
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
				if (!silent)
					input.setEcho(true);
				// ha nem, akkor választhatunk konzol és fájl közül
			} else {
				// a választ konzolról olvassuk, ehhez a reader
				BufferedReader in = new BufferedReader(new InputStreamReader(
						System.in));

				// menü megjelenítése
				logger.logMessage("Please choose input stream:");
				logger.logMessage("1 - console");
				logger.logMessage("2 - file");

				// válasz beolvasása
				String str = in.readLine();

				// alapértelmezett választás a konzol, érvénytelen válasz esetén
				// is
				input.setInput(new BufferedReader(new InputStreamReader(
						System.in)));

				if (str.compareTo("1") == 0) {
					// ha konzolt választottunk
					System.out
							.println("You've chosen to give input manually from command line.");
					silent=false;
				} else

				if (str.compareTo("2") == 0) {
					// ha fájlt választottunk, bekérjük a nevét
					System.out
							.println("You've chosen to give input via a text file.");
					System.out
							.print("Please enter the name of the input file: ");
					try {
						while (fileName == "")
							fileName = in.readLine();
						input.setInput(new BufferedReader(new FileReader(
								fileName)));
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

	private static void test(int testCase, Logger logger, CustomReader input)
			throws IOException {
		logger.setSuperSilent(false);
		switch (testCase) {
		// első teszteset: inicializálás
		case 1:
			// játék objektum létrehozása
			logger.logMessage("Creating game object...");
			Game game = new Game("game", logger, input);
			logger.logMessage("[" + game.getName() + "|Game|" + game.hashCode()
					+ "] successfully created.");

			// pálya adatainak bekérése

			// bejáratok száma
			logger.logMessage("Please specify the number of city entries:");
			int nEntries = Integer.valueOf(input.readLine());
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
		case 2: //TODO valsztani lehet civilcar es rendor kozott
			logger.logMessage("Generating test map");
			logger.setSuperSilent(true);
			RoadCell cell0=new RoadCell("cell0",false,logger,input);
			RoadCell cell1=new RoadCell("cell1",true,logger,input);
			cell0.setNeighbourCells(null, cell1);
			cell1.setNeighbourCells(cell0, null);
			CivilCar car0=new CivilCar("car0",cell0,10,logger,input);
			logger.setSuperSilent(false);
			logger.logCall(car0, cell1,"getSign()");	
			ISign sign=cell1.getSign();
			logger.logReturn(car0, cell1, "getSign()", sign);
			if (sign!=null)
			{
				logger.logCall(car0, sign, "isBlocking()");
				boolean blocking=sign.isBlocking();
				logger.logReturn(car0, sign, "isBlocking()", new NamedObject((new Boolean(blocking)).toString(),logger,input)); //TODO mivan, ha nem named object a return pl bool ? Erre gondoltam
				if (blocking==true) logger.logMessage("Cell is blocked"); else logger.logMessage("Cell is free.");
			}
			else
			{
				logger.logMessage("No blocking signs on next cell");
			}
			break;
		default:
			logger.logMessage("There is no such test case.");
			break;
		}
	}
}
