package skeleton;
public class Policeman extends Vehicle {

	private Robber wanted;

	public Policeman(String name,Cell cell, int ispeed) {
		super(name,cell,ispeed);
	}

	/**
	 * 
	 * @param v
	 * @return 
	 */
	public void onTheSameRoad(Vehicle v) {
		throw new UnsupportedOperationException();
	}

}
