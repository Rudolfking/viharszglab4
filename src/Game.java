//import InitialClassDiagram.*;

public class Game implements INamedObject {

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
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void setLogger(ILogger logger) {
		this.logger = logger;
	}

	/**
	 * 
	 * @return 
	 */
	public void tick() {
		for(Road r : roads) {
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
	public void generateLevel() {
		
		// TESZT!!
		
		final int nRoads = 4;
		
		roads = new Road[nRoads];
		for(int i = 0; i < nRoads; i++) {
			roads[i] = new Road();
			roads[i].setName("road" + Integer.toString(i));
		}
		
		// ---
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