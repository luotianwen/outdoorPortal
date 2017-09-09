package com.op.entity.travels;

import java.io.Serializable;
import java.util.Date;

/**
 * 游记评论回复(travelsCommentReply)实体类
 * 
 * @author 世峰户外商城
 * @version Revision: 1.00 Date: 2016-05-05 14:41:12
 */
public class TravelsCommentReply implements Serializable {

	/**
	 * 
	 */
	static final long serialVersionUID = 1L;

	// id
	int tcr_id;
	// 评论id
	int tcr_travelsCommentId;
	// 回复内容
	String tcr_replyContent;
	// 回复时间
	Date tcr_createTime;
	// 回复用户id
	String tcr_replyUserId;
	// 被回复用户ID
	String tcr_beReplyUserId;
	
	// 回复用户信息
	String uName;
	String uHeadImg;
	
	public TravelsCommentReply(){
		
	}
	
	public TravelsCommentReply(int tcr_travelsCommentId,String tcr_replyContent,String tcr_replyUserId,String tcr_beReplyUserId){
		this.tcr_travelsCommentId = tcr_travelsCommentId;
		this.tcr_replyContent = tcr_replyContent;
		this.tcr_replyUserId = tcr_replyUserId;
		this.tcr_beReplyUserId = tcr_beReplyUserId;
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

	/**
	 * 方法描述：被回复用户ID
	 */
	public String getTcr_beReplyUserId() {
		return tcr_beReplyUserId;
	}

	public void setTcr_beReplyUserId(String tcr_beReplyUserId) {
		this.tcr_beReplyUserId = tcr_beReplyUserId;
	}

	/**
	 * id
	 */
	public int getTcr_id() {
		return tcr_id;
	}

	public void setTcr_id(int tcr_id) {
		this.tcr_id = tcr_id;
	}

	/**
	 * 评论id
	 */
	public int getTcr_travelsCommentId() {
		return tcr_travelsCommentId;
	}

	public void setTcr_travelsCommentId(int tcr_travelsCommentId) {
		this.tcr_travelsCommentId = tcr_travelsCommentId;
	}

	/**
	 * 回复内容
	 */
	public String getTcr_replyContent() {
		return tcr_replyContent;
	}

	public void setTcr_replyContent(String tcr_replyContent) {
		this.tcr_replyContent = tcr_replyContent;
	}

	/**
	 * 回复时间
	 */
	public Date getTcr_createTime() {
		return tcr_createTime;
	}

	public void setTcr_createTime(Date tcr_createTime) {
		this.tcr_createTime = tcr_createTime;
	}

	/**
	 * 回复用户id
	 */
	public String getTcr_replyUserId() {
		return tcr_replyUserId;
	}

	public void setTcr_replyUserId(String tcr_replyUserId) {
		this.tcr_replyUserId = tcr_replyUserId;
	}

}
