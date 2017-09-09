package com.op.entity.activity.cost;

import java.io.Serializable;
import java.util.Date;

/**
 * 活动费用表(activeCorrelationCost)实体类
 * 
 * @author 世峰户外商城
 * @version Revision: 1.00 Date: 2016-03-17 13:51:04
 */
public class ActiveCorrelationCost implements Serializable {

	/**
	 * 
	 */
	static final long serialVersionUID = 1L;

	// id
	String acp_id;
	
	// 费用名称
	String acp_cost_name;
	
	// 单价/补差费用
	double acp_cost;
	
	// 费用说明
	String acp_comment;
	
	// 费用类型（0:包括费用；1：可选费用）
	String acp_type;
	
	// 创建人
	String acp_create_user;
	
	// 创建时间
	Date acp_create_time;
	
	// 活动ID
	int acp_activity_id;
	
	// 保险排序，保险为0，其余的 都为1
	int acp_order;
	
	
	

	public int getAcp_order() {
		return acp_order;
	}

	public void setAcp_order(int acp_order) {
		this.acp_order = acp_order;
	}

	/**
	 * id
	 */
	public String getAcp_id() {
		return acp_id;
	}

	public void setAcp_id(String acp_id) {
		this.acp_id = acp_id;
	}

	/**
	 * 费用名称
	 */
	public String getAcp_cost_name() {
		return acp_cost_name;
	}

	public void setAcp_cost_name(String acp_cost_name) {
		this.acp_cost_name = acp_cost_name;
	}

	/**
	 * 单价/补差费用
	 */
	public double getAcp_cost() {
		return acp_cost;
	}

	public void setAcp_cost(double acp_cost) {
		this.acp_cost = acp_cost;
	}

	/**
	 * 费用说明
	 */
	public String getAcp_comment() {
		return acp_comment;
	}

	public void setAcp_comment(String acp_comment) {
		this.acp_comment = acp_comment;
	}

	/**
	 * 费用类型（0:包括费用；1：可选费用）
	 */
	public String getAcp_type() {
		return acp_type;
	}

	public void setAcp_type(String acp_type) {
		this.acp_type = acp_type;
	}

	/**
	 * 创建人
	 */
	public String getAcp_create_user() {
		return acp_create_user;
	}

	public void setAcp_create_user(String acp_create_user) {
		this.acp_create_user = acp_create_user;
	}

	/**
	 * 创建时间
	 */
	public Date getAcp_create_time() {
		return acp_create_time;
	}

	public void setAcp_create_time(Date acp_create_time) {
		this.acp_create_time = acp_create_time;
	}

	/**
	 * 活动ID
	 */
	public int getAcp_activity_id() {
		return acp_activity_id;
	}

	public void setAcp_activity_id(int acp_activity_id) {
		this.acp_activity_id = acp_activity_id;
	}

}
