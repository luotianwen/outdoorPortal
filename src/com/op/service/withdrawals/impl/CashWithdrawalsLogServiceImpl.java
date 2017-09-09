package com.op.service.withdrawals.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.op.dao.BaseDao; 
import com.op.service.withdrawals.CashWithdrawalsLogService;

/** 
 * 用户资金提现申请操作日志表(cashWithdrawalsLog)接口实现类
 * @author Win Zhong 
 * @version Revision: 1.00 
 *  Date: 2016-02-25 09:36:36 
 */  
@Service("cashWithdrawalsLogServiceImpl")
public class CashWithdrawalsLogServiceImpl implements CashWithdrawalsLogService{

	@Resource(name = "baseDaoImpl")
	private BaseDao dao;
	
}
