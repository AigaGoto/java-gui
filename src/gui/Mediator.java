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
	Vector<MyDrawing> buffer = new Vector<>(); // Cut, Copy バッファ
	
	int add_x, add_y; // pasteをずらす値
	
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
	
	// 図形を移動するメソッド
	public void move(int dx, int dy) {
		for (MyDrawing d : selectedDrawings) {
			d.move(dx, dy);
		}
	}
	
	public void repaint() {
		canvas.repaint();
	}
	
	// 選択する図形があるかどうかのメソッド
	public boolean returnSelect(int x, int y) {
		boolean select = false;
		for (MyDrawing draw : drawings) {
			if (draw.contains(x, y)) {
				select = true;
			}
		}
		
		return select;
	}
	
	// 単選択用のメソッド
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
	
	// 範囲選択用のメソッド
	public void setSelected2(int x, int y, int w, int h) {
		resetSelected();

		// 選択用の四角形を含まないように、size()-1までにした。		
		for (int i = 0; i < drawings.size() - 1; i++) {
			MyDrawing draw = drawings.get(i);
			if (draw.intersect(x, y, w, h)) {
				setSelectedDrawing(draw);
				draw.setSelected(true);
			}
		} 
		
		
		repaint();
	}
	
	
	// 図形を動かすかを返すメソッド
	public boolean isMove (int x, int y) {
		boolean isMove = false;
		
		for (MyDrawing draw : selectedDrawings) 
			if (draw.contains(x,  y))
				isMove = true;
		
		return isMove;
	}
	
	 // 最後に描画された図形を取得
	public MyDrawing getLastDrawing() {
		return this.drawings.lastElement();
	}
	
	// 全図形の選択状態を解除 ・ selectedDrawingsを空にする
	public void resetSelected() {
		for (MyDrawing draw : drawings) {
			draw.setSelected(false);
		}
		
		if (!selectedDrawings.isEmpty())
			selectedDrawings.clear();
		
	}
	
	// 選択した図形の線の色を変更
	public void setLineColor(Color color) {

		for (MyDrawing d : selectedDrawings) {
			d.setLineColor(color);
			repaint();
		}
		
	}
	
	// 選択した図形の塗色を変更
	public void setFillColor(Color color) {
		
		for (MyDrawing d : selectedDrawings) {
			d.setFillColor(color);
			repaint();
		}
		
	}
	
	
	// 選択した図形の線の太さを変更
	public void setLineWidth(int lineWidth) {

		for (MyDrawing d : selectedDrawings) {
			d.setLineWidth(lineWidth);
			repaint();
		}
		
	}
	
	// 選択した図形の透明度を変更
	public void setAlpha(int alpha) {
		
		for(MyDrawing d : selectedDrawings) {
			d.setAlpha(alpha);
		}
		repaint();
		
	}
	
	// 選択した図形に影をつける
	public void setShade(boolean shade) {
		
		for (MyDrawing d : selectedDrawings) {
			d.setShade(shade);
			repaint();
		}
		
	}
	
	// 選択した図形を削除
	public void delete() {
		for (MyDrawing d : selectedDrawings) {
			removeDrawing(d);
		}
		
		repaint();
	}
	
	// 全図形の削除
	public void all_delete() {
		JFrame isDelete = new JFrame();
		int ok = JOptionPane.showConfirmDialog(isDelete, "本当に全ての図形を削除しますか？", "注意", JOptionPane.YES_NO_OPTION);
		
		if (ok == JOptionPane.YES_OPTION) {
			drawings.clear();
			repaint();
		}
	}
	
	// バッファを空にする
	public void clearBuffer() {
		buffer.clear();
	}
	
	// pasteのずらす値のリセット
	public void clearAdd() {
		add_x = 0;
		add_y = 0;
	}
	
	// 図形のコピー
	public void copy(){
		clearBuffer();
		clearAdd();
		
		for (MyDrawing d : selectedDrawings) {
			buffer.add(d.clone());
		}
		
		
	}
	
	// 図形のカット
	public void cut(){
		clearBuffer();
		clearAdd();
		
		for (MyDrawing d : selectedDrawings) {
			buffer.add(d.clone());
			removeDrawing(d);
		}
		
		repaint();
		
	}
	
	// 図形のペースト
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
	

	
	
	// ファイル入力	
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
	
	
	// ファイル出力
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
