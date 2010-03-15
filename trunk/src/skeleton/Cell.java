//import InitialClassDiagram.*;
//import RoadCell.*;
package skeleton;

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
     * @return
     */
    public abstract Road getRoad();

    /**
     * @return
     */
    public void leave() {
        vehicle = null;
    }

    /**
     * @param r
     * @return
     */
    public void enter(Robber r) {
        vehicle = r;
    }

    /**
     * @param c
     * @return
     */
    public void enter(CivilCar c) {
        vehicle = c;
    }

    /**
     * @param p
     * @return
     */
    public void enter(Policeman p) {
        vehicle = p;
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

    /**
     * @param r
     * @return
     */
    public void enter(Robber r) {
        throw new UnsupportedOperationException();
    }

    /**
     * @param c
     * @return
     */
    public void enter(CivilCar c) {
        
    }

    /**
     * @param p
     * @return
     */
    public void enter(Policeman p) {
        throw new UnsupportedOperationException();
    }

	public abstract ISign getSign();
}
