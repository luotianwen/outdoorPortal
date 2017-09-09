package com.op.controller.emay;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.op.dto.usercenter.user.update.YzmDTO;
import com.op.entity.users.Users;
import com.op.plugin.sms.AlidayuSMS;
import com.op.plugin.sms.SmsTemplateCode;
import com.op.service.emay.EmayInfoService;
import com.op.service.users.UsersService;
import com.op.util.Const;

/** 
 * 短信模板信息表(emayInfo)Controller
 * @author 世峰户外商城 
 * @version Revision: 1.00 
 *  Date: 2016-01-11 14:31:08 
 */ 
@Controller
@RequestMapping(value="/emayInfo")
public class EmayInfoController {
	
	// 短信服务
	@Resource(name="emayInfoServiceImpl")
	private EmayInfoService emayInfoServiceImpl;
    
    @Resource(name="usersServiceImpl")
    private UsersService usersService;

	/**
	 * 方法描述：发送旧手机验证码
	 * 返回类型：Map<String,Object>
	 * @param session
	 * @return
	 */
	@RequestMapping(value="/sendOldPhoneYzm")
	@ResponseBody
	public Map<String,Object> sendOldPhoneYzm(HttpSession session
			,@RequestParam(value="template") String template){
		
		Map<String,Object> resultMap = new HashMap<String,Object>();
		resultMap.put(Const.RESPONSE_STATE, 500);
		
		// 根据不同模板，存储不同类型的更改数据 
		String sessionKey = "";
		if(template.equals("updatePassword")){
			sessionKey = Const.UPDATE_PASSWORD_OBJ;
		}else if(template.equals("oldPhoneInfo")){
			sessionKey = Const.UPDATE_PHONE_OBJ;
		}else{
			resultMap.put(Const.ERROR_INFO, "获取短信模板失败，请刷新页面重试!!!");
			return resultMap;
		}
		
		try {
			// 当前登录用户，获取该用户的手机号码
			Users user = (Users) session.getAttribute(Const.SESSION_USER);
			
			// 判断发送时间等信息
			YzmDTO alreadyDto = (YzmDTO) session.getAttribute(sessionKey);
			if(alreadyDto != null  && alreadyDto.getOldPostDate() != null
					&& ((new Date()).getTime() - alreadyDto.getOldPostDate().getTime())/1000 < 60){
				resultMap.put(Const.ERROR_INFO, "短信发送间隔为60秒");
				return resultMap;
			}
			
			// 获取相应的短信模板
			String templateInfo = emayInfoServiceImpl.getEmayTemplate(template);
			
			if("".equals(templateInfo)){
				resultMap.put(Const.ERROR_INFO, "获取短信模板失败，请稍后重试!!!");
				return resultMap;
			}

			// 生成4为数字验证码
			int yzm = (int) (Math.random() * 8999) + 1000;

			// 替换验证码至模板
			String info = templateInfo.replace("{yzm}", yzm+"");
			int i = AlidayuSMS.sendNotify(user.getuPhone(),info,SmsTemplateCode.VERIFICATIONCODE);
			
			// 发送成功
			if(i == 0){
				// 将发送信息存入session
				if(alreadyDto == null){
					alreadyDto = new YzmDTO();
				}
				alreadyDto.setOldPhone(user.getuPhone());
				alreadyDto.setOldPostDate(new Date());
				alreadyDto.setOldYzm(yzm+"");
				session.setAttribute(sessionKey, alreadyDto);
				
				resultMap.put(Const.RESPONSE_STATE, Const.RESPONSE_SUCCESS);
				return resultMap;
			}else{
				resultMap.put(Const.ERROR_INFO, "发送失败，请稍后重试!!!");
				return resultMap;
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			resultMap.put(Const.ERROR_INFO, "操作异常");
		}
		
		return resultMap;
	}
	
	/**
	 * 方法描述：发送新手机验证码
	 * 返回类型：Map<String,Object>
	 * @param session
	 * @return
	 */
	@RequestMapping(value="/sendNewPhoneYzm")
	@ResponseBody
	public Map<String,Object> sendNewPhoneYzm(HttpSession session,@RequestParam(value="phone") String phone){
		
		Map<String,Object> resultMap = new HashMap<String,Object>();
		resultMap.put(Const.RESPONSE_STATE, 500);
		
		try {
			// 当前登录用户，获取该用户的手机号码
			Users user = (Users) session.getAttribute(Const.SESSION_USER);
			
			// 手机号码验证
			if(phone.equals(user.getuPhone())){
				resultMap.put(Const.ERROR_INFO, "手机号码不能和原来的一样！");
				return resultMap;
			}
			
			if(!usersService.checkUserPhoneUnique(phone)){
				resultMap.put(Const.ERROR_INFO, "该手机号码已经注册过!");
				return resultMap;
			}
			
			// 判断发送时间等信息
			YzmDTO alreadyDto = (YzmDTO) session.getAttribute(Const.UPDATE_PHONE_OBJ);
			if(alreadyDto != null && alreadyDto.getNewPostDate() != null
					&& ((new Date()).getTime() - alreadyDto.getNewPostDate().getTime())/1000 < 60){
				resultMap.put(Const.ERROR_INFO, "短信发送间隔为60秒");
				return resultMap;
			}
			
			// 获取相应的短信模板
			String newPhoneInfo = emayInfoServiceImpl.getEmayTemplate("newPhoneInfo");
			
			if("".equals(newPhoneInfo)){
				resultMap.put(Const.ERROR_INFO, "获取短信模板失败，请稍后重试!!!");
				return resultMap;
			}

			// 生成4为数字验证码
			int yzm = (int) (Math.random() * 8999) + 1000;

			// 替换验证码至模板
			String info = newPhoneInfo.replace("{yzm}", yzm+"");
			int i = AlidayuSMS.sendNotify(phone,info,SmsTemplateCode.VERIFICATIONCODE);
			
			// 发送成功
			if(i == 0){
				// 将发送信息存入session
				if(alreadyDto == null){
					alreadyDto = new YzmDTO();
				}
				alreadyDto.setNewPhone(phone);
				alreadyDto.setNewPostDate(new Date());
				alreadyDto.setNewYzm(yzm+"");
				session.setAttribute(Const.UPDATE_PHONE_OBJ, alreadyDto);
				
				resultMap.put(Const.RESPONSE_STATE, Const.RESPONSE_SUCCESS);
				return resultMap;
			}else{
				resultMap.put(Const.ERROR_INFO, "发送失败，请稍后重试!!!");
				return resultMap;
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			resultMap.put(Const.ERROR_INFO, "操作异常");
		}
		
		return resultMap;
	}
	
	
	/**
	 * 方法描述：修改手机号码
	 * 返回类型：Map<String,Object>
	 * @param session
	 * @param oldPhoneYzm	旧验证码
	 * @param newPhoneYzm	新验证码
	 * @param newPhone	新手机号
	 * @return
	 */
	@RequestMapping(value="/updatePhone")
	@ResponseBody
	public Map<String,Object> updatePhone(HttpSession session
			,@RequestParam(value="oldPhoneYzm")Integer oldPhoneYzm
			,@RequestParam(value="newPhoneYzm")Integer newPhoneYzm
			,@RequestParam(value="newPhone")String newPhone){
		Map<String,Object> resultMap = new HashMap<String,Object>();
		resultMap.put(Const.RESPONSE_STATE, 500);
		try {
			Users user = (Users) session.getAttribute(Const.SESSION_USER);
			
			// 判断是否进行过验证码操作
			YzmDTO dto = (YzmDTO) session.getAttribute(Const.UPDATE_PHONE_OBJ);
			
			if(dto == null || dto.check()){
				resultMap.put(Const.ERROR_INFO, "请先获取手机验证码再继续操作!");
				return resultMap;
			}

			// 比对验证码和手机号
			if(!newPhone.equals(dto.getNewPhone())){
				resultMap.put(Const.ERROR_INFO, "该更换手机号不是接收验证码手机号!");
				return resultMap;
			}
			
 			if(!usersService.checkUserPhoneUnique(newPhone)){
				resultMap.put(Const.ERROR_INFO, "该手机号码已经注册过!");
				return resultMap;
			}
			
			// 比对验证码
			if((oldPhoneYzm+"").equals(dto.getOldYzm())){
				resultMap.put(Const.ERROR_INFO, "旧手机验证码匹配失败!");
				return resultMap;
			}
			
			// 比对验证码
			if((newPhoneYzm+"").equals(dto.getNewYzm())){
				resultMap.put(Const.ERROR_INFO, "新手机验证码匹配失败!");
				return resultMap;
			}
			
			// 有效期判断
			Date cd = new Date();
			if((cd.getTime()-dto.getOldPostDate().getTime()) / 1000 / 60 >= 5){
				resultMap.put(Const.ERROR_INFO, "旧手机验证码已过有效期，请重新获取!");
				return resultMap;
			}
			
			if((cd.getTime()-dto.getNewPostDate().getTime()) / 1000 / 60 >= 5){
				resultMap.put(Const.ERROR_INFO, "新手机验证码已过有效期，请重新获取!");
				return resultMap;
			}
			
			dto.setuId(user.getuId());
			usersService.updateUserPhone(dto,resultMap);
			
			// 更改成功
			if(resultMap.get(Const.RESPONSE_STATE).toString().equals(Const.RESPONSE_SUCCESS)){
				session.removeAttribute(Const.UPDATE_PHONE_OBJ);
				
				user.setuPhone(newPhone);
				session.setAttribute(Const.SESSION_USER, user);
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			resultMap.put(Const.ERROR_INFO, resultMap.get(Const.ERROR_INFO) == null ? "修改异常":resultMap.get(Const.ERROR_INFO));
		}
		return resultMap;
	}
}
