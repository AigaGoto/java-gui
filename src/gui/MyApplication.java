package gui;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class MyApplication extends JFrame {
	
	StateManager stateManager;
	MyCanvas canvas;
	KeyManager km;
	
	LineColorLabel lcl;
	FillColorLabel fcl;
	
	public MyApplication() {
		super("My Paint Application");

		canvas = new MyCanvas();
		stateManager = new StateManager(canvas);
		km = new KeyManager(stateManager);

		
		
		JPanel jp1 = new JPanel();
		jp1.setLayout(new GridLayout(2, 1, 10, 5));
		
		JPanel jp2 = new JPanel();
		jp2.setLayout(new GridLayout(2, 2, 10, 5));
		
		JPanel jp3 = new JPanel();
		jp3.setLayout(new GridLayout(2, 1, 10, 5));
		
		JPanel jp4 = new JPanel();
		jp4.setLayout(new GridLayout(2, 1, 10, 5));
		
		JPanel jp5 = new JPanel();
		jp5.setLayout(new GridLayout(2, 1, 10, 5));
		
		JPanel jp6 = new JPanel();
		jp6.setLayout(new GridLayout(2, 1, 10, 5));
		
		// 選択ボタン
		SelectButton selectButton = new SelectButton(stateManager);
		jp1.add(selectButton);
		
		// 削除ボタン
		DeleteButton deleteButton = new DeleteButton(stateManager);
		jp1.add(deleteButton);
	
		
		// 長方形ボタン
		RectButton rectButton = new RectButton(stateManager);
		jp2.add(rectButton);
		
		// 楕円ボタン
		OvalButton ovalButton = new OvalButton(stateManager);
		jp2.add(ovalButton);
		
		// 11角形ボタン
		HendeButton hendeButton = new HendeButton(stateManager);
		jp2.add(hendeButton);
		
		// 3角形ボタン
		TriButton triButton = new TriButton(stateManager);
		jp2.add(triButton);
		
		
		// 影をつけるかどうかのチェックボックスの設置
		ShadeCheckBox shadeCheckBox = new ShadeCheckBox(stateManager);
		jp3.add(shadeCheckBox);
		
		// 破線にするかどうかのチェックボックスの設置
		DashCheckBox dashCheckBox = new DashCheckBox(stateManager);
		jp3.add(dashCheckBox);
		
		/*
		// 線の太さを変更するテキストボックスを設置
		JTextField lineWidthTextBox = new LineWidthTextBox();
		jp.add(lineWidthTextBox);
		// テキストボックスの内容をセットするボタンを設置
		GetButton getButton = new GetButton(stateManager, lineWidthTextBox);
		jp.add(getButton);
		*/
		
		// Label　：　線の太さ
		JLabel lineWidth = new JLabel("Line Width");
		jp4.add(lineWidth);
		
		// 線の太さを変更するコンボボックス
		String[] lw_data = {"1", "2", "4", "8"};
		LineWidthComboBox lw = new LineWidthComboBox(lw_data, stateManager);
		jp4.add(lw);
		
		
		// Label　：　透明度
		JLabel transparent = new JLabel("Transparency");
		jp5.add(transparent);
		
		// 図形の透明度を変更するコンボボックス
		String[] tp_data = {"10","20","30","40","50","60","70","80","90","100"};
		AlphaComboBox tp = new AlphaComboBox(tp_data, stateManager);
		jp5.add(tp);
		
		
		// 線の色を表示するラベル
		lcl = new LineColorLabel();
		jp6.add(lcl);
		
		// 線の色を表示するラベル
		fcl = new FillColorLabel();
		jp6.add(fcl);
		
		
		// 上側のパネルを合体
		JPanel jpjp = new JPanel();
		jpjp.setLayout(new FlowLayout(FlowLayout.CENTER, 50, 5));
		
		jpjp.add(jp1);
		jpjp.add(jp2);
		jpjp.add(jp3);
		jpjp.add(jp4);
		jpjp.add(jp5);
		jpjp.add(jp6);
		
		
		// ユーザ画面の設置
		MyMenubar menuBar = new MyMenubar(this);
		setJMenuBar(menuBar);
		
		getContentPane().setLayout(new BorderLayout());
		getContentPane().add(jpjp, BorderLayout.NORTH);
		getContentPane().add(canvas, BorderLayout.CENTER);
		
		
		canvas.addMouseListener(new MouseAdapter() {
			// 現在の状態のmouseDown　処理の呼び出し
			public void mousePressed(MouseEvent e) {
				stateManager.mouseDown(e.getX(), e.getY());
				canvas.requestFocusInWindow();
			}
			
			// 現在の状態のmouseUp　処理の呼び出し
			public void mouseReleased(MouseEvent e) {
				stateManager.mouseUp(e.getX(), e.getY());
				canvas.requestFocusInWindow();
			}
			
		});
		

		// ドラッグの処理を記述
		canvas.addMouseMotionListener(new MouseMotionAdapter() {
			public void mouseDragged(MouseEvent e) {
				stateManager.mouseDrag(e.getX(), e.getY());
				canvas.requestFocusInWindow();
			}
		});
		
		// キーボードの処理を記述
		canvas.addKeyListener(new KeyAdapter() {
			
			public void keyPressed(KeyEvent e) {
				km.keyPressed(e.getKeyCode(), e.getModifiersEx());
			}
			
		});
		
		canvas.setFocusable(true);

		//　ウィンドウを閉じたときの処理
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
	}

	// ウィンドウサイズを設定
	public Dimension getPreferredSize() {
		return new Dimension(1200, 600);
	}

	public static void main(String[] args) {
		MyApplication app = new MyApplication();
		app.pack();
		app.setVisible(true);
	}
}
