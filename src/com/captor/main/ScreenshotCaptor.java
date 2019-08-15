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
 * 截图工具
 */
public class ScreenshotCaptor
{

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
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

			// 获取当前键盘事件的管理器
			KeyboardFocusManager manager = KeyboardFocusManager.getCurrentKeyboardFocusManager();
			// 然后为管理器添加一个新的键盘事件监听者。
			manager.addKeyEventPostProcessor(new KeyEventPostProcessor()
			{
				private boolean flag = false;

				@Override
				public boolean postProcessKeyEvent(KeyEvent e)
				{
					flag = !flag;
					if (flag)
					{
						// 只一次有效
						if (e.getKeyCode() == KeyEvent.VK_F1)
						{
							// 按下F1键
							JOptionPane.showMessageDialog(frame,
									"点击截图按钮后，按住鼠标左键拖拽选定区域，此时\r\n1.按下回车键完成截图，截图保存在桌面。\r\n2.单击鼠标右键可以取消区域，重新选择区域。\r\n完成截图后可以再次选择新区域，开始新的截图。",
									"帮助", JOptionPane.INFORMATION_MESSAGE);
						}
					}

					// 按下ESC键立即关闭程序
					if (e.getKeyCode() == KeyEvent.VK_ESCAPE)
					{
						System.exit(0);
					}

					return true;
				}
			});

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
