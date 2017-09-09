package com.op.entity.insurance;

import java.io.Serializable;
/** 
 * 保险被保人(Insured)实体类
 * @author Win Zhong 
 * @version Revision: 1.00 
 *  Date: 2016-03-16 11:19:59 
 */  
public class Insured implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

 	//id
  	 private String id;
 	//被保人ID，由渠道商传递（同一单此字段不能重复，只能为Int范围内的数字）
  	 private String insurantId;
 	//投保单号
  	 private String insureNum;
 	//保险公司保单号
  	 private String policyNum;
 	//被保人姓名
  	 private String cName;
 	//被保险人姓名拼音，境外旅游险必填
  	 private String eName;
 	//被保险人性别 0：女 1：男
  	 private String sex;
 	//被保险人证件类型
  	 private int cardTypeId;
 	//被保人证件号码
  	 private String cardCode;
 	//被保险人出生日期 格式：yyyy-MM-dd
  	 private String birthday;
 	//与投保人关系
  	 private int relationId;
 	//被保险人购买份数
  	 private int count;
 	//产品结算价（单位：元）
  	 private double singlePrice;
 	//航班号，航意险必填
  	 private String fltNo;
 	//起飞时间，航意险必填
  	 private String fltDate;
 	//所在地区，特定产品必填
  	 private String city;
 	//出行目的
  	 private int tripPurposeId;
 	//出行目的地
  	 private String destination;
 	//签证办理城市
  	 private String visaCity;
 	//被保人职业信息（职业代码、职业名称、职业等级，以 | 分割，如“100401|电子业-工程师|2”），部分产品必填
  	 private String jobInfo;
 	//手机号码
  	 private String mobile;
 	//国籍，部分产品必填
  	 private String country;
 	//保险产品ID
  	 private int productId;
 	//保险产品名称
  	 private String productName;
 	//计划ID
  	 private int planId;
 	//计划名称
  	 private String planName;
 	//投保人姓名
  	 private String applicant;

  	 //联系人ID
  	 private String linkman_id;
  	 //订单ID
  	 private String order_id;
	 
    /**
    *id
    */ 
	public String getId() {
		return id;
	}
    /**
    *id
    */ 
	public void setId(String id) {
		this.id = id;
	}
    /**
    *被保人ID，由渠道商传递（同一单此字段不能重复，只能为Int范围内的数字）
    */ 
	public String getInsurantId() {
		return insurantId;
	}
    /**
    *被保人ID，由渠道商传递（同一单此字段不能重复，只能为Int范围内的数字）
    */ 
	public void setInsurantId(String insurantId) {
		this.insurantId = insurantId;
	}
    /**
    *投保单号
    */ 
	public String getInsureNum() {
		return insureNum;
	}
    /**
    *投保单号
    */ 
	public void setInsureNum(String insureNum) {
		this.insureNum = insureNum;
	}
    /**
    *保险公司保单号
    */ 
	public String getPolicyNum() {
		return policyNum;
	}
    /**
    *保险公司保单号
    */ 
	public void setPolicyNum(String policyNum) {
		this.policyNum = policyNum;
	}
    /**
    *被保人姓名
    */ 
	public String getCName() {
		return cName;
	}
    /**
    *被保人姓名
    */ 
	public void setCName(String cName) {
		this.cName = cName;
	}
    /**
    *被保险人姓名拼音，境外旅游险必填
    */ 
	public String getEName() {
		return eName;
	}
    /**
    *被保险人姓名拼音，境外旅游险必填
    */ 
	public void setEName(String eName) {
		this.eName = eName;
	}
    /**
    *被保险人性别 0：女 1：男
    */ 
	public String getSex() {
		return sex;
	}
    /**
    *被保险人性别 0：女 1：男
    */ 
	public void setSex(String sex) {
		this.sex = sex;
	}
    /**
    *被保险人证件类型
    */ 
	public int getCardTypeId() {
		return cardTypeId;
	}
    /**
    *被保险人证件类型
    */ 
	public void setCardTypeId(int cardTypeId) {
		this.cardTypeId = cardTypeId;
	}
    /**
    *被保人证件号码
    */ 
	public String getCardCode() {
		return cardCode;
	}
    /**
    *被保人证件号码
    */ 
	public void setCardCode(String cardCode) {
		this.cardCode = cardCode;
	}
    /**
    *被保险人出生日期 格式：yyyy-MM-dd
    */ 
	public String getBirthday() {
		return birthday;
	}
    /**
    *被保险人出生日期 格式：yyyy-MM-dd
    */ 
	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}
    /**
    *与投保人关系
    */ 
	public int getRelationId() {
		return relationId;
	}
    /**
    *与投保人关系
    */ 
	public void setRelationId(int relationId) {
		this.relationId = relationId;
	}
    /**
    *被保险人购买份数
    */ 
	public int getCount() {
		return count;
	}
    /**
    *被保险人购买份数
    */ 
	public void setCount(int count) {
		this.count = count;
	}
    /**
    *产品结算价（单位：元）
    */ 
	public double getSinglePrice() {
		return singlePrice;
	}
    /**
    *产品结算价（单位：元）
    */ 
	public void setSinglePrice(double singlePrice) {
		this.singlePrice = singlePrice;
	}
    /**
    *航班号，航意险必填
    */ 
	public String getFltNo() {
		return fltNo;
	}
    /**
    *航班号，航意险必填
    */ 
	public void setFltNo(String fltNo) {
		this.fltNo = fltNo;
	}
    /**
    *起飞时间，航意险必填
    */ 
	public String getFltDate() {
		return fltDate;
	}
    /**
    *起飞时间，航意险必填
    */ 
	public void setFltDate(String fltDate) {
		this.fltDate = fltDate;
	}
    /**
    *所在地区，特定产品必填
    */ 
	public String getCity() {
		return city;
	}
    /**
    *所在地区，特定产品必填
    */ 
	public void setCity(String city) {
		this.city = city;
	}
    /**
    *出行目的
    */ 
	public int getTripPurposeId() {
		return tripPurposeId;
	}
    /**
    *出行目的
    */ 
	public void setTripPurposeId(int tripPurposeId) {
		this.tripPurposeId = tripPurposeId;
	}
    /**
    *出行目的地
    */ 
	public String getDestination() {
		return destination;
	}
    /**
    *出行目的地
    */ 
	public void setDestination(String destination) {
		this.destination = destination;
	}
    /**
    *签证办理城市
    */ 
	public String getVisaCity() {
		return visaCity;
	}
    /**
    *签证办理城市
    */ 
	public void setVisaCity(String visaCity) {
		this.visaCity = visaCity;
	}
    /**
    *被保人职业信息（职业代码、职业名称、职业等级，以 | 分割，如“100401|电子业-工程师|2”），部分产品必填
    */ 
	public String getJobInfo() {
		return jobInfo;
	}
    /**
    *被保人职业信息（职业代码、职业名称、职业等级，以 | 分割，如“100401|电子业-工程师|2”），部分产品必填
    */ 
	public void setJobInfo(String jobInfo) {
		this.jobInfo = jobInfo;
	}
    /**
    *手机号码
    */ 
	public String getMobile() {
		return mobile;
	}
    /**
    *手机号码
    */ 
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
    /**
    *国籍，部分产品必填
    */ 
	public String getCountry() {
		return country;
	}
    /**
    *国籍，部分产品必填
    */ 
	public void setCountry(String country) {
		this.country = country;
	}
    /**
    *保险产品ID
    */ 
	public int getProductId() {
		return productId;
	}
    /**
    *保险产品ID
    */ 
	public void setProductId(int productId) {
		this.productId = productId;
	}
    /**
    *保险产品名称
    */ 
	public String getProductName() {
		return productName;
	}
    /**
    *保险产品名称
    */ 
	public void setProductName(String productName) {
		this.productName = productName;
	}
    /**
    *计划ID
    */ 
	public int getPlanId() {
		return planId;
	}
    /**
    *计划ID
    */ 
	public void setPlanId(int planId) {
		this.planId = planId;
	}
    /**
    *计划名称
    */ 
	public String getPlanName() {
		return planName;
	}
    /**
    *计划名称
    */ 
	public void setPlanName(String planName) {
		this.planName = planName;
	}
    /**
    *投保人姓名
    */ 
	public String getApplicant() {
		return applicant;
	}
    /**
    *投保人姓名
    */ 
	public void setApplicant(String applicant) {
		this.applicant = applicant;
	}
	/**
    *联系人ID
    */ 
	public String getLinkman_id() {
		return linkman_id;
	}
	/**
    *联系人ID
    */ 
	public void setLinkman_id(String linkman_id) {
		this.linkman_id = linkman_id;
	}
	/**
    *订单ID
    */ 
	public String getOrder_id() {
		return order_id;
	}
	/**
    *订单ID
    */ 
	public void setOrder_id(String order_id) {
		this.order_id = order_id;
	}
	
	
	
}
