package com.op.entity.screening;

import java.io.Serializable;
import java.util.Date;

/**
 * 筛选条件(screening)实体类
 * 
 * @author Win Zhong
 * @version Revision: 1.00 Date: 2016-08-20 13:25:21
 */
public class Screening implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	// id
	private String sc_id;
	// 筛选类目名称
	private String sc_name;
	// 排序
	private int sc_sort;
	// 类型(1：多选；2：单选)
	private int sc_type;
	// 模块类别
	private String sc_modularType;
	// 父id
	private int sc_parent_id;
	// 创建时间
	private Date sc_create_time;
	// 创建人
	private String sc_create;
	// 删除（1：否；2：是）
	private int sc_delete;
	// 最后修改时间
	private Date sc_update_time;
	// 最后修改用户
	private String sc_update;
	
	/**
	 * id
	 * 
	 * @return
	 */
	public String getSc_id() {
		return sc_id;
	}

	/**
	 * id
	 * 
	 * @param type
	 */
	public void setSc_id(String sc_id) {
		this.sc_id = sc_id;
	}

	/**
	 * 筛选类目名称
	 * 
	 * @return
	 */
	public String getSc_name() {
		return sc_name;
	}

	/**
	 * 筛选类目名称
	 * 
	 * @param type
	 */
	public void setSc_name(String sc_name) {
		this.sc_name = sc_name;
	}

	/**
	 * 排序
	 * 
	 * @return
	 */
	public int getSc_sort() {
		return sc_sort;
	}

	/**
	 * 排序
	 * 
	 * @param type
	 */
	public void setSc_sort(int sc_sort) {
		this.sc_sort = sc_sort;
	}

	/**
	 * 类型(1：多选；2：单选)
	 * 
	 * @return
	 */
	public int getSc_type() {
		return sc_type;
	}

	/**
	 * 类型(1：多选；2：单选)
	 * 
	 * @param type
	 */
	public void setSc_type(int sc_type) {
		this.sc_type = sc_type;
	}

	/**
	 * 模块类别
	 * 
	 * @return
	 */
	public String getSc_modularType() {
		return sc_modularType;
	}

	/**
	 * 模块类别
	 * 
	 * @param type
	 */
	public void setSc_modularType(String sc_modularType) {
		this.sc_modularType = sc_modularType;
	}

	/**
	 * 父id
	 * 
	 * @return
	 */
	public int getSc_parent_id() {
		return sc_parent_id;
	}

	/**
	 * 父id
	 * 
	 * @param type
	 */
	public void setSc_parent_id(int sc_parent_id) {
		this.sc_parent_id = sc_parent_id;
	}

	/**
	 * 创建时间
	 * 
	 * @return
	 */
	public Date getSc_create_time() {
		return sc_create_time;
	}

	/**
	 * 创建时间
	 * 
	 * @param type
	 */
	public void setSc_create_time(Date sc_create_time) {
		this.sc_create_time = sc_create_time;
	}

	/**
	 * 创建人
	 * 
	 * @return
	 */
	public String getSc_create() {
		return sc_create;
	}

	/**
	 * 创建人
	 * 
	 * @param type
	 */
	public void setSc_create(String sc_create) {
		this.sc_create = sc_create;
	}

	/**
	 * 删除（1：否；2：是）
	 * @return
	 */
	public int getSc_delete() {
		return sc_delete;
	}

	/**
	 * 删除（1：否；2：是）
	 * @return
	 */
	public void setSc_delete(int sc_delete) {
		this.sc_delete = sc_delete;
	}

	/**
	 * 最后修改时间
	 * @return
	 */
	public Date getSc_update_time() {
		return sc_update_time;
	}

	/**
	 * 最后修改时间
	 * @return
	 */
	public void setSc_update_time(Date sc_update_time) {
		this.sc_update_time = sc_update_time;
	}

	/**
	 * 最后修改用户
	 * @return
	 */
	public String getSc_update() {
		return sc_update;
	}

	/**
	 * 最后修改用户
	 * @return
	 */
	public void setSc_update(String sc_update) {
		this.sc_update = sc_update;
	}

}
