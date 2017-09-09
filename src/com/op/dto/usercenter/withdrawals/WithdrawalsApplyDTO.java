package com.op.dto.usercenter.withdrawals;


public class WithdrawalsApplyDTO {

	private int money;
	private String payPassword;
	private String account_id;
	

 	//申请用户ID
  	 private String user_id;

  	 //当前日期（用于查询当天已申请的提现单子）
  	 private String today_date;
	
	public int getMoney() {
		return money;
	}
	public void setMoney(int money) {
		this.money = money;
	}
	public String getPayPassword() {
		return payPassword;
	}
	public void setPayPassword(String payPassword) {
		this.payPassword = payPassword;
	}
	public String getAccount_id() {
		return account_id;
	}
	public void setAccount_id(String account_id) {
		this.account_id = account_id;
	}
	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	/**
	 * 当前日期（用于查询当天已申请的提现单子）
	 * @return
	 */
	public String getToday_date() {
		return today_date;
	}
	/**
	 * 当前日期（用于查询当天已申请的提现单子）
	 * @param today_date
	 */
	public void setToday_date(String today_date) {
		this.today_date = today_date;
	}

	
}
