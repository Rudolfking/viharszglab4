//import InitialClassDiagram.*;
package proto;

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
		INamedObject[] param = {p};
		logger.logEvent("Policeman $name leaves the city",param);    
		p.die();				
    }

    /**
     * Lekezeli, ha civil autó lép erre a mezőre.
     * @param c ide lépő civil autó
     */
    public void enter(CivilCar c) {    
		INamedObject[] param = {c};
		logger.logEvent("CivilCar $name leaves the city",param);    
		c.die();				
    }
}
