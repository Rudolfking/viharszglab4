

public class LoggerTest {
	
	public static void main(String args[]) {
		
		//ILogger logger = new ConsoleLogger();
		
		//INamedObject robber = new Robber();
		//INamedObject rob2 = new Robber();
		//robber.setName("robber");
		
		//logger.setLevel(3);
		//logger.logMessage("teszt");
		//logger.logCall(robber,rob2,"test-function(param-list)");
		
		Game game = new Game();
		game.setName("game");
		game.setLogger(new ConsoleLogger());
		game.generateLevel();
		
		game.tick();
	}
}