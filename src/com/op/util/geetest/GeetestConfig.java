package com.op.util.geetest;

import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;

/**
 * 项目名：outdoorPortal
 * 类描述：极验验证码
 * 创建人：zheng
 * 创建时间： 2015-11-30 上午9:47:58
 * 最后修改时间：2015-11-30上午9:47:58
 */
public class GeetestConfig {

	// TODO: replace the these two string with your own captcha's id/key,the id/key below is just for demo
	private static String captcha_id = "";
	private static String private_key = "";

	static{
		ResourceBundle bundle = ResourceBundle.getBundle("geetest");
		captcha_id = bundle.getString("captchaId").trim();
		private_key = bundle.getString("privateKey").trim();
	}
	
	public static final String getCaptcha_id() {
		return captcha_id;
	}

	public static final String getPrivate_key() {
		return private_key;
	}

}
