
public class LoggerTest {

	public static void main(String args[]) {

		Game game = new Game("game", new ConsoleLogger());

		game.generateLevel(4, 6);

		// game.tick();
	}
}