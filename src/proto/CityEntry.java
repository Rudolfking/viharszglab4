//import InitialClassDiagram.*;
package proto;
/**
 * A városba a belépési pontot reprezentáló keresztezõdés.
 * @author Balázs
 *  Cell -> Intersection
 */
public class CityEntry extends Intersection {
    public CityEntry(String name, Logger logger, CustomReader input) {
        super(name, logger, input);
    }
}
