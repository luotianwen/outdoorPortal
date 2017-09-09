package com.op.service.pointService.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;




import com.op.dao.BaseDao;
import com.op.dto.pointService.order.BuyProjectDTO;
import com.op.dto.pointService.order.OrderEvaluateInfo;
import com.op.dto.pointService.order.OrderRefundDTO;
import com.op.dto.pointService.order.OrderRefundInfoDTO;
import com.op.dto.pointService.order.PointServiceOrderInfoDTO;
import com.op.dto.pointService.order.PointServiceOrderListDTO;
import com.op.entity.alipay.Alipay;
import com.op.entity.pointService.order.PointServiceOrder;
import com.op.entity.pointService.order.PointServiceUniqueness;
import com.op.entity.users.Users;
import com.op.plugin.page.Page;
import com.op.service.pointService.PointServiceOrderService;
import com.op.service.pointService.PointServiceProjectsService;
import com.op.util.Const;
import com.op.util.SerializationUtil;
import  com.op.util.jedis.RedisUtil;


@Service("pointServiceOrderServiceImpl")
public class PointServiceOrderServiceImpl implements PointServiceOrderService{

	@Resource(name = "baseDaoImpl")
	private BaseDao dao;

	@Resource(name="pointServiceProjectsServiceImpl")
	PointServiceProjectsService pointServiceProjectsServiceImpl;
	
	/**
	 * 查询场馆类型的数量
	 */
	@Override
	public Map<String,String> findPointServiceOrderCount(String usersId) throws Exception{
		return (Map<String,String>) dao.findForObject("pointServiceOrderMapper.findPointServiceOrderCount", usersId);
	}
	
	/**
	 * 查询所有场馆订单
	 * @param userId
	 * @return
	 * @throws Exception
	 */
	@Override
	public List<PointServiceOrderListDTO> findPointServiceOrderByUserId(Page<Map<String,Object>> page) throws Exception{
		return (List<PointServiceOrderListDTO>)dao.findForList("pointServiceOrderMapper.findPointServiceOrderByUserIdPage", page);
	}
	
	/**
	 * 查询商家所有场馆订单
	 * @param userId 用户ID
	 * @param page 分页
	 * @return
	 * @throws Exception
	 */
	@Override
	public List<PointServiceOrderListDTO> findPointServiceBusinessOrderByUserId(Page<Map<String,Object>> page) throws Exception{
		return (List<PointServiceOrderListDTO>)dao.findForList("pointServiceOrderMapper.findPointServiceBusinessOrderByUserId", page);
		
	}
	
	/**
	 * 新增订单
	 * @param pspId 项目ID
	 * @param psId 场馆ID
	 * @param num 数量
	 * @param users 登录用户
	 * @return
	 * @throws Exception
	 */
	@Override
	public PointServiceOrder savePointServiceOrder(String pspId,String psId,String num,Users users) throws Exception{
		PointServiceOrder pointServiceOrder = new PointServiceOrder();

		
		byte[] bs = RedisUtil.get(("buyProject_"+pspId+"_"+users.getuId()).getBytes());
		
		BuyProjectDTO buyProjectDTO = (BuyProjectDTO) SerializationUtil.deserialize(bs);
		
		if(StringUtils.isEmpty(buyProjectDTO)){
			try {
				buyProjectDTO = pointServiceProjectsServiceImpl.buyProjectDTOInfo(pspId);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		// 项目ID
		pointServiceOrder.setPso_project_id(pspId);
		// 项目名称
		pointServiceOrder.setPso_project_name(buyProjectDTO.getPsp_item_name());
		// 项目单价
		pointServiceOrder.setPso_project_price(buyProjectDTO.getPsp_item_price());
		// 项目打折价
		pointServiceOrder.setPso_discount_price(buyProjectDTO.getPsp_discount_price());
		// 项目数量
		pointServiceOrder.setPso_project_num(num);
		// 订单总金额
		pointServiceOrder.setPso_price(buyProjectDTO.getPsp_discount_price()*Integer.valueOf(num));
		//有效期start
		pointServiceOrder.setPso_validity_date_start(buyProjectDTO.getPsp_validity_date_start());
	 	//有效期end
		pointServiceOrder.setPso_validity_date_end(buyProjectDTO.getPsp_validity_date_end());
	 	//场馆中文名
		pointServiceOrder.setPso_zh_name(buyProjectDTO.getPs_zh_name());
		// 地点服务ID
		pointServiceOrder.setPso_point_service_id(psId);
		// 手机号
		pointServiceOrder.setPso_phone(users.getuPhone());
		// 姓名
		pointServiceOrder.setPso_user_name(users.getuName());
		//创建人
		pointServiceOrder.setPso_create_user_id(users.getuId());
		
		int count = (int) dao.update("pointServiceOrderMapper.updateBuyOrder", pointServiceOrder);
		
		if(count==0){
			dao.save("pointServiceOrderMapper.savePointServiceOrder", pointServiceOrder);
		}else{
			Map<String,String> map = new HashMap<String,String>();
			
			map.put("pspId", pspId);
			map.put("psId", psId);
			map.put("userId", users.getuId());
			
			pointServiceOrder = (PointServiceOrder) dao.findForObject("pointServiceOrderMapper.findOrderForBuy", map);
		}
		
		return pointServiceOrder;
	}
	
	/**
	 * 立即付款查询未支付订单信息
	 */
	@Override
	public PointServiceOrder findOrderForImmediatePay(String psoId,String userId) throws Exception{
		Map<String,String> map = new HashMap<String,String>();
		
		map.put("psoId", psoId);
		map.put("userId", userId);
		
		return (PointServiceOrder) dao.findForObject("pointServiceOrderMapper.findOrderForImmediatePay", map);
	}
	
	
	/**
	 * 通过ID查询订单
	 * @param id
	 * @throws Exception
	 */
	@Override
	public PointServiceOrderInfoDTO findPointServiceOrderById(String id,String userId) throws Exception{
		Map<String,String> map = new HashMap<String,String>();
		
		map.put("id", id);
		map.put("userId", userId);
		
		return (PointServiceOrderInfoDTO)dao.findForObject("pointServiceOrderMapper.findPointServiceOrderById", map);
	}
	
	/**
	 * 查询订单应付金额
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public double findPointServiceOrderPriceById(String id) throws Exception{
		String price = (String)dao.findForObject("pointServiceOrderMapper.findPointServiceOrderPriceById", id);
		if(StringUtils.isEmpty(price)){
			return 0;
		}else{
			return Double.valueOf(price);
		}
		
	}
	
	/**
	 * 修改订单
	 * @param pointServiceOrder
	 * @throws Exception
	 */
	@Override
	public void updatePointServiceOrder(PointServiceOrder pointServiceOrder) throws Exception{
		dao.update("pointServiceOrderMapper.updatePointServiceOrder", pointServiceOrder);
	}
	
	/**
	 * 修改订单支付状态
	 */
	@Override
	public void updateOrderPayState(Alipay ap) throws Exception{
		dao.update("pointServiceOrderMapper.updateOrderPayState", ap);
	}
	
	/**
	 * 申请退款页面显示信息
	 * @param id 订单ID
	 * @param userId 用户ID
	 * @throws Exception
	 */
	@Override
	public OrderRefundDTO orderRefundInfo(String id,String userId) throws Exception{
		Map<String,String> map = new HashMap<String,String>();
		map.put("id", id);
		map.put("userId", userId);
		return (OrderRefundDTO) dao.findForObject("pointServiceOrderMapper.orderRefundInfo", map);
	}
	
	/**
	 * 申请退款
	 * @param id 订单ID
	 * @param userId 用户ID
	 * @throws Exception
	 */
	@Override
	public void orderRefund(String id,String userId) throws Exception{
		Map<String,String> map = new HashMap<String,String>();
		map.put("id", id);
		map.put("userId", userId);
		
		dao.update("pointServiceOrderMapper.orderRefund", map);
	}
	
	/**
	 * 申请退款修改订单信息
	 * @param id 订单ID
	 * @param userId 用户ID
	 * @param uniqueness 退款识别码
	 * @param reason 退款原因
	 * @throws Exception
	 */
	@Override
	public void updateOrderRefund(String id,String userId,String uniqueness,String reason,Map<String,String> pageMap) throws Exception{
		Map<String,Object> map = new HashMap<String,Object>();
		//验证识别码是否可退款
		map.put("id", id);
		map.put("userId", userId);
		map.put("uniqueness", uniqueness);
		//验证识别码是否可退款
		int count = (int) dao.findForObject("pointServiceUniquenessMapper.verificationUniqueness", map);
		
		String[] uniquenessAll = uniqueness.split(",");
		
		if(count == uniquenessAll.length){
			//修改订单信息
			map.put("refundNum", uniquenessAll.length);
			int ordernum = (int) dao.update("pointServiceOrderMapper.updateOrderRefund", map);
			//验证订单是否已退款或不是自己的订单
			if(ordernum>0){
				//修改识别码信息
				dao.update("pointServiceUniquenessMapper.updateUniquenessRefund", map);
				
				//新增退款申请
				dao.save("pointServiceOrderMapper.saveRefundReason", map);
				
				//新增退款原因
				String[] reasonAll = reason.split(",");
				List<Map<String,String>> list = new ArrayList<Map<String,String>>();
				for(int i=0,len=reasonAll.length;i<len;i++){
					Map<String,String> listmap = new HashMap<String,String>();
					
					listmap.put("reason", reasonAll[i]);
					
					list.add(listmap);
				}
				
				map.put("list", list);
				dao.save("pointServiceOrderMapper.saveOrderRefundReason", map);
				
				pageMap.put(Const.RESPONSE_STATE, Const.RESPONSE_SUCCESS);
			}else{
				pageMap.put(Const.RESPONSE_STATE, "500");
			}
		}else{
			pageMap.put(Const.RESPONSE_STATE, "500");
		}
	}
	
	/**
	 * 删除订单
	 * @param id 订单ID
	 * @param userId 用户ID
	 * @throws Exception
	 */
	@Override
	public void deletePointServiceOrder(String id,String userId) throws Exception{
		Map<String,String> map = new HashMap<String,String>();
		map.put("id", id);
		map.put("userId", userId);
		
		dao.update("pointServiceOrderMapper.deletePointServiceOrder", map);
	}
	
	/**
	 * 购买人数
	 * @param id 项目ID
	 * @return
	 * @throws Exception
	 */
	@Override
	public int findPurchaseNumber(String id) throws Exception{
		 String num = (String) dao.findForObject("pointServiceOrderMapper.findPurchaseNumber", id);
		 if(StringUtils.isEmpty(num)){
			 return 0;
		 }else{
			 return Integer.valueOf(num);
		 }
		 
	}

	/**
	 * 新增唯一标识吗
	 * @param id
	 * @throws Exception
	 */
	@Override
	public void addPointServiceUniqueness(String id) throws Exception{
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("id", id);
		

		
		List<String> list = new ArrayList<String>();
		
		int num = (int) dao.findForObject("pointServiceOrderMapper.orderProjectNum", id);
		
		//Map<String,String> listMap =  new HashMap<String,String>();
		for(int i=0;i<num;i++){
			list.add(getUniqueness());
		}
		map.put("uniqueness", list);
		
		dao.save("pointServiceUniquenessMapper.addPointServiceUniqueness", map);
	}
	
	//获取随机数
	public String getUniqueness(){
		long num = (long) (Math.random()*8999999999L)+1000000000L;
		
		if("[null]".equals(RedisUtil.hmget("uniqueness", num+"")+"")){
			RedisUtil.hset("uniqueness", num+"",num+"");
			
			return num+"";
		}else{
			return getUniqueness();
		}
		
	}
	
	/**
	 * 取消退款
	 */
	@Override
	public void cancelRefund(String id,String userId) throws Exception{
		//验证是否可退款
		Map<String,String> map = new HashMap<String,String>();
		map.put("id", id);
		map.put("userId", userId);
		
		OrderRefundInfoDTO orderRefundInfoDTO = (OrderRefundInfoDTO) dao.findForObject("pointServiceOrderMapper.verificationCancelRefund", map);
		
		if(orderRefundInfoDTO.getPsoar_state()==10){
			//修改退款申请信息
			dao.update("pointServiceOrderMapper.cancelApplyRefund", map);
			//修改订单信息
			map.put("psoId", orderRefundInfoDTO.getPso_id());
			map.put("cancelNum", orderRefundInfoDTO.getPsoar_refund_number()+"");
			dao.update("pointServiceOrderMapper.cancelOrderRefund", map);
			//修改标识码信息
			map.put("psuId", orderRefundInfoDTO.getPsu_id());
			dao.update("pointServiceOrderMapper.cancelUniquenessRefund", map);
		}
	}
	
	/**
	 * 退款详情
	 * @param id 订单ID
	 * @param userId 用户ID
	 * @return
	 * @throws Exception
	 */
	@Override
	public List<OrderRefundInfoDTO> findRefundInfo(String id,String userId) throws Exception{
		Map<String,String> map = new HashMap<String,String>();
		map.put("id", id);
		map.put("userId", userId);
		
		return (List<OrderRefundInfoDTO>) dao.findForList("pointServiceOrderMapper.findRefundInfo", map);
	}
	
	/**
	 * 订单评论页面显示信息
	 * @param id 订单ID
	 * @param userId 用户ID
	 * @return
	 * @throws Exception
	 */
	@Override
	public OrderEvaluateInfo findOrderEvaluateInfo(String id,String userId) throws Exception{
		Map<String,String> map = new HashMap<String,String>();
		map.put("id", id);
		map.put("userId", userId);
		
		return (OrderEvaluateInfo) dao.findForObject("pointServiceOrderMapper.findOrderEvaluateInfo", map);
	}
	
	/**
	 * 到店消费(验证密码)
	 * @param pass
	 * @param userId
	 * @throws Exception
	 */
	@Override
	public void verifyPassword(String pass,String userId,Map<String,String> map) throws Exception{
		Map<String,String> sqlmap = new HashMap<String,String>();
		sqlmap.put("pass", pass);
		sqlmap.put("userId", userId);
		
		PointServiceUniqueness pointServiceUniqueness = (PointServiceUniqueness) dao.findForObject("pointServiceUniquenessMapper.verifyPassword", sqlmap);
		
		if(pointServiceUniqueness!=null){
			if(pointServiceUniqueness.getPso_refund_state()!=0&&pointServiceUniqueness.getPso_refund_state()!=40&&pointServiceUniqueness.getPso_refund_state()!=50){
				map.put(Const.RESPONSE_STATE, "500");
				map.put(Const.ERROR_INFO, "消费码已申请退款，无法使用！");
			}else{
				if(pointServiceUniqueness.getPsu_state()==1){
					map.put(Const.RESPONSE_STATE, "500");
					map.put(Const.ERROR_INFO, "消费码已使用，无法再次使用！");
				}else{
					dao.update("pointServiceUniquenessMapper.updateUniquenessUse", pointServiceUniqueness.getPsu_id());
					
					int num = (int) dao.findForObject("pointServiceUniquenessMapper.orderUniquenessNum", pointServiceUniqueness.getPso_id()+"");
					
					if(num==0){
						dao.update("pointServiceOrderMapper.updateOrderUse", pointServiceUniqueness.getPso_id()+"");
					}
					
					map.put(Const.RESPONSE_STATE, Const.RESPONSE_SUCCESS);
				}
			}
		}else{
			map.put(Const.RESPONSE_STATE, "500");
			map.put(Const.ERROR_INFO, "消费码不存在，请查证后再试！");
		}		
		
	}
	
	
}
