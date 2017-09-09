package com.op.dto.activity.consultation;

import java.io.Serializable;
import java.util.Date;

public class ResultConsultationDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	// 该咨询信息ID
	String id;
	
	// 提问内容
	String ac_comment;

	// 提问时间
	Date ac_create_time;

	// 回复内容
	String ac_reply_comment;

	// 回复时间
	String ac_reply_time;

	// 点赞数量
	int ac_praises;
	
	// 如果当前用户处于登录状态，要根据用户ID和咨询ID链接查询关联的点赞列表查看当前用户是否点赞
	String acp_id;

	/*------------------------------	get	&&	set		-----------------------------------*/

	public String getAc_comment() {
		return ac_comment;
	}


	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}


	public String getAcp_id() {
		return acp_id;
	}

	public void setAcp_id(String acp_id) {
		this.acp_id = acp_id;
	}

	public void setAc_comment(String ac_comment) {
		this.ac_comment = ac_comment;
	}

	public Date getAc_create_time() {
		return ac_create_time;
	}

	public void setAc_create_time(Date ac_create_time) {
		this.ac_create_time = ac_create_time;
	}

	public String getAc_reply_comment() {
		return ac_reply_comment;
	}

	public void setAc_reply_comment(String ac_reply_comment) {
		this.ac_reply_comment = ac_reply_comment;
	}

	public String getAc_reply_time() {
		return ac_reply_time;
	}

	public void setAc_reply_time(String ac_reply_time) {
		this.ac_reply_time = ac_reply_time;
	}

	public int getAc_praises() {
		return ac_praises;
	}

	public void setAc_praises(int ac_praises) {
		this.ac_praises = ac_praises;
	}

}
