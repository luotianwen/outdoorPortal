package com.op.dto.activity.update;

import java.util.Date;

import com.op.util.ZdGetValue;

/**
 * =============================================================
 * 项目名：outdoorPortal
 * 类描述：查询活动数据（更新活动的最新数据）
 * 创建人：Yan
 * 创建时间： 2016-5-4
 * modification list：
 * =============================================================
 */
public class UpdateActiveDTO {
	// 活动ID
	String activityId;
	
	// 用户ID
	String userId;
	
	// 用户名称
	String uName;
	
	// 用户头像
	String uHeadImg;
	
	// 用户类型
	String uType;

	// 开始时间
	Date activityTime;
	
	// 结束时间
	Date endTime;

	// 截止时间
	Date enrollTime;
	
	// 当前时间
	Date currentTime;
	
	// 报名人数
	int signUpNum;
	
	// 总共需要人数
	int needUserNum;
	
	// 已确认人数
	int confirmUserNum;
	
	// 活动状态
	int state;
	
	// 活动状态（1：草稿；2：发布待审核；3：审核中；4：审核不通过；5：审核成功（发布））
	String stateVal;
	
	// 是否收藏
	boolean isFollow;
	
	// 评论数量
	int commentNum;
	
	// 咨询问答数量
	int consultationNum;
	
	


	public int getConfirmUserNum() {
		return confirmUserNum;
	}

	public void setConfirmUserNum(int confirmUserNum) {
		this.confirmUserNum = confirmUserNum;
	}

	public Date getCurrentTime() {
		return currentTime;
	}

	public void setCurrentTime(Date currentTime) {
		this.currentTime = currentTime;
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

	public String getuType() {
		return uType;
	}

	public void setuType(String uType) {
		this.uType = uType;
	}

	public int getNeedUserNum() {
		return needUserNum;
	}

	public void setNeedUserNum(int needUserNum) {
		this.needUserNum = needUserNum;
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

	public Date getEnrollTime() {
		return enrollTime;
	}

	public void setEnrollTime(Date enrollTime) {
		this.enrollTime = enrollTime;
	}

	public int getSignUpNum() {
		return signUpNum;
	}

	public void setSignUpNum(int signUpNum) {
		this.signUpNum = signUpNum;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		setStateVal(ZdGetValue.getTableColumnKeyVal("ACTIVITY", "STATE", state+""));
		this.state = state;
	}

	public boolean isFollow() {
		return isFollow;
	}

	public void setFollow(boolean isFollow) {
		this.isFollow = isFollow;
	}


	public int getCommentNum() {
		return commentNum;
	}

	public void setCommentNum(int commentNum) {
		this.commentNum = commentNum;
	}

	public int getConsultationNum() {
		return consultationNum;
	}

	public void setConsultationNum(int consultationNum) {
		this.consultationNum = consultationNum;
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

	public String getStateVal() {
		return stateVal;
	}

	public void setStateVal(String stateVal) {
		this.stateVal = stateVal;
	}
	
	
}
