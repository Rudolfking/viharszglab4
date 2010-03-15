package skeleton;

public class Clock extends NamedObject {
    public Clock(String name, Logger logger, CustomReader input) {
        super(name, logger, input);
    }

    /**
     * @return
     */
    public boolean tick() {
        throw new UnsupportedOperationException();
    }
}