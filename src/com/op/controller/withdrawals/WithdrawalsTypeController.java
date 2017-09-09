package com.op.controller.withdrawals;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.op.controller.BaseController;
import com.op.service.withdrawals.WithdrawalsTypeService;

/** 
 * 用户提现类型(withdrawalsType)Controller
 * @author Win Zhong 
 * @version Revision: 1.00 
 *  Date: 2016-02-24 15:11:37 
 */ 
@Controller
@RequestMapping(value="/withdrawalsType")
public class WithdrawalsTypeController extends BaseController {
	
	@Resource(name="withdrawalsTypeServiceImpl")
	private WithdrawalsTypeService withdrawalsTypeServiceImpl;

	 
	 
}
