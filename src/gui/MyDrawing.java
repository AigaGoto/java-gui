package gui;

import java.awt.*;
import java.io.Serializable;

public class MyDrawing implements Cloneable,Serializable{
	private int x, y, w, h; //X座標、　Y座標、　幅、　高さ
	private Color lineColor, fillColor; // 線の色、　塗色
	private int lineWidth; // 選の太さ
	private boolean isDashed = false; // 線種 
	
	boolean isSelected;
	Shape region;
	final int SIZE = 7;
	
	boolean shade;
	
	public MyDrawing() {
		x = y = 0;
		w = h = 40;
		lineColor = Color.black;
		fillColor = Color.white;
		lineWidth = 1;
	}
	
	public MyDrawing(int x, int y) {
		this();
		setLocation(x, y);
	}
	
	public MyDrawing(int x, int y, int w, int h) {
		this(x, y);
		setSize(w, h);
	}
	
	public MyDrawing(int x, int y, Color lineColor, int lineWidth) {
		this(x, y);
		setLineColor(lineColor);
		setLineWidth(lineWidth);
	}
	
	public MyDrawing(int x, int y, Color lineColor, Color fillColor, int lineWidth) {
		this(x, y, lineColor, lineWidth);
		setFillColor(fillColor);
	}
	
	public MyDrawing(int x, int y, int w, int h, Color lineColor, Color fillColor, int lineWidth) {
		this(x, y, lineColor, fillColor, lineWidth);
		setSize(w, h);
	}
	
	public void draw(Graphics g) {
		if (isSelected) {
			g.setColor(Color.gray);
			g.fillRect(x +  w/2 - SIZE/2, y - SIZE/2, SIZE, SIZE);
			g.fillRect(x - SIZE/2, y + h/2 - SIZE/2, SIZE, SIZE);
			g.fillRect(x +  w/2 - SIZE/2, y + h - SIZE/2, SIZE, SIZE);
			g.fillRect(x + w - SIZE/2, y + h/2 - SIZE/2, SIZE, SIZE);
			g.fillRect(x - SIZE/2, y - SIZE/2, SIZE, SIZE);
			g.fillRect(x + w - SIZE/2, y - SIZE/2, SIZE, SIZE);
			g.fillRect(x - SIZE/2, y + h - SIZE/2, SIZE, SIZE);
			g.fillRect(x + w - SIZE/2, y + h - SIZE/2, SIZE, SIZE);
		}
	}
	
	public void draw_pol(Graphics g) {
		if (isSelected) {
			g.setColor(Color.gray);
			int x_p = x - w;
			int y_p = y - w;
			int w_p = w*2;
			int h_p = w_p;
			g.fillRect(x_p + w_p/2 - SIZE/2, y_p - SIZE/2, SIZE, SIZE);
			g.fillRect(x_p - SIZE/2, y_p + h_p/2 - SIZE/2, SIZE, SIZE);
			g.fillRect(x_p +  w_p/2 - SIZE/2, y_p + h_p - SIZE/2, SIZE, SIZE);
			g.fillRect(x_p + w_p - SIZE/2, y_p + h_p/2 - SIZE/2, SIZE, SIZE);
			g.fillRect(x_p - SIZE/2, y_p - SIZE/2, SIZE, SIZE);
			g.fillRect(x_p + w_p - SIZE/2, y_p - SIZE/2, SIZE, SIZE);
			g.fillRect(x_p - SIZE/2, y_p + h_p - SIZE/2, SIZE, SIZE);
			g.fillRect(x_p + w_p - SIZE/2, y_p + h_p - SIZE/2, SIZE, SIZE);
		}
	}

	
	//　選択されているかどうかを返す
	public boolean getSelected() {
		return isSelected;
	}
	
	// その図形を選択状態にするかどうかの処理
	public void setSelected(boolean isSelected) {
		this.isSelected = isSelected;
	}
	
	// 影をつけるかどうかの処理
	public void setShade(boolean shade) {
		this.shade = shade;
	}
		
	// 影をつけているかどうかを返す
	public boolean getShade() {
		return this.shade;
	}
	
	// 範囲選択
	public boolean intersect(int x, int y, int w, int h) {
		return region.intersects(x, y, w, h);
	}
	
	// 包合判定用のメソッド
	public boolean contains(int x, int y) {
		// MyDrawingを継承する子クラス内でそれぞれ定義する。
		return region.contains(x,y);
	}
	
	public void setRegion() {
		// MyDrawingを継承する子クラス内でそれぞれ定義する。
		region = new Rectangle(x,y,w,h);
	}
	
	
	// 複製用メソッド
	public MyDrawing clone() {
		MyDrawing cloned = new MyDrawing();
		try {
			cloned = (MyDrawing) super.clone();
		}catch (Exception e){
			//e.printStackTrace();
		}
		return cloned;
	}
	
	
	
	// オブジェクトの移動
	public void move(int dx, int dy) {
		this.x += dx;
		this.y += dy;
		this.setRegion();
	}
	
	// オブジェクトの位置の変更
	public void setLocation(int x, int y) {
		this.x = x;
		this.y = y;
		this.setRegion();
	}
	
	// オブジェクトのサイズを変更
	public void setSize(int w, int h) {
		this.w = w;
		this.h = h;
		this.setRegion();
	}
	
	// オブジェクトのX位置を取得
	public int getX() {
		return this.x;
	}
	
	// オブジェクトのY位置を取得
	public int getY() {
		return this.y;
		}
		
	// オブジェクトの幅を取得
	public int getW() {
		return this.w;
		}
	
	// オブジェクトの高さを取得
	public int getH() {
		return this.h;
	}
	
	// オブジェクトの線の色を変更
	public void setLineColor(Color lineColor) {
		this.lineColor = lineColor;
	}
	
	// オブジェクトの塗色を変更
	public void setFillColor(Color fillColor) {
		this.fillColor = fillColor;
	}
	
	// オブジェクトの線の色を取得
	public Color getLineColor() {
		return this.lineColor;
	}
	
	// オブジェクトの塗色を取得
	public Color getFillColor() {
		return this.fillColor;
	}
	
	// オブジェクトの透明度を変更
	public void setAlpha(int alpha) {
		Color fc = getFillColor();
		int R = fc.getRed();
		int G = fc.getGreen();
		int B = fc.getBlue();
		
		setFillColor(new Color(R, G, B, alpha));
		
		Color lc = this.lineColor;
		R = lc.getRed();
		G = lc.getGreen();
		B = lc.getBlue();
		
		setLineColor(new Color(R, G, B, alpha));
	}
	
	// オブジェクトの透明度を取得
	public int getAlpha() {
		Color fc = getFillColor();
		int alpha = fc.getAlpha();
		return alpha;
	}
	
	// オブジェクトの線の太さを変更
	public void setLineWidth(int lineWidth) {
		this.lineWidth = lineWidth;
	}
	
	// オブジェクトの線の太さを取得
	public int getLineWidth() {
		return this.lineWidth;
	}
	
	// オブジェクトの線種を変更
	public void setDashed(boolean b) {
		isDashed = b;
	}
	
	// オブジェクトの線種を取得
	public boolean getDashed() {
		return isDashed;
	}
}
