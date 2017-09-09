package com.op.entity.pointService;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 地点服务类型字典表(pointServiceType)实体类
 * 
 * @author 世峰户外商城
 * @version Revision: 1.00 Date: 2016-06-16 15:05:38
 */
public class PointServiceType implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	// id
	int id;
	// 分类ID
	int categoryId;
	// 类型名称
	String categoryName;
	// N级菜单
	int categoryLevel;
	// 类型父id
	int parentId;
	// 一级类型ID
	int oneTypeId;
	// 修改时间
	Date updateTime;
	// 修改人
	String updateUserId;
	// 修改人
	String updateUserName;
	// 菜单排序
	int orderNumber;
	
	// 是否锁定（0：否；10：是）
	int isLock;
	
	List<PointServiceType> list;
	
	

	public int getIsLock() {
		return isLock;
	}

	public void setIsLock(int isLock) {
		this.isLock = isLock;
	}

	public List<PointServiceType> getList() {
		return list;
	}

	public void setList(List<PointServiceType> list) {
		this.list = list;
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
	 * 分类ID
	 */
	public int getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}

	/**
	 * 类型名称
	 */
	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	/**
	 * N级菜单
	 */
	public int getCategoryLevel() {
		return categoryLevel;
	}

	public void setCategoryLevel(int categoryLevel) {
		this.categoryLevel = categoryLevel;
	}

	/**
	 * 类型父id
	 */
	public int getParentId() {
		return parentId;
	}

	public void setParentId(int parentId) {
		this.parentId = parentId;
	}

	/**
	 * 一级类型ID
	 */
	public int getOneTypeId() {
		return oneTypeId;
	}

	public void setOneTypeId(int oneTypeId) {
		this.oneTypeId = oneTypeId;
	}

	/**
	 * 修改时间
	 */
	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	/**
	 * 修改人
	 */
	public String getUpdateUserId() {
		return updateUserId;
	}

	public void setUpdateUserId(String updateUserId) {
		this.updateUserId = updateUserId;
	}

	/**
	 * 菜单排序
	 */
	public int getOrderNumber() {
		return orderNumber;
	}

	public void setOrderNumber(int orderNumber) {
		this.orderNumber = orderNumber;
	}

	public String getUpdateUserName() {
		return updateUserName;
	}

	public void setUpdateUserName(String updateUserName) {
		this.updateUserName = updateUserName;
	}
	
	
}
