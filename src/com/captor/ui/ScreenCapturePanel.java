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
 * 截图工具面板
 * 
 * @author ordinary-student
 *
 */
public class ScreenCapturePanel extends KPanel
{
	private static final long serialVersionUID = -4737845854985832976L;
	// 截图状态
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
	 * 构造方法
	 */
	public ScreenCapturePanel(JFrame frame)
	{
		this.frame = frame;
		// 获取全屏图像
		fullScreenImage = ScreenCaptureUtil.getFullScreenCapture();
	}

	@Override
	public void paint(Graphics g)
	{
		super.paint(g);

		// 面板绘制全屏图像
		g.drawImage(fullScreenImage, 0, 0, this);

		// 截图中
		if (state == CAPTURER_PROGRESS)
		{
			// 设置红色
			g.setColor(Color.red);
			// 画矩形
			g.drawRect(begin_x, begin_y, end_x - begin_x, end_y - begin_y);

			// 设置黑色
			g.setColor(Color.black);
			// 填充矩形
			g.fillRect(begin_x, begin_y - 30, 120, 30);

			// 设置白色
			g.setColor(Color.white);
			// 设置字体
			g.setFont(new Font("微软雅黑", Font.PLAIN, 18));
			// 画数字
			g.drawString((Math.abs(end_x - begin_x) + " x " + Math.abs(end_y - begin_y)), begin_x + 5, begin_y - 10);

		}

	}

	@Override
	public void mouseClicked(MouseEvent e)
	{
		// 右键重选截图区域或者退出
		if (e.getButton() == MouseEvent.BUTTON3)
		{
			// 如果是完成状态，则可以重选截图区域
			if (state == CAPTURER_FINISHED)
			{
				// 绘制
				this.repaint();
				state = CAPTURER_START;

			} else if (state == CAPTURER_START)
			{
				// 如果是开始状态，设置为退出状态
				state = CAPTURER_EXIT;

			} else if (state == CAPTURER_EXIT)
			{
				// 如果是退出状态，则退出

				frame.setVisible(true);
				// 窗口最前
				frame.toFront();
			}
		}

	}

	@Override
	public void mousePressed(MouseEvent e)
	{
		// 鼠标左键
		if (e.getButton() == MouseEvent.BUTTON1)
		{
			// 开始截图
			if ((state == CAPTURER_START) || (state == CAPTURER_EXIT))
			{
				frame.setVisible(false);
				// 截图中
				state = CAPTURER_PROGRESS;
				// 记录开始位置
				begin_x = e.getX();
				begin_y = e.getY();
			}
		}
	}

	@Override
	public void mouseDragged(MouseEvent e)
	{
		// 截图中
		if (state == CAPTURER_PROGRESS)
		{
			// 记录结束位置
			end_x = e.getX();
			end_y = e.getY();
			// 绘制
			this.repaint();
		}

	}

	@Override
	public void mouseReleased(MouseEvent e)
	{
		// 鼠标左键
		if (e.getButton() == MouseEvent.BUTTON1)
		{
			// 截图中
			if (state == CAPTURER_PROGRESS)
			{
				// 记录结束位置
				end_x = e.getX();
				end_y = e.getY();
				// 获取截图
				subScreenImage = fullScreenImage.getSubimage(begin_x, begin_y, end_x - begin_x, end_y - begin_y);
				// 截图完成
				state = CAPTURER_FINISHED;
			}
		}
	}

	@Override
	public void keyReleased(KeyEvent e)
	{
		// 按下回车键
		if (e.getKeyCode() == KeyEvent.VK_ENTER)
		{
			// 截图完成状态
			if (state == CAPTURER_FINISHED)
			{
				// 保存截图
				ScreenCaptureUtil.save(subScreenImage);
				// 绘制
				this.repaint();
				// 重选区域
				state = CAPTURER_START;
				frame.setVisible(true);
			}
		}
	}

}
