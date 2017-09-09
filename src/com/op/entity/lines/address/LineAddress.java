package com.op.entity.lines.address;

import java.io.Serializable;
/** 
 * 线路地点集合(lineAddress)实体类
 * @author 世峰户外商城 
 * @version Revision: 1.00 
 *  Date: 2016-03-17 13:51:04 
 */  
public class LineAddress implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

 	//id
  	 private String la_id;
 	//地点名称
	private String la_address_name;
 	//分段线路ID
  	 private String la_line_id;



	 
    /**
    *id
    */ 
	public String getLa_id() {
		return la_id;
	}
	public void setLa_id(String la_id) {
		this.la_id = la_id;
	}
    /**
    *地点名称
    */ 
	public String getLa_address_name() {
		return la_address_name;
	}
	public void setLa_address_name(String la_address_name) {
		this.la_address_name = la_address_name;
	}
    /**
    *分段线路ID
    */ 
	public String getLa_line_id() {
		return la_line_id;
	}
	public void setLa_line_id(String la_line_id) {
		this.la_line_id = la_line_id;
	}
	
}
