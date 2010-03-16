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
        super(name, logger, input);
        logger.logCreate(this, "Clock");
        clock = new Clock("clock", logger, input);
        logger.logCreated(this, clock);
    }

    /**
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
     * @return
     */
    public void gameOver() {
        logger.logMessage("%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%");
		logger.logMessage("%%%         GAME OVER         %%%");
		logger.logMessage("%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%");
    }

    /**
     * @return
     */
    public void generateLevel(int nEntries, int nExits, int nIntersections, int nRoads, int nCivilCars) {
        logger.logCall(this, this, "generateMap(int nEntries, int nExits, int nIntersections, int nRoads)");
        generateMap(nEntries, nExits, nIntersections, nRoads);
        logger.logReturn(this, this, "generateMap(int nEntries, int nExits, int nIntersections, int nRoads)", null);
        logger.logCall(this, this, "generateVehicles()");
        generateVehicles(nCivilCars);
        logger.logReturn(this, this, "generateVehicles()", null);
    }

    /**
     * @return
     */
    private void generateMap(int nEntries, int nExits, int nIntersections, int nRoads) {
        // bank legenerálása
        logger.logCreate(this, "Bank");
        bank = new Bank("bank", logger, input);
        logger.logCreated(this, bank);
        intersections = new Intersection[nIntersections + nEntries + nExits];
        // bejáratok legenerálása
        for (int i = 0; i < nEntries; i++) {
            logger.logCreate(this, "CityEntry");
            intersections[i] = new CityEntry("cityEntry" + Integer.toString(i), logger, input);
            logger.logCreated(this, intersections[i]);
        }
        // kijáratok legenerálása
        for (int i = nEntries; i < nEntries + nExits; i++) {
            logger.logCreate(this, "CityExit");
            intersections[i] = new CityExit("cityExit" + Integer.toString(i), logger, input);
            logger.logCreated(this, intersections[i]);
        }
        // útkereszteződések legenerálása
        logger.logMessage("Setting bank as intersection at index " + Integer.toString(nEntries + nExits));
        intersections[nEntries + nExits] = bank;
        for (int i = nEntries + nExits + 1; i < nEntries + nExits + nIntersections; i++) {
            logger.logCreate(this, "Intersection");
            intersections[i] = new Intersection("intersection" + Integer.toString(i), logger, input);
            logger.logCreated(this, intersections[i]);
        }
        // utak legenerálása
        roads = new Road[nRoads];
        for (int i = 0; i < nRoads; i++) {
            logger.logCreate(this, "Road");
            logger.logMessage("Enter index of entry intersection for road" + Integer.toString(i) + " (0-" + Integer.toString(nIntersections + nEntries + nExits - 1) + "):");
            int j = input.readInt(0, nIntersections + nEntries + nExits - 1);
            logger.logMessage("Enter index of exit intersection for road" + Integer.toString(i) + " (0-" + Integer.toString(nIntersections + nEntries + nExits - 1) + "):");
            int k = input.readInt(0, nIntersections + nEntries + nExits - 1);
            logger.logMessage("Enter number of cells for road" + Integer.toString(i) + " (1-n):");
            int l = input.readInt();
            roads[i] = new Road("road" + Integer.toString(i), intersections[j], intersections[k], l, true, logger, input);
            logger.logCreated(this, roads[i]);
        }
    }

    /**
     * @return
     */
    private void generateVehicles(int nCivilCars) {
        // rendőr legenerálása
        policemen = new Policeman[1];
        logger.logCreate(this, "Policeman");
        policemen[0] = new Policeman("policeman", null, 10, logger, input); 
        logger.logCreated(this, policemen[0]);
        // rendőr elhelyezése egy cellára
        logger.logMessage("Please choose a road to place " + policemen[0].getName() + " on (0-" + Integer.toString(roads.length - 1) + "):");
        int road = input.readInt(0, roads.length - 1);
        logger.logCall(this, roads[road], "placeCar(Vehicle v)");
        roads[road].placeCar(policemen[0]);
        logger.logReturn(this, roads[road], "placeCar(Vehicle v)", null);
        // autók legenerálása
        cars = new CivilCar[nCivilCars];
        for (int i = 0; i < nCivilCars; i++) {
            logger.logCreate(this, "CivilCar");
            cars[i] = new CivilCar("car" + Integer.toString(i), null, 10, logger, input);
            logger.logCreated(this, cars[i]);
            // autó elhelyezése egy cellára
            logger.logMessage("Please choose a road to place " + cars[i].getName() + " on (0-" + Integer.toString(roads.length - 1) + "):");
            int chosenRoad = input.readInt(0, roads.length - 1);
            logger.logCall(this, roads[chosenRoad], "placeCar(Vehicle v)");
            roads[chosenRoad].placeCar(cars[i]);
            logger.logReturn(this, roads[chosenRoad], "placeCar(Vehicle v)", null);
        }
        // rabló legenerálása
        logger.logCreate(this, "Robber");
        player = new Robber("player", this, null, 10, logger, input); 
        logger.logCreated(this, player);
		// rendőrök ráállítása a rablóra
		for (Policeman p : policemen) {
			logger.logCall(this, p, "setWanted(Robber r)");
			p.setWanted(player);
			logger.logReturn(this, p, "setWanted(Robber r)", null);
		}
        // rabló elhelyezése a bankra
        logger.logCall(this, bank, "setVehicle(Vehicle v)");
        bank.setVehicle(player);
        logger.logReturn(this, bank, "setVehicle(Vehicle v)", null);
    }

    /**
     * @return
     */
    public void winGame() {
        logger.logMessage(":):):):):):):):):):):):):):):):):)");
		logger.logMessage(":):)  YOU HAVE WON THE GAME!  :):)");
		logger.logMessage(":):):):):):):):):):):):):):):):):)");
    }

    /**
     * @param c
     * @return
     */
    public void kill(CivilCar c) {
        throw new UnsupportedOperationException();
    }

    /**
     * @param p
     * @return
     */
    public void kill(Policeman p) {
        throw new UnsupportedOperationException();
    }

    /**
     * @param r
     * @return
     */
    public void kill(Robber r) {
        
    }

    /**
     * Hianyzo autok (rendorok es civilek) ujrageneralasat vegzo metodus
     */
    public void regenerateKilledVehicles() {

        String str = "";
        while (str.compareTo("0") != 0) {
			//valaszhatunk, hogy hianyzik-e meg auto
            logger.logMessage("Are there any vehicles missing? (y/n)");               
                str = input.readLine();
                if (str.compareTo("y") == 0) {
					//ha hianyzik, mi az? Policeman vagy CivilCar?
                    logger.logMessage("Policeman or CivilCar?");     
                    logger.logMessage("0 - CivilCar");
                    logger.logMessage("1 - Policeman");                    
                        int choice = input.readInt(0,1);
                        if (choice == 1) {
							//uj rendor generalasa
                            logger.logCreate(this, "Policeman");							
                            Policeman p = new Policeman("policeman0", null, 10, logger, input);    
                            logger.logCreated(this, p);
							//ures varoshatar lekerese
                            logger.logCall(this, this, "getEmptyCityEntry()");							
                            CityEntry c = getEmptyCityEntry();                              
                            logger.logReturn(this, this, "getEmptyCityEntry()", c);
							//auto lehelyezese
                            logger.logCall(this, c, "setVehicle(Vehicle v)");
                            c.setVehicle(p);                                                
                            logger.logReturn(this, c, "setVehicle(Vehicle v)", null);
                        } else {  
							//uj CivilCar generalasa                          
                            logger.logCreate(this, "CivilCar");
                            CivilCar cc = new CivilCar("civilcar0", null, 10, logger, input);    
                            logger.logCreated(this, cc);
							//ures varoshatar lekerese
                            logger.logCall(this, this, "getEmptyCityEntry()");
                            CityEntry c = getEmptyCityEntry();                       
                            logger.logReturn(this, this, "getEmptyCityEntry()", c);
							//auto lehelyezese
                            logger.logCall(this, c, "setVehicle(Vehicle v)");
                            c.setVehicle(cc);                                       
                            logger.logReturn(this, c, "setVehicle(Vehicle v)", null);
                        }                    
                } else if (str.compareTo("0") == 0) ;
                else {
                    logger.logMessage("not valid - no assumed");
                    str = "0";                                                     //mar nem kell tobb auto
                }            
        }
    }

    /**
     * @return
     */
    public CityEntry getEmptyCityEntry() { 
		return new CityEntry("cityentry0", logger, input);      //visszaadunk egy random cityentrit, na de hogy találjuk meg??
		// kiválasztunk egy randomot, aztán onnan addig iterálunk, amíg egy üreshez nem érünk.
		// tudjuk mennyi van, úgyhogy ha a végére érünk, kezdjük előlről (a cityentrykkel kezdődik
		// az intersections lista)
		// ha nincs üres, nullt adunk vissza, és akkor a regenerate nem tesz sehova semmit
	}

}
