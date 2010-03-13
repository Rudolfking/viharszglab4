//import RoadCell.*;
package proto;
public class TrafficLight extends NamedObject implements ISign {

	private int redTime;
	private int greenTime;
	private int offset;
	
	public TrafficLight(String name) {
		super(name);
	}
	
	public boolean isBlocking() {
		return true;
	}
	
	public void vehicleEntered() {
		
	}
	
	public void tick() {
		
	}

}