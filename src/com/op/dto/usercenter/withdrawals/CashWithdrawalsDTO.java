package com.op.dto.usercenter.withdrawals;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
/** 
 * 用户资金提现申请表(cashWithdrawals)实体类
 * @author Win Zhong 
 * @version Revision: 1.00 
 *  Date: 2016-04-08 15:58:19 
 */  
public class CashWithdrawalsDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

 	//提现流水号（id）
  	 private String id;
 	//申请用户ID
  	 private String user_id;
 	//提现金额
  	 private BigDecimal cw_cash;
 	//提现账户ID
  	 private String wa_id;
  
 	//账户姓名
  	 private String account_name;
 	//账户账号
  	 private String account_number;
 	 
 	//申请时间
  	 private Date application_time;
 	 
 	//提现状态（1：提交申请；2：财务处理；3：提现完成；4：提现失败；5：用户取消）   默认1
  	 private String cw_state;
  	 
  	 

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public BigDecimal getCw_cash() {
		return cw_cash;
	}

	public void setCw_cash(BigDecimal cw_cash) {
		this.cw_cash = cw_cash;
	}

	public String getWa_id() {
		return wa_id;
	}

	public void setWa_id(String wa_id) {
		this.wa_id = wa_id;
	}

	public String getAccount_name() {
		return account_name;
	}

	public void setAccount_name(String account_name) {
		this.account_name = account_name;
	}

	public String getAccount_number() {
		return account_number;
	}

	public void setAccount_number(String account_number) {
		this.account_number = account_number;
	}

	public Date getApplication_time() {
		return application_time;
	}

	public void setApplication_time(Date application_time) {
		this.application_time = application_time;
	}

	public String getCw_state() {
		return cw_state;
	}

	public void setCw_state(String cw_state) {
		this.cw_state = cw_state;
	}
  	 
	
	
}
