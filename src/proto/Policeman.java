package proto;

/**
 * A rendırt megvalÛsÌtÛ oszt·ly, mely a j·rm˚ minden tulajdons·g·val rendelkezik, valamint
 *  tiszt·ban van mag·val a bankrablÛval is, Ès ha egy utc·ba ker¸lnek, Ìgy el is tudja kapni ıt.
 */
public class Policeman extends Vehicle {
    private Robber wanted;

    public Policeman(String name, Game game, Cell cell, int ispeed, Logger logger, CustomReader input) {
        super(name, game, cell, ispeed, logger, input);
    }
    /**
     * 
     * @param r rˆgzÌti a r, robber ÈrtÈket mag·nak
     */
	public void setWanted(Robber r) {
		wanted = r;
	}

    /**
     * @param v A j·rm˚, mellyel egy utc·ra ker¸l
     * @return Ha egy utc·ra ker¸l a rendır a rablÛval, abban a pillanatban letartÛztatja.
     */
    public boolean onTheSameRoad(Vehicle v) {
        return ((getCell().getRoad() != null) && (getCell().getRoad() == v.getCell().getRoad()));
    }

	/**
     * @return LÈptet
     */
    public void tick() {        
		
		if (ticksLeft>0)
			ticksLeft--;
		else
		// ellen≈ërizz√ºk, hogy eltelt-e a m√°r a sebess√©gnek megfelel≈ë id≈ë        				
		// ha eltelt, megk√≠s√©rel√ºnk l√©pni
		if (((game != null) && (!game.speed)) || (ticksLeft==0)) {
			// sebess√©g-sz√°ml√°l√≥ √∫jraind√≠t√°sa
			ticksLeft = inverseSpeed;			
			// l√©p√©s megk√≠s√©rl√©se
			step();				
    	}    	
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
				logger.logEvent("Policeman $name moved to cell option " + Integer.toString(preferredCell),param);				
				preferredCell = -1;
			} else {
				INamedObject[] param = {this};
				logger.logEvent("Policeman $name moved to next cell",param);				
			}	
			nextCell.enter(this);						
			boolean arrest = onTheSameRoad(wanted);
			if (arrest) {
				INamedObject[] param = {this,wanted};
				logger.logEvent("Policeman $name arrested $robber",param);				
				wanted.busted();
			}			
		} else {
			v.interact(this);
		}
	}
	/**
	 * Sima cell interact
	 * @param c Civil
	 */
	public void interact(CivilCar c) {
	}
	/**
	 * Sima cell interact
	 * @param p Rendır
	 */
	public void interact(Policeman p) {
	}
	/**
	 * Sima cell interact
	 * @param r Robber
	 */
	public void interact(Robber r) {
	}
	/**
	 * Sima cell interact
	 * @param b Nyuszi
	 */
	public void interact(Bunny b) {
	}	
}
