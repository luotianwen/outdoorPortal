package com.op.entity.authority;

import java.io.Serializable;
import java.util.Date;

/**
 * 项目名：outdoorPortal 类描述：系统角色 创建人：Yan 创建时间： 2015-11-7 上午11:22:33
 * 最后修改时间：2015-11-7上午11:22:33
 */
public class Roles implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// id
	private String rId;
	// 角色名称
	private String rName;
	// 角色排序
	private int rOrder;
	// 更新用户
	private String rUpUser;
	// 更新时间
	private Date rUpTime;
	// 分组ID
	private String aId;

	
	public String getaId() {
		return aId;
	}

	public void setaId(String aId) {
		this.aId = aId;
	}

	public String getrUpUser() {
		return rUpUser;
	}

	public void setrUpUser(String rUpUser) {
		this.rUpUser = rUpUser;
	}

	public String getrId() {
		return rId;
	}

	public void setrId(String rId) {
		this.rId = rId;
	}

	public String getrName() {
		return rName;
	}

	public void setrName(String rName) {
		this.rName = rName;
	}

	public Date getrUpTime() {
		return rUpTime;
	}

	public void setrUpTime(Date rUpTime) {
		this.rUpTime = rUpTime;
	}

	public int getrOrder() {
		return rOrder;
	}

	public void setrOrder(int rOrder) {
		this.rOrder = rOrder;
	}
}
