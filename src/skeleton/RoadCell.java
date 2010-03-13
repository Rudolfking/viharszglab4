import java.io.*;

public class RoadCell extends Cell {

	private Cell nextCell;
	private Cell previousCell;
	private Road road;
	private ISign sign;
	private CustomReader input;

	public RoadCell(String name, boolean createSign, Logger logger,
			CustomReader input) {
		super(name);
		this.input = input;
		if (createSign == true) {
			logger
					.logMessage("What kind of traffic sign do you want to place on "
							+ name + "?");
			logger.logMessage("1- Stop sign");
			logger.logMessage("2- Traffic light");
			logger.logMessage("3- nothing");
			try {
				String str = input.readLine();
				if (str == "1") {
					logger.logCreate(this, "StopSign");
					sign = new StopSign(name + "_stopsign");
					logger.logCreated(this, sign);
				} else if (str == "2") {
					logger.logCreate(this, "TrafficLight");
					sign = new TrafficLight(name + "_trafficlight");
					logger.logCreated(this, sign);
				}
			} catch (IOException e) {
				e.printStackTrace();
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