package com.op.service.pay;

import com.newAlipay.AlipayInfo;
import com.op.dao.BaseDao;

/**
 * 公共支付处理接口，支付回调都得实现这个接口
 * @author WinZhong
 *
 */
public interface PayService {
 
	/**
	 * 支付成功回调接口
	 * @param alipayInfo
	 * @return 返回订单类型（1：活动订单、2：场馆订单、3：景点门票订单……）
	 */
	int payCallback(AlipayInfo alipayInfo);
	
}
