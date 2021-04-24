package gui;

import java.awt.*;
import java.io.Serializable;

public class MyDrawing implements Cloneable,Serializable{
	private int x, y, w, h; //X���W�A�@Y���W�A�@���A�@����
	private Color lineColor, fillColor; // ���̐F�A�@�h�F
	private int lineWidth; // �I�̑���
	private boolean isDashed = false; // ���� 
	
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

	
	//�@�I������Ă��邩�ǂ�����Ԃ�
	public boolean getSelected() {
		return isSelected;
	}
	
	// ���̐}�`��I����Ԃɂ��邩�ǂ����̏���
	public void setSelected(boolean isSelected) {
		this.isSelected = isSelected;
	}
	
	// �e�����邩�ǂ����̏���
	public void setShade(boolean shade) {
		this.shade = shade;
	}
		
	// �e�����Ă��邩�ǂ�����Ԃ�
	public boolean getShade() {
		return this.shade;
	}
	
	// �͈͑I��
	public boolean intersect(int x, int y, int w, int h) {
		return region.intersects(x, y, w, h);
	}
	
	// �����p�̃��\�b�h
	public boolean contains(int x, int y) {
		// MyDrawing���p������q�N���X���ł��ꂼ���`����B
		return region.contains(x,y);
	}
	
	public void setRegion() {
		// MyDrawing���p������q�N���X���ł��ꂼ���`����B
		region = new Rectangle(x,y,w,h);
	}
	
	
	// �����p���\�b�h
	public MyDrawing clone() {
		MyDrawing cloned = new MyDrawing();
		try {
			cloned = (MyDrawing) super.clone();
		}catch (Exception e){
			//e.printStackTrace();
		}
		return cloned;
	}
	
	
	
	// �I�u�W�F�N�g�̈ړ�
	public void move(int dx, int dy) {
		this.x += dx;
		this.y += dy;
		this.setRegion();
	}
	
	// �I�u�W�F�N�g�̈ʒu�̕ύX
	public void setLocation(int x, int y) {
		this.x = x;
		this.y = y;
		this.setRegion();
	}
	
	// �I�u�W�F�N�g�̃T�C�Y��ύX
	public void setSize(int w, int h) {
		this.w = w;
		this.h = h;
		this.setRegion();
	}
	
	// �I�u�W�F�N�g��X�ʒu���擾
	public int getX() {
		return this.x;
	}
	
	// �I�u�W�F�N�g��Y�ʒu���擾
	public int getY() {
		return this.y;
		}
		
	// �I�u�W�F�N�g�̕����擾
	public int getW() {
		return this.w;
		}
	
	// �I�u�W�F�N�g�̍������擾
	public int getH() {
		return this.h;
	}
	
	// �I�u�W�F�N�g�̐��̐F��ύX
	public void setLineColor(Color lineColor) {
		this.lineColor = lineColor;
	}
	
	// �I�u�W�F�N�g�̓h�F��ύX
	public void setFillColor(Color fillColor) {
		this.fillColor = fillColor;
	}
	
	// �I�u�W�F�N�g�̐��̐F���擾
	public Color getLineColor() {
		return this.lineColor;
	}
	
	// �I�u�W�F�N�g�̓h�F���擾
	public Color getFillColor() {
		return this.fillColor;
	}
	
	// �I�u�W�F�N�g�̓����x��ύX
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
	
	// �I�u�W�F�N�g�̓����x���擾
	public int getAlpha() {
		Color fc = getFillColor();
		int alpha = fc.getAlpha();
		return alpha;
	}
	
	// �I�u�W�F�N�g�̐��̑�����ύX
	public void setLineWidth(int lineWidth) {
		this.lineWidth = lineWidth;
	}
	
	// �I�u�W�F�N�g�̐��̑������擾
	public int getLineWidth() {
		return this.lineWidth;
	}
	
	// �I�u�W�F�N�g�̐����ύX
	public void setDashed(boolean b) {
		isDashed = b;
	}
	
	// �I�u�W�F�N�g�̐�����擾
	public boolean getDashed() {
		return isDashed;
	}
}
