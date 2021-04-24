package gui;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.*;

import gui.EditMenu.AddShadeListener;
import gui.EditMenu.CopyListener;
import gui.EditMenu.CutListener;
import gui.EditMenu.DeleteShadeListener;
import gui.EditMenu.PasteListener;

public class FileMenu extends JMenu{
	StateManager stateManager;
	
	public FileMenu(StateManager stateManager) {
		super("File");
		
		JMenuItem load, save;
		load = new JMenuItem("Load");
		save = new JMenuItem("Save");
		
		this.add(load);
		this.add(save);
		
		load.addActionListener(new FileInActionListener());
		save.addActionListener(new FileOutActionListener());
		
		this.stateManager = stateManager;
		
	}
	
	class FileInActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			stateManager.mediator.fileIn();
		}
	}
	
	class FileOutActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			stateManager.mediator.fileOut();
		}
	}
	
}
