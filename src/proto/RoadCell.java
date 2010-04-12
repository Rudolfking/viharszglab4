package proto;

import java.io.IOException;
/**
 * Az �tcella egy olyan speci�lis cella, mely egy konkr�t 
 * cell�t (egy �thoz tartoz� egy-bel�p�s�, egy-kil�p�s�)
 *  reprezent�l. Ennek megfelel�en van fel�p�tve (attrib�tumok, stb.).
 * @author Bal�zs
 *
 */
public class RoadCell extends Cell {
	/**
	 * A k�vetkez� cell�t t�rol� priv�t attrib�tum, el�refel� halad�s eset�n �rv�nyes ez.
	 */
    private Cell nextCell;
    /**
     * El�z� cell�t ez a publikus l�that�s�g� referencia t�rolja, a visszafel� halad�s (bankrabl� pl.) eset�n ez a �k�vetkez� cella�
     */
    private Cell previousCell;
    /**
     * A tartoz� �t
     */
    private Road road;
    /**
     * �ton a t�bla!
     */
    private ISign sign;

    public RoadCell(String name, Road road, ISign sign, Logger logger, CustomReader input) {
        super(name, logger, input);
        this.road = road;
        this.sign = sign;        
    }

    /**
     * @param prev El�z� cella be�ll�tand�
     * @param next K�vetkez� cella be�ll�tand�
     * @return A szomsz�dos cell�kat be�ll�t� publikus f�ggv�ny (hogy a gener�l�sn�l el�rhess�k), egy el�z�, �s egy k�vetkez� cell�t fix�l.
     */
    public void setNeighbourCells(Cell prev, Cell next) {
        previousCell = prev;
        nextCell = next;
    }

	/**
     * @return Visszaadja a k�vetkez� cell�kat
     */
    public Cell[] getNextCells() {
		Cell[] res = new Cell[1];
		res[0] = nextCell;
		return res;
	}

    /**
     * @return Visszaadja az el�z� cell�kat
     */
    public Cell[] getPreviousCells() {
		Cell[] res = new Cell[1];
		res[0] = previousCell;
		return res;
	}

    /**
     * @return Visszaadja a t�bl�t az �ton
     */
    public ISign getSign() {
        return sign;
    }
    /**
     * 
     * @return Visszaadja a tartalmaz� utat.
     */
	public Road getRoad() {
		return road;
	}
}
