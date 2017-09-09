package com.op.entity.activity;

import java.io.Serializable;
/** 
 * 活动对应的交通方式(activeTraffic)实体类
 * @author 世峰户外商城 
 * @version Revision: 1.00 
 *  Date: 2016-03-17 15:03:42 
 */  
public class ActiveTraffic implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

 	//id
  	 private String at_id;
 	//交通方式字典表id
  	 private String at_traffic_id;
 	//活动ID
  	 private String at_active_id;

  	 //交通名称
  	 private String trafficName;

	 
    /**
    *id
    */ 
	public String getAt_id() {
		return at_id;
	}
	public void setAt_id(String at_id) {
		this.at_id = at_id;
	}
    /**
    *交通方式字典表id
    */ 
	public String getAt_traffic_id() {
		return at_traffic_id;
	}
	public void setAt_traffic_id(String at_traffic_id) {
		this.at_traffic_id = at_traffic_id;
	}
    /**
    *活动ID
    */ 
	public String getAt_active_id() {
		return at_active_id;
	}
	public void setAt_active_id(String at_active_id) {
		this.at_active_id = at_active_id;
	}
	/**
	 * 交通名称
	 */
	public String getTrafficName() {
		return trafficName;
	}
	public void setTrafficName(String trafficName) {
		this.trafficName = trafficName;
	}
	
}
