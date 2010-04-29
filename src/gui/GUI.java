package gui;

import java.io.*;
import java.awt.*;
import java.awt.event.*;

public class GUI extends Frame {
	
	class WindowCloser extends WindowAdapter {
		
	}
	
	Canvas canvas;

	GUI(String name) {
		super(name);
		
		setLayout(new BorderLayout());
				
		canvas = new Canvas();
		canvas.setSize(400,400);
		
		add("Center", canvas);
		
		Panel menuPanel = new Panel();
		add("South",menuPanel);
		
		menuPanel.setLayout(new GridLayout(1,3));
		menuPanel.add(new Button("New game"));
		Choice mapSelector = new Choice();
		mapSelector.add("map1");
		mapSelector.add("map2");
		mapSelector.add("map3");
		menuPanel.add(mapSelector);
		menuPanel.add(new Button("Pause"));
		
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
	}

	public static void main(String[] args) {
		
		GUI mainForm = new GUI("SzoftLab4 beadandó - GUI - vihar");
		
		mainForm.pack();
		//mainForm.show();       // java 1.5-től deprecated
		mainForm.setResizable(false);		
		mainForm.setVisible(true);
		
		Logger logger = new ConsoleLogger();
		CustomReader input = new CustomReader(logger);
		input.setInput(new BufferedReader(new InputStreamReader(System.in)));
		Game game = new Game("game", logger, input);
		
		game.generateLevel("I[0]FFFX[0]");
		
		game.createDrawers(mainForm.canvas.getGraphics());
		game.draw();
				
		mainForm.repaint();
	}
}
