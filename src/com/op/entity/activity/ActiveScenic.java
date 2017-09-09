package com.op.entity.activity;

import java.io.Serializable;
/** 
 * 活动景点表(activeScenic)实体类
 * @author 世峰户外商城 
 * @version Revision: 1.00 
 *  Date: 2016-03-11 15:31:20 
 */  
public class ActiveScenic implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

 	//id
  	 private String as_id;
 	//景点名称
  	 private String as_name;
 	//活动ID
  	 private String as_active_id;



	 
    /**
    *id
    */ 
	public String getAs_id() {
		return as_id;
	}
	public void setAs_id(String as_id) {
		this.as_id = as_id;
	}
    /**
    *景点名称
    */ 
	public String getAs_name() {
		return as_name;
	}
	public void setAs_name(String as_name) {
		this.as_name = as_name;
	}
    /**
    *活动ID
    */ 
	public String getAs_active_id() {
		return as_active_id;
	}
	public void setAs_active_id(String as_active_id) {
		this.as_active_id = as_active_id;
	}
	
}
