package gui;

import java.awt.Color;
import java.awt.event.InputEvent;
import java.io.*;
import java.util.Enumeration;
import java.util.Vector;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class Mediator{
	Vector<MyDrawing> drawings = new Vector<>();
	MyCanvas canvas;
	Vector<MyDrawing> selectedDrawings = new Vector<>();
	Vector<MyDrawing> buffer = new Vector<>(); // Cut, Copy �o�b�t�@
	
	int add_x, add_y; // paste�����炷�l
	
	public Mediator(MyCanvas canvas) {
		this.canvas = canvas;
		drawings = new Vector<MyDrawing>();
	}
	
	public Enumeration<MyDrawing> drawingsElements() {
		return drawings.elements();
	}
	
	public void addDrawing(MyDrawing d) {
		drawings.add(d);
		d.setRegion();
		resetSelected();
	}
	
	public void removeDrawing(MyDrawing d) {
		drawings.remove(d);
		repaint();
	}
	
	public void setSelectedDrawing(MyDrawing d) {
		selectedDrawings.add(d);
	}
	
	public Vector<MyDrawing> getSelectedDrawing() {
		return selectedDrawings;
	}
	
	// �}�`���ړ����郁�\�b�h
	public void move(int dx, int dy) {
		for (MyDrawing d : selectedDrawings) {
			d.move(dx, dy);
		}
	}
	
	public void repaint() {
		canvas.repaint();
	}
	
	// �I������}�`�����邩�ǂ����̃��\�b�h
	public boolean returnSelect(int x, int y) {
		boolean select = false;
		for (MyDrawing draw : drawings) {
			if (draw.contains(x, y)) {
				select = true;
			}
		}
		
		return select;
	}
	
	// �P�I��p�̃��\�b�h
	public void setSelected(int x, int y) {
		resetSelected();
		for (MyDrawing draw : drawings) {
			if (draw.contains(x, y)) {
				resetSelected();
				setSelectedDrawing(draw);
				draw.setSelected(true);
			}
		}
		
		repaint();
	}
	
	// �͈͑I��p�̃��\�b�h
	public void setSelected2(int x, int y, int w, int h) {
		resetSelected();

		// �I��p�̎l�p�`���܂܂Ȃ��悤�ɁAsize()-1�܂łɂ����B		
		for (int i = 0; i < drawings.size() - 1; i++) {
			MyDrawing draw = drawings.get(i);
			if (draw.intersect(x, y, w, h)) {
				setSelectedDrawing(draw);
				draw.setSelected(true);
			}
		} 
		
		
		repaint();
	}
	
	
	// �}�`�𓮂�������Ԃ����\�b�h
	public boolean isMove (int x, int y) {
		boolean isMove = false;
		
		for (MyDrawing draw : selectedDrawings) 
			if (draw.contains(x,  y))
				isMove = true;
		
		return isMove;
	}
	
	 // �Ō�ɕ`�悳�ꂽ�}�`���擾
	public MyDrawing getLastDrawing() {
		return this.drawings.lastElement();
	}
	
	// �S�}�`�̑I����Ԃ����� �E selectedDrawings����ɂ���
	public void resetSelected() {
		for (MyDrawing draw : drawings) {
			draw.setSelected(false);
		}
		
		if (!selectedDrawings.isEmpty())
			selectedDrawings.clear();
		
	}
	
	// �I�������}�`�̐��̐F��ύX
	public void setLineColor(Color color) {

		for (MyDrawing d : selectedDrawings) {
			d.setLineColor(color);
			repaint();
		}
		
	}
	
	// �I�������}�`�̓h�F��ύX
	public void setFillColor(Color color) {
		
		for (MyDrawing d : selectedDrawings) {
			d.setFillColor(color);
			repaint();
		}
		
	}
	
	
	// �I�������}�`�̐��̑�����ύX
	public void setLineWidth(int lineWidth) {

		for (MyDrawing d : selectedDrawings) {
			d.setLineWidth(lineWidth);
			repaint();
		}
		
	}
	
	// �I�������}�`�̓����x��ύX
	public void setAlpha(int alpha) {
		
		for(MyDrawing d : selectedDrawings) {
			d.setAlpha(alpha);
		}
		repaint();
		
	}
	
	// �I�������}�`�ɉe������
	public void setShade(boolean shade) {
		
		for (MyDrawing d : selectedDrawings) {
			d.setShade(shade);
			repaint();
		}
		
	}
	
	// �I�������}�`���폜
	public void delete() {
		for (MyDrawing d : selectedDrawings) {
			removeDrawing(d);
		}
		
		repaint();
	}
	
	// �S�}�`�̍폜
	public void all_delete() {
		JFrame isDelete = new JFrame();
		int ok = JOptionPane.showConfirmDialog(isDelete, "�{���ɑS�Ă̐}�`���폜���܂����H", "����", JOptionPane.YES_NO_OPTION);
		
		if (ok == JOptionPane.YES_OPTION) {
			drawings.clear();
			repaint();
		}
	}
	
	// �o�b�t�@����ɂ���
	public void clearBuffer() {
		buffer.clear();
	}
	
	// paste�̂��炷�l�̃��Z�b�g
	public void clearAdd() {
		add_x = 0;
		add_y = 0;
	}
	
	// �}�`�̃R�s�[
	public void copy(){
		clearBuffer();
		clearAdd();
		
		for (MyDrawing d : selectedDrawings) {
			buffer.add(d.clone());
		}
		
		
	}
	
	// �}�`�̃J�b�g
	public void cut(){
		clearBuffer();
		clearAdd();
		
		for (MyDrawing d : selectedDrawings) {
			buffer.add(d.clone());
			removeDrawing(d);
		}
		
		repaint();
		
	}
	
	// �}�`�̃y�[�X�g
	public void paste() {
		int x,y;
		
		if (buffer.isEmpty()) {
			JFrame error = new JFrame();
			JOptionPane.showMessageDialog(error, "Please clone drawings", "Error", JOptionPane.ERROR_MESSAGE);
		} else {
			add_x += 5;
			add_y += 5;
			for (MyDrawing d : buffer) {
				MyDrawing clone = (MyDrawing)d.clone();
				x = d.getX() + add_x;
				y = d.getY() + add_y;
				clone.setLocation(x, y);
				addDrawing(clone);
				repaint();
			}
		}
		
	}
	

	
	
	// �t�@�C������	
	public void fileIn() {
		try {
			JFileChooser fc = new JFileChooser();
			int returnVal = fc.showOpenDialog(null);
			
			if (returnVal == JFileChooser.APPROVE_OPTION) {
				File file = fc.getSelectedFile();
			
				FileInputStream fin = new FileInputStream(file);
				ObjectInputStream in = new ObjectInputStream(fin);
				
				drawings = (Vector<MyDrawing>) in.readObject();
				fin.close();
				
				repaint();
			}
		} catch(Exception ex) {}
		
	}
	
	
	// �t�@�C���o��
	public void fileOut() {
		try {
			JFileChooser fc = new JFileChooser();
			int returnVal = fc.showSaveDialog(null);
			
			if (returnVal == JFileChooser.APPROVE_OPTION) {
				File file = fc.getSelectedFile();
			
				FileOutputStream fout = new FileOutputStream(file);
				ObjectOutputStream out = new ObjectOutputStream(fout);
			
				out.writeObject(drawings);
				out.flush();
			
				fout.close();
			}
		} catch (Exception ex) {}
		
	}


	
}
