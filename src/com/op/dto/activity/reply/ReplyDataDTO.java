package com.op.dto.activity.reply;

import java.util.Date;
import java.util.List;

import com.op.entity.activity.ActiveConsultationReply;
import com.op.util.RelativeDateFormat;

/**
 * =============================================================
 * 项目名：outdoorPortal
 * 类描述：待回答活动关联的问题
 * 创建人：Yan
 * 创建时间： 2016-4-5
 * modification list：
 * =============================================================
 */
public class ReplyDataDTO {
	
	// 主键id
	String ac_id;
	/*-------------------------提问人----------------------------*/
	
	// 提问人ID
	String ac_create_user_id;
	
	// 提问时间 
	Date ac_create_time;
	
	// 提问时间转换【几秒前，几分钟前，几小时前，几天前，几月前，几年前】
	String create_time_str;
	
	// 提问内容
	String ac_comment;
	
	// 提问用户头像
	String uHeadImg;
	
	// 提问用户名称
	String uName;
	
	/*-------------------------回复人----------------------------*/
	
	// 回复人ID
	String ac_reply_user_id;
	
	// 回复内容
	String ac_reply_comment;
	
	// 回复时间
	Date ac_reply_time;
	
	// 回复时间转换【几秒前，几分钟前，几小时前，几天前，几月前，几年前】
	String ac_reply_time_str;
	
	// 回复用户头像
	String replyHeadImg;
	
	// 回复用户名称
	String replyUname;
	
	// 是否已经回复(0:未回复；1：已回复；2：追问)
	int ac_is_reply;
	
	// 回复列表
	List<ActiveConsultationReply> acrs;

	
	
	public String getReplyHeadImg() {
		return replyHeadImg;
	}

	public void setReplyHeadImg(String replyHeadImg) {
		this.replyHeadImg = replyHeadImg;
	}

	public String getReplyUname() {
		return replyUname;
	}

	public void setReplyUname(String replyUname) {
		this.replyUname = replyUname;
	}

	public String getCreate_time_str() {
		return create_time_str;
	}

	public void setCreate_time_str(String create_time_str) {
		this.create_time_str = create_time_str;
	}

	public String getuName() {
		return uName;
	}

	public void setuName(String uName) {
		this.uName = uName;
	}

	public List<ActiveConsultationReply> getAcrs() {
		return acrs;
	}

	public void setAcrs(List<ActiveConsultationReply> acrs) {
		this.acrs = acrs;
	}

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
		this.setAc_reply_time_str(RelativeDateFormat.format(ac_reply_time));
		this.ac_reply_time = ac_reply_time;
	}

	public String getAc_reply_time_str() {
		return ac_reply_time_str;
	}

	public void setAc_reply_time_str(String ac_reply_time_str) {
		this.ac_reply_time_str = ac_reply_time_str;
	}

	public int getAc_is_reply() {
		return ac_is_reply;
	}

	public void setAc_is_reply(int ac_is_reply) {
		this.ac_is_reply = ac_is_reply;
	}

	public String getuHeadImg() {
		return uHeadImg;
	}

	public void setuHeadImg(String uHeadImg) {
		this.uHeadImg = uHeadImg;
	}

	public String getAc_id() {
		return ac_id;
	}

	public void setAc_id(String ac_id) {
		this.ac_id = ac_id;
	}

	public String getAc_create_user_id() {
		return ac_create_user_id;
	}

	public void setAc_create_user_id(String ac_create_user_id) {
		this.ac_create_user_id = ac_create_user_id;
	}

	public Date getAc_create_time() {
		return ac_create_time;
	}

	public void setAc_create_time(Date ac_create_time) {
		this.setCreate_time_str(RelativeDateFormat.format(ac_create_time));
		this.ac_create_time = ac_create_time;
	}

	public String getAc_comment() {
		return ac_comment;
	}

	public void setAc_comment(String ac_comment) {
		this.ac_comment = ac_comment;
	}
	
	
}
