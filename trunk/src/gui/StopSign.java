//import RoadCell.*;
package gui;

import java.io.IOException;
/**
 * Egy ISign interfészû tábla, méghozzá speciálisan 
 * STOP tábla. Az ide érkezõ jármûvek várakozásra 
 * kényszerülnek, mielõtt továbbhaladnának.
 * @author Balázs
 *
 */
public class StopSign extends NamedObject implements ISign {
	/**
	 * default várakozási idõ (ha nincs set)
	 */
	public static final int default_waitTime = 40;
	/**
	 * A várakozási idõt deklarálja egy adott STOP táblánál. Így tehát privát belsõ változó.
	 */
    private int waitTime;
    /**
     * Privát attribútum, azt határozza meg, mennyi van még a várakozási idõbõl vissza.
     */
    private int tickAmount;

    public StopSign(String name, Logger logger, CustomReader input) {
        super(name, logger, input);
        waitTime = default_waitTime;
        tickAmount = 0;
    }
    /**
     * 
     * @return Blokkol-e a tábla vehicle-t
     */
    public boolean isBlocking() {
        return (tickAmount < waitTime);
    }
    /**
     * 
     * @param value Beállítja a blokkolását
     */
    public void setBlocking(boolean value) {
		tickAmount = value?0:waitTime;
	}
    /**
     * indul a várakozás, belépett egy vehicle
     */
    public void vehicleEntered() {
		tickAmount = 0;
    }
    /**
     * Stop tábla tick!
     */
    public void tick() {
		tickAmount++;
    }
}
