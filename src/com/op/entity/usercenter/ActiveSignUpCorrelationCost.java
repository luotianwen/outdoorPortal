package com.op.entity.usercenter;

import java.io.Serializable;
import java.util.Date;
/** 
 * 订单费用表(activeSignUpCorrelationCost)实体类
 * @author 世峰户外商城 
 * @version Revision: 1.00 
 *  Date: 2016-04-20 19:06:13 
 */  
public class ActiveSignUpCorrelationCost implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

 	//id
  	 private String asp_id;
 	//费用名称
  	 private String asp_cost_name;
 	//单价/补差费用
  	 private double asp_cost;
 	//费用说明
  	 private String asp_comment;
 	//创建人
  	 private String asp_create_user;
 	//创建时间
  	 private Date asp_create_time;
 	//订单ID
  	 private String asp_asu_id;

	 
    /**
    *id
    */ 
	public String getAsp_id() {
		return asp_id;
	}
	public void setAsp_id(String asp_id) {
		this.asp_id = asp_id;
	}
    /**
    *费用名称
    */ 
	public String getAsp_cost_name() {
		return asp_cost_name;
	}
	public void setAsp_cost_name(String asp_cost_name) {
		this.asp_cost_name = asp_cost_name;
	}
    /**
    *单价/补差费用
    */ 
	public double getAsp_cost() {
		return asp_cost;
	}
	public void setAsp_cost(double asp_cost) {
		this.asp_cost = asp_cost;
	}
    /**
    *费用说明
    */ 
	public String getAsp_comment() {
		return asp_comment;
	}
	public void setAsp_comment(String asp_comment) {
		this.asp_comment = asp_comment;
	}
    /**
    *创建人
    */ 
	public String getAsp_create_user() {
		return asp_create_user;
	}
	public void setAsp_create_user(String asp_create_user) {
		this.asp_create_user = asp_create_user;
	}
    /**
    *创建时间
    */ 
	public Date getAsp_create_time() {
		return asp_create_time;
	}
	public void setAsp_create_time(Date asp_create_time) {
		this.asp_create_time = asp_create_time;
	}
    /**
    *订单ID
    */ 
	public String getAsp_asu_id() {
		return asp_asu_id;
	}
	public void setAsp_asu_id(String asp_asu_id) {
		this.asp_asu_id = asp_asu_id;
	}
	
}
