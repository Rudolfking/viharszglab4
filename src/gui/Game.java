package gui;

import java.io.*;
import java.util.*;
import java.awt.Graphics;

/**
 * A j�t�k eg�sz��rt, annak ir�ny�t�s��rt, karban tart�s��rt felel�s oszt�lyt val�s�tja meg.
 * @author Bal�zs
 *
 */
public class Game extends NamedObject {
    protected CivilCar[] cars;
    protected Policeman[] policemen;
    protected Robber player;
    protected Road[] roads;
    public Road[] Roads() { return roads; }
    protected Intersection[] intersections;
    public Intersection[] Intersections() { return intersections; }
    protected Clock clock;
    protected int minPolice;
    protected int minCivilCar;
    protected Bank bank;
    protected Bunny bunny;
    protected String name;
    private boolean gameOverFlag;
    public boolean gameIsOver() { return gameOverFlag; }
    private boolean gameWinFlag;
    public boolean gameIsWon() { return gameWinFlag; }
    
    private IDrawer[] drawers;
    
    int ticks = 0;
    
    public boolean speed;
    
    // autók alapértelmezett inverz sebessége
    final int def_ispeed = 10;

    /**
     * Konstruktor a naplókimenet és felhasználói bemenet beállításával.
     */
    public Game(String name, Logger logger, CustomReader input) {
        super(name, logger, input);        
        clock = new Clock("clock", logger, input);
        speed = true;
        gameOverFlag = false;
        gameWinFlag = false;
    }

    /**
     * Minden entitás (ami valamilyen formában reagál az időre) léptetése
     */
    public void tick() {
		
		if(gameOverFlag || gameWinFlag)
			return;
		
		ticks++;
		logger.log("o tick "+Integer.toString(ticks));
		
		regenerateKilledVehicles();
		
		// utak léptetése (léptetik a rajtuk lévő jelzőlámpákat, táblákat)
        for (Road r : roads) {            
            r.tick();            
        }
		// civil autók léptetése
		for (CivilCar c : cars) {			
            c.tick();            
		}
		// rendőrök léptetése
		for (Policeman p : policemen) {			
            p.tick();            
		}
		// bankrabló léptetése		
		if((!gameOverFlag) && (player != null))
			player.tick();
    }
    
    /**
     *
     */ 
    private String indexedName(char letter, int index) {
		return letter + "[" + Integer.toString(index) + "]";
	}
    
    /**
     * A kimeneti nyelvben definiált formulának megfelelő string alapján
     * építi fel a pályát. A string természetesen több sort is 
     * tartalmazhat.
     * 
     * @param level a pályát leíró string (lásd a dokumentáció 7.1.3 
     * pontját)
     */ 
    public void generateLevel(String level) {
		String[] roadDefs = level.split("\r\n|\r|\n");		
		
		List<Intersection> ints = new ArrayList<Intersection>();
		List<Road> rds = new ArrayList<Road>();
		List<CivilCar> crs = new ArrayList<CivilCar>();
		List<Integer> car_road = new ArrayList<Integer>();
		List<Integer> car_cell = new ArrayList<Integer>();
		List<Policeman> pmn = new ArrayList<Policeman>();
		List<Integer> pol_road = new ArrayList<Integer>();
		List<Integer> pol_cell = new ArrayList<Integer>();
		int rob_road = -1;
		int rob_cell = -1;
		int bun_road = -1;
		int bun_cell = -1;
		
		int i=0;
		char type;
		int index = -1;
		
		// végigmegyünk az egyes utakon
		for (String road : roadDefs) {			
			
			i=0;	
			
			// a létrehozandó út majdani mezőit tároló változók
			Intersection e = null; // bejárat
			Intersection x = null; // kijárat
			int nCells = 0;			  // mezők száma
			ISign sign = null;		  // utolsó mező táblája
			
			// végigmegyünk a stringen
			while (i<road.length()) {
				// típusjelző karakter beolvasása
				type = road.charAt(i);
				i++;
				// index beolvasása	
				if ((i<road.length()) && (road.charAt(i)=='[')) {
					String s = "";								
					for (i++; road.charAt(i) != ']'; i++) {					
						s += road.charAt(i);					
					}
					index = Integer.valueOf(s);
					i++;
				}
			
				int j;
				boolean itemAlreadyExists = false;
			
				// mező típusának azonosítása
				switch (type) {
				// CityEntry
				case 'E':
					// először ellenőrizni kell, hogy van-e már ilyen nevű bejárat
					itemAlreadyExists=false;
					for (j=0; j<ints.size() && !itemAlreadyExists; j++)
						if (ints.get(j).getName().compareTo(indexedName('E',index))==0)
							itemAlreadyExists = true;
					// ha még nincs, felvesszük, és beállítjuk az út bejáratának
					if (!itemAlreadyExists) {
						ints.add(new CityEntry(indexedName('E',index), logger, input));					
						e = ints.get(ints.size()-1);
					}
					// ha már van, a meglévőt állítjuk be
					else {
						e = ints.get(j-1);
					}					
					break;
				// CityExit
				case 'X':
					// először ellenőrizni kell, hogy van-e már ilyen nevű kijárat
					itemAlreadyExists=false;
					for (j=0; j<ints.size() && !itemAlreadyExists; j++)
						if (ints.get(j).getName().compareTo(indexedName('X',index))==0)
							itemAlreadyExists = true;
					// ha még nincs, felvesszük, és beállítjuk az út kijáratának
					if (!itemAlreadyExists) {
						ints.add(new CityExit(indexedName('X',index), this, logger, input));
						x = ints.get(ints.size()-1);
					}
					// ha már van, a meglévőt állítjuk be
					else {
						x = ints.get(j-1);
					}					
					break;
				// Intersection
				case 'I':
					// először ellenőrizni kell, hogy van-e már ilyen nevű kereszteződés
					itemAlreadyExists=false;
					for (j=0; j<ints.size() && !itemAlreadyExists; j++)
						if (ints.get(j).getName().compareTo(indexedName('I',index))==0)
							itemAlreadyExists = true;
					// ha még nincs, beállítjuk be- vagy kijáratnak
					if (!itemAlreadyExists) {
						ints.add(new Intersection(indexedName('I',index), logger, input));
						if (e == null)
							e = ints.get(ints.size()-1);
						else
							x = ints.get(ints.size()-1);
					}
					// ha már van, a meglévőt állítjuk be
					else {
						if (e == null)
							e = ints.get(j-1);
						else
							x = ints.get(j-1);
					}					
					break;					
				// Bank, HidingPlace
				case 'B': 
				case 'H':
					// először ellenőrizni kell, hogy van-e már bank
					itemAlreadyExists=false;
					for (j=0; j<ints.size() && !itemAlreadyExists; j++)
						if (ints.get(j).getName().compareTo(""+type)==0)
							itemAlreadyExists = true;
					// ha még nincs, beállítjuk be- vagy kijáratnak
					if (!itemAlreadyExists) {
						if (type=='B')
							ints.add(new Bank("B",logger,input));
						else
							ints.add(new HidingPlace("H",logger,input));
						if (e == null)
							e = ints.get(ints.size()-1);
						else
							x = ints.get(ints.size()-1);
					}
					// ha már van, a meglévőt állítjuk be
					else {
						if (e == null)
							e = ints.get(j-1);
						else
							x = ints.get(j-1);
					}					
					break;
				// RoadCell
				case 'F':
					nCells++;					
					break;
				}
				
				// mezőn lévő objektumok beolvasása
				if ((i<road.length()) && (road.charAt(i)=='{')) {
					i++; 
					while (road.charAt(i) != '}') {
						// típuazonosító eltárolása
						char objType = road.charAt(i);
						i++;
						// index beolvasása	
						index = -1;
						if (road.charAt(i)=='[') {
							String s = "";								
							for (i++; road.charAt(i) != ']'; i++) {					
								s += road.charAt(i);					
							}
							index = Integer.valueOf(s);
							i++;
						}
						
						// objektum típusának azonosítása
						switch (objType) {
						// StopSign
						case 'S':
							sign = new StopSign(indexedName('S',index), logger, input);
							break;
						// TrafficLight
						case 'T':
							sign = new TrafficLight(indexedName('T',index), logger, input);
							break;
						// CivilCar
						case 'C':
							// ha már létezett korábban a cella, akkor az autót is létrehoztuk már hozzá
							if (!itemAlreadyExists) {
								// ha egy kereszteződésre rakjuk, akkor eltárolhatjuk a kereszteződés referenicáját
								if (type!='F') {
									crs.add(new CivilCar(indexedName('C',index),this,ints.get(ints.size()-1),def_ispeed,logger,input));
									car_road.add(new Integer(-1));
									car_cell.add(new Integer(-1));
								}
								// ha pedig az út egyik cellájára, akkor külön kell elmentei annak az adatait, mivel
								// még nincsenek létrehozva cellák
								else {
									crs.add(new CivilCar(indexedName('C',index),this,null,def_ispeed,logger,input));
									car_road.add(new Integer(rds.size()));
									car_cell.add(new Integer(nCells-1));
								}								
							}
							break;
						// Policeman
						case 'P':
							// ha már létezett korábban a cella, akkor az autót is létrehoztuk már hozzá
							if (!itemAlreadyExists) {
								// ha egy kereszteződésre rakjuk, akkor eltárolhatjuk a kereszteződés referenicáját
								if (type!='F') {
									pmn.add(new Policeman(indexedName('P',index),this,ints.get(ints.size()-1),def_ispeed,logger,input));
									pol_road.add(new Integer(-1));
									pol_cell.add(new Integer(-1));
								}
								// ha pedig az út egyik cellájára, akkor külön kell elmentei annak az adatait, mivel
								// még nincsenek létrehozva cellák
								else {
									pmn.add(new Policeman(indexedName('P',index),this,null,def_ispeed,logger,input));
									pol_road.add(new Integer(rds.size()));
									pol_cell.add(new Integer(nCells-1));
								}								
							}
							break;
						// Robber
						case 'R':
							// ha már létezett korábban a cella, akkor az autót is létrehoztuk már hozzá
							if (!itemAlreadyExists) {
								// ha egy kereszteződésre rakjuk, akkor eltárolhatjuk a kereszteződés referenicáját
								if (type!='F') {
									player = new Robber("R",this,ints.get(ints.size()-1),def_ispeed,logger,input);
									rob_road=-1;
									rob_cell=-1;
								}
								// ha pedig az út egyik cellájára, akkor külön kell elmentei annak az adatait, mivel
								// még nincsenek létrehozva cellák
								else {
									player = new Robber("R",this,null,def_ispeed,logger,input);
									rob_road=rds.size();
									rob_cell=nCells-1;
								}								
							}
							break;
						// Bunny
						case 'U':
							// ha már létezett korábban a cella, akkor az autót is létrehoztuk már hozzá
							if (!itemAlreadyExists) {
								// ha egy kereszteződésre rakjuk, akkor eltárolhatjuk a kereszteződés referenicáját
								if (type!='F') {
									bunny = new Bunny("U",this,ints.get(ints.size()-1),logger,input);
									bun_road=-1;
									bun_cell=-1;
								}
								// ha pedig az út egyik cellájára, akkor külön kell elmentei annak az adatait, mivel
								// még nincsenek létrehozva cellák
								else {
									bunny = new Bunny("U",this,null,logger,input);
									bun_road=rds.size();
									bun_cell=nCells-1;
								}								
							}
							break;
						}
						
						if (road.charAt(i)==',')
							i++;
					}
					i++;
				}
			}
			
			rds.add(new Road(indexedName('r', rds.size()),e,x,nCells,sign,logger,input));
		}
		
		// az ideiglenes adatszerkezetekből át kell másolni az adatokat a véglegesekbe
		
		intersections = new Intersection[ints.size()];
		System.arraycopy(ints.toArray(),0,intersections,0,ints.size());
		for (Intersection in : intersections) {
			in.nextCells = new Cell[0];
			in.previousCells = new Cell[0];
		}		
		
		Intersection e = null;
		Intersection x = null;
		
		roads = new Road[rds.size()];
		
		for (int j = 0; j<rds.size(); j++) {
			for (int k = 0; k<intersections.length; k++) {
				if (rds.get(j).getEntryIntersection().getName().compareTo(intersections[k].getName())==0)
					e = intersections[k];
				if (rds.get(j).getExitIntersection().getName().compareTo(intersections[k].getName())==0)
					x = intersections[k];					
			}
			Cell[] c = rds.get(j).getCells();
			roads[j] = new Road(indexedName('r', j),e,x,c.length,c[c.length-1].getSign(),logger,input);
		}
		
		cars = new CivilCar[crs.size()];
		System.arraycopy(crs.toArray(),0,cars,0,crs.size());
		
		for (int j = 0; j<crs.size(); j++) {
			if (crs.get(j).getCell()!=null) {
				for (int k = 0; k<intersections.length; k++) {
					if (crs.get(j).getCell().getName().compareTo(intersections[k].getName())==0) {
						cars[j].setCell(intersections[k]);
						intersections[k].setVehicle(cars[j]);
					}
				}			
			}
			else {
				roads[car_road.get(j)].placeCar(cars[j],car_cell.get(j));
			}
		}
		
		policemen = new Policeman[pmn.size()];
		System.arraycopy(pmn.toArray(),0,policemen,0,pmn.size());
		
		for (Policeman p : policemen)
			p.setWanted(player);
		
		for (int j = 0; j<pmn.size(); j++) {
			if (pmn.get(j).getCell()!=null) {
				for (int k = 0; k<intersections.length; k++) {					
					if (pmn.get(j).getCell().getName().compareTo(intersections[k].getName())==0) {						
						policemen[j].setCell(intersections[k]);
						intersections[k].setVehicle(policemen[j]);
					}
				}			
			}
			else {
				roads[pol_road.get(j)].placeCar(policemen[j],pol_cell.get(j));
			}
		}
		
		if (player!=null) {
			if (player.getCell()!=null) {
				for (int k = 0; k<intersections.length; k++) {
					if (player.getCell().getName().compareTo(intersections[k].getName())==0) {
						player.setCell(intersections[k]);	
						intersections[k].setVehicle(player);
					}	
				}			
			}
			else {
				roads[rob_road].placeCar(player,rob_cell);
			}
		}
		
		if (bunny!=null) {
			if (bunny.getCell()!=null) {
				for (int k = 0; k<intersections.length; k++) {
					if (bunny.getCell().getName().compareTo(intersections[k].getName())==0) {
						bunny.setCell(intersections[k]);	
						intersections[k].setVehicle(bunny);
					}	
				}			
			}
			else {
				roads[bun_road].placeCar(bunny,bun_cell);
			}
		}
	}
	
	/**
	 * 
	 */ 
	private String cellContentToString(Cell c) {
		String s = "";
		if ((c.getVehicle()!=null) || (c.getSign()!=null)) {
			s = s + '{';
			if (c.getVehicle()!=null) {
				s = s + c.getVehicle().getName();
				if (c.getSign()!=null)
					s = s + ',';
			}
			if (c.getSign()!=null)
				s = s + c.getSign().getName();
			s = s + '}';
		}	
		return s;		
	}
	
	/**
     * A kimeneti nyelvben definiált formulának megfelelő stringet
     * naplóz, mely a pálya aktuális állapotát írja le.
     *
     */
    public void writeLevel() {	
		Boolean[] int_finished = new Boolean[intersections.length];
		for (int i=0; i<int_finished.length; i++) int_finished[i]=false;
		
		for (Road r : roads) {
			
			int j;
			
			String s = r.getEntryIntersection().getName();
			for	(j=0; intersections[j] != r.getEntryIntersection(); j++);
			if (!int_finished[j])
				s = s + cellContentToString(r.getEntryIntersection());						
			int_finished[j]=true;
			
			for (Cell c : r.getCells()) {
				s = s + 'F' + cellContentToString(c);								
			}
			
			s = s + r.getExitIntersection().getName();
			for	(j=0; intersections[j] != r.getExitIntersection(); j++);
			if (!int_finished[j]) 
				s = s + cellContentToString(r.getExitIntersection());			
			int_finished[j]=true;
			
			logger.logMessage(s);
		}
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
            roads[i] = new Road("road" + Integer.toString(i), intersections[j], intersections[k], l, null, logger, input);
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
        policemen[0] = new Policeman("policeman", this,null, 10, logger, input); 
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
            cars[i] = new CivilCar("car" + Integer.toString(i), this, null, 10, logger, input);
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
        //logger.logMessage(":):):):):):):):):):):):):):):):):)");
		//logger.logMessage(":):)  YOU HAVE WON THE GAME!  :):)");
		//logger.logMessage(":):):):):):):):):):):):):):):):):)");
		logger.logMessage("o You won the game!");	
		gameWinFlag = true;	
    }

	/**
     * Vereség üzenet megjelenítése.
     */
    public void gameOver() {
        //logger.logMessage("%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%");
		//logger.logMessage("%%%         GAME OVER         %%%");
		//logger.logMessage("%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%");
		logger.logMessage("o Game Over");		
		gameOverFlag = true;
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
        
		// várost elhagyott civil autók keresése
		for (CivilCar c : cars) {			
			if (c.getCell() == null) {
				// szabad városhatár lekérése
				CityEntry e = getEmptyCityEntry();
				// k�t�sek l�trehoz�sa
				if (e != null) {
					e.enter(c);	
					c.ticksLeft = -1;									
					
					INamedObject[] param = {c,e};
					logger.logEvent("CivilCar $car created at $entry",param);
				}				
			}			
		}
		
		// várost elhagyott civil autók keresése
		for (Policeman p : policemen) {
			if (p.getCell() == null) {
				// szabad városhatár lekérése
				CityEntry e = getEmptyCityEntry();
				// kötések létrehozása
				if (e != null) {
					e.enter(p);			
					p.ticksLeft = -1;		
					
					INamedObject[] param = {p,e};
					logger.logEvent("Policeman $car created at $entry",param);
				}				
			}			
		}			           
    }

    /**
     * Egy olyan bejáratot ad vissza, ahol épp nem tartózkodik jármű
	 * @result a talált üres bejárat
     */
    public CityEntry getEmptyCityEntry() {
		Random r = new Random();
		int index = r.nextInt(intersections.length);
		int i = (index + 1) % intersections.length;
		while (!((intersections[i].getNextCells().length>0) && 
			(intersections[i].getPreviousCells().length==0) &&
			(intersections[i].getVehicle() == null)) && (i != index)) {
				i=(i+1)%intersections.length;
		}
		if ((intersections[i].getNextCells().length>0) && 
			(intersections[i].getPreviousCells().length==0) &&
			(intersections[i].getVehicle() == null)) {
				return (CityEntry)(intersections[i]);
		} else {
			return null;
		}
	}	
	
	private int[] addDrawer(int i, int[] cols, Intersection in) {
		
		
		if (in.getName().charAt(0) == 'B') {
			cols[1]++;
			drawers[i] = new BankDrawer(in,100,cols[1]*50);
		} else if (in.getName().charAt(0) == 'H') {
			cols[1]++;
			drawers[i] = new HidingPlaceDrawer(in,100,cols[1]*50);
		} else if (in.getName().charAt(0) == 'E') {
			cols[0]++;
			drawers[i] = new CityEntryDrawer(in,50,cols[0]*50);
		} else if (in.getName().charAt(0) == 'X') {
			cols[2]++;
			drawers[i] = new CityExitDrawer(in,150,cols[2]*50);
		} else {
			cols[1]++;
			drawers[i] = new IntersectionDrawer(in,100,cols[1]*50);
		}
		return cols;
	}
	
	public void createDrawers() {		
		
		drawers = new IntersectionDrawer[intersections.length];
		
		int i = 0;
		//int entries = 0;
		//int exits = 0;
		//int inters = 0;
		int[] columns = {0,0,0};
		for(Intersection in : intersections) {
			logger.logMessage("creating drawer...");
			columns = addDrawer(i,columns,in);//,((i%5)+1)*40,((i/5)+1)*40);			
			i++;
		}
	}
	
	public void draw(Graphics g) {
		
		for(IDrawer d : drawers) {
			logger.logMessage("drawing drawer...");
			d.draw(g);
		}
	}
	
	public void refresh(Graphics g) {
		for(IDrawer d : drawers)
			d.refresh(g);
	}

}
