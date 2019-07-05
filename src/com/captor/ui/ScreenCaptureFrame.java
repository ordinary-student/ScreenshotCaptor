package com.captor.ui;

import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.UIManager;

/**
 * ��ͼ���ߴ���
 * 
 * @author ordinary-student
 *
 */
public class ScreenCaptureFrame extends KFrame
{
	private static final long serialVersionUID = -8527318153636964518L;

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

	public static void main(String[] args)
	{
		try
		{
			// ���ñ���ϵͳĬ�ϵĴ��ڷ����ʽ
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e)
		{
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "���ر���ϵͳ������ʽʧ�ܣ�");
		} finally
		{
			// ����
			JFrame frame = new JFrame("��ͼ����");
			frame.setBounds(500, 400, 300, 100);
			frame.setResizable(false);
			frame.setLayout(null);
			frame.setDefaultCloseOperation(EXIT_ON_CLOSE);

			// ��ť
			JButton button = new JButton("��ͼ");
			button.setBounds(50, 20, 150, 40);
			frame.add(button);

			button.addActionListener(new ActionListener()
			{
				@Override
				public void actionPerformed(ActionEvent e)
				{
					frame.setVisible(false);
					// ������������
					ScreenCaptureFrame scf = new ScreenCaptureFrame(frame);
					scf.setVisible(true);
					// ������ǰ
					scf.toFront();
				}
			});

			frame.setVisible(true);
		}
	}
}
