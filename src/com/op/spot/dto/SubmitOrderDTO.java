package com.op.spot.dto;

import java.io.Serializable;
import java.util.Arrays;

/**
 * 景点门票提交订单信息DTO
 * @author WinZhong
 *
 */
public class SubmitOrderDTO  implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

 	//用户id
  	 private int user_id;
	//产品id
	private int productId;
 	//产品编号
  	 private String productSn;
 	//产品名称
  	 private String productName;
 	//产品种类 默认1
  	 private int productType = 1;
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
 	//取票人姓名
  	 private String fetchName;
 	//取票人手机
  	 private String fetchPhone;
 	//取票人性别（0:男,1:女）
  	 private int sex;
 	//证件类型
  	 private String cardType;
 	//证件号码
  	 private String cardNum;
  	 

  	//出游人姓名
   	 private String[] visitorName;
  	//出游人电话
   	 private String[] visitorPhone;
 	//出游人证件类型
  	 private String[] tp_cardType;
 	//出游人证件号码
  	 private String[] tp_cardNum;
 	//出游人性别（0:男,1:女）
  	 private int tp_sex;




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
    *取票人姓名
	* @return
    */ 
	public String getFetchName() {
		return fetchName;
	}
    /**
    *取票人姓名
	* @param type
    */ 
	public void setFetchName(String fetchName) {
		this.fetchName = fetchName;
	}
    /**
    *取票人手机
	* @return
    */ 
	public String getFetchPhone() {
		return fetchPhone;
	}
    /**
    *取票人手机
	* @param type
    */ 
	public void setFetchPhone(String fetchPhone) {
		this.fetchPhone = fetchPhone;
	}
    /**
    *取票人性别（0:男,1:女）
	* @return
    */ 
	public int getSex() {
		return sex;
	}
    /**
    *取票人性别（0:男,1:女）
	* @param type
    */ 
	public void setSex(int sex) {
		this.sex = sex;
	}
	public String[] getVisitorName() {
		return visitorName;
	}
	public void setVisitorName(String[] visitorName) {
		this.visitorName = visitorName;
	}
	public String[] getVisitorPhone() {
		return visitorPhone;
	}
	public void setVisitorPhone(String[] visitorPhone) {
		this.visitorPhone = visitorPhone;
	}
	public String[] getTp_cardType() {
		return tp_cardType;
	}
	public void setTp_cardType(String[] tp_cardType) {
		this.tp_cardType = tp_cardType;
	}
	public String[] getTp_cardNum() {
		return tp_cardNum;
	}
	public void setTp_cardNum(String[] tp_cardNum) {
		this.tp_cardNum = tp_cardNum;
	}
	public int getTp_sex() {
		return tp_sex;
	}
	public void setTp_sex(int tp_sex) {
		this.tp_sex = tp_sex;
	}
	public int getProductId() {
		return productId;
	}
	public void setProductId(int productId) {
		this.productId = productId;
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
	@Override
	public String toString() {
		return "SubmitOrderDTO [user_id=" + user_id + ", productId="
				+ productId + ", productSn=" + productSn + ", productName="
				+ productName + ", productType=" + productType + ", unitPrice="
				+ unitPrice + ", count=" + count + ", retailPrice="
				+ retailPrice + ", settlementPrice=" + settlementPrice
				+ ", inDate=" + inDate + ", fetchName=" + fetchName
				+ ", fetchPhone=" + fetchPhone + ", sex=" + sex + ", cardType="
				+ cardType + ", cardNum=" + cardNum + ", visitorName="
				+ Arrays.toString(visitorName) + ", visitorPhone="
				+ Arrays.toString(visitorPhone) + ", tp_cardType="
				+ Arrays.toString(tp_cardType) + ", tp_cardNum="
				+ Arrays.toString(tp_cardNum) + ", tp_sex=" + tp_sex + "]";
	}
	 
	
}
