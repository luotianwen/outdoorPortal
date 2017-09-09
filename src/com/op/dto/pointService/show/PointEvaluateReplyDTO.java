package com.op.dto.pointService.show;

import java.util.Date;

public class PointEvaluateReplyDTO {
	// id
	private int pser_id;
	// 评价ID
	private int pser_venue_evaluate_id;
	// 回复内容
	private String pser_comment;
	// 回复人
	private String pser_reply_user_id;
	// 被回复人
	private String pser_by_reply_user_id;
	// 回复时间
	private Date pser_reply_time;
	// 回复人ID
	private String ruId;
	// 用户昵称 OR 法定代表人姓名
	protected String ruName;
	// 用户头像
	protected String ruHeadImg;
	
	//被回复人ID
	private String uId;
	//被回复人
	private String uName;
	
	public int getPser_id() {
		return pser_id;
	}
	public void setPser_id(int pser_id) {
		this.pser_id = pser_id;
	}
	public int getPser_venue_evaluate_id() {
		return pser_venue_evaluate_id;
	}
	public void setPser_venue_evaluate_id(int pser_venue_evaluate_id) {
		this.pser_venue_evaluate_id = pser_venue_evaluate_id;
	}
	public String getPser_comment() {
		return pser_comment;
	}
	public void setPser_comment(String pser_comment) {
		this.pser_comment = pser_comment;
	}
	public String getPser_reply_user_id() {
		return pser_reply_user_id;
	}
	public void setPser_reply_user_id(String pser_reply_user_id) {
		this.pser_reply_user_id = pser_reply_user_id;
	}
	public String getPser_by_reply_user_id() {
		return pser_by_reply_user_id;
	}
	public void setPser_by_reply_user_id(String pser_by_reply_user_id) {
		this.pser_by_reply_user_id = pser_by_reply_user_id;
	}
	public Date getPser_reply_time() {
		return pser_reply_time;
	}
	public void setPser_reply_time(Date pser_reply_time) {
		this.pser_reply_time = pser_reply_time;
	}
	public String getRuId() {
		return ruId;
	}
	public void setRuId(String ruId) {
		this.ruId = ruId;
	}
	public String getRuName() {
		return ruName;
	}
	public void setRuName(String ruName) {
		this.ruName = ruName;
	}
	public String getRuHeadImg() {
		return ruHeadImg;
	}
	public void setRuHeadImg(String ruHeadImg) {
		this.ruHeadImg = ruHeadImg;
	}
	public String getuId() {
		return uId;
	}
	public void setuId(String uId) {
		this.uId = uId;
	}
	public String getuName() {
		return uName;
	}
	public void setuName(String uName) {
		this.uName = uName;
	}
	
	
}
