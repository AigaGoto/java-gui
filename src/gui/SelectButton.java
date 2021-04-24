package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class SelectButton extends JButton implements State{
	
	StateManager stateManager;
	boolean select = false;
	MyDrawing selectD;
	
	int mouseDown_x;
	int mouseDown_y;
	
	public SelectButton(StateManager stateManager) {
		super("Select");
		
		addActionListener(new SelectListener());
		
		this.stateManager = stateManager;
	}

	
	class SelectListener implements ActionListener{
			public void actionPerformed(ActionEvent e) {
				stateManager.setState(new SelectButton(stateManager));
		}
	}

	public void mouseDown(int x, int y) {
		mouseDown_x = x;
		mouseDown_y = y;
		select = stateManager.mediator.returnSelect(x, y);
		
		// mouseDownした位置に、図形がない時、複数選択の準備
		if (!select) {
			stateManager.mediator.resetSelected();
			stateManager.mediator.drawings.add(new MyRectangle(x, y, 0, 0, Color.black, new Color(255,255,255,100) , 1));
			selectD = stateManager.mediator.getLastDrawing();
			selectD.setDashed(true);
		} 
		// mouseDownした位置に図形があり、選択されている図形がないとき、その図形を選択状態にする
		else if (!stateManager.mediator.isMove(x, y)) {
			stateManager.mediator.setSelected(x, y);
		} 
		// mouseDownした位置に図形があり、選択されている図形があるとき、移動の準備  
		else {
			// mouseDown時には何もしない。
		}
	}


	public void mouseUp(int x, int y) {		
		if (!(select)) {
			
			stateManager.mediator.removeDrawing(selectD);
			selectD = null;
			
		}
	}


	
	public void mouseDrag(int x, int y) {
		if (!select) {
			selectD.setSize(x - selectD.getX(), y - selectD.getY());
			int s_x = selectD.getX(); // selectDrawing X
			int s_y = selectD.getY();
			int s_w = selectD.getW();
			int s_h = selectD.getH();
			
			if (s_w < 0) {
				s_x += s_w;
				s_w *= -1;
			}
			if (s_h < 0) {
				s_y += s_h;
				s_h *= -1;
			}
			
			stateManager.mediator.setSelected2(s_x, s_y, s_w, s_h);
		}else {
			stateManager.mediator.move(x - mouseDown_x, y - mouseDown_y);
			mouseDown_x = x;
			mouseDown_y = y;
		}
	}
	
}


