package com.op.service.pay.impl;

import java.util.Date;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.newAlipay.AlipayInfo;
import com.op.dao.BaseDao;
import com.op.plugin.SpringWiredBean;
import com.op.service.pay.AlipayInfoService;
import com.op.service.pay.PayService;

/** 
 * 支付宝支付流水信息表(tg_alipayInfo)接口实现类
 * @author Win Zhong 
 * @version Revision: 1.00 
 *  Date: 2016-09-07 09:30:45 
 */  
@Service("alipayInfoServiceImpl")
public class AlipayInfoServiceImpl implements AlipayInfoService{

	@Resource(name = "baseDaoImpl")
	private BaseDao dao;

	/**
	 * 更新支付信息
	 * @param alipayInfo
	 * @throws Exception
	 */
	public void updateInfo(AlipayInfo alipayInfo)throws Exception {

		PayService pay = (PayService) SpringWiredBean.getInstance().getBeanById(alipayInfo.getExtra_common_param());//Class.forName("com.op.service.pay.impl."+alipayInfo.getExtra_common_param()).newInstance(); 
		int order_type = pay.payCallback(alipayInfo);
		alipayInfo.setOrder_type(order_type);
		//修改支付流水信息
		dao.update("alipayInfoMapper.updateInfo", alipayInfo);
		
	}
	


	/**
	 * 保存支付流水信息
	 * @param alipayInfo
	 * @throws Exception
	 */
	public void saveInfo(AlipayInfo alipayInfo)throws Exception {
		dao.save("alipayInfoMapper.saveInfo", alipayInfo);
	}
}
