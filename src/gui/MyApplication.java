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
		
		// �I���{�^��
		SelectButton selectButton = new SelectButton(stateManager);
		jp1.add(selectButton);
		
		// �폜�{�^��
		DeleteButton deleteButton = new DeleteButton(stateManager);
		jp1.add(deleteButton);
	
		
		// �����`�{�^��
		RectButton rectButton = new RectButton(stateManager);
		jp2.add(rectButton);
		
		// �ȉ~�{�^��
		OvalButton ovalButton = new OvalButton(stateManager);
		jp2.add(ovalButton);
		
		// 11�p�`�{�^��
		HendeButton hendeButton = new HendeButton(stateManager);
		jp2.add(hendeButton);
		
		// 3�p�`�{�^��
		TriButton triButton = new TriButton(stateManager);
		jp2.add(triButton);
		
		
		// �e�����邩�ǂ����̃`�F�b�N�{�b�N�X�̐ݒu
		ShadeCheckBox shadeCheckBox = new ShadeCheckBox(stateManager);
		jp3.add(shadeCheckBox);
		
		// �j���ɂ��邩�ǂ����̃`�F�b�N�{�b�N�X�̐ݒu
		DashCheckBox dashCheckBox = new DashCheckBox(stateManager);
		jp3.add(dashCheckBox);
		
		/*
		// ���̑�����ύX����e�L�X�g�{�b�N�X��ݒu
		JTextField lineWidthTextBox = new LineWidthTextBox();
		jp.add(lineWidthTextBox);
		// �e�L�X�g�{�b�N�X�̓��e���Z�b�g����{�^����ݒu
		GetButton getButton = new GetButton(stateManager, lineWidthTextBox);
		jp.add(getButton);
		*/
		
		// Label�@�F�@���̑���
		JLabel lineWidth = new JLabel("Line Width");
		jp4.add(lineWidth);
		
		// ���̑�����ύX����R���{�{�b�N�X
		String[] lw_data = {"1", "2", "4", "8"};
		LineWidthComboBox lw = new LineWidthComboBox(lw_data, stateManager);
		jp4.add(lw);
		
		
		// Label�@�F�@�����x
		JLabel transparent = new JLabel("Transparency");
		jp5.add(transparent);
		
		// �}�`�̓����x��ύX����R���{�{�b�N�X
		String[] tp_data = {"10","20","30","40","50","60","70","80","90","100"};
		AlphaComboBox tp = new AlphaComboBox(tp_data, stateManager);
		jp5.add(tp);
		
		
		// ���̐F��\�����郉�x��
		lcl = new LineColorLabel();
		jp6.add(lcl);
		
		// ���̐F��\�����郉�x��
		fcl = new FillColorLabel();
		jp6.add(fcl);
		
		
		// �㑤�̃p�l��������
		JPanel jpjp = new JPanel();
		jpjp.setLayout(new FlowLayout(FlowLayout.CENTER, 50, 5));
		
		jpjp.add(jp1);
		jpjp.add(jp2);
		jpjp.add(jp3);
		jpjp.add(jp4);
		jpjp.add(jp5);
		jpjp.add(jp6);
		
		
		// ���[�U��ʂ̐ݒu
		MyMenubar menuBar = new MyMenubar(this);
		setJMenuBar(menuBar);
		
		getContentPane().setLayout(new BorderLayout());
		getContentPane().add(jpjp, BorderLayout.NORTH);
		getContentPane().add(canvas, BorderLayout.CENTER);
		
		
		canvas.addMouseListener(new MouseAdapter() {
			// ���݂̏�Ԃ�mouseDown�@�����̌Ăяo��
			public void mousePressed(MouseEvent e) {
				stateManager.mouseDown(e.getX(), e.getY());
				canvas.requestFocusInWindow();
			}
			
			// ���݂̏�Ԃ�mouseUp�@�����̌Ăяo��
			public void mouseReleased(MouseEvent e) {
				stateManager.mouseUp(e.getX(), e.getY());
				canvas.requestFocusInWindow();
			}
			
		});
		

		// �h���b�O�̏������L�q
		canvas.addMouseMotionListener(new MouseMotionAdapter() {
			public void mouseDragged(MouseEvent e) {
				stateManager.mouseDrag(e.getX(), e.getY());
				canvas.requestFocusInWindow();
			}
		});
		
		// �L�[�{�[�h�̏������L�q
		canvas.addKeyListener(new KeyAdapter() {
			
			public void keyPressed(KeyEvent e) {
				km.keyPressed(e.getKeyCode(), e.getModifiersEx());
			}
			
		});
		
		canvas.setFocusable(true);

		//�@�E�B���h�E������Ƃ��̏���
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
	}

	// �E�B���h�E�T�C�Y��ݒ�
	public Dimension getPreferredSize() {
		return new Dimension(1200, 600);
	}

	public static void main(String[] args) {
		MyApplication app = new MyApplication();
		app.pack();
		app.setVisible(true);
	}
}
