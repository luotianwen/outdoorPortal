package com.op.entity.emay;

import java.io.Serializable;
import java.util.Date;
/** 
 * 短信模板信息表(emayInfo)实体类
 * @author 世峰户外商城 
 * @version Revision: 1.00 
 *  Date: 2016-01-11 14:31:08 
 */  
public class EmayInfo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

 	//id
  	 private String ei_id;
 	//提示类型
  	 private String el_type;
 	//提示信息
  	 private String el_value;
 	//创建时间
  	 private Date el_create_time;
 	//修改时间
  	 private Date el_update_time;
 	//修改人
  	 private String el_update_user;



	 
    /**
    *id
    */ 
	public String getEi_id() {
		return ei_id;
	}
	public void setEi_id(String ei_id) {
		this.ei_id = ei_id;
	}
    /**
    *提示类型
    */ 
	public String getEl_type() {
		return el_type;
	}
	public void setEl_type(String el_type) {
		this.el_type = el_type;
	}
    /**
    *提示信息
    */ 
	public String getEl_value() {
		return el_value;
	}
	public void setEl_value(String el_value) {
		this.el_value = el_value;
	}
    /**
    *创建时间
    */ 
	public Date getEl_create_time() {
		return el_create_time;
	}
	public void setEl_create_time(Date el_create_time) {
		this.el_create_time = el_create_time;
	}
    /**
    *修改时间
    */ 
	public Date getEl_update_time() {
		return el_update_time;
	}
	public void setEl_update_time(Date el_update_time) {
		this.el_update_time = el_update_time;
	}
    /**
    *修改人
    */ 
	public String getEl_update_user() {
		return el_update_user;
	}
	public void setEl_update_user(String el_update_user) {
		this.el_update_user = el_update_user;
	}
	
}
