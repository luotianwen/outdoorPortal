package com.op.dto.activity.baoxian;

public class BaoxianDTO {

	// 活动ID
	String activityId;
	
	// 保险ID
	String insuranceId;
	
	// 产品名称
	String chanpin;
	
	// 计划名称
	String jihua;
	
	// 期限
	String qixian;
	
	// 价格
	double jiage;
	
	// 用户ID
	String userId;
	
	// 费用说明
	String acp_comment;
	
	
	
	

	public String getAcp_comment() {
		return acp_comment;
	}

	public void setAcp_comment(String acp_comment) {
		this.acp_comment = acp_comment;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getActivityId() {
		return activityId;
	}

	public void setActivityId(String activityId) {
		this.activityId = activityId;
	}

	public String getInsuranceId() {
		return insuranceId;
	}

	public void setInsuranceId(String insuranceId) {
		this.insuranceId = insuranceId;
	}

	public String getChanpin() {
		return chanpin;
	}

	public void setChanpin(String chanpin) {
		this.chanpin = chanpin;
	}

	public String getJihua() {
		return jihua;
	}

	public void setJihua(String jihua) {
		this.jihua = jihua;
	}

	public String getQixian() {
		return qixian;
	}

	public void setQixian(String qixian) {
		this.qixian = qixian;
	}

	public double getJiage() {
		return jiage;
	}

	public void setJiage(double jiage) {
		this.jiage = jiage;
	}

}
