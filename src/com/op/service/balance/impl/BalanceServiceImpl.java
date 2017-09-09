package com.op.service.balance.impl;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.op.dao.BaseDao;
import com.op.dto.usercenter.userInfo.PayPasswordDTO;
import com.op.entity.balance.Balance;
import com.op.service.balance.BalanceService;
import com.op.util.MD5;

/** 
 * 用户资金账户余额表(balance)接口实现类
 * @author Win Zhong 
 * @version Revision: 1.00 
 *  Date: 2016-02-25 13:42:49 
 */  
@Service("balanceServiceImpl")
public class BalanceServiceImpl implements BalanceService{

	@Resource(name = "baseDaoImpl")
	private BaseDao dao;

	/**
	 * 用户注册成功后，给用户开通余额
	 * @param user_id
	 * @throws Exception
	 */
	@Override
	public void inertBalance(String user_id)throws Exception{
		dao.save("balanceMapper.insertBalance", user_id);
	}
	
	/**
	 * 根据用户id获取用户余额信息
	 * @param userId
	 * @return
	 */
	@Override
	public Balance getBalanceByUserId(String userId)throws Exception{
		return (Balance)dao.findForObject("balanceMapper.getBalanceByUserId", userId);
	}
	
	/**
	 * 根据用户id获取支付密码
	 * @param userId
	 * @return
	 * @throws Exception
	 */
	@Override
	public String getPayPwd(String userId)throws Exception{
		String payPassword = (String)dao.findForObject("balanceMapper.isSetPayPwd", userId);
		return payPassword;
	}

	/**
	 * 设置支付密码
	 * @param payPasswordDTO
	 * @return
	 * @throws Exception
	 */
	@Override
	public boolean setPayPwd(PayPasswordDTO payPasswordDTO){
		try {
			payPasswordDTO.setPayPassword(payPasswordDTO.getPayPassword() == null ? null : MD5.md5(payPasswordDTO.getPayPassword()));
			payPasswordDTO.setOldPayPassword(payPasswordDTO.getOldPayPassword()== null ? null : MD5.md5(payPasswordDTO.getOldPayPassword()));
			dao.update("balanceMapper.updatePayPassword", payPasswordDTO);
			return true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		
	}  
	
	/**
	 * 用户支付成功后修改发布者钱包余额
	 * @param usersId 发布者ID
	 * @param available_money 可用金额（预约费）
	 * @param frozen_money 冻结金额（剩余费用）
	 * @throws Exception
	 */
	@Override
	public void updateMoneyByUserId(String userId,double available_money,double frozen_money) throws Exception{
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("userId", userId);
		map.put("available_money", available_money);
		map.put("frozen_money", frozen_money);
		dao.update("balanceMapper.updateMoneyByUserId", map);
	}
	
	
}
