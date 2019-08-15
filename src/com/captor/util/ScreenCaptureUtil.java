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
 * ��Ļ��ͼ������
 * 
 * @author ordinary-student
 *
 */
public class ScreenCaptureUtil
{
	/*
	 * ���췽��
	 */
	public ScreenCaptureUtil()
	{
	}

	/**
	 * ������Ļ��ͼ
	 * 
	 * @param press��ʼλ��
	 * @param release��ֹλ��
	 * @return
	 */
	public static BufferedImage createScreenCapture(Point press, Point release)
	{
		return createScreenCapture(press.x, press.y, release.x - press.x, release.y - press.y);
	}

	/**
	 * ������Ļ��ͼ
	 * 
	 * @param x��ʼ��x����
	 * @param y��ʼ��y����
	 * @param widthͼƬ���
	 * @param heightͼƬ�߶�
	 * @return
	 */
	public static BufferedImage createScreenCapture(int x, int y, int width, int height)
	{
		// ��Ļ��ͼ
		BufferedImage screenshot = null;
		try
		{
			// ��ͼ��Ļ����
			screenshot = (new Robot()).createScreenCapture(new Rectangle(x, y, width, height));
		} catch (AWTException e)
		{
			e.printStackTrace();
		}

		// ����
		return screenshot;
	}

	/**
	 * ��ȡȫ����ͼ
	 * 
	 * @return
	 */
	public static BufferedImage getFullScreenCapture()
	{
		// ȫ����ͼ
		BufferedImage screenshot = null;
		try
		{
			// ��ͼȫ����Ļ����
			screenshot = (new Robot()).createScreenCapture(new Rectangle(Toolkit.getDefaultToolkit().getScreenSize()));
		} catch (AWTException e)
		{
			e.printStackTrace();
		}

		// ����
		return screenshot;
	}

	/*
	 * ����ͼƬ
	 */
	public static void save(BufferedImage image)
	{
		// ��׺��
		String formatname = "png";
		// ����ͼƬ�ļ���
		String filename = "screenshot_" + getCurrentTime() + "." + formatname;

		// ����·��
		File desktopDir = FileSystemView.getFileSystemView().getHomeDirectory();
		// ����һ�����ļ�
		File file = new File(desktopDir, filename);

		try
		{
			// д��ͼ��
			ImageIO.write(image, formatname, file);

		} catch (IOException e)
		{
			e.printStackTrace();
		}
	}

	/*
	 * ��������ȡ��ǰʱ��
	 */
	public static String getCurrentTime()
	{
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd_HHmmss");
		String currentTime = df.format(System.currentTimeMillis());
		return currentTime;
	}

}
