package com.op.entity.pointService;

import java.io.Serializable;
/** 
 * 营业日集合(businessDays)实体类
 * @author 世峰户外商城 
 * @version Revision: 1.00 
 *  Date: 2016-06-23 13:36:42 
 */  
public class BusinessDays implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

 	//id
  	 private int psbd_id;
 	//营业日
  	 private String psbd_day;
 	//地点服务ID
  	 private int psbd_point_service_id;
 	//地点服务营业日ID
  	 private int psabd_id;



	 
    /**
    *id
    */ 
	public int getPsbd_id() {
		return psbd_id;
	}
	public void setPsbd_id(int psbd_id) {
		this.psbd_id = psbd_id;
	}
    /**
    *营业日
    */ 
	public String getPsbd_day() {
		return psbd_day;
	}
	public void setPsbd_day(String psbd_day) {
		this.psbd_day = psbd_day;
	}
    /**
    *地点服务ID
    */ 
	public int getPsbd_point_service_id() {
		return psbd_point_service_id;
	}
	public void setPsbd_point_service_id(int psbd_point_service_id) {
		this.psbd_point_service_id = psbd_point_service_id;
	}
    /**
    *地点服务营业日ID
    */ 
	public int getPsabd_id() {
		return psabd_id;
	}
	public void setPsabd_id(int psabd_id) {
		this.psabd_id = psabd_id;
	}
	
}
