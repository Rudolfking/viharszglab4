package proto;

import java.io.IOException;

/**
 * Általános absztrakt jármű osztály: közös interfészt biztosít a különböző járműveknek, és a közös
 * tulajdonságaikat is összefogja.
 */
public abstract class Vehicle extends NamedObject {
    private int ticksLeft;
    protected Cell cell;
    private int inverseSpeed;
    private Game game;    

	/**
	 * Konstruktor az alaptulajdonságok beállításával.
	 */
    public Vehicle(String name, Cell cell, int ispeed, Logger logger, CustomReader input) {
        super(name, logger, input);
        logger.logCall(this, this, "setCell(Cell c)");
		setCell(cell);
		logger.logReturn(this, this, "setCell(Cell c)", null);
		
        inverseSpeed=ispeed;
    }

    /**
	 * Következő cellára lépéskor ez a függvény "reagál" a kiszemelt cella tartalmára.
     *
     * @param nextCell a cella, ahova lépni szeretnénk
	 * @param v a jármű, amit a cellától lekérdeztünk
     */
    public abstract void accept(Cell nextCell, Vehicle v);

    /**
	 * Választ egyet az átadott cellák közül.
     * @param c a cellák listáa, amik közül választani kell
     * @return a kiválasztott cella
	 *
     */
    protected Cell chooseFrom(Cell[] c) {
        
		// szkeleton: felhasználói bemenet alapján választunk
		if(c.length>1) {
			logger.logMessage("Which next cell should " + name + " choose?");
			// lehetőségek felsorolása
			for (int i = 0; i<c.length; i++)
				logger.logMessage(Integer.toString(i) + " - " + c[i].getName());
			// válasz beolvasása
			int choice = input.readInt(0,c.length-1);
			return c[choice];
		} else {
			return c[0];
		}

    }

    /**
	 * cell attribútum lekérdező függvénye
	 *
     * @return a cell attribútum (a cella, ahol a jármű tartózkodik)
     */
    public Cell getCell() {
        return cell;
    }

    /**
	 * cell attribútum beállító függvénye
	 *
     * @param c a cell attribútum (a cella, ahol a jármű tartózkodik)
     */
    public void setCell(Cell c) {
        this.cell=c;
    }

    /**
     * Az autó letörli magát a cellájáról, mielott felrobban
     */
    public void die() {
		//cella lekerdezese
        logger.logCall(this,this,"getCell()");
        Cell cell0=getCell();                          
        logger.logReturn(this,this,"getCell()",cell0);
		//cella elhagyasa
        logger.logCall(this,cell0,"leave()");
        cell0.leave();                                
        logger.logReturn(this,cell0,"leave()",null);
    }

    /**
     * Minden óraléptetéskor végrehajtott függvény: visszaszámlál két lépés
	 * között, és megpróbál lépni, ha lejárt a számláló.
     */
    public void tick() {        

		// ellenőrizzük, hogy eltelt-e a már a sebességnek megfelelő idő
        logger.logMessage("Speed countdown for " + name + " finished? (y/n)");
		String res = input.readLine();
		boolean  ready = (res.compareTo("y")==0);
		// ha eltelt, megkísérelünk lépni
		if (ready) {
			// aktuális cellán lévő közlekedési jelzés lekérdezése		
			logger.logCall(this, cell, "getSign()");
			ISign s = cell.getSign();
			logger.logReturn(this, cell, "getSign()", s);
			boolean blocking = false;
			if (s != null) {
				// ha van jelzés, blokkolás lekérdezése
				logger.logCall(this, s, "isBlocking(Vehicle v)");
				blocking = s.isBlocking();
				logger.logReturn(this, s, "isBlocking(Vehicle v)", new NamedObject((Boolean.valueOf(blocking)).toString(), logger, input));								
			}
			if (!blocking) {
				// ha nincs blokkolás, léphetünk a következő cellára
				logger.logCall(this, this, "step()");
				step();
				logger.logReturn(this, this, "step()", null);
			}		
    	}
	}	

	/**
	 * Lépés a kövektező cellára.
	 */
	public void step() {
		
		// a következő cellák listájának lekérdezése
		logger.logCall(this, cell, "getNextCells()");
		Cell[] nextCells = cell.getNextCells();
		for (Cell c : nextCells) {
			logger.logReturn(this, cell, "getNextCells()", c);
			logger.setLevel(logger.getLevel()+1);
		}
		// a listából egy cella kiválasztása
		logger.logCall(this, this, "chooseFrom(Cell[] cells)");
		Cell nextCell = chooseFrom(nextCells);
		logger.logReturn(this, this, "chooseFrom(Cell[] cells)", nextCell);
		// annak a vizsgálata, hogy a kiszemelt cellán tartózkodik-e autó
		logger.logCall(this, nextCell, "getVehicle()");
		Vehicle v = nextCell.getVehicle();
		logger.logReturn(this, nextCell, "getVehicle()", v);
		// a kapott eredmény elfogadása
		logger.logCall(this, this, "accept(Cell nextCell, Vehicle v)");
		accept(nextCell,v);
		logger.logReturn(this, this, "accept(Cell nextCell, Vehicle v)", null);
	}
		
}
