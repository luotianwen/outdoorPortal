package com.op.entity.usercenter;

/** 
 * 报名状态字典表(signupState)实体类
 * @author Yan
 * @version Revision: 1.00 
 *  Date: 2015-12-16 11:12:22 
 */  
public class SignupState {


 	//id
  	 private int sus_id;
 	//描述
  	 private String sus_description;
 	//类型(1：报名；2：退款)
  	 private int sus_type;



	 
    /**
    *id
    */ 
	public int getSus_id() {
		return sus_id;
	}
	public void setSus_id(int sus_id) {
		this.sus_id = sus_id;
	}
    /**
    *描述
    */ 
	public String getSus_description() {
		return sus_description;
	}
	public void setSus_description(String sus_description) {
		this.sus_description = sus_description;
	}
    /**
    *类型(1：报名；2：退款)
    */ 
	public int getSus_type() {
		return sus_type;
	}
	public void setSus_type(int sus_type) {
		this.sus_type = sus_type;
	}
	
}
