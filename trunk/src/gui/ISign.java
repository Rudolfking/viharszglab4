package gui;
/**
 * Interf�sz a k�zleked�si t�bl�kat/l�mp�kat megval�s�t�
 *  oszt�lyok sz�m�ra. Feladata a blokkol�s lek�rdez�s�re adott v�lasz, 
 *  kezelni a bel�p� j�rm�vet, stb.
 * @author Bal�zs
 *
 */
public interface ISign extends INamedObject {
    /**
     * 
     * @return Egy publikus l�that�s�g� boolean f�ggv�ny, mely annak 
     * megfelel�en t�r vissza, hogy a t�bla vagy l�mpla �ppen blokkolja-e az �thalad�st
     */
	boolean isBlocking();
	/**
	 * 
	 * @param value be�ll�tja a blocking �rt�ket
	 */
	void setBlocking(boolean value);
	/**
	 * Egy j�rm� bel�p�s�t kezeli le (elind�tja a v�rakoztat�st, stb.). L�that�s�ga publikus.
	 */
    void vehicleEntered();
    /**
     * Tick parancs (publikus f�ggv�ny), mely a t�bl�kat sz�l�tja fel az id�egys�g l�ptet�s�re
     */
    void tick();
}
