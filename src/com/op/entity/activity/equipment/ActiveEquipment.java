package com.op.entity.activity.equipment;

import java.io.Serializable;
import java.util.Date;

/**
 * 活动装备(activeEquipment)实体类
 * 
 * @author Yan
 * @version Revision: 1.00 Date: 2015-12-10 10:51:35
 */
public class ActiveEquipment implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	// id
	private int ae_id;
	// 装备名称
	private String ae_name;
	// 装备描述
	private String ae_description;
	// 活动ID
	private int ae_activeId;
	// 创建时间
	private Date ae_create_time;
	// 创建用户
	private String ae_create_user;

	/**
	 * id
	 */
	public int getAe_id() {
		return ae_id;
	}

	public void setAe_id(int ae_id) {
		this.ae_id = ae_id;
	}

	/**
	 * 装备名称
	 */
	public String getAe_name() {
		return ae_name;
	}

	public void setAe_name(String ae_name) {
		this.ae_name = ae_name;
	}

	/**
	 * 装备描述
	 */
	public String getAe_description() {
		return ae_description;
	}

	public void setAe_description(String ae_description) {
		this.ae_description = ae_description;
	}

	/**
	 * 活动ID
	 */
	public int getAe_activeId() {
		return ae_activeId;
	}

	public void setAe_activeId(int ae_activeId) {
		this.ae_activeId = ae_activeId;
	}

	/**
	 * 创建时间
	 */
	public Date getAe_create_time() {
		return ae_create_time;
	}

	public void setAe_create_time(Date ae_create_time) {
		this.ae_create_time = ae_create_time;
	}

	/**
	 * 创建用户
	 */
	public String getAe_create_user() {
		return ae_create_user;
	}

	public void setAe_create_user(String ae_create_user) {
		this.ae_create_user = ae_create_user;
	}
}
