//package InitialClassDiagram;
package proto;
public class Road extends NamedObject {

	private Cell[] cells;
	private Intersection entry;
	private Intersection exit;

	public Road(String name, Intersection entry, Intersection exit, int nCells,
			ISign sign, Logger logger, CustomReader input) {
		super(name,logger,input);
		this.entry = entry;
		this.exit = exit;
		
		generateCells(entry, exit, nCells, sign);		
	}

	/**
	 * Visszaadja az út celláinak listáját - csak a szkeletonhoz kell
	 * @return az út celláinak listája
	 */
	public Cell[] getCells() {
		return cells;
	}

	/**
	 * 
	 * @return
	 */
	public Intersection getExitIntersection() {
		return exit;
	}

	/**
	 * 
	 * @return
	 */
	public Intersection getEntryIntersection() {
		return entry;
	}

	/**
	 * 
	 * @return
	 */
	public void generateCells(Intersection entry, Intersection exit, int nCells, ISign sign) {

		cells = new RoadCell[nCells];


		// első cella létrehozása		
		cells[0] = new RoadCell(getName() + "_cell0", this, (nCells==1)?(sign):(null), logger, input);		
		// bejárat-kereszteződéshez első cella bekötése		
		entry.addNextCell(cells[0]);		
		
		// más a helyzet, ha több cellánk van
		if (nCells > 1) {
			// köztes cellák létrehozása
			for (int i = 1; i < nCells - 1; i++) {
				logger.logCreate(this, "RoadCell");
				cells[i] = new RoadCell(getName() + "_cell" + Integer.toString(i),
						this, null, logger, input);
				logger.logCreated(this, cells[i]);
			}

			// utolsó cella létrehozása
			logger.logCreate(this, "RoadCell");
			cells[nCells - 1] = new RoadCell(getName() + "_cell"
					+ Integer.toString(nCells - 1), this, sign, logger, input);
			logger.logCreated(this, cells[nCells - 1]);		

			// utolsó cella szomszédainak bekötése
			logger.logCall(this, cells[nCells-1], "setNeighbourCells(Cell prev, Cell next)");
			((RoadCell)cells[nCells-1]).setNeighbourCells(cells[nCells-2], exit);
			logger.logReturn(this, cells[nCells-1], "setNeighbourCells(Cell prev, Cell next)", null);
		} else {
			// egyetlen cella szomszédainak bekötése
			logger.logCall(this, cells[0], "setNeighbourCells(Cell prev, Cell next)");
			((RoadCell)cells[0]).setNeighbourCells(entry, exit);
			logger.logReturn(this, cells[0], "setNeighbourCells(Cell prev, Cell next)", null);
		}

		// kijárat-kereszteződéshez utolsó cella bekötése
		logger.logCall(this, exit, "addPreviousCell(Cell c)");
		entry.addPreviousCell(cells[nCells - 1]);
		logger.logReturn(this, exit, "addPreviousCell(Cell c)", null);

		if (nCells > 1) {
			// első cella szomszédainak bekötése
			logger.logCall(this, cells[0], "setNeighbourCells(Cell prev, Cell next)");
			((RoadCell)cells[0]).setNeighbourCells(entry, cells[1]);
			logger.logReturn(this, cells[0], "setNeighbourCells(Cell prev, Cell next)", null);
		}

		// köztes cellák egymáshoz kötése
		for (int i = 1; i < nCells - 1; i++) {
			logger.logCall(this, cells[i], "setNeighbourCells(Cell prev, Cell next)");
			((RoadCell)cells[i]).setNeighbourCells(cells[i-1], cells[i+1]);
			logger.logReturn(this, cells[i], "setNeighbourCells(Cell prev, Cell next)", null);
		}
	}

	/**
	 * Elhelyezi egy járművet az út egyik celláján.
	 *
	 * @param v
	 * 		Az elhelyezendő jármű
	 */
	public void placeCar(Vehicle v) {
		
		logger.logMessage("Please choose a cell of " + getName() + " to place " + v.getName() + " on (0-" + Integer.toString(cells.length-1) + "):");
		int cell = input.readInt(0,cells.length-1);
		placeCar(v,cell);
	}

	/**
	 * Elhelyezi egy járművet az út megadott celláján.
	 *
	 * @param v
	 * 		Az elhelyezendő jármű
	 * @param cell
	 * 		A cella sorszáma
	 */
	public void placeCar(Vehicle v, int cell) {
				
		logger.logCall(this, cells[cell], "setVehicle(Vehicle v)");
		cells[cell].setVehicle(v);
		logger.logReturn(this, cells[cell], "setVehicle(Vehicle v)", null);
		logger.logCall(this, v, "setCell(Cell c)");
		v.setCell(cells[cell]);
		logger.logReturn(this, v, "setCell(Cell c)", null);
	}

	/**
	 * 
	 * @return
	 */
	public void tick() {
		for (Cell c : cells) {
			logger.logCall(this, c, "getSign()");			
			ISign s = ((RoadCell)c).getSign();
			logger.logReturn(this, c, "getSign()",s);
		}
	}

}
