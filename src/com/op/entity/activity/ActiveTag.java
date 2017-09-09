package com.op.entity.activity;

import java.io.Serializable;
/** 
 * 活动标签表(activeTag)实体类
 * @author 世峰户外商城 
 * @version Revision: 1.00 
 *  Date: 2016-03-11 15:31:20 
 */  
public class ActiveTag implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

 	//id
  	 private String at_id;
 	//标签名称
  	 private String at_name;
 	//活动ID
  	 private String at_active_id;



	 
    /**
    *id
    */ 
	public String getAt_id() {
		return at_id;
	}
	public void setAt_id(String at_id) {
		this.at_id = at_id;
	}
    /**
    *标签名称
    */ 
	public String getAt_name() {
		return at_name;
	}
	public void setAt_name(String at_name) {
		this.at_name = at_name;
	}
    /**
    *活动ID
    */ 
	public String getAt_active_id() {
		return at_active_id;
	}
	public void setAt_active_id(String at_active_id) {
		this.at_active_id = at_active_id;
	}
	
}
