package gui;

import java.io.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.Timer;

public class GUI extends Frame {
	
	class WindowCloser extends WindowAdapter {
		
	}
		
	Game game;
	GUICanvas canvas;
	Panel menuPanel;
	Button newGameButton;
	Choice mapSelector;
	Button pauseButton;
	
	Timer timer;

	GUI(String name, Logger logger, CustomReader input) {
		super(name);		
		
		setLayout(new BorderLayout());
				
		game = new Game("game", logger, input);
		
		game.generateLevel("I[0]FFFX[0]\nBFFI[0]");	
		game.createDrawers();
		
		canvas = new GUICanvas(game);
		canvas.setSize(400,400);
		
		add("Center", canvas);
		
		menuPanel = new Panel();
		add("South",menuPanel);
		
		menuPanel.setLayout(new GridLayout(1,3));
		newGameButton = new Button("New game");
		menuPanel.add(newGameButton);
		mapSelector = new Choice();
		
		File mapDir = new File("maps");
		String[] maps = mapDir.list();
		if (maps == null) {
			mapSelector.add("Map directory not found!");
			mapSelector.setEnabled(false);
		} else {
			for(String s : maps)
				mapSelector.add(s);
		}		
		
		menuPanel.add(mapSelector);
		pauseButton = new Button("Pause");
		menuPanel.add(pauseButton);
		
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});	
		
		class StartListener implements ActionListener {			
			
			public void actionPerformed(ActionEvent e) {
				
				game = new Game("game", game.logger, game.input);
				canvas.setGame(game);
								
				String map = "";
				boolean fileExists = false;
				try {
					CustomReader fileInput = new CustomReader(new ConsoleLogger());
					fileInput.setInput(new BufferedReader(new FileReader("maps/" + mapSelector.getSelectedItem())));					

					String s = fileInput.readLineUnsafe();
					fileExists = (s != null);
					while (s != null) {					
						if (map.compareTo("")!=0)
							map = map + "\n";
						map = map + s;
						s = fileInput.readLineUnsafe();
					}
				} catch(IOException ioe) {
					if (!fileExists) {
						// nem történik semmi, ez az eset szinte sosem fordulhat elő
					}
				} finally {					
					if(map.length()>0) {
						game.generateLevel(map);
						game.createDrawers();
						canvas.repaint();
					}
				}
				
				game.logger.logMessage("o New game started.");
			}
		}
		newGameButton.addActionListener(new StartListener());
		
		class PauseListener implements ActionListener {
			
			public void actionPerformed(ActionEvent e) {
				
				if(timer.isRunning()) {
					timer.stop();
					pauseButton.setLabel("Continue");
					game.logger.logMessage("o Game paused.");
				} else {
					timer.start();
					pauseButton.setLabel("Pause");
					game.logger.logMessage("o Continuing game...");
				}
			}			
		}
		pauseButton.addActionListener(new PauseListener());
		
		class TickListener implements ActionListener {
			
			public void actionPerformed(ActionEvent e) {
				
				if(!(game.gameIsOver() || game.gameIsWon())) {
					game.tick();
					game.refresh(canvas.getGraphics());
				}
			}
		}
		
		class RobberControlListener extends KeyAdapter {
			
			public void keyPressed(KeyEvent e) {				
				
				if ((game.player == null) || (game.player.getCell() == null))
					return;
				
				switch(e.getKeyCode()) {
					case KeyEvent.VK_LEFT:
					case KeyEvent.VK_RIGHT:
						
						int options = 0;
						if (game.player.getCell().getRoad() != null) {
							if(game.player.getIsGoingForward())
								options = game.player.getCell().getRoad().getExitIntersection().getNextCells().length + game.player.getCell().getRoad().getExitIntersection().getPreviousCells().length;
							else
								options = game.player.getCell().getRoad().getEntryIntersection().getNextCells().length + game.player.getCell().getRoad().getEntryIntersection().getPreviousCells().length;
						}
						else
							options = game.player.getCell().getNextCells().length + game.player.getCell().getPreviousCells().length;
						
						if(e.getKeyCode() == KeyEvent.VK_LEFT)
							game.player.setPreferredCell((game.player.getPreferredCell()-1+options) % options);
						else
							game.player.setPreferredCell((game.player.getPreferredCell()+1) % options);
						break;
						
					case KeyEvent.VK_UP:
						game.player.increaseSpeed();
						break;
					case KeyEvent.VK_DOWN:
						game.player.decreaseSpeed();
						break;
				}
			}
		}
		newGameButton.addKeyListener(new RobberControlListener());
		
		timer = new Timer(100, new TickListener());
		timer.start();
	}

	public static void main(String[] args) {
		
		Logger logger = new ConsoleLogger();
		CustomReader input = new CustomReader(logger);
		input.setInput(new BufferedReader(new InputStreamReader(System.in)));
		
		GUI mainForm = new GUI("SzoftLab4 beadandó - GUI - vihar",logger,input);
		
		mainForm.pack();
		//mainForm.show();       // java 1.5-től deprecated
		mainForm.setResizable(false);		
		mainForm.setVisible(true);		
	}
}
