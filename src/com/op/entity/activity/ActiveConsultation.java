package com.op.entity.activity;

import java.io.Serializable;
import java.util.Date;

/**
 * 活动咨询信息表(activeConsultation)实体类
 * 
 * @author Yan
 * @version Revision: 1.00 Date: 2015-12-30 11:19:08
 */
public class ActiveConsultation implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	// id
	String ac_id;
	
	// 咨询人ID
	String ac_create_user_id;
	
	// 咨询人用户名
	String ac_create_user_name;
	
	// 咨询人头像
	String ac_create_user_header_img;
	
	// 咨询时间
	Date ac_create_time;
	
	// 咨询内容
	String ac_comment;
	
	// 咨询活动ID
	int ac_active_id;
	
	// 点赞数量
	int ac_praises;
	
	// 回复人ID
	String ac_reply_user_id;
	
	//回复内容
	String ac_reply_comment;
	
	//回复时间
	Date ac_reply_time;
	
	// 当前用户是否点赞(默认为不点赞)
	String acp_id;
	
	//是否已经回复(0:未回复；1：已回复；2：追问；)
	String ac_is_reply;
	
	public String getAc_reply_user_id() {
		return ac_reply_user_id;
	}

	public void setAc_reply_user_id(String ac_reply_user_id) {
		this.ac_reply_user_id = ac_reply_user_id;
	}

	public String getAc_reply_comment() {
		return ac_reply_comment;
	}

	public void setAc_reply_comment(String ac_reply_comment) {
		this.ac_reply_comment = ac_reply_comment;
	}

	public Date getAc_reply_time() {
		return ac_reply_time;
	}

	public void setAc_reply_time(Date ac_reply_time) {
		this.ac_reply_time = ac_reply_time;
	}

	public int getAc_praises() {
		return ac_praises;
	}

	public void setAc_praises(int ac_praises) {
		this.ac_praises = ac_praises;
	}

	public String getAc_create_user_header_img() {
		return ac_create_user_header_img;
	}

	public void setAc_create_user_header_img(String ac_create_user_header_img) {
		this.ac_create_user_header_img = ac_create_user_header_img;
	}

	public String getAc_create_user_name() {
		return ac_create_user_name;
	}

	public void setAc_create_user_name(String ac_create_user_name) {
		this.ac_create_user_name = ac_create_user_name;
	}



	/**
	 * 评论活动ID
	 */
	public int getAc_active_id() {
		return ac_active_id;
	}

	public void setAc_active_id(int ac_active_id) {
		this.ac_active_id = ac_active_id;
	}

	/**
	 * id
	 */
	public String getAc_id() {
		return ac_id;
	}

	public void setAc_id(String ac_id) {
		this.ac_id = ac_id;
	}

	/**
	 * 评论人ID
	 */
	public String getAc_create_user_id() {
		return ac_create_user_id;
	}

	public void setAc_create_user_id(String ac_create_user_id) {
		this.ac_create_user_id = ac_create_user_id;
	}

	/**
	 * 评论时间
	 */
	public Date getAc_create_time() {
		return ac_create_time;
	}

	public void setAc_create_time(Date ac_create_time) {
		this.ac_create_time = ac_create_time;
	}

	/**
	 * 评论内容
	 */
	public String getAc_comment() {
		return ac_comment;
	}

	public void setAc_comment(String ac_comment) {
		this.ac_comment = ac_comment;
	}

	public String getAcp_id() {
		return acp_id;
	}

	public void setAcp_id(String acp_id) {
		this.acp_id = acp_id;
	}

	public String getAc_is_reply() {
		return ac_is_reply;
	}

	public void setAc_is_reply(String ac_is_reply) {
		this.ac_is_reply = ac_is_reply;
	}


}
