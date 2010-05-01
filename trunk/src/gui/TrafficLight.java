//import RoadCell.*;
package gui;

import java.io.IOException;
/**
 * K�zleked�si l�mp�t val�s�t meg ez az oszt�ly. 
 * Hol z�ld, hol piros. Ez ut�bbin�l a j�rm�vek 
 * v�rakozni k�nyszer�lnek, m�g ism�t nem v�lt 
 * a l�mpa.
 * @author Bal�zs
 *
 */
public class TrafficLight extends NamedObject implements ISign {
	/**
	 * Default timing-ek
	 */
	public static final int default_redTime = 30;
	public static final int default_greenTime = 30;
	
	/**
	 * A piros idej�t hat�rozza meg ez a priv�t bels� tagv�ltoz�
	 */
    private int redTime;
    /**
     * A z�ld idej�t hat�rozza meg ez a priv�t attrib�tum
     */
    private int greenTime;
    /**
     * K�t �llapot k�z�tti sz�ml�l�, ez alapj�n v�ltakozik, ld. �llapotdiagram
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
     * @return Blokkol-e a piros l�mpa
     */
    public boolean isBlocking() {
        return (offset > greenTime);
    }       
    /**
     * 
     * @param value blokkol�s be�ll�t�sa (greentime)
     */
	public void setBlocking(boolean value) {
		offset = value?greenTime:0;
	}
	/**
	 * J�rm� bel�pett
	 */
    public void vehicleEntered() {		
    }
    /**
     * L�ptet�s! L�mpa �llapotdiagramja.
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
