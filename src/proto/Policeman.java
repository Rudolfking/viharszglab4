package proto;

/**
 * Rendőr: nem áll meg a lámpáknál, jelzéseknél, ha egy utcára kerül a bankrablóval, elkapja.
 */
public class Policeman extends Vehicle {
    private Robber wanted;

    public Policeman(String name, Game game, Cell cell, int ispeed, Logger logger, CustomReader input) {
        super(name, game, cell, ispeed, logger, input);
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
				logger.logEvent("Policeman $name moved to cell option " + Integer.toString(preferredCell),param);				
				preferredCell = -1;
			} else {
				INamedObject[] param = {this};
				logger.logEvent("Policeman $name moved to next cell",param);				
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
