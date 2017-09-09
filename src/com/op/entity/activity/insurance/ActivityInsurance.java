package com.op.entity.activity.insurance;

import java.io.Serializable;
import java.util.Date;
/** 
 * 活动保险表(activityInsurance)实体类
 * @author 世峰户外商城 
 * @version Revision: 1.00 
 *  Date: 2016-03-28 14:10:00 
 */  
public class ActivityInsurance implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

 	//id
  	 private int ai_id;
 	//保险ID
  	 private String ai_insurance_id;
 	//活动ID
  	 private int ai_activity_id;
 	//创建时间
  	 private Date ai_create_time;
 	//创建用户
  	 private String ai_create_user;



	 
    /**
    *id
    */ 
	public int getAi_id() {
		return ai_id;
	}
	public void setAi_id(int ai_id) {
		this.ai_id = ai_id;
	}
    /**
    *保险ID
    */ 
	public String getAi_insurance_id() {
		return ai_insurance_id;
	}
	public void setAi_insurance_id(String ai_insurance_id) {
		this.ai_insurance_id = ai_insurance_id;
	}
    /**
    *活动ID
    */ 
	public int getAi_activity_id() {
		return ai_activity_id;
	}
	public void setAi_activity_id(int ai_activity_id) {
		this.ai_activity_id = ai_activity_id;
	}
    /**
    *创建时间
    */ 
	public Date getAi_create_time() {
		return ai_create_time;
	}
	public void setAi_create_time(Date ai_create_time) {
		this.ai_create_time = ai_create_time;
	}
    /**
    *创建用户
    */ 
	public String getAi_create_user() {
		return ai_create_user;
	}
	public void setAi_create_user(String ai_create_user) {
		this.ai_create_user = ai_create_user;
	}
	
}
