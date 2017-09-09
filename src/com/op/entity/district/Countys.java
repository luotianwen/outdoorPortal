package com.op.entity.district;

import java.io.Serializable;
/** 
 * 县(countys)实体类
 * @author 世峰户外商城 
 * @version Revision: 1.00 
 *  Date: 2016-04-15 16:04:33 
 */  
public class Countys implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

 	//id
  	 private int id;
 	//编码
  	 private String code;
 	//名称
  	 private String name;
 	//父编码
  	 private String parent_code;



	 
    /**
    *id
    */ 
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
    /**
    *编码
    */ 
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
    /**
    *名称
    */ 
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
    /**
    *父编码
    */ 
	public String getParent_code() {
		return parent_code;
	}
	public void setParent_code(String parent_code) {
		this.parent_code = parent_code;
	}
	
}
