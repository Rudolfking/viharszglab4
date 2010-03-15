package skeleton;

public class Policeman extends Vehicle {
    private Robber wanted;

    public Policeman(String name, Cell cell, int ispeed, Logger logger, CustomReader input) {
        super(name, cell, ispeed, logger, input);
    }

	public void setWanted(Robber r) {
		wanted = r;
	}

    /**
     * @param v
     * @return
     */
    public boolean onTheSameRoad(Vehicle v) {
        return (getCell().getRoad() == v.getCell().getRoad());
    }

	/**
     * @return
     */
    public void tick() {        

		// ellenőrizzük, hogy eltelt-e a már a sebességnek megfelelő idő
        logger.logMessage("Speed countdown finished? (y/n)");
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

			// ellenőrizzük, hogy nem kerültünk-e egy útra a rablóval
			logger.logCall(this, this, "onTheSameRoad(Robber r)");
			boolean otsr = onTheSameRoad(wanted);
			logger.logReturn(this, this, "onTheSameRoad(Robber r)", new NamedObject(Boolean.toString(otsr),logger,input));
			// ha igen, letartóztatjuk
			if (otsr)
				wanted.busted();
		}	
		// ha nem üres...
	}
	
}
