package com.op.entity.withdrawals;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
/** 
 * 用户资金提现申请表(cashWithdrawals)实体类
 * @author Win Zhong 
 * @version Revision: 1.00 
 *  Date: 2016-04-08 15:58:19 
 */  
public class CashWithdrawals implements Serializable {

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
 	//提现类型ID
  	 private String wt_id;
 	//账户姓名
  	 private String account_name;
 	//账户账号
  	 private String account_number;
 	//提现手续费
  	 private BigDecimal fee_cash;
 	//申请时间
  	 private Date application_time;
 	//处理人（ID）
  	 private String operator;
 	//处理时间
  	 private Date handling_time;
 	//第三方或银行转账成功信息
  	 private String success_message;
 	//第三方或银行转账成功截图
  	 private String success_screenshot;
 	//用户提现申请备注
  	 private String user_remarks;
 	//备注
  	 private String remarks;
 	//提现状态（1：提交申请；2：财务处理；3：提现完成；4：提现失败；5：用户取消）   默认1
  	 private String cw_state = "1";

  	 /*****************************/
  	 //提现用户名称
  	 private String uName;
  	 //户资金提现申请操作日志
  	 private List<CashWithdrawalsLog> cashWithdrawalsLogList;
  	 //处理人用户名称
  	 private String operator_name;
  	 

   	//提现类型名称
    	 private String type_name;
   	//提现类型图标
    	 private String icon;
	 
    /**
    *提现流水号（id）
    */ 
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
    /**
    *申请用户ID
    */ 
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
    /**
    *提现金额
    */ 
	public BigDecimal getCw_cash() {
		return cw_cash;
	}
	public void setCw_cash(BigDecimal cw_cash) {
		this.cw_cash = cw_cash;
	}
    /**
    *提现账户ID
    */ 
	public String getWa_id() {
		return wa_id;
	}
    /**
    *提现账户ID
    */ 
	public void setWa_id(String wa_id) {
		this.wa_id = wa_id;
	}
    /**
    *提现类型ID
    */ 
	public String getWt_id() {
		return wt_id;
	}
    /**
    *提现类型ID
    */ 
	public void setWt_id(String wt_id) {
		this.wt_id = wt_id;
	}
    /**
    *账户姓名
    */ 
	public String getAccount_name() {
		return account_name;
	}
    /**
    *账户姓名
    */ 
	public void setAccount_name(String account_name) {
		this.account_name = account_name;
	}
    /**
    *账户账号
    */ 
	public String getAccount_number() {
		return account_number;
	}
    /**
    *账户账号
    */ 
	public void setAccount_number(String account_number) {
		this.account_number = account_number;
	}
    /**
    *提现手续费
    */ 
	public BigDecimal getFee_cash() {
		return fee_cash;
	}
	public void setFee_cash(BigDecimal fee_cash) {
		this.fee_cash = fee_cash;
	}
    /**
    *申请时间
    */ 
	public Date getApplication_time() {
		return application_time;
	}
	public void setApplication_time(Date application_time) {
		this.application_time = application_time;
	}
    /**
    *处理人（ID）
    */ 
	public String getOperator() {
		return operator;
	}
	public void setOperator(String operator) {
		this.operator = operator;
	}
    /**
    *处理时间
    */ 
	public Date getHandling_time() {
		return handling_time;
	}
	public void setHandling_time(Date handling_time) {
		this.handling_time = handling_time;
	}
    /**
    *第三方或银行转账成功信息
    */ 
	public String getSuccess_message() {
		return success_message;
	}
	public void setSuccess_message(String success_message) {
		this.success_message = success_message;
	}
    /**
    *第三方或银行转账成功截图
    */ 
	public String getSuccess_screenshot() {
		return success_screenshot;
	}
	public void setSuccess_screenshot(String success_screenshot) {
		this.success_screenshot = success_screenshot;
	}
    /**
    *用户提现申请备注
    */ 
	public String getUser_remarks() {
		return user_remarks;
	}
	public void setUser_remarks(String user_remarks) {
		this.user_remarks = user_remarks;
	}
    /**
    *备注
    */ 
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
    /**
    *提现状态（1：提交申请；2：财务处理；3：提现完成；4：提现失败；5：用户取消）
    */ 
	public String getCw_state() {
		return cw_state;
	}
	public void setCw_state(String cw_state) {
		this.cw_state = cw_state;
	}
	public String getuName() {
		return uName;
	}
	public void setuName(String uName) {
		this.uName = uName;
	}
	public List<CashWithdrawalsLog> getCashWithdrawalsLogList() {
		return cashWithdrawalsLogList;
	}
	public void setCashWithdrawalsLogList(
			List<CashWithdrawalsLog> cashWithdrawalsLogList) {
		this.cashWithdrawalsLogList = cashWithdrawalsLogList;
	}
	public String getOperator_name() {
		return operator_name;
	}
	public void setOperator_name(String operator_name) {
		this.operator_name = operator_name;
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
