package gui;

import java.awt.*;
import java.awt.geom.Ellipse2D;

public class MyOval extends MyDrawing{
	public MyOval(int xpt, int ypt) {
		super();
		setLocation(xpt,ypt);
	}
	
	public MyOval(int x, int y, int w, int h, Color lineColor, Color fillColor, int lineWidth) {
		super(x, y, lineColor, fillColor, lineWidth);
		setSize(w, h);
	}
	
	public MyOval(int x, int y, int w, int h) {
		super(x, y, w, h);
	}
	
	public boolean contains(int x, int y) {
		// MyDrawing���p������q�N���X���ł��ꂼ���`����B
		return region.contains(x,y);
	}
	
	public void setRegion() {
		// MyDrawing���p������q�N���X���ł��ꂼ���`����B
		int x = getX();
		int y = getY();
		int w = getW();
		int h = getH();
		
		// �����⍂�������̎��̏���
		if (w < 0) {
			x += w;
			w *= -1;
		}
		if (h < 0) {
			y += h;
			h *= -1;
		}
		
		region = new Ellipse2D.Double(x, y, w, h);
	}

	public void draw(Graphics g) {
		
		int x = getX();
		int y = getY();
		int w = getW();
		int h = getH();
		
		// �����⍂�������̎��̏���
		if (w < 0) {
			x += w;
			w *= -1;
		}
		if (h < 0) {
			y += h;
			h *= -1;
		}
		
		Graphics2D g2 = (Graphics2D) g;
		
		if (getDashed())
			g2.setStroke(new MyDashStroke(getLineWidth()));
		else
			g2.setStroke(new BasicStroke(getLineWidth()));
		
		if (shade == true) {
			g2.setColor(Color.black);
			g2.fillOval(x+5, y+5, w, h);
			g2.setColor(Color.black);
			g2.drawOval(x+5, y+5, w, h);
		}
		
		g2.setColor(getFillColor());
		g2.fillOval(x, y, w, h);
		g2.setColor(getLineColor());
		g2.drawOval(x, y, w, h);
		
		super.draw(g);
	}
}
