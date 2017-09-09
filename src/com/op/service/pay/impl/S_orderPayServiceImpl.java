package com.op.service.pay.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.newAlipay.AlipayInfo;
import com.op.dao.BaseDao;
import com.op.service.pay.PayService;
import com.op.spot.entity.S_orderTravelPeople;
import com.op.spot.visitbeijing.SpotTicketUtil;
/**
 * 景点支付回调处理服务
 * @author WinZhong
 *
 */
@Service("s_orderPayServiceImpl")
public class S_orderPayServiceImpl implements PayService {

	@Resource(name = "baseDaoImpl")
	private BaseDao dao;

	protected Logger logger = Logger.getLogger(this.getClass());

	/**
	 * 支付成功回调接口
	 * @param alipayInfo
	 * @return 返回订单类型（1：活动订单、2：场馆订单、3：景点门票订单……）
	 */
	@Override
	public int payCallback(AlipayInfo alipayInfo) {
		try {
			//更新景点订单表支付状态
			dao.update("s_orderMapper.updatePayState", alipayInfo);
			//更新景点订单出游人支付状态
			dao.update("s_orderTravelPeopleMapper.updatePayState", alipayInfo);
			
			List<S_orderTravelPeople> tpList = (List<S_orderTravelPeople>)dao.findForList("s_orderTravelPeopleMapper.getOrderTravelPeople",alipayInfo.getOut_trade_no());
			for(S_orderTravelPeople tp:tpList){
				System.out.println(tp.getProductName());
				System.out.println(tp.getProductSn()); 
				System.out.println(tp.getVisitorName());
				System.out.println(tp.getVisitorPhone());
				System.out.println(tp.getCardNum());
				System.out.println(tp.getInDate());
				SpotTicketUtil.submitOrder(tp);
			}
			//更新出票信息
			dao.update("s_orderTravelPeopleMapper.updateTicketState", tpList);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error("订单号："+alipayInfo.getOut_trade_no()+"修改支付信息错误【2】：", e);
		}
		
		return 3;
		
	}
	
	
}
