//import InitialClassDiagram.*;
//import RoadCell.*;
package proto;
/**
 * Egy cell�t reprezent�l, ami a p�ly�n helyezkedik el. Absztrakt oszt�ly, de minden cell�hoz sz�ks�ges tulajdons�ggal fel van ruh�zva.
 * @author Bal�zs
 *
 */
public abstract class Cell extends NamedObject {
	/**
	 * @param A cell�n �ll� Vehicle referenci�ja, priv�t adattag, csak a bels� f�ggv�nyek l�tj�k.
	 */
    private Vehicle vehicle;

    public Cell(String name, Logger logger, CustomReader input) {
        super(name, logger, input);
    }

    /**
     * @return visszaadja a kovetkezo cell�kat, ahova lehet menni innen
     */
    public abstract Cell[] getNextCells();

    /**
     * @return visszaadja az elozo cell�kat, ahova lehet menni innen
     */
    public abstract Cell[] getPreviousCells();

    /**
     * @return A cell�n tal�lhat� j�rm� referenci�j�val visszat�r� publikus l�that�s�g� tagf�ggv�nye a Cell oszt�lynak
     */
    public Vehicle getVehicle() {
        return vehicle;
    }

    /**
     * @param v A cell�ra j�rm� �rkezik, ezt r�gz�ti �s elt�rolja
     * 
     */
    public void setVehicle(Vehicle v) {
		vehicle = v;
		if (getSign() != null)
			getSign().vehicleEntered();		
		v.setCell(this);
    }

    /**
     * @param v civil aut� esete
     * 
     */
    public void enter(CivilCar c) {		
        setVehicle(c);
    }
    
    /**
     * @param p Rend�r esete
     * @return
     */
    public void enter(Policeman p) {		
        setVehicle(p);
    }
    
    /**
     * @param r A cell�ra �rkez� bankrabl�
     * @return
     */
    public void enter(Robber r) {		
        setVehicle(r);
    }
    
    /**
     * @param b  A nyuszi be�rkez�s�nek eset�ben
     * @return
     */
    public void enter(Bunny b) {		
        setVehicle(b);
    }

    /**
     * @return Road A cell�hoz tartoz� utat adja vissza
     */
    public abstract Road getRoad();

    /**
     * @return A cell�n tal�lhat� j�rm� elhagyja a cell�t
     */
    public void leave() {
        vehicle = null;
    }
/**
 * 
 * @return A cell�n (esetlegesen) tal�lhat� t�bl�val visszat�r
 */
	public abstract ISign getSign();
}
