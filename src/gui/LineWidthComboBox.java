package gui;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class LineWidthComboBox extends JComboBox{
	
	StateManager sm;
	Mediator ma;
	
	
	public LineWidthComboBox(Object[] item, StateManager stateManager) {
		super(item);
		
		addActionListener(new LineWidthListener());
		this.setEditable(true);
		this.setPreferredSize(new Dimension(60, 25));
		
		this.sm = stateManager;
		this.ma = sm.mediator;
		
	}
	
	class LineWidthListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			try {
				String x = (String)LineWidthComboBox.this.getSelectedItem();
				int lineWidth = Integer.parseInt(x);
				sm.setLineWidth(lineWidth);
				ma.setLineWidth(lineWidth);
			} catch (NumberFormatException ex) {
				JFrame error = new JFrame();
				JOptionPane.showMessageDialog(error, "Please input an integer", "Error", JOptionPane.ERROR_MESSAGE);
				
			}
		}
	}
	
}
