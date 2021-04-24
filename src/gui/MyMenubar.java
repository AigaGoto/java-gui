package gui;

import javax.swing.JMenuBar;

public class MyMenubar extends JMenuBar{
	
	MyMenubar(MyApplication app) {
		FileMenu fileMenu = new FileMenu(app.stateManager);
		this.add(fileMenu);
		
		EditMenu editMenu = new EditMenu(app.stateManager);
		this.add(editMenu);
		
		LineColorMenu lineColorMenu = new LineColorMenu(app);
		this.add(lineColorMenu);
		
		FillColorMenu fillColorMenu = new FillColorMenu(app);
		this.add(fillColorMenu);
	}
}
