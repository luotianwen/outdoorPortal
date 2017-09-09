package com.op.entity.pointService;

import java.io.Serializable;
/** 
 * 地点服务类型对应字段表(pointServiceTypeColumn)实体类
 * @author 世峰户外商城 
 * @version Revision: 1.00 
 *  Date: 2016-06-23 13:36:42 
 */  
public class PointServiceTypeColumn implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

 	//id
  	 private int pstc_id;
 	//类型ID
  	 private int pstc_type_id;
 	//字段名称
  	 private String pstc_column_title;



	 
    /**
    *id
    */ 
	public int getPstc_id() {
		return pstc_id;
	}
	public void setPstc_id(int pstc_id) {
		this.pstc_id = pstc_id;
	}
    /**
    *类型ID
    */ 
	public int getPstc_type_id() {
		return pstc_type_id;
	}
	public void setPstc_type_id(int pstc_type_id) {
		this.pstc_type_id = pstc_type_id;
	}
    /**
    *字段名称
    */ 
	public String getPstc_column_title() {
		return pstc_column_title;
	}
	public void setPstc_column_title(String pstc_column_title) {
		this.pstc_column_title = pstc_column_title;
	}
	
}
