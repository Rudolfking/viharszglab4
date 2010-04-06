package proto;

public class Bunny extends Vehicle {
	
	public Bunny(String name, Cell cell, Logger logger, CustomReader input) {
        super(name, cell, 0, logger, input);		
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
		}	
		// ha nem üres, nem csinálunk semmit (várakozunk, amíg elmegy)	
	}
	
}
