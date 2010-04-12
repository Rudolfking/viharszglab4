package proto;

/**
 * Hétköznapi, civil autó: betartja a közlekedési szabályokat.
 */
public class CivilCar extends Vehicle {
    public CivilCar(String name, Game game, Cell cell, int ispeed, Logger logger, CustomReader input) {
        super(name, game, cell, ispeed, logger, input);
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
				logger.logEvent("CivilCar $name moved to cell option " + Integer.toString(preferredCell),param);				
				preferredCell = -1;
			} else {
				INamedObject[] param = {this};
				logger.logEvent("CivilCar $name moved to next cell",param);				
			}			
			cell = nextCell;
		} else {
			v.interact(this);
		}
	}	
	
	public void interact(CivilCar c) {
		INamedObject[] param = {c,this};
		logger.logEvent("CivilCar $otherCar waiting for $thisCar to pass",param);
	}
	
	public void interact(Policeman p) {
	}
	
	public void interact(Robber r) {
		INamedObject[] param = {r,this};
		logger.logEvent("Robber $robber crashed to $thisCar",param);
		r.crash();
	}
	
	public void interact(Bunny b) {
	}

}
