package skeleton;

import java.io.IOException;

public abstract class Vehicle extends NamedObject {
    private int ticksLeft;
    protected Cell cell;
    private int inverseSpeed;
    private Game game;
    String name;

    public Vehicle(String name, Cell cell, int ispeed, Logger logger, CustomReader input) {
        super(name, logger, input);
        logger.logCall(this, this, "setCell(Cell c)");
		setCell(cell);
		logger.logReturn(this, this, "setCell(Cell c)", null);
        inverseSpeed=ispeed;
    }

    /**
     * @param v
     * @return
     */
    public void accept(Vehicle v) {

		
    }

    /**
     * @param c
     * @return
     */
    protected Cell chooseFrom(Cell[] c) {
        
		logger.logMessage("Which next cell should " + name + " choose (0" + Integer.toString(c.length-1) + ")?");
		int choice = input.readInt(0,c.length-1);
		return c[choice];

    }

    /**
     * @return
     */
    public Cell getCell() {
        return cell;
    }

    /**
     * @param c
     * @return
     */
    public void setCell(Cell c) {
        this.cell=c;
    }

    /**
     * Az auto letorli magat a cellajarol, mielott felrobban
     */
    public void die() {
        logger.logCall(this,this,"getCell()");
        Cell cell0=getCell();                          //cella lekerdezese
        logger.logReturn(this,this,"getCell()",cell0);
        logger.logCall(this,cell0,"leave()");
        cell0.leave();                                //cella elhagyasa
        logger.logReturn(this,cell0,"leave()",null);
    }

    /**
     * @return
     */
    public void tick() {        

        logger.logMessage("Speed countdown finished? (y/n)");
		String res = input.readLine();
		boolean  ready = (res.compareTo("y")==0);
		if (ready) {
			logger.logCall(this, this, "step()");
			step();
			logger.logReturn(this, this, "step()", null);		
		}		
    }

	/**
	 * Lépés a következő cellára. Akkor hívjuk meg, ha lejárt a számláló.
	 */
	public abstract void step();
}
