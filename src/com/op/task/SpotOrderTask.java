package com.op.task;

import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.util.StringUtils;

import com.op.spot.entity.S_order;
import com.op.spot.service.S_orderService;
import com.op.spot.visitbeijing.SpotTicketUtil;
import com.op.util.Const;
import com.op.util.jedis.RedisUtil;

/**
 * 订单相关定时任务
 * @author WinZhong
 *
 */
public class SpotOrderTask {

	protected Logger logger = Logger.getLogger(this.getClass());
	@Resource(name="s_orderServiceImpl")
	private S_orderService orderService;
	
		/**
		 * 取消超时未支付的订单
		 */
		public void timeoutCancel(){
			logger.info("开始执行【取消超时未支付的订单】任务");
			List<S_order> orderList = orderService.selectNotPayOrderList();
			if(!StringUtils.isEmpty(orderList) || orderList.size() > 0){
				orderService.closePayTimeoutOrder(orderList);
			}
		}
		
		/**
		 * 检测订单出票状态
		 */
		public void checkTicketStatus(){
			logger.info("开始执行【检测订单出票状态】任务");
			List<String> orderIdList =  RedisUtil.lrange(Const.CHECK_TICKET_QUEUE,0,-1);
			if(orderIdList != null && orderIdList.size() > 0){
				for(String orderId:orderIdList){
					int status = SpotTicketUtil.queryOrder(orderId);
					if(status != 2){
						//更新出票状态
						orderService.updateOrderTicketStatus(orderId,status);
						//从队列中移除
						RedisUtil.lrem(Const.CHECK_TICKET_QUEUE, 1, orderId);
					}
				}
			}
		}
}
