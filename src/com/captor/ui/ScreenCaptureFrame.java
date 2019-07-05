package com.captor.ui;

import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.UIManager;

/**
 * 截图工具窗口
 * 
 * @author ordinary-student
 *
 */
public class ScreenCaptureFrame extends KFrame
{
	private static final long serialVersionUID = -8527318153636964518L;

	public ScreenCaptureFrame(JFrame frame)
	{
		// 初始化界面
		initUI(frame);
	}

	/*
	 * 初始化界面
	 */
	private void initUI(JFrame frame)
	{
		// 不装饰
		setUndecorated(true);
		// 设置大小
		setSize((int) Toolkit.getDefaultToolkit().getScreenSize().getWidth(),
				(int) Toolkit.getDefaultToolkit().getScreenSize().getHeight());
		// 隐藏任务栏图标
		setType(Type.UTILITY);
		// 设置关闭方式
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		// 创建面板
		ScreenCapturePanel panel = new ScreenCapturePanel(frame);
		// 添加面板
		add(panel);
		// 添加监听
		addMouseListener(panel);
		addMouseMotionListener(panel);
		addKeyListener(panel);

		// 不可视
		setVisible(false);
	}

	public static void main(String[] args)
	{
		try
		{
			// 设置本地系统默认的窗口风格样式
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e)
		{
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "加载本地系统窗口样式失败！");
		} finally
		{
			// 窗口
			JFrame frame = new JFrame("截图工具");
			frame.setBounds(500, 400, 300, 100);
			frame.setResizable(false);
			frame.setLayout(null);
			frame.setDefaultCloseOperation(EXIT_ON_CLOSE);

			// 按钮
			JButton button = new JButton("截图");
			button.setBounds(50, 20, 150, 40);
			frame.add(button);

			button.addActionListener(new ActionListener()
			{
				@Override
				public void actionPerformed(ActionEvent e)
				{
					frame.setVisible(false);
					// 创建截屏窗口
					ScreenCaptureFrame scf = new ScreenCaptureFrame(frame);
					scf.setVisible(true);
					// 窗口最前
					scf.toFront();
				}
			});

			frame.setVisible(true);
		}
	}
}
