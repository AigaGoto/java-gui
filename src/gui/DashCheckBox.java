package gui;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class DashCheckBox extends JCheckBox{
	
	StateManager stateManager;
	
	DashCheckBox(StateManager stateManager) {
		super("Dash");
		
		addItemListener(new dashCheckListener());
		
		this.stateManager = stateManager;
	}
	
	class dashCheckListener implements ItemListener {

		public void itemStateChanged(ItemEvent e) {
			int state = e.getStateChange();
			
			if (state == ItemEvent.SELECTED)
				stateManager.setDash(true);
			else
				stateManager.setDash(false);
		}
	}

}