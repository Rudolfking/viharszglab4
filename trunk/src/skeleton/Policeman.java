package skeleton;
public class Policeman extends Vehicle {

	private Robber wanted;

	public Policeman(String name) {
		super(name);
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
