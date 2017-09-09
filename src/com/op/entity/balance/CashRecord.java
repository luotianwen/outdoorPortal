package com.op.entity.balance;

import java.io.Serializable;
import java.util.Date;
/** 
 * 账户资金明细表(cashRecord)实体类
 * @author Win Zhong 
 * @version Revision: 1.00 
 *  Date: 2016-04-07 13:42:17 
 */  
public class CashRecord implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

 	//ID
  	 private String id;
 	//账户ID
  	 private String balance_id;
 	//之前金额（上次账户余额）
  	 private int before_money;
 	//之后金额（本次账户余额）
  	 private int after_money;
 	//本次金额（充值、提现或使用的金额）
  	 private int this_money;
 	//冻结金额
  	 private int frozen_money;
 	//收入/支出  （1：收入；2：支出）
  	 private String behavior_type;
 	//冻结金额收入/支出  （1：收入；2：支出）
  	 private String frozen_type;
 	//账目类型（1：提现；2：订单）
  	 private String account_type;
 	//账目关联id
  	 private String relation_id;
 	//原因备注（收入或支出动作导致的结果）
  	 private String remarks;
 	//记录时间
  	 private Date create_time;



	 
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
    *账户ID
    */ 
	public String getBalance_id() {
		return balance_id;
	}
    /**
    *账户ID
    */ 
	public void setBalance_id(String balance_id) {
		this.balance_id = balance_id;
	}
    /**
    *之前金额（上次账户余额）
    */ 
	public int getBefore_money() {
		return before_money;
	}
    /**
    *之前金额（上次账户余额）
    */ 
	public void setBefore_money(int before_money) {
		this.before_money = before_money;
	}
    /**
    *之后金额（本次账户余额）
    */ 
	public int getAfter_money() {
		return after_money;
	}
    /**
    *之后金额（本次账户余额）
    */ 
	public void setAfter_money(int after_money) {
		this.after_money = after_money;
	}
    /**
    *本次金额（充值、提现或使用的金额）
    */ 
	public int getThis_money() {
		return this_money;
	}
    /**
    *本次金额（充值、提现或使用的金额）
    */ 
	public void setThis_money(int this_money) {
		this.this_money = this_money;
	}
    /**
    *冻结金额
    */ 
	public int getFrozen_money() {
		return frozen_money;
	}
    /**
    *冻结金额
    */ 
	public void setFrozen_money(int frozen_money) {
		this.frozen_money = frozen_money;
	}
    /**
    *收入/支出  （1：收入；2：支出）
    */ 
	public String getBehavior_type() {
		return behavior_type;
	}
    /**
    *收入/支出  （1：收入；2：支出）
    */ 
	public void setBehavior_type(String behavior_type) {
		this.behavior_type = behavior_type;
	}
    /**
    *冻结金额收入/支出  （1：收入；2：支出）
    */ 
	public String getFrozen_type() {
		return frozen_type;
	}
    /**
    *冻结金额收入/支出  （1：收入；2：支出）
    */ 
	public void setFrozen_type(String frozen_type) {
		this.frozen_type = frozen_type;
	}
    /**
    *账目类型（1：提现；2：订单）
    */ 
	public String getAccount_type() {
		return account_type;
	}
    /**
    *账目类型（1：提现；2：订单）
    */ 
	public void setAccount_type(String account_type) {
		this.account_type = account_type;
	}
    /**
    *账目关联id
    */ 
	public String getRelation_id() {
		return relation_id;
	}
    /**
    *账目关联id
    */ 
	public void setRelation_id(String relation_id) {
		this.relation_id = relation_id;
	}
    /**
    *原因备注（收入或支出动作导致的结果）
    */ 
	public String getRemarks() {
		return remarks;
	}
    /**
    *原因备注（收入或支出动作导致的结果）
    */ 
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
    /**
    *记录时间
    */ 
	public Date getCreate_time() {
		return create_time;
	}
    /**
    *记录时间
    */ 
	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}
	
}
