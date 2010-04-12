package proto;

public class Bunny extends Vehicle {
	
	public Bunny(String name, Game game, Cell cell, Logger logger, CustomReader input) {
        super(name, game, cell, 0, logger, input);		
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
			if (cell.getRoad() == null) {
				INamedObject[] param = {this};
				logger.logEvent("Bunny $name moved to cell option " + Integer.toString(preferredCell),param);				
				preferredCell = -1;
			} else {
				INamedObject[] param = {this};
				logger.logEvent("Bunny $name moved to next cell",param);				
			}
			nextCell.enter(this);							
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
