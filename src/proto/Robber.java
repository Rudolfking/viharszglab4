package proto;
public class Robber extends Vehicle {

	private boolean isGoingForward;
	private int minimumInverseSpeed;
	private int maximumInverseSpeed;
	private Cell preferredCell;

	/**
	 * 
	 * @return 
	 */
	public void increaseSpeed() {
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @return 
	 */
	public void decreaseSpeed() {
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @return 
	 */
	public void turnArround() {
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param c
	 * @return 
	 */
	public void setPreferredCell(Cell c) {
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param cIn
	 * @param cOut
	 * @return 
	 */
	protected Cell chooseFrom(Cell[] cIn, Cell[] cOut) {
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @return 
	 */
	public void hide() {
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @return 
	 */
	public void busted() {
		throw new UnsupportedOperationException();
	}

}