package gui;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.*;

public class EditMenu extends JMenu{
	
	StateManager stateManager;
	
	public EditMenu(StateManager stateManager) {
		super("Edit");
		
		JMenuItem copyItem, cutItem, pasteItem, deleteItem, allDeleteItem, addShade, deleteShade;
		copyItem = new JMenuItem("copy");
		cutItem = new JMenuItem("cut");		
		pasteItem = new JMenuItem("paste");
		deleteItem = new JMenuItem("delete");
		allDeleteItem = new JMenuItem("all delete");
		addShade = new JMenuItem("add shade");
		deleteShade = new JMenuItem("delete shade");
		
		this.add(copyItem);
		this.add(cutItem);
		this.add(pasteItem);
		
		this.addSeparator();
		
		this.add(deleteItem);
		this.add(allDeleteItem);
		
		this.addSeparator();
		
		this.add(addShade);
		this.add(deleteShade);
		
		copyItem.addActionListener(new CopyListener());
		cutItem.addActionListener(new CutListener());
		pasteItem.addActionListener(new PasteListener());
		deleteItem.addActionListener(new DeleteListener());
		allDeleteItem.addActionListener(new AllDeleteListener());
		addShade.addActionListener(new AddShadeListener());
		deleteShade.addActionListener(new DeleteShadeListener());
		
		this.stateManager = stateManager;
		
	}
	
	class CopyListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			
				stateManager.mediator.copy();
			
		}
	}
	
	class CutListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			
				stateManager.mediator.cut();
			
		}
	}
	
	class PasteListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			
				stateManager.mediator.paste();
	
		}
		
	}
	
	class AddShadeListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			stateManager.mediator.setShade(true);
		}
	}
	
	class DeleteShadeListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			stateManager.mediator.setShade(false);
		}
	}
	
	class DeleteListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			stateManager.mediator.delete();
		}
	}
	
	class AllDeleteListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			stateManager.mediator.all_delete();
		}
	}
	
}
