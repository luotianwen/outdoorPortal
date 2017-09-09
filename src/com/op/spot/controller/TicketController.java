package com.op.spot.controller;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.newAlipay.AlipayInfo;
import com.newAlipay.config.AlipayConfig;
import com.newAlipay.util.AlipaySubmit;
import com.op.controller.BaseController;
import com.op.entity.usercenter.LinkSignUpUser;
import com.op.entity.users.Users;
import com.op.plugin.page.Page;
import com.op.service.pay.AlipayInfoService;
import com.op.service.usercenter.LinkSignUpUserService;
import com.op.spot.dto.SpotOrderInfoDTO;
import com.op.spot.dto.SubmitOrderDTO;
import com.op.spot.entity.PriceInfo;
import com.op.spot.entity.Product;
import com.op.spot.entity.S_order;
import com.op.spot.service.PriceInfoService;
import com.op.spot.service.ProductService;
import com.op.spot.service.S_orderService;
import com.op.util.Const;
import com.op.util.DateUtil;
import com.op.util.IdWorker;


/**
 * 景点门票
 * @author WinZhong
 *
 */
@Controller
@RequestMapping(value = "/ticket")
public class TicketController extends BaseController {

	
	/**
	 * 景点门票产品
	 */
	@Resource(name="productServiceImpl")
	private ProductService productServiceImpl;

	/**
	 * 景点门票产品价格
	 */
	@Resource(name="priceInfoServiceImpl")
	private PriceInfoService priceInfoServiceImpl;
	
	/**
	 * 景点门票产品价格
	 */
	@Resource(name="s_orderServiceImpl")
	private S_orderService s_orderServiceImpl;
	
	/**
	 *  常用报名人
	 */
	@Resource(name="linkSignUpUserServiceImpl")
	LinkSignUpUserService LinkSignUpUserServiceImpl;
	
	/**
	 * 门票下单
	 * @param mv
	 * @param productId  产品id
	 * @return
	 */
    @RequestMapping(value = "/orderInput")
    public ModelAndView orderInput( ModelAndView mv,int productId) {
    	
    	try {
			Product product = productServiceImpl.getTicketInfo(productId);

			 SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			//当天日期 yyyy-MM-dd
			String nowDate = null;
	    	//预定时间限制  默认值就0|0  0|0表示无限制  9:00|16:00表示只能9点至16点下单
			if(!"0|0".equals(product.getLimitOrderTime())){
				String[] limitOrderTime = product.getLimitOrderTime().split("\\|");
				SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
				long now = sdf.parse(sdf.format(new Date())).getTime();
				if("0".equals(limitOrderTime[0])){
					mv.addObject("allottedTime", " 当天"+limitOrderTime[1]+"前");
					if(sdf.parse(limitOrderTime[1]).getTime() < now){
						nowDate = format.format(new Date());
						mv.addObject("nowDate", nowDate);
					}
				}else{
					mv.addObject("allottedTime", " 当天"+limitOrderTime[0]+"-"+limitOrderTime[1]+"前");
					if(sdf.parse(limitOrderTime[0]).getTime() > now && sdf.parse(limitOrderTime[1]).getTime() < now){
						nowDate = format.format(new Date());
						mv.addObject("nowDate", nowDate);
					}
				}
			}else{
				mv.addObject("allottedTime", "全天");
			}
			
			String exceptDate = product.getExceptDate();
			/*if(exceptDate != null){
				exceptDate  = "'"+exceptDate.replaceAll(",", "','")+"','"+nowDate+"'"; 
				mv.addObject("exceptDate", exceptDate);
			}*/
			
			List<PriceInfo> producPriceList = product.getProducPriceList();
			if(producPriceList != null && producPriceList.size() == 1){
				PriceInfo price = producPriceList.get(0);
				String displayBeginDate = price.getDisplayBeginDate();
		        String displayEndDate = price.getDisplayEndDate();
		        mv.addObject("displayBeginDate", displayBeginDate);
		        mv.addObject("displayEndDate", displayEndDate);
		        StringBuffer sb = new StringBuffer();
		        Calendar start = Calendar.getInstance(); 
		        start.setTime(format.parse(displayBeginDate));
		        
		        Calendar end = Calendar.getInstance();
		        end.setTime(format.parse(displayEndDate));  
	            end.add(Calendar.DAY_OF_MONTH,1);
	            
		        Date now = new Date();
		        if(!now.before(start.getTime())){
		        	start.setTime(now);  
		        	//start.add(Calendar.DAY_OF_MONTH,-1);
			        mv.addObject("displayBeginDate", format.format(start.getTime()));
		        }
		        List<String> exceptWeekList = null;
		        if(product.getExceptWeek() != null){
		        	exceptWeekList = Arrays.asList(product.getExceptWeek().split(","));
		        }
		        System.out.println(exceptWeekList+"==========");
		        while(start.before(end)){
		        	String d = format.format(start.getTime());
		        	//System.out.println(d+"**"+start.get(Calendar.DAY_OF_WEEK));
	            	String week = start.get(Calendar.DAY_OF_WEEK)-1+"";
	            	if(exceptWeekList != null && exceptWeekList.contains(week)){
	            		if(!StringUtils.isEmpty(product.getSpecialDate()) && d.equals(product.getSpecialDate())){
	            			start.add(Calendar.DAY_OF_MONTH,1);
	            			continue;
	            		}
	            		if(!StringUtils.isEmpty(exceptDate)){
	        				exceptDate = exceptDate+","+format.format(start.getTime()); 
	        			}else{
	        				exceptDate = format.format(start.getTime()); 
	        			}
	            	}
		        	if(nowDate != null && d.equals(nowDate)){
			        	start.add(Calendar.DAY_OF_MONTH,1);
		        		continue;
		        	}
		        	sb.append("<input type=\"hidden\" id=\"");
		        	sb.append(d);
		        	sb.append("_0\" name=\"d_");
		        	sb.append(d);
		        	sb.append("\" value=\"");
		        	sb.append(price.getRetailPrice());
		        	sb.append("\" stock=\"");
		        	sb.append(price.getStockNumber());
		        	sb.append("\">");
		        	sb.append("\n");
		        	start.add(Calendar.DAY_OF_MONTH,1);
		        }
		        mv.addObject("useDate", sb.toString());
			}else{
				/*if(producPriceList != null && producPriceList.size() > 0){
						
			        Calendar start = Calendar.getInstance(); 
			        start.setTime(format.parse(producPriceList.get(0).getUseDate()));
			        Calendar end = Calendar.getInstance();
			        end.setTime(format.parse(producPriceList.get(producPriceList.size()-1).getUseDate()));
		            end.add(Calendar.DAY_OF_MONTH,1);
		            
		            List<String> exceptWeekList = null;
			        if(product.getExceptWeek() != null){
			        	exceptWeekList = Arrays.asList(product.getExceptWeek().split(","));
			        }
		            
		            while(start.before(end)){
		            	String d = format.format(start.getTime());
		            	String week = start.get(Calendar.DAY_OF_WEEK)-1+"";
		            	//System.out.println(format.format(start.getTime())+"**"+week);
		            	if(exceptWeekList != null && exceptWeekList.contains(week)){
		            		if(!StringUtils.isEmpty(product.getSpecialDate()) && d.equals(product.getSpecialDate())){
		            			start.add(Calendar.DAY_OF_MONTH,1);
		            			continue;
		            		}
		            		if(!StringUtils.isEmpty(exceptDate)){
		        				exceptDate  = exceptDate+","+d; 
		        			}else{
		        				exceptDate  =d; 
		        			}
		            	}
		            	start.add(Calendar.DAY_OF_MONTH,1);
		            }
				}*/
			}
			System.out.println(exceptDate);
			if(nowDate != null){
				exceptDate  = exceptDate+","+nowDate; 
			}
			System.out.println(exceptDate);
			exceptDate  = "'"+exceptDate.replaceAll(",", "','")+"'";
			mv.addObject("exceptDate", exceptDate);
			Map<String, Object> map = new HashMap<String, Object>();
		    map.put("ht", "景区");
		    mv.addObject("map", map);
		    
		    Users user = this.getSessionUser();
			List<LinkSignUpUser> linkSignUpUser = LinkSignUpUserServiceImpl.selectUser(user.getuId());
			mv.addObject("linkSignUpUser", linkSignUpUser);
		    
			mv.addObject("productId", productId);
			mv.addObject("product", product);
			mv.setViewName("spot/orderInput");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
		return mv;
    }
	
    
    /**
     * 提交成功
     * @param order
     * @return
     */
    @RequestMapping(value = "/submitOrder")
    @ResponseBody
    public Object submitOrder(SubmitOrderDTO orderInfo){
    	Map<String,Object> map = null;
    	try {
    		Users user = this.getSessionUser();
    		//user = new Users();user.setuId("1");
    		PriceInfo price = priceInfoServiceImpl.getPriceInfo(orderInfo.getProductSn(),orderInfo.getInDate());
    		orderInfo.setUnitPrice(price.getRetailPrice());
    		orderInfo.setSettlementPrice(price.getSettlementPrice()); 
    		orderInfo.setProductName(price.getProductName());
    		orderInfo.setRetailPrice(price.getRetailPrice()*orderInfo.getCount());
    		orderInfo.setUser_id(Integer.parseInt(user.getuId()));
			System.out.println(orderInfo.toString());
			map = s_orderServiceImpl.submitOrder(orderInfo);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			map = new HashMap<String,Object>();
			map.put(Const.RESPONSE_STATE, 500);
			map.put(Const.ERROR_INFO, "订单提交异常");
		}
    	
		return map;
    }
    
    @RequestMapping(value = "/payInfo")
    public ModelAndView payInfo( ModelAndView mv,@ModelAttribute("orderId")String orderId) {
    	Users user = this.getSessionUser();
		//user = new Users();user.setuId("1");
		try {
			//获取待支付订单信息
			S_order order = s_orderServiceImpl.getUserOrderPayInfo(user.getuId(),orderId);
			mv.setViewName("spot/pay");
			mv.addObject("order", order);
			
			mv.addObject("time", DateUtil.YYYYMMDDgetAfterHour(order.getCreate_time(), "2"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return mv;
    }
    
	/**
	 * 支付订单
	 * @param mv
	 * @param orderId
	 * @return
	 */
    @RequestMapping(value = "/pay")
    public String pay(String orderId,RedirectAttributes attr) {
		Users user = this.getSessionUser();
		//user = new Users();user.setuId("1");
    	try {
    		//获取待支付订单信息
			S_order order = s_orderServiceImpl.getUserOrderPayInfo(user.getuId(),orderId);
			
			if(order != null){
				AlipayInfo alipayInfo = new AlipayInfo();
				//商户订单号，商户网站订单系统中唯一订单号，必填
			    alipayInfo.setOut_trade_no(order.getOrderId());
			    //订单名称，必填
			    alipayInfo.setSubject(order.getProductName());
			    //付款金额，必填
			    alipayInfo.setTotal_fee(order.getOrder_total_price());
			    //商品描述，可空
			    alipayInfo.setBody("【玩嘛】门票-"+order.getProductName()+" 数量：x"+order.getCount()+" 入园日期:"+order.getInDate());
				//自定义公用回传参数 （格式：service 注解name,返回地址）
				alipayInfo.setExtra_common_param("s_orderPayServiceImpl,ticket/payResult");
				//交易创建时间
				alipayInfo.setGmt_create(new Date());
				//交易创建时间
				alipayInfo.setQuantity(order.getCount());
				//商品单价
				alipayInfo.setPrice(order.getRetailPrice()/100);
				//订单类型（1：活动订单、2：场馆订单、3：景点门票订单）
				alipayInfo.setOrder_type(3);
				
				attr.addFlashAttribute("alipayInfo", alipayInfo);
			  
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
		return "redirect:/ali/pay";
    }
    
    
    @RequestMapping(value = "/payResult")
    public ModelAndView payResult( ModelAndView mv,String orderId,String result) {
    	Users user = this.getSessionUser();
		//user = new Users();user.setuId("1");
		//支付失败
		if("fail".equals(result)){
			mv.setViewName("spot/lose");
			return mv;
		}
		try {
			//获取支付成功订单信息
			S_order order = s_orderServiceImpl.getUserOrderPayInfo(user.getuId(),orderId,1);
			mv.setViewName("spot/succed");
			mv.addObject("order", order);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return mv;
    }
    
    
    /**
     * 查询用户所有订单
     */
    @RequestMapping("/MyOrder")
    public ModelAndView MyOrder(ModelAndView mv,Page<Map<String,String>> page,String tabCode){
    	Users user = this.getSessionUser();
    	//订单类型
    	if(StringUtils.isEmpty(tabCode)){
    		tabCode = "all";
    	}
    	
    	try {
    		Map<String,String> map = new HashMap<String,String>();
    		map.put("userId", user.getuId());
    		map.put("tabCode", tabCode);
    		page.setT(map);
    		
			List<SpotOrderInfoDTO> list = s_orderServiceImpl.getAllUserOrder(page);
			//未支付订单修改支付时间
			for(int i=0,len=list.size();i<len;i++){
				if(list.get(i).getPay_status()==0){
					list.get(i).setWaitPayTime(DateUtil.YYYYMMDDgetAfterHour(list.get(i).getCreate_time(),"1"));
				}
			}
			
			mv.addObject("list", list);
			mv.addObject("page", page);
			
			map.clear();
			map = s_orderServiceImpl.findUserOrderCount(user.getuId());
			mv.addObject("count", map);
			mv.addObject("tabCode", tabCode);
			
			setUserCenterHeaderMap(mv, "我的管家", "景点订单");
			
			mv.setViewName("spot/order/scenic-order");
		} catch (Exception e) {
			logger.error("查询用户所有订单方法异常！！！！！！！！！！！", e);
			mv.setViewName("500");
		}
    	
    	return mv;
    }
    
    /**
     * 查看订单详情
     */
    @RequestMapping("/orderInfo")
    public ModelAndView orderInfo(ModelAndView mv,String id){
    	Users users = this.getSessionUser();
    	
    	try {
			SpotOrderInfoDTO dto = s_orderServiceImpl.findUserOrderInfo(users.getuId(),id);
			mv.addObject("dto", dto);
			setUserCenterHeaderMap(mv, "我的管家", "景点订单");
			
			mv.setViewName("spot/order/scenic-order-info");
		} catch (Exception e) {
			logger.error("查看订单详情方法异常！！！！！！！！！！！", e);
			mv.setViewName("500");
		}
    	
    	return mv;
    }
    
    /**
     * 申请退票
     */
    @RequestMapping("/apply")
    public ModelAndView apply(ModelAndView mv,String id){
    	Users users = this.getSessionUser();
    	
    	try {
			SpotOrderInfoDTO dto = s_orderServiceImpl.findUserOrderInfo(users.getuId(),id);
			mv.addObject("dto", dto);
			setUserCenterHeaderMap(mv, "我的管家", "景点订单");
			mv.setViewName("spot/order/scenic-apply-refund");
		} catch (Exception e) {
			logger.error("申请退票方法异常！！！！！！！！！！！", e);
			mv.setViewName("500");
		}
    	
    	return mv;
    }

    
    /**
     * 申请退票
     */
    @RequestMapping("/applyticket")
    public ModelAndView applyticket(ModelAndView mv,String[] otpId,String orderId){
    	Users users = this.getSessionUser();
    	Map<String,String> map = null;
    	try {
    		map = s_orderServiceImpl.applyticket(otpId);
 			SpotOrderInfoDTO dto = s_orderServiceImpl.findUserOrderInfo(users.getuId(),orderId);
 			mv.addObject("dto", dto);
 			setUserCenterHeaderMap(mv, "我的管家", "景点订单");
		} catch (Exception e) {
			 map = new HashMap<String,String>();
			 map.put("errorMsg", "系统错误！");
			 map.put("errorCode", "104");
			 logger.error("申请退票方法异常！！！！！！！！！！！", e);
		}

		mv.addObject("errorMap", map);
    	mv.setViewName("spot/order/applyticket");
    	return mv;
    }
    
    
    /**
     * 取消未支付的订单
     */
    @RequestMapping("/cancel")
    @ResponseBody
    public Map<String,Object> cancel(String orderId){
    	Users users = this.getSessionUser();
    	Map<String,Object> map = new HashMap<String,Object>();
    	try {
 			s_orderServiceImpl.cancelNotPay(users.getuId(), orderId);
 			map.put(Const.RESPONSE_STATE, Const.RESPONSE_SUCCESS);
		} catch (Exception e) {
			map.put(Const.RESPONSE_STATE, 500);
			map.put(Const.ERROR_INFO, "取消订单失败");
			 logger.error("取消未支付的订单异常！！！！！！！！！！！", e);
		}
 
    	return map;
    }
    
    
    
}
