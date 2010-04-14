//import InitialClassDiagram.*;
//import RoadCell.*;
package proto;
/**
 * Egy cellát reprezentál, ami a pályán helyezkedik el. Absztrakt osztály, de minden cellához szükséges tulajdonsággal fel van ruházva.
 * @author Balázs
 */
public abstract class Cell extends NamedObject {
	/**
	 * @param A cellán álló Vehicle referenciája, privát adattag, csak a belső függvények látják.
	 */
    private Vehicle vehicle;

    public Cell(String name, Logger logger, CustomReader input) {
        super(name, logger, input);
    }

    /**
     * @return visszaadja a következő cellákat, ahova lehet menni innen
     */
    public abstract Cell[] getNextCells();

    /**
     * @return visszaadja az elozo cellákat, ahova lehet menni innen
     */
    public abstract Cell[] getPreviousCells();

    /**
     * @return A cellán található jármű referenciájával visszatérő publikus láthatóságú tagfüggvénye a Cell osztálynak
     */
    public Vehicle getVehicle() {
        return vehicle;
    }

    /**
     * @param v A cellára jármű érkezik, ezt rögzíti és eltárolja
     * 
     */
    public void setVehicle(Vehicle v) {
		vehicle = v;
		if (getSign() != null)
			getSign().vehicleEntered();		
		v.setCell(this);
    }

    /**
     * @param v civil autó esete
     * 
     */
    public void enter(CivilCar c) {		
        setVehicle(c);
    }
    
    /**
     * @param p Rendőr esete
     * @return
     */
    public void enter(Policeman p) {		
        setVehicle(p);
    }
    
    /**
     * @param r A cellára érkező bankrabló
     * @return
     */
    public void enter(Robber r) {		
        setVehicle(r);
    }
    
    /**
     * @param b  A nyuszi beérkezésének esetében
     * @return
     */
    public void enter(Bunny b) {		
        setVehicle(b);
    }

    /**
     * @return Road A cellához tartozó utat adja vissza
     */
    public abstract Road getRoad();

    /**
     * @return A cell�n található jármű elhagyja a cellát
     */
    public void leave() {
        vehicle = null;
    }
/**
 * 
 * @return A cellán (esetlegesen) található táblával visszatér
 */
	public abstract ISign getSign();
}
