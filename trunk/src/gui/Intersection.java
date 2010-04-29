//package InitialClassDiagram;
package gui;
/**
 * Egy speci�lis cella, mely el�gaz�st reprezent�l: T�bb bel�p� �s kil�p� cell�ja
 *  lehet/van, valamint t�bbf�le speci�lis el�gaz�s l�tezik (bank, b�v�hely�).
 * @author Bal�zs
 *
 */
public class Intersection extends Cell {
	/**
	 * @param Cell[]  []: A k�vetkez� cell�k list�j�t tartalmaz� publikus attrib�tum.
	 */
    protected Cell[] nextCells;
	/**
	 * @param Cell[]  []: Az el�z� cell�k list�j�t tartalmaz� publikus attrib�tum.
	 */
    protected Cell[] previousCells;

    public Intersection(String name, Logger logger, CustomReader input) {
        super(name, logger, input);
    }          

    /**
     * @param c Egy k�vetkez� cell�t csatol a m�r megl�v� cell�khoz
     * @return
     */
    public void addNextCell(Cell c) {
		if (nextCells == null)
			nextCells = new Cell[0];
		Cell[] nnc = new Cell[nextCells.length+1];
		System.arraycopy(nextCells, 0, nnc, 0, nextCells.length);
		nnc[nnc.length-1] = c;
		nextCells = nnc;
    }

    /**
     * @param c Egy el�z� cell�t csatol a m�r megl�v� cell�khoz
     * @return
     */
    public void addPreviousCell(Cell c) {
		if (previousCells == null)
			previousCells = new Cell[0];
		Cell[] npc = new Cell[previousCells.length+1];
		System.arraycopy(previousCells, 0, npc, 0, previousCells.length);
		npc[npc.length-1] = c;
		previousCells = npc;
    }

	/**
     * @return Az ezen cell�r�l el�refel� halad� ir�nyban el�rhet� cell�kat adja vissza
     */
    public Cell[] getNextCells() {
		return nextCells;
	}

    /**
     * @return Az ezen cell�r�l visszafel� halad�s k�zben el�rhet� cell�kat adja vissza (teh�t amik az ezt megel�z� cell�k)
     */
    public Cell[] getPreviousCells() {
		return previousCells;
	}
/**
 * 
 * @return A cell�n (esetlegesen) tal�lhat� t�bl�val visszat�r� publikus f�ggv�ny.
 */
	public ISign getSign() {
        return null;
    }
/**
 * 
 * @return A cell�hoz tartoz� utat adja vissza. Publikus tagf�ggv�ny
 */
	public Road getRoad() {
		return null;
	}
}
