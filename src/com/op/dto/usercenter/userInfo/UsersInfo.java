package com.op.dto.usercenter.userInfo;

import java.io.Serializable;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class UsersInfo implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	//用户ID
	private String u_Id;
	//昵称
	private String uName;
	//性别(1:男，2:女,3:保密)
	private int uSex;
	//居住城市
	private String uLoginAreaCode;
	//出生日期
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date birthday;
	//个性签名
	private String personalitySignature;
	
	/**
	 * 用户ID
	 */
	public String getu_Id() {
		return u_Id;
	}
	/**
	 * 用户ID
	 */
	public void setu_Id(String u_Id) {
		this.u_Id = u_Id;
	}
	/**
	 * 昵称
	 */
	public String getuName() {
		return uName;
	}
	/**
	 * 昵称
	 */
	public void setuName(String uName) {
		this.uName = uName;
	}
	/**
	 * 性别（1：男，2：女,3:保密）
	 */
	public int getuSex() {
		return uSex;
	}
	/**
	 * 性别（1：男，2：女,3:保密）
	 */
	public void setuSex(int uSex) {
		this.uSex = uSex;
	}
	/**
	 * 居住城市
	 */
	public String getuLoginAreaCode() {
		return uLoginAreaCode;
	}
	/**
	 * 居住城市
	 */
	public void setuLoginAreaCode(String uLoginAreaCode) {
		this.uLoginAreaCode = uLoginAreaCode;
	}
	/**
	 * 出生日期
	 */
	public Date getBirthday() {
		return birthday;
	}
	/**
	 * 出生日期
	 */
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	/**
	 * 个性签名
	 */
	public String getPersonalitySignature() {
		return personalitySignature;
	}
	/**
	 * 个性签名
	 */
	public void setPersonalitySignature(String personalitySignature) {
		this.personalitySignature = personalitySignature;
	}
	
}
