package com.op.entity.withdrawals;

import java.io.Serializable;
import java.util.Date;
/** 
 * 用户资金提现申请操作日志表(cashWithdrawalsLog)实体类
 * @author Win Zhong 
 * @version Revision: 1.00 
 *  Date: 2016-02-25 14:46:11 
 */  
public class CashWithdrawalsLog implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

 	//ID
  	 private String id;
 	//提现申请ID
  	 private String cw_id;
 	//操作人ID
  	 private String user_id;
 	//操作人名称
  	 private String user_name;
 	//操作时间
  	 private Date handling_time;
 	//日志信息
  	 private String log;



	 
    /**
    *ID
    */ 
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
    /**
    *提现申请ID
    */ 
	public String getCw_id() {
		return cw_id;
	}
	public void setCw_id(String cw_id) {
		this.cw_id = cw_id;
	}
    /**
    *操作人ID
    */ 
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
    /**
    *操作人名称
    */ 
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
    /**
    *操作时间
    */ 
	public Date getHandling_time() {
		return handling_time;
	}
	public void setHandling_time(Date handling_time) {
		this.handling_time = handling_time;
	}
    /**
    *日志信息
    */ 
	public String getLog() {
		return log;
	}
	public void setLog(String log) {
		this.log = log;
	}
	
}
