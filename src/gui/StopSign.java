//import RoadCell.*;
package gui;

import java.io.IOException;
/**
 * Egy ISign interf�sz� t�bla, m�ghozz� speci�lisan 
 * STOP t�bla. Az ide �rkez� j�rm�vek v�rakoz�sra 
 * k�nyszer�lnek, miel�tt tov�bbhaladn�nak.
 * @author Bal�zs
 *
 */
public class StopSign extends NamedObject implements ISign {
	/**
	 * default v�rakoz�si id� (ha nincs set)
	 */
	public static final int default_waitTime = 40;
	/**
	 * A v�rakoz�si id�t deklar�lja egy adott STOP t�bl�n�l. �gy teh�t priv�t bels� v�ltoz�.
	 */
    private int waitTime;
    /**
     * Priv�t attrib�tum, azt hat�rozza meg, mennyi van m�g a v�rakoz�si id�b�l vissza.
     */
    private int tickAmount;

    public StopSign(String name, Logger logger, CustomReader input) {
        super(name, logger, input);
        waitTime = default_waitTime;
        tickAmount = 0;
    }
    /**
     * 
     * @return Blokkol-e a t�bla vehicle-t
     */
    public boolean isBlocking() {
        return (tickAmount < waitTime);
    }
    /**
     * 
     * @param value Be�ll�tja a blokkol�s�t
     */
    public void setBlocking(boolean value) {
		tickAmount = value?0:waitTime;
	}
    /**
     * indul a v�rakoz�s, bel�pett egy vehicle
     */
    public void vehicleEntered() {
		tickAmount = 0;
    }
    /**
     * Stop t�bla tick!
     */
    public void tick() {
		tickAmount++;
    }
}
