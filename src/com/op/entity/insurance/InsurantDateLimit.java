package com.op.entity.insurance;

import java.io.Serializable;
/** 
 * 保险保障期限(InsurantDateLimit)实体类
 * @author Win Zhong 
 * @version Revision: 1.00 
 *  Date: 2016-05-31 09:18:49 
 */  
public class InsurantDateLimit implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

 	//id
  	 private int id;
 	//保险产品ID
  	 private int productId;
 	//最低保障期限
  	 private int minDeadline;
 	//最长保障期限
  	 private int maxDeadline;
 	//期限单位（1：天；2：年）
  	 private int unit;



	 
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
    *最低保障期限
	* @return
    */ 
	public int getMinDeadline() {
		return minDeadline;
	}
    /**
    *最低保障期限
	* @param type
    */ 
	public void setMinDeadline(int minDeadline) {
		this.minDeadline = minDeadline;
	}
    /**
    *最长保障期限
	* @return
    */ 
	public int getMaxDeadline() {
		return maxDeadline;
	}
    /**
    *最长保障期限
	* @param type
    */ 
	public void setMaxDeadline(int maxDeadline) {
		this.maxDeadline = maxDeadline;
	}
    /**
    *期限单位（1：天；2：年）
	* @return
    */ 
	public int getUnit() {
		return unit;
	}
    /**
    *期限单位（1：天；2：年）
	* @param type
    */ 
	public void setUnit(int unit) {
		this.unit = unit;
	}
	
}
