//import RoadCell.*;
package proto;

import java.io.IOException;

public class TrafficLight extends NamedObject implements ISign {
	
	public static final int default_redTime = 3;
	public static final int default_greenTime = 3;
	
    private int redTime;
    private int greenTime;
    private int offset;

    public TrafficLight(String name, Logger logger, CustomReader input) {
        super(name, logger, input);
        redTime = default_redTime;
        greenTime = default_greenTime;
        offset = 0;
    }

    public boolean isBlocking() {
        return (offset < greenTime);
    }       

	public void setBlocking(boolean value) {
		offset = value?greenTime:0;
	}

    public void vehicleEntered() {		
    }

    public void tick() {
		offset++;
		if(offset == (greenTime + redTime))
			offset = 0;
    }
}
