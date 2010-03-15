//import InitialClassDiagram.*;
package skeleton;

public class HidingPlace extends Intersection {
    public HidingPlace(String name, Logger logger, CustomReader input) {
        super(name, logger, input);
    }

    /**
     * @param r
     * @return
     */
    public void enter(Robber r) {
        throw new UnsupportedOperationException();
    }
}