package com.op.service.pay;

import com.newAlipay.AlipayInfo;
/** 
 * 支付宝支付流水信息表(tg_alipayInfo)接口
 * @author Win Zhong 
 * @version Revision: 1.00 
 *  Date: 2016-09-07 09:30:45 
 */  
public interface AlipayInfoService {

	/**
	 * 更新支付信息
	 * @param alipayInfo
	 * @throws Exception
	 */
	void updateInfo(AlipayInfo alipayInfo)throws Exception;

	/**
	 * 保存支付流水信息
	 * @param alipayInfo
	 * @throws Exception
	 */
	void saveInfo(AlipayInfo alipayInfo)throws Exception;

    

	
}
