package com.op.dto.activity.reply;

import java.util.Date;

/**
 * =============================================================
 * 项目名：outdoorPortal
 * 类描述：查询待回答活动及关联的问题
 * 创建人：Yan
 * 创建时间： 2016-4-5
 * modification list：
 * =============================================================
 */
public class ReplyActiveDTO {

	// 活动ID
	String id;
	
	// 活动标题
	String title;
	
	// 回复（0：未回复；1：已回复；2：追问）
	int isReply;
	
	// 开始时间
	Date activityTime;
	
	// 问题数量
	int num;
	
	

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public Date getActivityTime() {
		return activityTime;
	}

	public void setActivityTime(Date activityTime) {
		this.activityTime = activityTime;
	}

	public int getIsReply() {
		return isReply;
	}

	public void setIsReply(int isReply) {
		this.isReply = isReply;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

}
