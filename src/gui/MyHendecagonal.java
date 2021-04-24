package gui;

import java.awt.*;
import java.awt.geom.Ellipse2D;

public class MyHendecagonal extends MyDrawing{
	
	public MyHendecagonal(int x, int y, int w, int h, Color lineColor, Color fillColor, int lineWidth) {
		super(x, y, w, h, lineColor, fillColor, lineWidth);
	}
	
	public MyHendecagonal(int x, int y) {
		super(x, y);
	}

	public MyHendecagonal(int x, int y, int w, int h) {
		super(x, y, w, h);
	}
	
	
	public boolean contains(int x, int y) {
		// MyDrawingを継承する子クラス内でそれぞれ定義する。
		return region.contains(x,y);
	}
	

	
	public void setRegion() {
		// MyDrawingを継承する子クラス内でそれぞれ定義する。
		int x = getX();
		int y = getY();
		int w = getW();
			
		int point = 11;
		int x_i[] = new int[point];
		int y_i[] = new int[point];
		
		// 横幅が負の時の処理
		if (w < 0) {
			w *= -1;
		}
		
		for (int i= 0; i < point; i++) {
			x_i[i] = x + (int)(Math.cos(-Math.PI / 2 + 2 * Math.PI * i / (double)point) * w);
			y_i[i] = y + (int)(Math.sin(-Math.PI / 2 + 2 * Math.PI * i / (double)point) * w);
		}
		
		region = new Polygon(x_i, y_i, point);
	}

	public void draw(Graphics g) {
		
		int point = 11;
		
		int x_i[] = new int[point];
		int y_i[] = new int[point];
		int x_shade_i[] = new int[point];
		int y_shade_i[] = new int[point];
		
		int x = getX();
		int y = getY();
		int w = getW();
		
		
		// 横幅が負の時の処理
		if (w < 0) {
			w *= -1;
		}
		
		
		for (int i= 0; i < point; i++) {
			x_i[i] = x + (int)(Math.cos(-Math.PI / 2 + 2 * Math.PI * i / (double)point) * w);
			y_i[i] = y + (int)(Math.sin(-Math.PI / 2 + 2 * Math.PI * i / (double)point) * w);
			x_shade_i[i] = x_i[i] + 5;
			y_shade_i[i] = y_i[i] + 5;
		}
		
		
		
		Graphics2D g2 = (Graphics2D) g;
		
		if (getDashed())
			g2.setStroke(new MyDashStroke(getLineWidth()));
		else
			g2.setStroke(new BasicStroke(getLineWidth()));
		
		if (shade == true) {
			g2.setColor(Color.black);
			g2.fillPolygon(x_shade_i, y_shade_i, point);
			g2.setColor(Color.black);
			g2.drawPolygon(x_shade_i, y_shade_i, point);
		}
		
		g2.setColor(getFillColor());
		g2.fillPolygon(x_i, y_i, point);
		g2.setColor(getLineColor());
		g2.drawPolygon(x_i, y_i, point);
		
		super.draw_pol(g);
	}
}
