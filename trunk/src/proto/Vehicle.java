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
    public Vehicle(String name, Game game, Cell cell, int ispeed, Logger logger, CustomReader input) {
        super(name, logger, input);        
        this.game = game;
		setCell(cell);		
		
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

		ticksLeft--;		
		// ellenőrizzük, hogy eltelt-e a már a sebességnek megfelelő idő        				
		// ha eltelt, megkísérelünk lépni
		if (((game != null) && (!game.speed)) || (ticksLeft==0)) {
			ticksLeft = inverseSpeed;
			// aktuális cellán lévő közlekedési jelzés lekérdezése					
			ISign s = cell.getSign();			
			boolean blocking = false;
			if (s != null) {
				// ha van jelzés, blokkolás lekérdezése	
				logger.log("Sign" + s.getName() + "found");			
				blocking = s.isBlocking();				
			}
			if (!blocking) {
				// ha nincs blokkolás, léphetünk a következő cellára				
				step();				
			}
			else {
				INamedObject[] param = {this,s};
				logger.logEvent(logger.className(this) + " $name blocked by $signName",param);
			}
    	}
	}	

	/**
	 * Lépés a kövektező cellára.
	 */
	public void step() {
		
		// a következő cellák listájának lekérdezése		
		Cell[] nextCells = cell.getNextCells();		
		// a listából egy cella kiválasztása		
		Cell nextCell = chooseFrom(nextCells);		
		// annak a vizsgálata, hogy a kiszemelt cellán tartózkodik-e autó		
		Vehicle v = nextCell.getVehicle();		
		// a kapott eredmény elfogadása		
		accept(nextCell,v);		
	}
		
}
