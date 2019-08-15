package com.captor.ui;

import java.awt.Toolkit;

import javax.swing.JFrame;

/**
 * 截图工具窗口
 * 
 * @author ordinary-student
 *
 */
public class ScreenCaptureFrame extends KFrame
{
	private static final long serialVersionUID = -8527318153636964518L;

	/*
	 * 构造方法
	 */
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
}
