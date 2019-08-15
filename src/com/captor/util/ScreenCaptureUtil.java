package com.captor.util;

import java.awt.AWTException;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;

import javax.imageio.ImageIO;
import javax.swing.filechooser.FileSystemView;

/**
 * 屏幕截图工具类
 * 
 * @author ordinary-student
 *
 */
public class ScreenCaptureUtil
{
	/*
	 * 构造方法
	 */
	public ScreenCaptureUtil()
	{
	}

	/**
	 * 生成屏幕截图
	 * 
	 * @param press起始位置
	 * @param release终止位置
	 * @return
	 */
	public static BufferedImage createScreenCapture(Point press, Point release)
	{
		return createScreenCapture(press.x, press.y, release.x - press.x, release.y - press.y);
	}

	/**
	 * 生成屏幕截图
	 * 
	 * @param x起始点x坐标
	 * @param y起始点y坐标
	 * @param width图片宽度
	 * @param height图片高度
	 * @return
	 */
	public static BufferedImage createScreenCapture(int x, int y, int width, int height)
	{
		// 屏幕截图
		BufferedImage screenshot = null;
		try
		{
			// 截图屏幕区域
			screenshot = (new Robot()).createScreenCapture(new Rectangle(x, y, width, height));
		} catch (AWTException e)
		{
			e.printStackTrace();
		}

		// 返回
		return screenshot;
	}

	/**
	 * 获取全屏截图
	 * 
	 * @return
	 */
	public static BufferedImage getFullScreenCapture()
	{
		// 全屏截图
		BufferedImage screenshot = null;
		try
		{
			// 截图全部屏幕区域
			screenshot = (new Robot()).createScreenCapture(new Rectangle(Toolkit.getDefaultToolkit().getScreenSize()));
		} catch (AWTException e)
		{
			e.printStackTrace();
		}

		// 返回
		return screenshot;
	}

	/*
	 * 保存图片
	 */
	public static void save(BufferedImage image)
	{
		// 后缀名
		String formatname = "png";
		// 设置图片文件名
		String filename = "screenshot_" + getCurrentTime() + "." + formatname;

		// 桌面路径
		File desktopDir = FileSystemView.getFileSystemView().getHomeDirectory();
		// 创建一个新文件
		File file = new File(desktopDir, filename);

		try
		{
			// 写入图像
			ImageIO.write(image, formatname, file);

		} catch (IOException e)
		{
			e.printStackTrace();
		}
	}

	/*
	 * 方法：获取当前时间
	 */
	public static String getCurrentTime()
	{
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd_HHmmss");
		String currentTime = df.format(System.currentTimeMillis());
		return currentTime;
	}

}
