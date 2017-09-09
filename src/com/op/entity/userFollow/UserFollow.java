package com.op.entity.userFollow;

import java.io.Serializable;
import java.util.Date;
/** 
 * 用户关注表(userFollow)实体类
 * @author 世峰户外商城 
 * @version Revision: 1.00 
 *  Date: 2016-06-08 13:49:33 
 */  
public class UserFollow implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

 	//id
  	 private String uf_id;
 	//关注人
  	 private String uf_fans;
 	//被关注人
  	 private String uf_follow;
 	//关注时间
  	 private Date uf_time;



	 
    /**
    *id
    */ 
	public String getUf_id() {
		return uf_id;
	}
	public void setUf_id(String uf_id) {
		this.uf_id = uf_id;
	}
    /**
    *关注人
    */ 
	public String getUf_fans() {
		return uf_fans;
	}
	public void setUf_fans(String uf_fans) {
		this.uf_fans = uf_fans;
	}
    /**
    *被关注人
    */ 
	public String getUf_follow() {
		return uf_follow;
	}
	public void setUf_follow(String uf_follow) {
		this.uf_follow = uf_follow;
	}
    /**
    *关注时间
    */ 
	public Date getUf_time() {
		return uf_time;
	}
	public void setUf_time(Date uf_time) {
		this.uf_time = uf_time;
	}
	
}
