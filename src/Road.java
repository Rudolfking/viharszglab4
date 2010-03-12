//package InitialClassDiagram;

public class Road implements INamedObject {

	private int cells;
	private Intersection entry;
	private Intersection exit;
	
	private String name;
	
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
	public Intersection getExitIntersection() {
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @return 
	 */
	public Intersection getEntrytIntersection() {
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @return 
	 */
	public void generateCells() {
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param v
	 * @return 
	 */
	public void placeCar(Vehicle v) {
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @return 
	 */
	public void tick() {
		
	}

}