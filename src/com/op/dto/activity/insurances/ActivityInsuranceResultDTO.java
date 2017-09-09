package com.op.dto.activity.insurances;

import java.io.Serializable;
/**
 * =============================================================
 * 项目名：outdoorPortal
 * 类描述：保险
 * 创建人：Yan
 * 创建时间： 2016-3-30
 * modification list：
 * =============================================================
 */
public class ActivityInsuranceResultDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	// 活动保险ID
	String id;
	
	// 产品名称
	String productName;
	
	// 计划名称
	String planName;
	
	// 最小期限
	String minDeadline;
	
	// 最长期限
	String maxDeadline;
	
	// 期限单位（1：天；2：年）
	String unit;
	
	// 产品价格（单位：元）
	Double price;
	
	
	

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getPlanName() {
		return planName;
	}

	public void setPlanName(String planName) {
		this.planName = planName;
	}

	public String getMinDeadline() {
		return minDeadline;
	}

	public void setMinDeadline(String minDeadline) {
		this.minDeadline = minDeadline;
	}

	public String getMaxDeadline() {
		return maxDeadline;
	}

	public void setMaxDeadline(String maxDeadline) {
		this.maxDeadline = maxDeadline;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}
	
	
	
}
