//import InitialClassDiagram.*;
package proto;

/**
 * Rejtekhely, ahová a bankrablónak el kell jutnia. Az útkereszteződés speciális fajtája.
 */
public class HidingPlace extends Intersection {
    public HidingPlace(String name, Logger logger, CustomReader input) {
        super(name, logger, input);
    }

    /**
     * Ez a felüldefiniált függvény elrejti a belépő bankrablót.
     * 
     * @param r a belépő bankrabló
     */
    public void enter(Robber r) {	
		logger.logCall(this, r, "hide()");	
        r.hide();
		logger.logReturn(this, r, "hide()", null);	
    }
}
