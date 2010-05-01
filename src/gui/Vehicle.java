package gui;

import java.io.IOException;
import java.util.*;

/**
 * Egy olyan absztrakt oszt�ly, megy el �ltal�nos, k�zleked�sre 
 * k�pes j�rm�vet val�s�t meg az utakon. D�nt arr�l, hogy merre 
 * haladjon, betartja a szab�lyokat (t�bl�k, l�mp�k �s utca-ir�ny�t�sok). 
 * Ker�li az �tk�z�st, konkr�tan nem �tk�zik (csak ha belemegy a rabl�).
 */
public abstract class Vehicle extends NamedObject {
	/**
	 * A k�vetkez� l�p�sig h�tral�v� id� tick-ekben (maximum�rt�ke nyilv�n az inverseSpeed �rt�ke)
	 */
    protected int ticksLeft;
    /**
     * A cella referenci�ja, amin tart�zkodik
     */
    protected Cell cell;
    /**
     * (El�re) Kiv�lasztott k�vetkez� cella
     */
    protected int preferredCell;
    /**
     * A sebess�g�t megad� integer priv�t v�ltoz� (inverz, mert tick/l�p�s)
     */
    protected int inverseSpeed;
    /**
     * A j�t�k
     */
    protected Game game;      

	/**
	 * Konstruktor az alaptulajdonságok beállításával.
	 */
    public Vehicle(String name, Game game, Cell cell, int ispeed, Logger logger, CustomReader input) {
        super(name, logger, input);        
        this.game = game;
		setCell(cell);				
        inverseSpeed=ispeed;
        ticksLeft=inverseSpeed;
        preferredCell = -1;        
    }  
    
    /**
     * A következő kereszteződénél kiválasztandó cella sorszámának beállítása.
     * @param c a kivánt cella sorszáma     
     */
    public void setPreferredCell(int i) {		
		preferredCell = i;
    }    

    /**
	 * Választ egyet az átadott cellák közül.
     * @param c a cellák listáa, amik közül választani kell
     * @return a kiválasztott cella
	 *
     */
    protected Cell chooseFrom(Cell[] cells) {
			
		if(cell.getRoad()==null) {
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
        logger.logEvent(logger.className(this)+" $name dies",param);
        // cella referenciájának törlése	         
        cell.leave();        
        cell = null;               
    }

    /**
     * Minden óraléptetéskor végrehajtott függvény: visszaszámlál két lépés
	 * között, és megpróbál lépni, ha lejárt a számláló.
     */
    public void tick() {        
				
		// ellenőrizzük, hogy eltelt-e a már a sebességnek megfelelő idő        				
		// ha eltelt, megkísérelünk lépni
		if (((ticksLeft>=0) && (game != null) && (!game.speed)) || (ticksLeft==0)) {
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
    	else
    	if (ticksLeft>0)
			ticksLeft--;	
		else
			ticksLeft = inverseSpeed;
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
		/**
		 * Ha l�p, itt kezeli le a cell�ra l�p�s 
		 * k�vetkezm�nyeit a j�rm�. Maga h�vja 
		 * meg, �gy l�that�s�g�ra az OO elvek
		 *  betart�s�t szem el�tt tartva priv�t
		 * @param nextCell Az erre l�p� j�rm� fogad�s�nak helye
		 * @param v A j�rm�, amit fogad
		 */
	public abstract void accept(Cell nextCell, Vehicle v);
	/**
	 * 
	 * @param c Civilt kezel
	 */
	public abstract void interact(CivilCar c);
	/**
	 * 
	 * @param p rend�rt
	 */
	public abstract void interact(Policeman p);
	/**
	 * 
	 * @param r Bankrabl�t
	 */
	public abstract void interact(Robber r);
	/**
	 * 
	 * @param b Nyuszik�t
	 */
	public abstract void interact(Bunny b);
		
}
