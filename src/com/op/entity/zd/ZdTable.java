package com.op.entity.zd;

import java.io.Serializable;
import java.util.Set;
/**
 * 项目名：outdoorPortal
 * 类描述：字典表名对象
 * 创建人：Yan
 * 创建时间： 2015-11-9 下午4:59:46
 * 最后修改时间：2015-11-9下午4:59:46
 */
public class ZdTable implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// 表名
	private String name;
	// 列集合
	private Set<ZdColumn> columns;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<ZdColumn> getColumns() {
		return columns;
	}

	public void setColumns(Set<ZdColumn> columns) {
		this.columns = columns;
	}

}
