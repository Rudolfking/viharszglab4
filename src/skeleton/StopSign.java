//import RoadCell.*;
package skeleton;

import java.io.IOException;

public class StopSign extends NamedObject implements ISign {
    private int waitTime;
    private int tickAmount;

    public StopSign(String name, Logger logger, CustomReader input) {
        super(name, logger, input);
    }

    public boolean isBlocking() {
        logger.logMessage("Is Stop Sign - " + getName() + " - is blocking?");
        logger.logMessage("0 - false");
        logger.logMessage("1 - true");
        try {
            String str = input.readLine();
            if (str.compareTo("0") == 0) return false;
            if (str.compareTo("1") == 0) return true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    public void vehicleEntered() {
    }

    public void tick() {
    }
}