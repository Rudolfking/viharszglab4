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
		CustomReader input = new CustomReader();

		// program fejléc kiírása
		logger.logMessage("Software laboratory 4");
		logger.logMessage("Team vihar");
		logger.logMessage("Skeleton");
		logger.logMessage("---");

		// input forrásának meghatározása
		try {

			// ha adtunk meg parancssori paramétert, azt bemeneti fájlnak veszi
			if (args.length > 0) {
				logger.logMessage("Input file given as command line argument.");
				input.setInput(new BufferedReader(new FileReader(args[0])));
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

				if (str == "1") {
					// ha konzolt választottunk
					System.out
							.println("You've chosen to give input manually from command line.");
				} else

				if (str == "2") {
					// ha fájlt választottunk, bekérjük a nevét
					System.out
							.println("You've chosen to give input via a text file.");
					System.out
							.print("Please enter the name of the input file: ");
					String fileName = "";
					try {
						while (fileName == "")
							fileName = in.readLine();
						input.setInput(new BufferedReader(new FileReader(
								fileName)));
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

			// játék onjektum létrehozása
			Game game = new Game("game", logger, input);
			
			// pálya adatainak bekérése

			// útelágazások száma
			logger.logMessage("Please specify the number of intersections:");
			int nIntersections = Integer.valueOf(input.readLine());
			// utak száma
			logger.logMessage("Please specify the number of roads:");
			int nRoads = Integer.valueOf(input.readLine());

			// pálya legenerálása
			game.generateLevel(nIntersections, nRoads);

			logger.logMessage("---");
			logger.logMessage("Skeleton test finished succesfully!");

		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}
