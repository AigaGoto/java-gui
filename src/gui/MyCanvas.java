package gui;

import java.util.*;
import java.awt.*;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.*;

public class MyCanvas extends JPanel{
	
	Mediator mediator;
	
	public MyCanvas() {
		setBackground(Color.white);
		this.mediator = new Mediator(this);
	}
	
	public void paint(Graphics g) {
		super.paint(g);
		
		Enumeration<MyDrawing> e = mediator.drawingsElements();
		while (e.hasMoreElements()) {
			MyDrawing d = e.nextElement();
			d.draw(g);
		}
	}
	
	
}
