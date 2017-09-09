package com.op.entity.insurance;

import java.io.Serializable;
import java.util.Date;
/** 
 * 保险信息(Insurances)实体类
 * @author Win Zhong 
 * @version Revision: 1.00 
 *  Date: 2016-05-31 09:18:49 
 */  
public class Insurances implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

 	//保险产品ID
  	 private int productId;
 	//产品名称
  	 private String productName;
 	//公司名称
  	 private String companyName;
 	//承保年龄开始
  	 private int startAge;
 	//承保年龄结束
  	 private int endAge;
 	//产品类型 0：境内旅意险，1：寿险健康险，2：境外旅意险，3：家财险
  	 private int type;
 	//是否启用(1：启用；2否[玩嘛])
  	 private String isEnable;
 	//是否下架（0未下架，1下架[惠泽]）
  	 private String invalid;
 	//操作人
  	 private String operation_user;
 	//更新时间
  	 private Date update_time;



	 
    /**
    *保险产品ID
	* @return
    */ 
	public int getProductId() {
		return productId;
	}
    /**
    *保险产品ID
	* @param type
    */ 
	public void setProductId(int productId) {
		this.productId = productId;
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
    *公司名称
	* @return
    */ 
	public String getCompanyName() {
		return companyName;
	}
    /**
    *公司名称
	* @param type
    */ 
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
    /**
    *承保年龄开始
	* @return
    */ 
	public int getStartAge() {
		return startAge;
	}
    /**
    *承保年龄开始
	* @param type
    */ 
	public void setStartAge(int startAge) {
		this.startAge = startAge;
	}
    /**
    *承保年龄结束
	* @return
    */ 
	public int getEndAge() {
		return endAge;
	}
    /**
    *承保年龄结束
	* @param type
    */ 
	public void setEndAge(int endAge) {
		this.endAge = endAge;
	}
    /**
    *产品类型 0：境内旅意险，1：寿险健康险，2：境外旅意险，3：家财险
	* @return
    */ 
	public int getType() {
		return type;
	}
    /**
    *产品类型 0：境内旅意险，1：寿险健康险，2：境外旅意险，3：家财险
	* @param type
    */ 
	public void setType(int type) {
		this.type = type;
	}
    /**
    *是否启用(1：启用；2否[玩嘛])
	* @return
    */ 
	public String getIsEnable() {
		return isEnable;
	}
    /**
    *是否启用(1：启用；2否[玩嘛])
	* @param type
    */ 
	public void setIsEnable(String isEnable) {
		this.isEnable = isEnable;
	}
    /**
    *是否下架（0未下架，1下架[惠泽]）
	* @return
    */ 
	public String getInvalid() {
		return invalid;
	}
    /**
    *是否下架（0未下架，1下架[惠泽]）
	* @param type
    */ 
	public void setInvalid(String invalid) {
		this.invalid = invalid;
	}
    /**
    *操作人
	* @return
    */ 
	public String getOperation_user() {
		return operation_user;
	}
    /**
    *操作人
	* @param type
    */ 
	public void setOperation_user(String operation_user) {
		this.operation_user = operation_user;
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
	
}
