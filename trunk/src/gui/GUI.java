package gui;

import java.io.*;
import java.awt.*;
import java.awt.event.*;

public class GUI extends Frame {
	
	class WindowCloser extends WindowAdapter {
		
	}
	
	Game game;
	GUICanvas canvas;
	Choice mapSelector;

	GUI(String name, Logger logger, CustomReader input) {
		super(name);
		
		setLayout(new BorderLayout());
				
		game = new Game("game", logger, input);
		
		game.generateLevel("I[0]FFFX[0]\nBFFI[0]");	
		game.createDrawers();
		
		canvas = new GUICanvas(game);
		canvas.setSize(400,400);
		
		add("Center", canvas);
		
		Panel menuPanel = new Panel();
		add("South",menuPanel);
		
		menuPanel.setLayout(new GridLayout(1,3));
		Button newGameButton = new Button("New game");
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
		Button pauseButton = new Button("Pause");
		menuPanel.add(pauseButton);
		
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
		
		class StartListener implements ActionListener {			
			
			public void actionPerformed(ActionEvent e) {
				
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
					System.out.println(map);
					if(map.length()>0) {
						game.generateLevel(map);
						game.createDrawers();
						canvas.repaint();
					}
				}
			}
		}
		newGameButton.addActionListener(new StartListener());
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
