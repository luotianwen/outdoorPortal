package com.op.entity.travels;

import java.io.Serializable;
import java.util.Date;
/** 
 * 游记点赞(travelsPraise)实体类
 * @author 世峰户外商城 
 * @version Revision: 1.00 
 *  Date: 2016-05-05 14:10:34 
 */  
public class TravelsPraise implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

 	//id
  	 private int tp_id;
 	//游记id
  	 private String tp_travelsId;
 	//点赞用户id
  	 private String tp_userId;
 	//创建时间
  	 private Date tp_createTime;



	 
    /**
    *id
    */ 
	public int getTp_id() {
		return tp_id;
	}
	public void setTp_id(int tp_id) {
		this.tp_id = tp_id;
	}
    /**
    *游记id
    */ 
	public String getTp_travelsId() {
		return tp_travelsId;
	}
	public void setTp_travelsId(String tp_travelsId) {
		this.tp_travelsId = tp_travelsId;
	}
    /**
    *点赞用户id
    */ 
	public String getTp_userId() {
		return tp_userId;
	}
	public void setTp_userId(String tp_userId) {
		this.tp_userId = tp_userId;
	}
    /**
    *创建时间
    */ 
	public Date getTp_createTime() {
		return tp_createTime;
	}
	public void setTp_createTime(Date tp_createTime) {
		this.tp_createTime = tp_createTime;
	}
	
}
