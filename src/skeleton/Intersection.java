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
    }

    /**
     * @param c
     * @return
     */
    public void addPreviousCell(Cell c) {
    }
}