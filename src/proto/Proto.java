package proto;

import java.io.*;

public class Proto {
	
	static class MismatchingParametersException extends Exception {
		public MismatchingParametersException() {
		}
	}
	
	static Game game;
		
	/**
	 * 
	 */ 
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
	
	/**
	 * 
	 */ 
	public static void processCommand(String commandLine, Logger logger) 
		throws FileNotFoundException, MismatchingParametersException {
				
		String[] parameters = commandLine.split(" ");
		String command = parameters[0];
		
		// -------------------------------------------------------------
		// parancsok betöltése fájlból
		if (command.compareTo("loadCommands")==0) {
			if (parameters.length<2)
				throw new MismatchingParametersException();
			CustomReader fileInput = new CustomReader(logger);
			fileInput.setInput(new BufferedReader(new FileReader(parameters[1])));			
			try {
				String s = fileInput.readLineUnsafe();
				while (s != null)
				{					
					processCommand(s, logger);
					s = fileInput.readLineUnsafe();					
				}				
			} catch(IOException e) {
				return;
			}
		}
		// -------------------------------------------------------------
		// pálya betöltése fájlból
		else if (command.compareTo("loadMap")==0) {
			if (parameters.length<2)
				throw new MismatchingParametersException();
			CustomReader fileInput = new CustomReader(logger);
			fileInput.setInput(new BufferedReader(new FileReader(parameters[1])));
			String map = "";
			try {
				String s = fileInput.readLineUnsafe();
				while (s != null) {					
					if (map.compareTo("")!=0)
						map = map + "\n";
					map = map + s;
					s = fileInput.readLineUnsafe();
				}
			} catch(IOException e) {
			} finally {
				if(map.length()>0)
					game.generateLevel(map);
				return;
			}
		// -------------------------------------------------------------
		// pálya állapotának kiírása
		} else if (command.compareTo("writeMap")==0) {
			game.writeLevel();
		// -------------------------------------------------------------
		// i. jelzés beállítása
		} else if (command.compareTo("setSign")==0) {
			if (parameters.length != 3)
				throw new MismatchingParametersException();
			String signName = "S[" + Integer.valueOf(parameters[1]) + "]";
			String lightName = "T[" + Integer.valueOf(parameters[1]) + "]";
			ISign s = null;
			boolean end = false;
			while (!end)
			{
				for(Intersection i : game.Intersections()) {
					s = i.getSign();
					end = ((s != null) 
						&& ((s.getName().compareTo(signName) == 0) 
						|| (s.getName().compareTo(lightName) == 0)));
				}
				if (!end)
					for(Road r : game.Roads()) {
						if (!end)
							for(Cell c : r.getCells()) {
								s = c.getSign();
								end = ((s != null) 
									&& ((s.getName().compareTo(signName) == 0) 
									|| (s.getName().compareTo(lightName) == 0)));
							}
					}
			}
			boolean value = (parameters[2].compareTo("true")==0);
			if (s != null)
				s.setBlocking(value);
		// sebesség kikapcsolása
		} else if (command.compareTo("turnOffSpeed")==0) {
			game.speed = false;
		// -------------------------------------------------------------
		// játék léptetése
		} else if (command.compareTo("tick")==0) {
			int count = 1;
			if (parameters.length>=2)
				count = Integer.valueOf(parameters[1]);
			for (int i=0; i<count; i++)
				game.tick();
		// -------------------------------------------------------------
		// kilépés a tesztelésből
		} else if (command.compareTo("exit")==0) {
			System.exit(0);
		// -------------------------------------------------------------
		// értelmezhetetlen parancs
		} else {
			logger.logMessage("x Error: UnknownCommand");
		}
	}
}
