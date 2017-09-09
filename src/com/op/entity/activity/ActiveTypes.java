package com.op.entity.activity;

import java.io.Serializable;

/**
 * 活动类型表信息(activeTypes)实体类
 * 
 * @author 世峰户外商城
 * @version Revision: 1.00 Date: 2015-12-14 12:01:19
 */
public class ActiveTypes implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// id
	private String at_id;
	// 活动ID
	private int at_active_id;
	// 类型ID
	private int at_type_id;
	// 类型名称
	private String cl_name;

	/**
	 * 类型名称
	 */
	public String getCl_name() {
		return cl_name;
	}

	public void setCl_name(String cl_name) {
		this.cl_name = cl_name;
	}

	/**
	 * id
	 */
	public int getAt_active_id() {
		return at_active_id;
	}

	public String getAt_id() {
		return at_id;
	}

	/**
	 * 活动ID
	 */
	public void setAt_id(String at_id) {
		this.at_id = at_id;
	}

	public void setAt_active_id(int at_active_id) {
		this.at_active_id = at_active_id;
	}

	/**
	 * 类型ID
	 */
	public int getAt_type_id() {
		return at_type_id;
	}

	public void setAt_type_id(int at_type_id) {
		this.at_type_id = at_type_id;
	}

}
