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
     * Lekezeli, ha rendĹ‘r lĂ©p erre a mezĹ‘re.
     * @param p ide lĂ©pĹ‘ rendĹ‘r
     */
    public void enter(Policeman p) {        
		INamedObject[] param = {p};
		logger.logEvent("Policeman $name leaves the city",param);    
		p.die();				
    }

    /**
     * Lekezeli, ha civil autĂł lĂ©p erre a mezĹ‘re.
     * @param c ide lĂ©pĹ‘ civil autĂł
     */
    public void enter(CivilCar c) {    
		INamedObject[] param = {c};
		logger.logEvent("CivilCar $name leaves the city",param);    
		c.die();				
    }
}
