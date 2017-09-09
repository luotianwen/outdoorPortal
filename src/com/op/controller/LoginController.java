package com.op.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.util.SavedRequest;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.op.entity.users.Users;
import com.op.util.Common;
import com.op.util.Const;
import com.op.util.rsa.PublicKeyMap;
import com.op.util.rsa.RSAUtils;

/**
 * 项目名：outdoorPortal
 * 类描述：登录
 * 创建人：Yan
 * 创建时间： 2015-11-30 上午9:50:16
 * 最后修改时间：2015-11-30上午9:50:16
 */
@Controller
@RequestMapping(value = "/login")
public class LoginController {
	
	/**
	 * 异步登录
	 */
	@RequestMapping(value = "/asyn", method = RequestMethod.POST)
	@ResponseBody
	public Map<String,String> asyn(HttpServletRequest request, 
			@RequestParam(value="n") String username, 
			@RequestParam(value="p") String password,
			@RequestParam(value="y") String yzm,
			String c) {
		HttpSession session = request.getSession();
		Map<String,String> map = new HashMap<String,String>();
		
		// 解密
		password = RSAUtils.decryptStringByJs(password); 
		
		// 验证码
		int count = 0;
		if(!"true".equals(c)){
			count = this.countSessionLoginNum(session);
			if(count > 3){
				if(!yzm.equals(request.getSession().getAttribute(Const.YZM))){
					map.put(Const.RESPONSE_STATE, "400");
					map.put(Const.SESSION_LOGIN_ERROR_NUM, count+"");
					return map;
				}
			}
		}
		
		UsernamePasswordToken token = new UsernamePasswordToken(username.trim(), password.trim());
		token.setRememberMe(true);

		// 获取当前的Subject
		Subject currentUser = SecurityUtils.getSubject();
		boolean flag = false;
		try {
			// 获取登录IP
			currentUser.getSession().setAttribute(Const.LOGIN_IP, Common.getRemoteAddrIp(request));
			// 在调用了login方法后,SecurityManager会收到AuthenticationToken（验证token）,并将其发送给已配置的Realm执行必须的认证检查
			currentUser.login(token);
			flag = true;
		} catch (Exception uae) {
			flag = false;
			map.put(Const.RESPONSE_STATE, "500");
		}
		// 验证是否登录成功
		if (flag) {

			// 是否有拦截url
			SavedRequest savedRequest  = WebUtils.getSavedRequest(request);
			
			if(savedRequest != null){
				/*if(savedRequest.getRequestUrl().contains("outdoorPortal")){
					// 重定向到之前的拦截页面
					map.put("url", savedRequest.getRequestUrl().substring("/".length()));
				}else{*/
					map.put("url", savedRequest.getRequestUrl());
				//}
				
			}else{
				map.put("url", "index.html");
			}
			
			map.put(Const.RESPONSE_STATE, Const.RESPONSE_SUCCESS);
			
			Users user = (Users) currentUser.getSession().getAttribute(Const.SESSION_USER);
			map.put("uId", user.getuId());
			map.put("uType", user.getuType()+"");
			map.put("uName", user.getuName());
			map.put("uHeadImg", user.getuHeadImg());
			session.removeAttribute(Const.SESSION_LOGIN_ERROR_NUM);
		} else {
			map.put(Const.RESPONSE_STATE, "500");
			map.put(Const.SESSION_LOGIN_ERROR_NUM, count+"");
		}
		return map;
	}
	/**
	 * 方法描述：计算当前session登录次数
	 * 返回类型：Integer
	 * @param session
	 * @return
	 */
	public Integer countSessionLoginNum(HttpSession session){
		Integer loginNum = 0;
		// 记录当前session次数
		Integer sessionLoginNum = (Integer) session.getAttribute(Const.SESSION_LOGIN_ERROR_NUM);
		if(sessionLoginNum == null){
			session.setAttribute(Const.SESSION_LOGIN_ERROR_NUM, 1);
		}else{
			session.setAttribute(Const.SESSION_LOGIN_ERROR_NUM, sessionLoginNum + 1);
		}
		
		return loginNum;
	}
	
	/**
	 * 方法描述：注销 返回类型：String
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/logout")
	public String logout(HttpServletRequest request) {
		SecurityUtils.getSubject().logout();
		return "redirect:/login.html";
	}
	
	/**
	 * js加密
	 * @param mv
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="mi")
	@ResponseBody
	public PublicKeyMap mi(){ 
		return RSAUtils.getPublicKeyMap();
	}
}
