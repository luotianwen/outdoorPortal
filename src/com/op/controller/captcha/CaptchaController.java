package com.op.controller.captcha;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.op.controller.BaseController;
import com.op.entity.users.Users;
import com.op.plugin.sms.AlidayuSMS;
import com.op.plugin.sms.SmsTemplateCode;
import com.op.service.emay.EmayInfoService;
import com.op.util.DateUtil;

@Controller
@RequestMapping(value="/captcha")
public class CaptchaController extends BaseController {

	@Resource(name="emayInfoServiceImpl")
	private EmayInfoService emayInfoServiceImpl;
	
	/**
	 * 发送短信验证码 
	 * @param session
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value="sms")
	@ResponseBody
	public Object sms(HttpSession session) throws Exception{
		Map<String,Object> map = new HashMap<String,Object>();
		long timeInterval = this.timeInterval(session);
		Users user = this.getSessionUser();
		if(timeInterval == 0 && null != user){
			// 获取注册短信模板
		    String setPayPwdInfo = emayInfoServiceImpl.getEmayTemplate("setPayPwd");
		   // 生成4为数字验证码
 			int yzm = (int) (Math.random() * 8999) + 1000;
 			logger.info("给手机"+user.getuPhone()+"发送的验证码为："+yzm);
 			// 替换验证码至模板
 			String info = setPayPwdInfo.replace("{yzm}", yzm+"");
			AlidayuSMS.sendNotify(user.getuPhone(),info,SmsTemplateCode.VERIFICATIONCODE);
			
			session.setAttribute("yzm"+user.getuPhone(), yzm);
			map.put("resultCode", 0);
		}else{
			map.put("resultCode", 1);
			map.put("errorInfo", "系统繁忙，请稍后再试！");
		}
		logger.info(timeInterval+"****"+ map);
		return map;
	}
	
	/**
	 * 发送短信验证码
	 * @param session
	 * @return
	 */
	@RequestMapping(value="sms/{mobile}")
	@ResponseBody
	public Object sms(HttpSession session,@PathVariable String mobile){
		System.err.println("mobile:"+mobile);
		return "";
	}
	
	/**
	 * 计算验证码间隔
	 * @param session
	 * @return
	 */
	private long timeInterval(HttpSession session){
		Object time = session.getAttribute("captchaTimeInterval");
		if(null == time){
			session.setAttribute("captchaTimeInterval", new Date());
			return 0;
		}else{
			logger.info(time);
			long s = DateUtil.Sdifference((Date)time, new Date());
			if(s <= 60){
				return s;
			}else{
				session.setAttribute("captchaTimeInterval", new Date());
				return 0;
			}
		}
	}
	
	
}
