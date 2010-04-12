package proto;

/**
 * Bankrabló: nem tartja be a közlekedési szabályokat, a bankból próbál meg eljutni a rejtekhelyig.
 */
public class Robber extends Vehicle {
    private boolean isGoingForward;
    private int minimumInverseSpeed;
    private int maximumInverseSpeed;    

    public Robber(String name, Game game, Cell cell, int ispeed, Logger logger, CustomReader input) {
        super(name, game, cell, ispeed, logger, input);	
        isGoingForward = true;	
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
     * A következő kereszteződénél kiválasztandó cella sorszámának beállítása.
     * @param c a kivánt cella sorszáma     
     */
    /*public void setPreferredCell(int i) {
		
		Cell[] cIn;
		Cell[] cOut;
		
		if (cell.getRoad()!=null) {			
			if (isGoingForward) {
				cOut = cell.getRoad().getExitIntersection().getNextCells();
				cIn = cell.getRoad().getExitIntersection().getPreviousCells();
			} else {
				cOut = cell.getRoad().getEntryIntersection().getNextCells();
				cIn = cell.getRoad().getEntryIntersection().getPreviousCells();
			}			
			
		} else {
			cOut = cell.getNextCells();
			cIn = cell.getPreviousCells();			
		}
		
		if (i<cOut.length)
			preferredCell = cOut[i];
		else
			preferredCell = cIn[i-cOut.length];
					
    }   */    

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
		/*
		// választás a preferált cella ismeretében
		boolean prefCellIsReachable = false;
		// preferált cella elérhetőségének ellenőrzése (úton nem)
		if (cell.getRoad() == null) {
			for (Cell c : cIn)
				if(c == preferredCell) prefCellIsReachable = true;
			if (!prefCellIsReachable)
				for (Cell c : cOut)
					if(c == preferredCell) prefCellIsReachable = true;
		}
		// ha elérhető, akkor azt választjuk
		if(prefCellIsReachable)
			return preferredCell;
		else {
			// ha nem,
			// attól függően, hogy előre megyünk, vagy hátra, a következő, vagy előző cellát választjuk			
			if (isGoingForward)
				return cOut[0];
			else
				return cIn[0];
		}*/
    }

    /**
     * @return
     */
    public void hide() {        
		game.winGame();		
    }

    /**
     * @return
     */
    public void busted() {        
		die();		
		game.kill(this);		
		game.gameOver();		
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

		if (ticksLeft>0)
			ticksLeft--;		
		// ellenőrizzük, hogy eltelt-e a már a sebességnek megfelelő idő        				
		// ha eltelt, megkísérelünk lépni
		if (((game != null) && (!game.speed)) || (ticksLeft==0)) {
			// sebesség-számláló újraindítása
			ticksLeft = inverseSpeed;			
			// lépés megkísérlése
			step();				
    	}
	}

	/**
	 * Lépés a kövektező cellára.
	 */
	public void step() {
		
		// a következő cellák listájának lekérdezése
		logger.logCall(this, cell, "getNextCells()");
		Cell[] nextCells = cell.getNextCells();
		for (Cell c : nextCells) {
			logger.logReturn(this, cell, "getNextCells()", c);
			logger.setLevel(logger.getLevel()+1);
		}
		logger.setLevel(logger.getLevel()-1);
		logger.logCall(this, cell, "getPreviousCells()");
		Cell[] prevCells = cell.getPreviousCells();
		for (Cell c : prevCells) {
			logger.logReturn(this, cell, "getPreviousCells()", c);
			logger.setLevel(logger.getLevel()+1);
		}
		logger.setLevel(logger.getLevel()-1);		
		// a listából egy cella kiválasztása
		logger.logCall(this, this, "chooseFrom(Cell[] cIn, Cell[] cOut)");
		Cell nextCell = chooseFrom(prevCells, nextCells);
		logger.logReturn(this, this, "chooseFrom(Cell[] cIn, Cell[] cOut)", nextCell);
		// annak a vizsgálata, hogy a kiszemelt cellán tartózkodik-e autó
		logger.logCall(this, nextCell, "getVehicle()");
		Vehicle v = nextCell.getVehicle();
		logger.logReturn(this, nextCell, "getVehicle()", v);
		// a kapott eredmény elfogadása
		logger.logCall(this, this, "accept(Cell nextCell, Vehicle v)");
		accept(nextCell,v);
		logger.logReturn(this, this, "accept(Cell nextCell, Vehicle v)", null);
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
			nextCell.enter(this);	
			if (cell.getRoad() == null) {
				INamedObject[] param = {this};
				logger.logEvent("Robber $name moved to cell option " + Integer.toString(preferredCell),param);				
				preferredCell = 0;
			} else {
				INamedObject[] param = {this};
				logger.logEvent("Robber $name moved to next cell",param);				
			}			
			cell = nextCell;
		} else {
			v.interact(this);
		}
	}	
	
	public void interact(CivilCar c) {
	}
	
	public void interact(Policeman p) {
	}
	
	public void interact(Robber r) {
	}
	
	public void interact(Bunny b) {
	}
}
