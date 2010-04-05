package proto;

public class ConsoleLogger extends Logger {
    public void log(String message) {
        for (int i = 0; i < level * spacePerLevel; i++) {
            System.out.print(" ");
        }
        System.out.println(message);
    }
}
