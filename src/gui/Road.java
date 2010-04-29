//package InitialClassDiagram;
package gui;
/**
 * Egy utat val�s�t meg, cell�kat tartalmaz, melyb�l k�t kiemelt
 *  szerep� cella (mely nem mell�kesen keresztez�d�s) van: Egy bel�p�
 *   �s egy kil�p� (az �t eleje �s v�ge csatlakozik ide).
 * @author Bal�zs
 *
 */
public class Road extends NamedObject {
	/**
	 * Az �t saj�t cell�it tartalmaz� lista
	 */
	private Cell[] cells;
	
	/**
	 * A bel�p� keresztez�d�s. Priv�t
	 */
	private Intersection entry;
	/**
	 * A kil�p� keresztez�d�s. L�that�s�g�t tekintve priv�t
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
	 * Visszaadja az út celláinak listáját - csak a szkeletonhoz kell
	 * @return az út celláinak listája
	 */
	public Cell[] getCells() {
		return cells;
	}

	/**
	 * 
	 * @return Felel�s a kil�p� keresztez�d�s visszaad�s��rt, ennek �rdek�ben publikus tagf�ggv�ny.
	 */
	public Intersection getExitIntersection() {
		return exit;
	}

	/**
	 * 
	 * @return Ld. fentebb, csak bel�p�. Term�szetesen publikus tagf�ggv�ny.
	 */
	public Intersection getEntryIntersection() {
		return entry;
	}

	/**
	 * 
	 * @return Legener�lja a saj�t cell�it az �t, ez a f�ggv�ny �rte a felel�s. Hogy meg tudjuk h�vni k�v�lr�l, l�that�s�ga publikus
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
				cells[i] = new RoadCell(getName() + "_cell" + Integer.toString(i), this, null, logger, input);				
			}

			// utolsó cella létrehozása			
			cells[nCells - 1] = new RoadCell(getName() + "_cell" + Integer.toString(nCells - 1), this, sign, logger, input);			

			// utolsó cella szomszédainak bekötése			
			((RoadCell)cells[nCells-1]).setNeighbourCells(cells[nCells-2], exit);			
		} else {
			// egyetlen cella szomszédainak bekötése			
			((RoadCell)cells[0]).setNeighbourCells(entry, exit);			
		}

		// kijárat-kereszteződéshez utolsó cella bekötése		
		exit.addPreviousCell(cells[nCells - 1]);		

		if (nCells > 1) {
			// első cella szomszédainak bekötése			
			((RoadCell)cells[0]).setNeighbourCells(entry, cells[1]);			
		}

		// köztes cellák egymáshoz kötése
		for (int i = 1; i < nCells - 1; i++) {			
			((RoadCell)cells[i]).setNeighbourCells(cells[i-1], cells[i+1]);			
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
						
		cells[cell].setVehicle(v);		
		v.setCell(cells[cell]);		
	}

	/**
	 * 
	 * @return L�ptet!
	 */
	public void tick() {
		for (Cell c : cells) {			
			ISign s = ((RoadCell)c).getSign();
			if (s!=null)
				s.tick();			
		}
	}

}
