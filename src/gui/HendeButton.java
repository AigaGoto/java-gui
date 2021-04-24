package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class HendeButton extends JButton implements State{
	
	StateManager stateManager;
	Mediator mediator;
	
	public HendeButton(StateManager stateManager) {
		super("Hendecagonal");
		
		addActionListener(new HendecagonalListener());
		
		this.stateManager = stateManager;
		this.mediator = stateManager.mediator;
	}

	
	class HendecagonalListener implements ActionListener{
			public void actionPerformed(ActionEvent e) {
				stateManager.setState(new HendeButton(stateManager));
		}
	}
	
	
	public void mouseDown(int x, int y) {
		stateManager.addDrawing(new MyHendecagonal(x, y, 0, 0, stateManager.lineColor, stateManager.fillColor, 1));
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