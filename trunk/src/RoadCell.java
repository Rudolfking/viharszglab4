import InitialClassDiagram.*;

public class RoadCell extends Cell {

	private Cell nextCell;
	private Cell previousCell;
	private Road road;
	private ISign sign;

	/**
	 * 
	 * @param prev
	 * @param next
	 * @return 
	 */
	public void setNeighbourCells(Cell prev, Cell next) {
		throw new UnsupportedOperationException();
	}


	public interface ISign {

		/**
		 * 
		 * @return 
		 */
		boolean isBlocking();

		/**
		 * 
		 * @return 
		 */
		void vehicleEntered();

		/**
		 * 
		 * @return 
		 */
		void tick();

	}

}