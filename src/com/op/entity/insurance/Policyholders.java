package com.op.entity.insurance;

import java.io.Serializable;
import java.util.Date;
/** 
 * 保险投保人(policyholders)实体类
 * @author Win Zhong 
 * @version Revision: 1.00 
 *  Date: 2016-03-16 11:19:58 
 */  
public class Policyholders implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

 	//id
  	 private String id;
 	//购买用户ID
  	 private String user_id;
 	//保险交易流水号，每次请求不能相同
  	 private String transNo;
 	//投保产品ID
  	 private int productId;
 	//投保产品名称
  	 private String productName;
 	//投保保险公司名称
  	 private String companyName;
 	//投保计划ID
  	 private int planId;
 	//投保计划名称
  	 private String planName;
 	//投保单号
  	 private String insureNum;
 	//活动订单号
  	 private String order_id;
 	//方案代码，每一款保险公司产品的方案代码都不相同，由慧择分配
  	 private String caseCode;
 	//投保时间 格式：yyyy-MM-dd HH:mm:ss
  	 private String applicationDate;
 	//起保时间 格式：yyyy-MM-dd
  	 private String startDate;
 	//终保时间 格式：yyyy-MM-dd
  	 private String endDate;
 	//该投保单总价格（单位：元）
  	 private double priceTotal;
 	//被保人数量
  	 private int insurantCount;
 	//投保人姓名
  	 private String cName;
 	//投保人姓名拼音，境外旅游险必填
  	 private String eName;
 	//投保人证件类型
  	 private int cardTypeId;
 	//投保人证件号
  	 private String cardCode;
 	//投保人性别 0：女 1：男
  	 private int sex;
 	//投保人出生日期 格式：yyyy-MM-dd
  	 private String birthday;
 	//投保人移动电话
  	 private String mobile;
 	//投保人邮箱
  	 private String email;
 	//投保人职业信息（职业代码、职业名称、职业等级，以 | 分割，如“100401|电子业-工程师|2”） ，部分产品必填
  	 private String jobInfo;
 	//国籍，部分产品必填
  	 private String country;
 	//订单类型（1：活动订单保险）
  	 private String order_type;
 	//同步出单状态 0：出单成功，1：出单失败
  	 private int resultCode;
 	//返回出单失败原因
  	 private String errorInfo;
 	//总份数
  	 private int totalnum;
 	//异步出单状态 -1：出单失败 1：未支付 10：待出单 20：已出单 30：已过期 40：退保中 50：已退保
  	 private int issueState;
 	//保单是否已下载（0：已下载；1：未下载）
  	 private String isPolicyDownload;
 	//创建时间
  	 private Date create_time;
 	//更新时间
  	 private Date update_time;
  	 //联系人ID
  	 private String linkman_id;
  	 //退单状态（0:未退单，1:退单成功，2：退单失败）
  	 private int refundstatus;
  	 //退单结果
  	 private String refundmsg;
	 
    /**
    *id
	* @return
    */ 
	public String getId() {
		return id;
	}
    /**
    *id
	* @param type
    */ 
	public void setId(String id) {
		this.id = id;
	}
    /**
    *购买用户ID
	* @return
    */ 
	public String getUser_id() {
		return user_id;
	}
    /**
    *购买用户ID
	* @param type
    */ 
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
    /**
    *保险交易流水号，每次请求不能相同
	* @return
    */ 
	public String getTransNo() {
		return transNo;
	}
    /**
    *保险交易流水号，每次请求不能相同
	* @param type
    */ 
	public void setTransNo(String transNo) {
		this.transNo = transNo;
	}
    /**
    *投保产品ID
	* @return
    */ 
	public int getProductId() {
		return productId;
	}
    /**
    *投保产品ID
	* @param type
    */ 
	public void setProductId(int productId) {
		this.productId = productId;
	}
    /**
    *投保产品名称
	* @return
    */ 
	public String getProductName() {
		return productName;
	}
    /**
    *投保产品名称
	* @param type
    */ 
	public void setProductName(String productName) {
		this.productName = productName;
	}
    /**
    *投保保险公司名称
	* @return
    */ 
	public String getCompanyName() {
		return companyName;
	}
    /**
    *投保保险公司名称
	* @param type
    */ 
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
    /**
    *投保计划ID
	* @return
    */ 
	public int getPlanId() {
		return planId;
	}
    /**
    *投保计划ID
	* @param type
    */ 
	public void setPlanId(int planId) {
		this.planId = planId;
	}
    /**
    *投保计划名称
	* @return
    */ 
	public String getPlanName() {
		return planName;
	}
    /**
    *投保计划名称
	* @param type
    */ 
	public void setPlanName(String planName) {
		this.planName = planName;
	}
    /**
    *投保单号
	* @return
    */ 
	public String getInsureNum() {
		return insureNum;
	}
    /**
    *投保单号
	* @param type
    */ 
	public void setInsureNum(String insureNum) {
		this.insureNum = insureNum;
	}
    /**
    *活动订单号
	* @return
    */ 
	public String getOrder_id() {
		return order_id;
	}
    /**
    *活动订单号
	* @param type
    */ 
	public void setOrder_id(String order_id) {
		this.order_id = order_id;
	}
    /**
    *方案代码，每一款保险公司产品的方案代码都不相同，由慧择分配
	* @return
    */ 
	public String getCaseCode() {
		return caseCode;
	}
    /**
    *方案代码，每一款保险公司产品的方案代码都不相同，由慧择分配
	* @param type
    */ 
	public void setCaseCode(String caseCode) {
		this.caseCode = caseCode;
	}
    /**
    *投保时间 格式：yyyy-MM-dd HH:mm:ss
	* @return
    */ 
	public String getApplicationDate() {
		return applicationDate;
	}
    /**
    *投保时间 格式：yyyy-MM-dd HH:mm:ss
	* @param type
    */ 
	public void setApplicationDate(String applicationDate) {
		this.applicationDate = applicationDate;
	}
    /**
    *起保时间 格式：yyyy-MM-dd
	* @return
    */ 
	public String getStartDate() {
		return startDate;
	}
    /**
    *起保时间 格式：yyyy-MM-dd
	* @param type
    */ 
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
    /**
    *终保时间 格式：yyyy-MM-dd
	* @return
    */ 
	public String getEndDate() {
		return endDate;
	}
    /**
    *终保时间 格式：yyyy-MM-dd
	* @param type
    */ 
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
    /**
    *该投保单总价格（单位：元）
	* @return
    */ 
	public double getPriceTotal() {
		return priceTotal;
	}
    /**
    *该投保单总价格（单位：元）
	* @param type
    */ 
	public void setPriceTotal(double priceTotal) {
		this.priceTotal = priceTotal;
	}
    /**
    *被保人数量
	* @return
    */ 
	public int getInsurantCount() {
		return insurantCount;
	}
    /**
    *被保人数量
	* @param type
    */ 
	public void setInsurantCount(int insurantCount) {
		this.insurantCount = insurantCount;
	}
    /**
    *投保人姓名
	* @return
    */ 
	public String getCName() {
		return cName;
	}
    /**
    *投保人姓名
	* @param type
    */ 
	public void setCName(String cName) {
		this.cName = cName;
	}
    /**
    *投保人姓名拼音，境外旅游险必填
	* @return
    */ 
	public String getEName() {
		return eName;
	}
    /**
    *投保人姓名拼音，境外旅游险必填
	* @param type
    */ 
	public void setEName(String eName) {
		this.eName = eName;
	}
    /**
    *投保人证件类型
	* @return
    */ 
	public int getCardTypeId() {
		return cardTypeId;
	}
    /**
    *投保人证件类型
	* @param type
    */ 
	public void setCardTypeId(int cardTypeId) {
		this.cardTypeId = cardTypeId;
	}
    /**
    *投保人证件号
	* @return
    */ 
	public String getCardCode() {
		return cardCode;
	}
    /**
    *投保人证件号
	* @param type
    */ 
	public void setCardCode(String cardCode) {
		this.cardCode = cardCode;
	}
    /**
    *投保人性别 0：女 1：男
	* @return
    */ 
	public int getSex() {
		return sex;
	}
    /**
    *投保人性别 0：女 1：男
	* @param type
    */ 
	public void setSex(int sex) {
		this.sex = sex;
	}
    /**
    *投保人出生日期 格式：yyyy-MM-dd
	* @return
    */ 
	public String getBirthday() {
		return birthday;
	}
    /**
    *投保人出生日期 格式：yyyy-MM-dd
	* @param type
    */ 
	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}
    /**
    *投保人移动电话
	* @return
    */ 
	public String getMobile() {
		return mobile;
	}
    /**
    *投保人移动电话
	* @param type
    */ 
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
    /**
    *投保人邮箱
	* @return
    */ 
	public String getEmail() {
		return email;
	}
    /**
    *投保人邮箱
	* @param type
    */ 
	public void setEmail(String email) {
		this.email = email;
	}
    /**
    *投保人职业信息（职业代码、职业名称、职业等级，以 | 分割，如“100401|电子业-工程师|2”） ，部分产品必填
	* @return
    */ 
	public String getJobInfo() {
		return jobInfo;
	}
    /**
    *投保人职业信息（职业代码、职业名称、职业等级，以 | 分割，如“100401|电子业-工程师|2”） ，部分产品必填
	* @param type
    */ 
	public void setJobInfo(String jobInfo) {
		this.jobInfo = jobInfo;
	}
    /**
    *国籍，部分产品必填
	* @return
    */ 
	public String getCountry() {
		return country;
	}
    /**
    *国籍，部分产品必填
	* @param type
    */ 
	public void setCountry(String country) {
		this.country = country;
	}
    /**
    *订单类型（1：活动订单保险）
	* @return
    */ 
	public String getOrder_type() {
		return order_type;
	}
    /**
    *订单类型（1：活动订单保险）
	* @param type
    */ 
	public void setOrder_type(String order_type) {
		this.order_type = order_type;
	}
    /**
    *同步出单状态 0：出单成功，1：出单失败
	* @return
    */ 
	public int getResultCode() {
		return resultCode;
	}
    /**
    *同步出单状态 0：出单成功，1：出单失败
	* @param type
    */ 
	public void setResultCode(int resultCode) {
		this.resultCode = resultCode;
	}
    /**
    *返回出单失败原因
	* @return
    */ 
	public String getErrorInfo() {
		return errorInfo;
	}
    /**
    *返回出单失败原因
	* @param type
    */ 
	public void setErrorInfo(String errorInfo) {
		this.errorInfo = errorInfo;
	}
    /**
    *总份数
	* @return
    */ 
	public int getTotalnum() {
		return totalnum;
	}
    /**
    *总份数
	* @param type
    */ 
	public void setTotalnum(int totalnum) {
		this.totalnum = totalnum;
	}
    /**
    *异步出单状态 -1：出单失败 1：未支付 10：待出单 20：已出单 30：已过期 40：退保中 50：已退保
	* @return
    */ 
	public int getIssueState() {
		return issueState;
	}
    /**
    *异步出单状态 -1：出单失败 1：未支付 10：待出单 20：已出单 30：已过期 40：退保中 50：已退保
	* @param type
    */ 
	public void setIssueState(int issueState) {
		this.issueState = issueState;
	}
    /**
    *保单是否已下载（0：已下载；1：未下载）
	* @return
    */ 
	public String getIsPolicyDownload() {
		return isPolicyDownload;
	}
    /**
    *保单是否已下载（0：已下载；1：未下载）
	* @param type
    */ 
	public void setIsPolicyDownload(String isPolicyDownload) {
		this.isPolicyDownload = isPolicyDownload;
	}
    /**
    *创建时间
	* @return
    */ 
	public Date getCreate_time() {
		return create_time;
	}
    /**
    *创建时间
	* @param type
    */ 
	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}
    /**
    *更新时间
	* @return
    */ 
	public Date getUpdate_time() {
		return update_time;
	}
    /**
    *更新时间
	* @param type
    */ 
	public void setUpdate_time(Date update_time) {
		this.update_time = update_time;
	}
	/**
	 * 联系人ID
	 * @return
	 */
	public String getLinkman_id() {
		return linkman_id;
	}
	/**
	 * 联系人ID
	 * @return
	 */
	public void setLinkman_id(String linkman_id) {
		this.linkman_id = linkman_id;
	}
	/**
	 * 退单状态（0:未退单，1:退单成功，2：退单失败）
	 * @return
	 */
	public int getRefundstatus() {
		return refundstatus;
	}
	/**
	 * 退单状态（0:未退单，1:退单成功，2：退单失败）
	 * @param refundstatus
	 */
	public void setRefundstatus(int refundstatus) {
		this.refundstatus = refundstatus;
	}
	/**
	 * 退单结果
	 * @return
	 */
	public String getRefundmsg() {
		return refundmsg;
	}
	/**
	 * 退单结果
	 * @param refundmsg
	 */
	public void setRefundmsg(String refundmsg) {
		this.refundmsg = refundmsg;
	}
	
}
