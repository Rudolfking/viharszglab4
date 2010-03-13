package skeleton;
import java.io.*;

public class Game extends NamedObject {

	private CivilCar[] cars;
	private Policeman[] policemen;
	private Robber player;
	private Road[] roads;
	private Intersection[] intersections;
	private Clock clock;
	private int minPolice;
	private int minCivilCar;
	private Bank bank;

	private String name;
	private Logger logger;
	private CustomReader input;

	/**
	 *  
	 */
	public Game(String name, Logger logger, CustomReader input) {

		super(name);
		setLogger(logger);
		this.input = input;

		logger.logCreate(this, "Clock");
		clock = new Clock("clock");
		logger.logCreated(this, clock);
	}

	public void setLogger(Logger logger) {
		this.logger = logger;
	}

	/**
	 * 
	 * @return
	 */
	public void tick() {
		for (Road r : roads) {
			logger.logCall(this, r, "tick()");
			r.tick();
			logger.logReturn(this, r, "tick()", null);
		}
	}

	/**
	 * 
	 * @return
	 */
	public void gameOver() {
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @return
	 */
	public void generateLevel(int nIntersections, int nRoads) {

		logger.logCall(this, this,
				"generateMap(int nIntersections, int nRoads)");
		generateMap(nIntersections, nRoads);
		logger.logReturn(this, this,
				"generateMap(int nIntersections, int nRoads)", null);

		logger.logCall(this, this, "generateVehicles()");
		generateVehicles();
		logger.logReturn(this, this, "generateVehicles()", null);

	}

	/**
	 * 
	 * @return
	 */
	private void generateMap(int nIntersections, int nRoads) {

		logger.logCreate(this, "Bank");
		bank = new Bank("bank");
		logger.logCreated(this, bank);

		intersections = new Intersection[nIntersections];
		intersections[0] = bank;
		for (int i = 1; i < nIntersections; i++) {
			logger.logCreate(this, "Intersection");
			intersections[i] = new Intersection("intersection"
					+ Integer.toString(i));
			logger.logCreated(this, intersections[i]);
		}

		roads = new Road[nRoads];
		for (int i = 0; i < nRoads; i++) {
			logger.logCreate(this, "Road");

			try {
				logger.logMessage("Enter index of entry intersection for road"
						+ Integer.toString(i) + " (0-"
						+ Integer.toString(nIntersections - 1) + "):");
				int j = Integer.valueOf(input.readLine());
				logger.logMessage("Enter index of exit intersection for road"
						+ Integer.toString(i) + " (0-"
						+ Integer.toString(nIntersections - 1) + "):");
				int k = Integer.valueOf(input.readLine());
				logger.logMessage("Enter number of cells for road"
						+ Integer.toString(i) + " (1-n):");
				int l = Integer.valueOf(input.readLine());

				roads[i] = new Road("road" + Integer.toString(i),
						intersections[j], intersections[k], l, logger, input);
				logger.logCreated(this, roads[i]);

			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 
	 * @return
	 */
	private void generateVehicles() {

	}

	/**
	 * 
	 * @return
	 */
	public void winGame() {
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param c
	 * @return
	 */
	public void kill(CivilCar c) {
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param p
	 * @return
	 */
	public void kill(Policeman p) {
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param r
	 * @return
	 */
	public void kill(Robber r) {
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @return
	 */
	private void regenerateKilledVehicles() {
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @return
	 */
	public void getEmptyCityEntry() {
		throw new UnsupportedOperationException();
	}

}
