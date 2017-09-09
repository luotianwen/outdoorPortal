package com.op.entity.pointService;

import java.io.Serializable;
/** 
 * 场馆点赞(pointServicePraise)实体类
 * @author 世峰户外商城 
 * @version Revision: 1.00 
 *  Date: 2016-06-23 13:36:42 
 */  
public class PointServicePraise implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

 	//id
  	 private int psp_id;
 	//场馆ID
  	 private int psp_venue_id;
 	//用户ID
  	 private String psp_user_id;



	 
    /**
    *id
    */ 
	public int getPsp_id() {
		return psp_id;
	}
	public void setPsp_id(int psp_id) {
		this.psp_id = psp_id;
	}
    /**
    *场馆ID
    */ 
	public int getPsp_venue_id() {
		return psp_venue_id;
	}
	public void setPsp_venue_id(int psp_venue_id) {
		this.psp_venue_id = psp_venue_id;
	}
    /**
    *用户ID
    */ 
	public String getPsp_user_id() {
		return psp_user_id;
	}
	public void setPsp_user_id(String psp_user_id) {
		this.psp_user_id = psp_user_id;
	}
	
}
