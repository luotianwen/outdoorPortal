package com.op.entity.withdrawals;

import java.io.Serializable;
import java.math.BigDecimal;
/** 
 * 用户提现类型(withdrawalsType)实体类
 * @author Win Zhong 
 * @version Revision: 1.00 
 *  Date: 2016-04-07 17:32:52 
 */  
public class WithdrawalsType implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

 	//ID
  	 private String id;
 	//提现方式名称（例：支付宝、工商银行）
  	 private String type_name;
 	//提现方式简码
  	 private String simple_code;
 	//提现方式类型（1：银行提现；2：第三方支付提现）
  	 private String type;
 	//图标
  	 private String icon;
 	//提现手续费（%）
  	 private BigDecimal fee;
 	//是否可用（1：可用；2：不可用）
  	 private String isAvailable;
 	//备注
  	 private String remarks;
 	//排序
  	 private int sort;



	 
    /**
    *ID
    */ 
	public String getId() {
		return id;
	}
    /**
    *ID
    */ 
	public void setId(String id) {
		this.id = id;
	}
    /**
    *提现方式名称（例：支付宝、工商银行）
    */ 
	public String getType_name() {
		return type_name;
	}
    /**
    *提现方式名称（例：支付宝、工商银行）
    */ 
	public void setType_name(String type_name) {
		this.type_name = type_name;
	}
    /**
    *提现方式简码
    */ 
	public String getSimple_code() {
		return simple_code;
	}
    /**
    *提现方式简码
    */ 
	public void setSimple_code(String simple_code) {
		this.simple_code = simple_code;
	}
    /**
    *提现方式类型（1：银行提现；2：第三方支付提现）
    */ 
	public String getType() {
		return type;
	}
    /**
    *提现方式类型（1：银行提现；2：第三方支付提现）
    */ 
	public void setType(String type) {
		this.type = type;
	}
    /**
    *图标
    */ 
	public String getIcon() {
		return icon;
	}
    /**
    *图标
    */ 
	public void setIcon(String icon) {
		this.icon = icon;
	}
    /**
    *提现手续费（%）
    */ 
	public BigDecimal getFee() {
		return fee;
	}
    /**
    *提现手续费（%）
    */ 
	public void setFee(BigDecimal fee) {
		this.fee = fee;
	}
    /**
    *是否可用（1：可用；2：不可用）
    */ 
	public String getIsAvailable() {
		return isAvailable;
	}
    /**
    *是否可用（1：可用；2：不可用）
    */ 
	public void setIsAvailable(String isAvailable) {
		this.isAvailable = isAvailable;
	}
    /**
    *备注
    */ 
	public String getRemarks() {
		return remarks;
	}
    /**
    *备注
    */ 
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
    /**
    *排序
    */ 
	public int getSort() {
		return sort;
	}
    /**
    *排序
    */ 
	public void setSort(int sort) {
		this.sort = sort;
	}
	
}
