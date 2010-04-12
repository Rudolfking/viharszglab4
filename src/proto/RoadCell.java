package proto;

import java.io.IOException;

public class RoadCell extends Cell {
    private Cell nextCell;
    private Cell previousCell;
    private Road road;
    private ISign sign;

    public RoadCell(String name, Road road, ISign sign, Logger logger, CustomReader input) {
        super(name, logger, input);
        this.road = road;
        this.sign = sign;        
    }

    /**
     * @param prev
     * @param next
     * @return
     */
    public void setNeighbourCells(Cell prev, Cell next) {
        previousCell = prev;
        nextCell = next;
    }

	/**
     * @return
     */
    public Cell[] getNextCells() {
		Cell[] res = new Cell[1];
		res[0] = nextCell;
		return res;
	}

    /**
     * @return
     */
    public Cell[] getPreviousCells() {
		Cell[] res = new Cell[1];
		res[0] = previousCell;
		return res;
	}

    /**
     * @return
     */
    public ISign getSign() {
        return sign;
    }

	public Road getRoad() {
		return road;
	}
}
