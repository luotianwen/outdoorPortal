package com.op.dto.activity.equipment;

import java.util.List;

import com.op.entity.activity.equipment.ActiveEquipment;


/**
 * =============================================================
 * 项目名：outdoorPortal
 * 类描述：添加活动装备DTO
 * 创建人：Yan
 * 创建时间： 2016-3-28
 * modification list：
 * =============================================================
 */
public class InsertEquipmentDTO {

	// 活动ID
	String activityId;
	
	// 用户ID
	String userId;
	
	// 装备集合
	List<ActiveEquipment> aes;
	
	// 增删改查操作
	String crud;
	
	// 根据返回操作的数据数量来操作完成度
	int dataNum;
	

	public int getDataNum() {
		return dataNum;
	}

	public void setDataNum(int dataNum) {
		this.dataNum = dataNum;
	}

	public String getCrud() {
		return crud;
	}

	public void setCrud(String crud) {
		this.crud = crud;
	}

	public String getActivityId() {
		return activityId;
	}

	public void setActivityId(String activityId) {
		this.activityId = activityId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public List<ActiveEquipment> getAes() {
		return aes;
	}

	public void setAes(List<ActiveEquipment> aes) {
		this.aes = aes;
	}
	
	
}
