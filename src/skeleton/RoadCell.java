//import InitialClassDiagram.*;

public class RoadCell extends Cell {

	private Cell nextCell;
	private Cell previousCell;
	private Road road;
	private ISign sign;

	public RoadCell(String name, boolean createSign, ILogger logger) {
		super(name);
		if (createSign == true) {
			int s = (int) Math.round(Math.random() * 2) + 1;
			switch (s) {
			case 0:
				break;
			case 1:
				logger.logCreate(this, "StopSign");
				sign = new StopSign(name + "_stopsign");
				logger.logCreated(this, sign);
				break;
			case 2:
				logger.logCreate(this, "TrafficLight");
				sign = new StopSign(name + "_trafficlight");
				logger.logCreated(this, sign);
				break;
			}
		}

	}

	/**
	 * 
	 * @param prev
	 * @param next
	 * @return
	 */
	public void setNeighbourCells(Cell prev, Cell next) {
		throw new UnsupportedOperationException();
	}

}