package gui;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class ShadeCheckBox extends JCheckBox{
	
	StateManager stateManager;
	
	ShadeCheckBox(StateManager stateManager) {
		super("Shade");
		
		addItemListener(new shadeCheckListener());
		
		this.stateManager = stateManager;
	}
	
	class shadeCheckListener implements ItemListener {

		public void itemStateChanged(ItemEvent e) {
			int state = e.getStateChange();
			
			if (state == ItemEvent.SELECTED)
				stateManager.setShade(true);
			else
				stateManager.setShade(false);
		}
	}

}



