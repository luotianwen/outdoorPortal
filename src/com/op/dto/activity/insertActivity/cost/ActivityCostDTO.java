package com.op.dto.activity.insertActivity.cost;

import java.io.Serializable;
import java.util.List;

public class ActivityCostDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// 活动ID
	String activityId;
	// 创建人
	String acp_create_user;
	// 费用类型（0:包括费用；1：可选费用）
	int acp_type;
	// 费用集合
	List<CostDTO> costs;
	// 增删改查标识
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
	public String getAcp_create_user() {
		return acp_create_user;
	}
	public void setAcp_create_user(String acp_create_user) {
		this.acp_create_user = acp_create_user;
	}
	public List<CostDTO> getCosts() {
		return costs;
	}
	public void setCosts(List<CostDTO> costs) {
		this.costs = costs;
	}
	/**
	 * 方法描述： 费用类型（0:包括费用；1：可选费用）
	 * 返回类型：int
	 * @return
	 */
	public int getAcp_type() {
		return acp_type;
	}
	public void setAcp_type(int acp_type) {
		this.acp_type = acp_type;
	}
	
	
}
