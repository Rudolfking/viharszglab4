//import RoadCell.*;

public class StopSign extends NamedObject implements ISign {

	private int waitTime;
	private int tickAmount;
	
	public StopSign(String name) {
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