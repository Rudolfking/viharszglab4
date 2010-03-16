package skeleton;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Skeleton {
    /**
	 * Szkeleton main függvénye <br>
	 * Egy menüt jelenít meg, ahol kiválaszthatjuk a tesztelés bemenetét és
	 * a tesztesetet. Paraméterként kapott fájl esetén indítja az automatikus tesztelést.
	 * 
	 * @param args
	 *            parancssori argumentumok - silent mód kapcsoló, input fájl neve
	 */
    public static void main(String[] args) {
        // ezt a loggert használjuk az egész program során
        Logger logger = new ConsoleLogger();
        // saját bemeneti olvasó osztály: konzolról és fájlból is tud sort olvasni, kommenteket (#-val kezdődő sor) nem veszi figyelembe
        CustomReader input = new CustomReader(logger);
        // parancssori paraméterek elemzése
        boolean silent = false; // silent módban automatikus tesztelésnél nem írja ki a kérdéseket
        String fileName = ""; // tesztelés bemeneteként használt input fájlok neve
        if (args.length > 0) for (String str : args) {
            if (str.charAt(0) == '-') {
                if ((str.length() > 1) && (str.charAt(1) == 's')) silent = true;
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
            // ha adtunk meg parancssori paraméterként fájlt, beállítjuk
            // bemenetnek
            if (fileName.compareTo("") != 0) {
                logger.logMessage("Input file given as command line argument: " + fileName);
                input.setInput(new BufferedReader(new FileReader(fileName)));
                if (!silent) input.setEcho(true);
                // ha nem, akkor választhatunk konzol és fájl közül
            } else {
                // a választ konzolról olvassuk, ehhez a reader
                BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
                // menü megjelenítése
                logger.logMessage("Please choose input stream:");
                logger.logMessage("1 - console");
                logger.logMessage("2 - file");
                // válasz beolvasása
                String str = in.readLine();
                // alapértelmezett választás a konzol, érvénytelen válasz esetén
                // is
                input.setInput(new BufferedReader(new InputStreamReader(System.in)));
                if (str.compareTo("1") == 0) {
                    // ha konzolt választottunk
                    System.out.println("You've chosen to give input manually from command line.");
                    silent = false;
                } else if (str.compareTo("2") == 0) {
                    // ha fájlt választottunk, bekérjük a nevét
                    System.out.println("You've chosen to give input via a text file.");
                    System.out.print("Please enter the name of the input file: ");
                    try {
						// fájlnév beolvasása
						while (fileName.equals(""))
							fileName = in.readLine();
						// beállítás bemenetnek
						input.setInput(new BufferedReader(new FileReader(
								fileName)));
						// ha a kérdéseket megjelenítjük, a válaszokat is: echo mód
						if (!silent)
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
            logger.logMessage("1 - Initialize game");
            logger.logMessage("2 - Check blocking signs");
            logger.logMessage("3 - Policeman catches robber");
            logger.logMessage("4 - Replace vanished car");
            logger.logMessage("5 - Robber chooses next cell");
            logger.logMessage("6 - Robber crashes into a car");
            logger.logMessage("7 - Robber reaches hiding place");
            logger.logMessage("8 - Car chooses next cell");
            logger.logMessage("9 - Car removes itself from its cell");
            logger.logMessage("10 - Car slows down behind an other one");
            logger.logMessage("11 - Car moves to next cell");
            logger.logMessage("12 - Car exits the city");
            // teszteset lefuttatása
            test(Integer.valueOf(input.readLine()), logger, input);
            logger.setSilent(false);
            logger.logMessage("---");
            logger.logMessage("Skeleton test finished succesfully!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

	/**
	 * Kiválasztott teszteset lefuttatása.
	 * 
	 * @param testCase Teszteset sorszáma
     * @param logger   logger, amibe az üzeneteket írjuk.
     * @param input    stream, ahonnan az üzeneteket olvassuk.
     * @throws IOException beolvasási hibánál
	 */
	private static void test(int testCase, Logger logger, CustomReader input)

			throws IOException {
		logger.setSuperSilent(false);
		boolean mainSilent = logger.getSilent();
		
		switch (testCase) {
		// =============================================================================
		// első teszteset: inicializálás
		case 1:
            logger.setSilent(false);
			logger.logMessage("***");
			logger.logMessage("Test case 1: Initialization");
			logger.logMessage("***");
            logger.setSilent(mainSilent);
			// játék objektum létrehozása
			logger.logMessage("Creating game object...");
			Game game = new Game("game", logger, input);
			logger.logMessage("[" + game.getName() + "|Game|" + game.hashCode()
					+ "] successfully created.");

			// pálya adatainak bekérése

			// bejáratok száma
			logger.logMessage("Please specify the number of city entries:");
			int nEntries = input.readInt();
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
			logger.log("Starting test...");
			logger.log("game.generateLevel(int nEntries, int nExits, int nIntersections, int nRoads, int nCivilCars)");
			game.generateLevel(nEntries, nExits, nIntersections, nRoads, nCivilCars);
			break;
		// =============================================================================
		// második teszteset: tábla/lámpa blokkolásának ellenőrzése
		case 2:
            logger.setSilent(false);
			logger.logMessage("***");
			logger.logMessage("Test case 2: Check blocking signs");
			logger.logMessage("***");
			logger.setSilent(mainSilent);			            
			//tesztpálya építése
			logger.logMessage("Generating test map...");     
			logger.setSilent(true);
            logger.setSuperSilent(true);						
			//egy táblás cella
            RoadCell cell0 = new RoadCell("cell0", null, true, logger, input);  
			//egy üres cella
            RoadCell cell1 = new RoadCell("cell1", null, false, logger, input);    
            cell0.setNeighbourCells(null, cell1);
            cell1.setNeighbourCells(cell0, null);

			logger.setSilent(mainSilent);
			//választhatunk, hogy rendőr, vagy civil
            logger.logMessage("What kind of vehicle do you want to test?");      
            logger.logMessage("0 - CivilCar");
            logger.logMessage("1 - Policeman");
			logger.logMessage("2 - Robber");
            Vehicle car0;            
            int choice = input.readInt(0,2);
			switch(choice) {
            default:
				car0 = new CivilCar("civilcar", cell0, 10, logger, input);
				break;
			case 1:
                car0 = new Policeman("policeman", cell0, 10, logger, input);
				break;
			case 2:
				car0 = new Robber("robber", null, cell0, 10, logger, input);
				break;
            }            
            logger.setSuperSilent(false);
			logger.log("Starting test...");
			logger.log("car0.tick()");
            car0.tick();
            break;
		// =============================================================================
		// harmadik teszteset: bankrabló elkapása
		case 3:
            logger.setSilent(false);
			logger.logMessage("***");
			logger.logMessage("Test case 3: Policeman catches robber");
			logger.logMessage("***");
            logger.setSilent(mainSilent);			            
			//tesztpálya építése
			logger.logMessage("Generating test map...");     
			logger.setSilent(true);
            logger.setSuperSilent(true);						
			// játék objektum létrehozása
			game = new Game("game", logger, input);
			// két útkereszteződés
			Intersection i1 = new Intersection("i1", logger, input);
			Intersection i2 = new Intersection("i2", logger, input);
			// közöttük egy út
			Road r = new Road("r", i1, i2, 2, false, logger, input);
			// egy rendőr áll az első útkereszteződésen
			Policeman p = new Policeman("p",null,10,logger,input);
			i1.setVehicle(p);
			p.setCell(i1);
			// a rabló az út második celláján áll
			Robber robber = new Robber("robber",game,null,10,logger,input);
			r.placeCar(robber,1);
			// a rendőr a rablót akarja elkapni
			p.setWanted(robber);
	
			logger.setSilent(mainSilent);
			logger.setSuperSilent(false);

			// teszteset indítása
			logger.log("Starting test...");
			logger.log("p.step()");
			p.step();			
			
			break;
		// =============================================================================
		// negyedik teszteset: új autó érkezése
		case 4:
            logger.setSilent(false);
			logger.logMessage("***");
			logger.logMessage("Test case 4: Replace vanished car");
			logger.logMessage("***");
            logger.setSilent(mainSilent);			            
			//tesztpálya építése
			logger.logMessage("Generating test map...");     
			logger.setSilent(true);
            logger.setSuperSilent(true);									
			game = new Game("game", logger, input);     
			logger.setSilent(mainSilent);
			logger.setSuperSilent(false);
			//visszatesszuk az eltavozott autokat a varoshatarra
			logger.log("Starting test...");
			logger.log("game.regenerateKilledVehicles()");
            game.regenerateKilledVehicles();
			break;
		// =============================================================================
		// ötödik teszteset: rabló következő cellát választ
		case 5:
            logger.setSilent(false);
			logger.logMessage("***");
			logger.logMessage("Test case 5: Robber chooses next cell");
			logger.logMessage("***");
			logger.setSilent(mainSilent);			            
			//tesztpálya építése
			logger.logMessage("Generating test map...");     
			logger.setSilent(true);
            logger.setSuperSilent(true);						
			// egy útkereszteződés
			Intersection i = new Intersection("i", logger, input);
			// ez a pálya felépíthetősége miatt kell:
			i2 = new Intersection("i2", logger, input);
			// két kimenő út
			Road r1 = new Road("r1", i, i2, 3, false, logger, input);  
			Road r2 = new Road("r2", i, i2, 3, false, logger, input);  			
			// egy bemenő út
			Road r3 = new Road("r3", i2, i, 3, false, logger, input);  			
			// rabló
			robber = new Robber("r",null,null,10,logger,input);
			logger.setSilent(mainSilent);			
			// rabló elhelyezése
			logger.logMessage("Where do you want the robber to be placed?");
			logger.logMessage("1 - i intersection");
			logger.logMessage("2 - r1 outgoing road");
			logger.logMessage("3 - r2 outgoing road");
			logger.logMessage("4 - r3 incoming road");
			choice = input.readInt(1,4);
			switch(choice) {
			case 1:
				robber.setCell(i);
				i.setVehicle(robber);
				break;
			case 2:
				r1.placeCar(robber,1);
				break;
			case 3:
				r2.placeCar(robber,1);
				break;
			case 4:
				r3.placeCar(robber,1);
				break;
			}
			logger.logMessage("What would be the preffered cell for the robber?");			
			logger.logMessage("1 - r1's first cell ");
			logger.logMessage("2 - r2's first cell");
			logger.logMessage("3 - r3's last cell");
			choice = input.readInt(1,3);
			switch(choice) {
			case 1:
				robber.setPreferredCell(r1.getCells()[0]);
				break;
			case 2:
				robber.setPreferredCell(r2.getCells()[0]);
				break;
			case 3:
				robber.setPreferredCell(r3.getCells()[2]);
				break;			
			}

			logger.setSuperSilent(false);

			// teszteset indítása	
			logger.log("Starting test...");
			logger.log("robber.step()");
			robber.step();

			break;
		// =============================================================================
		// hatodik teszteset: rabló autóval ütközik
		case 6:
            logger.setSilent(false);
			logger.logMessage("***");
			logger.logMessage("Test case 6: Robber crashes into a car");
			logger.logMessage("***");
			logger.setSilent(mainSilent);			            
			//tesztpálya építése
			logger.logMessage("Generating test map...");     
			logger.setSilent(true);
            logger.setSuperSilent(true);						
			// játék objektum
			game = new Game("game", logger, input);
			// három szomszédos cella
			cell0 = new RoadCell("cell0", null, false, logger, input);  			
            cell1 = new RoadCell("cell1", null, false, logger, input);
			RoadCell cell2 = new RoadCell("cell2", null, false, logger, input);
			cell0.setNeighbourCells(null, cell1);
            cell1.setNeighbourCells(cell0, cell2);
			cell2.setNeighbourCells(cell1, null);
			// civil autó a harmadik cellán
			CivilCar car = new CivilCar("car", cell2, 10, logger, input);
			cell2.setVehicle(car);
			// rabló a középsőn
			robber = new Robber("robber", game, cell1, 10, logger, input);
			cell1.setVehicle(robber);
			
			logger.setSilent(mainSilent);			            
			logger.setSuperSilent(false);

			// teszteset indítása
			logger.log("Starting test...");
			logger.log("robber.step()");
			robber.step();

			break;
		// =============================================================================
		// hetedik teszteset: rabló elrejtőzik
		case 7:
            logger.setSilent(false);
			logger.logMessage("***");
			logger.logMessage("Test case 7: Robber reaches hiding place");
			logger.logMessage("***");
			logger.setSilent(mainSilent);			            
			//tesztpálya építése
			logger.logMessage("Generating test map...");     
			logger.setSilent(true);
            logger.setSuperSilent(true);						
			// játék objektum
			game = new Game("game", logger, input);
			// két szomszédos cella és a rejtekhely
			cell0 = new RoadCell("cell0", null, false, logger, input);
			cell1 = new RoadCell("cell1", null, false, logger, input);
			HidingPlace hp = new HidingPlace("hidingPlace", logger, input);
			cell0.setNeighbourCells(null, cell1);
            cell1.setNeighbourCells(cell0, hp);
			hp.addPreviousCell(cell1);
			// a rabló a második cellán áll
			robber = new Robber("robber", game, cell1, 10, logger, input);
			cell1.setVehicle(robber);

			logger.setSilent(mainSilent);			            
			logger.setSuperSilent(false);

			// teszteset indítása
			logger.log("Starting test...");
			logger.log("robber.step()");
			robber.step();
		
			break;			
		// =============================================================================
		// nyolcadik teszteset: civil autó következő mezőt választ
		case 8:
            logger.setSilent(false);
			logger.logMessage("***");
			logger.logMessage("Test case 8: Car chooses next cell");
			logger.logMessage("***");
			logger.setSilent(mainSilent);			            
			//tesztpálya építése
			logger.logMessage("Generating test map...");     
			logger.setSilent(true);
            logger.setSuperSilent(true);						
			// egy útkereszteződés
			i = new Intersection("i", logger, input);
			// ez a pálya felépíthetősége miatt kell:
			i2 = new Intersection("i2", logger, input);
			// két kimenő út
			r1 = new Road("r1", i, i2, 3, false, logger, input);  
			r2 = new Road("r2", i, i2, 3, false, logger, input);  			
			// egy bemenő út
			r3 = new Road("r3", i2, i, 3, false, logger, input);  			
			// autó
			car = new CivilCar("car",null,10,logger,input);
			logger.setSilent(mainSilent);			            			
			// autó elhelyezése
			logger.logMessage("Where do you want the car to be placed?");
			logger.logMessage("1 - i intersection");
			logger.logMessage("2 - r1 outgoing road");
			logger.logMessage("3 - r2 outgoing road");
			logger.logMessage("4 - r3 incoming road");
			choice = input.readInt(1,4);
			switch(choice) {
			case 1:
				car.setCell(i);
				i.setVehicle(car);
				break;
			case 2:
				r1.placeCar(car,1);
				break;
			case 3:
				r2.placeCar(car,1);
				break;
			case 4:
				r3.placeCar(car,1);
				break;
			}
			
			logger.setSuperSilent(false);
			
			// teszteset indítása
			logger.log("Starting test...");
			logger.log("car.step()");			
			car.step();		

			break;			
		// =============================================================================
		// kilencedik teszteset: Car removes itself from its cell
		case 9:            
            logger.logMessage("***");
		    logger.logMessage("Test case 9: Car removes itself from its cell");
		    logger.logMessage("***");
            logger.setSilent(mainSilent);			            
			//tesztpálya építése
			logger.logMessage("Generating test map...");     
			logger.setSilent(true);
            logger.setSuperSilent(true);						
			//teszt cella letrehozasa
            cell0 = new RoadCell("cell0",null,false,logger,input);   			
            logger.setSilent(mainSilent);
			//Policeman vagy CivilCar?
            logger.logMessage("Policeman or CivilCar?");         
            logger.logMessage("0 - CivilCar");
            logger.logMessage("1 - Policeman");                
            choice = input.readInt(0,1);
            if (choice == 1) {
            	car0 = new Policeman("policeman", cell0, 10, logger, input);
            } else {
            	car0 = new CivilCar("car", cell0, 10, logger, input);
            }                
            cell0.setVehicle(car0);
            logger.setSuperSilent(false);
			//auto megolese
            logger.log("Starting test...");
			logger.log("car0.die()");
            car0.die();                                             
            break;
        // =============================================================================
        // tizedik teszteset: Autó lelassít egy másik autó mögött
        case 10:
            logger.setSilent(false);
            logger.logMessage("***");
            logger.logMessage("Test case 10: Car slows down behind another one");
            logger.logMessage("***");
            logger.setSilent(mainSilent);			            
			//tesztpálya építése
			logger.logMessage("Generating test map...");     
			logger.setSilent(true);
            logger.setSuperSilent(true);						
			// két szomszédos cella létrehozása
            cell0 = new RoadCell("cell0", null, false, logger, input);   
            cell1 = new RoadCell("cell1", null, false, logger, input);   
            cell0.setNeighbourCells(null, cell1);                        
            cell1.setNeighbourCells(cell0, null);
            logger.setSilent(mainSilent);
            Vehicle car1;
			// első autó rendőr vagy civil?
            logger.logMessage("Car in front is Policeman or CivilCar?");  
            logger.logMessage("0 - CivilCar");
            logger.logMessage("1 - Policeman");                
            choice = input.readInt(0,1);
            if (choice == 1) {
            	car1 = new Policeman("cell1_policeman", cell1, 10, logger, input);
            } else {                                                                  
            	car1 = new CivilCar("cell1_civilcar", cell1, 10, logger, input);
            }                
			// második auto rendor vagy civil?
            logger.logMessage("Car behind is Policeman or CivilCar?");    
            logger.logMessage("0 - CivilCar");
            logger.logMessage("1 - Policeman");                
            choice = input.readInt(0,1);
            if (choice == 1) {
            	car0 = new Policeman("cell0_policeman", cell0, 10, logger, input);
            } else {                                                                   
            	car0 = new CivilCar("cell0_civilcar", cell0, 10, logger, input);
            }                
			// autók elhelyezése a cellákon
            cell0.setVehicle(car0);                             
            cell1.setVehicle(car1);
            logger.setSuperSilent(false);
			
			// teszteset indítása
			logger.log("Starting test...");
			logger.log(car0.getName()+".tick()");
            car0.tick();
            break;
        // =============================================================================
        // tizenegyedik teszteset: Autó következő cellára lép
        case 11:                                                                      
            logger.setSilent(false);
            logger.logMessage("***");
            logger.logMessage("Test case 11: Car moves to next cell");
            logger.logMessage("***");
            logger.setSilent(mainSilent);			            
			//tesztpálya építése
			logger.logMessage("Generating test map...");     
			logger.setSilent(true);
            logger.setSuperSilent(true);						
            game = new Game("game", logger, input);
			// két szomszédos cella létrehozása
            cell0 = new RoadCell("cell0", null, false, logger, input);   			
            cell1 = new RoadCell("cell1", null, false, logger, input);   			
            cell0.setNeighbourCells(null, cell1);                        
            cell1.setNeighbourCells(cell0, null);
            logger.setSilent(mainSilent);
			// rendőr vagy civil?
            logger.logMessage("The car to move shall be...");  
            logger.logMessage("0 - CivilCar");
            logger.logMessage("1 - Policeman");
            try {
				// választás beolvasása
                choice = input.readInt(0,1);
                if (choice == 1) {
					car0 = new Policeman("policeman", cell0, 10, logger, input);
				} else {
					car0 = new CivilCar("civilcar", cell0, 10, logger, input);                                                                  					
                }    
                cell0.setVehicle(car0);                             
                logger.setSuperSilent(false);

				// teszteset indítása
				logger.log("Starting test...");
				logger.log(car0.getName()+".step()");
                car0.step();                              
            } catch (Exception e) {
                e.printStackTrace();
            }
            break;
        // =============================================================================
        // tizenkettedik teszteset: Autó elhagyja a várost
        case 12:
            logger.setSilent(false);
            logger.logMessage("***");
            logger.logMessage("Test case 12: Car exits the city");
            logger.logMessage("***");
            logger.setSilent(mainSilent);			            
			//tesztpálya építése
			logger.logMessage("Generating test map...");     
			logger.setSilent(true);
            logger.setSuperSilent(true);
			game = new Game("game", logger, input);						
			i = new CityExit("exit", game, logger, input);
			i2 = new Intersection("i2", logger, input);
			r = new Road("road", i2, i, 3, false, logger, input);
			logger.setSilent(mainSilent);			            			
			// rendőr vagy civil?
            logger.logMessage("The car to exit shall be...");  
            logger.logMessage("0 - CivilCar");
            logger.logMessage("1 - Policeman");
			choice = input.readInt(0,1);
			if (choice == 1)
				car0 = new Policeman("p",null,10,logger, input);
			else
				car0 = new CivilCar("c",null,10,logger, input);
			r.placeCar(car0,2);
			
			logger.setSuperSilent(false);
			
			// teszteset indítása
			logger.log("Starting test...");
			logger.log(car0.getName()+".step()");
			car0.step();
			break;
        // =============================================================================
        // érvénytelen választás
        default:
            logger.logMessage("There is no such test case.");
			break;
		}
	}
}
