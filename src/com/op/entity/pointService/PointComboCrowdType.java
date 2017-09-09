package com.op.entity.pointService;

import java.io.Serializable;

/**
 * 地点项目/套餐适合人群字典表(pointComboCrowdType)实体类
 * 
 * @author 世峰户外商城
 * @version Revision: 1.00 Date: 2016-06-24 13:31:40
 */
public class PointComboCrowdType implements Serializable {

	/**
	 * 
	 */
	static final long serialVersionUID = 1L;

	// id
	int id;
	// 说明
	String pcct_describe;
	
	

	public PointComboCrowdType() {
		super();
	}

	public PointComboCrowdType(int id, String pcct_describe) {
		super();
		this.id = id;
		this.pcct_describe = pcct_describe;
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
	 * 说明
	 */
	public String getPcct_describe() {
		return pcct_describe;
	}

	public void setPcct_describe(String pcct_describe) {
		this.pcct_describe = pcct_describe;
	}

}
