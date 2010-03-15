package skeleton;

public class Robber extends Vehicle {
    private boolean isGoingForward;
    private int minimumInverseSpeed;
    private int maximumInverseSpeed;
    private Cell preferredCell;
	private Game game;

    public Robber(String name, Game game, Cell cell, int ispeed, Logger logger, CustomReader input) {
        super(name, cell, ispeed, logger, input);
		this.game = game;
    }

    /**
     * @return
     */
    public void increaseSpeed() {
    }

    /**
     * @return
     */
    public void decreaseSpeed() {        
    }

    /**
     * @return
     */
    public void turnArround() {
        throw new UnsupportedOperationException();
    }

    /**
     * @param c
     * @return
     */
    public void setPreferredCell(Cell c) {
        throw new UnsupportedOperationException();
    }

    /**
     * @param cIn
     * @param cOut
     * @return
     */
    protected Cell chooseFrom(Cell[] cIn, Cell[] cOut) {
        throw new UnsupportedOperationException();
    }

    /**
     * @return
     */
    public void hide() {
        throw new UnsupportedOperationException();
    }

    /**
     * @return
     */
    public void busted() {
        logger.logCall(this, this, "die()");
		die();
		logger.logReturn(this, this, "die()", null);
		logger.logCall(this, game, "kill(Robber r)");
		game.kill(this);
		logger.logReturn(this, game, "kill(Robber r)", null);
		logger.logCall(this, game, "gameOver()");
		game.gameOver();
		logger.logReturn(this, game, "gameOver()", null);
    }

	/**
     * @return
     */
    public void tick() {        

		// ellenőrizzük, hogy eltelt-e a már a sebességnek megfelelő idő
        logger.logMessage("Speed countdown finished? (y/n)");
		String res = input.readLine();
		boolean  ready = (res.compareTo("y")==0);
		// ha eltelt, megkísérelünk lépni
		if (ready) {			
			// (nem ellenőrzünk semmilyen blokkolást)
			logger.logCall(this, this, "step()");
			step();
			logger.logReturn(this, this, "step()", null);		
    	}
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
		// ha nem üres...
	}
}
