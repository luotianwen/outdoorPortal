package com.op.dto;

import java.io.Serializable;

/**
 * 项目名：outdoorPortal 类描述：数据库权限封装对象 创建人：Yan 创建时间： 2015-11-17 上午9:31:22
 * 最后修改时间：2015-11-17上午9:31:22
 */
public class ShiroDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// 角色ID
	private String roleId;
	// 访问资源路径
	private String path;

	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

}
