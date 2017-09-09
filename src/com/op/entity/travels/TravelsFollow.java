package com.op.entity.travels;

import java.io.Serializable;
import java.util.Date;
/** 
 * 游记收藏(travelsFollow)实体类
 * @author 世峰户外商城 
 * @version Revision: 1.00 
 *  Date: 2016-05-05 13:42:57 
 */  
public class TravelsFollow implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

 	//id
  	 private int tf_id;
 	//游记ID
  	 private String tf_travelsId;
 	//用户ID
  	 private String tf_userId;
 	//创建时间
  	 private Date tf_createTime;



	 
    /**
    *id
    */ 
	public int getTf_id() {
		return tf_id;
	}
	public void setTf_id(int tf_id) {
		this.tf_id = tf_id;
	}
    /**
    *游记ID
    */ 
	public String getTf_travelsId() {
		return tf_travelsId;
	}
	public void setTf_travelsId(String tf_travelsId) {
		this.tf_travelsId = tf_travelsId;
	}
    /**
    *用户ID
    */ 
	public String getTf_userId() {
		return tf_userId;
	}
	public void setTf_userId(String tf_userId) {
		this.tf_userId = tf_userId;
	}
    /**
    *创建时间
    */ 
	public Date getTf_createTime() {
		return tf_createTime;
	}
	public void setTf_createTime(Date tf_createTime) {
		this.tf_createTime = tf_createTime;
	}
	
}
