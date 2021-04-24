package gui;

import javax.swing.JLabel;
import java.awt.*;

public class FillColorLabel extends JLabel{
	
	Color fillColor;
	
	public FillColorLabel() {
		super("FillColor");
		
		fillColor = Color.white;
		
		setForeground(fillColor);
	}
	
	public void setFillColor(Color color) {
		fillColor = color;
		setForeground(fillColor);
	}

	
}