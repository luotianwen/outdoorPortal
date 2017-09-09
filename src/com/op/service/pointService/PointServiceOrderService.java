package com.op.service.pointService;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.op.dto.pointService.order.OrderEvaluateInfo;
import com.op.dto.pointService.order.OrderRefundDTO;
import com.op.dto.pointService.order.OrderRefundInfoDTO;
import com.op.dto.pointService.order.PointServiceOrderInfoDTO;
import com.op.dto.pointService.order.PointServiceOrderListDTO;
import com.op.entity.alipay.Alipay;
import com.op.entity.pointService.order.PointServiceOrder;
import com.op.entity.users.Users;
import com.op.plugin.page.Page;


@Service("pointServiceOrderService")
public interface PointServiceOrderService {

	/**
	 * 查询场馆类型的数量
	 */
	Map<String,String> findPointServiceOrderCount(String usersId) throws Exception;
	
	/**
	 * 查询所有场馆订单
	 * @param userId 用户ID
	 * @param page 分页
	 * @return
	 * @throws Exception
	 */
	List<PointServiceOrderListDTO> findPointServiceOrderByUserId(Page<Map<String,Object>> page) throws Exception;
	
	/**
	 * 查询商家所有场馆订单
	 * @param userId 用户ID
	 * @param page 分页
	 * @return
	 * @throws Exception
	 */
	List<PointServiceOrderListDTO> findPointServiceBusinessOrderByUserId(Page<Map<String,Object>> page) throws Exception;
	
	/**
	 * 新增订单
	 * @param pointServiceOrder
	 * @throws Exception
	 */
	PointServiceOrder savePointServiceOrder(String pspId,String psId,String num,Users users) throws Exception;
	
	/**
	 * 通过ID查询订单
	 * @param id
	 * @throws Exception
	 */
	PointServiceOrderInfoDTO findPointServiceOrderById(String id,String userId) throws Exception;
	
	/**
	 * 查询订单应付金额
	 * @param id
	 * @return
	 * @throws Exception
	 */
	double findPointServiceOrderPriceById(String id) throws Exception;
	
	/**
	 * 修改订单
	 * @param pointServiceOrder
	 * @throws Exception
	 */
	void updatePointServiceOrder(PointServiceOrder pointServiceOrder) throws Exception;
	
	/**
	 * 修改订单支付状态
	 */
	void updateOrderPayState(Alipay ap) throws Exception;
	
	/**
	 * 申请退款页面显示信息
	 * @param id 订单ID
	 * @param userId 用户ID
	 * @throws Exception
	 */
	OrderRefundDTO orderRefundInfo(String id,String userId) throws Exception;
	
	/**
	 * 获取申请退款页面数据
	 * @param id 订单ID
	 * @param userId 用户ID
	 * @throws Exception
	 */
	void orderRefund(String id,String userId) throws Exception;
	
	/**
	 * 申请退款修改订单信息
	 * @param id 订单ID
	 * @param userId 用户ID
	 * @param uniqueness 退款识别码
	 * @param reason 退款原因
	 * @throws Exception
	 */
	void updateOrderRefund(String id,String userId,String uniqueness,String reason,Map<String,String> map) throws Exception;
	
	
	/**
	 * 删除订单
	 * @param id 订单ID
	 * @param userId 用户ID
	 * @throws Exception
	 */
	void deletePointServiceOrder(String id,String userId) throws Exception;
	
	/**
	 * 购买人数
	 * @param id 项目ID
	 * @return
	 * @throws Exception
	 */
	int findPurchaseNumber(String id) throws Exception;
	
	/**
	 * 新增唯一标识吗
	 * @param id
	 * @throws Exception
	 */
	void addPointServiceUniqueness(String id) throws Exception;
	
	/**
	 * 立即付款查询未支付订单信息
	 */
	PointServiceOrder findOrderForImmediatePay(String psoId,String userId) throws Exception;
	
	/**
	 * 取消退款
	 */
	void cancelRefund(String id,String userId) throws Exception;
	
	/**
	 * 退款详情
	 * @param id 订单ID
	 * @param userId 用户ID
	 * @return
	 * @throws Exception
	 */
	List<OrderRefundInfoDTO> findRefundInfo(String id,String userId) throws Exception;
	
	/**
	 * 订单评论页面显示信息
	 * @param id 订单ID
	 * @param userId 用户ID
	 * @return
	 * @throws Exception
	 */
	OrderEvaluateInfo findOrderEvaluateInfo(String id,String userId) throws Exception;
	
	/**
	 * 到店消费(验证密码)
	 * @param pass
	 * @param userId
	 * @throws Exception
	 */
	void verifyPassword(String pass,String userId,Map<String,String> map) throws Exception;
	
}
