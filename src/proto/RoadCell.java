package proto;

import java.io.IOException;
/**
 * Az útcella egy olyan speciális cella, mely egy konkrét 
 * cellát (egy úthoz tartozó egy-belépésû, egy-kilépésû)
 *  reprezentál. Ennek megfelelõen van felépítve (attribútumok, stb.).
 * @author Balázs
 *
 */
public class RoadCell extends Cell {
	/**
	 * A következõ cellát tároló privát attribútum, elõrefelé haladás esetén érvényes ez.
	 */
    private Cell nextCell;
    /**
     * Elõzõ cellát ez a publikus láthatóságú referencia tárolja, a visszafelé haladás (bankrabló pl.) esetén ez a „következõ cella„
     */
    private Cell previousCell;
    /**
     * A tartozó út
     */
    private Road road;
    /**
     * Úton a tábla!
     */
    private ISign sign;

    public RoadCell(String name, Road road, ISign sign, Logger logger, CustomReader input) {
        super(name, logger, input);
        this.road = road;
        this.sign = sign;        
    }

    /**
     * @param prev Elõzõ cella beállítandó
     * @param next Következõ cella beállítandó
     * @return A szomszédos cellákat beállító publikus függvény (hogy a generálásnál elérhessük), egy elõzõ, és egy következõ cellát fixál.
     */
    public void setNeighbourCells(Cell prev, Cell next) {
        previousCell = prev;
        nextCell = next;
    }

	/**
     * @return Visszaadja a következõ cellákat
     */
    public Cell[] getNextCells() {
		Cell[] res = new Cell[1];
		res[0] = nextCell;
		return res;
	}

    /**
     * @return Visszaadja az elõzõ cellákat
     */
    public Cell[] getPreviousCells() {
		Cell[] res = new Cell[1];
		res[0] = previousCell;
		return res;
	}

    /**
     * @return Visszaadja a táblát az úton
     */
    public ISign getSign() {
        return sign;
    }
    /**
     * 
     * @return Visszaadja a tartalmazó utat.
     */
	public Road getRoad() {
		return road;
	}
}
