package com.op.entity.usercenter;

import java.io.Serializable;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

/**
 * 常用报名人管理(linkSignUpUser)实体类
 * 
 * @author 世峰户外商城
 * @version Revision: 1.00 Date: 2015-12-19 16:14:39
 */
public class LinkSignUpUser implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// id
	private String lsuu_id;
	// 真实姓名
	private String lsuu_user_name;
	// 性别（1：男；2：女）
	private int lsuu_user_sex;
	// 手机号码
	private String lsuu_phone;
	// 证件类型（1:身份证，2:护照，3:出生证，4:护照，5:港澳通行证，6:军官证，7:台胞证，8:警官证，99:其他）
	private int lsuu_cart_type;
	// 证件号码
	private String lsuu_cart_num;
	// 出生日期
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date lsuu_birthday;
	// 装备情况
	private String lsuu_equipment;
	// 户外经验
	private String lsuu_experience;
	// 应急联系人姓名
	private String lsuu_emgc_user_name;
	// 应急联系人电话
	private String lsuu_emgc_user_phone;
	// 创建用户
	private String lsuu_create_user;
	// 创建时间
	private Date lsuu_create_time;
	// 最后更新时间
	private Date lsuu_update_time;
	// 最后操作人
	private String lsuu_update_user;
	
	//报名联系人
	private int aa_signup_linkman;
	//是否保存常用联系人
	private int issave;
	
	/**
	 * id
	 */
	public String getLsuu_id() {
		return lsuu_id;
	}

	public void setLsuu_id(String lsuu_id) {
		this.lsuu_id = lsuu_id;
	}

	/**
	 * 真实姓名
	 */
	public String getLsuu_user_name() {
		if(null!=lsuu_user_name){
			return lsuu_user_name;
		}else{
			return "";
		}
	}

	public void setLsuu_user_name(String lsuu_user_name) {
		this.lsuu_user_name = lsuu_user_name;
	}

	/**
	 * 性别（1：男；2：女）
	 */
	public int getLsuu_user_sex() {
		return lsuu_user_sex;
	}

	public void setLsuu_user_sex(int lsuu_user_sex) {
		this.lsuu_user_sex = lsuu_user_sex;
	}

	/**
	 * 手机号码
	 */
	public String getLsuu_phone() {
		if(null!=lsuu_phone){
			return lsuu_phone;
		}else{
			return "";
		}
	}

	public void setLsuu_phone(String lsuu_phone) {
		this.lsuu_phone = lsuu_phone;
	}

	/**
	 * 证件类型（1:身份证，2:护照，3:出生证，4:护照，5:港澳通行证，6:军官证，7:台胞证，8:警官证，99:其他）
	 */
	public int getLsuu_cart_type() {
		return lsuu_cart_type;
	}

	public void setLsuu_cart_type(int lsuu_cart_type) {
		this.lsuu_cart_type = lsuu_cart_type;
	}

	/**
	 * 证件号码
	 */
	public String getLsuu_cart_num() {
		if(null!=lsuu_cart_num){
			return lsuu_cart_num;
		}else{
			return "";
		}
	}

	public void setLsuu_cart_num(String lsuu_cart_num) {
		this.lsuu_cart_num = lsuu_cart_num;
	}

	/**
	 * 出生日期
	 */
	public Date getLsuu_birthday() {
		return lsuu_birthday;
	}

	public void setLsuu_birthday(Date lsuu_birthday) {
		this.lsuu_birthday = lsuu_birthday;
	}

	/**
	 * 装备情况
	 */
	public String getLsuu_equipment() {
		if(null!=lsuu_equipment){
			return lsuu_equipment;
		}else{
			return "";
		}
	}

	public void setLsuu_equipment(String lsuu_equipment) {
		this.lsuu_equipment = lsuu_equipment;
	}

	/**
	 * 户外经验
	 */
	public String getLsuu_experience() {
		if(null!=lsuu_experience){
			return lsuu_experience;
		}else{
			return "";
		}
	}

	public void setLsuu_experience(String lsuu_experience) {
		this.lsuu_experience = lsuu_experience;
	}

	/**
	 * 应急联系人姓名
	 */
	public String getLsuu_emgc_user_name() {
		if(null!=lsuu_emgc_user_name){
			return lsuu_emgc_user_name;
		}else{
			return "";
		}
	}

	public void setLsuu_emgc_user_name(String lsuu_emgc_user_name) {
		this.lsuu_emgc_user_name = lsuu_emgc_user_name;
	}

	/**
	 * 应急联系人电话
	 */
	public String getLsuu_emgc_user_phone() {
		if(null!=lsuu_emgc_user_phone){
			return lsuu_emgc_user_phone;
		}else{
			return "";
		}
	}

	public void setLsuu_emgc_user_phone(String lsuu_emgc_user_phone) {
		this.lsuu_emgc_user_phone = lsuu_emgc_user_phone;
	}

	/**
	 * 创建用户
	 */
	public String getLsuu_create_user() {
		return lsuu_create_user;
	}

	public void setLsuu_create_user(String lsuu_create_user) {
		this.lsuu_create_user = lsuu_create_user;
	}

	/**
	 * 创建时间
	 */
	public Date getLsuu_create_time() {
		return lsuu_create_time;
	}

	public void setLsuu_create_time(Date lsuu_create_time) {
		this.lsuu_create_time = lsuu_create_time;
	}

	/**
	 * 最后更新时间
	 */
	public Date getLsuu_update_time() {
		return lsuu_update_time;
	}

	public void setLsuu_update_time(Date lsuu_update_time) {
		this.lsuu_update_time = lsuu_update_time;
	}

	/**
	 * 最后操作人
	 */
	public String getLsuu_update_user() {
		return lsuu_update_user;
	}

	public void setLsuu_update_user(String lsuu_update_user) {
		this.lsuu_update_user = lsuu_update_user;
	}

	/**
	 * 报名联系人
	 */
	public int getAa_signup_linkman() {
		return aa_signup_linkman;
	}

	public void setAa_signup_linkman(int aa_signup_linkman) {
		this.aa_signup_linkman = aa_signup_linkman;
	}

	/**
	 * 是否保存常用联系人
	 */
	public int getIssave() {
		return issave;
	}

	public void setIssave(int issave) {
		this.issave = issave;
	}

}
