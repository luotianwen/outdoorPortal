package com.op.entity.classification;

import java.io.Serializable;

/** 
 * 活动类型表(classification)实体类
 * @author Win Zhong 
 * @version Revision: 1.00 
 *  Date: 2015-11-30 12:21:18 
 */  
public class Classification implements Serializable {


 	/**
	 * 
	 */
	private static final long serialVersionUID = 8461462711569579998L;
	//类型ID
  	 private int cl_id;
 	//几级类型
  	 private int cl_level;
 	//类型名称
  	 private String cl_name;
 	//父ID
  	 private String cl_parentId;
 	//类型描述
  	 private String cl_describe;
 	//类型排序
  	 private int cl_sort;



	 
    /**
    *类型ID
    */ 
	public int getCl_id() {
		return cl_id;
	}
	public void setCl_id(int cl_id) {
		this.cl_id = cl_id;
	}
    /**
    *几级类型
    */ 
	public int getCl_level() {
		return cl_level;
	}
	public void setCl_level(int cl_level) {
		this.cl_level = cl_level;
	}
    /**
    *类型名称
    */ 
	public String getCl_name() {
		return cl_name;
	}
	public void setCl_name(String cl_name) {
		this.cl_name = cl_name;
	}
    /**
    *父ID
    */ 
	public String getCl_parentId() {
		return cl_parentId;
	}
	public void setCl_parentId(String cl_parentId) {
		this.cl_parentId = cl_parentId;
	}
    /**
    *类型描述
    */ 
	public String getCl_describe() {
		return cl_describe;
	}
	public void setCl_describe(String cl_describe) {
		this.cl_describe = cl_describe;
	}
    /**
    *类型排序
    */ 
	public int getCl_sort() {
		return cl_sort;
	}
	public void setCl_sort(int cl_sort) {
		this.cl_sort = cl_sort;
	}
	
}