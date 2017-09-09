package com.op.controller.balance;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.op.controller.BaseController;
import com.op.dto.balance.CashRecordDTO;
import com.op.entity.balance.Balance;
import com.op.entity.users.Users;
import com.op.plugin.page.Page;
import com.op.service.balance.BalanceService;
import com.op.service.balance.CashRecordService;

/** 
 * 账户资金明细表(cashRecord)Controller
 * @author Win Zhong 
 * @version Revision: 1.00 
 *  Date: 2016-02-29 17:14:09 
 */ 
@Controller
@RequestMapping(value="/cashRecord")
public class CashRecordController extends BaseController {
	
	@Resource(name="cashRecordServiceImpl")
	private CashRecordService cashRecordServiceImpl;
	
	@Resource(name="balanceServiceImpl")
	private BalanceService balanceServiceImpl;
	
	/**
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="recently")
	@ResponseBody
	public Object recently(Page<Users> page)throws Exception{
		Users user = this.getSessionUser();
		if(user.getBalanceId() == null){
			Balance balance = balanceServiceImpl.getBalanceByUserId(user.getuId());
			user.setBalanceId(balance.getId());
		}
		page.setT(user);
		List<CashRecordDTO> cashRecordList = cashRecordServiceImpl.getCashRecordListPageByBalanceId(page);
		page.setResultList(cashRecordList);
		return page;
	} 
}
