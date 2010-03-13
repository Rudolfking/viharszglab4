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
		boolean silent = false; // silent módban automatikus tesztelésnél nem írja ki a kérdéseket
		String fileName = "";	// tesztelés bemeneteként használt input fájl neve
		if (args.length > 0) 
			for(String str : args) {
				if(str.charAt(0)=='-') {
					if((str.length()>1)&&(str.charAt(1)=='s'))
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

			// ha adtunk meg parancssori paraméterként fájlt, beállítjuk bemenetnek
			if (fileName.compareTo("") != 0) {
				logger.logMessage("Input file given as command line argument.");
				input.setInput(new BufferedReader(new FileReader(fileName)));
				if(!silent)
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

				if (str.compareTo("1")==0) {
					// ha konzolt választottunk
					System.out
							.println("You've chosen to give input manually from command line.");
				} else

				if (str.compareTo("2")==0) {
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
						if(!silent)
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
			logger.logMessage("");
			logger.logMessage("1- initialization");			
			
			// teszteset lefuttatása
			test(Integer.valueOf(input.readLine()),logger,input);

			logger.setSilent(false);

			logger.logMessage("---");
			logger.logMessage("Skeleton test finished succesfully!");

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	private static void test(int testCase, Logger logger, CustomReader input) throws IOException {		

		switch(testCase) {
		// első teszteset: inicializálás
		case 1:
			// játék objektum létrehozása
			logger.logMessage("Creating game object...");
			Game game = new Game("game", logger, input);
			logger.logMessage("[" + game.getName() + "|Game|" + game.hashCode() + "] successfully created.");

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
			logger.logMessage("Calling game.generateLevel(int nEntries, int nExits, int nIntersections, int nRoads, int nCivilCars)");
			game.generateLevel(nEntries, nExits, nIntersections, nRoads, nCivilCars);
			break;
		default:
			logger.logMessage("There is no such test case.");
			break;
		}
	}
}
