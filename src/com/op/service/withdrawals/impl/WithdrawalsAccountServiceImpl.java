package com.op.service.withdrawals.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.op.dao.BaseDao; 
import com.op.entity.withdrawals.WithdrawalsAccount;
import com.op.service.withdrawals.WithdrawalsAccountService;
/** 
 * 用户提现账户(withdrawalsAccount)接口实现类
 * @author Win Zhong 
 * @version Revision: 1.00 
 *  Date: 2016-02-24 15:11:37 
 */  
@Service("withdrawalsAccountServiceImpl")
public class WithdrawalsAccountServiceImpl implements WithdrawalsAccountService{

	@Resource(name = "baseDaoImpl")
	private BaseDao dao;
	 
	/**
	 * 保存用户提现账户
	 * @param withdrawalsAccount
	 * @throws Exception
	 */
	@Override
	public void saveWithdrawalsAccount(WithdrawalsAccount withdrawalsAccount)throws Exception{
		if(withdrawalsAccount.getId() == null){
			dao.save("withdrawalsAccountMapper.saveWithdrawalsAccount", withdrawalsAccount);
		}else{
			dao.update("withdrawalsAccountMapper.updateWithdrawalsAccount", withdrawalsAccount);
		}

	}
	
	/**
	 * 根据用户Id获取用户提现账户
	 * @param userId
	 * @return
	 * @throws Exception
	 */
	@Override
	public List<WithdrawalsAccount> findWithdrawalsAccountByUserId(String userId)throws Exception{
		return (List<WithdrawalsAccount>)dao.findForList("withdrawalsAccountMapper.findWithdrawalsAccountByUserId", userId);
	}
	
	/**
	 * 设置默认账户
	 * @param map
	 */
	@Override
	public void setDefaultAccount(Map<String, Object> map)throws Exception{
		dao.update("withdrawalsAccountMapper.cancelDefaultAccount", map);
		dao.update("withdrawalsAccountMapper.setDefaultAccount", map);
	}
	
	/**
	 * 根据账户Id获取用户提现账户
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@Override
	public WithdrawalsAccount findWithdrawalsAccountById(Map<String,Object> map)throws Exception{
		return (WithdrawalsAccount)dao.findForObject("withdrawalsAccountMapper.findWithdrawalsAccountById", map);
	}
	
	/**
	 * 根据账户Id删除用户提现账户
	 * @param id
	 * @throws Exception
	 */
	@Override
	public void deletewAccount(Map<String, Object> map)throws Exception{
		dao.delete("withdrawalsAccountMapper.deletewAccount", map);
	}
}
