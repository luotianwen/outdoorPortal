package com.op.service.insurance;

import java.util.Map;

import com.op.entity.insurance.AsyncOrderInfo;
import com.op.entity.insurance.Policyholders;
/** 
 * 保险投保人(policyholders)接口
 * @author Win Zhong 
 * @version Revision: 1.00 
 *  Date: 2016-03-16 11:19:58 
 */  
public interface PolicyholdersService {

	/**
	 * 根据保险异步回调信息处理保险订单信息
	 * @param asyncOrderInfo
	 * @throws Exception 
	 */
	void callbackHandler(AsyncOrderInfo asyncOrderInfo)throws Exception;

	/**
	 * 订单购买保险总价格
	 * @param asu_id 订单ID
	 * @return
	 * @throws Exception
	 */
	Policyholders getPriceTotal(String asu_id) throws Exception;
	
}
