package proto;

/**
 * Hétköznapi, civil autó: betartja a közlekedési szabályokat.
 */
public class CivilCar extends Vehicle {
    public CivilCar(String name, Game game, Cell cell, int ispeed, Logger logger, CustomReader input) {
        super(name, game, cell, ispeed, logger, input);
    }	      

	/**
	 * A lépéshez kiszemelt cellától lekérdezett jármű alapján a döntést meghozó függvény.
	 */
	public void accept(Cell nextCell, Vehicle v) {
		// ha üres a cella, léphet
		if (v == null) {			
			cell.leave();			
			nextCell.enter(this);			
			cell = nextCell;
			INamedObject[] param = {this};
			logger.logEvent("CivilCar $name moved to next cell",param);
		}	
		// ha nem üres, nem csinálunk semmit (várakozunk, amíg elmegy)	
	}

}
