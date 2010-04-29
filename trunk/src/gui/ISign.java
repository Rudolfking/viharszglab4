package gui;
/**
 * Interfész a közlekedési táblákat/lámpákat megvalósító
 *  osztályok számára. Feladata a blokkolás lekérdezésére adott válasz, 
 *  kezelni a belépõ jármûvet, stb.
 * @author Balázs
 *
 */
public interface ISign extends INamedObject {
    /**
     * 
     * @return Egy publikus láthatóságú boolean függvény, mely annak 
     * megfelelõen tér vissza, hogy a tábla vagy lámpla éppen blokkolja-e az áthaladást
     */
	boolean isBlocking();
	/**
	 * 
	 * @param value beállítja a blocking értéket
	 */
	void setBlocking(boolean value);
	/**
	 * Egy jármû belépését kezeli le (elindítja a várakoztatást, stb.). Láthatósága publikus.
	 */
    void vehicleEntered();
    /**
     * Tick parancs (publikus függvény), mely a táblákat szólítja fel az idõegység léptetésére
     */
    void tick();
}
