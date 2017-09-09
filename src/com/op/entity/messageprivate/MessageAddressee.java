package com.op.entity.messageprivate;

import java.io.Serializable;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.op.entity.users.Users;
/** 
 * 收信人(MessageAddressee)实体类
 * @author 世峰户外商城 
 * @version Revision: 1.00 
 *  Date: 2016-03-08 16:13:38 
 */  
public class MessageAddressee implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

 	//id
  	 private String mar_id;
 	//信息表ID
  	 private String mp_id;
 	//阅读状态(0：未读，1：已读)
  	 private Integer mar_readStatus;
 	//信息状态
  	 private Integer mar_type;
 	//阅读时间
  	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
  	 private Date mar_readTime;
 	//接收人
  	 private String mar_addRessee;
 	//接收人删除状态（0：正常；1：删除到垃圾箱；2：彻底删除）
  	 private Integer mar_isaddresseedelete;
  	 //信息表数据
  	 private MessagePrivate messagePrivate;
  	 //用户信息
  	 private Users user;
	 
    /**
    *id
    */ 
	public String getMar_id() {
		return mar_id;
	}
	public void setMar_id(String mar_id) {
		this.mar_id = mar_id;
	}
    /**
    *信息表ID
    */ 
	public String getMp_id() {
		return mp_id;
	}
	public void setMp_id(String mp_id) {
		this.mp_id = mp_id;
	}
    /**
    *阅读状态(0：未读，1：已读)
    */ 
	public Integer getMar_readStatus() {
		return mar_readStatus;
	}
	public void setMar_readStatus(Integer mar_readStatus) {
		this.mar_readStatus = mar_readStatus;
	}
    /**
    *信息状态
    */ 
	public Integer getMar_type() {
		return mar_type;
	}
	public void setMar_type(Integer mar_type) {
		this.mar_type = mar_type;
	}
    /**
    *阅读时间
    */ 
	public Date getMar_readTime() {
		return mar_readTime;
	}
	public void setMar_readTime(Date mar_readTime) {
		this.mar_readTime = mar_readTime;
	}
    /**
    *接收人
    */ 
	public String getMar_addRessee() {
		return mar_addRessee;
	}
	public void setMar_addRessee(String mar_addRessee) {
		this.mar_addRessee = mar_addRessee;
	}
    /**
    *接收人删除状态（0：正常；1：删除到垃圾箱；2：彻底删除）
    */ 
	public Integer getMar_isaddresseedelete() {
		return mar_isaddresseedelete;
	}
	public void setMar_isaddresseedelete(Integer mar_isaddresseedelete) {
		this.mar_isaddresseedelete = mar_isaddresseedelete;
	}
	/**
	 * 信息表数据
	 */
	public MessagePrivate getMessagePrivate() {
		return messagePrivate;
	}
	public void setMessagePrivate(MessagePrivate messagePrivate) {
		this.messagePrivate = messagePrivate;
	}
	/**
	 * 用户信息
	 */
	public Users getUser() {
		return user;
	}
	public void setUser(Users user) {
		this.user = user;
	}
	
}
