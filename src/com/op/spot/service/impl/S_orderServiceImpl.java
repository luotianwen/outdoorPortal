package com.op.spot.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.op.dao.BaseDao;
import com.op.plugin.page.Page;
import com.op.spot.dto.SpotOrderInfoDTO;
import com.op.spot.dto.SubmitOrderDTO;
import com.op.spot.entity.Product;
import com.op.spot.entity.S_order;
import com.op.spot.entity.S_orderTravelPeople;
import com.op.spot.service.S_orderService;
import com.op.spot.visitbeijing.SpotTicketUtil;
import com.op.util.Const;
import com.op.util.IdCardUtils;
import com.op.util.IdWorker;

/** 
 * 景点订单(s_order)接口实现类
 * @author Win Zhong 
 * @version Revision: 1.00 
 *  Date: 2016-09-21 13:40:39 
 */  
@Service("s_orderServiceImpl")
public class S_orderServiceImpl implements S_orderService{

	@Resource(name = "baseDaoImpl")
	private BaseDao dao;
	
	/**
	 * 提交订单
	 * @param orderInfo
	 * @return
	 * @throws Exception
	 */
    public Map<String,Object> submitOrder(SubmitOrderDTO orderInfo)throws Exception {
    	Map<String,Object> map = new HashMap<String,Object>();
    	//获取产品信息
    	Product product = (Product)dao.findForObject("productMapper.getTicketInfo", orderInfo.getProductId());
    	orderInfo.setProductType(product.getProductType());
    	//订单
    	S_order order = new S_order();
    	order.setUser_id(orderInfo.getUser_id());
    	order.setOrderId("T"+IdWorker.getId());
    	order.setChannel(product.getChannel());
    	order.setProductSn(orderInfo.getProductSn());
    	order.setProductName(orderInfo.getProductName());
    	order.setProductType(product.getProductType());
    	order.setUnitPrice(orderInfo.getUnitPrice());
    	order.setCount(orderInfo.getCount());
    	order.setRetailPrice(orderInfo.getRetailPrice());
    	order.setSettlementPrice(orderInfo.getSettlementPrice());
    	//订单状态 0:等待付款
    	order.setStatus(0);
    	//计算订单支付总金额
    	order.setOrder_total_price(order.getCount()*order.getRetailPrice()/100);
    	order.setIsSendSms(0);
    	order.setPayType(1);
    	order.setInDate(orderInfo.getInDate());
    	order.setFetchName(orderInfo.getFetchName());
    	order.setFetchPhone(orderInfo.getFetchPhone());
    	order.setCardType("1");
    	order.setCardNum(orderInfo.getCardNum());
    	order.setSex(IdCardUtils.getSexByIdCard(orderInfo.getCardNum()));
    	//出游人列表
    	List<S_orderTravelPeople> tpList = new ArrayList<S_orderTravelPeople>();
    	
    	//预定时间限制  默认值就0|0  0|0表示无限制  9:00|16:00表示只能9点至16点下单
		if(!"0|0".equals(product.getLimitOrderTime())){
			SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			String[] limitOrderTime = product.getLimitOrderTime().split("\\|");
			Date nowDate = new Date();
			long now = sdf.parse(sdf.format(nowDate)).getTime();
			if("0".equals(limitOrderTime[0])){
				if(order.getInDate().equals(format.format(nowDate)) && sdf.parse(limitOrderTime[1]).getTime() < now){
	    			map.put(Const.ERROR_INFO, "只能在"+limitOrderTime[1]+"前下单");
	    			map.put(Const.RESPONSE_STATE, 500);
	    			return map;
				}
			}else{
				if(sdf.parse(limitOrderTime[0]).getTime() > now && sdf.parse(limitOrderTime[1]).getTime() < now){
	    			map.put(Const.ERROR_INFO, "只能在"+limitOrderTime[0]+""+limitOrderTime[1]+"之间下单");
	    			map.put(Const.RESPONSE_STATE, 500);
	    			return map;
				}
				
			}
			
	    
		}
    	
    	
    	System.out.println(product.getLimitNumber()+"*****************");
    	//判断限购 （0不限购 大于0限购，规则限购类型）     不限购出游人为取票人
    	if("0".equals(product.getLimitNumber())){
    		order.setIsRealName(0);
        	//订单出游人
        	S_orderTravelPeople tp = new S_orderTravelPeople();
        	tp.setUser_id(order.getUser_id());
        	tp.setOrderId(order.getOrderId());
        	tp.setProductSn(order.getProductSn());
        	tp.setProductName(order.getProductName());
        	tp.setProductType(order.getProductType());
        	tp.setUnitPrice(order.getUnitPrice());
        	tp.setCount(order.getCount());
        	tp.setRetailPrice(order.getRetailPrice());
        	tp.setSettlementPrice(order.getSettlementPrice());
        	tp.setInDate(order.getInDate());
        	tp.setVisitorName(order.getFetchName());
        	tp.setVisitorPhone(order.getFetchPhone());
        	tp.setCardType(order.getCardType());
        	tp.setCardNum(order.getCardNum());
        	tp.setSex(order.getSex());
        	//订单状态 0:等待付款
        	tp.setStatus(0);
        	//等待支付
        	tp.setPay_status(0);
        	tpList.add(tp);
    		
    	}else{
    		order.setIsRealName(1);
    		String[] visitorName = orderInfo.getVisitorName();
    		String[] visitorPhone = orderInfo.getVisitorPhone();
    		//String[] tp_cardType = orderInfo.getTp_cardType();
    		String[] tp_cardNum = orderInfo.getTp_cardNum();
    		if(visitorName == null || visitorName.length == 0){
    			map.put(Const.ERROR_INFO, "出游人信息错误");
    			map.put(Const.RESPONSE_STATE, 500);
    			return map;
    		}else{

        		if(visitorName.length != order.getCount()){
        			map.put(Const.ERROR_INFO, "出游人数量与购票数量不一致");
        			map.put(Const.RESPONSE_STATE, 500);
        			return map;
        		}
    			for(int i = 0;i<visitorName.length;i++){
    				//订单出游人
    	        	S_orderTravelPeople tp = new S_orderTravelPeople();
    	        	tp.setUser_id(order.getUser_id());
    	        	tp.setOrderId(order.getOrderId());
    	        	tp.setProductSn(order.getProductSn());
    	        	tp.setProductName(order.getProductName());
    	        	tp.setProductType(order.getProductType());
    	        	tp.setUnitPrice(order.getUnitPrice());
    	        	//订单状态 0:等待付款
    	        	tp.setStatus(0);
    	        	//限购一人一件
    	        	tp.setCount(1);
    	        	tp.setRetailPrice(order.getRetailPrice());
    	        	tp.setSettlementPrice(order.getSettlementPrice());
    	        	tp.setInDate(order.getInDate());
    	        	tp.setVisitorName(visitorName[i]);
    	        	tp.setVisitorPhone(visitorPhone[i]);
    	        	tp.setCardType("1");
    	        	tp.setCardNum(tp_cardNum[i]);
    	        	tp.setSex(IdCardUtils.getSexByIdCard(tp_cardNum[i]));
    	        	//等待支付
    	        	tp.setPay_status(0);
    	        	tpList.add(tp);
    			}
    		}
    		
    		
    		
    		/*//限购类型（空为不限购 0按身份证限购 1按手机号 每天每个产品限制 
    		if("0".equals(product.getLimitType())){
    			
    		}else if("1".equals(product.getLimitType())){
    			
    		}
    		//限购时间  1按天 2 按周 3 按月
    		if("1".equals(product.getLimitTime())){
    			
    		}else if("2".equals(product.getLimitTime())){
    			
    		}else if("3".equals(product.getLimitTime())){
    			
    		}
    		*/

    	}
    	//保存订单信息
    	dao.save("s_orderMapper.saveOrder", order);
    	//保存出游人信息
    	dao.save("s_orderTravelPeopleMapper.saveOrderTravelPeople", tpList);
    	map.put("orderId", order.getOrderId());
    	map.put(Const.RESPONSE_STATE, Const.RESPONSE_SUCCESS);
		return map;
	}
	
    
    
    

    /**
     * 查询用户订单信息
     * @param user_id   用户id
     * @param orderId   订单id
     * @return
     * @throws Exception
     */
	public S_order getUserOrderInfo(String user_id, String orderId)throws Exception {
		
		return (S_order)dao.findForObject("s_orderMapper.getUserOrderInfo", new String[]{user_id,orderId});
	}
    
	/**
	 * 获取待支付订单信息
     * @param user_id   用户id
     * @param orderId   订单id
     * @return
     * @throws Exception
	 */
	public S_order getUserOrderPayInfo(String user_id, String orderId)throws Exception {
		return (S_order)dao.findForObject("s_orderMapper.getUserOrderPayInfo", new String[]{user_id,orderId});
	}
    

	/**
	 * 获取订单信息
     * @param user_id   用户id
     * @param orderId   订单id
     * @param pay_status 付款状态（0：等待支付、1：付款成功、2：退款中、3：已退款）
     * @return
     * @throws Exception
	 */
	public S_order getUserOrderPayInfo(String user_id, String orderId,int pay_status)throws Exception {
		return (S_order)dao.findForObject("s_orderMapper.getUserOrderPayInfo", new String[]{user_id,orderId,pay_status+""});
	}
	
	/**
     * 查询用户所有订单信息
     * @param user_id   用户id
     * @param orderId   订单id
     * @return
     * @throws Exception
     */
	public List<SpotOrderInfoDTO> getAllUserOrder(Page<Map<String,String>> page)throws Exception{
		return (List<SpotOrderInfoDTO>) dao.findForList("s_orderMapper.getAllUserOrderPage", page);
	}
	
	/**
	 * 查询活动订单数量
	 * @param map
	 * @throws Exception
	 */
	public Map<String,String> findUserOrderCount(String userId)throws Exception{
		return (Map<String,String>) dao.findForObject("s_orderMapper.findUserOrderCount", userId);
	}
	
	/**
	 * 查看订单详情
	 * @param userId 用户ID
	 * @param id 订单ID
	 * @return
	 * @throws Exception
	 */
	public SpotOrderInfoDTO findUserOrderInfo(String userId,String id) throws Exception{
		Map<String,String> map = new HashMap<String,String>();
		map.put("userId", userId);
		map.put("id", id);
		
		return (SpotOrderInfoDTO) dao.findForObject("s_orderMapper.findUserOrderInfo", map);
	}
	
	/**
	 * 退票申请
	 * @param otp_id 出游人ID
	 * @throws Exception
	 */
	public Map<String,String> applyticket(String[] otp_id) throws Exception {
		Map<String,String> map = new HashMap<String,String>();
		List<S_orderTravelPeople> tpList = (List<S_orderTravelPeople>)dao.findForList("s_orderTravelPeopleMapper.getTravelPeopleByid",otp_id);
		if(tpList == null || tpList.size() == 0){
			map.put("errorMsg", "退款订单信息错误！");
			map.put("errorCode", "105");
			return map;
		}
		for(S_orderTravelPeople tp:tpList){
			System.out.println(tp.getProductName());
			System.out.println(tp.getProductSn()); 
			System.out.println(tp.getVisitorName());
			System.out.println(tp.getVisitorPhone());
			System.out.println(tp.getCardNum());
			System.out.println(tp.getInDate());
			SpotTicketUtil.refundTicket(tp);
		}
		//更新退票信息
		dao.update("s_orderTravelPeopleMapper.updateApplyTicketState", tpList);
		map.put("errorCode", "0");
		return map;
	}
	


	/**
	 * 取消未支付的订单
	 * @param user_id  用户id
	 * @param orderId 订单id
	 * @throws Exception
	 */
	public void cancelNotPay(String user_id, String orderId) throws Exception {
		
		dao.update("s_orderMapper.updateCancelNotPay", new String[]{user_id,orderId});
	}
	


	/**
	 * 获取未支付超时订单
	 * @return
	 */
	public List<S_order> selectNotPayOrderList() {
		try {
			List<S_order> orderList = (List<S_order>)dao.findForList("selectNotPayOrder");
			return orderList;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		
	}
	/**
	 * 取消未支付超时订单
	 * @param orderList
	 */
	public void closePayTimeoutOrder(List<S_order> orderList) {
		for(S_order order:orderList){
			try {
				dao.update("s_orderMapper.updateCancelNotPay", new String[]{order.getUser_id()+"",order.getOrderId()});
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	


	
	/**
	 * 更新景点订单门票出票状态   （定时任务调用）
	 * @param agentOrderId 对接方订单id
	 * @param status  订单状态   （参见 com.op.spot.OutTicketStatus 定义）
	 */
	public void updateOrderTicketStatus(String agentOrderId, int status) {
		
		try {
			dao.update("s_orderMapper.updateOrderTicketStatus", new String[]{agentOrderId,status+""});
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
