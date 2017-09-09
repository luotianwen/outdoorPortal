package com.op.dto.insurance;

import java.io.Serializable;

import com.op.util.IdCardUtils;
/** 
 * 订单参加保险人
 * @author Win Zhong
 * @version Revision: 1.00 
 *  Date: 2016-03-18 16:36:38 
 */  
public class BaoXianren implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

 	 //活动订单号
  	 private String asu_id;
 	 //参加人员姓名
  	 private String aa_user_name;
  	 //投保人邮箱
   	 private String email;
 	 //参加人员性别（1：男；2：女）
  	 private int aa_user_sex;
 	 //参加人员手机号码
  	 private String aa_user_phone;
 	 //参加人员证件类型
  	 private int aa_user_certificates_type;
 	 //参加人员证件号码
  	 private String aa_user_certificates_num;
     // 出生日期
     private String birthday;
  	 //是否投保人（1：是；2：不是）
  	 private int aa_signup_linkman;
  	 
  	 //联系人ID
  	 private String aa_id;
  	 
	public String getAsu_id() {
		return asu_id;
	}
	public void setAsu_id(String asu_id) {
		this.asu_id = asu_id;
	}
	public String getAa_user_name() {
		return aa_user_name;
	}
	public void setAa_user_name(String aa_user_name) {
		this.aa_user_name = aa_user_name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public int getAa_user_sex() {
		if(2 == aa_user_sex){
			aa_user_sex = 0;
		}
		return aa_user_sex;
	}
	public void setAa_user_sex(int aa_user_sex) {
		this.aa_user_sex = aa_user_sex;
	}
	public String getAa_user_phone() {
		return aa_user_phone;
	}
	public void setAa_user_phone(String aa_user_phone) {
		this.aa_user_phone = aa_user_phone;
	}
	public int getAa_user_certificates_type() {
		return aa_user_certificates_type;
	}
	public void setAa_user_certificates_type(int aa_user_certificates_type) {
		this.aa_user_certificates_type = aa_user_certificates_type;
	}
	public String getAa_user_certificates_num() {
		return aa_user_certificates_num;
	}
	public void setAa_user_certificates_num(String aa_user_certificates_num) {
		this.aa_user_certificates_num = aa_user_certificates_num;
	}
	public int getAa_signup_linkman() {
		return aa_signup_linkman;
	}
	public void setAa_signup_linkman(int aa_signup_linkman) {
		this.aa_signup_linkman = aa_signup_linkman;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public String getBirthday() {
		if(1 == aa_user_certificates_type){
			birthday = IdCardUtils.getBirthByIdCard(aa_user_certificates_num, "yyyy-MM-dd");
		}
		return birthday;
	}/*
	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}*/
	public String getAa_id() {
		return aa_id;
	}
	public void setAa_id(String aa_id) {
		this.aa_id = aa_id;
	}
   
	
}

