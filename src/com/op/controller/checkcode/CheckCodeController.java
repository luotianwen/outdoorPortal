package com.op.controller.checkcode;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.op.util.Const;
/**
 * =============================================================
 * 项目名：outdoorPortal
 * 类描述：验证码，根据设置画布的宽高，和几位验证码生成图片写回到页面
 * 创建人：Yan
 * 创建时间： 2016-3-10
 * modification list：
 * =============================================================
 */
@RequestMapping(value="checkCode")
@Controller
public class CheckCodeController {

	Logger log = Logger.getLogger(this.getClass());
	
	
	/**
	 * 方法描述：
	 * 返回类型：void
	 * @param request
	 * @param response
	 * @param width 画布宽度
	 * @param height 画布高度
	 * @param char_num 几位的验证码
	 * @throws Exception
	 */
	@RequestMapping(value="/generateCode")
	public void generateCode(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value="width",defaultValue="90") Integer width,// 画布宽度
			@RequestParam(value="height",defaultValue="20") Integer height,// 画布高度
			@RequestParam(value="char_num",defaultValue="6") Integer char_num// 几位的验证码
			)throws Exception{
		
		// 字符间距(一个字符占1位)
		int char_width = (width-char_num)/char_num;
		
		HttpSession session = request.getSession();
		response.setContentType("image/jpg");
		OutputStream sos = response.getOutputStream();

		// 设置浏览器不要缓存此图片
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);

		// 创建内存图像并获得图形上下文
		BufferedImage image = new BufferedImage(width, height,BufferedImage.TYPE_INT_RGB);

		Graphics g = image.getGraphics();

		// 产生随机的验证码
		char[] rands = generateCheckCode(char_num);  //a1cc

		// 产生图像
		drawBackground(g,width,height);
		drawRands(g, rands ,width ,height ,char_width ,char_num);

		// 结束图像的绘制过程，完成图像
		g.dispose();

		// 将图像输出到客户端
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		ImageIO.write(image, "jpg", bos);
		byte[] buf = bos.toByteArray();
		response.setContentLength(buf.length);

		// 下面的语句也可以写成：bos.writeTo(sos);
		sos.write(buf);
		bos.close();
		sos.close();

		String yzm = new String(rands);
		
		// 将当前验证码存入到Session中
		session.setAttribute(Const.YZM, yzm);
		
		log.info("本次生成的验证码为[" + yzm + "],已存放到HttpSession[YZM]中");

		// 直接使用下面的代码将有问题，Session对象必须在响应前获得
		// request.getSession().setAttribute("check_code", new String(rands));
	}
	
	
	/**
	 * 方法描述：产生随机的验证码
	 * 返回类型：char[]
	 * @return
	 */
	private char[] generateCheckCode(int num) {
		
		//定义验证码的字符表
		
		String chars = "0123456789abcdefghijklmnopqrstuvwxyz";
		
		char[] rands = new char[num];
		
		
		for(int i=0;i<num;i++){
			
			int rand = (int)(Math.random()*36);
			rands[i] = chars.charAt(rand);
			
		}
		return rands;

	}
	
	/**
	 * 方法描述：产生图像
	 * 返回类型：void
	 * @param g
	 */
	private void drawBackground(Graphics g,int width,int height){
		
		//画背景
		g.setColor(new Color(0xDCDCDC));
		g.fillRect(0, 0, width, height);
		
		//随机产生140个干扰点
		
		for(int i=0;i<140;i++){
			int x = (int)(Math.random()*width);
			int y = (int)(Math.random()*height);
			
			int red = (int)(Math.random()*255);
			int green = (int)(Math.random()*255);
			int blue = (int)(Math.random()*255);
			
			g.setColor(new Color(red,green,blue));
			g.drawOval(x, y, 1, 0);
		}
	}

	
	/**
	 * 方法描述：生成每个字符的位置（宽度和高度）
	 * 返回类型：void
	 * @param g
	 * @param rands
	 * @param width
	 * @param height
	 * @param char_width
	 * @param char_num
	 */
	private void drawRands(Graphics g,char[] rands,int width,int height,int char_width,int char_num){
		
		g.setColor(Color.BLACK);
		g.setFont(new Font(null,Font.ITALIC|Font.BOLD,18));
		
		// 最低比例和最高比例
		double ratioMin = 11d;
		double ratioMax = 18d;
		// 比例基数
		double ratioBase = 20d;

		// 动态生成每个字符的高度，取11/20-18/20之间
		double min = ratioMin/ratioBase*height,max = ratioMax/ratioBase*height,space = max-min;
		
		for(int i=0,index_width=1;i<char_num;i++){
			int random = (int)((Math.random()*space)+min);
			g.drawString(""+rands[i],index_width,random);
			index_width+=char_width;
		}
		
	}
}
