//import InitialClassDiagram.*;
//import RoadCell.*;
package skeleton;
public abstract class Cell extends NamedObject {
	
	private Vehicle vehicle;
	
	public Cell(String name, Logger logger,
			CustomReader input) {
		super(name,logger,input);
	}

	/**
	 * 
	 * @return 
	 */
	public Cell[] getNextCells() {
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @return 
	 */
	public Cell[] getPreviousCells() {
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @return 
	 */
	public Vehicle getVehicle() {
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param v
	 * @return 
	 */
	public void setVehicle(Vehicle v) {
	}

	/**
	 * 
	 * @return 
	 */
	public Road getRoad() {
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @return 
	 */
	public void leave() {
		throw new UnsupportedOperationException();
	}


	/**
	 * 
	 * @param r
	 * @return 
	 */
	public void enter(Robber r) {
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param c
	 * @return 
	 */
	public void enter(CivilCar c) {
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param p
	 * @return 
	 */
	public void enter(Policeman p) {
		throw new UnsupportedOperationException();
	}

}