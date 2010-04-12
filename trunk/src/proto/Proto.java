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
			if (args.length>0) {
				logger.log("Loading commands from file specified as input parameter: " + args[0]);				
				processCommand("loadCommands "+args[0],logger);
			}
			while (!game.gameIsOver() && !game.gameIsWon())
				processCommand(input.readLineUnsafe(),logger);
		} catch (MismatchingParametersException e) {
			logger.logMessage("x Error: MismatchingParameters");
		} catch (FileNotFoundException e) {
			logger.logMessage("x Error: FileNotFound");
		} catch (IOException e) {			
		}              
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
			boolean fileExists = false;		
			try {
				String s = fileInput.readLineUnsafe();
				fileExists = (s != null);
				while (s != null)
				{					
					processCommand(s, logger);
					s = fileInput.readLineUnsafe();					
				}				
			} catch(IOException e) {
				if (!fileExists)
					logger.log("x Error FileNotFound: "+parameters[1]);
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
			boolean fileExists = false;
			try {
				String s = fileInput.readLineUnsafe();
				fileExists = (s != null);
				while (s != null) {					
					if (map.compareTo("")!=0)
						map = map + "\n";
					map = map + s;
					s = fileInput.readLineUnsafe();
				}
			} catch(IOException e) {
				if (!fileExists) {
					logger.log("x Error FileNotFound: "+parameters[1]);
				}
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
		// -------------------------------------------------------------
		// sebesség kikapcsolása
		} else if (command.compareTo("turnOffSpeed")==0) {
			game.speed = false;
		// -------------------------------------------------------------
		// sebesség beállítása
		} else if (command.compareTo("setInverseSpeed")==0) {			
			if (parameters.length < 2)
				throw new MismatchingParametersException();
			int index;
			int iSpeed;
			switch (parameters[1].charAt(0)) {
			case 'c':
				if (parameters.length != 4)
					throw new MismatchingParametersException();
				index = Integer.valueOf(parameters[2]);
				iSpeed = Integer.valueOf(parameters[3]);
				game.cars[index].setInverseSpeed(iSpeed);
				break;
			case 'p':
				if (parameters.length != 4)
					throw new MismatchingParametersException();
				index = Integer.valueOf(parameters[2]);
				iSpeed = Integer.valueOf(parameters[3]);
				game.policemen[index].setInverseSpeed(iSpeed);
				break;
			case 'r':
				if (parameters.length != 3)
					throw new MismatchingParametersException();				
				iSpeed = Integer.valueOf(parameters[2]);
				game.player.setInverseSpeed(iSpeed);
				break;
			}			
		// -------------------------------------------------------------
		// civil autó következő választásának beállítása
		} else if (command.compareTo("moveCivil")==0) {
			if (parameters.length != 3)
				throw new MismatchingParametersException();
			int index = Integer.valueOf(parameters[1]);
			int option = Integer.valueOf(parameters[2]);
			game.cars[index].setPreferredCell(option);
		// -------------------------------------------------------------
		// rendőrautó következő választásának beállítása
		} else if (command.compareTo("movePolice")==0) {
			if (parameters.length != 3)
				throw new MismatchingParametersException();
			int index = Integer.valueOf(parameters[1]);
			int option = Integer.valueOf(parameters[2]);
			game.policemen[index].setPreferredCell(option);
		// -------------------------------------------------------------
		// rabló következő választásának beállítása
		} else if (command.compareTo("moveRobber")==0) {
			if (parameters.length != 2)
				throw new MismatchingParametersException();			
			int option = Integer.valueOf(parameters[1]);
			game.player.setPreferredCell(option);
		// -------------------------------------------------------------
		// halhatatlanság idejének beállítása
		} else if (command.compareTo("setGodModeDuration")==0) {
			if (parameters.length != 2)
				throw new MismatchingParametersException();			
			int value = Integer.valueOf(parameters[1]);
			game.player.setGodModeDuration(value);
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
