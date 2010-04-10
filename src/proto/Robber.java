package proto;

/**
 * Bankrabló: nem tartja be a közlekedési szabályokat, a bankból próbál meg eljutni a rejtekhelyig.
 */
public class Robber extends Vehicle {
    private boolean isGoingForward;
    private int minimumInverseSpeed;
    private int maximumInverseSpeed;
    private Cell preferredCell;
	private Game game;

    public Robber(String name, Game game, Cell cell, int ispeed, Logger logger, CustomReader input) {
        super(name, game, cell, ispeed, logger, input);		
    }

    /**
     * @return
     */
    public void increaseSpeed() {
    }

    /**
     * @return
     */
    public void decreaseSpeed() {        
    }

    /**
     * @return
     */
    public void turnArround() {
        throw new UnsupportedOperationException();
    }

    /**
     * @param c
     * @return
     */
    public void setPreferredCell(Cell c) {
		preferredCell = c;
    }

    /**
     * @param cIn
     * @param cOut
     * @return
     */
    protected Cell chooseFrom(Cell[] cIn, Cell[] cOut) {
		
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
			logger.logMessage("Is " + name + " going forward or backward?");
			logger.logMessage("1 - forward");			
			logger.logMessage("2 - backward");
			int choice = input.readInt(1,2);
			if (choice==1)
				return cOut[0];
			else
				return cIn[0];
		}
    }

    /**
     * @return
     */
    public void hide() {
        logger.logCall(this, game, "winGame()");
		game.winGame();
		logger.logReturn(this, game, "winGame()", null);
    }

    /**
     * @return
     */
    public void busted() {
        logger.logCall(this, this, "die()");
		die();
		logger.logReturn(this, this, "die()", null);
		logger.logCall(this, game, "kill(Robber r)");
		game.kill(this);
		logger.logReturn(this, game, "kill(Robber r)", null);
		logger.logCall(this, game, "gameOver()");
		game.gameOver();
		logger.logReturn(this, game, "gameOver()", null);
    }

	/**
     * @return
     */
    public void tick() {        

		// ellenőrizzük, hogy eltelt-e a már a sebességnek megfelelő idő
        logger.logMessage("Speed countdown for " + name + " finished? (y/n)");
		String res = input.readLine();
		boolean  ready = (res.compareTo("y")==0);
		// ha eltelt, megkísérelünk lépni
		if (ready) {			
			// (nem ellenőrzünk semmilyen blokkolást)
			logger.logCall(this, this, "step()");
			step();
			logger.logReturn(this, this, "step()", null);		
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
	 * A lépéshez kiszemelt cellától lekérdezett jármű alapján a döntést meghozó függvény.
	 */
	public void accept(Cell nextCell, Vehicle v) {		
		// ha üres a cella, léphet
		if (v == null) {
			logger.logCall(this, cell, "leave()");
			cell.leave();
			logger.logReturn(this, cell, "leave()", null);			
			logger.logCall(this, nextCell, "enter(Vehicle v)");
			nextCell.enter(this);
			logger.logReturn(this, nextCell, "enter(Vehicle v)", null);
			cell = nextCell;
		// ha nem üres, akkor ütközés van
		} else {
			logger.logCall(this, cell, "leave()");
			cell.leave();
			logger.logReturn(this, cell, "leave()", null);			
			logger.logCall(this, game, "gameOver()");		
			game.gameOver();
			logger.logReturn(this, game, "gameOver()", null);
		}
	}
}
