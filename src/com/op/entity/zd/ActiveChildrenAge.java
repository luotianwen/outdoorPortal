package com.op.entity.zd;

import java.io.Serializable;

/**
 * 儿童年龄段字典表(activeChildrenAge)实体类
 * 
 * @author 世峰户外商城
 * @version Revision: 1.00 Date: 2015-12-11 17:45:12
 */
public class ActiveChildrenAge implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// id
	private int aca_id;
	// 最低年龄
	private int aca_age_min;
	// 最高年龄
	private int aca_age_max;
	// 年龄阶段描述
	private String aca_description;

	/**
	 * 年龄阶段描述
	 */
	public String getAca_description() {
		return aca_description;
	}

	public void setAca_description(String aca_description) {
		this.aca_description = aca_description;
	}

	/**
	 * id
	 */
	public int getAca_id() {
		return aca_id;
	}

	public void setAca_id(int aca_id) {
		this.aca_id = aca_id;
	}

	/**
	 * 最低年龄
	 */
	public int getAca_age_min() {
		return aca_age_min;
	}

	public void setAca_age_min(int aca_age_min) {
		this.aca_age_min = aca_age_min;
	}

	/**
	 * 最高年龄
	 */
	public int getAca_age_max() {
		return aca_age_max;
	}

	public void setAca_age_max(int aca_age_max) {
		this.aca_age_max = aca_age_max;
	}

}
