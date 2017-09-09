package com.op.entity.zd;

import java.io.Serializable;
import java.util.Map;

/**
 * 项目名：outdoorPortal
 * 类描述：字典表列对象
 * 创建人：Yan
 * 创建时间： 2015-11-9 下午4:59:59
 * 最后修改时间：2015-11-9下午4:59:59
 */
public class ZdColumn implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// 列名
	private String name;
	// 字段值集合，用map是为了避免重复并且效率高
	private Map<String, String> map;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Map<String, String> getMap() {
		return map;
	}

	public void setMap(Map<String, String> map) {
		this.map = map;
	}

}
