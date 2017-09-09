package com.op.entity.recentVisit;

import java.io.Serializable;
import java.util.Date;
/** 
 * 最近访问表(recentVisit)实体类
 * @author 世峰户外商城 
 * @version Revision: 1.00 
 *  Date: 2016-06-08 17:41:18 
 */  
public class RecentVisit implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

 	//id
  	 private String rv_id;
 	//访问人
  	 private String rv_visit;
 	//用户ID
  	 private String rv_user;
 	//访问时间
  	 private Date rv_time;



	 
    /**
    *id
    */ 
	public String getRv_id() {
		return rv_id;
	}
	public void setRv_id(String rv_id) {
		this.rv_id = rv_id;
	}
    /**
    *访问人
    */ 
	public String getRv_visit() {
		return rv_visit;
	}
	public void setRv_visit(String rv_visit) {
		this.rv_visit = rv_visit;
	}
    /**
    *用户ID
    */ 
	public String getRv_user() {
		return rv_user;
	}
	public void setRv_user(String rv_user) {
		this.rv_user = rv_user;
	}
    /**
    *访问时间
    */ 
	public Date getRv_time() {
		return rv_time;
	}
	public void setRv_time(Date rv_time) {
		this.rv_time = rv_time;
	}
	
}
