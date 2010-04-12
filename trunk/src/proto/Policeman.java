package proto;

/**
 * A rend�rt megval�s�t� oszt�ly, mely a j�rm� minden tulajdons�g�val rendelkezik, valamint
 *  tiszt�ban van mag�val a bankrabl�val is, �s ha egy utc�ba ker�lnek, �gy el is tudja kapni �t.
 */
public class Policeman extends Vehicle {
    private Robber wanted;

    public Policeman(String name, Game game, Cell cell, int ispeed, Logger logger, CustomReader input) {
        super(name, game, cell, ispeed, logger, input);
    }
    /**
     * 
     * @param r r�gz�ti a r, robber �rt�ket mag�nak
     */
	public void setWanted(Robber r) {
		wanted = r;
	}

    /**
     * @param v A j�rm�, mellyel egy utc�ra ker�l
     * @return Ha egy utc�ra ker�l a rend�r a rabl�val, abban a pillanatban letart�ztatja.
     */
    public boolean onTheSameRoad(Vehicle v) {
        return ((getCell().getRoad() != null) && (getCell().getRoad() == v.getCell().getRoad()));
    }

	/**
     * @return L�ptet
     */
    public void tick() {        
		
		if (ticksLeft>0)
			ticksLeft--;
		else
		// ellenőrizzük, hogy eltelt-e a már a sebességnek megfelelő idő        				
		// ha eltelt, megkísérelünk lépni
		if (((game != null) && (!game.speed)) || (ticksLeft==0)) {
			// sebesség-számláló újraindítása
			ticksLeft = inverseSpeed;			
			// lépés megkísérlése
			step();				
    	}    	
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
	 * @param p Rend�r
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
