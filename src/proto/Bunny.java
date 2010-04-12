package proto;
/**
 * 
 * @author Bal·zs
 *
 *A nyuszit megvalÛsÌtÛ oszt·ly, az utakon van jelen.
 */
public class Bunny extends Vehicle {
	
	public Bunny(String name, Game game, Cell cell, Logger logger, CustomReader input) {
        super(name, game, cell, 0, logger, input);		
    }
    
    /**
	 * K√∂vetkez≈ë cell√°ra l√©p√©skor ez a f√ºggv√©ny a kiszemelt cella tartalma
	 * alapj√°n elind√≠tja az interakci√≥t, vagy a cell√°ra l√©p.
     *
     * @param nextCell a cella, ahova l√©pni szeretn√©nk
	 * @param v a j√°rm≈±, amit a cell√°t√≥l lek√©rdezt√ºnk
     */
	public void accept(Cell nextCell, Vehicle v) {
		// ha √ºres a cella, l√©phet
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
	 * Kapcsolatba lÈp egy Civil autoval
	 * @param c a civil auto, mely lÈpett r·
	 */
	public void interact(CivilCar c) {
		INamedObject[] param = {c};
		logger.logEvent("CivilCar $name hits bunny",param);
		Cell ce = cell;
		die();
		c.accept(ce,null);		
	}
	/**
	 * Kapcsolatba lÈp egy rendırrel
	 * @param p a rendır
	 */
	public void interact(Policeman p) {
		INamedObject[] param = {p};
		logger.logEvent("Policeman $name hits bunny",param);
		Cell c = cell;
		die();
		p.accept(c,null);		
	}
	/**
	 * Kapcsolatba lÈp a bankrabloval
	 * @param r a robber
	 */
	public void interact(Robber r) {
		INamedObject[] param = {r};
		logger.logEvent("Robber $name hits bunny, GodMode activated",param);
		Cell c = cell;
		die();
		r.startGodMode();
		r.accept(c,null);
	}
	/**
	 * Kapcsolatba lÈp a nyuszival (egyebekkel)
	 * @param b a nyuszi
	 */
	public void interact(Bunny b) {
	}
	
}
