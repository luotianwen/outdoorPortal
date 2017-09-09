package com.op.spot.entity;

import java.io.Serializable;
import java.util.Date;
/** 
 * 订单出游人(s_orderTravelPeople)实体类
 * @author Win Zhong 
 * @version Revision: 1.00 
 *  Date: 2016-09-23 13:24:11 
 */  
public class S_orderTravelPeople implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

 	//id
  	 private int otp_id;
 	//用户id
  	 private int user_id;
 	//订单id
  	 private String orderId;
 	//对接方订单编号
  	 private String agentOrderId;
 	//产品编号
  	 private String productSn;
 	//产品名称
  	 private String productName;
 	//产品种类
  	 private int productType;
 	//产品单价（单位：分）
  	 private double unitPrice;
 	//产品数量
  	 private int count;
 	//建议售价（单位：分）
  	 private double retailPrice;
 	//结算价格（单位：分）
  	 private double settlementPrice;
 	//入园日期
  	 private String inDate;
 	//电子票开始时间
  	 private String validTimeBegin;
 	//电子票结束时间
  	 private String validTimeEnd;
 	//姓名
  	 private String visitorName;
 	//手机号
  	 private String visitorPhone;
 	//证件类型
  	 private String cardType;
 	//证件号码
  	 private String cardNum;
 	//性别（0:男,1:女）
  	 private int sex;
 	//辅助码
  	 private String codeNumber;
 	//短信内容
  	 private String emsContent;
 	//电子票日期
  	 private String ticketTimeInfo;
 	//订单状态（0：等待付款、1：已付款、2：等待出票、3：已出票、4：申请退票、5：部分申请退票、6：退票成功、7：部分退票成功、8：拒绝退票、9：已完成、10：已取消、11：退票中、12：退票失败）
  	 private int status;
 	//付款状态（0：等待支付、1：付款成功、2：退款中、3：已退款、4：等待退款）
  	 private int pay_status;
 	//付款时间
  	 private Date pay_time;
 	//电子票已验证数量
  	 private int usedNum;
 	//电子票使用状态
  	 private int usedState;
 	//支付宝交易号
  	 private String trade_no;
 	//操作是否成功
  	 private boolean isSuccess;
 	//错误码
  	 private String errorCode;
 	//错误说明
  	 private String errorMsg;
 	//处理完成时间
  	 private String dealTime;



	 
    /**
    *id
	* @return
    */ 
	public int getOtp_id() {
		return otp_id;
	}
    /**
    *id
	* @param type
    */ 
	public void setOtp_id(int otp_id) {
		this.otp_id = otp_id;
	}
    /**
    *用户id
	* @return
    */ 
	public int getUser_id() {
		return user_id;
	}
    /**
    *用户id
	* @param type
    */ 
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
    /**
    *订单id
	* @return
    */ 
	public String getOrderId() {
		return orderId;
	}
    /**
    *订单id
	* @param type
    */ 
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
    /**
    *对接方订单编号
	* @return
    */ 
	public String getAgentOrderId() {
		return agentOrderId;
	}
    /**
    *对接方订单编号
	* @param type
    */ 
	public void setAgentOrderId(String agentOrderId) {
		this.agentOrderId = agentOrderId;
	}
    /**
    *产品编号
	* @return
    */ 
	public String getProductSn() {
		return productSn;
	}
    /**
    *产品编号
	* @param type
    */ 
	public void setProductSn(String productSn) {
		this.productSn = productSn;
	}
    /**
    *产品名称
	* @return
    */ 
	public String getProductName() {
		return productName;
	}
    /**
    *产品名称
	* @param type
    */ 
	public void setProductName(String productName) {
		this.productName = productName;
	}
    /**
    *产品种类
	* @return
    */ 
	public int getProductType() {
		return productType;
	}
    /**
    *产品种类
	* @param type
    */ 
	public void setProductType(int productType) {
		this.productType = productType;
	}
    /**
    *产品单价（单位：分）
	* @return
    */ 
	public double getUnitPrice() {
		return unitPrice;
	}
    /**
    *产品单价（单位：分）
	* @param type
    */ 
	public void setUnitPrice(double unitPrice) {
		this.unitPrice = unitPrice;
	}
    /**
    *产品数量
	* @return
    */ 
	public int getCount() {
		return count;
	}
    /**
    *产品数量
	* @param type
    */ 
	public void setCount(int count) {
		this.count = count;
	}
    /**
    *建议售价（单位：分）
	* @return
    */ 
	public double getRetailPrice() {
		return retailPrice;
	}
    /**
    *建议售价（单位：分）
	* @param type
    */ 
	public void setRetailPrice(double retailPrice) {
		this.retailPrice = retailPrice;
	}
    /**
    *结算价格（单位：分）
	* @return
    */ 
	public double getSettlementPrice() {
		return settlementPrice;
	}
    /**
    *结算价格（单位：分）
	* @param type
    */ 
	public void setSettlementPrice(double settlementPrice) {
		this.settlementPrice = settlementPrice;
	}
    /**
    *入园日期
	* @return
    */ 
	public String getInDate() {
		return inDate;
	}
    /**
    *入园日期
	* @param type
    */ 
	public void setInDate(String inDate) {
		this.inDate = inDate;
	}
    /**
    *电子票开始时间
	* @return
    */ 
	public String getValidTimeBegin() {
		return validTimeBegin;
	}
    /**
    *电子票开始时间
	* @param type
    */ 
	public void setValidTimeBegin(String validTimeBegin) {
		this.validTimeBegin = validTimeBegin;
	}
    /**
    *电子票结束时间
	* @return
    */ 
	public String getValidTimeEnd() {
		return validTimeEnd;
	}
    /**
    *电子票结束时间
	* @param type
    */ 
	public void setValidTimeEnd(String validTimeEnd) {
		this.validTimeEnd = validTimeEnd;
	}
    /**
    *姓名
	* @return
    */ 
	public String getVisitorName() {
		return visitorName;
	}
    /**
    *姓名
	* @param type
    */ 
	public void setVisitorName(String visitorName) {
		this.visitorName = visitorName;
	}
    /**
    *手机号
	* @return
    */ 
	public String getVisitorPhone() {
		return visitorPhone;
	}
    /**
    *手机号
	* @param type
    */ 
	public void setVisitorPhone(String visitorPhone) {
		this.visitorPhone = visitorPhone;
	}
    /**
    *证件类型
	* @return
    */ 
	public String getCardType() {
		return cardType;
	}
    /**
    *证件类型
	* @param type
    */ 
	public void setCardType(String cardType) {
		this.cardType = cardType;
	}
    /**
    *证件号码
	* @return
    */ 
	public String getCardNum() {
		return cardNum;
	}
    /**
    *证件号码
	* @param type
    */ 
	public void setCardNum(String cardNum) {
		this.cardNum = cardNum;
	}
    /**
    *性别（0:男,1:女）
	* @return
    */ 
	public int getSex() {
		return sex;
	}
    /**
    *性别（0:男,1:女）
	* @param type
    */ 
	public void setSex(int sex) {
		this.sex = sex;
	}
    /**
    *辅助码
	* @return
    */ 
	public String getCodeNumber() {
		return codeNumber;
	}
    /**
    *辅助码
	* @param type
    */ 
	public void setCodeNumber(String codeNumber) {
		this.codeNumber = codeNumber;
	}
    /**
    *短信内容
	* @return
    */ 
	public String getEmsContent() {
		return emsContent;
	}
    /**
    *短信内容
	* @param type
    */ 
	public void setEmsContent(String emsContent) {
		this.emsContent = emsContent;
	}
    /**
    *电子票日期
	* @return
    */ 
	public String getTicketTimeInfo() {
		return ticketTimeInfo;
	}
    /**
    *电子票日期
	* @param type
    */ 
	public void setTicketTimeInfo(String ticketTimeInfo) {
		this.ticketTimeInfo = ticketTimeInfo;
	}
    /**
    *订单状态（0：等待付款、1：已付款、2：等待出票、3：已出票、4：申请退票、5：部分申请退票、6：退票成功、7：部分退票成功、8：拒绝退票、9：已完成、10：已取消、11：退票中、12：退票失败）
	* @return
    */ 
	public int getStatus() {
		return status;
	}
    /**
    *订单状态（0：等待付款、1：已付款、2：等待出票、3：已出票、4：申请退票、5：部分申请退票、6：退票成功、7：部分退票成功、8：拒绝退票、9：已完成、10：已取消、11：退票中、12：退票失败）
	* @param type
    */ 
	public void setStatus(int status) {
		this.status = status;
	}
    /**
    *付款状态（0：等待支付、1：付款成功、2：退款中、3：已退款、4：等待退款）
	* @return
    */ 
	public int getPay_status() {
		return pay_status;
	}
    /**
    *付款状态（0：等待支付、1：付款成功、2：退款中、3：已退款、4：等待退款）
	* @param type
    */ 
	public void setPay_status(int pay_status) {
		this.pay_status = pay_status;
	}
    /**
    *付款时间
	* @return
    */ 
	public Date getPay_time() {
		return pay_time;
	}
    /**
    *付款时间
	* @param type
    */ 
	public void setPay_time(Date pay_time) {
		this.pay_time = pay_time;
	}
    /**
    *电子票已验证数量
	* @return
    */ 
	public int getUsedNum() {
		return usedNum;
	}
    /**
    *电子票已验证数量
	* @param type
    */ 
	public void setUsedNum(int usedNum) {
		this.usedNum = usedNum;
	}
    /**
    *电子票使用状态
	* @return
    */ 
	public int getUsedState() {
		return usedState;
	}
    /**
    *电子票使用状态
	* @param type
    */ 
	public void setUsedState(int usedState) {
		this.usedState = usedState;
	}
    /**
    *支付宝交易号
	* @return
    */ 
	public String getTrade_no() {
		return trade_no;
	}
    /**
    *支付宝交易号
	* @param type
    */ 
	public void setTrade_no(String trade_no) {
		this.trade_no = trade_no;
	}
    /**
    *操作是否成功
	* @return
    */ 
	public boolean getIsSuccess() {
		return isSuccess;
	}
    /**
    *操作是否成功
	* @param type
    */ 
	public void setIsSuccess(boolean isSuccess) {
		this.isSuccess = isSuccess;
	}
    /**
    *错误码
	* @return
    */ 
	public String getErrorCode() {
		return errorCode;
	}
    /**
    *错误码
	* @param type
    */ 
	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}
    /**
    *错误说明
	* @return
    */ 
	public String getErrorMsg() {
		return errorMsg;
	}
    /**
    *错误说明
	* @param type
    */ 
	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}
    /**
    *处理完成时间
	* @return
    */ 
	public String getDealTime() {
		return dealTime;
	}
    /**
    *处理完成时间
	* @param type
    */ 
	public void setDealTime(String dealTime) {
		this.dealTime = dealTime;
	}
	
}
