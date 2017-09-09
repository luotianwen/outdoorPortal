package com.op.dto.usercenter.userInfo;

import java.io.Serializable;

public class PayPasswordDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//用户ID
	private String userId;
	//验证码
	private String captcha;
	//支付密码
	private String payPassword;
	//支付密码确认
	private String checkPayPassword;
	//原密码
	private String oldPayPassword;
	//错误信息
	private String error_info;
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getCaptcha() {
		return captcha;
	}
	public void setCaptcha(String captcha) {
		this.captcha = captcha;
	}
	public String getPayPassword() {
		return payPassword;
	}
	public void setPayPassword(String payPassword) {
		this.payPassword = payPassword;
	}
	public String getCheckPayPassword() {
		return checkPayPassword;
	}
	public void setCheckPayPassword(String checkPayPassword) {
		this.checkPayPassword = checkPayPassword;
	}
	public String getOldPayPassword() {
		return oldPayPassword;
	}
	public void setOldPayPassword(String oldPayPassword) {
		this.oldPayPassword = oldPayPassword;
	}
	public String getError_info() {
		return error_info;
	}
	public void setError_info(String error_info) {
		this.error_info = error_info;
	}
	
	
}
