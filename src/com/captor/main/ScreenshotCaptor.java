package com.captor.main;

import java.awt.KeyEventPostProcessor;
import java.awt.KeyboardFocusManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.UIManager;

import com.captor.ui.ScreenCaptureFrame;

/*
 * ��ͼ����
 */
public class ScreenshotCaptor
{

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
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

			// ��ȡ��ǰ�����¼��Ĺ�����
			KeyboardFocusManager manager = KeyboardFocusManager.getCurrentKeyboardFocusManager();
			// Ȼ��Ϊ���������һ���µļ����¼������ߡ�
			manager.addKeyEventPostProcessor(new KeyEventPostProcessor()
			{
				private boolean flag = false;

				@Override
				public boolean postProcessKeyEvent(KeyEvent e)
				{
					flag = !flag;
					if (flag)
					{
						// ֻһ����Ч
						if (e.getKeyCode() == KeyEvent.VK_F1)
						{
							// ����F1��
							JOptionPane.showMessageDialog(frame,
									"�����ͼ��ť�󣬰�ס��������קѡ�����򣬴�ʱ\r\n1.���»س�����ɽ�ͼ����ͼ���������档\r\n2.��������Ҽ�����ȡ����������ѡ������\r\n��ɽ�ͼ������ٴ�ѡ�������򣬿�ʼ�µĽ�ͼ��",
									"����", JOptionPane.INFORMATION_MESSAGE);
						}
					}

					// ����ESC�������رճ���
					if (e.getKeyCode() == KeyEvent.VK_ESCAPE)
					{
						System.exit(0);
					}

					return true;
				}
			});

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
