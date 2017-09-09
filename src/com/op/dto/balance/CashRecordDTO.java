package com.op.dto.balance;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
/** 
 * 账户资金明细表(cashRecord)实体类
 * @author Win Zhong 
 * @version Revision: 1.00 
 *  Date: 2016-02-25 13:42:49 
 */  
public class CashRecordDTO implements Serializable{

 	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//本次金额（充值、提现或使用的金额）
 	 private BigDecimal money;
  	//冻结金额
  	 private BigDecimal frozen_money;
 	//收入/支出  （1：收入；2：支出）
 	 private String behavior;
 	//收入/支出  （1：收入；2：支出）
 	 private String frozen_behavior;
	//账目类型（1：提现；2：订单）
 	 private String account;
  	//记录时间
  	 private Date time;
  	//原因备注（收入或支出动作导致的结果）
  	 private String remarks;
    /**
    *收入/支出  （1：收入；2：支出）
    */ 
	public String getBehavior() {
		if("1".equals(behavior)){
			return "+";
		}else{
			return "-";
		}
		//return behavior;
	}
	public void setBehavior(String behavior) {
		this.behavior = behavior;
	}
    /**
    *账目类型（1：提现；2：订单）
    */ 
	public String getAccount() {
		if("1".equals(account)){
			return "提现";
		}else{
			return "订单";
		}
		//return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public BigDecimal getMoney() {
		return money;
	}
	public void setMoney(BigDecimal money) {
		this.money = money;
	}
	public Date getTime() {
		return time;
	}
	public void setTime(Date time) {
		this.time = time;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public BigDecimal getFrozen_money() {
		return frozen_money;
	}
	public void setFrozen_money(BigDecimal frozen_money) {
		this.frozen_money = frozen_money;
	}
	public String getFrozen_behavior() {
		if("1".equals(frozen_behavior)){
			return "+";
		}else{
			return "-";
		}
	}
	public void setFrozen(String frozen_behavior) {
		this.frozen_behavior = frozen_behavior;
	}
   
   
}
