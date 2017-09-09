package com.op.entity.zd;

import java.io.Serializable;

/** 
 * 活动难度等级字典表(activeDifficultyType)实体类
 * @author 世峰户外商城 
 * @version Revision: 1.00 
 *  Date: 2015-12-11 17:45:12 
 */  
public class ActiveDifficultyType implements Serializable{


 	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	//id
  	 private int adt_id;
 	//说明
  	 private String adt_description;



	 
    /**
    *id
    */ 
	public int getAdt_id() {
		return adt_id;
	}
	public void setAdt_id(int adt_id) {
		this.adt_id = adt_id;
	}
    /**
    *说明
    */ 
	public String getAdt_description() {
		return adt_description;
	}
	public void setAdt_description(String adt_description) {
		this.adt_description = adt_description;
	}
	
}
