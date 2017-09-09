package com.op.entity.insurance;

import java.util.List;

public class AsyncOrderInfo {

	
	private String insureNum;//		投保单号
	private String partnerId;//		渠道商身份标识，由慧择指定
	private List<AsyncPolicyInfo> policyList;//		保单信息列表
	private int resultCode;//		出单状态 0：出单成功，1：出单失败
	private String errorInfo;//		返回出单失败原因
	public String getInsureNum() {
		return insureNum;
	}
	public void setInsureNum(String insureNum) {
		this.insureNum = insureNum;
	}
	public String getPartnerId() {
		return partnerId;
	}
	public void setPartnerId(String partnerId) {
		this.partnerId = partnerId;
	}
	public List<AsyncPolicyInfo> getPolicyList() {
		return policyList;
	}
	public void setPolicyList(List<AsyncPolicyInfo> policyList) {
		this.policyList = policyList;
	}
	public int getResultCode() {
		return resultCode;
	}
	public void setResultCode(int resultCode) {
		this.resultCode = resultCode;
	}
	public String getErrorInfo() {
		return errorInfo;
	}
	public void setErrorInfo(String errorInfo) {
		this.errorInfo = errorInfo;
	}
	
	
}
