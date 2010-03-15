package skeleton;

public class Policeman extends Vehicle {
    private Robber wanted;

    public Policeman(String name, Cell cell, int ispeed, Logger logger, CustomReader input) {
        super(name, cell, ispeed, logger, input);
    }

    /**
     * @param v
     * @return
     */
    public boolean onTheSameRoad(Vehicle v) {
        throw new UnsupportedOperationException();
    }
}
