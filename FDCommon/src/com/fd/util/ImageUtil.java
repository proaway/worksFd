package com.fd.util;

import ij.process.ColorProcessor;
import ij.process.ImageProcessor;

import java.awt.Color;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Properties;

import javax.imageio.ImageIO;

import org.apache.commons.io.FileUtils;

public class ImageUtil {
	Image src;
	int w;
	int h;
	String srcPath;
	String targetPath;
	File target;
	
	public ImageUtil() {
		
	}

	public ImageUtil(String srcPath, int w, int h, String targetPath) {
		this.w = w;
		this.h = h;
		try {
			this.srcPath = srcPath;
			this.src = ImageIO.read(new File(srcPath));
			this.targetPath = targetPath;
			this.target = new File(targetPath);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void rize() throws Exception {
		// 缩略宽
		int resizeWidth = w;
		// 缩略高
		int resizeHeight = h;
		// 目标宽
		int targetWidth = w;
		// 目标高
		int targetHeight = h;
		BufferedImage bufferImage = null;
		
		ImageProcessor processor = new ColorProcessor(src);
		processor.setInterpolate(true);
		// 尺寸合适不需要处理
		if (resizeHeight == processor.getHeight()
				&& resizeWidth == processor.getWidth()) {
			if (!srcPath.equals(targetPath)) {
				FileUtils.copyFile(new File(srcPath), new File(targetPath));
			}
			return;
		}
		// 需要压缩
		if (resizeHeight < processor.getHeight()
				|| resizeWidth < processor.getWidth()) {
			// 等比例压缩
			if(processor.getHeight()>processor.getWidth()) {
				resizeWidth = resizeHeight * processor.getWidth() / processor.getHeight();
			} else {
				resizeHeight = resizeWidth * processor.getHeight() / processor.getWidth();
			}
			processor = processor.resize(resizeWidth, resizeHeight);
		}

		// 增加白边
		int startx = (targetWidth - processor.getWidth()) / 2;
		int starty = (targetHeight - processor.getHeight()) / 2;
		bufferImage = new BufferedImage(targetWidth, targetHeight,
				BufferedImage.TYPE_INT_RGB);
		bufferImage.getGraphics().setColor(Color.white);
		bufferImage.getGraphics().fillRect(0, 0, targetWidth, targetHeight);
		bufferImage.setRGB(startx, starty, processor.getWidth(), processor
				.getHeight(), (int[]) processor.getPixels(), 0, processor
				.getWidth());
		ImageIO.write(bufferImage, "jpg", target);
	}
	
	/**
	 * 切产品图片方法
	 * 
	 * @param imagePath 图片路径
	 * @throws Exception
	 */
	public static void cutProductImage(String imagePath) throws Exception {
		File img = new File(imagePath);
		if (img.exists()) {
			WebPropUtil prop = new WebPropUtil();
			String small = prop.getProperty("image.s.size");
			String smallFile = imagePath.replaceAll("(?i)\\.(jpg|gif)", ".s.$1");
			if (!new File(smallFile).exists()) {
				cutImage(imagePath, smallFile, small);
			}
			
			String middle = prop.getProperty("image.m.size");
			String middleFile = imagePath.replaceAll("(?i)\\.(jpg|gif)", ".m.$1");
			if (!new File(middleFile).exists()) {
				cutImage(imagePath, middleFile, middle);
			}
			
			String big = prop.getProperty("image.b.size");
			String bigFile = imagePath.replaceAll("(?i)\\.(jpg|gif)", ".b.$1");
			if (!new File(bigFile).exists()) {
				cutImage(imagePath, bigFile, big);
			}
		}
	}
	
	/**
	 * 修改图片大小
	 * 
	 * @param imagePath
	 * @param width
	 * @param height
	 * @throws Exception
	 */
	public static void resize(String imagePath, int width, int height) throws Exception {
		File img = new File(imagePath);
		if (img.exists()) {
			new ImageUtil(imagePath, width, height, imagePath).rize();
		}
	}
	
	/**
	 * @param srcFile 原文件路径
	 * @param destFile 切图后文件路径
	 * @param size width x height 格式
	 */
	public static void cutImage(String srcFile, String destFile, String size) throws Exception {
		String sizes[] = size.split("[^0-9]+");
		int width = Integer.valueOf(sizes[0]);
		int height = Integer.valueOf(sizes[1]);
		new ImageUtil(srcFile, width, height, destFile).rize();
	}

	public static void main(String[] args) {
		try {
//			new ImageUtil("e:/44182.jpg", 280, 280, "e:/44182_4.jpg").rize();
			String imagePath = "/as/df/asdfa.JPG";
			String src = imagePath.replaceAll("(?i)\\.(jpg|gif)", ".src.$1");
			System.out.println(src);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}