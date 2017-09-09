package com.op.entity.pointService;

import java.io.Serializable;
/** 
 * 地点服务描述信息表(pointServiceInfo)实体类
 * @author 世峰户外商城 
 * @version Revision: 1.00 
 *  Date: 2016-06-23 13:36:42 
 */  
public class PointServiceInfo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

 	//id
  	 private int psi_id;
 	//地点服务id
  	 private int ps_id;
 	//购买须知
  	 private String psi_pay_info;
 	//场馆所提供的服务
  	 private String psi_service;
 	//相关介绍（字段具体名称由该场馆类型决定，参考【场馆类型对应字段表】）
  	 private String psi_venue_type_column_value;
 	//场馆介绍（编辑器）
  	 private String psi_introduce;



	 
    /**
    *id
    */ 
	public int getPsi_id() {
		return psi_id;
	}
	public void setPsi_id(int psi_id) {
		this.psi_id = psi_id;
	}
    /**
    *地点服务id
    */ 
	public int getPs_id() {
		return ps_id;
	}
	public void setPs_id(int ps_id) {
		this.ps_id = ps_id;
	}
    /**
    *购买须知
    */ 
	public String getPsi_pay_info() {
		return psi_pay_info;
	}
	public void setPsi_pay_info(String psi_pay_info) {
		this.psi_pay_info = psi_pay_info;
	}
    /**
    *场馆所提供的服务
    */ 
	public String getPsi_service() {
		return psi_service;
	}
	public void setPsi_service(String psi_service) {
		this.psi_service = psi_service;
	}
    /**
    *相关介绍（字段具体名称由该场馆类型决定，参考【场馆类型对应字段表】）
    */ 
	public String getPsi_venue_type_column_value() {
		return psi_venue_type_column_value;
	}
	public void setPsi_venue_type_column_value(String psi_venue_type_column_value) {
		this.psi_venue_type_column_value = psi_venue_type_column_value;
	}
    /**
    *场馆介绍（编辑器）
    */ 
	public String getPsi_introduce() {
		return psi_introduce;
	}
	public void setPsi_introduce(String psi_introduce) {
		this.psi_introduce = psi_introduce;
	}
	
}
