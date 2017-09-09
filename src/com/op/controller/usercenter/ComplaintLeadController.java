package com.op.controller.usercenter;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.op.controller.BaseController;
import com.op.service.usercenter.ActiveSignupService;
import com.op.service.usercenter.ComplaintImageService;
import com.op.service.usercenter.ComplaintLeadService;
import com.op.util.Const;


/**
 * 投诉领队实体类
 * @author panyongwei
 * Date: 2016年1月13日 14:37:28
 */
@RequestMapping("/complaintLead")
@Controller
public class ComplaintLeadController extends BaseController {
		// 投诉领队
		@Resource(name="complaintLeadServiceImpl")
		private ComplaintLeadService complaintLeadServiceImpl;
		
		//报名信息
		@Resource(name="activeSignupServiceImpl")
		ActiveSignupService activeSignupServiceImpl;
		
		//投诉领队证据图片
		@Resource(name="complaintImageServiceImpl")
		ComplaintImageService complaintImageServiceImpl;
		
		
		/**
		 * 投诉领队
		 * @param clMap
		 * @param images
		 * @return
		 */
		@RequestMapping(value="/complaint",method=RequestMethod.POST)
		@ResponseBody
		public Map<String, Object> insertComplaintLead(String orderId,String conent,String phone,String images){
			Map<String,Object> map=new HashMap<String,Object>();//返回操作信息
			map.put(Const.RESPONSE_STATE, "200");//默认执行此操作成功!
			map.put(Const.SUCCESS_INFO, "操作成功!");
			
			Map<String,Object> CIMap=new HashMap<String,Object>();//投诉领队证据图片
			Map<String,Object> CLMap=new HashMap<String,Object>();//投诉领队信息
			Map<String,Object> activeSignupMap=new HashMap<String,Object>();//报名信息
			activeSignupMap.put("asu_user_id",this.getSessionUser().getuId());
			activeSignupMap.put("asu_order_id", orderId);//订单ID
				try {
					CLMap.put("orderId", orderId);
					CLMap.put("plaintiff", this.getSessionUser().getuId());//投诉者id
					if(activeSignupServiceImpl.selectOrderId(activeSignupMap)==1 && complaintLeadServiceImpl.selectOrderId(CLMap)==null){//如果有此订单,并且此订单没有投诉过领队
						CLMap.put("orderType", 1);//订单类型暂时为:活动订单
						CLMap.put("state", 1);//投诉状态为:待受理
						CLMap.put("phone", phone);//投诉人手机
						CLMap.put("conent", conent);//投诉内容
						if(images!=""){//有证据图片
							String[] imagesArray=images.split(",");//根据逗号分割图片字符串.
							complaintLeadServiceImpl.insertComplaintLead(CLMap);//插入投诉信息
							CIMap.put("cl_id",CLMap.get("cl_id"));//投诉id
							CIMap.put("img_urls",imagesArray);//证据图片url集合
							complaintImageServiceImpl.insertCI(CIMap);
						}else {
							complaintLeadServiceImpl.insertComplaintLead(CLMap);
						}
					}else if(activeSignupServiceImpl.selectOrderId(activeSignupMap)==1 && complaintLeadServiceImpl.selectOrderId(CLMap)!=null){
						map.put(Const.RESPONSE_STATE, 500);
						map.put(Const.ERROR_INFO, "您已经投诉过该领队不可重复投诉!请关注处理结果!");
						return map;
					}else {
						map.put(Const.RESPONSE_STATE, 500);
						map.put(Const.ERROR_INFO, "投诉失败!");
						return map;
					}
				} catch (Exception e) {
					map.put(Const.RESPONSE_STATE, 500);
					map.put(Const.ERROR_INFO, "投诉失败!");
					return map;
				}
			return map;
		}
			
}
