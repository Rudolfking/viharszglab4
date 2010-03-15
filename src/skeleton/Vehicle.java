package skeleton;

public abstract class Vehicle extends NamedObject {
    private int ticksLeft;
    private Cell cell;
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
        throw new UnsupportedOperationException();
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
     * @return
     */
    public void die() {
        logger.logCall(this,this,"getCell()");
        Cell cell0=getCell();
        logger.logReturn(this,this,"getCell()",cell0);
        logger.logCall(this,cell0,"leave()");
        cell0.leave();
        logger.logReturn(this,cell0,"leave()",null);
    }

    /**
     * @return
     */
    public void tick() {
        throw new UnsupportedOperationException();
    }
}
