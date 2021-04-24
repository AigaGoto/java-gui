package gui;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class AlphaComboBox extends JComboBox{
	StateManager sm;
	Mediator ma;
	
	AlphaComboBox(Object[] item, StateManager sm){
		super(item);
		
		addActionListener(new TransparentListener());
		this.setEditable(true);
		this.setPreferredSize(new Dimension(60, 25));
		
		this.sm = sm;
		this.ma = sm.mediator;
	}
	
	class TransparentListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			String x = (String)AlphaComboBox.this.getSelectedItem();
			try {
				int p = Integer.parseInt(x);
				int alpha = calc(p);
				
				sm.setAlpha(alpha);
				ma.setAlpha(alpha);
			} catch (Exception ex) {
				JFrame error = new JFrame();
				JOptionPane.showMessageDialog(error, "Please input an integer from 0 to 100", "Error", JOptionPane.ERROR_MESSAGE);
			}
		}
		
		// 0~255をパーセント表示(0~100%)にするための計算
		public int calc(int p) {
			double alpha; // alpha 
			alpha = (double)(p) / 100 * 255;
			return (int)alpha;
		}
		
	}
	
}
