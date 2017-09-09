package com.op.entity.activity;

import java.io.Serializable;
import java.util.Date;

import com.op.util.RelativeDateFormat;

/**
 * 活动咨询回复(activeConsultationReply)实体类
 * 
 * @author Yan
 * @version Revision: 1.00 Date: 2015-12-30 11:19:08
 */
public class ActiveConsultationReply implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	// id
	private String acr_id;
	
	// 评论表ID
	private String acr_ac_id;
	
	// 回复时间
	private Date acr_create_time;
	
	// 回复时间【几秒前，几分钟前，几小时前，几天前，几月前，几年前】
	private String acr_create_time_str;
	
	// 回复内容
	private String acr_comment;
	
	// 回复用户ID
	private String acr_create_user_id;
	
	// 被回复用户ID
	private String acr_ac_user_id;

	// 回复用户名
	private String acr_create_user_name;
	
	// 被回复用户名
	private String acr_ac_user_name;
	
	// 回复人头像
	private String acr_create_user_header_img;
	
	
	public String getAcr_create_user_header_img() {
		return acr_create_user_header_img;
	}

	public void setAcr_create_user_header_img(String acr_create_user_header_img) {
		this.acr_create_user_header_img = acr_create_user_header_img;
	}

	public String getAcr_create_user_name() {
		return acr_create_user_name;
	}

	public void setAcr_create_user_name(String acr_create_user_name) {
		this.acr_create_user_name = acr_create_user_name;
	}

	public String getAcr_ac_user_name() {
		return acr_ac_user_name;
	}

	public void setAcr_ac_user_name(String acr_ac_user_name) {
		this.acr_ac_user_name = acr_ac_user_name;
	}

	/**
	 * id
	 */
	public String getAcr_id() {
		return acr_id;
	}

	public void setAcr_id(String acr_id) {
		this.acr_id = acr_id;
	}

	/**
	 * 评论表ID
	 */
	public String getAcr_ac_id() {
		return acr_ac_id;
	}

	public void setAcr_ac_id(String acr_ac_id) {
		this.acr_ac_id = acr_ac_id;
	}

	/**
	 * 回复时间
	 */
	public Date getAcr_create_time() {
		return acr_create_time;
	}

	public void setAcr_create_time(Date acr_create_time) {
		this.setAcr_create_time_str(RelativeDateFormat.format(acr_create_time));
		this.acr_create_time = acr_create_time;
	}

	/**
	 * 回复时间【几秒前，几分钟前，几小时前，几天前，几月前，几年前】
	 */
	public String getAcr_create_time_str() {
		return acr_create_time_str;
	}

	public void setAcr_create_time_str(String acr_create_time_str) {
		this.acr_create_time_str = acr_create_time_str;
	}

	/**
	 * 回复内容
	 */
	public String getAcr_comment() {
		return acr_comment;
	}

	public void setAcr_comment(String acr_comment) {
		this.acr_comment = acr_comment;
	}

	/**
	 * 回复用户ID
	 */
	public String getAcr_create_user_id() {
		return acr_create_user_id;
	}

	public void setAcr_create_user_id(String acr_create_user_id) {
		this.acr_create_user_id = acr_create_user_id;
	}

	/**
	 * 被回复用户ID
	 */
	public String getAcr_ac_user_id() {
		return acr_ac_user_id;
	}

	public void setAcr_ac_user_id(String acr_ac_user_id) {
		this.acr_ac_user_id = acr_ac_user_id;
	}

}
