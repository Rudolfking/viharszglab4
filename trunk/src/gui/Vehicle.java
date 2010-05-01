package gui;

import java.io.IOException;
import java.util.*;

/**
 * Egy olyan absztrakt osztály, megy el általános, közlekedésre 
 * képes jármûvet valósít meg az utakon. Dönt arról, hogy merre 
 * haladjon, betartja a szabályokat (táblák, lámpák és utca-irányítások). 
 * Kerüli az ütközést, konkrétan nem ütközik (csak ha belemegy a rabló).
 */
public abstract class Vehicle extends NamedObject {
	/**
	 * A következõ lépésig hátralévõ idõ tick-ekben (maximumértéke nyilván az inverseSpeed értéke)
	 */
    protected int ticksLeft;
    /**
     * A cella referenciája, amin tartózkodik
     */
    protected Cell cell;
    /**
     * (Elõre) Kiválasztott következõ cella
     */
    protected int preferredCell;
    /**
     * A sebességét megadó integer privát változó (inverz, mert tick/lépés)
     */
    protected int inverseSpeed;
    /**
     * A játék
     */
    protected Game game;      

	/**
	 * Konstruktor az alaptulajdonsÃ¡gok beÃ¡llÃ­tÃ¡sÃ¡val.
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
     * A kÃ¶vetkezÅ‘ keresztezÅ‘dÃ©nÃ©l kivÃ¡lasztandÃ³ cella sorszÃ¡mÃ¡nak beÃ¡llÃ­tÃ¡sa.
     * @param c a kivÃ¡nt cella sorszÃ¡ma     
     */
    public void setPreferredCell(int i) {		
		preferredCell = i;
    }    

    /**
	 * VÃ¡laszt egyet az Ã¡tadott cellÃ¡k kÃ¶zÃ¼l.
     * @param c a cellÃ¡k listÃ¡a, amik kÃ¶zÃ¼l vÃ¡lasztani kell
     * @return a kivÃ¡lasztott cella
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
	 * cell attribÃºtum lekÃ©rdezÅ‘ fÃ¼ggvÃ©nye
	 *
     * @return a cell attribÃºtum (a cella, ahol a jÃ¡rmÅ± tartÃ³zkodik)
     */
    public Cell getCell() {
        return cell;
    }

    /**
	 * cell attribÃºtum beÃ¡llÃ­tÃ³ fÃ¼ggvÃ©nye
	 *
     * @param c a cell attribÃºtum (a cella, ahol a jÃ¡rmÅ± tartÃ³zkodik)
     */
    public void setCell(Cell c) {
        this.cell=c;
    }
    
    /**
	 * inverseSpeed attribÃºtum beÃ¡llÃ­tÃ³ fÃ¼ggvÃ©nye
	 *
     * @param c a cell attribÃºtum (a cella, ahol a jÃ¡rmÅ± tartÃ³zkodik)
     */
    public void setInverseSpeed(int i) {
        this.inverseSpeed=i;
        this.ticksLeft=i;
    }

    /**
     * Az autÃ³ tÃ¶rlÃ©sekor a cella vonatkozÃ³ referenciÃ¡jÃ¡nak megszÃ¼ntetÃ©se.
     */
    public void die() {
		// esemÃ©ny naplÃ³zÃ¡sa
		INamedObject[] param = {this};
        logger.logEvent(logger.className(this)+" $name dies",param);
        // cella referenciÃ¡jÃ¡nak tÃ¶rlÃ©se	         
        cell.leave();        
        cell = null;               
    }

    /**
     * Minden Ã³ralÃ©ptetÃ©skor vÃ©grehajtott fÃ¼ggvÃ©ny: visszaszÃ¡mlÃ¡l kÃ©t lÃ©pÃ©s
	 * kÃ¶zÃ¶tt, Ã©s megprÃ³bÃ¡l lÃ©pni, ha lejÃ¡rt a szÃ¡mlÃ¡lÃ³.
     */
    public void tick() {        
				
		// ellenÅ‘rizzÃ¼k, hogy eltelt-e a mÃ¡r a sebessÃ©gnek megfelelÅ‘ idÅ‘        				
		// ha eltelt, megkÃ­sÃ©relÃ¼nk lÃ©pni
		if (((ticksLeft>=0) && (game != null) && (!game.speed)) || (ticksLeft==0)) {
			ticksLeft = inverseSpeed;
			// aktuÃ¡lis cellÃ¡n lÃ©vÅ‘ kÃ¶zlekedÃ©si jelzÃ©s lekÃ©rdezÃ©se					
			ISign s = cell.getSign();			
			boolean blocking = false;
			if (s != null) {
				// ha van jelzÃ©s, blokkolÃ¡s lekÃ©rdezÃ©se					
				blocking = s.isBlocking();				
			}
			if (!blocking) {
				// ha nincs blokkolÃ¡s, lÃ©phetÃ¼nk a kÃ¶vetkezÅ‘ cellÃ¡ra				
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
	 * LÃ©pÃ©s a kÃ¶vektezÅ‘ cellÃ¡ra.
	 */
	public void step() {
		
		// a kÃ¶vetkezÅ‘ cellÃ¡k listÃ¡jÃ¡nak lekÃ©rdezÃ©se		
		Cell[] nextCells = cell.getNextCells();		
		// a listÃ¡bÃ³l egy cella kivÃ¡lasztÃ¡sa		
		Cell nextCell = chooseFrom(nextCells);		
		// annak a vizsgÃ¡lata, hogy a kiszemelt cellÃ¡n tartÃ³zkodik-e autÃ³		
		Vehicle v = nextCell.getVehicle();		
		// a kapott eredmÃ©ny elfogadÃ¡sa		
		accept(nextCell,v);		
	}
		/**
		 * Ha lép, itt kezeli le a cellára lépés 
		 * következményeit a jármû. Maga hívja 
		 * meg, így láthatóságára az OO elvek
		 *  betartását szem elõtt tartva privát
		 * @param nextCell Az erre lépõ jármû fogadásának helye
		 * @param v A jármû, amit fogad
		 */
	public abstract void accept(Cell nextCell, Vehicle v);
	/**
	 * 
	 * @param c Civilt kezel
	 */
	public abstract void interact(CivilCar c);
	/**
	 * 
	 * @param p rendõrt
	 */
	public abstract void interact(Policeman p);
	/**
	 * 
	 * @param r Bankrablót
	 */
	public abstract void interact(Robber r);
	/**
	 * 
	 * @param b Nyuszikát
	 */
	public abstract void interact(Bunny b);
		
}
