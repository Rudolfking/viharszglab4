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
		if (getSign() != null)
			getSign().vehicleEntered();		
		v.setCell(this);
    }

    /**
     * @param v
     * @return
     */
    public void enter(CivilCar c) {		
        setVehicle(c);
    }
    
    /**
     * @param p
     * @return
     */
    public void enter(Policeman p) {		
        setVehicle(p);
    }
    
    /**
     * @param r
     * @return
     */
    public void enter(Robber r) {		
        setVehicle(r);
    }
    
    /**
     * @param b
     * @return
     */
    public void enter(Bunny b) {		
        setVehicle(b);
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
