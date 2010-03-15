//package InitialClassDiagram;
package skeleton;

public class Road extends NamedObject {
    private Cell[] cells;
    private Intersection entry;
    private Intersection exit;

    public Road(String name, Intersection entry, Intersection exit, int nCells, Logger logger, CustomReader input) {
        super(name, logger, input);
        this.entry = entry;
        this.exit = exit;
        logger.logCall(this, this, "generateCells(Intersection entry, Intersection exit, int nCells, Logger logger, CustomReader input)");
        generateCells(entry, exit, nCells);
        logger.logReturn(this, this, "generateCells(Intersection entry, Intersection exit, int nCells, Logger logger, CustomReader input)", null);
    }

    /**
     * @return
     */
    public Intersection getExitIntersection() {
        throw new UnsupportedOperationException();
    }

    /**
     * @return
     */
    public Intersection getEntrytIntersection() {
        throw new UnsupportedOperationException();
    }

    /**
     * @return
     */
    public void generateCells(Intersection entry, Intersection exit, int nCells) {
        cells = new RoadCell[nCells];
        // első cella létrehozása
        logger.logCreate(this, "RoadCell");
        cells[0] = new RoadCell(getName() + "_cell0", (nCells == 1), logger, input);
        logger.logCreated(this, cells[0]);
        // bejárat-kereszteződéshez első cella bekötése
        logger.logCall(this, entry, "addNextCell(Cell c)");
        entry.addNextCell(cells[0]);
        logger.logReturn(this, entry, "addNextCell(Cell c)", null);
        // más a helyzet, ha több cellánk van
        if (nCells > 1) {
            // köztes cellák létrehozása
            for (int i = 1; i < nCells - 1; i++) {
                logger.logCreate(this, "RoadCell");
                cells[i] = new RoadCell(getName() + "_cell" + Integer.toString(i), false, logger, input);
                logger.logCreated(this, cells[i]);
            }
            // utolsó cella létrehozása
            logger.logCreate(this, "RoadCell");
            cells[nCells - 1] = new RoadCell(getName() + "_cell" + Integer.toString(nCells - 1), true, logger, input);
            logger.logCreated(this, cells[nCells - 1]);
            // utolsó cella szomszédainak bekötése
            logger.logCall(this, cells[nCells - 1], "setNeighbourCells(Cell prev, Cell next)");
            ((RoadCell) cells[nCells - 1]).setNeighbourCells(cells[nCells - 2], exit);
            logger.logReturn(this, cells[nCells - 1], "setNeighbourCells(Cell prev, Cell next)", null);
        } else {
            // egyetlen cella szomszédainak bekötése
            logger.logCall(this, cells[0], "setNeighbourCells(Cell prev, Cell next)");
            ((RoadCell) cells[0]).setNeighbourCells(entry, exit);
            logger.logReturn(this, cells[0], "setNeighbourCells(Cell prev, Cell next)", null);
        }
        // kijárat-kereszteződéshez utolsó cella bekötése
        logger.logCall(this, exit, "addPreviousCell(Cell c)");
        entry.addPreviousCell(cells[nCells - 1]);
        logger.logReturn(this, exit, "addPreviousCell(Cell c)", null);
        if (nCells > 1) {
            // első cella szomszédainak bekötése
            logger.logCall(this, cells[0], "setNeighbourCells(Cell prev, Cell next)");
            ((RoadCell) cells[0]).setNeighbourCells(entry, cells[1]);
            logger.logReturn(this, cells[0], "setNeighbourCells(Cell prev, Cell next)", null);
        }
        // köztes cellák egymáshoz kötése
        for (int i = 1; i < nCells - 1; i++) {
            logger.logCall(this, cells[i], "setNeighbourCells(Cell prev, Cell next)");
            ((RoadCell) cells[i]).setNeighbourCells(cells[i - 1], cells[i + 1]);
            logger.logReturn(this, cells[i], "setNeighbourCells(Cell prev, Cell next)", null);
        }
    }

    /**
     * @param v
     * @return
     */
    public void placeCar(Vehicle v) {
        throw new UnsupportedOperationException();
    }

    /**
     * @return
     */
    public void tick() {
    }
}
