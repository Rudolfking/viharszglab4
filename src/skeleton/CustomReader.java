package skeleton;

import java.io.BufferedReader;
import java.io.IOException;

/**
 * BufferedReaderrel soronként beolvasó osztály visszhang (echo) és komment támogatással.
 */
public class CustomReader {
    // ennek a segítségével olvasunk
    private BufferedReader input;
    // ezzel naplózzuk a visszhangot
    private Logger logger;
    // visszhang állapota
    private boolean echo;

    /**
     * Konstruktor a naplózó objektum beállításával.
     *
     * @param logger naplózó objektum
     */
    public CustomReader(Logger logger) {
        echo = false;
        this.logger = logger;
    }

    /**
     * input adattag beállítása.
     *
     * @param input input új értéke
     */
    public void setInput(BufferedReader input) {
        this.input = input;
    }

    /**
     * echo adattag beállítása.
     *
     * @param echo echo új értéke
     */
    public void setEcho(boolean echo) {
        this.echo = echo;
    }

    /**
     * Egy sor beolvasása stringbe a megadott inputból. A #-tel kezdődő sorokat átugorja.
     *
     * @return a beolvasott sor
     */
    public String readLine() throws IOException {
        String result;
        do {
            result = input.readLine();
        } while (result.charAt(0) == '#');
        if (echo) logger.logMessage(result);
        return result;
    }

    /**
     * Egy egész szám beolvasása a megadott inputból. A #-tel kezdődő sorokat átugorja.
     *
     * @return a beolvasott szám
     */
    public int readInt() {
        String str = "";
        int result = 0;
        try {
            do {
                str = input.readLine();
            } while (str.charAt(0) == '#');
            result = Integer.valueOf(str);
        } catch (Exception e) {
            logger.logMessage("Error while reading integer input!");
            e.printStackTrace();
        }
        if (echo) logger.logMessage(str);
        return result;
    }

    /**
     * Egy korlátos egész szám beolvasása a megadott inputból. A #-tel kezdődő sorokat átugorja.
     *
     * @param min a legkisebb elfogadott érték - ez alatti szám esetén újat kér
     * @param max a legnagyobb elfogadott érték - e fölötti szám esetén újat kér
     * @return a beolvasott szám
     */
    public int readInt(int min, int max) {
        int result = 0;
        do {
            result = readInt();
            if ((result < min) || (result > max)) {
                logger.logMessage("The given value is out of expected range (" + Integer.toString(min) + "-" + Integer.toString(max) + ")!");
                logger.logMessage("Please enter a valid value:");
			}
		} while ((result<min) || (result>max));		
		return result;
	}

}
