//import InitialClassDiagram.*;
package proto;
public class HidingPlace extends Intersection {
	
	public HidingPlace(String name) {
		super(name);
	}

	/**
	 * 
	 * @param r
	 * @return 
	 */
	public void enter(Robber r) {
		throw new UnsupportedOperationException();
	}

}