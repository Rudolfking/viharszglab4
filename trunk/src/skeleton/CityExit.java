//import InitialClassDiagram.*;
package skeleton;
public class CityExit extends Intersection {
	public CityExit(String name,Logger logger,
			CustomReader input) {
		super(name,logger,
				input);
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