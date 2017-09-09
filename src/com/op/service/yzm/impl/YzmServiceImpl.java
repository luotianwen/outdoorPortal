package com.op.service.yzm.impl;

import java.util.Date;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;





import com.op.dao.BaseDao;
import com.op.dto.usercenter.user.update.YzmDTO;
import com.op.plugin.sms.AlidayuSMS;
import com.op.plugin.sms.SmsTemplateCode;
import com.op.service.emay.EmayInfoService;
import com.op.service.yzm.YzmService;
import com.op.util.Const;
import  com.op.util.jedis.RedisUtil;

/**
 * =============================================================
 * 项目名：outdoorPortal
 * 类描述：验证码工具类
 * 创建人：Yan
 * 创建时间： 2016-4-7
 * modification list：
 * =============================================================
 */
@Service("yzmServiceImpl")
public class YzmServiceImpl implements YzmService{

	Logger log = Logger.getLogger(this.getClass());
	
	@Resource(name="emayInfoServiceImpl")
	EmayInfoService emayInfoServiceImpl;
	
	@Resource(name = "baseDaoImpl")
	private BaseDao dao;
	
	/**
	 * 短信验证码发送间隔时间
	 */
	static final Integer POST_YZM_INTERVAL_TIME = 60;
	
	/**
	 * 短信验证码发送间隔时间
	 */
	static final Integer YZM_VALID_MINUTE = 5;
	
	/**
	 * 方法描述：
	 * 返回类型：boolean
	 * @param session
	 * @param map	返回错误信息用
	 * @param phone	发送验证码的手机号
	 * @param template	验证码模板名称
	 * @return
	 */
	@Override
	public void validateYzmTime(HttpSession session,Map<String,String> map,String phone,String template,boolean oldOrNew){
		// 默认返回失败状态
		map.put(Const.RESPONSE_STATE, "500");
		
		// 判断是否在注册规定时间外
		Date currentDate = new Date();
		
		YzmDTO dto = new YzmDTO();

		String type = type(template);
		
		dto = (YzmDTO) session.getAttribute(type);
		
		// 发送验证码时间匹配
		if(dto != null){
			if(oldOrNew){
				if((currentDate.getTime() - dto.getOldPostDate().getTime()) / 1000 < POST_YZM_INTERVAL_TIME){
					map.put(Const.ERROR_INFO, "短信发送间隔为"+POST_YZM_INTERVAL_TIME+"秒，请稍后重试!!!");
					return;
				}
			}else{
				if(null!=dto.getNewPhone() && !"".equals(dto.getNewPhone()) && (currentDate.getTime() - dto.getNewPostDate().getTime()) / 1000 < POST_YZM_INTERVAL_TIME){
					map.put(Const.ERROR_INFO, "短信发送间隔为"+POST_YZM_INTERVAL_TIME+"秒，请稍后重试!!!");
					return;
				}
				//更改手机号，新手机号最多发送三次，之后需重新验证旧手机
				if(dto.getCount()>=3){
					map.put(Const.ERROR_INFO, "更换新手机号过于频繁，请稍后重试!!!");
					return;
				}
			}
		}
		try {
			if(postYzm(dto, currentDate, session, map, phone, template,oldOrNew,type)){
				map.put(Const.RESPONSE_STATE, Const.RESPONSE_SUCCESS);
			}
		} catch (Exception e) {
			log.error("发送验证码异常", e);
			map.put(Const.ERROR_INFO, "发送验证码异常");
			return;
		}
	}
	
	/**
	 * 方法描述：针对该手机号控制一天最多发送三次验证码，加入缓存判断
	 * 返回类型：boolean
	 * @param p
	 * @return
	 */
	boolean checkRedisPhoneNum(String p,Map<String,String> map){

		String num = RedisUtil.get(p);
		
		if(num == null){
			RedisUtil.set(p, 1+"");
			
			// 设置有效期为一天
			RedisUtil.expire(p, 24*60*60);
			return true;
		}else{
			if(Integer.valueOf(num) >= 5){
				map.put(Const.ERROR_INFO, "同一手机号一天最多接收5次验证码!");
				return false;
			}else{
				RedisUtil.set(p, (Integer.valueOf(num)+1)+"");
				return true;
			}
		}
	}
	
	/**
	 * 方法描述：
	 * 返回类型：boolean
	 * @param dto
	 * @param currentDate
	 * @param session
	 * @param map
	 * @param phone
	 * @param template	模板名称
	 * @return
	 * @throws Exception
	 */
	boolean postYzm(YzmDTO dto,Date currentDate,HttpSession session,Map<String,String> map,String phone,String template,boolean oldOrNew,String type) throws Exception{

		// 获取注册短信模板
		String registerInfo = emayInfoServiceImpl.getEmayTemplate(template);

		if("".equals(registerInfo)){
			map.put(Const.ERROR_INFO, "获取短信模板失败，请稍后重试!!!");
			return false;
		}
		
		// 针对该手机号控制一天最多发送三次验证码，加入缓存判断
		if(!checkRedisPhoneNum(phone,map)){
			return false;
		}

		// 生成4为数字验证码
		int yzm = (int) (Math.random() * 8999) + 1000;
		
		
		
		// 替换验证码至模板
		String info = registerInfo.replace("{yzm}", yzm+"");
		
		// 测试环境，硬编码为发送成功，验证码请看控制台
		//int state = 0;
		int state = AlidayuSMS.sendNotify(phone,info,SmsTemplateCode.VERIFICATIONCODE);

		if(state != 0){
			map.put(Const.ERROR_INFO, "发送失败，请稍后重试!!!");
			
			// 清除缓存加载的发送次数

			RedisUtil.set(phone, (Integer.valueOf(RedisUtil.get(phone))-1)+"");
			return false;
		}else{
			log.info("手机号为："+phone+"的验证码："+yzm);
		}

		dto = dto == null?new YzmDTO():dto;
		
		if(oldOrNew){
			// 保存session验证码，防止客户过滤前段验证
			dto.setOldYzm(yzm+"");
			// 保存接受验证码的手机号，防止用户恶意获取验证码后用别的号码进行注册
			dto.setOldPhone(phone);
			// 保存当前session接受验证码时间，防止恶意发送短信
			dto.setOldPostDate(currentDate);
			// 新号码发送短信次数
			dto.setCount(0);
		}else{
			// 保存session验证码，防止客户过滤前段验证
			dto.setNewYzm(yzm+"");
			// 保存接受验证码的手机号，防止用户恶意获取验证码后用别的号码进行注册
			dto.setNewPhone(phone);
			// 保存当前session接受验证码时间，防止恶意发送短信
			dto.setNewPostDate(currentDate);
			// 新号码发送短信次数
			dto.setCount(dto.getCount()+1);
		}
		
		session.setAttribute(type, dto);
		return true;
	}
	
	/**
	 * 方法描述：
	 * 返回类型：boolean
	 * @param session
	 * @param map	返回错误信息用
	 * @param yzm	前台传递的验证码
	 * @param phone	前台传递的手机号
	 * @param isVP	是否验证手机号码与接收验证码的手机号一致
	 * @return
	 */
	@Override
	public boolean saveVerify(HttpSession session,Map<String,String> map,String yzm,String uPhone,String template,boolean isVP,boolean oldOrNew){
		// 默认返回失败状态
		map.put(Const.RESPONSE_STATE, "500");
		
		// 获取缓存中验证码对象
		YzmDTO dto = new YzmDTO();
		
		String type = type(template);
		
		dto = (YzmDTO) session.getAttribute(type);
		
		if(dto == null){
			map.put(Const.ERROR_INFO, "请先进行手机验证!");
			return false;
		}
		
		//缓存中验证码
		String sessionyzm = "";
		//缓存中手机号
		String sessionPhone = "";
		//缓存中验证码时间
		Date postDate = new Date();
		
		if(oldOrNew){
			sessionyzm = dto.getOldYzm();
			sessionPhone = dto.getOldPhone();
			postDate = dto.getOldPostDate();
		}else{
			sessionyzm = dto.getNewYzm();
			sessionPhone = dto.getNewPhone();
			postDate = dto.getNewPostDate();
		}
		
		// 匹配验证码
		if (!yzm.equals(sessionyzm)) {
			map.put(Const.ERROR_INFO, "验证码错误!");
			return false;
		}
		// 匹配验证码对应的手机号
		else if (isVP && !sessionPhone.equals(uPhone)) {
			map.put(Const.ERROR_INFO, "该号码不是接受验证码的手机号!");
			return false;
		}
		
		Date curDate = new Date();
		
		// 判断验证码有效时间为5分钟
		long ss = (curDate.getTime() - postDate.getTime())/1000;
		if(ss/60 >= YZM_VALID_MINUTE){
			map.put(Const.ERROR_INFO, "验证码已经失效!");
			return false;
		}
		
		map.put(Const.RESPONSE_STATE, Const.RESPONSE_SUCCESS);
		return true;
	}

	public String type(String template){
		String type = "";
		if("registerInfo".equals(template)){
			//注册
			type = Const.REGISTER_PHONE_OBJ;
		}else if("oldPhoneInfo".equals(template)){
			//更换手机号
			type = Const.UPDATE_PHONE_OBJ;
		}else if("newPhoneInfo".equals(template)){
			//绑定新手机号
			type = Const.UPDATE_PHONE_OBJ;
		}else if("updatePassword".equals(template)){
			//修改密码验证手机
			type = Const.UPDATE_PASSWORD_OBJ;
		}else if("withdrawals".equals(template)){
			//申请提现验证
			type = Const.REGISTER_PHONE_OBJ;
		}else if("setPayPwd".equals(template)){
			//修改支付密码
			type = Const.UPDATE_PASSWORD_OBJ;
		}else if("bindingEmail".equals(template)){
			//绑定邮箱
			type = Const.EMAIL_PHONE_OBJ;
		}else if("updateEmail".equals(template)){
			//更换绑定邮箱
			type = Const.UPDATE_EMAIL_OBJ;
		}else if("forget".equals(template)){
			//找回密码
			type = Const.FORGET_PHONE_OBJ;
		}else if("bindingPhoneInfo".equals(template)){
			//绑定手机号
			type = Const.BINDING_PHONE;
		}else{
			//注册
			type = Const.REGISTER_PHONE_OBJ;
		}
		return type;
	}
	
}
