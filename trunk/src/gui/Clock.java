package gui;
/**
 * Az �r�t reprezent�l� oszt�ly, mely id�nk�nt kiadja a j�t�k vez�rl�s��rt felel� tick jelet. Ezzel egy k�r lez�rul �s �jabb kezd�dik a Game �let�ben.
 * @author Bal�zs
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
