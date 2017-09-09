package com.op.controller.withdrawals;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.op.controller.BaseController;
import com.op.dto.usercenter.withdrawals.CashWithdrawalsDTO;
import com.op.dto.usercenter.withdrawals.WithdrawalsApplyDTO;
import com.op.entity.balance.Balance;
import com.op.entity.users.Users;
import com.op.entity.withdrawals.CashWithdrawals;
import com.op.entity.withdrawals.WithdrawalsAccount;
import com.op.entity.withdrawals.WithdrawalsType;
import com.op.plugin.page.Page;
import com.op.service.balance.BalanceService;
import com.op.service.withdrawals.CashWithdrawalsService;
import com.op.service.withdrawals.WithdrawalsAccountService;
import com.op.service.withdrawals.WithdrawalsTypeService;
import com.op.util.DateUtil;
import com.op.util.MD5;
import com.op.util.Tools;
import com.op.util.rsa.RSAUtils;
/** 
 * 用户资金提现申请表(cashWithdrawals)Controller
 * @author Win Zhong 
 * @version Revision: 1.00 
 *  Date: 2016-02-25 13:42:49 
 */ 
@Controller
@RequestMapping(value="/withdrawals")
public class CashWithdrawalsController extends BaseController {
	
	@Resource(name="cashWithdrawalsServiceImpl")
	private CashWithdrawalsService cashWithdrawalsServiceImpl;
	/**
	 * 用户提现账户
	 */
	@Resource(name="withdrawalsAccountServiceImpl")
	private WithdrawalsAccountService withdrawalsAccountServiceImpl;
	
	@Resource(name="balanceServiceImpl")
	private BalanceService balanceServiceImpl;
	
	@Resource(name="withdrawalsTypeServiceImpl")
	private WithdrawalsTypeService withdrawalsTypeServiceImpl;
	
	protected Logger logger = Logger.getLogger(this.getClass());
	/**
	 * 提现申请
	 * @param mv
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="apply")
	public ModelAndView sysList(ModelAndView mv){ 
		try {
			Users user = this.getSessionUser();
			Balance balance = balanceServiceImpl.getBalanceByUserId(user.getuId());
			mv.addObject("balance",balance);
			boolean isSetPayPwd = balance.getPayPassword() == null ? false : true;
			System.err.println(balance.getState()+"判断用户是否设置支付密码:"+isSetPayPwd);
			mv.addObject("isSetPayPwd", isSetPayPwd); 
			List<WithdrawalsAccount> accountList = withdrawalsAccountServiceImpl.findWithdrawalsAccountByUserId(user.getuId());
			mv.addObject("accountList",accountList);
			mv.addObject("publicKey",RSAUtils.getPublicKeyMap());
			mv.addObject("userName", user == null?null:user.getuName());
			mv.addObject("left_menu_name", "我的钱包");
			this.setUserCenterHeaderMap(mv,  "我的玩嘛", null);
			mv.setViewName("usercenter/withdrawals/my-wallet-get");
		} catch (Exception e) {
			logger.error("前往提现申请页面出错：", e); 
			mv.setViewName("500");
		}
		return mv;
	}
	
	/**
	 * 提现申请受理
	 * @param mv
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="acceptance")
	public String acceptance(WithdrawalsApplyDTO withdrawalsApplyDTO){ 
		int result;
		try {
			System.out.println("====password====="+this.getParameter("password"));
			Users user = this.getSessionUser();
			//账户资金信息
			Balance balance = balanceServiceImpl.getBalanceByUserId(user.getuId());
			//获取是否设置了支付密码
			boolean isSetPayPwd = balance.getPayPassword() == null ? false : true;
			//支付密码
			String payPassword = null;
			if(isSetPayPwd){//如果设置了支付密码，解密用户输入的支付密码
				payPassword = RSAUtils.decryptStringByJs(withdrawalsApplyDTO.getPayPassword());
			}
			//判断资金账户是否可用
			if("1".equals(balance.getState())){
				BigDecimal money = new BigDecimal(withdrawalsApplyDTO.getMoney());
				result = balance.getAvailable_money().compareTo(money);
				withdrawalsApplyDTO.setToday_date(DateUtil.getDay());
				withdrawalsApplyDTO.setUser_id(user.getuId());
				int count = cashWithdrawalsServiceImpl.findCashWithdrawalsDayFrequency(withdrawalsApplyDTO);
				if(withdrawalsApplyDTO.getMoney() < 50){
					//mv.addObject("errorMsg","提现申请失败，单次提现金额必须是不小于50且不大于50000");
					result = 101;
				}else if(count >= 3){
					//mv.addObject("errorMsg","提现申请失败，当天可申请提现次数最多为1次");
					result = 102;
				}else if(result == -1){
					//mv.addObject("errorMsg","提现申请失败，账户可用余额不足"+balance.getAvailable_money()+"元");
					result = 103;
				}else if(isSetPayPwd && StringUtils.isEmpty(payPassword)){
					//mv.addObject("errorMsg","支付密码错误");
					result = 104;
				}else if(isSetPayPwd && !balance.getPayPassword().equals(MD5.md5(payPassword))){
					//mv.addObject("errorMsg","支付密码错误");
					result = 104;
				}else if(StringUtils.isEmpty(withdrawalsApplyDTO.getAccount_id())){
					//mv.addObject("errorMsg","参数错误");
					result = 105;
				}else{
					//for(int i =0;i<50;i++){
					//2016022514419856
					WithdrawalsType withdrawalsType = withdrawalsTypeServiceImpl.findWithdrawalsTypeByAccountId(withdrawalsApplyDTO.getAccount_id());
					CashWithdrawals cw = new CashWithdrawals();
					//money = new BigDecimal(Math.random()*9000+1000);
					cw.setId(DateUtil.getTimes() + Tools.getRandomNum());
					cw.setCw_cash(money);
					cw.setUser_id(user.getuId());
					cw.setWa_id(withdrawalsApplyDTO.getAccount_id());
					//提现状态（1：提交申请；2：财务处理；3：提现完成；4：提现失败；5：用户取消）
					cw.setCw_state("1");
					//计算手续费   暂不用           默认0.00
					cw.setFee_cash(money.multiply(withdrawalsType.getFee()).setScale(2,   BigDecimal.ROUND_HALF_UP));
					cw.setApplication_time(new Date());
					cashWithdrawalsServiceImpl.saveCashWithdrawals(cw);
					//}
					result = 200;
				}
			}else{
				result = 144;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error("提现申请失败，原因：", e); 
			result = 500;
		}
		return "redirect:/withdrawals/apply/result-"+result+".html";
	}
	
	// , RedirectAttributes redirectAttributes redirectAttributes.addFlashAttribute("result", 104); Map<String,?> map = RequestContextUtils.getInputFlashMap(request);
	
	/**
	 * 提现申请受理
	 * @param mv
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "apply/result-{result}")
	public ModelAndView applyResult(ModelAndView mv,HttpServletRequest request,@PathVariable("result") int result){ 
		mv.addObject("left_menu_name", "我的钱包");
		this.setUserCenterHeaderMap(mv,  "我的玩嘛", null);
		System.err.println(result);
		mv.setViewName("usercenter/withdrawals/my-wallet-get-error");
		mv.addObject("errorMsg","提现申请失败，处理异常");
		if(result == 101){
			mv.addObject("errorMsg","提现申请失败，单次提现金额必须是不小于50且不大于50000");
		}else if(result == 102){
			mv.addObject("errorMsg","提现申请失败，当天可申请提现次数最多为3次");
		}else if(result == 103){
			mv.addObject("errorMsg","提现申请失败，账户可用余额不足");
		}else if(result == 104){
			mv.addObject("errorMsg","支付密码错误");
		}else if(result == 105){
			mv.addObject("errorMsg","参数错误");
		}else if(result == 500){
			mv.addObject("errorMsg","提现申请失败，服务器内部错误");
		}else if(result == 144){
			mv.addObject("errorMsg","账户不可用，如有问题，请咨询客服");
		}else if(result == 200){
			mv.setViewName("usercenter/withdrawals/my-wallet-get-02");
		}
		return mv;
	}
	/**
	 * 提现申请列表
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="list")
	public ModelAndView list(ModelAndView mv,Page<CashWithdrawalsDTO> page,String state,HttpServletRequest request,CashWithdrawalsDTO cashWithdrawalsDTO){
		try {
			Users user = this.getSessionUser();
			Balance balance = balanceServiceImpl.getBalanceByUserId(user.getuId());
			cashWithdrawalsDTO.setUser_id(user.getuId());
			cashWithdrawalsDTO.setCw_state(state);
			page.setT(cashWithdrawalsDTO);
			//page.setRequest(request);
			List<CashWithdrawals>  withdrawalsList = cashWithdrawalsServiceImpl.getListPage(page);
			mv.addObject("cw", cashWithdrawalsDTO); 
			mv.addObject("withdrawalsList",withdrawalsList); 
			mv.addObject("page",page);
			mv.setViewName("usercenter/withdrawals/my-wallet-txmx");
			mv.addObject("left_menu_name", "我的钱包");
			this.setUserCenterHeaderMap(mv,  "我的玩嘛", null);
			mv.addObject("balance",balance);
			return mv;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error("查看提现申请列表异常：", e); 
			mv.setViewName("500");
			return mv;
		}
		//System.out.println(request.getRequestURI()+"**************"+UrlParamsFormatUtil.getUrlParamsByMap(this.findKeyMapByRequest(request)));
		
	}
	 
	/**
	 * 根据Id获取申请详细信息
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="details/{id}")
	public ModelAndView sysList(ModelAndView mv,@PathVariable("id") String id){
		try {
			Users user = this.getSessionUser();
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("id", id);
			map.put("user_id", user.getuId());
			CashWithdrawals  withdrawals = cashWithdrawalsServiceImpl.findCashWithdrawalsById(map);
			mv.addObject("withdrawals",withdrawals);
			mv.addObject("left_menu_name", "我的钱包");
			this.setUserCenterHeaderMap(mv,  "我的玩嘛", null);
			mv.setViewName("usercenter/withdrawals/my-wallet-txxq");
			return mv;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error("根据Id获取申请详细信息异常：", e); 
			mv.setViewName("500");
			return mv;
		}
	}
		 
	 
	/**
	 * @ModelAttribute 这个注解作用.每次执行controllor前都会先执行这个方法
	 * @param request
	 */
	@ModelAttribute
	@Override
	public void init(HttpServletRequest request,ModelAndView mv){
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
