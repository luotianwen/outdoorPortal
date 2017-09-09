package com.op.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.op.entity.users.Users;
import com.op.service.emay.EmayInfoService;
import com.op.service.users.UsersService;
import com.op.service.yzm.YzmService;
import com.op.util.Common;
import com.op.util.Const;
import com.op.util.words.WordsAnalyzer;

/**
 * 项目名：outdoorPortal
 * 类描述：注册
 * 创建人：Yan
 * 创建时间： 2015-11-30 上午9:50:31
 * 最后修改时间：2015-11-30上午9:50:31
 */
@Controller
@RequestMapping(value = "/register")
public class RegisterController {

	@Resource(name = "usersServiceImpl")
	UsersService usersServiceImpl;

	@Resource(name="emayInfoServiceImpl")
	EmayInfoService emayInfoServiceImpl;
	
	@Resource(name="yzmServiceImpl")
	YzmService yzmServiceImpl;
	
	protected Logger logger = Logger.getLogger(this.getClass());
	
	/**
	 * 方法描述：注册新用户 返回类型：ModelAndView
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/save",method=RequestMethod.POST)
	@ResponseBody
	public Map<String,String> register(Users user, 
			HttpServletRequest request, 
			@RequestParam(value="yzm") String yzm) {
		
		Map<String,String> map = new HashMap<String,String>();

		
		// 用户昵称敏感词匹配
		Set<String> badWord = WordsAnalyzer.badWord(user.getuName());
		if(badWord.size()>0){
			map.put(Const.RESPONSE_STATE, "500");
			map.put(Const.ERROR_INFO, "包含敏感词语:"+badWord.toArray()[0]);
			return map;
		}
		
		HttpSession session = request.getSession();
		
		if(yzmServiceImpl.saveVerify(session, map, yzm, user.getuPhone(),"registerInfo", true,true)){
			// 获取最后一次登录IP
			user.setuLoginIp(Common.getRemoteAddrIp(request));
			try {
				// 保存用户
				usersServiceImpl.saveUser(user,map);
				// 验证出错
				if (Const.RESPONSE_SUCCESS.equals(map.get(Const.RESPONSE_STATE))) {
					//session.removeAttribute(Const.REGISTER_PHONE_OBJ);
					session.setAttribute(Const.SESSION_USER, user);// 保存当前登录用户到session
				}
			} catch (Exception e) {
				map.put(Const.ERROR_INFO, Const.ERROR_INFO_STR);
			}
		}
		return map;
	}

	/**
	 * 方法描述：发送验证码 返回类型：Map<String,Object>
	 * 
	 * @return
	 */
	@RequestMapping(value = "/registerYzm",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, String> postYzm(@RequestParam(value="phone") String phone,String verify, HttpSession session) {
		Map<String,String> map = new HashMap<String,String>();
		map.put(Const.RESPONSE_STATE, "500");
		
		if(StringUtils.isEmpty(verify)){
			map.put(Const.ERROR_INFO, "请输入验证码!");
			return map;
		}
		if(!session.getAttribute("register_verifyCode").equals(verify)){
			map.put(Const.ERROR_INFO, "验证码不正确!");
			return map;
		}
		
		// 验证手机号码唯一性
		boolean flag = false;
		try {
			flag = usersServiceImpl.checkUserPhoneUnique(phone.trim());
		} catch (Exception e) {
			map.put(Const.ERROR_INFO, "验证手机号唯一性异常!");
			return map;
		}
		if(!flag){
			map.put(Const.ERROR_INFO, "该手机号码已经注册过，<a href='login.html' style='color: #ff8a01;'>直接登录</a>，如果非本人注册，点击此处<a href='forget.html' style='color: #ff8a01;'>找回密码</a>!");
			return map;
		}
		
		// 验证码
		yzmServiceImpl.validateYzmTime(session, map, phone, "registerInfo",true);
		return map;
	}

	/**
	 * 方法描述：发送验证码 返回类型：Map<String,Object>
	 * 
	 * @return
	 */
	@RequestMapping(value = "/validateName",method=RequestMethod.POST)
	@ResponseBody
	public boolean validateName(@RequestParam(value="uName") String uName,HttpSession session) {
		
		Users user = (Users) session.getAttribute(Const.SESSION_USER);
		
		try {
			return usersServiceImpl.validateName(uName,user==null?null:user.getuId());
		} catch (Exception e) {
			return false;
		}
	}
	
	
}
