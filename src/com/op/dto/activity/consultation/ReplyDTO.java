package com.op.dto.activity.consultation;

import java.util.Date;

/**
 * =============================================================
 * 项目名：outdoorPortal
 * 类描述：回复DTO
 * 创建人：Yan
 * 创建时间： 2016-4-7
 * modification list：
 * =============================================================
 */
public class ReplyDTO {
	
	// 该问题ID
	String ac_id;
	
	// 回复内容
	String comment;
	
	// 回复用户ID
	String replyUserId;
	
	// 被回复用户ID
	String userId;
	
	// 问题状态（0:未回复；1：已回复；2：追问）
	int replyType;
	
	// 创建时间
	Date time;
	
	// 回复表ID
	String id;
	
	
	

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public String getReplyUserId() {
		return replyUserId;
	}

	public void setReplyUserId(String replyUserId) {
		this.replyUserId = replyUserId;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getAc_id() {
		return ac_id;
	}

	public void setAc_id(String ac_id) {
		this.ac_id = ac_id;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public int getReplyType() {
		return replyType;
	}

	public void setReplyType(int replyType) {
		this.replyType = replyType;
	}

}
