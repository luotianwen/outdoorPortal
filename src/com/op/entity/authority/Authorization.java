package com.op.entity.authority;

import java.io.Serializable;

/**
 * 系统权限表(authorization)实体类
 * 
 * @author 世峰户外商城
 * @version Revision: 1.00 Date: 2015-11-13 16:09:22
 */
public class Authorization implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// id
	private String aId;
	// 角色ID
	private String rId;
	// 菜单ID
	private String mId;

	public String getaId() {
		return aId;
	}

	public void setaId(String aId) {
		this.aId = aId;
	}

	public String getrId() {
		return rId;
	}

	public void setrId(String rId) {
		this.rId = rId;
	}

	public String getmId() {
		return mId;
	}

	public void setmId(String mId) {
		this.mId = mId;
	}

}
