package com.op.controller.pointService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.op.controller.BaseController;
import com.op.dto.pointService.order.OrderEvaluateInfo;
import com.op.dto.pointService.order.OrderRefundDTO;
import com.op.dto.pointService.order.OrderRefundInfoDTO;
import com.op.dto.pointService.order.PointServiceOrderInfoDTO;
import com.op.dto.pointService.order.PointServiceOrderListDTO;
import com.op.entity.alipay.Alipay;
import com.op.entity.pointService.order.PointServiceOrder;
import com.op.entity.users.Users;
import com.op.plugin.page.Page;
import com.op.service.pointService.PointServiceOrderService;
import com.op.util.Const;

/**
 * =============================================================
 * 项目名：outdoorPortal
 * 类描述：场馆订单Controller
 * 创建时间： 2016-6-7
 * modification list：
 * =============================================================
 */
@Controller
@RequestMapping(value="/pointServiceOrder")
public class PointServiceOrderController extends BaseController{
	
	@Resource(name="pointServiceOrderServiceImpl")
	PointServiceOrderService pointServiceOrderServiceImpl;
	
	/**
	 * 新增订单
	 * @param mv
	 * @param pspId
	 * @param psId
	 * @param num
	 * @return
	 */
	@RequestMapping("/addOrder")
	public ModelAndView addOrder(ModelAndView mv,String pspId,String psId,String num,HttpServletRequest request){
		Users users = this.getSessionUser();
		
		try {
			PointServiceOrder pointServiceOrder = pointServiceOrderServiceImpl.savePointServiceOrder(pspId,psId,num,users);
			
			request.getSession().setAttribute("order_"+pointServiceOrder.getPso_id(), pointServiceOrder);
			mv.setViewName("redirect:payOrder.html?id="+pointServiceOrder.getPso_id());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return mv;
	}
	
	/**
	 * 支付订单
	 * @param mv
	 * @param request
	 * @return
	 */
	@RequestMapping("/payOrder")
	public ModelAndView payOrder(ModelAndView mv,HttpSession session,String id){
		PointServiceOrder pointServiceOrder = (PointServiceOrder) session.getAttribute("order_"+id);
		
		if(StringUtils.isEmpty(pointServiceOrder)){
			try {
				pointServiceOrder = pointServiceOrderServiceImpl.findOrderForImmediatePay(id, this.getSessionUser().getuId());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		if(!StringUtils.isEmpty(pointServiceOrder)){
			// 创建session支付数据
			getout_trade_no(pointServiceOrder.getPso_project_id(),pointServiceOrder.getPso_price()+"",session,pointServiceOrder.getPso_id(),pointServiceOrder.getPso_zh_name()+pointServiceOrder.getPso_project_name());
			
			mv.addObject("pointServiceOrder", pointServiceOrder);
			mv.setViewName("pointService/order/venue-order-2");
		}else{
			mv.setViewName("404");
		}
		
		return mv;
	}
	
	/**
	 * 方法描述：
	 * 返回类型：String
	 * @param aId	活动ID
	 * @param average	应该支付的金额
	 * @param session	session
	 * @param  asu_id 订单ID
	 * @param 
	 * @return
	 */
	private void getout_trade_no(String pspId,String average,HttpSession session,String psoId,String title){
		Alipay ap = new Alipay();
		
		// 商户订单号 商户网站订单系统中唯一订单号
		ap.setOut_trade_no(psoId);
		
		// 订单名称
		ap.setSubject(title);
		
		// 订单订单描述
		ap.setBody("玩嘛团购费用");
		
		// 商品地址(ID)
		ap.setShow_url(pspId);
		
		// 支付订单号对应的金额；key为对应的支付订单号，值为支付的金额
		ap.setTotal_fee(average);
		
		// 商家支付业务类型为活动报名
		ap.setOutDoorPayType(Const.PAY_ACTIVE);
		
		//自定义公用回传参数
		ap.setExtra_common_param("pointService");
		
		// 放入session，谨防修改; key为对应的支付订单号，值为支付的对象
		session.setAttribute("pay_"+psoId, ap);
		
	}
	
	
	/**
	 * 查询所有订单
	 * @param mv
	 * @param page
	 * @return
	 */
	@RequestMapping("/MyOrder")
	public ModelAndView myOrder(ModelAndView mv,Page<Map<String,Object>> page,String type){
		Users users = this.getSessionUser();
		
		mv.addObject("users", users);
		setUserCenterHeaderMap(mv, "我的管家", "场馆订单");
		
		if(StringUtils.isEmpty(type)){
			type = "0";
		}
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("userId", users.getuId());
		map.put("type", type);
		page.setT(map);
		try {
			if(users.getuType()==50){
				List<PointServiceOrderListDTO> pointServiceOrderListDTO = pointServiceOrderServiceImpl.findPointServiceBusinessOrderByUserId(page);
				mv.addObject("pointServiceOrderListDTO", pointServiceOrderListDTO);
				mv.setViewName("usercenter/poinService/pointOrder/my-venue-order");
			}else{
				List<PointServiceOrderListDTO> pointServiceOrderListDTO = pointServiceOrderServiceImpl.findPointServiceOrderByUserId(page);
				mv.addObject("pointServiceOrderListDTO", pointServiceOrderListDTO);
				
				Map<String,String> countMap = pointServiceOrderServiceImpl.findPointServiceOrderCount(users.getuId());
				mv.addObject("countMap", countMap);
				mv.addObject("type", type);
				
				mv.setViewName("usercenter/poinService/order/my-venue-order");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.error("查询所有订单方法异常！！！！！！", e);
		}
		mv.addObject("page", page);
		return mv;
	}
	
	/**
	 * 订单详情
	 */
	@RequestMapping("/orderInfo")
	public ModelAndView orderInfo(String id,ModelAndView mv){
		
		try {
			PointServiceOrderInfoDTO pointServiceOrderInfoDTO = pointServiceOrderServiceImpl.findPointServiceOrderById(id, this.getSessionUser().getuId());
			mv.addObject("pointServiceOrderInfoDTO", pointServiceOrderInfoDTO);
			mv.addObject("users", this.getSessionUser());
			setUserCenterHeaderMap(mv, "我的管家", "场馆订单");
			mv.setViewName("usercenter/poinService/order/venue-order-info");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return mv;
	}
	
	/**
	 * 跳转申请退款页面
	 */
	@RequestMapping("/orderRefund")
	public ModelAndView orderRefund(ModelAndView mv,String id){
		try {
			OrderRefundDTO orderRefundDTO = pointServiceOrderServiceImpl.orderRefundInfo(id,this.getSessionUser().getuId());
			
			mv.addObject("orderRefundDTO", orderRefundDTO);
			mv.addObject("users", this.getSessionUser());
			setUserCenterHeaderMap(mv, "我的管家", "场馆订单");
			mv.setViewName("usercenter/poinService/order/apply-refund");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return mv;
	}
	
	/**
	 * 修改退款信息
	 * @return
	 */
	@RequestMapping("/updateOrderRefund")
	@ResponseBody
	public Map<String,String> updateOrderRefund(String id,String uniqueness,String reason){
		Map<String,String> map = new HashMap<String,String>();
		
		try {
			pointServiceOrderServiceImpl.updateOrderRefund(id,this.getSessionUser().getuId(),uniqueness,reason,map);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.error("申请退款方法异常！！！！！！", e);
			map.put(Const.RESPONSE_STATE, "500");
		}

		return map;
	}
	
	/**
	 * 取消退款
	 */
	@RequestMapping("/cancelRefund")
	@ResponseBody
	public Map<String,String> cancelRefund(String id){
		Map<String,String> map = new HashMap<String,String>();
		
		try {
			pointServiceOrderServiceImpl.cancelRefund(id,this.getSessionUser().getuId());
			map.put(Const.RESPONSE_STATE, Const.RESPONSE_SUCCESS);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			map.put(Const.RESPONSE_STATE, "500");
		}
		
		return map;
	}
	
	/**
	 * 退款详情
	 */
	@RequestMapping("/refundInfo")
	public ModelAndView refundInfo(ModelAndView mv,String id){
		try {
			List<OrderRefundInfoDTO> orderRefundInfoDTOList = pointServiceOrderServiceImpl.findRefundInfo(id, this.getSessionUser().getuId());
			mv.addObject("orderRefundInfoDTOList", orderRefundInfoDTOList);
			mv.addObject("psoId", id);
			mv.addObject("users", this.getSessionUser());
			setUserCenterHeaderMap(mv, "我的管家", "场馆订单");
			mv.setViewName("usercenter/poinService/order/apply-refund-info");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return mv;
	}
	
	/**
	 * 删除订单
	 */
	@RequestMapping("/deleteOrder")
	@ResponseBody
	public Map<String,String> deleteOrder(String id){
		Map<String,String> map = new HashMap<String,String>();
		
		try {
			pointServiceOrderServiceImpl.deletePointServiceOrder(id, this.getSessionUser().getuId());
			map.put(Const.RESPONSE_STATE, Const.RESPONSE_SUCCESS);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.error("删除订单方法异常！！！！！！", e);
			map.put(Const.RESPONSE_STATE, "500");
		}
		
		return map;
	}
	
	/**
	 * 方法描述：设置个人中心首页需要的数据
	 * 返回类型：void
	 * @param mv
	 */
	@Override
	public void setUserCenterHeaderMap(ModelAndView mv,String center,String left){
		Map<String,Object> map = new HashMap<String,Object>();
		// 当前导航标签
		map.put("center", center);
		//当前左侧标签
		map.put("left", left);
		mv.addObject("map", map);
	}
	
	/**
	 * 评价订单
	 */
	@RequestMapping("/orderEvaluate")
	public ModelAndView orderEvaluate(ModelAndView mv,String id){
		try {
			Users users = this.getSessionUser();
			
			OrderEvaluateInfo orderEvaluateInfo = pointServiceOrderServiceImpl.findOrderEvaluateInfo(id,users.getuId());
			mv.addObject("orderEvaluateInfo", orderEvaluateInfo);
			
			mv.addObject("users", users);
			setUserCenterHeaderMap(mv, "我的管家", "场馆订单");
			mv.setViewName("usercenter/poinService/order/venue-rate");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error("评价订单页面展示信息查询方法异常！！！！！！！！！！",e);
			mv.setViewName("404");
		}
		
		return mv;
	}
	
	/**
	 * 到店消费(验证密码)
	 */
	@RequestMapping("/verifyPassword")
	@ResponseBody
	public Map<String,String> verifyPassword(String pass){
		Map<String,String> map = new HashMap<String,String>();
		
		try {
			pointServiceOrderServiceImpl.verifyPassword(pass,this.getSessionUser().getuId(),map);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return map;
	}
	
}
