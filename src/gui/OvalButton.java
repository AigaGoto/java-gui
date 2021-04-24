package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class OvalButton extends JButton implements State{
	
	StateManager stateManager;

	
	public OvalButton(StateManager stateManager) {
		
		super("Oval");
		
		addActionListener(new OvalListener());
		
		this.stateManager = stateManager;
	}


	class OvalListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			stateManager.setState(new OvalButton(stateManager));
		}
	}
	
	
	public void mouseDown(int x, int y) {
		stateManager.addDrawing(new MyOval(x, y, 0, 0, stateManager.lineColor, stateManager.fillColor, stateManager.lineWidth));
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