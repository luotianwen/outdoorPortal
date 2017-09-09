package com.op.entity.district;

import java.io.Serializable;
import java.util.List;

/**
 * 城市(citys)实体类
 * 
 * @author 世峰户外商城
 * @version Revision: 1.00 Date: 2016-04-15 16:04:33
 */
public class Citys implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	// 编码
	private String code;
	// 名称
	private String name;
	// 父编码
	private String parent_code;

	// 区/县集合
	List<Countys> countys;

	public List<Countys> getCountys() {
		return countys;
	}

	public void setCountys(List<Countys> countys) {
		this.countys = countys;
	}

	/**
	 * 编码
	 */
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	/**
	 * 名称
	 */
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	/**
	 * 父编码
	 */
	public String getParent_code() {
		return parent_code;
	}

	public void setParent_code(String parent_code) {
		this.parent_code = parent_code;
	}

}
