package com.op.controller.withdrawals;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.op.controller.BaseController;
import com.op.entity.users.Users;
import com.op.entity.withdrawals.WithdrawalsAccount;
import com.op.entity.withdrawals.WithdrawalsType;
import com.op.service.withdrawals.WithdrawalsAccountService;
import com.op.service.withdrawals.WithdrawalsTypeService;
import com.op.util.CheckBankCard;
import com.op.util.Const;
/** 
 * 用户提现账户(withdrawalsAccount)Controller
 * @author Win Zhong 
 * @version Revision: 1.00 
 *  Date: 2016-02-24 15:11:37 
 */ 
@Controller
@RequestMapping(value="/wAccount")
public class WithdrawalsAccountController extends BaseController {
	
	@Resource(name="withdrawalsAccountServiceImpl")
	private WithdrawalsAccountService withdrawalsAccountServiceImpl;
	/**
	 * 提现类型
	 */
	@Resource(name="withdrawalsTypeServiceImpl")
	private WithdrawalsTypeService withdrawalsTypeServiceImpl;

	
	/**
	 * 前往用户添加用户提现账户页面
	 * @param mv
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="addAccount")
	public ModelAndView addAccount(ModelAndView mv) throws Exception{
		List<WithdrawalsType> withdrawalsTypeList = withdrawalsTypeServiceImpl.getWithdrawalsTypeList();
		mv.addObject("withdrawalsTypeList",withdrawalsTypeList);
		mv.setViewName("usercenter/withdrawals/addWithdrawalsAccount");
		return mv;
	}	
	
	/**
	 * 前往用户添加用户提现账户页面
	 * @param mv
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="show/{id}")
	public ModelAndView show(ModelAndView mv,@PathVariable("id") String id) throws Exception{
		Users user = this.getSessionUser();
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("id", id);
		map.put("user_id", user.getuId());
		WithdrawalsAccount account = withdrawalsAccountServiceImpl.findWithdrawalsAccountById(map);
		List<WithdrawalsType> withdrawalsTypeList = withdrawalsTypeServiceImpl.getWithdrawalsTypeList();
		mv.addObject("withdrawalsTypeList",withdrawalsTypeList);
		mv.addObject("account",account);
		mv.setViewName("usercenter/withdrawals/editWithdrawalsAccount");
		return mv;
	}	
	
	/**
	 * 前往用户添加用户提现账户页面
	 * @param mv
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="edit")
	public ModelAndView edit(ModelAndView mv,@PathVariable("id") String id) throws Exception{
		Users user = this.getSessionUser();
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("id", id);
		map.put("user_id", user.getuId());
		WithdrawalsAccount account = withdrawalsAccountServiceImpl.findWithdrawalsAccountById(map);
		List<WithdrawalsType> withdrawalsTypeList = withdrawalsTypeServiceImpl.getWithdrawalsTypeList();
		mv.addObject("withdrawalsTypeList",withdrawalsTypeList);
		mv.addObject("account",account);
		mv.setViewName("usercenter/withdrawals/editWithdrawalsAccount");
		return mv;
	}
	/**
	 * 保存用户提现账户
	 * @param withdrawalsAccount
	 * @return
	 */
	@RequestMapping(value="del/{id}")
	@ResponseBody
	public Map<String,Object> del(@PathVariable("id") String id){
		Map<String,Object> map = new HashMap<String,Object>();
		try {
			Users user = this.getSessionUser();
			map.put("id",id);
			map.put("user_id",user.getuId());
			withdrawalsAccountServiceImpl.deletewAccount(map);
			map.clear();
			map.put(Const.RESPONSE_STATE, Const.RESPONSE_SUCCESS);
		} catch (Exception e) {
			logger.info(e);
			e.printStackTrace();
			map.put(Const.RESPONSE_STATE, 500);
			map.put(Const.ERROR_INFO, "保存异常!!!");
		}
		return map;
	}
		/**
		 * 保存用户提现账户
		 * @param withdrawalsAccount
		 * @return
		 */
		@RequestMapping(value="save")
		@ResponseBody
		public Map<String,Object> save(WithdrawalsAccount withdrawalsAccount){
			Map<String,Object> map = new HashMap<String,Object>();
			try {
				WithdrawalsType withdrawalsType = withdrawalsTypeServiceImpl.findWithdrawalsTypeById(withdrawalsAccount.getWt_id());
				if(withdrawalsType == null){
					map.put(Const.RESPONSE_STATE, 500);
					map.put(Const.ERROR_INFO, "提现账户类型选择错误");
				}else if("1".equals(withdrawalsType.getType()) && !CheckBankCard.checkBankCard(withdrawalsAccount.getAccount_number())){ //检查银行卡账号是否正确
					map.put(Const.RESPONSE_STATE, 500);
					map.put(Const.ERROR_INFO, "账户账号输入错误");
				}else{
					
					Users user = this.getSessionUser();
					withdrawalsAccount.setUser_id(user.getuId());
					withdrawalsAccountServiceImpl.saveWithdrawalsAccount(withdrawalsAccount); 
					map.clear();
					map.put("id",withdrawalsAccount.getId());
					map.put(Const.RESPONSE_STATE, Const.RESPONSE_SUCCESS);
				}
			} catch (Exception e) {
				logger.info(e);
				e.printStackTrace();
				map.put(Const.RESPONSE_STATE, 500);
				map.put(Const.ERROR_INFO, "保存异常!!!");
			}
			return map;
		}
		 
		/**
		 * 设置默认提现账户
		 * @param withdrawalsAccount
		 * @return
		 */
		@RequestMapping(value="setDefault/{id}")
		@ResponseBody
		public Map<String,Object> setDefaultAccount(@PathVariable("id") String id){
			Map<String,Object> map = new HashMap<String,Object>();
			try {
				Users user = this.getSessionUser();
				map.put("id",id);
				map.put("user_id",user.getuId());
				withdrawalsAccountServiceImpl.setDefaultAccount(map); 
				map.clear();
				map.put("id",id);
				map.put(Const.RESPONSE_STATE, Const.RESPONSE_SUCCESS);
			} catch (Exception e) {
				logger.info(e);
				e.printStackTrace();
				map.put(Const.RESPONSE_STATE, 500);
				map.put(Const.ERROR_INFO, "保存异常!!!");
			}
			return map;
		}
 
}
