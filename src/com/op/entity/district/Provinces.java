package com.op.entity.district;

import java.io.Serializable;
import java.util.List;

/**
 * 省份(provinces)实体类
 * 
 * @author 世峰户外商城
 * @version Revision: 1.00 Date: 2016-04-15 16:04:33
 */
public class Provinces implements Serializable {

	/**
	 * 
	 */
	static final long serialVersionUID = 1L;

	// id
	int id;
	// 编号
	String code;
	// 名称
	String name;

	// 市集合
	List<Citys> citys;

	public List<Citys> getCitys() {
		return citys;
	}

	public void setCitys(List<Citys> citys) {
		this.citys = citys;
	}

	/**
	 * id
	 */
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	/**
	 * 编号
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

}
