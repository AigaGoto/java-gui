package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class TriButton extends JButton implements State{
	
	StateManager stateManager;
	Mediator mediator;
	
	public TriButton(StateManager stateManager) {
		super("Triangle");
		
		addActionListener(new TriListener());
		
		this.stateManager = stateManager;
		this.mediator = stateManager.mediator;
	}

	
	class TriListener implements ActionListener{
			public void actionPerformed(ActionEvent e) {
				stateManager.setState(new TriButton(stateManager));
		}
	}
	
	
	public void mouseDown(int x, int y) {
		stateManager.addDrawing(new MyTriangle(x, y, 0, 0, stateManager.lineColor, stateManager.fillColor, 1));
	}
	
	public void mouseUp(int x, int y) {
		if (stateManager.shade == true) {
			MyDrawing d = stateManager.currentDrawing;
			d.setShade(true);
		}
	}
	
	public void mouseDrag(int x, int y) {
		int x1 = stateManager.currentDrawing.getX();
		int y1 = stateManager.currentDrawing.getY();
		stateManager.currentDrawing.setSize(x - x1, y -y1);
	}
	
	
}

