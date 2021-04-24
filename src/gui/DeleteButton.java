package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class DeleteButton extends JButton{
	
	StateManager stateManager;
	
	public DeleteButton(StateManager stateManager) {
		super("Delete");
		
		addActionListener(new DeleteListener());
		
		this.stateManager = stateManager;
	}

	
	class DeleteListener implements ActionListener{
			public void actionPerformed(ActionEvent e) {
				stateManager.mediator.delete();
		}
	}
	
}