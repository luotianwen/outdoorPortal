package com.op.entity.pointService;

import java.io.Serializable;
/** 
 * 地点服务收藏(pointServiceEnshrine)实体类
 * @author 世峰户外商城 
 * @version Revision: 1.00 
 *  Date: 2016-06-23 13:36:41 
 */  
public class PointServiceEnshrine implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

 	//id
  	 private int pse_id;
 	//场馆ID
  	 private int pse_venue_id;
 	//用户ID
  	 private String pse_user_id;



	 
    /**
    *id
    */ 
	public int getPse_id() {
		return pse_id;
	}
	public void setPse_id(int pse_id) {
		this.pse_id = pse_id;
	}
    /**
    *场馆ID
    */ 
	public int getPse_venue_id() {
		return pse_venue_id;
	}
	public void setPse_venue_id(int pse_venue_id) {
		this.pse_venue_id = pse_venue_id;
	}
    /**
    *用户ID
    */ 
	public String getPse_user_id() {
		return pse_user_id;
	}
	public void setPse_user_id(String pse_user_id) {
		this.pse_user_id = pse_user_id;
	}
	
}
