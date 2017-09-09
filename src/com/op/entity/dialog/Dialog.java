package com.op.entity.dialog;

import java.io.Serializable;
import java.util.Date;

import com.op.dto.usercenter.usercenter.UserCenterInfo;
/** 
 * 对话表(Dialog)实体类
 * @author Win Zhong 
 * @version Revision: 1.00 
 *  Date: 2016-03-12 13:29:51 
 */  
public class Dialog implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	//id
	private String id;
	//创建人
	private String creater;
	//创建时间
	private Date created;
	//被邀请好友
	private String friend;
	//对方信息
	private UserCenterInfo user;
	//当前登录用户
	private String loginId;
	//消息数量
	private String count;
	
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
    *创建人
    */ 
	public String getCreater() {
		return creater;
	}
	public void setCreater(String creater) {
		this.creater = creater;
	}
    /**
    *创建时间
    */ 
	public Date getCreated() {
		return created;
	}
	public void setCreated(Date created) {
		this.created = created;
	}
    /**
    *被邀请好友
    */ 
	public String getFriend() {
		return friend;
	}
	public void setFriend(String friend) {
		this.friend = friend;
	}
	/**
	 * 对方信息
	 */
	public UserCenterInfo getUser() {
		return user;
	}
	public void setUser(UserCenterInfo user) {
		this.user = user;
	}
	/**
	 * 当前登录用户
	 */
	public String getLogin() {
		return loginId;
	}
	public void setLogin(String loginId) {
		this.loginId = loginId;
	}
	/**
	 * 登录用户
	 * @return
	 */
	public String getLoginId() {
		return loginId;
	}
	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}
	/**
	 * 消息数量
	 * @return
	 */
	public String getCount() {
		return count;
	}
	public void setCount(String count) {
		this.count = count;
	}
	
}
