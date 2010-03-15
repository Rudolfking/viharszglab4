package skeleton;

import java.io.BufferedReader;
import java.io.IOException;

public class CustomReader {
    private BufferedReader input;
    private Logger logger;
    private boolean echo;

    public CustomReader(Logger logger) {
        echo = false;
        this.logger = logger;
    }

    public void setInput(BufferedReader input) {
        this.input = input;
    }

    public void setEcho(boolean echo) {
        this.echo = echo;
    }

    public String readLine() throws IOException {
        String result;
        do {
            result = input.readLine();
        } while (result.charAt(0) == '#');
        if (echo) logger.logMessage(result);
        return result;
    }
}
