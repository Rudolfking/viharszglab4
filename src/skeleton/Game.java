//import InitialClassDiagram.*;

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
	private ILogger logger;

	/**
	 *  
	 */
	public Game(String name, ILogger logger) {

		super(name);
		setLogger(logger);

		logger.logCreate(this, "Clock");
		clock = new Clock("clock");
		logger.logCreated(this, clock);
	}

	public void setLogger(ILogger logger) {
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

		logger.logCreate(this, "Bank");
		bank = new Bank("bank");
		logger.logCreated(this, bank);

		intersections = new Intersection[nIntersections];
		for (int i = 0; i < nIntersections; i++) {
			logger.logCreate(this, "Intersection");
			intersections[i] = new Intersection("intersection" + Integer.toString(i));
			logger.logCreated(this, intersections[i]);
		}

		roads = new Road[nRoads];
		for (int i = 0; i < nRoads; i++) {
			logger.logCreate(this, "Road");
			int j = i;
			if (j >= nIntersections)
				j = (int) Math.round(Math.random() * (nIntersections - 2));
			int k = (int) Math.round(Math.random() * (nIntersections - 2));
			if (k >= j)
				k++;
			roads[i] = new Road("road" + Integer.toString(i), intersections[j],
					intersections[k], (int) Math.round(Math.random() * 4 + 1),
					logger);
			logger.logCreated(this, roads[i]);
		}

	}

	/**
	 * 
	 * @return
	 */
	private void generateMap() {
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @return
	 */
	private void generateVehicles() {
		throw new UnsupportedOperationException();
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