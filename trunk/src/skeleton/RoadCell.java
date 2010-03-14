package skeleton;
import java.io.*;

public class RoadCell extends Cell {

	private Cell nextCell;
	private Cell previousCell;
	private Road road;
	private ISign sign;

	public RoadCell(String name, boolean createSign, Logger logger,
			CustomReader input) {
		super(name,logger,input);
		if (createSign == true) {
			logger
					.logMessage("What kind of traffic sign do you want to place on "
							+ name + "?");
			logger.logMessage("1- Stop sign");
			logger.logMessage("2- Traffic light");
			logger.logMessage("3- nothing");
			try {
				String str = input.readLine();
				if (str.compareTo("1") == 0) {
					logger.logCreate(this, "StopSign");
					sign = new StopSign(name + "_stopsign",logger,input);
					logger.logCreated(this, sign);
				} else if (str.compareTo("2") == 0) {
					logger.logCreate(this, "TrafficLight");
					sign = new TrafficLight(name + "_trafficlight",logger,input);
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
		
	}
	
	/**
	 * 
	 * @return 
	 */
	public ISign getSign() {
		return sign;
	}

}
