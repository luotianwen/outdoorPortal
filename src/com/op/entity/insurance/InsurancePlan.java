package com.op.entity.insurance;

import java.io.Serializable;
/** 
 * 保险计划详情(InsurancePlan)实体类
 * @author Win Zhong 
 * @version Revision: 1.00 
 *  Date: 2016-05-31 09:18:49 
 */  
public class InsurancePlan implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

 	//保险计划ID
  	 private int planId;
 	//保险产品ID
  	 private int productId;
 	//方案代码，每一款保险公司产品的方案代码都不相同，由慧择分配
  	 private String caseCode;
 	//计划名称
  	 private String planName;
 	//保障计划列表
  	 private String planDetails;



	 
    /**
    *保险计划ID
	* @return
    */ 
	public int getPlanId() {
		return planId;
	}
    /**
    *保险计划ID
	* @param type
    */ 
	public void setPlanId(int planId) {
		this.planId = planId;
	}
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
    *计划名称
	* @return
    */ 
	public String getPlanName() {
		return planName;
	}
    /**
    *计划名称
	* @param type
    */ 
	public void setPlanName(String planName) {
		this.planName = planName;
	}
    /**
    *保障计划列表
	* @return
    */ 
	public String getPlanDetails() {
		return planDetails;
	}
    /**
    *保障计划列表
	* @param type
    */ 
	public void setPlanDetails(String planDetails) {
		this.planDetails = planDetails;
	}
	
}
