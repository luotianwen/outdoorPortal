package com.op.controller;

import java.util.HashMap;
import java.util.Map;

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
import com.op.util.MD5;
import  com.op.util.jedis.RedisUtil;
import com.op.util.mail.EmailUtil;

/**
 * 项目名：outdoorPortal
 * 类描述：找回密码
 */
@Controller
@RequestMapping(value = "/forget")
public class ForgetController {

	@Resource(name = "usersServiceImpl")
	UsersService usersServiceImpl;

	@Resource(name="emayInfoServiceImpl")
	EmayInfoService emayInfoServiceImpl;
	
	@Resource(name="yzmServiceImpl")
	YzmService yzmServiceImpl;
	
	@Resource(name="emailUtil")
	EmailUtil emailUtil;
	
	protected Logger logger = Logger.getLogger(this.getClass());
	
	/**
	 * 方法描述：发送验证码 返回类型：Map<String,Object>
	 * 
	 * @return
	 */
	@RequestMapping(value = "/postYzm",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, String> postYzm(@RequestParam(value="n") String n,String type,String verify,HttpSession session) {
		Map<String,String> map = new HashMap<String,String>();
		map.put(Const.RESPONSE_STATE, "500");
		
		if("mobile".equals(type)){
			// 验证手机号码唯一性
			boolean flag = false;
			try {
				flag = usersServiceImpl.checkUserPhoneUnique(n.trim());
			} catch (Exception e) {
				map.put(Const.ERROR_INFO, "验证手机号唯一性异常!");
				return map;
			}
			if(flag){
				map.put(Const.ERROR_INFO, "该手机号码尚未注册，<a href='register.html'>去注册</a>!");
				return map;
			}
			
			if(StringUtils.isEmpty(verify)){
				map.put(Const.ERROR_INFO, "请输入验证码!");
				return map;
			}
			if(!session.getAttribute("forget_verifyCode").equals(verify)){
				map.put(Const.ERROR_INFO, "验证码不正确!");
				return map;
			}
			
			// 验证码
			yzmServiceImpl.validateYzmTime(session, map, n, "forget",true);
		}else if("email".equals(type)){
			boolean flag = false;
			try {
				flag = usersServiceImpl.checkUserEmailUnique(n.trim());
			} catch (Exception e) {
				map.put(Const.ERROR_INFO, "验证邮箱账号唯一性异常!");
				return map;
			}
			if(flag){
				map.put(Const.ERROR_INFO, "该邮箱账号尚未绑定账号，请查证后再试!");
				return map;
			}
			
			if(StringUtils.isEmpty(verify)){
				map.put(Const.ERROR_INFO, "请输入验证码!");
				return map;
			}
			if(!session.getAttribute("forget_verifyCode").equals(verify)){
				map.put(Const.ERROR_INFO, "验证码不正确!");
				return map;
			}
			
			String yzm = emailUtil.sendCheckEmail(n,"Forget");

    		
    		String key = "forget_"+n+"_";
    		RedisUtil.set(key,yzm);
    		RedisUtil.expire(key.getBytes(), 259200);//259200秒过期 即三天后自动删除缓存
    		
    		map.put(Const.RESPONSE_STATE, "200");
    		map.put("url", emailUrl(n));
    		map.put(Const.SUCCESS_INFO, "验证码已经发送到你的邮箱，请注意查收");
		}else{
			map.put(Const.ERROR_INFO, "验证异常，请刷新后再试！");
		}
		return map;
	}
	
	/**
	 * 方法描述：修改用户密码 返回类型：ModelAndView
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/update",method=RequestMethod.POST)
	@ResponseBody
	public Map<String,String> register(Users user, 
			HttpServletRequest request, 
			@RequestParam(value="y") String yzm,String n,String type) {
		Map<String,String> map = new HashMap<String,String>();
		
		HttpSession session = request.getSession();
		
		boolean bool = false;

		String key = "";
		
		if("mobile".equals(type)){
			bool = yzmServiceImpl.saveVerify(session, map, yzm, n,"forget", true,true);
			user.setuPhone(n);
		}else if("email".equals(type)){

    		
    		key = "forget_"+n+"_";
    		String jedisyzm = RedisUtil.get(key);
			
			if(jedisyzm!=null&&jedisyzm.equals(yzm)){
				bool = true;
				map.put(Const.RESPONSE_STATE, "200");
				user.setuEmail(n);
			}else{
				map.put(Const.RESPONSE_STATE, "500");
				map.put(Const.ERROR_INFO, "验证码错误！");
			}
		}else{
			bool = false;
			map.put(Const.RESPONSE_STATE, "500");
			map.put(Const.ERROR_INFO, "数据异常，请刷新后再试！");
		}
		
		if(bool){
			// 获取最后一次登录IP
			user.setuLoginIp(Common.getRemoteAddrIp(request));
			try {
				user.setuPassword(MD5.md5(user.getuPassword()));
				// 保存用户
				usersServiceImpl.updateUserPassword(user);
				
				session.removeAttribute(Const.REGISTER_PHONE_OBJ);
				session.setAttribute(Const.SESSION_USER, user);// 保存当前登录用户到session
			} catch (Exception e) {
				map.put(Const.ERROR_INFO, Const.ERROR_INFO_STR);
			}
		}
		return map;
	}

	
	/**
	 * 获取邮箱后缀
	 * 判断邮箱url
	 * @param email
	 * @return
	 */
    public String emailUrl(String email){
    	String url = email.split("@")[1];
    	url = url.toLowerCase();
        if ("163.com".equals(url)) {
            return "http://mail.163.com";
        } else if ("vip.163.com".equals(url)) {
            return "http://vip.163.com";
        } else if ("126.com".equals(url)) {
            return "http://mail.126.com";
        } else if ("qq.com".equals(url) || "vip.qq.com".equals(url) || "foxmail.com".equals(url)) {
            return "http://mail.qq.com";
        } else if ("gmail.com".equals(url)) {
            return "http://mail.google.com";
        } else if ("sohu.com".equals(url)) {
            return "http://mail.sohu.com";
        } else if ("tom.com".equals(url)) {
            return "http://mail.tom.com";
        } else if ("vip.sina.com".equals(url)) {
            return "http://vip.sina.com";
        } else if ("sina.com.cn".equals(url) || "sina.com".equals(url)) {
            return "http://mail.sina.com.cn";
        } else if ("yahoo.com.cn".equals(url) || "yahoo.cn".equals(url)) {
            return "http://mail.cn.yahoo.com";
        } else if ("yeah.net".equals(url)) {
            return "http://www.yeah.net";
        } else if ("21cn.com".equals(url)) {
            return "http://mail.21cn.com";
        } else if ("hotmail.com".equals(url)) {
            return "http://www.hotmail.com";
        } else if ("sogou.com".equals(url)) {
            return "http://mail.sogou.com";
        } else if ("188.com".equals(url)) {
            return "http://www.188.com";
        } else if ("139.com".equals(url)) {
            return "http://mail.10086.cn";
        } else if ("189.cn".equals(url)) {
            return "http://webmail15.189.cn/webmail";
        } else if ("wo.com.cn".equals(url)) {
            return "http://mail.wo.com.cn/smsmail";
        } else {
            return "http://www."+url;
        }
    }
}
