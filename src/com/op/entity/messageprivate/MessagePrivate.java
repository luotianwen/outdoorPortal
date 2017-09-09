package com.op.entity.messageprivate;

import java.io.Serializable;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;
/** 
 * 站内信消息(PrivateMessage)实体类
 * @author 世峰户外商城 
 * @version Revision: 1.00 
 *  Date: 2016-03-07 17:33:09 
 */  
public class MessagePrivate implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	//id
	private String mp_id;
	//标题
	private String mp_title;
 	//内容
	private String mp_content;
 	//发送时间
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date mp_sendTime;
 	//信息状态(0：系统消息，其他暂定)
	private Integer mp_type;
	//用户身份(0：所有人，其他暂定)
	private Integer mp_identity;
	//接收人删除状态（0：正常；1：删除到垃圾箱；2：彻底删除）
	private Integer mp_isaddresserdelete;
	
	/**
	 * id
	 */
	public String getMp_id() {
		return mp_id;
	}
	public void setMp_id(String mp_id) {
		this.mp_id = mp_id;
	}
	/**
	 * 标题
	 */
	public String getMp_title() {
		return mp_title;
	}
	public void setMp_title(String mp_title) {
		this.mp_title = mp_title;
	}
	/**
	 * 内容
	 */
	public String getMp_content() {
		return mp_content;
	}
	public void setMp_content(String mp_content) {
		this.mp_content = mp_content;
	}
	/**
	 * 发送时间
	 */
	public Date getMp_sendTime() {
		return mp_sendTime;
	}
	public void setMp_sendTime(Date mp_sendTime) {
		this.mp_sendTime = mp_sendTime;
	}
	/**
	 * 信息状态(0：系统消息，其他暂定)
	 */
	public Integer getMp_type() {
		return mp_type;
	}
	public void setMp_type(Integer mp_type) {
		this.mp_type = mp_type;
	}
	/**
	 * 用户身份(0：所有人，其他暂定)
	 */
	public Integer getMp_identity() {
		return mp_identity;
	}
	public void setMp_identity(Integer mp_identity) {
		this.mp_identity = mp_identity;
	}
	/**
	 * 接收人删除状态（0：正常；1：删除到垃圾箱；2：彻底删除）
	 */
	public Integer getMp_isaddresserdelete() {
		return mp_isaddresserdelete;
	}
	public void setMp_isaddresserdelete(Integer mp_isaddresserdelete) {
		this.mp_isaddresserdelete = mp_isaddresserdelete;
	}
	
}
