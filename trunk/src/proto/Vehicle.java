package proto;
public abstract class Vehicle implements INamedObject {

	private int ticksLeft;
	private Cell cell;
	private int inverseSpeed;
	private Game game;
	String name;
	
	public String getName() {
		return name; 
	}
	
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * 
	 * @param v
	 * @return 
	 */
	private void accept(Vehicle v) {
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param c
	 * @return 
	 */
	protected Cell chooseFrom(Cell[] c) {
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @return 
	 */
	public Cell getCell() {
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param c
	 * @return 
	 */
	public void setCell(Cell c) {
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @return 
	 */
	public void die() {
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @return 
	 */
	public void tick() {
		throw new UnsupportedOperationException();
	}

}