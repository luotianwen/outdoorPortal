package com.op.entity.pointService;

import java.io.Serializable;
/** 
 * 地点服务营业日(businessTime)实体类
 * @author 世峰户外商城 
 * @version Revision: 1.00 
 *  Date: 2016-06-23 13:36:41 
 */  
public class BusinessTime implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

 	//id
  	 private int psabd_id;
 	//营业开始时间
  	 private String psabd_start_date;
 	//营业结束时间
  	 private String psabd_end_date;
 	//地点服务ID
  	 private int psabd_point_service_id;



	 
    /**
    *id
    */ 
	public int getPsabd_id() {
		return psabd_id;
	}
	public void setPsabd_id(int psabd_id) {
		this.psabd_id = psabd_id;
	}
    /**
    *营业开始时间
    */ 
	public String getPsabd_start_date() {
		return psabd_start_date;
	}
	public void setPsabd_start_date(String psabd_start_date) {
		this.psabd_start_date = psabd_start_date;
	}
    /**
    *营业结束时间
    */ 
	public String getPsabd_end_date() {
		return psabd_end_date;
	}
	public void setPsabd_end_date(String psabd_end_date) {
		this.psabd_end_date = psabd_end_date;
	}
    /**
    *地点服务ID
    */ 
	public int getPsabd_point_service_id() {
		return psabd_point_service_id;
	}
	public void setPsabd_point_service_id(int psabd_point_service_id) {
		this.psabd_point_service_id = psabd_point_service_id;
	}
	
}
