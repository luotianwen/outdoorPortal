package com.op.controller;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.op.entity.VerifyCodeUtil;

@Controller
@RequestMapping(value = "/verifyCode")
public class VerifyCodeController {
	
	@RequestMapping("/getVerifyCodeImage/{type}")
	@ResponseBody
	public void getVerifyCodeImage(HttpServletRequest request, HttpServletResponse response,@PathVariable("type") String type) throws IOException {
		// 设置页面不缓存
		response.setHeader("Pragma", "no-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
		String verifyCode = VerifyCodeUtil.generateTextCode(VerifyCodeUtil.TYPE_ARITHMETIC, 5, null);
		String[] str = verifyCode.split(",");
		
		// 将验证码放到HttpSession里面
		request.getSession().setAttribute(type+"_verifyCode", str[1]);
		// 设置输出的内容的类型为JPEG图像
		response.setContentType("image/jpeg");
		BufferedImage bufferedImage = VerifyCodeUtil.generateImageCode(str[0], 105, 38, 5, true, Color.WHITE, Color.BLACK, null);
		// 写给浏览器
		ImageIO.write(bufferedImage, "JPEG", response.getOutputStream());
	}
}
