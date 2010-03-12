//import InitialClassDiagram.*;
//import RoadCell.*;

public abstract class Cell implements INamedObject {

	private String name;
	private Vehicle vehicle;
	
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
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
		throw new UnsupportedOperationException();
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
	 * @return 
	 */
	public ISign getSign() {
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