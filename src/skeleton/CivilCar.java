package skeleton;

public class CivilCar extends Vehicle {
    public CivilCar(String name, Cell cell, int ispeed, Logger logger, CustomReader input) {
        super(name, cell, ispeed, logger, input);
    }

	/**
	 *
	 */
	public void step() {
		
		// aktuális cellán lévő közlekedési jelzés lekérdezése		
		logger.logCall(this, cell, "getSign()");
		ISign s = cell.getSign();
		logger.logReturn(this, cell, "getSign()", s);
		if (s != null) {
			// ha van jelzés, blokkolás lekérdezése
			logger.logCall(this, s, "isBlocking()");
			boolean blocking = s.isBlocking();
			if (!blocking) {
				// ha a jelzés nem blokkol, a következő cellák listájának lekérdezése
				logger.logCall(this, cell, "getNextCells()");
				Cell[] nextCells = cell.getNextCells();
				for (Cell c : nextCells)
					logger.logReturn(this, cell, "getNextCells()", c);
				// a listából egy cella kiválasztása
				logger.logCall(this, this, "chooseFrom(Cell[] cells)");
				Cell nextCell = chooseFrom(nextCells);
				logger.logReturn(this, this, "chooseFrom(Cell[] cells)", nextCell);
				// annak a vizsgálata, hogy a kiszemelt cellán tartózkodik-e autó
				logger.logCall(this, nextCell, "getVehicle()");
				Vehicle v = nextCell.getVehicle();
				logger.logReturn(this, nextCell, "getVehicle()", v);
			}
			logger.logReturn(this, s, "isBlocking()", new NamedObject((Boolean.valueOf(blocking)).toString(), logger, input));
		}
			
	}
}
