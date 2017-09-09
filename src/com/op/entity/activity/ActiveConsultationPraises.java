package com.op.entity.activity;

import java.io.Serializable;
/** 
 * 活动提问点赞列表(activeConsultationPraises)实体类
 * @author 世峰户外商城 
 * @version Revision: 1.00 
 *  Date: 2016-03-07 15:50:05 
 */  
public class ActiveConsultationPraises implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

 	//id
  	 private String acp_id;
 	//点赞人ID
  	 private String acp_user_Id;
 	//咨询ID
  	 private String acp_ac_id;



	 
    /**
    *id
    */ 
	public String getAcp_id() {
		return acp_id;
	}
	public void setAcp_id(String acp_id) {
		this.acp_id = acp_id;
	}
    /**
    *点赞人ID
    */ 
	public String getAcp_user_Id() {
		return acp_user_Id;
	}
	public void setAcp_user_Id(String acp_user_Id) {
		this.acp_user_Id = acp_user_Id;
	}
    /**
    *咨询ID
    */ 
	public String getAcp_ac_id() {
		return acp_ac_id;
	}
	public void setAcp_ac_id(String acp_ac_id) {
		this.acp_ac_id = acp_ac_id;
	}
	
}
