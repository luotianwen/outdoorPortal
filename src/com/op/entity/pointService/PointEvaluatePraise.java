package com.op.entity.pointService;

import java.io.Serializable;
/** 
 * 地点服务评价点赞表(pointEvaluatePraise)实体类
 * @author 世峰户外商城 
 * @version Revision: 1.00 
 *  Date: 2016-06-23 13:36:42 
 */  
public class PointEvaluatePraise implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

 	//id
  	 private int psep_id;
 	//评价ID
  	 private int psep_venue_evaluate_id;
 	//点赞用户id
  	 private String psep_user_id;



	 
    /**
    *id
    */ 
	public int getPsep_id() {
		return psep_id;
	}
	public void setPsep_id(int psep_id) {
		this.psep_id = psep_id;
	}
    /**
    *评价ID
    */ 
	public int getPsep_venue_evaluate_id() {
		return psep_venue_evaluate_id;
	}
	public void setPsep_venue_evaluate_id(int psep_venue_evaluate_id) {
		this.psep_venue_evaluate_id = psep_venue_evaluate_id;
	}
    /**
    *点赞用户id
    */ 
	public String getPsep_user_id() {
		return psep_user_id;
	}
	public void setPsep_user_id(String psep_user_id) {
		this.psep_user_id = psep_user_id;
	}
	
}
