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

	/**
	 *  
	 */
	public Game(String name, Logger logger, CustomReader input) {

		super(name,logger,input);
		logger.logCreate(this, "Clock");
		clock = new Clock("clock",logger,input);
		logger.logCreated(this, clock);
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
	public void generateLevel(int nEntries, int nExits, int nIntersections, int nRoads, int nCivilCars) {

		logger.logCall(this, this,
				"generateMap(int nEntries, int nExits, int nIntersections, int nRoads)");
		generateMap(nEntries,nExits,nIntersections, nRoads);
		logger.logReturn(this, this,
				"generateMap(int nEntries, int nExits, int nIntersections, int nRoads)", null);

		logger.logCall(this, this, "generateVehicles()");
		generateVehicles(nCivilCars);
		logger.logReturn(this, this, "generateVehicles()", null);

	}
	

	/**
	 * 
	 * @return
	 */
	private void generateMap(int nEntries, int nExits, int nIntersections, int nRoads) {

		// bank legenerálása
		logger.logCreate(this, "Bank");
		bank = new Bank("bank",logger,input);
		logger.logCreated(this, bank);		

		intersections = new Intersection[nIntersections+nEntries+nExits];
		// bejáratok legenerálása
		for (int i = 0; i < nEntries; i++) {
			logger.logCreate(this, "CityEntry");
			intersections[i] = new CityEntry("cityEntry"
					+ Integer.toString(i),logger,input);
			logger.logCreated(this, intersections[i]);
		}
		// kijáratok legenerálása
		for (int i = nEntries; i < nEntries+nExits; i++) {
			logger.logCreate(this, "CityExit");
			intersections[i] = new CityExit("cityExit"
					+ Integer.toString(i),logger,input);
			logger.logCreated(this, intersections[i]);
		}
		// útkereszteződések legenerálása	
		logger.logMessage("Setting bank as intersection at index " + Integer.toString(nEntries+nExits));	
		intersections[nEntries+nExits] = bank;
		for (int i = nEntries+nExits+1; i < nEntries+nExits+nIntersections; i++) {
			logger.logCreate(this, "Intersection");
			intersections[i] = new Intersection("intersection"
					+ Integer.toString(i),logger,input);
			logger.logCreated(this, intersections[i]);
		}		

		roads = new Road[nRoads];
		for (int i = 0; i < nRoads; i++) {
			logger.logCreate(this, "Road");

			try {
				logger.logMessage("Enter index of entry intersection for road"
						+ Integer.toString(i) + " (0-"
						+ Integer.toString(nIntersections + nEntries + nExits - 1) + "):");
				int j = Integer.valueOf(input.readLine());
				logger.logMessage("Enter index of exit intersection for road"
						+ Integer.toString(i) + " (0-"
						+ Integer.toString(nIntersections + nEntries + nExits - 1) + "):");
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
	private void generateVehicles(int nCivilCars) {

		// rendőr legenerálása
		policemen = new Policeman[1];
		logger.logCreate(this, "Policeman");
		policemen[0] = new Policeman("policeman",null,10,logger,input); //TODO cellak speed beallitasa
		logger.logCreated(this, policemen[0]);		

		// autók legenerálása
		cars = new CivilCar[nCivilCars];
		for (int i = 0; i < nCivilCars; i++) {
			logger.logCreate(this, "CivilCar");
			cars[i] = new CivilCar("car" + Integer.toString(i),null,10,logger,input);
			logger.logCreated(this, cars[i]);		
		}

		// rabló legenerálása
		logger.logCreate(this, "Robber");
		player = new Robber("player",null,10,logger,input); //TODO cellak speed beallitasa
		logger.logCreated(this, player);		
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
	public void regenerateKilledVehicles() {
		String str = "";
		while (str.compareTo("0") != 0) {
			logger.logMessage("Is there missing vehicle?");
			logger.logMessage("0 - no");
			logger.logMessage("1 - yes");
			try {
				str = input.readLine();
				if (str.compareTo("1") == 0) {
					logger.logMessage("Policeman or CivilCar?");
					logger.logMessage("0 - CivilCar");
					logger.logMessage("1 - Policeman");
					try {
						String str2 = input.readLine();
						if (str2.compareTo("1") == 0) {
							logger.logCreate(this, "Policeman");
							Policeman p=new Policeman("policeman0", null, 10, logger, input);
							logger.logCreated(this, p);
							logger.logCall(this, this, "getEmptyCityEntry()");
							CityEntry c=getEmptyCityEntry();
							logger.logReturn(this, this, "getEmptyCityEntry()", c);
							logger.logCall(this, c, "setVehicle(Vehicle v)");
							c.setVehicle(p);
							logger.logReturn(this, c, "setVehicle(Vehicle v)", null);
						} else 
						{
							if (str2.compareTo("0") != 0) logger.logMessage("not valid - CivilCar assumed");
							logger.logCreate(this, "CivilCar");
							CivilCar cc=new CivilCar("civilcar0", null, 10, logger, input);
							logger.logCreated(this, cc);
							logger.logCall(this, this, "getEmptyCityEntry()");
							CityEntry c=getEmptyCityEntry();
							logger.logReturn(this, this, "getEmptyCityEntry()", c);
							logger.logCall(this, c, "setVehicle(Vehicle v)");
							c.setVehicle(cc);
							logger.logReturn(this, c, "setVehicle(Vehicle v)", null);
						}
					} catch (IOException e) {
						e.printStackTrace();
					}
				} else if (str.compareTo("0") == 0)
					;
				else {
					logger.logMessage("not valid - no assumed");
					str = "0";
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 
	 * @return
	 */
	public CityEntry getEmptyCityEntry() { //TODO fontos!!! ez hogy fog m�k�dni?
		return new CityEntry("cityentry0", logger, input);
	}

}
