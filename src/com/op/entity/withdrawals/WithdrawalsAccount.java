package com.op.entity.withdrawals;

import java.io.Serializable;
import java.util.Date;
/** 
 * 用户提现账户(withdrawalsAccount)实体类
 * @author Win Zhong 
 * @version Revision: 1.00 
 *  Date: 2016-02-25 13:34:11 
 */  
public class WithdrawalsAccount implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

 	//id
  	 private String id;
 	//用户ID
  	 private String user_id;
 	//提现类型ID
  	 private String wt_id;
 	//账户姓名
  	 private String account_name;
 	//账户账号
  	 private String account_number;
  	//默认提现账户（1：是；2：否）
  	 private String isDefault;
  	//添加时间
  	 private Date create_time;
  	 /****************************/
  	//提现类型名称
   	 private String type_name;
  	//提现类型图标
   	 private String icon;

	 
    /**
    *id
    */ 
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
    /**
    *用户ID
    */ 
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
    /**
    *提现类型ID
    */ 
	public String getWt_id() {
		return wt_id;
	}
	public void setWt_id(String wt_id) {
		this.wt_id = wt_id;
	}
    /**
    *账户姓名
    */ 
	public String getAccount_name() {
		return account_name;
	}
	public void setAccount_name(String account_name) {
		this.account_name = account_name;
	}
    /**
    *账户账号
    */ 
	public String getAccount_number() {
		return account_number;
	}
	public void setAccount_number(String account_number) {
		this.account_number = account_number;
	}
    /**
    *默认提现账户（1：是；2：否）
    */ 
	public String getIsDefault() {
		return isDefault;
	}
	public void setIsDefault(String isDefault) {
		this.isDefault = isDefault;
	}
    /**
    *添加时间
    */ 
	public Date getCreate_time() {
		return create_time;
	}
	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}
	public String getType_name() {
		return type_name;
	}
	public void setType_name(String type_name) {
		this.type_name = type_name;
	}
	public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}
	
}
