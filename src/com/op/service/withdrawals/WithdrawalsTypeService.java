package com.op.service.withdrawals;

import java.util.List;
import com.op.entity.withdrawals.WithdrawalsType;
/** 
 * 用户提现类型(withdrawalsType)接口
 * @author Win Zhong 
 * @version Revision: 1.00 
 *  Date: 2016-02-24 15:11:37 
 */  
public interface WithdrawalsTypeService {

	/**
	 * 查询所有的提现方式接口
	 * @return
	 */
	List<WithdrawalsType> getWithdrawalsTypeList()throws Exception;

	/**
	 * 根据id查询详细信息
	 * @param id
	 * @return
	 * @throws Exception
	 */
	WithdrawalsType findWithdrawalsTypeById(String id)throws Exception;

	/**
	 * 根据用户提现账户ID获取账户提现方式信息
	 * @param account_id
	 * @return
	 */
	WithdrawalsType findWithdrawalsTypeByAccountId(String account_id)throws Exception;

	
}
