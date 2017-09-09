package com.op.entity.jointLogin;

import java.io.Serializable;
import java.util.Date;
/** 
 * 第三方平台账号联合登录(jointLogin)实体类
 * @author Win Zhong 
 * @version Revision: 1.00 
 *  Date: 2016-05-18 14:57:00 
 */  
public class JointLogin implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

 	//id
  	 private String id;
 	//平台类型（QQ；weixin）
  	 private String login_type;
 	//第三方平台授权用户身份唯一标识(一般32位字符串)
  	 private String openid;
 	//绑定用户id
  	 private String user_id;
 	//第三方平台授权码
  	 private String accessToken;
 	//授权码到期时间（单位：秒）
  	 private long tokenExpireIn;
 	//授权刷新时间
  	 private Date update_time;

  	 /**************************************/

   	//第三方平台用户昵称
  	 private String nickname;
  	//第三方平台用户性别
  	 private String gender;
   	
	 
    /**
    *id
	* @return
    */ 
	public String getId() {
		return id;
	}
    /**
    *id
	* @param type
    */ 
	public void setId(String id) {
		this.id = id;
	}
    /**
    *平台类型（QQ；weixin）
	* @return
    */ 
	public String getLogin_type() {
		return login_type;
	}
    /**
    *平台类型（QQ；weixin）
	* @param type
    */ 
	public void setLogin_type(String login_type) {
		this.login_type = login_type;
	}
    /**
    *第三方平台授权用户身份唯一标识(一般32位字符串)
	* @return
    */ 
	public String getOpenid() {
		return openid;
	}
    /**
    *第三方平台授权用户身份唯一标识(一般32位字符串)
	* @param type
    */ 
	public void setOpenid(String openid) {
		this.openid = openid;
	}
    /**
    *绑定用户id
	* @return
    */ 
	public String getUser_id() {
		return user_id;
	}
    /**
    *绑定用户id
	* @param type
    */ 
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
    /**
    *第三方平台授权码
	* @return
    */ 
	public String getAccessToken() {
		return accessToken;
	}
    /**
    *第三方平台授权码
	* @param type
    */ 
	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}
    /**
    *授权码到期时间（单位：秒）
	* @return
    */ 
	public long getTokenExpireIn() {
		return tokenExpireIn;
	}
    /**
    *授权码到期时间（单位：秒）
	* @param type
    */ 
	public void setTokenExpireIn(long tokenExpireIn) {
		this.tokenExpireIn = tokenExpireIn;
	}
    /**
    *授权刷新时间
	* @return
    */ 
	public Date getUpdate_time() {
		return update_time;
	}
    /**
    *授权刷新时间
	* @param type
    */ 
	public void setUpdate_time(Date update_time) {
		this.update_time = update_time;
	}
	/**
	 * 第三方平台用户昵称
	 * @return
	 */
	public String getNickname() {
		return nickname.length() > 10 ?nickname.substring(0, 10):nickname;
	}
	/**
	 * 第三方平台用户昵称
	 * @param nickname
	 */
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	/**
	 * 第三方平台用户性别
	 * @return
	 */
	public String getGender() {
		return gender;
	}
	/**
	 * 第三方平台用户性别
	 * @param gender
	 */
	public void setGender(String gender) {
		this.gender = gender;
	}
	
}
