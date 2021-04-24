package gui;

import java.awt.event.InputEvent;

public class KeyManager {
	
	StateManager sm;
	Mediator ma;
	
	public KeyManager(StateManager stateManager) {
		this.sm = stateManager;
		this.ma = sm.mediator;
	}

	public void keyPressed(int keycode, int mod) {
		
		if (((mod & InputEvent.CTRL_DOWN_MASK) != 0) && keycode == 'X'){
			ma.cut();
		}
		
		if (((mod & InputEvent.CTRL_DOWN_MASK) != 0) && keycode == 'C'){
			ma.copy();
		}

		if (((mod & InputEvent.CTRL_DOWN_MASK) != 0) && keycode == 'V'){
			ma.paste();
		}

		if (keycode == 127){ // Deleteのキーコード
			ma.delete();
		}
		
		if (((mod & InputEvent.CTRL_DOWN_MASK) != 0) && keycode == 'S'){
			ma.fileOut();
		}
		
		if (((mod & InputEvent.CTRL_DOWN_MASK) != 0) && keycode == 'L'){
			ma.fileIn();
		}
		
	}
	
}
