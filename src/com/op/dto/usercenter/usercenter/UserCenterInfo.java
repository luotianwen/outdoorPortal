package com.op.dto.usercenter.usercenter;

import java.io.Serializable;

public class UserCenterInfo implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	//用户ID
	private String u_id;
	//用户名
	private String uName;
	//用户头像
	private String uHeadImg;
	
	public String getU_id() {
		return u_id;
	}
	public void setU_id(String u_id) {
		this.u_id = u_id;
	}
	public String getuName() {
		return uName;
	}
	public void setuName(String uName) {
		this.uName = uName;
	}
	public String getuHeadImg() {
		return uHeadImg;
	}
	public void setuHeadImg(String uHeadImg) {
		this.uHeadImg = uHeadImg;
	}
}
