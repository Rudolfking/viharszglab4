package proto;

import java.io.IOException;

public class RoadCell extends Cell {
    private Cell nextCell;
    private Cell previousCell;
    private Road road;
    private ISign sign;

    public RoadCell(String name, Road road, boolean createSign, Logger logger, CustomReader input) {
        super(name, logger, input);
        this.road = road;
        if (createSign == true) {
            logger.logMessage("What kind of traffic sign do you want to place on " + name + "?");
            logger.logMessage("1- Stop sign");
            logger.logMessage("2- Traffic light");
            logger.logMessage("3- nothing");            
                String str = input.readLine();
                if (str.compareTo("1") == 0) {
                    logger.logCreate(this, "StopSign");
                    sign = new StopSign(name + "_stopsign", logger, input);
                    logger.logCreated(this, sign);
                } else if (str.compareTo("2") == 0) {
                    logger.logCreate(this, "TrafficLight");
                    sign = new TrafficLight(name + "_trafficlight", logger, input);
                    logger.logCreated(this, sign);
                }            
        }
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
