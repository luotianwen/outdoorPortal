package com.op.util;

import java.awt.*;
import java.awt.image.*;
import javax.imageio.ImageIO;
import javax.imageio.IIOException;
import java.io.*;
import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;
import javax.servlet.http.HttpSession;

/**
 * 
*    
* 项目名称：outdoorPortal   
* 类名称：ImageOperate   
* 类描述：   图片操作，改变大小加水印
* 创建人：Win Zhong   
* 创建时间：2015年12月8日 上午10:40:23   
* 修改人：Win Zhong   
* 修改时间：2015年12月8日 上午10:40:23   
* 修改备注：   
* @version    
*
 */
public class ImageOperate {

	/**
	 * 给图片加水印，但不改变大小
	 * 
	 * @param strOriginalFileName
	 *            String(原始文件)
	 * @param strWaterMarkFileName
	 *            String(水印后)
	 */
	public void waterMark(String strOriginalFileName,
			String strWaterMarkFileName, HttpSession session) {
		try {
			// 源文件
			String root = session.getServletContext().getRealPath("/");
			File fileOriginal = new File(root + strOriginalFileName);
			Image imageOriginal = ImageIO.read(fileOriginal);
			int widthOriginal = imageOriginal.getWidth(null);
			int heightOriginal = imageOriginal.getHeight(null);
			System.out.println("widthOriginal:" + widthOriginal
					+ "theightOriginal:" + heightOriginal);
			BufferedImage bufImage = new BufferedImage(widthOriginal,
					heightOriginal, BufferedImage.TYPE_INT_RGB);
			Graphics g = bufImage.createGraphics();
			g.drawImage(imageOriginal, 0, 0, widthOriginal, heightOriginal,
					null);
			// 水印文件
			File fileWaterMark = new File(root + strWaterMarkFileName);
			Image imageWaterMark = ImageIO.read(fileWaterMark);
			int widthWaterMark = imageWaterMark.getWidth(null);
			int heightWaterMark = imageWaterMark.getHeight(null);
			System.out.println("widthWaterMark:" + widthWaterMark
					+ "theightWaterMark:" + heightWaterMark);
			// 水印文件在源文件的右下角
			g.drawImage(imageWaterMark, widthOriginal - widthWaterMark,
					heightOriginal - heightWaterMark, widthWaterMark,
					heightWaterMark, null);
			g.dispose();
			FileOutputStream fos = new FileOutputStream(root
					+ strOriginalFileName);
			JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(fos);
			encoder.encode(bufImage);
			fos.flush();
			fos.close();
			fos = null;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 修改图片大小
	 */
	public void alterSize(String srcImgFile, String addChar, int new_w,
			int new_h, HttpSession session) {
		// System.out.print("文件路径为:"+"//"+srcImgFile);
		String root = session.getServletContext().getRealPath("/");
		java.io.File file = new java.io.File(root + srcImgFile);
		if (file.exists())
			System.out.println("文件存在");
		else
			System.out.println("文件不存在");
		int i = srcImgFile.indexOf(".");
		int sLen = srcImgFile.length();
		String suffix = srcImgFile.substring(i, sLen); // 带点+后缀名
		String urlName = srcImgFile.substring(0, i); // 路径+文件名
		String newUrlName = root + urlName + addChar + suffix;
		// System.out.print("新文件名为"+newUrlName);
		Image src = null;
		try {
			src = javax.imageio.ImageIO.read(file);
			java.awt.image.BufferedImage tag = new java.awt.image.BufferedImage(
					new_w, new_h, java.awt.image.BufferedImage.TYPE_INT_RGB);
			tag.getGraphics().drawImage(src, 0, 0, new_w, new_h, null);
			FileOutputStream newimage = new FileOutputStream(newUrlName);
			JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(newimage);
			encoder.encode(tag); // 近JPEG编码
			newimage.close();
		} catch (IIOException ee) {
			ee.printStackTrace();
			System.out.print("这里出错了");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}// End sizeAlter

}