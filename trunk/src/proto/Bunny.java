package proto;
/**
 * 
 * @author Bal�zs
 *
 *A nyuszit megval�s�t� oszt�ly, az utakon van jelen.
 */
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
	/**
	 * Kapcsolatba l�p egy Civil autoval
	 * @param c a civil auto, mely l�pett r�
	 */
	public void interact(CivilCar c) {
		
	}
	/**
	 * Kapcsolatba l�p egy rend�rrel
	 * @param p a rend�r
	 */
	public void interact(Policeman p) {
	}
	/**
	 * Kapcsolatba l�p a bankrabloval
	 * @param r a robber
	 */
	public void interact(Robber r) {
	}
	/**
	 * Kapcsolatba l�p a nyuszival (egyebekkel)
	 * @param b a nyuszi
	 */
	public void interact(Bunny b) {
	}
	
}
