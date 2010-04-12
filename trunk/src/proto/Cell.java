//import InitialClassDiagram.*;
//import RoadCell.*;
package proto;

public abstract class Cell extends NamedObject {
    private Vehicle vehicle;

    public Cell(String name, Logger logger, CustomReader input) {
        super(name, logger, input);
    }

    /**
     * @return
     */
    public abstract Cell[] getNextCells();

    /**
     * @return
     */
    public abstract Cell[] getPreviousCells();

    /**
     * @return
     */
    public Vehicle getVehicle() {
        return vehicle;
    }

    /**
     * @param v
     * @return
     */
    public void setVehicle(Vehicle v) {
		vehicle = v;		
    }

    /**
     * @param v
     * @return
     */
    public void enter(Vehicle v) {		
        vehicle = v;
    }

    /**
     * @return
     */
    public abstract Road getRoad();

    /**
     * @return
     */
    public void leave() {
        vehicle = null;
    }

	public abstract ISign getSign();
}
