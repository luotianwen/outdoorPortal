package com.op.spot.service;

import java.util.List;
import java.util.Map;

import com.op.plugin.page.Page;
import com.op.spot.dto.SpotOrderInfoDTO;
import com.op.spot.dto.SubmitOrderDTO;
import com.op.spot.entity.S_order;
/** 
 * 景点订单(s_order)接口
 * @author Win Zhong 
 * @version Revision: 1.00 
 *  Date: 2016-09-21 13:40:39 
 */  
public interface S_orderService {

	
	/**
	 * 提交订单
	 * @param orderInfo
	 * @return
	 * @throws Exception
	 */
    Map<String,Object> submitOrder(SubmitOrderDTO orderInfo)throws Exception;

    /**
     * 查询用户订单信息
     * @param user_id   用户id
     * @param orderId   订单id
     * @return
     * @throws Exception
     */
	S_order getUserOrderInfo(String user_id, String orderId)throws Exception;

	/**
	 * 获取待支付订单信息
     * @param user_id   用户id
     * @param orderId   订单id
     * @return
     * @throws Exception
	 */
	S_order getUserOrderPayInfo(String user_id, String orderId)throws Exception;


	/**
	 * 获取订单信息
     * @param user_id   用户id
     * @param orderId   订单id
     * @param pay_status 付款状态（0：等待支付、1：付款成功、2：退款中、3：已退款）
     * @return
     * @throws Exception
	 */
	S_order getUserOrderPayInfo(String user_id, String orderId,int pay_status)throws Exception;

	/**
     * 查询用户所有订单信息
     * @param user_id   用户id
     * @param orderId   订单id
     * @return
     * @throws Exception
     */
	List<SpotOrderInfoDTO> getAllUserOrder(Page<Map<String,String>> page)throws Exception;
	
	/**
	 * 查询活动订单数量
	 * @param map
	 * @throws Exception
	 */
	Map<String,String> findUserOrderCount(String userId)throws Exception;
	
	/**
	 * 查看订单详情
	 * @param userId 用户ID
	 * @param id 订单ID
	 * @return
	 * @throws Exception
	 */
	SpotOrderInfoDTO findUserOrderInfo(String userId,String id) throws Exception;

	
	/**
	 * 退票申请
	 * @param otp_id 出游人ID
	 * @throws Exception
	 */
	Map<String,String> applyticket(String[] otp_id) throws Exception;

	/**
	 * 取消未支付的订单
	 * @param user_id  用户id
	 * @param orderId 订单id
	 * @throws Exception
	 */
	void cancelNotPay(String user_id, String orderId) throws Exception;

	/**
	 * 获取未支付超时订单
	 * @return
	 */
	List<S_order> selectNotPayOrderList();
	/**
	 * 取消未支付超时订单
	 * @param orderList
	 */
	void closePayTimeoutOrder(List<S_order> orderList);

	
	/**
	 * 更新景点订单门票出票状态   （定时任务调用）
	 * @param agentOrderId 对接方订单id
	 * @param status  订单状态   （参见 com.op.spot.OutTicketStatus 定义）
	 */
	void updateOrderTicketStatus(String agentOrderId, int status);
	
}
