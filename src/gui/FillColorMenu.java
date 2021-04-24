package gui;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.*;

import gui.LineColorMenu.ColorChooserListener;
import gui.LineColorMenu.ColorListener;

public class FillColorMenu extends JMenu{
	
	MyApplication app;
	StateManager stateManager;
	FillColorLabel fcl;
	
	public FillColorMenu(MyApplication app) {
		super("FillColor");
		
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
		this.fcl = app.fcl;
		
	}
	
	class ColorListener implements ActionListener{
		
		Color color;
		
		ColorListener(Color color){
			this.color = color;
		}
		
		public void actionPerformed(ActionEvent e) {
			stateManager.mediator.setFillColor(color);
			stateManager.setFillColor(color);
			fcl.setFillColor(color);
		}
	}
	
	class ColorChooserListener implements ActionListener{
		
		public void actionPerformed(ActionEvent e) {
			JColorChooser colorChooser = new JColorChooser();
			Color color = colorChooser.showDialog(app, "êFÇÃëIë", stateManager.getFillColor());
			
			if (color != null) {
				stateManager.mediator.setFillColor(color);
				stateManager.setFillColor(color);
				fcl.setFillColor(color);
			}
		}
	}
	
}
