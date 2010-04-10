//import RoadCell.*;
package proto;

import java.io.IOException;

public class StopSign extends NamedObject implements ISign {
	
	public static final int default_waitTime = 5;
	
    private int waitTime;
    private int tickAmount;

    public StopSign(String name, Logger logger, CustomReader input) {
        super(name, logger, input);
        waitTime = default_waitTime;
        tickAmount = 0;
    }

    public boolean isBlocking() {
        return (tickAmount < waitTime);
    }
    
    public void setBlocking(boolean value) {
		tickAmount = value?0:waitTime;
	}

    public void vehicleEntered() {
		tickAmount = 0;
    }

    public void tick() {
		tickAmount++;
    }
}
