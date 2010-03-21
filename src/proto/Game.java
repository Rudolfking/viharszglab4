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
     * Konstruktor a naplókimenet és felhasználói bemenet beállításával.
     */
    public Game(String name, Logger logger, CustomReader input) {
        super(name, logger, input);
        logger.logCreate(this, "Clock");
        clock = new Clock("clock", logger, input);
        logger.logCreated(this, clock);
    }

    /**
     * Minden entitás (ami valamilyen formában reagál az időre) léptetése
     */
    public void tick() {
		// utak léptetése (léptetik a rajtuk lévő jelzőlámpákat, táblákat)
        for (Road r : roads) {
            logger.logCall(this, r, "tick()");
            r.tick();
            logger.logReturn(this, r, "tick()", null);
        }
		// civil autók léptetése
		for (CivilCar c : cars) {
			logger.logCall(this, c, "tick()");
            c.tick();
            logger.logReturn(this, c, "tick()", null);
		}
		// rendőrök léptetése
		for (Policeman p : policemen) {
			logger.logCall(this, p, "tick()");
            p.tick();
            logger.logReturn(this, p, "tick()", null);
		}
		// bankrabló léptetése
		logger.logCall(this, player, "tick()");
        player.tick();
        logger.logReturn(this, player, "tick()", null);
    }

    /**
	 * Pálya (úthálózat + járművek) felépítése a megadott paraméterekkel.
	 *
     * @param nEntries város bejáratainak száma
	 * @param nExits város kijáratainak száma
	 * @param nIntersections város belső kereszteződéseinek száma (bankot, rejtekhelyet is beleértve)
	 * @param nRoads utak száma
	 * @param nCivilCars civil autók száma (rendőrből és betörőből egy van)
     */
    public void generateLevel(int nEntries, int nExits, int nIntersections, int nRoads, int nCivilCars) {
		// úthálózat felépítése
        logger.logCall(this, this, "generateMap(int nEntries, int nExits, int nIntersections, int nRoads)");
        generateMap(nEntries, nExits, nIntersections, nRoads);
        logger.logReturn(this, this, "generateMap(int nEntries, int nExits, int nIntersections, int nRoads)", null);
		// járművek létrehozása és elhelyezése
        logger.logCall(this, this, "generateVehicles()");
        generateVehicles(nCivilCars);
        logger.logReturn(this, this, "generateVehicles()", null);
    }

    /**
     * Úthálózat felépítése a megadott paraméterekkel.
	 *
     * @param nEntries város bejáratainak száma
	 * @param nExits város kijáratainak száma
	 * @param nIntersections város belső kereszteződéseinek száma (bankot, rejtekhelyet is beleértve)
	 * @param nRoads utak száma
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
            intersections[i] = new CityExit("cityExit" + Integer.toString(i), this, logger, input);
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
	 * Járművek létrehozása és elhelyezése a városban
     * @param nCivilCars civil autók száma (rendőrből és betörőből egy van)
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
	 * Győzelmi üzenet megjelenítése.
     */
    public void winGame() {
        logger.logMessage(":):):):):):):):):):):):):):):):):)");
		logger.logMessage(":):)  YOU HAVE WON THE GAME!  :):)");
		logger.logMessage(":):):):):):):):):):):):):):):):):)");
    }

	/**
     * Vereség üzenet megjelenítése.
     */
    public void gameOver() {
        logger.logMessage("%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%");
		logger.logMessage("%%%         GAME OVER         %%%");
		logger.logMessage("%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%");
    }

    /**
	 * Ha ezt a függvényt valaki meghívja, tudjuk, hogy egy autóval kevesebb van.
     * @param c az autó, ami megszűnik     
     */
    public void kill(CivilCar c) {        
    }

    /**
     * Ha ezt a függvényt valaki meghívja, tudjuk, hogy egy rendőrrel kevesebb van.
     * @param p a rendőr, ami megszűnik     
     */
    public void kill(Policeman p) {        
    }

    /**
     * Bankrabló halálakor hívódik meg.     
     */
    public void kill(Robber r) {        
    }

    /**
     * Hiányzó autók (rendőrök és civilek) újragenerálását végző metódus
     */
    public void regenerateKilledVehicles() {
        
		// Felhasználói bemenet alapján döntünk, hogy van-e hiány...
        logger.logMessage("Are there any vehicles missing? (y/n)");               
        String str = input.readLine();
        if (str.compareTo("y") == 0) {			
			// ...és hogy miből			
            logger.logMessage("The missing vehicle is a...");     
            logger.logMessage("0 - CivilCar");
            logger.logMessage("1 - Policeman");                    
            int choice = input.readInt(0,1);
			// szabad városhatár lekérése
            logger.logCall(this, this, "getEmptyCityEntry()");							
            CityEntry c = getEmptyCityEntry();                              
            logger.logReturn(this, this, "getEmptyCityEntry()", c);
            if (choice == 1) {
				// új rendőr generálása
                logger.logCreate(this, "Policeman");							
                Policeman p = new Policeman("policeman" + Integer.toString(policemen.length), c, 10, logger, input);    
                logger.logCreated(this, p);				
				// rendőr elhelyezése
                logger.logCall(this, c, "setVehicle(Vehicle v)");
                c.setVehicle(p);                                                
                logger.logReturn(this, c, "setVehicle(Vehicle v)", null);
            } else {  
				// új CivilCar generálása                          
                logger.logCreate(this, "CivilCar");
				CivilCar cc;
				if (cars != null)
                	cc = new CivilCar("civilcar" + Integer.toString(cars.length), c, 10, logger, input);
				else
					cc = new CivilCar("civilcar0", c, 10, logger, input);
                logger.logCreated(this, cc);					
				// autó elhelyezése
                logger.logCall(this, c, "setVehicle(Vehicle v)");
                c.setVehicle(cc);                                       
                logger.logReturn(this, c, "setVehicle(Vehicle v)", null);
            }                                            
        }
    }

    /**
     * Egy olyan bejáratot ad vissza, ahol épp nem tartózkodik jármű
	 * @result a talált üres bejárat
     */
    public CityEntry getEmptyCityEntry() { 
		return new CityEntry("cityentry0", logger, input);      
		// kiválasztunk egy randomot, aztán onnan addig iterálunk, amíg egy üreshez nem érünk.
		// tudjuk mennyi van, úgyhogy ha a végére érünk, kezdjük előlről (a cityentrykkel kezdődik
		// az intersections lista)
		// ha nincs üres, nullt adunk vissza, és akkor a regenerate nem tesz sehova semmit
	}

}
