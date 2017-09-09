package com.op.service.withdrawals;

import java.util.List;
import java.util.Map;

import com.op.entity.withdrawals.WithdrawalsAccount;
/** 
 * 用户提现账户(withdrawalsAccount)接口
 * @author Win Zhong 
 * @version Revision: 1.00 
 *  Date: 2016-02-24 15:11:37 
 */  
public interface WithdrawalsAccountService {
	
	/**
	 * 保存用户提现账户
	 * @param withdrawalsAccount
	 * @throws Exception
	 */
	void saveWithdrawalsAccount(WithdrawalsAccount withdrawalsAccount)throws Exception;
	/**
	 * 根据用户Id获取用户提现账户
	 * @param userId
	 * @return
	 * @throws Exception
	 */
	List<WithdrawalsAccount> findWithdrawalsAccountByUserId(String userId)throws Exception;
	
	/**
	 * 设置默认账户
	 * @param map
	 */
	void setDefaultAccount(Map<String, Object> map)throws Exception;
	
	/**
	 * 根据账户Id获取用户提现账户
	 * @param id
	 * @return
	 * @throws Exception
	 */
	WithdrawalsAccount findWithdrawalsAccountById(Map<String,Object> map)throws Exception;
	
	/**
	 * 根据账户Id删除用户提现账户
	 * @param map
	 * @throws Exception
	 */
	void deletewAccount(Map<String, Object> map)throws Exception;
    

	
}
