package proto;

public class Proto {
	
	public static void main(String[] args) {
		// ezt a loggert használjuk az egész program során
        Logger logger = new ConsoleLogger();
        // saját bemeneti olvasó osztály: konzolról és fájlból is tud sort olvasni, kommenteket (#-val kezdődő sor) nem veszi figyelembe
        CustomReader input = new CustomReader(logger);
        
        Game game = new Game("game",logger,input);
        
        game.generateLevel("E[0]F{C[0]}FF{S[0]}X[0]");
        game.writeLevel();
	}
}
