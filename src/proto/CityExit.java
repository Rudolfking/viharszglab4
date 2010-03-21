//import InitialClassDiagram.*;
package skeleton;

/**
 * Kijárat a városból: a civil autók és rendőrök ide lépve elhagyják a várost.
 */
public class CityExit extends Intersection {
	private Game game;

    public CityExit(String name, Game game, Logger logger, CustomReader input) {
        super(name, logger, input);
		this.game = game;
    }

    /**
     * Lekezeli, ha rendőr lép erre a mezőre.
     * @param p ide lépő rendőr
     */
    public void enter(Policeman p) {
        logger.logCall(this, p, "die()");
		p.die();
		logger.logReturn(this, p, "die()", null);
		logger.logCall(this, game, "kill(Policeman p)");
		game.kill(p);
		logger.logReturn(this, game, "kill(Policeman p)", null);
    }

    /**
     * Lekezeli, ha civil autó lép erre a mezőre.
     * @param c ide lépő civil autó
     */
    public void enter(CivilCar c) {
        logger.logCall(this, c, "die()");
		c.die();
		logger.logReturn(this, c, "die()", null);
		logger.logCall(this, game, "kill(CivilCar c)");
		game.kill(c);
		logger.logReturn(this, game, "kill(CivilCar c)", null);
    }
}
