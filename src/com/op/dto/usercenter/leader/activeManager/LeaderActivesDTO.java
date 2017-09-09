package com.op.dto.usercenter.leader.activeManager;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import com.op.entity.activity.ActiveTypes;
import com.op.util.ZdGetValue;
/**
 * 项目名：outdoorPortal
 * 类描述：领队查询所有活动数据DTO
 * 创建人：Yan
 * 创建时间： 2016-1-5 上午10:26:16
 * 最后修改时间：2016-1-5上午10:26:16
 */
public class LeaderActivesDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	// 活动ID
	private String activeId;
	// 创建用户ID
	private String createUserId;
	// 活动标题
	private String title;
	// 活动金额
	private double price;
	// 活动金额
	private Double price_start;
	// 活动金额
	private Double price_to;
	// 已报名人数
	private int alreadyInNum;
	// 需要人数
	private int needUserNum;
	// 确认人数
	private int confirmUserNum;
	// 开始时间
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date activityTime;
	// 开始时间
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date activityTimeTo;
	// 结束时间
	private Date endTime;
	// 报名截止时间
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date a_enroll_end_time;
	// 报名截止时间
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date a_enroll_end_time_to;
	// 活动状态
	private int de_state;
	// 活动状态
	private String state;
	// 活动类型集合
	private List<ActiveTypes> ats;
	
	
	/*-------------------	get	&&  set---------------------------*/
	
	
	
	
	public String getCreateUserId() {
		return createUserId;
	}

	public Double getPrice_start() {
		return price_start;
	}

	public void setPrice_start(Double price_start) {
		this.price_start = price_start;
	}

	public Double getPrice_to() {
		return price_to;
	}

	public void setPrice_to(Double price_to) {
		this.price_to = price_to;
	}

	public Date getActivityTimeTo() {
		return activityTimeTo;
	}

	public void setActivityTimeTo(Date activityTimeTo) {
		this.activityTimeTo = activityTimeTo;
	}

	public Date getA_enroll_end_time_to() {
		return a_enroll_end_time_to;
	}

	public void setA_enroll_end_time_to(Date a_enroll_end_time_to) {
		this.a_enroll_end_time_to = a_enroll_end_time_to;
	}

	public int getDe_state() {
		return de_state;
	}

	public void setDe_state(int de_state) {
		this.de_state = de_state;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = ZdGetValue.getTableColumnKeyVal("ACTIVITY", "STATE", state+"");
	}

	public List<ActiveTypes> getAts() {
		return ats;
	}

	public void setAts(List<ActiveTypes> ats) {
		this.ats = ats;
	}

	public String getActiveId() {
		return activeId;
	}

	public void setActiveId(String activeId) {
		this.activeId = activeId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getAlreadyInNum() {
		return alreadyInNum;
	}

	public void setAlreadyInNum(int alreadyInNum) {
		this.alreadyInNum = alreadyInNum;
	}

	public int getNeedUserNum() {
		return needUserNum;
	}

	public void setNeedUserNum(int needUserNum) {
		this.needUserNum = needUserNum;
	}

	public int getConfirmUserNum() {
		return confirmUserNum;
	}

	public void setConfirmUserNum(int confirmUserNum) {
		this.confirmUserNum = confirmUserNum;
	}

	public Date getActivityTime() {
		return activityTime;
	}

	public void setActivityTime(Date activityTime) {
		this.activityTime = activityTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public Date getA_enroll_end_time() {
		return a_enroll_end_time;
	}

	public void setA_enroll_end_time(Date a_enroll_end_time) {
		this.a_enroll_end_time = a_enroll_end_time;
	}

	public void setCreateUserId(String createUserId) {
		this.createUserId = createUserId;
	}
	
	
}
