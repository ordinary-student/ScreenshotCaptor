package com.captor.ui;

import java.awt.Toolkit;

import javax.swing.JFrame;

/**
 * ��ͼ���ߴ���
 * 
 * @author ordinary-student
 *
 */
public class ScreenCaptureFrame extends KFrame
{
	private static final long serialVersionUID = -8527318153636964518L;

	/*
	 * ���췽��
	 */
	public ScreenCaptureFrame(JFrame frame)
	{
		// ��ʼ������
		initUI(frame);
	}

	/*
	 * ��ʼ������
	 */
	private void initUI(JFrame frame)
	{
		// ��װ��
		setUndecorated(true);
		// ���ô�С
		setSize((int) Toolkit.getDefaultToolkit().getScreenSize().getWidth(),
				(int) Toolkit.getDefaultToolkit().getScreenSize().getHeight());
		// ����������ͼ��
		setType(Type.UTILITY);
		// ���ùرշ�ʽ
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		// �������
		ScreenCapturePanel panel = new ScreenCapturePanel(frame);
		// ������
		add(panel);
		// ��Ӽ���
		addMouseListener(panel);
		addMouseMotionListener(panel);
		addKeyListener(panel);

		// ������
		setVisible(false);
	}
}
