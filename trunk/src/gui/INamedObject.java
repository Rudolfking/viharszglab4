package gui;

/**
 * Általános interfész névvel rendelkező objektumok számára.
 */ 
public interface INamedObject {
	/**
	 * Név lekérdezése.
	 * @return az objektum neve
	 */ 
    String getName();

	/**
	 * Név beállítása
	 * @param a beállítandó név
	 */
    void setName(String name);
}
