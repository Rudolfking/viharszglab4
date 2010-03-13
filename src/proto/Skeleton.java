package proto;
import java.io.*;

public class Skeleton {

	public static void main(String[] args) {

		Logger logger = new ConsoleLogger();
		CustomReader input = new CustomReader();

		logger.logMessage("Software laboratory 4");
		logger.logMessage("Team vihar");
		logger.logMessage("Skeleton");
		logger.logMessage("---");

		try {

			if (args.length > 0) {
				logger.logMessage("Input file given as command line argument.");
				input.setInput(new BufferedReader(new FileReader(args[0])));
			} else {
				BufferedReader in = new BufferedReader(new InputStreamReader(
						System.in));

				logger.logMessage("Please choose input stream:");
				logger.logMessage("1 - console");
				logger.logMessage("2 - file");

				String str = in.readLine();
				int i = Character.getNumericValue(str.charAt(0));

				input.setInput(new BufferedReader(new InputStreamReader(
						System.in)));

				if (i == 1) {
					System.out
							.println("You've chosen to give input manually from command line.");
					input.setInput(new BufferedReader(new InputStreamReader(
							System.in)));
				} else

				if (i == 2) {
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
					logger.logMessage("Your choice is not valid.");
					logger.logMessage("Console assumed.");
				}
			}

			Game game = new Game("game", logger, input);

			logger.logMessage("Please specify the number of intersections:");
			int nIntersections = Integer.valueOf(input.readLine());
			logger.logMessage("Please specify the number of roads:");
			int nRoads = Integer.valueOf(input.readLine());

			game.generateLevel(nIntersections, nRoads);

			logger.logMessage("---");
			logger.logMessage("Skeleton test finished succesfully!");

		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}
