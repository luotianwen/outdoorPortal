package com.op.dto.activity.insertActivity;

import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * 项目名：outdoorPortal
 * 类描述：添加活动实体类
 * 创建人：Yan
 * 创建时间： 2016-1-27 下午1:57:09
 * 最后修改时间：2016-1-27下午1:57:09
 */
public class ActivityBaseInfoDTO{

	// 活动ID
	String activityId;
	// 频道ID
	int ch_id;
	// 活动类型集合{采用逗号分隔ID}
	String types;
	// 标题
	String title;
	// 适合人群
	int sc_id;
	// 儿童年龄段
	int a_children_age;
	// 活动难度等级
	int a_difficulty_type;
	// 活动需要人数
	Integer needUserNum;
	// 咨询电话
	Long a_phone;
	// 活动费用
	Double price;
	// 开始时间
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm")
	Date activityTime;
	// 活动结束时间
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm")
	Date endTime;
	// 报名截止时间
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm")
	Date a_enroll_end_time;
	// 添加用户ID
	String userId;
	
	/*-------------------------------------------	ADD	----------------------------------*/
	
	/*2016年3月11日*/
	
	// 出发地
	String a_start_location;
	
	// 目的地
	String a_end_location;
	
	// 集合时间
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm")
	Date a_gather_time;
	
	// 集合地点
	String a_gather_location;
	
	// 活动主图
	String a_active_img;
	
	// 交通方式{采用逗号分隔ID}
	String a_traffic_id;

	// 活动标签
	String[] activeTag;
	
	// 活动景点
	String[] activeScenic;
	
	// 根据返回操作的数据数量来操作完成度
	int dataNum;
	
	// 活动是否收费{1:"收费",2:"免费"}
	int isCharge;
	
	/*-------------------------------------------	get	&&	set --------------------------*/

	public int getCh_id() {
		return ch_id;
	}


	public int getIsCharge() {
		return isCharge;
	}


	public void setIsCharge(int isCharge) {
		this.isCharge = isCharge;
	}


	public int getDataNum() {
		return dataNum;
	}


	public void setDataNum(int dataNum) {
		this.dataNum = dataNum;
	}


	public String getActivityId() {
		return activityId;
	}


	public void setActivityId(String activityId) {
		this.activityId = activityId;
	}


	public void setCh_id(int ch_id) {
		this.ch_id = ch_id;
	}

	public String getTypes() {
		return types;
	}

	public void setTypes(String types) {
		this.types = types;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getSc_id() {
		return sc_id;
	}

	public void setSc_id(int sc_id) {
		this.sc_id = sc_id;
	}

	public int getA_children_age() {
		return a_children_age;
	}

	public void setA_children_age(int a_children_age) {
		this.a_children_age = a_children_age;
	}

	public int getA_difficulty_type() {
		return a_difficulty_type;
	}

	public void setA_difficulty_type(int a_difficulty_type) {
		this.a_difficulty_type = a_difficulty_type;
	}

	public Integer getNeedUserNum() {
		return needUserNum;
	}

	public void setNeedUserNum(Integer needUserNum) {
		this.needUserNum = needUserNum;
	}

	public Long getA_phone() {
		return a_phone;
	}

	public void setA_phone(Long a_phone) {
		this.a_phone = a_phone;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
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

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getA_start_location() {
		return a_start_location;
	}

	public void setA_start_location(String a_start_location) {
		this.a_start_location = a_start_location;
	}

	public String getA_end_location() {
		return a_end_location;
	}

	public void setA_end_location(String a_end_location) {
		this.a_end_location = a_end_location;
	}

	public Date getA_gather_time() {
		return a_gather_time;
	}

	public void setA_gather_time(Date a_gather_time) {
		this.a_gather_time = a_gather_time;
	}

	public String getA_gather_location() {
		return a_gather_location;
	}

	public void setA_gather_location(String a_gather_location) {
		this.a_gather_location = a_gather_location;
	}

	public String getA_active_img() {
		return a_active_img;
	}

	public void setA_active_img(String a_active_img) {
		this.a_active_img = a_active_img;
	}


	public String getA_traffic_id() {
		return a_traffic_id;
	}

	public void setA_traffic_id(String a_traffic_id) {
		this.a_traffic_id = a_traffic_id;
	}

	public String[] getActiveTag() {
		return activeTag;
	}

	public void setActiveTag(String[] activeTag) {
		this.activeTag = activeTag;
	}

	public String[] getActiveScenic() {
		return activeScenic;
	}

	public void setActiveScenic(String[] activeScenic) {
		this.activeScenic = activeScenic;
	}

}
