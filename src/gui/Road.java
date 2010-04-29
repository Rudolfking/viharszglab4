//package InitialClassDiagram;
package gui;
/**
 * Egy utat valÛsÌt meg, cell·kat tartalmaz, melybıl kÈt kiemelt
 *  szerep˚ cella (mely nem mellÈkesen keresztezıdÈs) van: Egy belÈpı
 *   Ès egy kilÈpı (az ˙t eleje Ès vÈge csatlakozik ide).
 * @author Bal·zs
 *
 */
public class Road extends NamedObject {
	/**
	 * Az ˙t saj·t cell·it tartalmazÛ lista
	 */
	private Cell[] cells;
	
	/**
	 * A belÈpı keresztezıdÈs. Priv·t
	 */
	private Intersection entry;
	/**
	 * A kilÈpı keresztezıdÈs. L·thatÛs·g·t tekintve priv·t
	 */
	private Intersection exit;

	public Road(String name, Intersection entry, Intersection exit, int nCells,
			ISign sign, Logger logger, CustomReader input) {
		super(name,logger,input);
		this.entry = entry;
		this.exit = exit;
		
		generateCells(entry, exit, nCells, sign);		
	}

	/**
	 * Visszaadja az √∫t cell√°inak list√°j√°t - csak a szkeletonhoz kell
	 * @return az √∫t cell√°inak list√°ja
	 */
	public Cell[] getCells() {
		return cells;
	}

	/**
	 * 
	 * @return Felelıs a kilÈpı keresztezıdÈs visszaad·s·Èrt, ennek ÈrdekÈben publikus tagf¸ggvÈny.
	 */
	public Intersection getExitIntersection() {
		return exit;
	}

	/**
	 * 
	 * @return Ld. fentebb, csak belÈpı. TermÈszetesen publikus tagf¸ggvÈny.
	 */
	public Intersection getEntryIntersection() {
		return entry;
	}

	/**
	 * 
	 * @return Legener·lja a saj·t cell·it az ˙t, ez a f¸ggvÈny Èrte a felelıs. Hogy meg tudjuk hÌvni kÌv¸lrıl, l·thatÛs·ga publikus
	 */
	public void generateCells(Intersection entry, Intersection exit, int nCells, ISign sign) {

		cells = new RoadCell[nCells];


		// els≈ë cella l√©trehoz√°sa		
		cells[0] = new RoadCell(getName() + "_cell0", this, (nCells==1)?(sign):(null), logger, input);		
		// bej√°rat-keresztez≈ëd√©shez els≈ë cella bek√∂t√©se		
		entry.addNextCell(cells[0]);		
		
		// m√°s a helyzet, ha t√∂bb cell√°nk van
		if (nCells > 1) {
			// k√∂ztes cell√°k l√©trehoz√°sa
			for (int i = 1; i < nCells - 1; i++) {				
				cells[i] = new RoadCell(getName() + "_cell" + Integer.toString(i), this, null, logger, input);				
			}

			// utols√≥ cella l√©trehoz√°sa			
			cells[nCells - 1] = new RoadCell(getName() + "_cell" + Integer.toString(nCells - 1), this, sign, logger, input);			

			// utols√≥ cella szomsz√©dainak bek√∂t√©se			
			((RoadCell)cells[nCells-1]).setNeighbourCells(cells[nCells-2], exit);			
		} else {
			// egyetlen cella szomsz√©dainak bek√∂t√©se			
			((RoadCell)cells[0]).setNeighbourCells(entry, exit);			
		}

		// kij√°rat-keresztez≈ëd√©shez utols√≥ cella bek√∂t√©se		
		exit.addPreviousCell(cells[nCells - 1]);		

		if (nCells > 1) {
			// els≈ë cella szomsz√©dainak bek√∂t√©se			
			((RoadCell)cells[0]).setNeighbourCells(entry, cells[1]);			
		}

		// k√∂ztes cell√°k egym√°shoz k√∂t√©se
		for (int i = 1; i < nCells - 1; i++) {			
			((RoadCell)cells[i]).setNeighbourCells(cells[i-1], cells[i+1]);			
		}
	}

	/**
	 * Elhelyezi egy j√°rm≈±vet az √∫t egyik cell√°j√°n.
	 *
	 * @param v
	 * 		Az elhelyezend≈ë j√°rm≈±
	 */
	public void placeCar(Vehicle v) {
		
		logger.logMessage("Please choose a cell of " + getName() + " to place " + v.getName() + " on (0-" + Integer.toString(cells.length-1) + "):");
		int cell = input.readInt(0,cells.length-1);
		placeCar(v,cell);
	}

	/**
	 * Elhelyezi egy j√°rm≈±vet az √∫t megadott cell√°j√°n.
	 *
	 * @param v
	 * 		Az elhelyezend≈ë j√°rm≈±
	 * @param cell
	 * 		A cella sorsz√°ma
	 */
	public void placeCar(Vehicle v, int cell) {
						
		cells[cell].setVehicle(v);		
		v.setCell(cells[cell]);		
	}

	/**
	 * 
	 * @return LÈptet!
	 */
	public void tick() {
		for (Cell c : cells) {			
			ISign s = ((RoadCell)c).getSign();
			if (s!=null)
				s.tick();			
		}
	}

}
