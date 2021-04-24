package gui;

import java.awt.*;

public class MyString extends MyDrawing{
	private String str;
	
	public MyString(String line, int x, int y, Color lineColor,  int lineWidth) {
		super(x, y, lineColor, lineWidth);
		this.str = line;
	}
	
	public String getStr() {
		return this.str;
	}
	
	public void draw(Graphics g) {
	
		int x = getX();
		int y = getY();
		
		String str = getStr();
		
		Graphics2D g2 = (Graphics2D) g;
		g2.setStroke(new BasicStroke(getLineWidth()));
		g2.setColor(getLineColor());
		g2.drawString(str, x, y);
	}
}
