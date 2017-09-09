package com.op.service.insurance.impl;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.alibaba.druid.util.StringUtils;
import com.op.dao.BaseDao; 
import com.op.entity.insurance.AsyncOrderInfo;
import com.op.entity.insurance.Policyholders;
import com.op.service.insurance.InsuredService;
import com.op.service.insurance.PolicyholdersService;

/** 
 * 保险投保人(policyholders)接口实现类
 * @author Win Zhong 
 * @version Revision: 1.00 
 *  Date: 2016-03-16 11:19:58 
 */  
@Service("policyholdersServiceImpl")
public class PolicyholdersServiceImpl implements PolicyholdersService{

	@Resource(name = "baseDaoImpl")
	private BaseDao dao;
	
	@Resource(name = "insuredServiceImpl")
	private InsuredService insuredServiceImpl;
	
	/**
	 * 根据保险异步回调信息处理保险订单信息
	 * @param asyncOrderInfo
	 * @throws Exception 
	 */
	@Override
	public void callbackHandler(AsyncOrderInfo asyncOrderInfo) throws Exception{
		dao.update("policyholdersMapper.updateCallbackInfo", asyncOrderInfo);
		if(asyncOrderInfo.getResultCode() == 0){
			dao.update("InsuredMapper.updateCallbackInfo", asyncOrderInfo);
			//insuredServiceImpl.updatePolicy(asyncOrderInfo.getInsureNum());
		}
		
	}
	
	/**
	 * 订单购买保险总价格
	 * @param asu_id 订单ID
	 * @return
	 * @throws Exception
	 */
	public Policyholders getPriceTotal(String order_id) throws Exception{
		return (Policyholders) dao.findForObject("policyholdersMapper.getPriceTotal", order_id);
	}
	
	
}
