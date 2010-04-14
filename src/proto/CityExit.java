//import InitialClassDiagram.*;
package proto;

/**
 * A városból való kilépési pontot valósítja meg, itt távozhatnak a meghatározott autók a városból.
 * @author Balázs
 */
public class CityExit extends Intersection {
	private Game game;

    public CityExit(String name, Game game, Logger logger, CustomReader input) {
        super(name, logger, input);
		this.game = game;
    }

    /**
     * Lekezeli, ha rendőr láp erre a mezőre.
     * @param p ide lép a rendőr
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
