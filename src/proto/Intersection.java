//package InitialClassDiagram;
package skeleton;

public class Intersection extends Cell {
    private Cell[] nextCells;
    private Cell[] previousCells;

    public Intersection(String name, Logger logger, CustomReader input) {
        super(name, logger, input);
    }

    /**
     * @param c
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
     * @param c
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
     * @return
     */
    public Cell[] getNextCells() {
		return nextCells;
	}

    /**
     * @return
     */
    public Cell[] getPreviousCells() {
		return previousCells;
	}

	public ISign getSign() {
        return null;
    }

	public Road getRoad() {
		return null;
	}
}
