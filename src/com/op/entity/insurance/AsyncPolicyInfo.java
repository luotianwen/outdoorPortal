package com.op.entity.insurance;

public class AsyncPolicyInfo {

	private int productId;//		产品ID
	private String productName;//		产品名称
	private int planId;//		计划ID
	private String planName;//		计划名称
	private String applicant;//		投保人姓名
	private String insurant;//		被保人姓名
	private String startDate;//		起保日期 格式：yyyy-MM-dd
	private String endDate;//		终保日期 格式：yyyy-MM-dd
	private int totalnum;//		份数
	private int issueState;//		出单状态 -1：出单失败 1：未支付 10：待出单 20：已出单 30：已过期 40：退保中 50：已退保
	private String signKey;//		签名，预签名数据：投保单号+计划ID
	private String policyNum;//		保险公司保单号
	public int getProductId() {
		return productId;
	}
	public void setProductId(int productId) {
		this.productId = productId;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public int getPlanId() {
		return planId;
	}
	public void setPlanId(int planId) {
		this.planId = planId;
	}
	public String getPlanName() {
		return planName;
	}
	public void setPlanName(String planName) {
		this.planName = planName;
	}
	public String getApplicant() {
		return applicant;
	}
	public void setApplicant(String applicant) {
		this.applicant = applicant;
	}
	public String getInsurant() {
		return insurant;
	}
	public void setInsurant(String insurant) {
		this.insurant = insurant;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	public int getTotalnum() {
		return totalnum;
	}
	public void setTotalnum(int totalnum) {
		this.totalnum = totalnum;
	}
	public int getIssueState() {
		return issueState;
	}
	public void setIssueState(int issueState) {
		this.issueState = issueState;
	}
	public String getSignKey() {
		return signKey;
	}
	public void setSignKey(String signKey) {
		this.signKey = signKey;
	}
	public String getPolicyNum() {
		return policyNum;
	}
	public void setPolicyNum(String policyNum) {
		this.policyNum = policyNum;
	}
	
	
	
	
}
