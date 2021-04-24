package gui;

import java.awt.Color;

public class StateManager {
	
	Mediator mediator;
	MyCanvas canvas;
	
	State currentState;
	MyDrawing currentDrawing;
	
	Color lineColor = Color.black;
	Color fillColor = Color.white;
	
	boolean shade = false;
	boolean dash = false;
	
	int lineWidth = 1;
	
	public StateManager(MyCanvas canvas) {
		this.canvas = canvas;
		this.mediator = canvas.mediator;
		this.currentState = new RectButton(this);
	}
	
	public void setState(State state) {
		currentState = state;
	}
	
	public void setShade(boolean shade) {
		this.shade = shade;
	}
	
	public void setDash(boolean dash) {
		this.dash = dash;
	}
	
	public void setLineWidth(int lineWidth) {
		this.lineWidth = lineWidth;
	}
	
	
	public void setLineColor(Color color) {
		this.lineColor = color;
	}
	
	public Color getLineColor() {
		return this.lineColor;
	}
	
	public void setFillColor(Color color) {
		this.fillColor = color;
	}
	
	public Color getFillColor() {
		return this.fillColor;
	}
	
	public void setAlpha(int alpha) {
		
		Color fc = this.fillColor;
		int R = fc.getRed();
		int G = fc.getGreen();
		int B = fc.getBlue();
		
		this.fillColor = (new Color(R, G, B, alpha));
		
		Color lc = this.lineColor;
		R = lc.getRed();
		G = lc.getGreen();
		B = lc.getBlue();
		
		this.lineColor = (new Color(R, G, B, alpha));
	}
	
	public void addDrawing(MyDrawing d) {
		d.setDashed(dash);
		d.setLineWidth(lineWidth);
		mediator.addDrawing(d);
		mediator.repaint();
	}
	
	public void mouseDown(int x, int y) {
		currentState.mouseDown(x, y);
		currentDrawing = mediator.getLastDrawing();
	}
	
	
	public void mouseUp(int x, int y) {
		currentState.mouseUp(x, y);
		mediator.repaint();
	}
	
	// マウスのドラッグ処理
	public void mouseDrag(int x, int y) {
		currentState.mouseDrag(x, y);
		mediator.repaint();
	}
	

}
