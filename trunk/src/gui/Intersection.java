//package InitialClassDiagram;
package gui;
/**
 * Egy speciális cella, mely elágazást reprezentál: Több belépõ és kilépõ cellája
 *  lehet/van, valamint többféle speciális elágazás létezik (bank, búvóhely…).
 * @author Balázs
 *
 */
public class Intersection extends Cell {
	/**
	 * @param Cell[]  []: A következõ cellák listáját tartalmazó publikus attribútum.
	 */
    protected Cell[] nextCells;
	/**
	 * @param Cell[]  []: Az elõzõ cellák listáját tartalmazó publikus attribútum.
	 */
    protected Cell[] previousCells;

    public Intersection(String name, Logger logger, CustomReader input) {
        super(name, logger, input);
    }          

    /**
     * @param c Egy következõ cellát csatol a már meglévõ cellákhoz
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
     * @param c Egy elõzõ cellát csatol a már meglévõ cellákhoz
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
     * @return Az ezen celláról elõrefelé haladó irányban elérhetõ cellákat adja vissza
     */
    public Cell[] getNextCells() {
		return nextCells;
	}

    /**
     * @return Az ezen celláról visszafelé haladás közben elérhetõ cellákat adja vissza (tehát amik az ezt megelõzõ cellák)
     */
    public Cell[] getPreviousCells() {
		return previousCells;
	}
/**
 * 
 * @return A cellán (esetlegesen) található táblával visszatérõ publikus függvény.
 */
	public ISign getSign() {
        return null;
    }
/**
 * 
 * @return A cellához tartozó utat adja vissza. Publikus tagfüggvény
 */
	public Road getRoad() {
		return null;
	}
}
