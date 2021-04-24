package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class GetButton extends JButton{
	
	JTextField text;
	StateManager stateManager;
	
	GetButton(StateManager stateManager, JTextField text){
		super("Get");
		
		this.text = text;
		
		addActionListener(new GetListener());
		
		this.stateManager = stateManager;
	}
	
	class GetListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			try {
				int lineWidth = Integer.parseInt(text.getText());
				stateManager.setLineWidth(lineWidth);
				stateManager.mediator.setLineWidth(lineWidth);
			} catch (NumberFormatException error){
				System.out.println("”š‚ğ“ü‚ê‚Ä‚­‚¾‚³‚¢");
			}
			
		}
	}
}
