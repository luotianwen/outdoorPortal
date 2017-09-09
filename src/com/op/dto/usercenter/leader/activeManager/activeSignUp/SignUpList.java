package com.op.dto.usercenter.leader.activeManager.activeSignUp;

import java.io.Serializable;

public class SignUpList implements Serializable{

	private static final long serialVersionUID = 1L;
	
	//用户 名
	private String user_name;
	//报名时间
	private String asu_create_time;
	//人数
	private String peoplenum;
	//装备
	private String asu_equipment;
	//户外经验
	
	//备注
	private String asu_order_area;
	//领队确认
	private String asu_state;
	
	
	
	
	/**
	 * 用户名
	 */
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	/**
	 * 报名时间
	 */
	public String getAsu_create_time() {
		return asu_create_time;
	}
	public void setAsu_create_time(String asu_create_time) {
		this.asu_create_time = asu_create_time;
	}
	/**
	 * 人数
	 */
	public String getPeoplenum() {
		return peoplenum;
	}
	public void setPeoplenum(String peoplenum) {
		this.peoplenum = peoplenum;
	}
	/**
	 * 装备
	 */
	public String getAsu_equipment() {
		return asu_equipment;
	}
	public void setAsu_equipment(String asu_equipment) {
		this.asu_equipment = asu_equipment;
	}
	/**
	 * 备注
	 */
	public String getAsu_order_area() {
		return asu_order_area;
	}
	public void setAsu_order_area(String asu_order_area) {
		this.asu_order_area = asu_order_area;
	}
	/**
	 * 状态
	 */
	public String getAsu_state() {
		return asu_state;
	}
	public void setAsu_state(String asu_state) {
		this.asu_state = asu_state;
	}
	
}
