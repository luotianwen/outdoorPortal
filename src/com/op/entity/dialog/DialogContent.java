package com.op.entity.dialog;

import java.io.Serializable;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;
/** 
 * 对话内容表(DialogContent)实体类
 * @author Win Zhong 
 * @version Revision: 1.00 
 *  Date: 2016-03-12 13:29:51 
 */  
public class DialogContent implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	//id
	private String id;
	//对话表ID
	private String dialogId;
	//发送人
	private String addresser;
 	//接收人
	private String addressee;
 	//发送时间
  	@DateTimeFormat(pattern="yyyy-MM-dd")
  	private Date sendTime;
 	//阅读状态(0：未读；1：已读)
  	private int readStatus;
 	//发送人删除状态（0：正常；1：删除）
  	private int isAddresserDelete;
 	//接收人删除状态（0：正常；1：删除）
  	private int isAddresseeDelete;
 	//消息内容
  	private String content;



	 
    /**
    *id
    */ 
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
    /**
    *对话表ID
    */ 
	public String getDialogId() {
		return dialogId;
	}
	public void setDialogId(String dialogId) {
		this.dialogId = dialogId;
	}
    /**
    *发送人
    */ 
	public String getAddresser() {
		return addresser;
	}
	public void setAddresser(String addresser) {
		this.addresser = addresser;
	}
    /**
    *接收人
    */ 
	public String getAddressee() {
		return addressee;
	}
	public void setAddressee(String addressee) {
		this.addressee = addressee;
	}
    /**
    *发送时间
    */ 
	public Date getSendTime() {
		return sendTime;
	}
	public void setSendTime(Date sendTime) {
		this.sendTime = sendTime;
	}
    /**
    *阅读状态(0：未读；1：已读)
    */ 
	public int getReadStatus() {
		return readStatus;
	}
	public void setReadStatus(int readStatus) {
		this.readStatus = readStatus;
	}
    /**
    *发送人删除状态（0：正常；1：删除）
    */ 
	public int getIsAddresserDelete() {
		return isAddresserDelete;
	}
	public void setIsAddresserDelete(int isAddresserDelete) {
		this.isAddresserDelete = isAddresserDelete;
	}
    /**
    *接收人删除状态（0：正常；1：删除）
    */ 
	public int getIsAddresseeDelete() {
		return isAddresseeDelete;
	}
	public void setIsAddresseeDelete(int isAddresseeDelete) {
		this.isAddresseeDelete = isAddresseeDelete;
	}
    /**
    *消息内容
    */ 
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
}
