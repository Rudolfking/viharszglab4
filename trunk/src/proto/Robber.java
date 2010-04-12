package proto;

/**
 * Bankrabló: nem tartja be a közlekedési szabályokat, a bankból próbál meg eljutni a rejtekhelyig.
 */
public class Robber extends Vehicle {
    private boolean isGoingForward;
    private int minimumInverseSpeed;
    private int maximumInverseSpeed; 
    
    final int default_godModeDuration = 5;
    
    protected int godModeDuration = default_godModeDuration;
    protected int godModeTicksLeft;

    public Robber(String name, Game game, Cell cell, int ispeed, Logger logger, CustomReader input) {
        super(name, game, cell, ispeed, logger, input);	
        preferredCell = 0;
        isGoingForward = true;	
        godModeTicksLeft = 0;        
    }

    /**
     * Sebesség növelése (ha még lehet)
     */
    public void increaseSpeed() {
		if(inverseSpeed > minimumInverseSpeed)
			inverseSpeed--;
    }

    /**
     * Sebesség csökkentése (ha még lehet)
     */
    public void decreaseSpeed() {        
		if(inverseSpeed < maximumInverseSpeed)
			inverseSpeed++;
    }

    /**
     * Haladási irány megfordítása.
     */
    public void turnArround() {
        isGoingForward = !isGoingForward;
    }    

    /**
     * @param cIn
     * @param cOut
     * @return
     */
    protected Cell chooseFrom(Cell[] cIn, Cell[] cOut) {
		
		if (cell.getRoad() != null) {
			if (isGoingForward)
				return cOut[0];
			else
				return cIn[0];
		} else {
			if (preferredCell<cOut.length)
				return cOut[preferredCell];
			else
				return cIn[preferredCell-cOut.length];
		}		
    }

    /**
     * Elrejtőzés a rejtekhelyen. A játékos győz.
     */
    public void hide() {
		INamedObject[] param = {this};   
		logger.logEvent("Robber $name hides",param);     
		game.winGame();		
    }

    /**
     * Rendőr letartóztatja.
     */
    public void busted() {   		
		if (godModeTicksLeft <= 0) {
			die();				
			game.gameOver();
		}
    }
    
    /**
     * Ütközés más autóval.
     */
    public void crash() {
		die();		
		game.gameOver();
	}

	/**
     * Minden óraléptetéskor végrehajtott függvény: visszaszámlál két lépés
	 * között, és megpróbál lépni, ha lejárt a számláló.
     */
    public void tick() {  
						
		if (godModeTicksLeft > -1)	
			godModeTicksLeft--;	
		// ellenőrizzük, hogy eltelt-e a már a sebességnek megfelelő idő        				
		// ha eltelt, megkísérelünk lépni
		if (((game != null) && (!game.speed)) || (ticksLeft==0)) {
			// sebesség-számláló újraindítása
			ticksLeft = inverseSpeed;			
			// lépés megkísérlése
			step();				
    	} 
    	else
    	if (ticksLeft>0)
			ticksLeft--;   	
		if (godModeTicksLeft==0) {
			INamedObject[] param = {this};
			logger.logEvent("Robber $name is no longer in GodMode",param);
		}
	}

	/**
	 * Lépés a kövektező cellára.
	 */
	public void step() {
		
		// a következő cellák listájának lekérdezése		
		Cell[] nextCells = cell.getNextCells();
		Cell[] prevCells = cell.getPreviousCells();		
		// a listából egy cella kiválasztása		
		Cell nextCell = chooseFrom(prevCells, nextCells);		
		// annak a vizsgálata, hogy a kiszemelt cellán tartózkodik-e autó		
		Vehicle v = nextCell.getVehicle();		
		// a kapott eredmény elfogadása		
		accept(nextCell,v);		
	}
	
	/**
	 * Következő cellára lépéskor ez a függvény a kiszemelt cella tartalma
	 * alapján elindítja az interakciót, vagy a cellára lép.
     *
     * @param nextCell a cella, ahova lépni szeretnénk
	 * @param v a jármű, amit a cellától lekérdeztünk
     */
	public void accept(Cell nextCell, Vehicle v) {
		// ha üres a cella, léphet
		if (v == null) {			
			cell.leave();						
			if (cell.getRoad() == null) {
				INamedObject[] param = {this};
				logger.logEvent("Robber $name moved to cell option " + Integer.toString(preferredCell),param);				
				isGoingForward = (preferredCell < cell.getNextCells().length);				
				preferredCell = 0;				
			} else {
				INamedObject[] param = {this};
				logger.logEvent("Robber $name moved to next cell",param);				
			}	
			nextCell.enter(this);					
		} else {
			v.interact(this);
		}
	}	
	/**
	 * 
	 * @param c Civillel interact
	 */
	public void interact(CivilCar c) {
	}

	/**
	 * 
	 * @param c Rend�rrel
	 */
	public void interact(Policeman p) {
	}

	/**
	 * 
	 * @param c Bankrabl�val
	 */
	public void interact(Robber r) {
	}

	/**
	 * 
	 * @param c Nyuszik�val
	 */
	public void interact(Bunny b) {
	}
	
	public void setGodModeDuration(int i) {
		godModeDuration = i;
	}
	
	public void startGodMode() {
		godModeTicksLeft = godModeDuration;
	}
	
	public boolean inGodMode() {
		return godModeTicksLeft > 0;
	}
}
