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
				Cell[] nextCells = cell.getNextCells();
				Cell nextCell = chooseFrom(nextCells);
				Vehicle v = nextCell.getVehicle();
			}
			logger.logReturn(this, s, "isBlocking()", new NamedObject((Boolean.valueOf(blocking)).toString(), logger, input));
		}
			
	}
}
