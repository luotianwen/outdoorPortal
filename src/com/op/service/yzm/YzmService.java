package com.op.service.yzm;

import java.util.Map;

import javax.servlet.http.HttpSession;
/**
 * =============================================================
 * 项目名：outdoorPortal
 * 类描述：验证码接口
 * 创建人：Yan
 * 创建时间： 2016-4-7
 * modification list：
 * =============================================================
 */
public interface YzmService {

	/**
	 * 方法描述：验证短信验证码时间
	 * 返回类型：boolean
	 * @param session
	 * @param map	返回错误信息用
	 * @param phone	发送验证码的手机号
	 * @param template	验证码模板名称
	 * @return
	 */
	void validateYzmTime(HttpSession session,Map<String,String> map,String phone,String template,boolean oldOrNew);
	
	/**
	 * 方法描述：保存验证
	 * 返回类型：boolean
	 * @param session
	 * @param map	返回错误信息用
	 * @param yzm	前台传递的验证码
	 * @param phone	前台传递的手机号
	 * @param isVP	是否验证当前手机号码与接收验证码的手机号一致
	 * @return
	 */
	boolean saveVerify(HttpSession session,Map<String,String> map,String yzm,String uPhone,String type,boolean isVP,boolean oldOrNew);
}
