package proto;
/**
 * A bank reprezentálja azt az osztályt, ahonnan a rabló indul.
 * @author Balázs
 */
public class Bank extends Intersection {
    public Bank(String name, Logger logger, CustomReader input) {
        super(name, logger, input);
    }
}
