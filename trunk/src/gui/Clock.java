package gui;
/**
 * Az órát reprezentáló osztály, mely idõnként kiadja a játék vezérléséért felelõ tick jelet. Ezzel egy kör lezárul és újabb kezdõdik a Game életében.
 * @author Balázs
 *
 */
public class Clock extends NamedObject {
    public Clock(String name, Logger logger, CustomReader input) {
        super(name, logger, input);
    }

    /**
     * @return tick kiadva
     */
    public boolean tick() {
        throw new UnsupportedOperationException();
    }
}
