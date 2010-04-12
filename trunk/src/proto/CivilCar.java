package proto;

/**
 * A civil aut�t megval�s�t� oszt�ly, egy, az utakon k�zleked� civil aut� reprezent�l�s�ra.
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
			if (cell.getRoad() == null) {
				INamedObject[] param = {this};
				logger.logEvent("CivilCar $name moved to cell option " + Integer.toString(preferredCell),param);				
				preferredCell = -1;
			} else {
				INamedObject[] param = {this};
				logger.logEvent("CivilCar $name moved to next cell",param);				
			}			
			nextCell.enter(this);				
		} else {
			v.interact(this);
		}
	}	
	/**
	 * 
	 * @param c A bel�p� civil aut� interact-ja
	 */
	public void interact(CivilCar c) {
		INamedObject[] param = {c,this};
		logger.logEvent("CivilCar $otherCar waiting for $thisCar to pass",param);
	}
	/**
	 * 
	 * @param p rend�rre
	 */
	public void interact(Policeman p) {
	}
	
	public void interact(Robber r) {
		INamedObject[] param = {r,this};
		logger.logEvent("Robber $robber crashed to $thisCar",param);
		r.crash();
	}
	/**
	 * 
	 * @param b nyuszira
	 */
	public void interact(Bunny b) {
	}

}
