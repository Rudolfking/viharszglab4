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
        return ((getCell().getRoad() != null) && (getCell().getRoad() == v.getCell().getRoad()));
    }

	/**
     * @return
     */
    public void tick() {        
		
		if (ticksLeft>0)
			ticksLeft--;
		else
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
			boolean arrest = onTheSameRoad(wanted);
			if (arrest) {
				INamedObject[] param = {this,wanted};
				logger.logEvent("Policeman $name arrested $robber",param);				
				wanted.busted();
			}
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
