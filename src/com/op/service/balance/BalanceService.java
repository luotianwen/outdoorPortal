package com.op.service.balance;

import com.op.dto.usercenter.userInfo.PayPasswordDTO;
import com.op.entity.balance.Balance;
/** 
 * 用户资金账户余额表(balance)接口
 * @author Win Zhong 
 * @version Revision: 1.00 
 *  Date: 2016-02-25 13:42:49 
 */  
public interface BalanceService {

	/**
	 * 用户注册成功后，给用户开通余额
	 * @param user_id
	 * @throws Exception
	 */
	void inertBalance(String user_id)throws Exception;

	/**
	 * 根据用户id获取用户余额信息
	 * @param userId
	 * @return
	 */
	Balance getBalanceByUserId(String userId)throws Exception;

	/**
	 * 根据用户id获取支付密码
	 * @param userId
	 * @return
	 * @throws Exception
	 */
	String getPayPwd(String userId)throws Exception;   

	/**
	 * 设置支付密码
	 * @param payPasswordDTO
	 * @throws Exception
	 */
	boolean setPayPwd(PayPasswordDTO payPasswordDTO);
	
	/**
	 * 用户支付成功后修改发布者钱包余额
	 * @param usersId 发布者ID
	 * @param available_money 可用金额（预约费）
	 * @param frozen_money 冻结金额（剩余费用）
	 * @throws Exception
	 */
	void updateMoneyByUserId(String userId,double available_money,double frozen_money) throws Exception;
	
}
