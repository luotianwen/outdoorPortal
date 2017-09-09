package com.op.service.withdrawals.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.op.dao.BaseDao;
import com.op.entity.withdrawals.WithdrawalsType;
import com.op.service.withdrawals.WithdrawalsTypeService;


@Service("withdrawalsTypeServiceImpl")
public class WithdrawalsTypeServiceImpl implements WithdrawalsTypeService{

	@Resource(name = "baseDaoImpl")
	private BaseDao dao;
	 
	/**
	 * 查询所有的提现方式接口
	 * @return
	 */
	@Override
	public List<WithdrawalsType> getWithdrawalsTypeList()throws Exception{
		return (List<WithdrawalsType>)dao.findForList("withdrawalsTypeMapper.getWithdrawalsTypeList", null);
	};


	/**
	 * 根据id查询详细信息
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@Override
	public WithdrawalsType findWithdrawalsTypeById(String id)throws Exception{
		return (WithdrawalsType)dao.findForObject("withdrawalsTypeMapper.findWithdrawalsTypeById",id);
	}
	
	


	/**
	 * 根据用户提现账户ID获取账户提现方式信息
	 * @param account_id
	 * @return
	 */
	@Override
	public WithdrawalsType findWithdrawalsTypeByAccountId(String account_id)throws Exception{
		return (WithdrawalsType)dao.findForObject("withdrawalsTypeMapper.findWithdrawalsTypeByAccountId",account_id);
	}

}
