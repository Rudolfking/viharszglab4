//import RoadCell.*;
package skeleton;

import java.io.IOException;

public class TrafficLight extends NamedObject implements ISign {
    private int redTime;
    private int greenTime;
    private int offset;

    public TrafficLight(String name, Logger logger, CustomReader input) {
        super(name, logger, input);
    }

    public boolean isBlocking() {
        logger.logMessage("Is Stop Sign - " + getName() + " - is blocking?");
        logger.logMessage("0 - false");
        logger.logMessage("1 - true");        
            String str = input.readLine();
            if (str.compareTo("0") == 0) return false;
            if (str.compareTo("1") == 0) return true;        
        return false;
    }

    public void vehicleEntered() {
    }

    public void tick() {
    }
}
