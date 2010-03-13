//import InitialClassDiagram.*;

public class CityExit extends Intersection {

	public CityExit(String name) {
		super(name);
	}
	
	/**
	 * 
	 * @param p
	 * @return 
	 */
	public void enter(Policeman p) {
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

}