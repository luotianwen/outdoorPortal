package com.op.dto.insurance;

import java.io.Serializable;
/** 
 * 保险信息(Insurances)实体类
 * @author Win Zhong 
 * @version Revision: 1.00 
 *  Date: 2016-05-31 09:18:49 
 */  
public class InsurancesDTO implements Serializable {

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
}
