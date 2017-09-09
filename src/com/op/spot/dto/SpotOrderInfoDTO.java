package com.op.spot.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import com.op.spot.entity.S_orderTravelPeople;

/**
 * 景点订单详情
 * @author sen
 *
 */
public class SpotOrderInfoDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	// 订单编号
	private String orderId;
	// 用户id
	private int user_id;
	// 渠道id
	private int channel;
	// 对接方订单编号（暂以出游人为准）
	private String agentOrderId;
	// 产品编号
	private String productSn;
	// 产品名称
	private String productName;
	// 产品种类
	private int productType;
	// 产品单价（单位：分）
	private double unitPrice;
	// 产品数量
	private int count;
	// 建议售价（单位：分）
	private double retailPrice;
	// 结算价格（单位：分）
	private double settlementPrice;
	// 订单支付总金额
	private double order_total_price;
	// 是否发送短信（1、发送；0：不发送）
	private int isSendSms;
	// 支付方式（1、在线支付）
	private int payType;
	// 入园日期
	private String inDate;
	// 电子票开始时间
	private String validTimeBegin;
	// 电子票结束时间
	private String validTimeEnd;
	// 备用
	private String feature;
	// 取票人姓名
	private String fetchName;
	// 取票人手机
	private String fetchPhone;
	// 取票人性别（0:男,1:女）
	private int sex;
	// 证件类型
	private String cardType;
	// 证件号码
	private String cardNum;
	// 辅助码
	private String codeNumber;
	// 短信内容
	private String emsContent;
	// 电子票日期
	private String ticketTimeInfo;
	// 是否实名认证(0：否、1：是)
	private int isRealName;
	// 订单状态
	private int status;
	// 付款状态（0：等待支付、1：付款成功、2：退款中、3：已退款）
	private int pay_status;
	// 付款时间
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date pay_time;
	// 电子票已验证数量
	private int usedNum;
	// 电子票使用状态
	private int usedState;
	// 支付宝交易号
	private String trade_no;
	// 创建时间
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date create_time;
	// 操作是否成功
	private boolean isSuccess;
	// 错误码
	private String errorCode;
	// 错误说明
	private String errorMsg;
	// 处理完成时间
	private String dealTime;

	//等待支付时间
	private String waitPayTime;
	
	//是否支持退款
	private String p_isRefund;
	//景点地址
	private String s_address;
	//景点名称
	private String s_name;
	
	// 订单出游人
	private List<S_orderTravelPeople> travelPeople;
	
	
	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	public int getChannel() {
		return channel;
	}

	public void setChannel(int channel) {
		this.channel = channel;
	}

	public String getAgentOrderId() {
		return agentOrderId;
	}

	public void setAgentOrderId(String agentOrderId) {
		this.agentOrderId = agentOrderId;
	}

	public String getProductSn() {
		return productSn;
	}

	public void setProductSn(String productSn) {
		this.productSn = productSn;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public int getProductType() {
		return productType;
	}

	public void setProductType(int productType) {
		this.productType = productType;
	}

	public double getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(double unitPrice) {
		this.unitPrice = unitPrice;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public double getRetailPrice() {
		return retailPrice;
	}

	public void setRetailPrice(double retailPrice) {
		this.retailPrice = retailPrice;
	}

	public double getSettlementPrice() {
		return settlementPrice;
	}

	public void setSettlementPrice(double settlementPrice) {
		this.settlementPrice = settlementPrice;
	}

	public double getOrder_total_price() {
		return order_total_price;
	}

	public void setOrder_total_price(double order_total_price) {
		this.order_total_price = order_total_price;
	}

	public int getIsSendSms() {
		return isSendSms;
	}

	public void setIsSendSms(int isSendSms) {
		this.isSendSms = isSendSms;
	}

	public int getPayType() {
		return payType;
	}

	public void setPayType(int payType) {
		this.payType = payType;
	}

	public String getInDate() {
		return inDate;
	}

	public void setInDate(String inDate) {
		this.inDate = inDate;
	}

	public String getValidTimeBegin() {
		return validTimeBegin;
	}

	public void setValidTimeBegin(String validTimeBegin) {
		this.validTimeBegin = validTimeBegin;
	}

	public String getValidTimeEnd() {
		return validTimeEnd;
	}

	public void setValidTimeEnd(String validTimeEnd) {
		this.validTimeEnd = validTimeEnd;
	}

	public String getFeature() {
		return feature;
	}

	public void setFeature(String feature) {
		this.feature = feature;
	}

	public String getFetchName() {
		return fetchName;
	}

	public void setFetchName(String fetchName) {
		this.fetchName = fetchName;
	}

	public String getFetchPhone() {
		return fetchPhone;
	}

	public void setFetchPhone(String fetchPhone) {
		this.fetchPhone = fetchPhone;
	}

	public int getSex() {
		return sex;
	}

	public void setSex(int sex) {
		this.sex = sex;
	}

	public String getCardType() {
		return cardType;
	}

	public void setCardType(String cardType) {
		this.cardType = cardType;
	}

	public String getCardNum() {
		return cardNum;
	}

	public void setCardNum(String cardNum) {
		this.cardNum = cardNum;
	}

	public String getCodeNumber() {
		return codeNumber;
	}

	public void setCodeNumber(String codeNumber) {
		this.codeNumber = codeNumber;
	}

	public String getEmsContent() {
		return emsContent;
	}

	public void setEmsContent(String emsContent) {
		this.emsContent = emsContent;
	}

	public String getTicketTimeInfo() {
		return ticketTimeInfo;
	}

	public void setTicketTimeInfo(String ticketTimeInfo) {
		this.ticketTimeInfo = ticketTimeInfo;
	}

	public int getIsRealName() {
		return isRealName;
	}

	public void setIsRealName(int isRealName) {
		this.isRealName = isRealName;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public int getPay_status() {
		return pay_status;
	}

	public void setPay_status(int pay_status) {
		this.pay_status = pay_status;
	}

	public Date getPay_time() {
		return pay_time;
	}

	public void setPay_time(Date pay_time) {
		this.pay_time = pay_time;
	}

	public int getUsedNum() {
		return usedNum;
	}

	public void setUsedNum(int usedNum) {
		this.usedNum = usedNum;
	}

	public int getUsedState() {
		return usedState;
	}

	public void setUsedState(int usedState) {
		this.usedState = usedState;
	}

	public String getTrade_no() {
		return trade_no;
	}

	public void setTrade_no(String trade_no) {
		this.trade_no = trade_no;
	}

	public Date getCreate_time() {
		return create_time;
	}

	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}

	public boolean isSuccess() {
		return isSuccess;
	}

	public void setSuccess(boolean isSuccess) {
		this.isSuccess = isSuccess;
	}

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	public String getErrorMsg() {
		return errorMsg;
	}

	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}

	public String getDealTime() {
		return dealTime;
	}

	public void setDealTime(String dealTime) {
		this.dealTime = dealTime;
	}

	public List<S_orderTravelPeople> getTravelPeople() {
		return travelPeople;
	}

	public void setTravelPeople(List<S_orderTravelPeople> travelPeople) {
		this.travelPeople = travelPeople;
	}

	public String getWaitPayTime() {
		return waitPayTime;
	}

	public void setWaitPayTime(String waitPayTime) {
		this.waitPayTime = waitPayTime;
	}

	public String getP_isRefund() {
		return p_isRefund;
	}

	public void setP_isRefund(String p_isRefund) {
		this.p_isRefund = p_isRefund;
	}

	public String getS_address() {
		return s_address;
	}

	public void setS_address(String s_address) {
		this.s_address = s_address;
	}

	public String getS_name() {
		return s_name;
	}

	public void setS_name(String s_name) {
		this.s_name = s_name;
	}

}
