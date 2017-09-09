package com.op.entity.browser;

import java.io.Serializable;
/** 
 * 用户浏览器(UserBrowser)实体类
 * @author Win Zhong 
 * @version Revision: 1.00 
 *  String: 2016-01-28 10:23:58 
 */  
public class UserBrowser implements Serializable {

 	/**
	 * 
	 */
	private static final long serialVersionUID = 2435342853058928009L;
	
	//id
  	 private String id;
 	//ip
  	 private String ip;
 	//浏览器名称
  	 private String browser;
 	//浏览器版本号
  	 private String browserVersion;
 	//用户操作系统
  	 private String operatingSystem;
 	//访问时间
  	 private String accessTime;



	 
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
    *ip
    */ 
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
    /**
    *浏览器名称
    */ 
	public String getBrowser() {
		return browser;
	}
	public void setBrowser(String browser) {
		this.browser = browser;
	}
    /**
    *浏览器版本号
    */ 
	public String getBrowserVersion() {
		return browserVersion;
	}
	public void setBrowserVersion(String browserVersion) {
		this.browserVersion = browserVersion;
	}
    /**
    *用户操作系统
    */ 
	public String getOperatingSystem() {
		return operatingSystem;
	}
	public void setOperatingSystem(String operatingSystem) {
		this.operatingSystem = operatingSystem;
	}
    /**
    *访问时间
    */ 
	public String getAccessTime() {
		return accessTime;
	}
	public void setAccessTime(String accessTime) {
		this.accessTime = accessTime;
	}
	
}
