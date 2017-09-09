package com.op.entity.correlationCost;

import java.io.Serializable;
/** 
 * 费用字典表(correlationCost)实体类
 * @author 世峰户外商城 
 * @version Revision: 1.00 
 *  Date: 2016-03-21 10:32:33 
 */  
public class CorrelationCost implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

 	//id
  	 private int cc_id;
 	//费用名称
  	 private String cc_name;
 	//费用类型(0:包括费用；1：可选费用)
  	 private int cc_type;



	 
    /**
    *id
    */ 
	public int getCc_id() {
		return cc_id;
	}
	public void setCc_id(int cc_id) {
		this.cc_id = cc_id;
	}
    /**
    *费用名称
    */ 
	public String getCc_name() {
		return cc_name;
	}
	public void setCc_name(String cc_name) {
		this.cc_name = cc_name;
	}
    /**
    *费用类型(0:包括费用；1：可选费用)
    */ 
	public int getCc_type() {
		return cc_type;
	}
	public void setCc_type(int cc_type) {
		this.cc_type = cc_type;
	}
	
}
