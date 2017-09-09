package com.op.entity.pointService;

import java.io.Serializable;
/** 
 * 地点服务联系方式(pointServiceContact)实体类
 * @author 世峰户外商城 
 * @version Revision: 1.00 
 *  Date: 2016-06-23 13:36:41 
 */  
public class PointServiceContact implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

 	//id
  	 private int psc_id;
 	//地点服务id
  	 private int psc_point_service_id;
 	//联系方式
  	 private String psc_number;



	 
    /**
    *id
    */ 
	public int getPsc_id() {
		return psc_id;
	}
	public void setPsc_id(int psc_id) {
		this.psc_id = psc_id;
	}
    /**
    *地点服务id
    */ 
	public int getPsc_point_service_id() {
		return psc_point_service_id;
	}
	public void setPsc_point_service_id(int psc_point_service_id) {
		this.psc_point_service_id = psc_point_service_id;
	}
    /**
    *联系方式
    */ 
	public String getPsc_number() {
		return psc_number;
	}
	public void setPsc_number(String psc_number) {
		this.psc_number = psc_number;
	}
	
}
