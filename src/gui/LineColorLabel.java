package gui;

import javax.swing.JLabel;
import java.awt.*;

public class LineColorLabel extends JLabel{
	
	Color lineColor;
	
	public LineColorLabel() {
		super("LineColor");
		
		lineColor = Color.black;
		
		setForeground(lineColor);
	}
	
	public void setLineColor(Color color) {
		lineColor = color;
		setForeground(lineColor);
	}

	
}
