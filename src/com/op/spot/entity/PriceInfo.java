package com.op.spot.entity;

import java.io.Serializable;
import java.util.Date;
/** 
 * 景点门票价格信息(s_priceInfo)实体类
 * @author Win Zhong 
 * @version Revision: 1.00 
 *  Date: 2016-09-21 13:40:39 
 */  
public class PriceInfo implements Serializable {

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
 	//电子票有效期类型
  	 private String ticketTimeType;
 	//游玩时间
  	 private String useDate;
 	//市场价格
  	 private double salesPrice;
 	//建议售价（单位：分）
  	 private double retailPrice;
 	//结算价格（单位：分）
  	 private double settlementPrice;
 	//展示有效开始时间
  	 private String displayBeginDate;
 	//展示有效结束时间
  	 private String displayEndDate;
 	//可用库存
  	 private String stockNumber;



	 
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
    *游玩时间
	* @return
    */ 
	public String getUseDate() {
		return useDate;
	}
    /**
    *游玩时间
	* @param type
    */ 
	public void setUseDate(String useDate) {
		this.useDate = useDate;
	}
    /**
    *市场价格
	* @return
    */ 
	public double getSalesPrice() {
		return salesPrice;
	}
    /**
    *市场价格
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
    *展示有效开始时间
	* @return
    */ 
	public String getDisplayBeginDate() {
		return displayBeginDate;
	}
    /**
    *展示有效开始时间
	* @param type
    */ 
	public void setDisplayBeginDate(String displayBeginDate) {
		this.displayBeginDate = displayBeginDate;
	}
    /**
    *展示有效结束时间
	* @return
    */ 
	public String getDisplayEndDate() {
		return displayEndDate;
	}
    /**
    *展示有效结束时间
	* @param type
    */ 
	public void setDisplayEndDate(String displayEndDate) {
		this.displayEndDate = displayEndDate;
	}
    /**
    *可用库存
	* @return
    */ 
	public String getStockNumber() {
		return stockNumber;
	}
    /**
    *可用库存
	* @param type
    */ 
	public void setStockNumber(String stockNumber) {
		this.stockNumber = stockNumber;
	}
	
}
