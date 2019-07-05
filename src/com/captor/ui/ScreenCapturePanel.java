package com.captor.ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;

import com.captor.util.ScreenCaptureUtil;

/**
 * ��ͼ�������
 * 
 * @author ordinary-student
 *
 */
public class ScreenCapturePanel extends KPanel
{
	private static final long serialVersionUID = -4737845854985832976L;
	// ��ͼ״̬
	public static final int CAPTURER_START = 0;
	public static final int CAPTURER_PROGRESS = 1;
	public static final int CAPTURER_FINISHED = 2;
	public static final int CAPTURER_EXIT = 3;

	public int state = CAPTURER_START;
	public int begin_x, begin_y, end_x, end_y;

	public BufferedImage fullScreenImage;
	public BufferedImage subScreenImage;

	public JFrame frame;

	/*
	 * ���췽��
	 */
	public ScreenCapturePanel(JFrame frame)
	{
		this.frame = frame;
		// ��ȡȫ��ͼ��
		fullScreenImage = ScreenCaptureUtil.getFullScreenCapture();
	}

	@Override
	public void paint(Graphics g)
	{
		super.paint(g);

		// ������ȫ��ͼ��
		g.drawImage(fullScreenImage, 0, 0, this);

		// ��ͼ��
		if (state == CAPTURER_PROGRESS)
		{
			// ���ú�ɫ
			g.setColor(Color.red);
			// ������
			g.drawRect(begin_x, begin_y, end_x - begin_x, end_y - begin_y);

			// ���ú�ɫ
			g.setColor(Color.black);
			// ������
			g.fillRect(begin_x, begin_y - 30, 120, 30);

			// ���ð�ɫ
			g.setColor(Color.white);
			// ��������
			g.setFont(new Font("΢���ź�", Font.PLAIN, 18));
			// ������
			g.drawString((Math.abs(end_x - begin_x) + " x " + Math.abs(end_y - begin_y)), begin_x + 5, begin_y - 10);

		}

	}

	@Override
	public void mouseClicked(MouseEvent e)
	{
		// �Ҽ���ѡ��ͼ��������˳�
		if (e.getButton() == MouseEvent.BUTTON3)
		{
			// ��������״̬���������ѡ��ͼ����
			if (state == CAPTURER_FINISHED)
			{
				// ����
				this.repaint();
				state = CAPTURER_START;

			} else if (state == CAPTURER_START)
			{
				// ����ǿ�ʼ״̬������Ϊ�˳�״̬
				state = CAPTURER_EXIT;

			} else if (state == CAPTURER_EXIT)
			{
				// ������˳�״̬�����˳�

				frame.setVisible(true);
				// ������ǰ
				frame.toFront();
			}
		}

	}

	@Override
	public void mousePressed(MouseEvent e)
	{
		// ������
		if (e.getButton() == MouseEvent.BUTTON1)
		{
			// ��ʼ��ͼ
			if ((state == CAPTURER_START) || (state == CAPTURER_EXIT))
			{
				frame.setVisible(false);
				// ��ͼ��
				state = CAPTURER_PROGRESS;
				// ��¼��ʼλ��
				begin_x = e.getX();
				begin_y = e.getY();
			}
		}
	}

	@Override
	public void mouseDragged(MouseEvent e)
	{
		// ��ͼ��
		if (state == CAPTURER_PROGRESS)
		{
			// ��¼����λ��
			end_x = e.getX();
			end_y = e.getY();
			// ����
			this.repaint();
		}

	}

	@Override
	public void mouseReleased(MouseEvent e)
	{
		// ������
		if (e.getButton() == MouseEvent.BUTTON1)
		{
			// ��ͼ��
			if (state == CAPTURER_PROGRESS)
			{
				// ��¼����λ��
				end_x = e.getX();
				end_y = e.getY();
				// ��ȡ��ͼ
				subScreenImage = fullScreenImage.getSubimage(begin_x, begin_y, end_x - begin_x, end_y - begin_y);
				// ��ͼ���
				state = CAPTURER_FINISHED;
			}
		}
	}

	@Override
	public void keyReleased(KeyEvent e)
	{
		// ���»س���
		if (e.getKeyCode() == KeyEvent.VK_ENTER)
		{
			// ��ͼ���״̬
			if (state == CAPTURER_FINISHED)
			{
				// �����ͼ
				ScreenCaptureUtil.save(subScreenImage);
				// ����
				this.repaint();
				// ��ѡ����
				state = CAPTURER_START;
				frame.setVisible(true);
			}
		}
	}

}
