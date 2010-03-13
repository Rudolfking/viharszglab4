//package InitialClassDiagram;
package skeleton;
public class Road extends NamedObject {

	private Cell[] cells;
	private Intersection entry;
	private Intersection exit;

	public Road(String name, Intersection entry, Intersection exit, int nCells,
			Logger logger, CustomReader input) {
		super(name);
		this.entry = entry;
		this.exit = exit;

		cells = new RoadCell[nCells];

		logger.logCreate(this, "RoadCell");
		cells[0] = new RoadCell(name + "_cell0", false, logger, input);
		logger.logCreated(this, cells[0]);
		logger.logCall(this, entry, "addNextCell(Cell c)");
		entry.addNextCell(cells[0]);
		logger.logReturn(this, entry, "addNextCell(Cell c)", null);

		if (nCells > 1) {
			for (int i = 1; i < nCells - 1; i++) {
				logger.logCreate(this, "RoadCell");
				cells[i] = new RoadCell(name + "_cell" + Integer.toString(i),
						false, logger, input);
				logger.logCreated(this, cells[i]);
			}

			logger.logCreate(this, "RoadCell");
			cells[nCells - 1] = new RoadCell(name + "_cell"
					+ Integer.toString(nCells - 1), true, logger, input);
			logger.logCreated(this, cells[nCells - 1]);
		}
		logger.logCall(this, exit, "addPreviousCell(Cell c)");
		entry.addPreviousCell(cells[nCells - 1]);
		logger.logReturn(this, exit, "addPreviousCell(Cell c)", null);

	}

	/**
	 * 
	 * @return
	 */
	public Intersection getExitIntersection() {
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @return
	 */
	public Intersection getEntrytIntersection() {
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @return
	 */
	public void generateCells() {
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param v
	 * @return
	 */
	public void placeCar(Vehicle v) {
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @return
	 */
	public void tick() {

	}

}