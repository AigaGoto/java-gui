package gui;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.*;

public class LineColorMenu extends JMenu{
	
	MyApplication app;
	StateManager stateManager;
	LineColorLabel lcl;
	
	public LineColorMenu(MyApplication app) {
		super("LineColor");
		
		JMenuItem redItem, blueItem, greenItem, whiteItem, blackItem, colorChooser;
		redItem = new JMenuItem("Red");
		blueItem = new JMenuItem("Blue");
		greenItem = new JMenuItem("Green");
		whiteItem = new JMenuItem("White");
		blackItem = new JMenuItem("Black");
		colorChooser = new JMenuItem("Other Colors");
		
		this.add(redItem);
		this.add(blueItem);
		this.add(greenItem);
		this.add(whiteItem);
		this.add(blackItem);
		
		this.addSeparator();
		
		this.add(colorChooser);
		
		redItem.addActionListener(new ColorListener(Color.red));
		blueItem.addActionListener(new ColorListener(Color.blue));
		greenItem.addActionListener(new ColorListener(Color.green));
		whiteItem.addActionListener(new ColorListener(Color.white));
		blackItem.addActionListener(new ColorListener(Color.black));
		colorChooser.addActionListener(new ColorChooserListener());
		
		this.stateManager = app.stateManager;
		this.lcl = app.lcl; 
		
	}
	
	class ColorListener implements ActionListener{
		
		Color color;
		
		ColorListener(Color color){
			this.color = color;
		}
		
		public void actionPerformed(ActionEvent e) {
			stateManager.mediator.setLineColor(color);
			stateManager.setLineColor(color);
			lcl.setLineColor(color);
		}
	}
	
	class ColorChooserListener implements ActionListener{
		
		public void actionPerformed(ActionEvent e) {
			JColorChooser colorChooser = new JColorChooser();
			Color color = colorChooser.showDialog(app, "êFÇÃëIë", stateManager.getLineColor());
			
			if (color != null) {
				stateManager.mediator.setLineColor(color);
				stateManager.setLineColor(color);
				lcl.setLineColor(color);
			}
		}
	}
	
}
