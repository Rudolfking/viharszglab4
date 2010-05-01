//import RoadCell.*;
package gui;

import java.io.IOException;
/**
 * Közlekedési lámpát valósít meg ez az osztály. 
 * Hol zöld, hol piros. Ez utóbbinál a jármûvek 
 * várakozni kényszerülnek, míg ismét nem vált 
 * a lámpa.
 * @author Balázs
 *
 */
public class TrafficLight extends NamedObject implements ISign {
	/**
	 * Default timing-ek
	 */
	public static final int default_redTime = 30;
	public static final int default_greenTime = 30;
	
	/**
	 * A piros idejét határozza meg ez a privát belsõ tagváltozó
	 */
    private int redTime;
    /**
     * A zöld idejét határozza meg ez a privát attribútum
     */
    private int greenTime;
    /**
     * Két állapot közötti számláló, ez alapján váltakozik, ld. állapotdiagram
     */
    private int offset;

    public TrafficLight(String name, Logger logger, CustomReader input) {
        super(name, logger, input);
        redTime = default_redTime;
        greenTime = default_greenTime;
        offset = 0;
    }
    /**
     * 
     * @return Blokkol-e a piros lámpa
     */
    public boolean isBlocking() {
        return (offset > greenTime);
    }       
    /**
     * 
     * @param value blokkolás beállítása (greentime)
     */
	public void setBlocking(boolean value) {
		offset = value?greenTime:0;
	}
	/**
	 * Jármû belépett
	 */
    public void vehicleEntered() {		
    }
    /**
     * Léptetés! Lámpa állapotdiagramja.
     */
    public void tick() {
		offset++;
		if(offset == greenTime) {
			INamedObject[] param = {this};
			logger.logEvent("TrafficLight $name changed to blocking",param);
		} else
		if(offset == (greenTime + redTime)) {
			offset = 0;
			INamedObject[] param = {this};
			logger.logEvent("TrafficLight $name changed to non-blocking",param);
		}
    }
}
