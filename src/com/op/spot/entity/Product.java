package com.op.spot.entity;

import java.io.Serializable;
import java.util.List;
/** 
 * 景点产品(s_product)实体类
 * @author Win Zhong 
 * @version Revision: 1.00 
 *  Date: 2016-09-22 17:56:04 
 */  
public class Product implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

 	//id
  	 private int id;
 	//产品编号
  	 private String productSn;
 	//产品名称
  	 private String productName;
 	//计量单位（张、套）
  	 private String productUnit;
 	//支付类型（0预付  1到付）
  	 private String payType;
 	//渠道id
  	 private int channel;
 	//资源ID
  	 private int suppliersId;
 	//分类ID
  	 private int catId;
 	//标签ID
  	 private String tagId;
 	//产品种类 默认为1
  	 private int productType;
 	//门票类型（0为儿童票、1为成人票 、2为老人票、3学生票、4家庭套票、5情侣套票）
  	 private String ticketType;
 	//上架状态（0下架 1上架）
  	 private String saleStatus;
 	//是否免运费（0免 1是）
  	 private String isShipping;
 	//是否实名（0否 1是）
  	 private String isTrue;
 	//是否限购（0不限购 大于0限购，规则限购类型）
  	 private String limitNumber;
 	//限购类型（空为不限购 0按身份证限购 1按手机号 每天每个产品限制 ）
  	 private String limitType;
 	//限购时间（1按天、2 按周、3 按月）
  	 private String limitTime;
 	//预定时间限制（默认值就0|0 0|0表示无限制 9:00|16:00表示只能9点至16点下单）
  	 private String limitOrderTime;
 	//可以预定天数
  	 private String calendarRange;
 	//是否支持退票（1：是、2否）
  	 private String isRefund;
 	//提前退票天数
  	 private String refundDay;
 	//提前退票时间
  	 private String refundTime;
 	//退票限制
  	 private String refundRule;
 	//产品有效期
  	 private String productValidTime;
 	//电子票有效期类型
  	 private String ticketTimeType;
 	//电子票有效期
  	 private String ticketTimeInfo;
 	//是否是价格日历（1是0否 1是获取价格日历）
  	 private String priceType;
 	//市场价格（单位：分）
  	 private double salesPrice;
 	//建议售价（单位：分）
  	 private double retailPrice;
 	//结算价格（单位：分）
  	 private double settlementPrice;
 	//屏蔽日期
  	 private String exceptDate;
 	//屏蔽星期
  	 private String exceptWeek;
 	//特例日期
  	 private String specialDate;
 	//优惠信息
  	 private String pfnerInfo;
 	//产品介绍
  	 private String productDesc;
 	//费用包含
  	 private String costIncludes;
 	//验证窗口
  	 private String confirmInfo;
 	//重要提示
  	 private String importInfo;
 	//使用须知
  	 private String usedInfo;
 	//开放时间
  	 private String openTime;
 	//入园时间
  	 private String enterTime;
 	//参团须知
  	 private String joinInfo;
 	//退款介绍
  	 private String refundInfo;
 	//补充内容
  	 private String additionInfo;
 	//产品图片
  	 private String picUrl;
 	//修改时间
  	 private int last_update;
 	//扩展字段,备用
  	 private String feature;


  	 /*******************************************************/
  	 //产品价格列表
  	 private List<PriceInfo> producPriceList;



	 
    /**
    *id
	* @return
    */ 
	public int getId() {
		return id;
	}
    /**
    *id
	* @param type
    */ 
	public void setId(int id) {
		this.id = id;
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
    *计量单位（张、套）
	* @return
    */ 
	public String getProductUnit() {
		return productUnit;
	}
    /**
    *计量单位（张、套）
	* @param type
    */ 
	public void setProductUnit(String productUnit) {
		this.productUnit = productUnit;
	}
    /**
    *支付类型（0预付  1到付）
	* @return
    */ 
	public String getPayType() {
		return payType;
	}
    /**
    *支付类型（0预付  1到付）
	* @param type
    */ 
	public void setPayType(String payType) {
		this.payType = payType;
	}
    /**
    *渠道id
	* @return
    */ 
	public int getChannel() {
		return channel;
	}
    /**
    *渠道id
	* @param type
    */ 
	public void setChannel(int channel) {
		this.channel = channel;
	}
    /**
    *资源ID
	* @return
    */ 
	public int getSuppliersId() {
		return suppliersId;
	}
    /**
    *资源ID
	* @param type
    */ 
	public void setSuppliersId(int suppliersId) {
		this.suppliersId = suppliersId;
	}
    /**
    *分类ID
	* @return
    */ 
	public int getCatId() {
		return catId;
	}
    /**
    *分类ID
	* @param type
    */ 
	public void setCatId(int catId) {
		this.catId = catId;
	}
    /**
    *标签ID
	* @return
    */ 
	public String getTagId() {
		return tagId;
	}
    /**
    *标签ID
	* @param type
    */ 
	public void setTagId(String tagId) {
		this.tagId = tagId;
	}
    /**
    *产品种类 默认为1
	* @return
    */ 
	public int getProductType() {
		return productType;
	}
    /**
    *产品种类 默认为1
	* @param type
    */ 
	public void setProductType(int productType) {
		this.productType = productType;
	}
    /**
    *门票类型（0为儿童票、1为成人票 、2为老人票、3学生票、4家庭套票、5情侣套票）
	* @return
    */ 
	public String getTicketType() {
		return ticketType;
	}
    /**
    *门票类型（0为儿童票、1为成人票 、2为老人票、3学生票、4家庭套票、5情侣套票）
	* @param type
    */ 
	public void setTicketType(String ticketType) {
		this.ticketType = ticketType;
	}
    /**
    *上架状态（0下架 1上架）
	* @return
    */ 
	public String getSaleStatus() {
		return saleStatus;
	}
    /**
    *上架状态（0下架 1上架）
	* @param type
    */ 
	public void setSaleStatus(String saleStatus) {
		this.saleStatus = saleStatus;
	}
    /**
    *是否免运费（0免 1是）
	* @return
    */ 
	public String getIsShipping() {
		return isShipping;
	}
    /**
    *是否免运费（0免 1是）
	* @param type
    */ 
	public void setIsShipping(String isShipping) {
		this.isShipping = isShipping;
	}
    /**
    *是否实名（0否 1是）
	* @return
    */ 
	public String getIsTrue() {
		return isTrue;
	}
    /**
    *是否实名（0否 1是）
	* @param type
    */ 
	public void setIsTrue(String isTrue) {
		this.isTrue = isTrue;
	}
    /**
    *是否限购（0不限购 大于0限购，规则限购类型）
	* @return
    */ 
	public String getLimitNumber() {
		return limitNumber;
	}
    /**
    *是否限购（0不限购 大于0限购，规则限购类型）
	* @param type
    */ 
	public void setLimitNumber(String limitNumber) {
		this.limitNumber = limitNumber;
	}
    /**
    *限购类型（空为不限购 0按身份证限购 1按手机号 每天每个产品限制 ）
	* @return
    */ 
	public String getLimitType() {
		return limitType;
	}
    /**
    *限购类型（空为不限购 0按身份证限购 1按手机号 每天每个产品限制 ）
	* @param type
    */ 
	public void setLimitType(String limitType) {
		this.limitType = limitType;
	}
    /**
    *限购时间（1按天、2 按周、3 按月）
	* @return
    */ 
	public String getLimitTime() {
		return limitTime;
	}
    /**
    *限购时间（1按天、2 按周、3 按月）
	* @param type
    */ 
	public void setLimitTime(String limitTime) {
		this.limitTime = limitTime;
	}
    /**
    *预定时间限制（默认值就0|0 0|0表示无限制 9:00|16:00表示只能9点至16点下单）
	* @return
    */ 
	public String getLimitOrderTime() {
		return limitOrderTime;
	}
    /**
    *预定时间限制（默认值就0|0 0|0表示无限制 9:00|16:00表示只能9点至16点下单）
	* @param type
    */ 
	public void setLimitOrderTime(String limitOrderTime) {
		this.limitOrderTime = limitOrderTime;
	}
    /**
    *可以预定天数
	* @return
    */ 
	public String getCalendarRange() {
		return calendarRange;
	}
    /**
    *可以预定天数
	* @param type
    */ 
	public void setCalendarRange(String calendarRange) {
		this.calendarRange = calendarRange;
	}
    /**
    *是否支持退票（1：是、2否）
	* @return
    */ 
	public String getIsRefund() {
		return isRefund;
	}
    /**
    *是否支持退票（1：是、2否）
	* @param type
    */ 
	public void setIsRefund(String isRefund) {
		this.isRefund = isRefund;
	}
    /**
    *提前退票天数
	* @return
    */ 
	public String getRefundDay() {
		return refundDay;
	}
    /**
    *提前退票天数
	* @param type
    */ 
	public void setRefundDay(String refundDay) {
		this.refundDay = refundDay;
	}
    /**
    *提前退票时间
	* @return
    */ 
	public String getRefundTime() {
		return refundTime;
	}
    /**
    *提前退票时间
	* @param type
    */ 
	public void setRefundTime(String refundTime) {
		this.refundTime = refundTime;
	}
    /**
    *退票限制
	* @return
    */ 
	public String getRefundRule() {
		return refundRule;
	}
    /**
    *退票限制
	* @param type
    */ 
	public void setRefundRule(String refundRule) {
		this.refundRule = refundRule;
	}
    /**
    *产品有效期
	* @return
    */ 
	public String getProductValidTime() {
		return productValidTime;
	}
    /**
    *产品有效期
	* @param type
    */ 
	public void setProductValidTime(String productValidTime) {
		this.productValidTime = productValidTime;
	}
    /**
    *电子票有效期类型
	* @return
    */ 
	public String getTicketTimeType() {
		return ticketTimeType;
	}
    /**
    *电子票有效期类型
	* @param type
    */ 
	public void setTicketTimeType(String ticketTimeType) {
		this.ticketTimeType = ticketTimeType;
	}
    /**
    *电子票有效期
	* @return
    */ 
	public String getTicketTimeInfo() {
		return ticketTimeInfo;
	}
    /**
    *电子票有效期
	* @param type
    */ 
	public void setTicketTimeInfo(String ticketTimeInfo) {
		this.ticketTimeInfo = ticketTimeInfo;
	}
    /**
    *是否是价格日历（1是0否 1是获取价格日历）
	* @return
    */ 
	public String getPriceType() {
		return priceType;
	}
    /**
    *是否是价格日历（1是0否 1是获取价格日历）
	* @param type
    */ 
	public void setPriceType(String priceType) {
		this.priceType = priceType;
	}
    /**
    *市场价格（单位：分）
	* @return
    */ 
	public double getSalesPrice() {
		return salesPrice;
	}
    /**
    *市场价格（单位：分）
	* @param type
    */ 
	public void setSalesPrice(double salesPrice) {
		this.salesPrice = salesPrice;
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
    *屏蔽日期
	* @return
    */ 
	public String getExceptDate() {
		return exceptDate;
	}
    /**
    *屏蔽日期
	* @param type
    */ 
	public void setExceptDate(String exceptDate) {
		this.exceptDate = exceptDate;
	}
    /**
    *屏蔽星期
	* @return
    */ 
	public String getExceptWeek() {
		return exceptWeek;
	}
    /**
    *屏蔽星期
	* @param type
    */ 
	public void setExceptWeek(String exceptWeek) {
		this.exceptWeek = exceptWeek;
	}
    /**
    *特例日期
	* @return
    */ 
	public String getSpecialDate() {
		return specialDate;
	}
    /**
    *特例日期
	* @param type
    */ 
	public void setSpecialDate(String specialDate) {
		this.specialDate = specialDate;
	}
    /**
    *优惠信息
	* @return
    */ 
	public String getPfnerInfo() {
		return pfnerInfo;
	}
    /**
    *优惠信息
	* @param type
    */ 
	public void setPfnerInfo(String pfnerInfo) {
		this.pfnerInfo = pfnerInfo;
	}
    /**
    *产品介绍
	* @return
    */ 
	public String getProductDesc() {
		return productDesc;
	}
    /**
    *产品介绍
	* @param type
    */ 
	public void setProductDesc(String productDesc) {
		this.productDesc = productDesc;
	}
    /**
    *费用包含
	* @return
    */ 
	public String getCostIncludes() {
		return costIncludes;
	}
    /**
    *费用包含
	* @param type
    */ 
	public void setCostIncludes(String costIncludes) {
		this.costIncludes = costIncludes;
	}
    /**
    *验证窗口
	* @return
    */ 
	public String getConfirmInfo() {
		return confirmInfo;
	}
    /**
    *验证窗口
	* @param type
    */ 
	public void setConfirmInfo(String confirmInfo) {
		this.confirmInfo = confirmInfo;
	}
    /**
    *重要提示
	* @return
    */ 
	public String getImportInfo() {
		return importInfo;
	}
    /**
    *重要提示
	* @param type
    */ 
	public void setImportInfo(String importInfo) {
		this.importInfo = importInfo;
	}
    /**
    *使用须知
	* @return
    */ 
	public String getUsedInfo() {
		return usedInfo;
	}
    /**
    *使用须知
	* @param type
    */ 
	public void setUsedInfo(String usedInfo) {
		this.usedInfo = usedInfo;
	}
    /**
    *开放时间
	* @return
    */ 
	public String getOpenTime() {
		return openTime;
	}
    /**
    *开放时间
	* @param type
    */ 
	public void setOpenTime(String openTime) {
		this.openTime = openTime;
	}
    /**
    *入园时间
	* @return
    */ 
	public String getEnterTime() {
		return enterTime;
	}
    /**
    *入园时间
	* @param type
    */ 
	public void setEnterTime(String enterTime) {
		this.enterTime = enterTime;
	}
    /**
    *参团须知
	* @return
    */ 
	public String getJoinInfo() {
		return joinInfo;
	}
    /**
    *参团须知
	* @param type
    */ 
	public void setJoinInfo(String joinInfo) {
		this.joinInfo = joinInfo;
	}
    /**
    *退款介绍
	* @return
    */ 
	public String getRefundInfo() {
		return refundInfo;
	}
    /**
    *退款介绍
	* @param type
    */ 
	public void setRefundInfo(String refundInfo) {
		this.refundInfo = refundInfo;
	}
    /**
    *补充内容
	* @return
    */ 
	public String getAdditionInfo() {
		return additionInfo;
	}
    /**
    *补充内容
	* @param type
    */ 
	public void setAdditionInfo(String additionInfo) {
		this.additionInfo = additionInfo;
	}
    /**
    *产品图片
	* @return
    */ 
	public String getPicUrl() {
		return picUrl;
	}
    /**
    *产品图片
	* @param type
    */ 
	public void setPicUrl(String picUrl) {
		this.picUrl = picUrl;
	}
    /**
    *修改时间
	* @return
    */ 
	public int getLast_update() {
		return last_update;
	}
    /**
    *修改时间
	* @param type
    */ 
	public void setLast_update(int last_update) {
		this.last_update = last_update;
	}
    /**
    *扩展字段,备用
	* @return
    */ 
	public String getFeature() {
		return feature;
	}
    /**
    *扩展字段,备用
	* @param type
    */ 
	public void setFeature(String feature) {
		this.feature = feature;
	}
	/**
	 * 产品价格列表
	 * @return
	 */
	public List<PriceInfo> getProducPriceList() {
		return producPriceList;
	}
	/**
	 * 产品价格列表
	 * @param producPriceList
	 */
	public void setProducPriceList(List<PriceInfo> producPriceList) {
		this.producPriceList = producPriceList;
	}
	
	
	
}
