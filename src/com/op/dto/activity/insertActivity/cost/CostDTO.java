package com.op.dto.activity.insertActivity.cost;

import java.io.Serializable;
/**
 * =============================================================
 * 项目名：outdoorPortal
 * 类描述：保存活动额外费用DTO
 * 创建人：Yan
 * 创建时间： 2016-3-21
 * modification list：
 * =============================================================
 */
public class CostDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// 费用名称
	String acp_cost_name;
	// 单价/补差费用
	Double acp_cost;
	// 费用说明
	String acp_comment;
	// 是否保存当前费用(0:否；1：是) checkbox判断
	String isSave;
	
	
	public String getAcp_cost_name() {
		return acp_cost_name;
	}
	public void setAcp_cost_name(String acp_cost_name) {
		this.acp_cost_name = acp_cost_name;
	}
	public String getAcp_comment() {
		return acp_comment;
	}
	public void setAcp_comment(String acp_comment) {
		this.acp_comment = acp_comment;
	}
	public Double getAcp_cost() {
		return acp_cost;
	}
	public void setAcp_cost(Double acp_cost) {
		this.acp_cost = acp_cost;
	}
	public String getIsSave() {
		return isSave;
	}
	public void setIsSave(String isSave) {
		this.isSave = isSave;
	}
}
