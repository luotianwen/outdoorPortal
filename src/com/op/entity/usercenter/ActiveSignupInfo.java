package com.op.entity.usercenter;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

/**
 * 活动报名表(activeSignup)实体类
 * 
 * @author panyongwei
 * @version Revision: 1.00 Date: 2015年12月24日 16:40:49
 */
public class ActiveSignupInfo extends ActiveSignup implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// 状态ID 此处字段与父类asu_state一一对应.
	private int sus_id;
	// 报名状态描述
	private String sus_description;
	// 活动名称
	private String title;
	// 报名开始时间
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date starttime;
	// 报名结束时间
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date endtime;
	// 活动费用总额(元)
	private double price;
	// 领队电话
	private String mobile;
	// 状态类型
	private int sus_type;
	// 玩嘛帐号(用户昵称users表)
	private String uname;
	// 活动集合id
	private List<Integer> listId;
	// 需要人数
	private int needusernum;
	// 已报名人数
	private int alreadyinnum;
	// 确认人数
	private int confirmusernum;
	// 活动开始时间
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date activitytime;
	// 报名截止时间
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date a_enroll_end_time;
	//能否投诉领队
	private String complain;
	/**
	 * 状态ID
	 */
	public int getSus_id() {
		return sus_id;
	}

	public void setSus_id(int sus_id) {
		this.sus_id = sus_id;
	}

	/**
	 * 报名状态描述
	 */
	public String getSus_description() {
		return sus_description;
	}

	public void setSus_description(String sus_description) {
		this.sus_description = sus_description;
	}

	/**
	 * 活动名称
	 */
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * 报名开始时间
	 */
	public Date getStarttime() {
		return starttime;
	}

	public void setStarttime(Date starttime) {
		this.starttime = starttime;
	}

	/**
	 * 报名结束时间
	 */
	public Date getEndtime() {
		return endtime;
	}

	public void setEndtime(Date endtime) {
		this.endtime = endtime;
	}

	/**
	 * 报名人数
	 */
	public int getAlreadyinnum() {
		return alreadyinnum;
	}

	public void setAlreadyinnum(int alreadyinnum) {
		this.alreadyinnum = alreadyinnum;
	}

	/**
	 * 活动费用总额(元)
	 */
	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	/**
	 * 领队电话
	 */
	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	/**
	 * 状态类型
	 */
	public int getSus_type() {
		return sus_type;
	}

	public void setSus_type(int sus_type) {
		this.sus_type = sus_type;
	}

	/**
	 * 活动开始时间
	 */
	public Date getActivitytime() {
		return activitytime;
	}

	public void setActivitytime(Date activitytime) {
		this.activitytime = activitytime;
	}

	/**
	 * 玩嘛帐号(用户昵称users表)
	 */
	public String getUname() {
		return uname;
	}

	public void setUname(String uname) {
		this.uname = uname;
	}

	/**
	 * 活动集合id
	 */
	public List<Integer> getListId() {
		return listId;
	}

	public void setListId(List<Integer> listId) {
		this.listId = listId;
	}
	/**
	 * (活动)确认人数
	 */
	public int getConfirmusernum() {
		return confirmusernum;
	}

	public void setConfirmusernum(int confirmusernum) {
		this.confirmusernum = confirmusernum;
	}

	/**
	 * 需要人数
	 */
	public int getNeedusernum() {
		return needusernum;
	}

	public void setNeedusernum(int needusernum) {
		this.needusernum = needusernum;
	}

	/**
	 * 报名截止时间
	 */
	public Date getA_enroll_end_time() {
		return a_enroll_end_time;
	}

	public void setA_enroll_end_time(Date a_enroll_end_time) {
		this.a_enroll_end_time = a_enroll_end_time;
	}
	
	/**
	 * 能否投诉领队
	 */
	public String getComplain() {
		return complain;
	}

	public void setComplain(String complain) {
		this.complain = complain;
	}
}
