package proto;

import java.io.IOException;
import java.util.*;

/**
 * Általános absztrakt jármű osztály: közös interfészt biztosít a különböző járműveknek, és a közös
 * tulajdonságaikat is összefogja.
 */
public abstract class Vehicle extends NamedObject {
    protected int ticksLeft;
    protected Cell cell;
    //protected Cell preferredCell;	  
    protected int preferredCell;
    protected int inverseSpeed;
    protected Game game;      

	/**
	 * Konstruktor az alaptulajdonságok beállításával.
	 */
    public Vehicle(String name, Game game, Cell cell, int ispeed, Logger logger, CustomReader input) {
        super(name, logger, input);        
        this.game = game;
		setCell(cell);				
        inverseSpeed=ispeed;
        preferredCell = -1;        
    }  
    
    /**
     * A következő kereszteződénél kiválasztandó cella sorszámának beállítása.
     * @param c a kivánt cella sorszáma     
     */
    public void setPreferredCell(int i) {
		/*/if (cell.getRoad()!=null) {
			Cell[] cells = cell.getRoad().getExitIntersection().getNextCells();
			preferredCell = cells[i];
		} else {
			Cell[] cells = cell.getNextCells();
			preferredCell = cells[i];
		}*/
		preferredCell = i;
    }    

    /**
	 * Választ egyet az átadott cellák közül.
     * @param c a cellák listáa, amik közül választani kell
     * @return a kiválasztott cella
	 *
     */
    protected Cell chooseFrom(Cell[] cells) {
        		
		/*// választás a preferált cella ismeretében
		boolean prefCellIsReachable = false;
		// preferált cella elérhetőségének ellenőrzése (úton nem)
		if (cell.getRoad() == null) {
			for (Cell c : cells)
				if(c == preferredCell) prefCellIsReachable = true;			
		}
		// ha elérhető, akkor azt választjuk
		if(prefCellIsReachable)
			return preferredCell;
		else {
			// ha nem, véletlenszerűen választunk			
			Random r = new Random();			
			return cells[r.nextInt(cells.length)];
		}*/
			
		if(cells.length>1)	{
			if (preferredCell<0) {
				Random r = new Random();
				preferredCell = r.nextInt(cells.length);
			}
			return cells[preferredCell];
		}
		else
			return cells[0];

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
	 * inverseSpeed attribútum beállító függvénye
	 *
     * @param c a cell attribútum (a cella, ahol a jármű tartózkodik)
     */
    public void setInverseSpeed(int i) {
        this.inverseSpeed=i;
        this.ticksLeft=i;
    }

    /**
     * Az autó törlésekor a cella vonatkozó referenciájának megszüntetése.
     */
    public void die() {
		// esemény naplózása
		INamedObject[] param = {this};
        logger.logEvent(this.getClass().getName()+" $name dies",param);
        // cella referenciájának törlése	
        Cell c = getCell();	        
        c.leave();        
    }

    /**
     * Minden óraléptetéskor végrehajtott függvény: visszaszámlál két lépés
	 * között, és megpróbál lépni, ha lejárt a számláló.
     */
    public void tick() {        

		if (ticksLeft>0)
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
		
	public abstract void accept(Cell nextCell, Vehicle v);
	
	public abstract void interact(CivilCar c);
	
	public abstract void interact(Policeman p);
	
	public abstract void interact(Robber r);
	
	public abstract void interact(Bunny b);
		
}
