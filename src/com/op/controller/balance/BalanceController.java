package com.op.controller.balance;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.op.controller.BaseController;
import com.op.dto.usercenter.userInfo.PayPasswordDTO;
import com.op.entity.balance.Balance;
import com.op.entity.users.Users;
import com.op.service.balance.BalanceService;
import com.op.util.Const;
import com.op.util.MD5;

/** 
 * 用户资金账户余额表(balance)Controller
 * @author Win Zhong 
 * @version Revision: 1.00 
 *  Date: 2016-02-29 17:14:09 
 */ 
@Controller
@RequestMapping(value="/balance")
public class BalanceController extends BaseController {
	
	@Resource(name="balanceServiceImpl")
	private BalanceService balanceServiceImpl;

	/**
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="details")
	public ModelAndView details(HttpSession session,ModelAndView mv)throws Exception{ 
		Users user = this.getSessionUser();
		Balance balance = balanceServiceImpl.getBalanceByUserId(user.getuId());
		user.setBalanceId(balance.getId());
		boolean isSetPayPwd = balanceServiceImpl.getPayPwd(user.getuId()) == null ? false : true;
		System.err.println(balance.getState()+"判断用户是否设置支付密码:"+isSetPayPwd);
		mv.addObject("isSetPayPwd", isSetPayPwd); 
		mv.addObject("userName", user == null?null:user.getuName()); 
		mv.addObject("balance",balance);
		mv.setViewName("usercenter/user/wallet/my-wallet");
		return mv;
	}

	/**
	 * 显示修改支付密码页面
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="showModifyPayPwd")
	public ModelAndView showModifyPayPwd(HttpSession session,ModelAndView mv)throws Exception{ 
		Users user = this.getSessionUser();
		mv.addObject("userName", user == null?null:user.getuName()); 
		mv.addObject("uPhone", user == null?null:user.getuPhone()); 
		//mv.addObject("publicKey",RSAUtils.getPublicKeyMap());
		mv.addObject("isSetPayPwd", balanceServiceImpl.getPayPwd(user.getuId()) == null ? false : true); 
		mv.setViewName("usercenter/user/wallet/payPassword");
		return mv;
	}

	/**
	 * 修改或设置支付密码
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="modifyPayPwd")
	@ResponseBody
	public Map<String,Object> modifyPayPwd(HttpSession session,PayPasswordDTO payPasswordDTO)throws Exception{
		Map<String,Object> map = new HashMap<String,Object>();
		Users user = this.getSessionUser();
		System.err.println(session.getAttribute("yzm"+user.getuPhone()));
		Object yzm = session.getAttribute("yzm"+user.getuPhone());
		map.put(Const.RESPONSE_STATE, "500");
		if(null == yzm){
			map.put(Const.ERROR_INFO, "验证码已失效！");
			return map;
		}else if(!yzm.toString().equals(payPasswordDTO.getCaptcha())){
			map.put(Const.ERROR_INFO, "验证码不正确！");
			return map;
		}else{
			session.removeAttribute("yzm"+user.getuPhone());
		}
		//验证支付密码，支付密码至少包含一个大写字母、一个小写字母，长度至少8位
		Pattern pattern = Pattern.compile("^(?=^.{8,15}$)((?=.*\\d)|(?=.*\\W+))(?![.\\n])(?=.*[A-Z])(?=.*[a-z]).*$");
		Matcher matcher = pattern.matcher(payPasswordDTO.getPayPassword());
		if(!matcher.matches()){
			map.put(Const.ERROR_INFO, "支付密码至少包含一个大写字母、一个小写字母，长度至少8位！");
			return map;
		}
		if(!payPasswordDTO.getPayPassword().equals(payPasswordDTO.getCheckPayPassword())){
			map.put(Const.ERROR_INFO, "两次输入密码不一致！");
			return map;
		}
		//以保存的支付密码
		String payPwd = balanceServiceImpl.getPayPwd(user.getuId());
		if(null != payPwd){
			if(!payPwd.equals(MD5.md5(payPasswordDTO.getOldPayPassword()))){
				map.put(Const.ERROR_INFO, "原密码错误！");
				return map;
			}
		}
		payPasswordDTO.setUserId(user.getuId());
		boolean result = balanceServiceImpl.setPayPwd(payPasswordDTO);
		if(!result){
			map.put(Const.ERROR_INFO, "支付密码设置异常！");
			return map;
		}
		map.put(Const.RESPONSE_STATE, Const.RESPONSE_SUCCESS);
		return map;
	} 
 
	/**
	 * @ModelAttribute 这个注解作用.每次执行controllor前都会先执行这个方法
	 * @param request
	 */
	
	@ModelAttribute
	@Override
	public void init(HttpServletRequest request,ModelAndView mv) {
		//设置左边菜单选中
		mv.addObject("left_menu_name","我的钱包");
		
		Map<String,Object> map = new HashMap<String,Object>();
		// 当前导航标签
		map.put("center", "我的玩嘛");
		// 当前用户信息
		Users user = this.getSessionUser();
		map.put("un", user);
		mv.addObject("map", map);
		
	}
	
} 
