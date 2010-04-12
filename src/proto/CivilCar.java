package proto;

/**
 * A civil autÛt megvalÛsÌtÛ oszt·ly, egy, az utakon kˆzlekedı civil autÛ reprezent·l·s·ra.
 */
public class CivilCar extends Vehicle {
    public CivilCar(String name, Game game, Cell cell, int ispeed, Logger logger, CustomReader input) {
        super(name, game, cell, ispeed, logger, input);
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
	 * @param c A belÈpı civil autÛ interact-ja
	 */
	public void interact(CivilCar c) {
		INamedObject[] param = {c,this};
		logger.logEvent("CivilCar $otherCar waiting for $thisCar to pass",param);
	}
	/**
	 * 
	 * @param p rendırre
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
