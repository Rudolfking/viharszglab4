package proto;

import java.io.*;

public class Proto {
	
	static class MismatchingParametersException extends Exception {
		public MismatchingParametersException() {
		}
	}
	
	static Game game;
	
	public static void main(String[] args) {
		// ezt a loggert használjuk az egész program során
        Logger logger = new ConsoleLogger();
        // saját bemeneti olvasó osztály: konzolról és fájlból is tud sort olvasni, kommenteket (#-val kezdődő sor) nem veszi figyelembe
        CustomReader input = new CustomReader(logger);
        input.setInput(new BufferedReader(new InputStreamReader(System.in)));
        
        logger.setSuperSilent(true);
        
        game = new Game("game",logger,input);
        
        try {
			while (true)
				processCommand(input.readLineUnsafe(),logger);
		} catch (MismatchingParametersException e) {
			logger.logMessage("x Error: MismatchingParameters");
		} catch (FileNotFoundException e) {
			logger.logMessage("x Error: FileNotFound");
		} catch (IOException e) {			
		}
        
        // map1
        //game.generateLevel("E[0]F{C[0]}FF{S[0]}X[0]");
        // map2
        //game.generateLevel("E[0]F{C[0]}FF{C[1]}X[0]");
        // map3
        //game.generateLevel("E[0]F{R}FF{C[0]}X[0]");
        // map4
        //game.generateLevel("E[0]F{C[0]}I[0]\n" +
		//				   "I[0]FX[0]\n" +
		//				   "I[0]FX[1]");    
		// map5				   
		//game.generateLevel("E[0]F{R}I[0]\n" +
		//				   "E[1]FI[0]\n" +
		//				   "I[0]FX[0]");
		// map6
		//game.generateLevel("E[0]F{P[0]}I[0]\n" + 
		//				   "I[0]F{R}FX[0]");
		// map7
		//game.generateLevel("E[0]{P[0]}FB{R}\n" +
		//				   "E[0]FH\n" +
		//				   "BFFF{T[0]}I[0]\n" +
		//				   "HF{C[0]}I[0]\n" +
		//				   "I[0]F{C[1]}FF{S[0]}X[0]\n" +
		//				   "I[0]FFFX[1]");
		// map8
		//game.generateLevel("E[0]F{C[0]}X[0]");
		// map9
		//game.generateLevel("E[0]F{T[0]}X[0]");
		// map10
		//game.generateLevel("E[0]F{R}F{U}I[0]\n" +
		//				   "I[0]F{P[0]}FFFFFFFX[0]");
		// map11
		//game.generateLevel("E[0]F{C[0]}F{U}FX[0]");
	
        //game.writeLevel();
	}
	
	public static void processCommand(String commandLine, Logger logger) 
		throws FileNotFoundException, MismatchingParametersException {
				
		String[] parameters = commandLine.split(" ");
		String command = parameters[0];
		
		if (command.compareTo("loadCommands")==0) {
			if (parameters.length<2)
				throw new MismatchingParametersException();
			CustomReader fileInput = new CustomReader(logger);
			fileInput.setInput(new BufferedReader(new FileReader(parameters[1])));
			try {
				while (true) {
					fileInput.readLineUnsafe();
				}
			} catch(IOException e) {
			}
		}
		else if (command.compareTo("loadMap")==0) {
			if (parameters.length<2)
				throw new MismatchingParametersException();
			CustomReader fileInput = new CustomReader(logger);
			fileInput.setInput(new BufferedReader(new FileReader(parameters[1])));
			String map = "";
			try {
				while (true) {
					if (map.compareTo("")!=0)
						map = map + "\n";
					map = map + fileInput.readLineUnsafe();
				}
			} catch(IOException e) {
			} finally {
				game.generateLevel(map);
				return;
			}
		} else if (command.compareTo("writeMap")==0) {
			game.writeLevel();
		} else if (command.compareTo("exit")==0) {
			System.exit(0);
		} else {
			logger.logMessage("x Error: UnknownCommand");
		}
	}
}
