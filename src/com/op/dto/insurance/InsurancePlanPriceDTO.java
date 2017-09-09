package com.op.dto.insurance;

import java.io.Serializable;

public class InsurancePlanPriceDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

 	//保险计划ID
  	 private int planId;
 	//保险产品ID
  	 private int productId;
 	//方案代码，每一款保险公司产品的方案代码都不相同，由慧择分配
  	 private String caseCode;
 	//计划名称
  	 private String planName;
 	//产品名称
  	 private String productName;
 	//公司名称
  	 private String companyName;
	private double price;
	public String getCaseCode() {
		return caseCode;
	}
	public void setCaseCode(String caseCode) {
		this.caseCode = caseCode;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public int getPlanId() {
		return planId;
	}
	public void setPlanId(int planId) {
		this.planId = planId;
	}
	public int getProductId() {
		return productId;
	}
	public void setProductId(int productId) {
		this.productId = productId;
	}
	public String getPlanName() {
		return planName;
	}
	public void setPlanName(String planName) {
		this.planName = planName;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	 
	

}
