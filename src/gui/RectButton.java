package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class RectButton extends JButton implements State{
	
	StateManager stateManager;
	Mediator mediator;
	
	public RectButton(StateManager stateManager) {
		super("Rectangle");
		
		addActionListener(new RectListener());
		
		this.stateManager = stateManager;
		this.mediator = stateManager.mediator;
	}

	
	class RectListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			stateManager.setState(new RectButton(stateManager));
		}
	}
	
	public void mouseDown(int x, int y) {
		stateManager.addDrawing(new MyRectangle(x, y, 0, 0, stateManager.lineColor, stateManager.fillColor, 1));
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

